package dfa05;


/*
 Jim Klayder -- spring 2023

simple demo of how to make a Runnable class
that runs and pauses 
showing a counter value in the Java console

Brandon White, spring 2023
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextArea;

/*need a public void run() method to implement runnable*/
public class DFA implements Runnable {

    private int count;
    private int ms_to_sleep;
    private boolean pause;
    private final Thread thread;
    private final JTextArea textArea;

    //pass a JTextArea in so that run() can manipulate the textArea
    DFA(JTextArea textArea) {

        count = 0;
        ms_to_sleep = 500;
        pause = true;
        thread = new Thread(this);
        thread.start();
        this.textArea = textArea;
    }

    public void reset() {
        pause = true;
        count = 0;
    }

    @Override
    /*need a public void run() method to implement runnable*/
    public void run() {

        while (true) {
            if (!pause) {
                singleStep();
                textArea.append("Count: " + this.count + '\n');
            }

            try {
                thread.sleep(ms_to_sleep);
            } catch (Exception ex) {
            }
        }
    }

    public void singleStep() {
        count += 1;
        System.out.println("count:  " + count);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int i) {
        count = i;
    }

    public boolean getPause() {
        return pause;
    }

    public void setSleep(int i) {
        ms_to_sleep = i;
    }

    public int getSleep() {
        return ms_to_sleep;
    }

    public boolean validateDfa(String input) {
        String[] lines = input.split("\\r?\\n");
        List<String> filteredLines = new ArrayList<>();

        // Filter out comment lines and remove superfluous whitespace
        for (String line : lines) {
            String trimmedLine = line.trim();
            if (!trimmedLine.startsWith("//") && !trimmedLine.isEmpty()) {
                filteredLines.add(trimmedLine.replaceAll("\\s+", " "));
            }
        }

        // Check the number of lines
        if (filteredLines.size() != 5) {
            textArea.append("//invalid number of non comment lines (must be 5)\n");
            return false;
        }

        // Check the format of each line
        // Line 1: set of states
        String[] states = filteredLines.get(0).split(" ");
        Set<String> stateSet = new HashSet<>(Arrays.asList(states));
        if (stateSet.size() < states.length) {
            textArea.append("//duplicate states in the state list\n");
            return false;
        }
        if (states.length < 1) {
            textArea.append("//invalid number of states\n");
            return false;
        }
        for (String state : states) {
            if (!state.matches("^[a-zA-Z]\\w{0,9}$")) {
                textArea.append("//invalid state name '" + state + "'\n");
                return false;
            }
        }
        // Line 2: input alphabet
        String[] inputAlphabet = filteredLines.get(1).split(" ");
        if (inputAlphabet.length < 1) {
            textArea.append("//invalid number of input symbols\n");
            return false;
        }
        for (String symbol : inputAlphabet) {
            if (!symbol.matches("^\\w$")) {
                textArea.append("//invalid input symbol '" + symbol + "'\n");
                return false;
            }
        }

        // Line 3: initial state
        String initialState = filteredLines.get(2);
        if (!initialState.matches("^[a-zA-Z]\\w{0,9}$")) {
            textArea.append("//invalid initial state name '" + initialState + "'\n");
            return false;
        }
        if (!Arrays.asList(states).contains(initialState)) {
            textArea.append("//start state not in state list -- '" + initialState + "'\n");
            return false;
        }

        // Line 4: set of favorable states
        String[] favorableStates = filteredLines.get(3).split(" ");
        if (favorableStates.length < 1) {
            textArea.append("//invalid number of favorable states\n");
            return false;
        }
        for (String state : favorableStates) {
            if (!state.matches("^[a-zA-Z]\\w{0,9}$")) {
                textArea.append("//invalid favorable state name '" + state + "'\n");
                return false;
            }
            if (!Arrays.asList(states).contains(state)) {
                textArea.append("//favorable state not in state list -- '" + state + "'\n");
                return false;
            }
        }

        // Line 5: transition table
        String[] table = filteredLines.get(4).split(" ");
        if (table.length != states.length * inputAlphabet.length) {
            textArea.append("//invalid number of transitions\n");
            return false;
        }
        for (int i = 0; i < table.length; i++) {
            String state = states[i / inputAlphabet.length];
            String symbol = inputAlphabet[i % inputAlphabet.length];
            String nextState = table[i];
            if (!nextState.matches("^[a-zA-Z]\\w{0,9}$")) {
                textArea.append("//invalid next state name '" + nextState + "'\n");
                return false;
            }
            if (!Arrays.asList(states).contains(nextState)) {
                textArea.append("//next state not in state list -- '" + nextState + "'\n");
                return false;
            }
        }

        // All checks passed
        textArea.append("//valid dfa\n");
        isStringAccepted(filteredLines, "1");
        return true;
    }

    public void isStringAccepted(List<String> dfaDefinition, String inputString) {
        String[] states = dfaDefinition.get(0).split(" ");
        String[] inputSymbols = dfaDefinition.get(1).split(" ");
        String startState = dfaDefinition.get(2);
        String[] finalStates = dfaDefinition.get(3).split(" ");
        String[][] deltaTable = new String[states.length][inputSymbols.length];
        String[] deltaTableRow = dfaDefinition.get(4).split(" ");
        int rowIndex = 0;
        for (int i = 0; i < deltaTableRow.length; i += 2) {
            deltaTable[rowIndex][0] = deltaTableRow[i];
            deltaTable[rowIndex][1] = deltaTableRow[i + 1];
            rowIndex++;
        }

        String output = "set of states: " + Arrays.toString(states).replaceAll("[\\[\\],]", "") + "\n"
                + "set of input symbols: " + Arrays.toString(inputSymbols).replaceAll("[\\[\\],]", "") + "\n"
                + "start state: " + startState + "\n"
                + "set of final states: " + Arrays.toString(finalStates).replaceAll("[\\[\\],]", "") + "\n"
                + "delta:\n"
                + "      " + inputSymbols[0] + "   " + inputSymbols[1] + "\n"
                + "    ---------\n";
        for (int i = 0; i < deltaTable.length; i++) {
            output += states[i] + " |   " + deltaTable[i][0] + "   " + deltaTable[i][1] + "\n";
        }
        output += "\ncurrentInputString '" + inputString + "'\n";
        String currentState = startState;
        for (int i = 0; i < inputString.length(); i++) {
            char currentInputSymbol = inputString.charAt(i);
            int columnIndex = Arrays.asList(inputSymbols).indexOf(String.valueOf(currentInputSymbol));
            rowIndex = Arrays.asList(states).indexOf(currentState);
            output += "    delta( " + currentState + ", " + currentInputSymbol + " ) = " + deltaTable[rowIndex][columnIndex] + "\n";
            currentState = deltaTable[rowIndex][columnIndex];
        }
        output += inputString + " is ";
        if (Arrays.asList(finalStates).contains(currentState)) {
            output += "accepted";
        } else {
            output += "not accepted";
        }
        textArea.append(output);
    }

}
