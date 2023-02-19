/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfa05;

import javax.swing.text.DefaultCaret;

/**
 *
 * @author bmwco
 */
public class MainFrame extends javax.swing.JFrame {
    
    private DFA dfa;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        dfa = new DFA(textArea);
        
        //Create a DefaultCaret object to autoscroll to bottom
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        stepButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        speedSlider = new javax.swing.JSlider();
        speedLabel = new javax.swing.JLabel();
        fileComboBox = new javax.swing.JComboBox<>();
        readDfaButton = new javax.swing.JButton();
        fileTextField = new javax.swing.JTextField();
        validateDfaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Brandon White - dfa05");

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        stepButton.setText("Step");
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        speedSlider.setMajorTickSpacing(10);
        speedSlider.setMaximum(1000);
        speedSlider.setMinimum(100);
        speedSlider.setMinorTickSpacing(10);
        speedSlider.setSnapToTicks(true);
        speedSlider.setValue(500);
        speedSlider.setName(""); // NOI18N
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });

        speedLabel.setText("Speed: 500ms");

        fileComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "data/dfa/dfa01.txt", "data/dfa/dfa02.txt", "data/dfa/dfa03.txt", "http://klayder.org/UMU/CSC450/dfa/dfa05.txt" }));
        fileComboBox.setSelectedIndex(-1);
        fileComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileComboBoxActionPerformed(evt);
            }
        });

        readDfaButton.setText("Read DFA");
        readDfaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readDfaButtonActionPerformed(evt);
            }
        });

        validateDfaButton.setText("Validate DFA");
        validateDfaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateDfaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fileTextField)
                    .addComponent(fileComboBox, 0, 462, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stepButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(speedSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(readDfaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(validateDfaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(speedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readDfaButton))
                .addGap(18, 18, 18)
                .addComponent(fileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stepButton)
                        .addGap(18, 18, 18)
                        .addComponent(runButton)
                        .addGap(18, 18, 18)
                        .addComponent(resetButton)
                        .addGap(18, 18, 18)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(speedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(validateDfaButton)
                        .addGap(0, 162, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        dfa.singleStep();
        textArea.append("Count: " + dfa.getCount()+ '\n');
        
    }//GEN-LAST:event_stepButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // Call pause method if not paused
        if (!dfa.getPause()) {
            runButtonActionPerformed(evt);
        }
        dfa.setCount(0);
        textArea.append("DFA has been reset\n");
        
    }//GEN-LAST:event_resetButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        
        if(dfa.getPause()){
            dfa.setPause(false);
            runButton.setText("Pause");
        }
        else{
            dfa.setPause(true);
            runButton.setText("Run");
        }
        
    }//GEN-LAST:event_runButtonActionPerformed

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        // TODO add your handling code here:
        dfa.setSleep(speedSlider.getValue());
        speedLabel.setText("Speed: " + dfa.getSleep() + "ms");
    }//GEN-LAST:event_speedSliderStateChanged

    private void fileComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileComboBoxActionPerformed
        //pause dfa if running, then set combobox selection into text field
        if (!dfa.getPause()) {
            runButtonActionPerformed(evt);
        }
        fileTextField.setText(fileComboBox.getSelectedItem().toString());
        readDfaButtonActionPerformed(evt);
    }//GEN-LAST:event_fileComboBoxActionPerformed

    private void readDfaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readDfaButtonActionPerformed
        if (!dfa.getPause()) {
            runButtonActionPerformed(evt);
        }
        String path = fileTextField.getText();
        try{
            In in = new In(path);
            String s = in.readAll();
            textArea.setText(s + '\n');
        }
        catch(IllegalArgumentException e){
            textArea.setText(e.getLocalizedMessage()+'\n');
        }
    }//GEN-LAST:event_readDfaButtonActionPerformed

    private void validateDfaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateDfaButtonActionPerformed
        // Call pause method if not paused
        if (!dfa.getPause()) {
            runButtonActionPerformed(evt);
        }
        dfa.validateDfa(textArea.getText());
    }//GEN-LAST:event_validateDfaButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> fileComboBox;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton readDfaButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton runButton;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JButton stepButton;
    private javax.swing.JTextArea textArea;
    private javax.swing.JButton validateDfaButton;
    // End of variables declaration//GEN-END:variables
}
