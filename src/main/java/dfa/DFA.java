package dfa;


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
    private final JTextArea outputArea;
    private final JTextArea inputArea;
    private StringBuilder output;

    //pass a JTextArea in so that run() can manipulate the outputTextArea
    DFA(JTextArea outputTextArea, JTextArea inputTextArea) {

        count = 0;
        ms_to_sleep = 500;
        pause = true;
        thread = new Thread(this);
        thread.start();
        this.outputArea = outputTextArea;
        this.inputArea = inputTextArea;
        this.output = new StringBuilder();
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
                outputArea.append("Count: " + this.count + '\n');
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
    public String getOutput(){
        return output.toString();
    }
    //todo remove inputtextarea field and pass it in as an argument
    //  don't remove inputtextarea, so that I can pass filteredlines to simulateDfa
    //  unless i store the lines as part of the dfa class
    //todo separate validation and simulation
    public Boolean validateDfa(String input) {
        String[] lines = input.split("\\r?\\n");
        List<String> filteredLines = new ArrayList<>();
        // Reset the output
        output.setLength(0);

        // Filter out comment lines and remove superfluous whitespace
        for (String line : lines) {
            String trimmedLine = line.trim();
            if (!trimmedLine.startsWith("//") && !trimmedLine.isEmpty()) {
                filteredLines.add(trimmedLine.replaceAll("\\s+", " "));
            }
        }

        // Check the number of lines
        if (filteredLines.size() != 5) {
            output.append("invalid number of non comment lines (must be 5)\n");
            return false;
        }

        // Check the format of each line
        // Line 1: set of states
        String[] states = filteredLines.get(0).split(" ");
        Set<String> stateSet = new HashSet<>(Arrays.asList(states));
        if (stateSet.size() < states.length) {
            output.append("duplicate states in the state list\n");
            return false;
        }
        if (states.length < 1) {
            output.append("invalid number of states\n");
            return false;
        }
        for (String state : states) {
            if (!state.matches("^[a-zA-Z]\\w{0,9}$")) {
                output.append("invalid state name '").append(state).append("'\n");
            }
        }
        // Line 2: input alphabet
        String[] inputAlphabet = filteredLines.get(1).split(" ");
        if (inputAlphabet.length < 1) {
            output.append("invalid number of input symbols\n");
            return false;
        }
        for (String symbol : inputAlphabet) {
            if (!symbol.matches("^\\w$")) {
                output.append("invalid input symbol '").append(symbol).append("'\n");
                return false;
            }
        }

        // Line 3: initial state
        String initialState = filteredLines.get(2);
        if (!initialState.matches("^[a-zA-Z]\\w{0,9}$")) {
            output.append("invalid initial state name '").append(initialState).append("'\n");
            return false;
        }
        if (!Arrays.asList(states).contains(initialState)) {
            output.append("start state not in state list -- '").append(initialState).append("'\n");
        }

        // Line 4: set of favorable states
        String[] favorableStates = filteredLines.get(3).split(" ");
        if (favorableStates.length < 1) {
            output.append("invalid number of favorable states\n");
            return false;
        }
        for (String state : favorableStates) {
            if (!state.matches("^[a-zA-Z]\\w{0,9}$")) {
                output.append("invalid favorable state name '").append(state).append("'\n");
                return false;
            }
            if (!Arrays.asList(states).contains(state)) {
                output.append("favorable state not in state list -- '").append(state).append("'\n");
                return false;
            }
        }

        // Line 5: transition table
        String[] table = filteredLines.get(4).split(" ");
        if (table.length != states.length * inputAlphabet.length) {
            output.append("invalid number of transitions\n");
            return false;
        }
        for (int i = 0; i < table.length; i++) {
            String state = states[i / inputAlphabet.length];
            String symbol = inputAlphabet[i % inputAlphabet.length];
            String nextState = table[i];
            if (!nextState.matches("^[a-zA-Z]\\w{0,9}$")) {
                output.append("invalid next state name '").append(nextState).append("'\n");
                return false;
            }
            if (!Arrays.asList(states).contains(nextState)) {
                output.append("next state not in state list -- '").append(nextState).append("'\n");
                return false;
            }
        }

        // All checks passed
        output.append("valid dfa\n");
        simulateDFA(filteredLines, inputArea.getText());
        return true;
    }

    public Boolean simulateDFA(List<String> dfaDefinition, String inputString) {
        String[] states = dfaDefinition.get(0).split(" ");
        String[] inputSymbols = dfaDefinition.get(1).split(" ");
        String startState = dfaDefinition.get(2);
        String[] finalStates = dfaDefinition.get(3).split(" ");
        String[][] delta = new String[states.length][inputSymbols.length];
        String[] transitions = dfaDefinition.get(4).split(" ");

        // Populate delta matrix
        int transIndex = 0;
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < inputSymbols.length; j++) {
                delta[i][j] = transitions[transIndex];
                transIndex++;
            }
        }

        // Check if input string is valid
        boolean valid = true;
        for (int i = 0; i < inputString.length(); i++) {
            boolean validSymbol = false;
            for (int j = 0; j < inputSymbols.length; j++) {
                if (inputString.charAt(i) == inputSymbols[j].charAt(0)) {
                    validSymbol = true;
                    break;
                }
            }
            if (!validSymbol) {
                valid = false;
                break;
            }
        }
        if (!valid) {
            output.append("Input string is invalid.\n");
            return false;
        }

        // Check if input string is accepted by DFA
        String currentState = startState;
        output.append("set of states: ");
        for (String s : states) {
            output.append(s).append(" ");
        }
        output.append("\nset of input symbols: ");
        for (String s : inputSymbols) {
            output.append(s).append(" ");
        }
        output.append("\nstart state: ").append(startState).append("\nset of final states: ");
        for (String s : finalStates) {
            output.append(s).append(" ");
        }
        output.append("\ndelta:\n");

        // Print delta
        for (String inputSymbol : inputSymbols) {
            output.append("    ").append(inputSymbol).append(" ");
        }
        output.append("\n  -----------\n");
        for (int i = 0; i < states.length; i++) {
            output.append(states[i]).append(" | ");
            for (int j = 0; j < inputSymbols.length; j++) {
                output.append(delta[i][j]).append("   ");
            }
            output.append("\n");
        }

        output.append("currentInputString '").append(inputString).append("'\n");
        for (int i = 0; i < inputString.length(); i++) {
            String symbol = inputString.substring(i, i + 1);
            int stateIndex = -1;
            for (int j = 0; j < states.length; j++) {
                if (states[j].equals(currentState)) {
                    stateIndex = j;
                    break;
                }
            }
            int symbolIndex = -1;
            for (int j = 0; j < inputSymbols.length; j++) {
                if (inputSymbols[j].equals(symbol)) {
                    symbolIndex = j;
                    break;
                }
            }
            if (stateIndex == -1 || symbolIndex == -1) {
                output.append("Input string is not accepted by DFA\n");
            }
            output.append("    delta( ").append(currentState).append(", ").append(symbol)
                    .append(" ) = ").append(delta[stateIndex][symbolIndex]).append("\n");
            currentState = delta[stateIndex][symbolIndex];
        }

        boolean accepted = false;
        for (String s : finalStates) {
            if (currentState.equals(s)) {
                accepted = true;
            }
        }

        if (accepted) {
            output.append(inputString).append(" is accepted\n\n");
        } else {
            output.append(inputString).append(" is not accepted\n\n");
        }

        System.out.println(output.toString());
        return true;
    }

}
