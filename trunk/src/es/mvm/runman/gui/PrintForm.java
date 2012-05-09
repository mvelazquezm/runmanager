package es.mvm.runman.gui;

import es.mvm.runman.core.ApplicationManager;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PrintForm extends JFrame {
    
    private ApplicationManager manager;
    
    private JLabel jLabel1 = new JLabel();
    private JComboBox cb_orden = new JComboBox();
    private JLabel jLabel2 = new JLabel();
    private JComboBox cb_category = new JComboBox();
    private JLabel jLabel3 = new JLabel();
    private JComboBox cb_meta = new JComboBox();
    private JButton bt_acept = new JButton();

    public PrintForm(ApplicationManager manager) {
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
        this.setSize(new Dimension(400, 235));
        this.setTitle( "Imprimir" );
        jLabel1.setText("Orden:");
        jLabel1.setBounds(new Rectangle(35, 40, 105, 20));
        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
        cb_orden.setBounds(new Rectangle(145, 40, 150, 20));
        jLabel2.setText("Categorías:");
        jLabel2.setBounds(new Rectangle(65, 75, 75, 20));
        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
        cb_category.setBounds(new Rectangle(145, 75, 150, 20));
        jLabel3.setText("Meta:");
        jLabel3.setBounds(new Rectangle(40, 110, 100, 20));
        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
        cb_meta.setBounds(new Rectangle(145, 110, 150, 20));
        bt_acept.setText("Aceptar");
        bt_acept.setBounds(new Rectangle(135, 150, 130, 35));
        this.getContentPane().add(bt_acept, null);
        this.getContentPane().add(cb_meta, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(cb_category, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(cb_orden, null);
        this.getContentPane().add(jLabel1, null);
        
        cb_orden.addItem("Nombre");
        cb_orden.addItem("Dorsal");
        
        cb_category.addItem("Categorías");
        cb_category.addItem("Global");
        
        cb_meta.addItem("No");
        cb_meta.addItem("Si");
        
        bt_acept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
                bt_acept_Action(ae);
                
            }
        });
    }
    
    public void bt_acept_Action(ActionEvent ae) {
        
        boolean nombre = cb_orden.getSelectedItem().equals("Nombre");
        boolean global = cb_category.getSelectedItem().equals("Global");
        boolean meta = cb_meta.getSelectedItem().equals("Si");
        
        manager.printOrderedRace(nombre, global, meta);
        
        this.setVisible(false);
         
    }
}
