package es.mvm.runman.gui;

import es.mvm.runman.beans.Category;

import es.mvm.runman.core.ApplicationManager;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EndRaceForm extends JFrame {
    
    private ApplicationManager manager;
    
    private JLabel jLabel1 = new JLabel();
    private JTextField tf_time = new JTextField();
    private JSeparator jSeparator1 = new JSeparator();
    private JLabel jLabel2 = new JLabel();
    private JTextField tf_dorsal = new JTextField();
    private JLabel jLabel3 = new JLabel();
    private JComboBox cb_category = new JComboBox();
    private JButton bt_acept = new JButton();

    public EndRaceForm(ApplicationManager manager) {
        try {
            this.manager = manager;
            jbInit();
            this.setLocationRelativeTo(manager.getMainWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(400, 278));
        this.setTitle( "Llegada a meta" );
        jLabel1.setText("Tiempo del primero:");
        jLabel1.setBounds(new Rectangle(35, 35, 135, 20));
        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
        tf_time.setBounds(new Rectangle(175, 35, 160, 20));
        jSeparator1.setBounds(new Rectangle(60, 80, 280, 2));
        jLabel2.setText("Dorsal:");
        jLabel2.setBounds(new Rectangle(75, 110, 90, 20));
        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
        tf_dorsal.setBounds(new Rectangle(170, 110, 165, 20));
        jLabel3.setText("Categoría:");
        jLabel3.setBounds(new Rectangle(20, 140, 145, 20));
        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
        cb_category.setBounds(new Rectangle(170, 140, 165, 20));
        bt_acept.setText("Aceptar");
        bt_acept.setBounds(new Rectangle(150, 190, 115, 35));
        this.getContentPane().add(bt_acept, null);
        this.getContentPane().add(cb_category, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(tf_dorsal, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jSeparator1, null);
        this.getContentPane().add(tf_time, null);
        this.getContentPane().add(jLabel1, null);
        
        bt_acept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                bt_actept_Action(ae);
            }
        });
        
        //Cargamos las categorías disponibles en el 
        //combo
        List<Category> list = manager.getAllCategories();
        
        cb_category.addItem("Global");
        
        for (Category aux : list) {
            cb_category.addItem(aux);
        }
        
        tf_time.setText(manager.getEndTime());
    }
    
    public void bt_actept_Action(ActionEvent ae) {
        
        //Buscamos el dorsal y lo agregamos al bean EndRace.
        try {
            
            int dorsal = Integer.parseInt(tf_dorsal.getText());
            
            Category cat = null;
            
            if (cb_category.getSelectedItem() instanceof Category) {
                cat = (Category)cb_category.getSelectedItem();
            }
            
            
            manager.addCompetitorEnd(tf_time.getText(),
                                     dorsal,
                                     cat);
            
            setVisible(false);
            
        } catch (Exception e) {
            AlertWin alert = new AlertWin("El dorsal debe de ser un número", manager);
            alert.setVisible(true);
            return;
        }

    }
}
