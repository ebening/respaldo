package com.admon.model.admon;

import com.admon.bss.admon.PerfilPaginaPantallaBss;
import com.admon.entity.admon.*;
import com.admon.model.BaseModel;
import java.util.List;

public class PerfilPaginaPantallaDWR extends BaseModel {

    /*
 * Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase.
     */
    private PerfilPaginaPantallaBss perfilPaginaPantallaBss;

    public PerfilPaginaPantallaDWR() {
    }

    public List<PerfilPaginaPantalla> getFuncionalidadPantalla(PerfilPaginaPantalla pantalla){
        Integer usuarioId = obtieneSessionVar().getUsuarioId();
        List<PerfilPaginaPantalla> list = perfilPaginaPantallaBss.getperfilPaginaPantalla(pantalla,usuarioId);
        return list;
    }
   
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setPerfilPaginaPantallaBss(PerfilPaginaPantallaBss perfilPaginaPantallaBss) {
        this.perfilPaginaPantallaBss = perfilPaginaPantallaBss;
    }

}
