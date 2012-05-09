package es.mvm.runman;

import es.mvm.runman.gui.MainWindow;
import es.mvm.runman.core.ApplicationManager;

public class Main {
    public Main() {
        super();
    }

    public static void main(String[] args) {
        Main main = new Main();
        
        //Inicializamos los datos
        ApplicationManager appMan = new ApplicationManager();
        
        //Abrimos la ventana pricipal de la aplicación
        MainWindow mainWindow = new MainWindow(appMan);
        
        appMan.setMainWindow(mainWindow);
        mainWindow.setVisible(true);
        
    } //main
} //Clase Main
