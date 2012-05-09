package es.mvm.runman.gui;

import es.mvm.runman.beans.Category;
import es.mvm.runman.core.ApplicationManager;

import es.mvm.runman.util.Constants;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CategoryForm extends JFrame {
    
    private ApplicationManager manager;
    
    private Category cat;
    
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JComboBox cb_sex = new JComboBox();
    private JTextField tf_iniAge = new JTextField();
    private JTextField tf_endAge = new JTextField();
    private JTextField tf_name = new JTextField();
    private JTextField tf_shortName = new JTextField();
    private JButton bt_acept = new JButton();
    
    
    public CategoryForm(ApplicationManager manager, Category defCat) {
        this(manager);
        
        this.cat = defCat;
        
        cb_sex.setSelectedItem(cat.getSex());
        
        tf_iniAge.setText("" + cat.getIniAge());
        tf_endAge.setText("" + cat.getEndAge());
        tf_name.setText(cat.getName());
        tf_shortName.setText(cat.getShortName());
    }


    public CategoryForm(ApplicationManager manager) {
        
        this.manager = manager;
        this.cat = null;
        
        //Rellenamos el combo de sexo, con los sexos disponibles
        cb_sex.addItem(Constants.TSex.Indiferente);
        cb_sex.addItem(Constants.TSex.Femenino);
        cb_sex.addItem(Constants.TSex.Masculino);
        
        cb_sex.setSelectedIndex(0);
        
        try {
            jbInit();
            
            this.setLocationRelativeTo(manager.getMainWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(419, 264));
        this.setTitle( "Categoría" );
        jLabel1.setText("Nombre:");
        jLabel1.setBounds(new Rectangle(60, 124, 65, 20));
        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel2.setText("Nombre corto:");
        jLabel2.setBounds(new Rectangle(35, 155, 90, 20));
        jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setText("Edad mínima:");
        jLabel3.setBounds(new Rectangle(60, 61, 65, 20));
        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel4.setText("Edad máxima:");
        jLabel4.setBounds(new Rectangle(30, 95, 95, 20));
        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel5.setText("Sexo:");
        jLabel5.setBounds(new Rectangle(60, 30, 65, 20));
        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel5.setHorizontalTextPosition(SwingConstants.RIGHT);
        cb_sex.setBounds(new Rectangle(135, 30, 145, 20));
        tf_iniAge.setBounds(new Rectangle(135, 60, 75, 20));
        tf_endAge.setBounds(new Rectangle(135, 95, 75, 20));
        tf_name.setBounds(new Rectangle(135, 125, 225, 20));
        tf_shortName.setBounds(new Rectangle(135, 155, 80, 20));
        bt_acept.setText("Aceptar");
        bt_acept.setBounds(new Rectangle(140, 195, 140, 30));
        this.getContentPane().add(bt_acept, null);
        this.getContentPane().add(tf_shortName, null);
        this.getContentPane().add(tf_name, null);
        this.getContentPane().add(tf_endAge, null);
        this.getContentPane().add(tf_iniAge, null);
        this.getContentPane().add(cb_sex, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        
        bt_acept.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { bt_acept_Action(ae);}});
    }
    
    /**
     * Hace el alta de la nueva categoría
     * @param e
     */
    void bt_acept_Action (ActionEvent e) {
        int minAge = 0;
        int maxAge = 0;
        
        try {
            minAge = Integer.parseInt(tf_iniAge.getText());
            maxAge = Integer.parseInt(tf_endAge.getText());
        } catch (Exception ex) {
            AlertWin alert = new AlertWin("Los campos edad mínima y máxima deben de ser un número.", manager);
            alert.setVisible(true);
            return;
        }
        
        if (tf_name.getText().length() == 0) {
            AlertWin alert = new AlertWin("El nombre (largo) es obligatorio", manager);
            alert.setVisible(true);
            return;
        }
        
        if (cat == null) {
        
        manager.createCategory(tf_name.getText(),
                               tf_shortName.getText(),
                               minAge, maxAge,
                               (Constants.TSex)cb_sex.getSelectedItem());
        } else {
            
            cat.setEndAge(maxAge);
            cat.setIniAge(minAge);
            cat.setName(tf_name.getText());
            cat.setShortName(tf_shortName.getText());
            cat.setSex((Constants.TSex)cb_sex.getSelectedItem());
            
            manager.updateCompetitor();
        }
        
        this.setVisible(false);
        
        
    } //bt_acept_Action
}
