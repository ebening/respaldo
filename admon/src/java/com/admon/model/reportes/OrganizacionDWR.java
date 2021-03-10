package com.admon.model.reportes;

import com.admon.bss.reportes.OrganizacionBss;
import com.admon.entity.reportes.OrganizacionAdmon;
import com.admon.model.BaseModel;

public class OrganizacionDWR extends BaseModel {

    /*
     * Inyección de dependencias con Spring. Estas dependencias se
     * configuran en el applicationContext.xml, y además necesitan un
     * método setter por cada variable que se anexa al final de la clase.
     */
    private OrganizacionBss organizacionBss;

    public OrganizacionDWR() {
    }

    /*
     * Método que busca el registro <b>organizacion</b> por su ID en la tabla de <b>OrganizacionAdmon</b>.
     * @param id es el ID del registro que se quiere obtener.
     * @return Regresa un objeto tipo <b>OrganizacionAdmon</b> con la información del registro <b>organizacion</b>.
     */
    public OrganizacionAdmon findById(Integer organizacionId) {
        return organizacionBss.findById(organizacionId);
    }

    /**
     * Método que obtiene el primer resultado de una consulta, utilizado cuando
     * la pagina tiene la propiedad individual
     *
     * @return regresa el primer resultado de la consulta, si no hay resultados
     * regresa null
     */
    public OrganizacionAdmon findFirst() {
        return organizacionBss.findFirst();
    }

    /*
     * Inyección de dependencias con Spring, cada
     * referencia definida al inicio de la clase requiere un método
     * Setter.
     */
    public void setOrganizacionBss(OrganizacionBss organizacionBss) {
        this.organizacionBss = organizacionBss;
    }

}