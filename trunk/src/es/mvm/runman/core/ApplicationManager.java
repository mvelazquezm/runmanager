package es.mvm.runman.core;

import es.mvm.runman.beans.Category;
import es.mvm.runman.beans.Competitor;
import es.mvm.runman.beans.EndRace;
import es.mvm.runman.beans.Race;
import es.mvm.runman.beans.RaceManager;
import es.mvm.runman.gui.AlertWin;
import es.mvm.runman.gui.MainWindow;

import es.mvm.runman.util.Constants;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

/**
 * Clase central de toda la aplicación.
 */
public class ApplicationManager {
    
    /**
     * Manager principal de los datos
     */
    private RaceManager raceManager;
    
    /**
     * Carrera abierta actual
     */
    private Race currentRace;
    
    /**
     * Puntero a la ventana principal
     */
    private MainWindow main;
    
    /**
     * Indica si el botón de cambio en meta está pulsado
     */
    private boolean togglePressed;
    
    
    static final Comparator<Competitor> CAT_NAME_ORDER = new Comparator<Competitor>() {
        public int compare(Competitor c1, Competitor c2) {   
            int compValue = c1.getCategory().compareTo(c2.getCategory()); 
            return compValue != 0 ? compValue : c1.compareTo(c2);  
        }
    };
    
    static final Comparator<Competitor> CAT_DORSAL_ORDER = new Comparator<Competitor>() {
        public int compare(Competitor c1, Competitor c2) {   
            int compValue = c1.getCategory().compareTo(c2.getCategory());
            
            if (compValue != 0) return compValue;
            
            if (c1.getDorsal() > c2.getDorsal())
                return 1;
            else if (c1.getDorsal() == c2.getDorsal())
                return 0;
            else return -1; 
        }
    };
    
    static final Comparator<Competitor> DORSAL_ORDER = new Comparator<Competitor>() {
        public int compare(Competitor c1, Competitor c2) {   
            if (c1.getDorsal() > c2.getDorsal())
                return 1;
            else if (c1.getDorsal() == c2.getDorsal())
                return 0;
            else return -1; 
        }
    };
    
    /**
     * Constructor por defecto
     */
    public ApplicationManager() {
        raceManager = Database.getSingleton().init();
        currentRace = null;
        main = null;
        togglePressed = false;
    }
    
    public void saveDatabase() {
        Database.getSingleton().save(raceManager);
    }
    
    /**
     * Cierra la aplicación
     */
    public void closeApplication() {
        saveDatabase();
    }
    
    public void createRace(String name, String date, String location, float distance,
                           int limit, boolean upperLimit) {
        Race race = new Race(name, date, location, distance, limit, upperLimit);               
        raceManager.addRace(race);
        
        setCurrentRace(race);
        
    }
    
    public void createCategory(String name, String shortName, int minAge, int maxAge, Constants.TSex sex) {
        
        Category category = new Category(name, shortName, minAge, maxAge, sex);
        
        currentRace.addCategory(category);
        
        redisplay();
        
    }
    
    public void createCompetitor(int dorsal, String surname, String name, Date bornDate, Constants.TSex sex, Category cat, boolean local) {
        
        Competitor comp = new Competitor(dorsal, surname, name, bornDate, sex, cat, local);
        
        currentRace.addCompetitor(comp);
        
        redisplay();
        
        
    }
    
    public void updateCompetitor() {
        redisplay();
    }
    
    public void addCompetitorEnd(String time, int dorsal, Category cat) {
        
        //Si tiene categoría, buscamos un dorsal que tenga esa categoría.
        EndRace endRace = currentRace.getEndRace();
        String error = "";
        boolean added = false;
        
        if (cat != null) {
            for (Competitor comp : currentRace.getCompetitors()) {
                if (comp.getDorsal() == dorsal && comp.getCategory().equals(cat)) {
                    if (!comp.isEndRun()) {
                        comp.setEndRun(true);
                        
                        endRace.getOrderList().add(comp);
                        added = true;
                        endRace.setFirsTime(time);
                        currentRace.setEnded(true);
                        
                    } else {
                        error = "El competidor dorsal " + dorsal + ", ya se había registrado en meta";
                    }
                }
            }
        } else {
            for (Competitor comp : currentRace.getCompetitors()) {
                if (comp.getDorsal() == dorsal) {
                    if (!comp.isEndRun()) {
                        comp.setEndRun(true);
                        
                        endRace.getOrderList().add(comp);
                        added = true;
                        endRace.setFirsTime(time);
                        currentRace.setEnded(true);
                        
                    } else {
                        error = "El competidor dorsal " + dorsal + ", ya se había registrado en meta";
                    }
                }
            }
        }
        
        if (!added && error.equals("")) {
            error = "No se ha encontrado al dorsal " + dorsal + " en la carrera";
        }
        
        if (!error.equals("")) {
            AlertWin alert = new AlertWin(error, this);
            alert.setVisible(true);
        }
        
        redisplay();
    }
    
    public String getEndTime() {
        return currentRace.getEndRace().getFirsTime();
    }
                                
                    
    
    public void setMainWindow(MainWindow main) {
        this.main = main;
    }
    
    public MainWindow getMainWindow() {
        return this.main;
    }

    public boolean hasCreatedRaces () {
        boolean result = raceManager.getData().size() == 0 ? false : true;

        return result;
    }
    
    public List<Race> getAllRaces() {
        return raceManager.getData();
    }
    
    public List<Category> getAllCategories() {
        return currentRace.getCategories();
    }
    
    public void setCurrentRace(Race race) {
        this.currentRace = race;
        
        main.displayCurrentRace(currentRace);
    }
    
    public Race getCurrentRace() {
        return this.currentRace;
    }
    
    public void redisplay() {
        main.displayCurrentRace(currentRace);
    }
    
    public int getNextGenericDorsal() {
        int maxDorsal = 0;
        if (currentRace.getCompetitors().size() == 0)
            return 1;
        else {
            
            for (Competitor comp : currentRace.getCompetitors()) {
                
                if (comp.getDorsal() > maxDorsal)
                    maxDorsal = comp.getDorsal();
            }
            
            
            return maxDorsal + 1;
        }
    }
    
    public int getNextCategoryDorsal (Category cat) {
        
        Competitor lastComp = null;
        int maxDorsal = 0;
        
        for (Competitor comp : currentRace.getCompetitors()) {
            if (comp.getCategory().equals(cat) && comp.getDorsal() > maxDorsal) {
                lastComp = comp;
                maxDorsal = comp.getDorsal();
            }
        }
        
        if (lastComp == null)
            return 1;
        else
            return lastComp.getDorsal() + 1;
    }
    
    public int edad(String fecha_nac) {
       
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = formato.format(fechaActual);
        String[] dat1 = fecha_nac.split("/");
        String[] dat2 = hoy.split("/");
        int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
        if (mes < 0) {
          anos = anos - 1;
        } else if (mes == 0) {
          int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
          if (dia > 0) {
            anos = anos - 1;
          }
        }
        return anos;
    }
    
    /**
     * Ordenamos el listado y lo imprimimos en pdf.
     * 
     * Si se utiliza el listado de meta. Sólo se mostrarán los datos globales de llegada
     * o los datos de llegada por categoría.
     * 
     * Si no se utiliza el listado de meta. Se pueden mostrar tanto por dorsal como por nombre
     * y tanto en un listado global, como en un por categoría.
     * 
     * @param name si es cierto, se ordena por apellidos y nombre, sino, por dorsal
     * @param global si es cierto, se obtiene un listado global, sino, por categoría
     * @param meta si es cierto, sólo se usa el listado de meta, sino el listado normal
     */
    public void printOrderedRace(boolean name, boolean global, boolean meta) {
        
        PrintManager printManager = new PrintManager();
        
        if (meta) {
        
            //Ha llegado a meta. Usamos listado de endRace.
        
            if (global) {
                //Listado global y por órden de llegada.
                //No hay que hacer nada sobre el vector de llegadas.
                printManager.printEnd(currentRace);
            
            } else {
                //Listado por categorías. Usamos un vector de vectores.
                
                List<List<Competitor>> listadoFinal = new ArrayList<List<Competitor>>();
                
                for (Competitor comp : currentRace.getEndRace().getOrderList()) {
                    
                    boolean added = false;
                    
                    for (List<Competitor> semiList : listadoFinal) {
                        
                        if (!added) {
                        
                            if (semiList.size() == 0) {
                                
                                //El listado está vacío. Lo agregamos
                                semiList.add(comp);
                                added = true;
                            } else {
                                
                                if (semiList.get(0).getCategory().equals(comp.getCategory())/* ||
                                
                                semiList.get(0).getCategory().getName().equals(comp.getCategory().getName())*/) {
                                    
                                    //Estamos ante un listado que es de la misma categoría., lo incluimos al final
                                    semiList.add(comp);
                                    added = true;
                                }
                                
                            }
                        
                        }
                        
                    } //end for listados.
                    
                    if (!added) {
                        
                        //No se ha agregado. creamos una nueva lista y la incluimos.
                        List<Competitor> newList = new ArrayList<Competitor>();
                        
                        newList.add(comp);
                        
                        listadoFinal.add(newList);
                    }
                    
                } //end for.
                
                //Finalmente pasamos estos arrays al manager de impresión
                printManager.printEndCat(currentRace, listadoFinal);
                
            } //end if-else global
            
        } else {
            
            //Es un listado normal. Hay que ver el resto de parámetros para
            //realizar los filtros
            
            if (global && name) {

                Collections.sort(currentRace.getCompetitors());
            } else if (global && !name) {
                
                Collections.sort(currentRace.getCompetitors(), DORSAL_ORDER);
            } else if (!global && name) {
                Collections.sort(currentRace.getCompetitors(), CAT_NAME_ORDER);
            } else {
                //!global !name
                Collections.sort(currentRace.getCompetitors(), CAT_DORSAL_ORDER);
            }
            
            printManager.printVector(currentRace, global);
            
        } //end if-else meta
    }
    
    public boolean canEditEnd() {
        boolean can = false;
        
        if (currentRace != null) {
            
            if (!currentRace.getEndRace().getFirsTime().equals("") && togglePressed) {
                can = true;
            }
        }
        
        return can;
    }
    
    public void setEditTogg(boolean state) {
        togglePressed = state;
    }
    
    public void removeCompetitor(Competitor competitor) {
        
        Competitor compToDelete = null;
        
        for (Competitor aux : currentRace.getEndRace().getOrderList()) {
            
            if (competitor.equals(aux))
                compToDelete = aux;
            else if (competitor.getDorsal() == aux.getDorsal() &&
                     competitor.getCategory().equals(aux.getCategory()))
                compToDelete = aux;
            
        }
        
        currentRace.getEndRace().getOrderList().remove(compToDelete);
        
    }
    
    public boolean canAddCompetitor() {
        
        boolean can = true;
        
        if (currentRace.isEnded()) {
            can = false;
            
            AlertWin alert = new AlertWin("La carrera ya está finalizada. No se pueden dar de alta más corredores.", this);
            alert.setVisible(true);
        } else if (currentRace.getCompetitors().size() >= currentRace.getCompetitorsLimit() && currentRace.getCompetitorsLimit() != 0) {
            
            if (currentRace.isUpperLimit()) {
                can = true;
                AlertWin alert = new AlertWin("Se han sobrepasado las inscripciones, pero se permite hacer el alta.", this);
                alert.setVisible(true);
            } else {
                can = false;
                AlertWin alert = new AlertWin("Se ha alcanzado el límite de corredores, no puede dar más altas", this);
                alert.setVisible(true);
            }
        }
        
        return can;
        
    }
}
