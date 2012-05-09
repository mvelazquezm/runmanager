package es.mvm.runman.gui;

import es.mvm.runman.core.ApplicationManager;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class AlertWin extends JFrame {
    private JTextArea ta_note = new JTextArea();
    private JButton bt_acept = new JButton();
    
    private ApplicationManager manager;

    public AlertWin(String text, ApplicationManager manager) {
        try {
            this.manager = manager;
            jbInit();
            
            ta_note.setText(text);
            
            this.setLocationRelativeTo(manager.getMainWindow());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(294, 155));
        this.setTitle( "Atención" );
        ta_note.setBounds(new Rectangle(20, 10, 255, 65));
        ta_note.setEditable(false);
        ta_note.setWrapStyleWord(true);
        ta_note.setLineWrap(true);
        bt_acept.setText("Aceptar");
        bt_acept.setBounds(new Rectangle(90, 85, 105, 25));
        bt_acept.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { bt_action (ae); }});
        this.getContentPane().add(bt_acept, null);
        this.getContentPane().add(ta_note, null);
    }
    
    void bt_action (ActionEvent a) {
        this.setVisible(false);
    }
}
