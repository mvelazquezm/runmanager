package es.mvm.runman.gui;

import es.mvm.runman.core.ApplicationManager;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpWindow extends JFrame {
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JButton bt_acept = new JButton();
    private JTextArea ta_text = new JTextArea();

    public HelpWindow(ApplicationManager manager) {
        try {
            jbInit();
            
            this.setLocationRelativeTo(manager.getMainWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(636, 446));
        jScrollPane1.setBounds(new Rectangle(5, 5, 605, 360));
        bt_acept.setText("Aceptar");
        bt_acept.setBounds(new Rectangle(250, 370, 110, 35));
        bt_acept.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    closeAction(e);
                }
            });
        ta_text.setLineWrap(true);
        ta_text.setEditable(false);
        this.getContentPane().add(bt_acept, null);
        jScrollPane1.getViewport().add(ta_text, null);
        this.getContentPane().add(jScrollPane1, null);
        
        String texto = "Bienvenido a la ayuda de RunManager\n\n" +
                       "Con RunManager puede gestionar carreras deportivas, incluyendo los datos " +
                       "de la carrera, las distintas categorías y los competidores. Además se pueden " +
                       "obtener clasificaciones de carrera y listados en PDF de los participantes.\n\n" +
                       "Para empezar a trabajar, tiene que dar de alta una carrera con el botón Nueva carrera" +
                       "(folio blanco).\n\nSiempre que lo considere necesario puede grabar pulsando sobre Guardar " +
                        "(diskete con flecha amarilla).\n\nTras un cierre de aplicación, lo primero que debe de hacer " +
                        "es abrir una carrera, pulsando sobre el botón Abrir Carrera (carpeta amarila) y seleccionando.\n\n " +
                        "Una vez esté la carrera abierta, se procede a dar de alta Categorías con el botón Nueva Categoría (Formulario con cruz verde)\n.Las categorías tienen distintos " +
                        "datos. Al crear la categoría aparecerá en la tabla. Para editar categorías puede hacer " +
                        "doble clic sobre la categoría a editar (Los cambios no se refrescan al instante en la tabla general, " +
                        "aunque pulsando sobre ella se actualizan).\n\nLas tablas principales pueden ordenarse por cualquier campo, " +
                        "sólo hay que pulsar sobre el campo deseado para ordenar por el.\n\n" +
                        "Con las categorías creadas, se pueden dar de alta a competidores con el botón Nuevo corredor (folio con cara) " +
                        "los corredores tienen algunos datos obligatorios. Para editar un corredor puede pulsar sobre el icono que aparece en la tabla, " +
                        "para borrarlo debe hacer doble clic sobre el botón rojo correspondiente.\n\n" +
                        "Con el botón Imprimir (impresora) puede imprimir PDF con los corredores participantes y las llegadas a meta ordenadas.\n\n" +
                        "Con el botón Agregar llegada a meta (calendario) puede agregar la llegada de un corredor a meta, sólo se guarda el tiempo del mejor corredor " +
                        "Puede pulsar sobre ese botón o una vez, establecido el primer corredor en llegar a meta, puede activar el botón de llegadas " +
                        "(reloj) y directamente en la tabla ir pulsando sobre la columna de Fin, para habilitar la llegada del corredor";
        
        ta_text.append(texto);
            
            
    }

    private void closeAction(ActionEvent e) {
        this.setVisible(false);
    }
}
