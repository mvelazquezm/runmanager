package es.mvm.runman.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Esta clase simplemente contiene una lista ordenada de participantes.
 * Se puede incluir un tiempo, como fecha de referencia del primero en llegar.
 */
public class EndRace implements Serializable {
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto
     */
    public EndRace() {
        orderList = new ArrayList<Competitor>();
        
        firsTime = "";
    }
    
    private List <Competitor> orderList;
    
    /**
     * Tiempo empleado por el primero en llegar
     */
    private String firsTime;

    public void setOrderList(List<Competitor> orderList) {
        this.orderList = orderList;
    }

    public List<Competitor> getOrderList() {
        return orderList;
    }

    public void setFirsTime(String firsTime) {
        this.firsTime = firsTime;
    }

    public String getFirsTime() {
        return firsTime;
    }
}
