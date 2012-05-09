package es.mvm.runman.gui;

import es.mvm.runman.beans.Category;
import es.mvm.runman.beans.Competitor;
import es.mvm.runman.beans.Race;
import es.mvm.runman.core.ApplicationManager;

import es.mvm.runman.util.Constants;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusAdapter;

import java.awt.event.FocusEvent;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CompetitorForm extends JFrame {
    
    private ApplicationManager manager;
    
    private Competitor comp;
    
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JRadioButton rb_manual = new JRadioButton();
    private JLabel jLabel5 = new JLabel();
    private JRadioButton rb_general = new JRadioButton();
    private JRadioButton rb_category = new JRadioButton();
    private JSeparator jSeparator1 = new JSeparator();
    private JTextField tf_dorsal = new JTextField();
    private JButton bt_acept = new JButton();
    private JComboBox cb_sex = new JComboBox();
    private JTextField tf_bornDate = new JTextField();
    private JTextField tf_name = new JTextField();
    private JComboBox cb_category = new JComboBox();
    private JLabel jLabel6 = new JLabel();
    private JTextField tf_surname = new JTextField();
    private JLabel jLabel7 = new JLabel();
    private JCheckBox ch_local = new JCheckBox();


    public CompetitorForm(ApplicationManager appMan, Competitor defComp) {
        
        this(appMan);
        
        cb_sex.setSelectedItem(defComp.getSex());
        cb_category.setSelectedItem(defComp.getCategory());
        
        tf_dorsal.setText("" + defComp.getDorsal());
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String born = formatoFecha.format(defComp.getBornDate());
        
        tf_bornDate.setText(born);
        
        tf_name.setText(defComp.getName());
        tf_surname.setText(defComp.getSurname());
        
        ch_local.setSelected(defComp.isLocal());
        
        this.comp = defComp;
        
    }
    

    public CompetitorForm(ApplicationManager appMan) {
        
        manager = appMan;
        
        comp = null;
        
        //Cargamos los SEXOS
        cb_sex.addItem(Constants.TSex.Femenino);
        cb_sex.addItem(Constants.TSex.Masculino);
        
        cb_sex.setSelectedIndex(0);
    
        //Cargamos las categorías disponibles en el 
        //combo
        List<Category> list = manager.getAllCategories();
        
        for (Category aux : list) {
            cb_category.addItem(aux);
        }
        
        //Establecemos el dorsal, a priori general.
        int dorsal = manager.getNextGenericDorsal();
        tf_dorsal.setText("" + dorsal);

        try {
            jbInit();
            
            this.setLocationRelativeTo(manager.getMainWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(508, 395));
        this.setTitle( "Corredor" );
        jLabel1.setText("Sexo:");
        jLabel1.setBounds(new Rectangle(135, 35, 95, 20));
        jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Fecha de nacimientos (dd/mm/aaaa):");
        jLabel2.setBounds(new Rectangle(10, 58, 220, 20));
        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel3.setText("Nombre:");
        jLabel3.setBounds(new Rectangle(100, 110, 130, 20));
        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel4.setText("Categoría:");
        jLabel4.setBounds(new Rectangle(115, 130, 115, 20));
        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setHorizontalTextPosition(SwingConstants.RIGHT);
        rb_manual.setText("Manual");
        rb_manual.setBounds(new Rectangle(70, 220, 86, 18));
        rb_manual.setActionCommand("Manual");
        rb_manual.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { rb_manual_Action(ae);}});
        jLabel5.setText("Dorsal:");
        jLabel5.setBounds(new Rectangle(155, 255, 75, 20));
        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel5.setHorizontalTextPosition(SwingConstants.RIGHT);
        rb_general.setText("General");
        rb_general.setBounds(new Rectangle(160, 220, 86, 18));
        rb_general.setSelected(true);
        rb_general.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { rb_general_Action(ae);}});
        rb_category.setText("Categoría");
        rb_category.setBounds(new Rectangle(250, 220, 86, 18));
        rb_category.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { rb_category_Action(ae);}});
        jSeparator1.setBounds(new Rectangle(55, 195, 355, 2));
        tf_dorsal.setBounds(new Rectangle(235, 255, 70, 20));
        tf_dorsal.setEditable(false);
        bt_acept.setText("Aceptar");
        bt_acept.setBounds(new Rectangle(185, 295, 110, 35));
        bt_acept.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { bt_Acept_Action(ae);}});
        cb_sex.setBounds(new Rectangle(240, 35, 205, 20));
        tf_bornDate.setBounds(new Rectangle(240, 60, 205, 20));
        tf_bornDate.addActionListener(new ActionListener() { public void actionPerformed (ActionEvent ae) { tf_date_Action(ae);}});
        tf_bornDate.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) {
                    tf_date_Action(e);
                }
            });
        tf_name.setBounds(new Rectangle(240, 110, 205, 20));
        cb_category.setBounds(new Rectangle(240, 135, 205, 20));
        jLabel6.setText("Apellidos:");
        jLabel6.setBounds(new Rectangle(120, 85, 110, 20));
        jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel6.setHorizontalTextPosition(SwingConstants.RIGHT);
        tf_surname.setBounds(new Rectangle(240, 85, 205, 20));
        jLabel7.setText("Local:");
        jLabel7.setBounds(new Rectangle(160, 165, 75, 20));
        jLabel7.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
        ch_local.setText("Es corredor local");
        ch_local.setBounds(new Rectangle(240, 165, 205, 20));
        this.getContentPane().add(ch_local, null);
        this.getContentPane().add(jLabel7, null);
        this.getContentPane().add(tf_surname, null);
        this.getContentPane().add(jLabel6, null);
        this.getContentPane().add(cb_category, null);
        this.getContentPane().add(tf_name, null);
        this.getContentPane().add(tf_bornDate, null);
        this.getContentPane().add(cb_sex, null);
        this.getContentPane().add(bt_acept, null);
        this.getContentPane().add(tf_dorsal, null);
        this.getContentPane().add(jSeparator1, null);
        this.getContentPane().add(rb_category, null);
        this.getContentPane().add(rb_general, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(rb_manual, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
    }
    
    private void tf_date_Action(ActionEvent e) {
           
        didWorksWithDate();
    }
    
    private void rb_manual_Action(ActionEvent a) {
        rb_manual.setSelected(true);
        rb_general.setSelected(false);
        rb_category.setSelected(false);
        
        tf_dorsal.setEditable(true);
    }
    
    private void rb_general_Action(ActionEvent a) {
        rb_manual.setSelected(false);
        rb_general.setSelected(true);
        rb_category.setSelected(false);
        
        tf_dorsal.setEditable(false);
        
        //Establecemos el dorsal, a priori general.
        int dorsal = manager.getNextGenericDorsal();
        tf_dorsal.setText("" + dorsal);
    }
    
    private void rb_category_Action(ActionEvent a) {
        rb_manual.setSelected(false);
        rb_general.setSelected(false);
        rb_category.setSelected(true);
        
        tf_dorsal.setEditable(false);
        
        //Establecemos el dorsal, a priori general.
        int dorsal = manager.getNextCategoryDorsal((Category)cb_category.getSelectedItem());
        tf_dorsal.setText("" + dorsal);
    }

    private void tf_date_Action(FocusEvent e) {
        didWorksWithDate();
    }
    
    private void bt_Acept_Action(ActionEvent a) {
        if (tf_bornDate.getText().equals("") || 
            tf_name.getText().equals("") ||
            tf_surname.getText().equals("") ||
            tf_dorsal.getText().equals("")) {
            
            AlertWin alert = new AlertWin("Todos los campos son obligatorios", manager);
            alert.setVisible(true);
            
        } else {
            
            //Procedemos al alta del participante.
            try {
                
                int dorsalInt = Integer.parseInt(tf_dorsal.getText());
                
                Calendar cal = Calendar.getInstance();
                
                String[] fechaSplit = tf_bornDate.getText().split("/");
                cal.set(Integer.parseInt(fechaSplit[2]),
                                Integer.parseInt(fechaSplit[1]) - 1,
                                Integer.parseInt(fechaSplit[0]));
                
                Date date = new Date(cal.getTimeInMillis());
                
                if (comp == null) {
                
                    manager.createCompetitor(dorsalInt,
                                           tf_surname.getText(),
                                           tf_name.getText(),
                                           date,
                                           (Constants.TSex)cb_sex.getSelectedItem(),
                                           (Category)cb_category.getSelectedItem(),
                                           ch_local.isSelected());
                } else {
                    
                    comp.setDorsal(dorsalInt);
                    comp.setSurname(tf_surname.getText());
                    comp.setName(tf_name.getText());
                    comp.setBornDate(date);
                    comp.setSex((Constants.TSex)cb_sex.getSelectedItem());
                    comp.setCategory((Category)cb_category.getSelectedItem());
                    comp.setLocal(ch_local.isSelected());
                    
                    manager.updateCompetitor();
                }
                    
                
                this.setVisible(false);
                
                
                
            } catch (Exception e) {
                AlertWin alert = new AlertWin("El dorsal debe ser un número y/o la fecha debe de tener formato dd/mm/aaaa", manager);
                alert.setVisible(true);
            }
            
        }
    }
    
    private void didWorksWithDate() {
        String error = "";
        String str = tf_bornDate.getText();
        
        String[] splt = str.split("/");
        
        if (splt.length == 3) {
            
            if (splt[0].length() != 2 || splt[1].length() != 2 || splt[2].length() != 4) {
                error = "Formato incorrecto, debe ser dd/mm/aaaa";
            } else {
                try {
                    int dia = Integer.parseInt(splt[0]);
                    int mes = Integer.parseInt(splt[1]);
                    int anio = Integer.parseInt(splt[2]);
                    
                    if (dia < 0 || dia > 31)
                        error = "El día va entre 01 y 31";
                    
                    if (mes < 0 || mes > 12)
                        error = "El mes va entre 01 y 12";
                    
                } catch (Exception ex) {
                    error = "No ha incluido números en las fechas";
                }
            }
            
        } else {
            error = "El formato es dd/mm/aaaa"; 
        }
        
        if (error.length() == 0) {
            //Seteamos una categoría...según la edad
        
            int anios = manager.edad(str);
            
            List<Category> categories = manager.getAllCategories();
            
            for (Category cat : categories) {
                if (cat.getSex().equals(Constants.TSex.Indiferente) ||
                    cat.getSex().equals(cb_sex.getSelectedItem())) {
                    
                    if (cat.getIniAge() <= anios && cat.getEndAge() >= anios)
                        cb_category.setSelectedItem(cat);
                }
            }
        
        
        } else {
            AlertWin alert = new AlertWin(error, manager);
            alert.setVisible(true);
        }
    }
}
