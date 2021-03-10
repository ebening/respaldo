package com.admon.entity.admon;

import java.io.Serializable;

public class SeguridadRolOperFunc implements Serializable  {

    private String id;
    private String nombre;
    private String anidar;

    /*
*Constructor de la clase Perfil.java
     */
    public SeguridadRolOperFunc() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the anidar
     */
    public String getAnidar() {
        return anidar;
    }

    /**
     * @param anidar the anidar to set
     */
    public void setAnidar(String anidar) {
        this.anidar = anidar;
    }
    

}
