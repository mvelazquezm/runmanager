package es.mvm.runman.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class RaceManager implements Serializable {

    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;

    public RaceManager() {
        data = new ArrayList<Race> ();
    }
    
    /**
     * Lista con las carreras
     */
    private List <Race> data;

    public void setData(List<Race> data) {
        this.data = data;
    }

    public List<Race> getData() {
        return data;
    }
    
    ///Otros métodos
    public void addRace(Race race) {
        data.add(race);
    }
}
