package es.mvm.runman.beans;

import es.mvm.runman.util.Constants;

import java.io.Serializable;

import java.util.Date;

public class Competitor implements Serializable, Comparable<Competitor> {
    
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor por defecto
     */
    public Competitor() {
        dorsal = 0;
        name = "";
        surname = "";
        sex = Constants.TSex.Femenino;
        category = null;
        bornDate = new Date();
        timeSpend = new Date();
        endRun = false;
        local = false;
    } //Competitor
    
    public Competitor(int dorsal, String surname, String name, Date bornDate, Constants.TSex sex, Category cat, boolean local) {
        this.dorsal = dorsal;
        this.surname = surname;
        this.name = name;
        this.bornDate = bornDate;
        this.sex = sex;
        this.category = cat;
        this.local = local;
        
        timeSpend = new Date();
        endRun = false;
    }
    
    /**
     * Número de dorsal.
     * Puede generarse de forma automática o manual
     */
    private int dorsal;
    
    /**
     * Nombre del participante
     */
    private String name;
    
    /**
     * Apellidos del participante
     */
    private String surname;
    
    /**
     * Sexo del participante
     */
    private Constants.TSex sex;
    
    /**
     * La categoría con la que participa
     */
    private Category category;
    
    /**
     * Fecha de nacimiento
     */
    private Date bornDate;
    
    /**
     * Tiempo invertido en la carrera
     */
    private Date timeSpend;
    
    /**
     * Si ha finalizado la carrera o no
     */
    private boolean endRun;
    
    /**
     * Indica si es un corredor local
     */
    private boolean local;


    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(Constants.TSex sex) {
        this.sex = sex;
    }

    public Constants.TSex getSex() {
        return sex;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setTimeSpend(Date timeSpend) {
        this.timeSpend = timeSpend;
    }

    public Date getTimeSpend() {
        return timeSpend;
    }

    public void setEndRun(boolean endRun) {
        this.endRun = endRun;
    }

    public boolean isEndRun() {
        return endRun;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public boolean isLocal() {
        return local;
    }

    @Override
    public int compareTo(Competitor o) {
        //Por defecto, ordenamos por nombre
        int lastCmp = surname.toUpperCase().compareTo(o.surname.toUpperCase());
        return (lastCmp != 0 ? lastCmp : name.toUpperCase().compareTo(o.name.toUpperCase()));
    }
} //clase
