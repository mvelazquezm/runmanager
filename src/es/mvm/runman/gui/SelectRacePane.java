/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.mvm.runman.gui;


import es.mvm.runman.beans.Race;
import es.mvm.runman.core.ApplicationManager;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author mvelazquezm
 */
public class SelectRacePane extends JFrame {
    private JComboBox cb_races = new JComboBox();
    private JButton bt_acept = new JButton();

    private ApplicationManager manager;

    public SelectRacePane(ApplicationManager manager) {
        try {
            this.manager = manager;
            jbInit();
            
            //Cargamos las carreras disponibles en el 
            //combo
            List<Race> list = manager.getAllRaces();
            
            this.setLocationRelativeTo(manager.getMainWindow());
            
            for (Race aux : list) {
                cb_races.addItem(aux);
            }
            


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(294, 155));
        this.setTitle( "Selección de Carrera" );
        cb_races.setBounds(new Rectangle(20, 35, 255, 25));
        cb_races.setEditable(false);
        bt_acept.setText("Aceptar");
        bt_acept.setBounds(new Rectangle(90, 85, 105, 25));
        bt_acept.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { bt_action (ae); }});
        this.getContentPane().add(bt_acept, null);
        this.getContentPane().add(cb_races, null);
    }

    void bt_action (ActionEvent a) {
        
        if (cb_races.getSelectedItem() != null) {
            
            manager.setCurrentRace((Race)cb_races.getSelectedItem());
            
            this.setVisible(false);
            
        }
    }
} //SelectRacePane
