package es.mvm.runman.core;

import es.mvm.runman.beans.RaceManager;

import es.mvm.runman.util.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase que controla los accesos a la base de datos de la aplicación
 *
 */
public class Database {
    
    /**
     * Puntero estático y único a la clase
     */
    static private Database singleton = null;
    
    /**
     * Constructor privado para que no pueda llamarse
     */
    private Database() {}
    
    /**
     * Retorna la instancia única a la clase. Si no existe
     * la crea
     * @return instancia a la clase
     */
    static public Database getSingleton() {
        if (singleton == null) {
            singleton = new Database();
        }
        return singleton;
    } //getSingleton
    
    
    /**
     * Este método carga del fichero toda la base de datos guardada anteriormente
     * Si no existe el fichero. Lo crea y crea una nueva base de datos vacía.
     * 
     * @return la base de datos cargada o vacía
     */
    public static RaceManager init() {
        
        File file = new File(Constants.DB_NAME);
        
        if (file.exists()) {
            //Si existe el fichero, lo leemos
            try {
                
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.DB_NAME));
                
                RaceManager raceManager = (RaceManager) ois.readObject();
                
                ois.close();
                
                return raceManager;
                
            } catch (Exception ex) {
                
                ex.printStackTrace();
                return null;
            }
        } else {
            //Si no existe el fichero, lo creamos e inicializamos
            //la base de datos
            try {
                file.createNewFile();
                
                RaceManager raceManager = new RaceManager();
                
                return raceManager;
                
            } catch (Exception ex) {
                
                ex.printStackTrace();
                return null;
            }
        } //end if-else existe fichero
        
    } //init
    
    public static void save(RaceManager raceManager) {
      try {
          
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.DB_NAME));
        oos.writeObject(raceManager);
        oos.flush();
        oos.close();
          
      } catch (Exception e) {
          
        e.printStackTrace();
      }
    }
    
} //Database
