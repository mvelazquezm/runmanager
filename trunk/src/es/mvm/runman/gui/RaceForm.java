package es.mvm.runman.gui;

import es.mvm.runman.core.ApplicationManager;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class with the Race form.
 * Used for new races and updates.
 */
public class RaceForm extends JFrame {
    
    private ApplicationManager manager;
    
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JTextField tf_name = new JTextField();
    private JTextField tf_date = new JTextField();
    private JTextField tf_location = new JTextField();
    private JTextField tf_distance = new JTextField();
    private JTextField tf_limit = new JTextField();
    private JCheckBox ch_upperValued = new JCheckBox();
    private JButton bt_acept = new JButton();
    private JCheckBox ch_noLimit = new JCheckBox();

    public RaceForm(ApplicationManager appMan) {
        manager = appMan;
        
        try {
            jbInit();
            this.setLocationRelativeTo(manager.getMainWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(512, 272));
        this.setTitle( "Race" );
        jLabel1.setText("Nombre:");
        jLabel1.setBounds(new Rectangle(45, 15, 115, 25));
        jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Fecha:");
        jLabel2.setBounds(new Rectangle(45, 45, 115, 25));
        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel3.setText("Lugar:");
        jLabel3.setBounds(new Rectangle(45, 75, 115, 25));
        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel4.setText("Distancia (metros):");
        jLabel4.setBounds(new Rectangle(45, 105, 115, 25));
        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel5.setText("Límite corredores:");
        jLabel5.setBounds(new Rectangle(45, 130, 115, 25));
        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel5.setHorizontalTextPosition(SwingConstants.RIGHT);
        tf_name.setBounds(new Rectangle(180, 15, 305, 20));
        tf_date.setBounds(new Rectangle(180, 45, 305, 20));
        tf_location.setBounds(new Rectangle(180, 75, 305, 20));
        tf_distance.setBounds(new Rectangle(180, 105, 305, 20));
        tf_limit.setBounds(new Rectangle(180, 130, 130, 20));
        ch_upperValued.setText("0");
        ch_noLimit.setText("Ilimitado");
        ch_noLimit.setBounds(new Rectangle(320, 130, 165, 20));
        ch_upperValued.setSelected(true);
        bt_acept.setText("Aceptar");
        bt_acept.setBounds(new Rectangle(160, 200, 175, 40));
        ch_upperValued.setText("Sobrepasable");
        ch_upperValued.setBounds(new Rectangle(320, 155, 165, 20));
        
        //Events
        ch_noLimit.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { check_limit_Action (ae);}});
        bt_acept.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { bt_acept_Action(ae);}});
        //End events
        
        this.getContentPane().add(ch_upperValued, null);
        this.getContentPane().add(bt_acept, null);
        this.getContentPane().add(ch_noLimit, null);
        this.getContentPane().add(tf_limit, null);
        this.getContentPane().add(tf_distance, null);
        this.getContentPane().add(tf_location, null);
        this.getContentPane().add(tf_date, null);
        this.getContentPane().add(tf_name, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
    }
    
    /**
     * It controls changes in noLimit check.
     * 
     * if it is check, then tf_limit is disabled
     * @param e
     */
    void check_limit_Action (ActionEvent e) {
        
        if (ch_noLimit.isSelected()) {
            tf_limit.setText("0");
            tf_limit.setEditable(false);
            tf_limit.setEnabled(false);
        } else {
            tf_limit.setText("0");
            tf_limit.setEditable(true);
            tf_limit.setEnabled(true);
        }
    } //check_limiti_Action
    
    /**
     * Manages the action of pushing acept button.
     * 
     * Makes text field check and make a new Race.
     * @param e
     */
    void bt_acept_Action (ActionEvent e) {
        if (tf_name.getText().length() == 0 ||
            tf_date.getText().length() == 0) {
            //TODO: mostrar alerta indicando que el nombre y la
            //fecha son obligatorios
            AlertWin alert = new AlertWin("Los campos nombre y fecha son obligatorios.", manager);
            alert.setVisible(true);
        } else {
            float distance = 0.0f;
            int numCompetitors = 0;
            
            try {
                distance = Float.parseFloat(tf_distance.getText());
            } catch (Exception ex) {
                AlertWin alert = new AlertWin("El campo distancia debe de ser un número", manager);
                alert.setVisible(true);
                return;
            }
            
            try {
                numCompetitors = Integer.parseInt(tf_limit.getText());
            } catch (Exception ex) {
                AlertWin alert = new AlertWin("El campo Límite de corredores debe de ser un número", manager);
                alert.setVisible(true);
                return;
            }
            
            manager.createRace(tf_name.getText(), 
                               tf_date.getText(), 
                               tf_location.getText(), 
                               distance, 
                               numCompetitors, 
                               ch_upperValued.isSelected() ? true : false);
            
            this.setVisible(false);
        } //end if-else
    }
}
