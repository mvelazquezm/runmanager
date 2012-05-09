package es.mvm.runman.beans;

import es.mvm.runman.util.Constants;

import java.io.Serializable;

/**
 * Bean que representa a la entidad Categoria en base de datos
 */
public class Category implements Serializable, Comparable<Category> {
    
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor por defecto
     */
    public Category() {
        idCat = 0; 
        name = "";
        shortName = "";
        iniAge = 0;
        endAge = 0;
        sex = Constants.TSex.Indiferente;
    }
    
    public Category(String name, String shortName,
                    int iniAge, int endAge, Constants.TSex sex) {
        
        this.name = name;
        this.shortName = shortName;
        this.iniAge = iniAge;
        this.endAge = endAge;
        this.sex = sex;
        
        idCat = 0;      
    }
    
    /**
     * Identificador de categoria. De uso interno. Es posible que 
     * no sea necesario de cara a la implementacion.
     */
    private int idCat;
    
    /**
     * Nombre de la categoría
     * Por ejemplo Cadete
     */
    private String name;
    
    /**
     * Nombre corto de la categoría, por ejemplo CD
     */
    private String shortName;
    
    /**
     * Edad de inicio de esta categoría
     */
    private int iniAge;
    
    /**
     * Edad de máxima inclusive de esta categoría
     */
    private int endAge;
    
    /**
     * Sexo de la categoría. M,F
     */
    private Constants.TSex sex;
    
    //------------------------------------------
    // METODOS
    //------------------------------------------
    
    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setShortName(String param) {
        this.shortName = param;
    }

    public String getShortName() {
        return shortName;
    }

    public void setIniAge(int iniAge) {
        this.iniAge = iniAge;
    }

    public int getIniAge() {
        return iniAge;
    }

    public void setEndAge(int endAge) {
        this.endAge = endAge;
    }

    public int getEndAge() {
        return endAge;
    }

    public void setSex(Constants.TSex sex) {
        this.sex = sex;
    }

    public Constants.TSex getSex() {
        return sex;
    }
    
    public String toString() {
        return this.name;
    }


    @Override
    public int compareTo(Category o) {
        return name.toUpperCase().compareTo(o.getName().toUpperCase());
    }
}
