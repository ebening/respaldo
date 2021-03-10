package com.admon.entity.admon;

import java.io.Serializable;

public class SeguridadFuncionRestriccion implements Serializable {

    
    private Integer aplicacionId;
    private Integer funcionalidadId;
    private Integer rolId;
    private Integer estatusId;

    /*
*Constructor de la clase SeguridadRol.java
     */
    public SeguridadFuncionRestriccion() {
    }

    /**
     * @return the aplicacionId
     */
    public Integer getAplicacionId() {
        return aplicacionId;
    }

    /**
     * @param aplicacionId the aplicacionId to set
     */
    public void setAplicacionId(Integer aplicacionId) {
        this.aplicacionId = aplicacionId;
    }

    /**
     * @return the funcionalidadId
     */
    public Integer getFuncionalidadId() {
        return funcionalidadId;
    }

    /**
     * @param funcionalidadId the funcionalidadId to set
     */
    public void setFuncionalidadId(Integer funcionalidadId) {
        this.funcionalidadId = funcionalidadId;
    }

    /**
     * @return the rolId
     */
    public Integer getRolId() {
        return rolId;
    }

    /**
     * @param rolId the rolId to set
     */
    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    /**
     * @return the estatusId
     */
    public Integer getEstatusId() {
        return estatusId;
    }

    /**
     * @param estatusId the estatusId to set
     */
    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    
}
