package es.mvm.runman.gui;

import es.mvm.runman.beans.Race;
import es.mvm.runman.core.ApplicationManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame implements WindowListener {

    private ApplicationManager manager;
    
    private BorderLayout layoutMain = new BorderLayout();
    private JPanel panelCenter = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private JLabel statusBar = new JLabel();
    private JToolBar toolBar = new JToolBar();
    private JButton buttonOpen = new JButton();
    private JButton buttonClose = new JButton();
    private JButton buttonHelp = new JButton();
    private JButton buttonNew = new JButton();
    private JButton buttonNewCat = new JButton();
    private JButton buttonNewComp = new JButton();
    private JButton buttonEndRace = new JButton();
    private JButton buttonPrint = new JButton();
    private JToggleButton buttonClock = new JToggleButton();
    private ImageIcon imageOpen = new ImageIcon(MainWindow.class.getResource("img/openfile.gif"));
    private ImageIcon imageClose = new ImageIcon(MainWindow.class.getResource("img/closefile.gif"));
    private ImageIcon imageHelp = new ImageIcon(MainWindow.class.getResource("img/help.gif"));
    private ImageIcon imageNew = new ImageIcon(MainWindow.class.getResource("img/page.gif"));
    private ImageIcon imageNewCat = new ImageIcon(MainWindow.class.getResource("img/form_add.gif"));
    private ImageIcon imageNewComp = new ImageIcon(MainWindow.class.getResource("img/page_user.gif"));
    private ImageIcon imageEndRace = new ImageIcon(MainWindow.class.getResource("img/date.gif"));
    private ImageIcon imagePrint = new ImageIcon(MainWindow.class.getResource("img/icon_imprimir.gif"));
    private ImageIcon imageClock = new ImageIcon(MainWindow.class.getResource("img/icon_clock.gif"));
    private ImageIcon imageList = new ImageIcon(MainWindow.class.getResource("img/icon_listado.gif"));
    //
    private GridLayout gridLayout = new GridLayout(1, 2);
    private GridLayout gridLayout2 = new GridLayout(2, 1);
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JPanel jPanel1 = new JPanel();
    private CompetitorTableModel competitorModel = new CompetitorTableModel(manager);
    private CategoryTableModel categoryModel = new CategoryTableModel();
    private JTable tb_competitors = new JTable(competitorModel); //Este lo usaremos directamente
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JPanel pn_raceDetail = new JPanel(); //Este lo usaremos directamente
    private JTable tb_category = new JTable(categoryModel);
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JTextField tf_date = new JTextField();
    private JTextField tf_location = new JTextField();
    private JTextField tf_nameRace = new JTextField();
    private JTextField tf_compLimit = new JTextField();
    private JTextField tf_distante = new JTextField();
    private JTextField tf_ended = new JTextField();
    private JTextField tf_insc_limited = new JTextField();
    private JTextField tf_registrationNum = new JTextField();
    private JTextField tf_numCategories = new JTextField(); //Este lo usaremos directamente

    public MainWindow(ApplicationManager man) {
        try {
            jbInit();
            manager = man;
            
            competitorModel.setManager(manager);
            categoryModel.setManager(manager);
            
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            addWindowListener(this);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar(menuBar);
        this.getContentPane().setLayout(layoutMain);
        panelCenter.setLayout(gridLayout);
        jPanel1.setLayout(gridLayout2);
        pn_raceDetail.setLayout(null);
        jLabel1.setText("Carrera:");
        jLabel1.setBounds(new Rectangle(15, 20, 105, 20));
        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel2.setText("Fecha:");
        jLabel2.setBounds(new Rectangle(15, 45, 105, 20));
        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel3.setText("Lugar:");
        jLabel3.setBounds(new Rectangle(15, 87, 105, 20));
        jLabel3.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setText("Límite competidores:");
        jLabel4.setBounds(new Rectangle(15, 110, 105, 20));
        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel5.setText("Distancia (Metros):");
        jLabel5.setBounds(new Rectangle(15, 135, 105, 20));
        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel5.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel6.setText("Inscritos:");
        jLabel6.setBounds(new Rectangle(225, 110, 90, 20));
        jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel6.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel7.setText("Categorías:");
        jLabel7.setBounds(new Rectangle(250, 135, 65, 20));
        jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel7.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel8.setText("Finalizada:");
        jLabel8.setBounds(new Rectangle(15, 160, 105, 20));
        jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel8.setHorizontalTextPosition(SwingConstants.RIGHT);
        jLabel9.setText("Inscrip. Limitadas:");
        jLabel9.setBounds(new Rectangle(15, 185, 105, 20));
        jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel9.setHorizontalTextPosition(SwingConstants.RIGHT);
        tf_date.setBounds(new Rectangle(130, 45, 290, 20));
        tf_date.setEditable(false);
        tf_location.setBounds(new Rectangle(130, 85, 290, 20));
        tf_location.setEditable(false);
        tf_nameRace.setBounds(new Rectangle(130, 20, 290, 20));
        tf_nameRace.setEditable(false);
        tf_compLimit.setBounds(new Rectangle(130, 110, 95, 20));
        tf_compLimit.setEditable(false);
        tf_distante.setBounds(new Rectangle(130, 135, 95, 20));
        tf_distante.setEditable(false);
        tf_ended.setBounds(new Rectangle(130, 160, 95, 20));
        tf_ended.setEditable(false);
        tf_insc_limited.setBounds(new Rectangle(130, 185, 95, 20));
        tf_insc_limited.setEditable(false);
        tf_registrationNum.setBounds(new Rectangle(325, 110, 95, 20));
        tf_registrationNum.setEditable(false);
        tf_numCategories.setBounds(new Rectangle(325, 135, 95, 20));
        tf_numCategories.setEditable(false);
        this.setSize(new Dimension(889, 586));
        this.setTitle("Running Manager");
        menuFile.setText("Archivo");
        menuFileExit.setText("Salir");
        menuFileExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                fileExit_ActionPerformed(ae);
            }
        });
        menuHelp.setText("Info");
        menuHelpAbout.setText("Acerca de...");
        menuHelpAbout.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                helpAbout_ActionPerformed(ae);
            }
        });
        statusBar.setText("");
        buttonOpen.setToolTipText("Abrir Carrera");
        buttonOpen.setIcon(imageOpen);
        buttonOpen.setDisabledIcon(null);
        buttonOpen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                openRace_ActionPerformed(ae);
            }
        });
        buttonClose.setToolTipText("Guardar");
        buttonClose.setIcon(imageClose);
        buttonOpen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                saveRace_ActionPerformed(ae);
            }
        });
        buttonHelp.setToolTipText("Ayuda");
        buttonHelp.setIcon(imageHelp);
        buttonHelp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    helpAction(e);
                }
            });
        buttonNew.setToolTipText("Nueva Carrera");
        buttonNew.setIcon(imageNew);
        buttonNew.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                newRace_Action(ae);
            }
        });
        buttonNewCat.setToolTipText("Nueva Categoría");
        buttonNewCat.setIcon(imageNewCat);
        buttonNewCat.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent ae) {
                newCategory_Action(ae);
            }});
        buttonNewComp.setToolTipText("Nuevo corredor");
        buttonNewComp.setIcon(imageNewComp);
        buttonNewComp.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent ae) {
                newCompetitor_Action(ae);
            }});
        
        buttonEndRace.setToolTipText("Añadir llegada a meta");
        buttonEndRace.setIcon(imageEndRace);
        buttonEndRace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addEndRace_Action(ae); 
            }
        });
        
        buttonPrint.setToolTipText("Imprimir datos");
        buttonPrint.setIcon(imagePrint);
        buttonPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                print_Action(ae);
            }
        });
        
        buttonClock.setToolTipText("Habilita/Deshabilita llegada a meta");
        buttonClock.setIcon(imageClock);
        //buttonClock.setPressedIcon(imageList);
        //buttonClock.setRolloverEnabled(false);
        buttonClock.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ae) {
                buttonClock_Action(ae);
            }
        });
        
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);
        this.getContentPane().add(statusBar, BorderLayout.SOUTH);
        toolBar.add(buttonOpen);
        toolBar.add(buttonNew);
        toolBar.add(buttonNewCat);
        toolBar.add(buttonNewComp);
        toolBar.add(buttonClose);
        toolBar.add(buttonHelp);
        toolBar.addSeparator(new Dimension(10,12));
        toolBar.add(buttonEndRace);
        toolBar.add(buttonPrint);
        toolBar.add(buttonClock);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        jScrollPane1.getViewport().add(tb_competitors, null);
        panelCenter.add(jScrollPane1, null);
        jScrollPane2.getViewport().add(tb_category, null);
        jPanel1.add(jScrollPane2, null);
        jPanel1.add(pn_raceDetail, null);
        pn_raceDetail.add(tf_numCategories, null);
        pn_raceDetail.add(tf_registrationNum, null);
        pn_raceDetail.add(tf_insc_limited, null);
        pn_raceDetail.add(tf_ended, null);
        pn_raceDetail.add(tf_distante, null);
        pn_raceDetail.add(tf_compLimit, null);
        pn_raceDetail.add(tf_nameRace, null);
        pn_raceDetail.add(tf_location, null);
        pn_raceDetail.add(tf_date, null);
        pn_raceDetail.add(jLabel9, null);
        pn_raceDetail.add(jLabel8, null);
        pn_raceDetail.add(jLabel7, null);
        pn_raceDetail.add(jLabel6, null);
        pn_raceDetail.add(jLabel5, null);
        pn_raceDetail.add(jLabel4, null);
        pn_raceDetail.add(jLabel3, null);
        pn_raceDetail.add(jLabel2, null);
        pn_raceDetail.add(jLabel1, null);
        panelCenter.add(jPanel1, null);

        this.getContentPane().add(panelCenter, BorderLayout.CENTER);
        
        categoryModel.addMouseListenerToHeaderInTable(tb_category);
        competitorModel.addMouseListenerToHeaderInTable(tb_competitors);
        
        tb_competitors.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                if (tb_competitors.getSelectedColumn() == 6)
                    
                    competitorModel.modifyCompetitor(tb_competitors.getSelectedRow());
                
                if (e.getClickCount() == 2 && tb_competitors.getSelectedColumn() == 7) {
                    
                    competitorModel.removeCompetitor(tb_competitors.getSelectedRow());
                    
                    manager.redisplay();
                }
            }
        } );
        
        tb_category.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                if (e.getClickCount() == 2) {
                    
                    categoryModel.updateCategotry(tb_category.getSelectedRow());

                }
            }
        } );
        buttonClock.setModel(new JToggleButton.ToggleButtonModel());
    }

    void fileExit_ActionPerformed(ActionEvent e) {

        manager.closeApplication();

        System.exit(0);
    }

    void helpAbout_ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, new MainWindow_AboutBoxPanel1(), "About", JOptionPane.PLAIN_MESSAGE);
    }

    void openRace_ActionPerformed(ActionEvent e) {

        if (manager.hasCreatedRaces()) {
            //Si tiene carreras, las mostramos
            SelectRacePane selectRace = new SelectRacePane(manager);
            selectRace.setVisible(true);
        } else {
            AlertWin alert = new AlertWin("No existen carreras para abrir", manager);
            alert.setVisible(true);
            return;
        }
    } //openRace_ActionPerformed
    
    void saveRace_ActionPerformed(ActionEvent e) {
        manager.saveDatabase();
    }

    void newRace_Action(ActionEvent e) {
        RaceForm raceForm = new RaceForm(manager);
        raceForm.setVisible(true);
    } //newRace_Action
    
    void newCategory_Action(ActionEvent e) {
        if (manager.getCurrentRace() != null) {
            CategoryForm catForm = new CategoryForm(manager);
            catForm.setVisible(true);
        } else {
            AlertWin alert = new AlertWin("Se necesita una carrera abierta", manager);
            alert.setVisible(true);
            return;
        }
    }
    
    void newCompetitor_Action(ActionEvent e) {
        if (manager.getCurrentRace() != null && 
            manager.getCurrentRace().getCategories().size() > 0) {
        
            if (manager.canAddCompetitor()) {
            
                CompetitorForm compForm = new CompetitorForm(manager);
                compForm.setVisible(true);
            }
        
        } else {
            AlertWin alert = new AlertWin("Se necesita una carrera abierta y categorías creadas", manager);
            alert.setVisible(true);
            return;
        }
    }

    /**
     * Muestra los datos de la carrera 
     * @param currentRace la carrera a mostrar
     */
    public void displayCurrentRace(Race currentRace) {

        //Primero rellenamos los datos de la carrera en el
        //JPane de la derecha.
        tf_date.setText(currentRace.getRaceDate());
        tf_location.setText(currentRace.getLocation());
        tf_nameRace.setText(currentRace.getName());
        tf_compLimit.setText(currentRace.getCompetitorsLimit() == 0 ? "Ilimitado" : "" + currentRace.getCompetitorsLimit());
        tf_distante.setText("" + currentRace.getDistance());
        tf_ended.setText(currentRace.isEnded() ? "Si" : "No");
        tf_insc_limited.setText(currentRace.isUpperLimit() ? "Si" : "No");
        tf_registrationNum.setText("" + currentRace.getCompetitors().size());
        tf_numCategories.setText("" + currentRace.getCategories().size());
        
        categoryModel.setRace(currentRace);

        //A continuación rellenamos la tabla de participantes
        //Sería algo así modelo.addListadoInicial(currentRace.getCompetitors())
        //y en el modelo quizás hay que hacer los eventos... para cada objeto que entra
        //El modelo además necesita una insercción, actualización y borrado para un único objeto
        competitorModel.setCompetitorsList(currentRace.getCompetitors());

        //Finalizamos rellenando la tabla de categorías
        categoryModel.setCategoryList(currentRace.getCategories());
    }
    
    
    public void addEndRace_Action(ActionEvent ae) {
        //Hay que hacer comprobaciones....
        if (manager.getCurrentRace() != null) {
            
            EndRaceForm endform = new EndRaceForm(manager);
            endform.setVisible(true);
        
        } else {
            AlertWin alert = new AlertWin("Se necesita una carrera abierta", manager);
            alert.setVisible(true);
            return;
        }
    }
    
    public void print_Action(ActionEvent ae) {
        if (manager.getCurrentRace() != null) {
            PrintForm printForm = new PrintForm(manager);
            printForm.setVisible(true);
        }
    }
    
    public void buttonClock_Action (ChangeEvent ae) {
        manager.setEditTogg(buttonClock.isSelected());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        manager.closeApplication();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        manager.closeApplication();
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    private void helpAction(ActionEvent e) {
        HelpWindow helpWin = new HelpWindow(manager);
        helpWin.setVisible(true);
    }
}
