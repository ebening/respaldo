package com.admon.bss.admon;

import com.admon.bss.BaseBss;
import com.admon.dao.admon.PerfilPaginaPantallaDAO;
import com.admon.entity.admon.*;
import java.text.Normalizer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerfilPaginaPantallaBssImpl extends BaseBss implements PerfilPaginaPantallaBss {
   /* Inyección de dependencias con Spring. Estas dependencias se
 * configuran en el applicationContext.xml, y además necesitan un
 * método setter por cada variable que se anexa al final de la clase. */
    private PerfilPaginaPantallaDAO perfilPaginaPantallaDAO;
    private ConfiguracionParametroBss configuracionParametroBss;
    private UsuarioBss usuarioBss;
    private PerfilPaginaBss perfilPaginaBss;


    public PerfilPaginaPantallaBssImpl() {
    }
    
    @Override
    public List<PerfilPaginaPantalla> getperfilPaginaPantalla(PerfilPaginaPantalla pantalla, int usuarioId){
        PerfilPagina perfilPagina = null;
        String keyPantalla = pantalla.getPantalla();
        Pagina pag = getNombrePagina(pantalla.getActionName());
        perfilPagina = new PerfilPagina();
        perfilPagina.setPaginaId(pag.getPaginaId());
        keyPantalla = keyPantalla.substring(0, keyPantalla.length()-pantalla.getPatern().length()); 
        Usuario usuario = usuarioBss.findById(usuarioId);
        List<PerfilPagina> ppaginas = perfilPaginaBss.findByCriteriaIgnorePrivacy(perfilPagina);
        PerfilPagina pPagina = new PerfilPagina();
        if(!ppaginas.isEmpty()){
            for (PerfilPagina p :ppaginas){
                if(p.getPerfilId().intValue()== usuario.getPerfilId().intValue()){
                     pPagina = p;
                     break;
                }
            }
        }
        return perfilPaginaPantallaDAO.getperfilPaginaPantalla(getCleanedString(keyPantalla), pPagina.getPerfilPaginaId(), pantalla.getActionName(), usuario.getPerfilId());
    }
    public static String getCleanedString(String original){
        String cadenaNormalize = Normalizer.normalize(original, Normalizer.Form.NFD);   
        String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
        System.out.println("Resultado: " + cadenaSinAcentos);
        return cadenaSinAcentos;
    }
    @Override
    public List<Integer> save(Integer userId, List<PerfilPaginaPantalla> perfilPaginaPantallaList) {
        List<Integer> savedList = new ArrayList<Integer>();
        for (PerfilPaginaPantalla perfilPaginaPantalla : perfilPaginaPantallaList) {
            perfilPaginaPantalla.setModificacionUsuario(userId);
            perfilPaginaPantalla.setCreacionUsuario(userId);
            perfilPaginaPantalla.setCreacionFecha(new Timestamp(new Date().getTime()));
            perfilPaginaPantalla.setModificacionFecha(new Timestamp(new Date().getTime()));
            perfilPaginaPantallaDAO.savePerfilPaginaPantalla(perfilPaginaPantalla);
        }
        return savedList;
    }
    @Override
    public void delete(List<Integer> perfilPaginaIdList) {
        for (Integer perfilPaginaId : perfilPaginaIdList) {
            perfilPaginaPantallaDAO.deleteRolOperacion(perfilPaginaId);
        }
    }
    
    @Override
    public  List<String> getPaginaFuncionalidades(int perfilId) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("eliminadoId", NOELIMINADO);
        parametros.put("perfilId", perfilId);
        return perfilPaginaPantallaDAO.getPaginaFuncionalidades(parametros);
    }
    
    @Override
    public  List<PerfilPagina> getPerfilPagina(Integer perfilId, List<Integer> paginasListId) {
        return perfilPaginaPantallaDAO.getPerfilPagina(perfilId,paginasListId);
    }
    

    @Override
    public Pagina getNombrePagina(String obj) {
        Pagina pagina = new Pagina(); 
        String url ="";
        if(obj.equals("EstadoCuentaAction"))
            url= "com.admon.model.reportes.";
        else
            url= "com.admon.model.admon.";
           
         obj = url+obj;
        Class c;
        try {
            c = Class.forName(obj);
            pagina = findPageByActionClass(c);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PerfilPaginaPantallaBssImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pagina;
    }
    /*
 * Inyección de dependencias con Spring, cada
 * referencia definida al inicio de la clase requiere un método
 * Setter.
     */
    public void setPerfilPaginaPantallaDAO(PerfilPaginaPantallaDAO perfilPaginaPantallaDAO) {
        this.perfilPaginaPantallaDAO = perfilPaginaPantallaDAO;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }

    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }
    
    
    public void setPerfilPaginaBss(PerfilPaginaBss perfilPaginaBss) {
        this.perfilPaginaBss = perfilPaginaBss;
    }
    

}
