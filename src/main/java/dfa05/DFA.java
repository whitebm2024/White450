
package dfa05;


/*
 Jim Klayder -- spring 2023

simple demo of how to make a Runnable class
that runs and pauses 
showing a counter value in the Java console

Brandon White, spring 2023
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
        
        
import javax.swing.JTextArea;

/*need a public void run() method to implement runnable*/
public class DFA implements Runnable  {
    
    private int count;
    private int ms_to_sleep;
    private boolean pause;
    private Thread thread;
    private JTextArea textArea;
    
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
        
         while (true)
        {
            if ( ! pause )
            {
                singleStep();
                textArea.append("Count: " + this.count + '\n');
            }
            
            try
            {
                thread.sleep(ms_to_sleep);
            }catch (Exception ex) {}
        }
    }
    
    public void singleStep() {
        count += 1;
        System.out.println("count:  "+count);
    }
    
    public void setPause(boolean pause) {
        this.pause = pause;
    }
    
    public int getCount(){
        return count;
    }
    public void setCount(int i){
        count = i;
    }
    public boolean getPause(){
        return pause;
    }
    public void setSleep(int i){
        ms_to_sleep = i;
    }
    public int getSleep(){
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
    return true;
}
}



    

