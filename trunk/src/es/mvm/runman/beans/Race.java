package es.mvm.runman.beans;

import es.mvm.runman.util.Constants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Race implements Serializable {
    
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;

    public Race() {
        competitors = new ArrayList <Competitor> ();
        categories = new ArrayList <Category> ();
        endRace = new EndRace();
        
        id = 0;
        name = "";
        raceDate = "";
        location = "";
        competitorsLimit = 0;
        distance = 0.0f;
        ended = false;
        upperLimit = true;
    }
    
    public Race(String name, String date, String location,
                float distance, int limit, boolean upperLimit) {
        
        competitors = new ArrayList <Competitor> ();
        categories = new ArrayList <Category> ();
        endRace = new EndRace(); 
        
        this.id = 0;
        this.name = name;
        this.raceDate = date;
        this.location = location;
        this.competitorsLimit = limit;
        this.distance = distance;
        this.ended = false;
        this.upperLimit = upperLimit;
    }
    
    /**
     * Identificador de la carrera, no es necesario utilizar
     */
    private int id;
    
    /**
     * Nombre de la carrera
     */
    private String name;
    
    /**
     * Fecha de celebración de la carrera
     */
    private String raceDate;
    
    /**
     * Localización de la carrera
     */
    private String location;
    
    /**
     * Número máximo de participantes permitidos
     */
    private int competitorsLimit;
    
    /**
     * Longitud en metros de la carrera
     */
    private float distance;
    
    /**
     * Indica si la carrera ya ha finalizado
     */
    private boolean ended;
    
    /**
     * Indica si puede sobrepasarse el límite de 
     * competidores o no
     */
    private boolean upperLimit;
    
    /**
     * Lista de los competidores
     */
    private List <Competitor> competitors;
    
    /**
     * Lista de las categorías permitidas
     */
    private List <Category> categories;
    
    /**
     * Orden de llegada a meta
     */
    private EndRace endRace;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }

    public String getRaceDate() {
        return raceDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setCompetitorsLimit(int competitorsLimit) {
        this.competitorsLimit = competitorsLimit;
    }

    public int getCompetitorsLimit() {
        return competitorsLimit;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
    }

    public List<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setEndRace(EndRace endRace) {
        this.endRace = endRace;
    }

    public EndRace getEndRace() {
        return endRace;
    }

    public void setUpperLimit(boolean upperLimit) {
        this.upperLimit = upperLimit;
    }

    public boolean isUpperLimit() {
        return upperLimit;
    }
    
    
    //-------------------------------------------------------------------//
    
    public String toString() {
        return this.name + Constants.SEPARATOR + this.getRaceDate();
    }
    
    public void addCategory(Category category) {
        this.categories.add(category);
    }
    
    public void addCompetitor(Competitor comp) {
        this.competitors.add(comp);
    }
}
