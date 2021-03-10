package com.admon.bss.admon;

import com.admon.entity.admon.*;
import java.util.List;

public interface PerfilPaginaPantallaBss {
    
   public List<PerfilPaginaPantalla> getperfilPaginaPantalla(PerfilPaginaPantalla pantalla, int perfilId);
    public List<Integer> save(Integer userId, List<PerfilPaginaPantalla> perfilPaginaPantallaList);
    public void delete(List<Integer> perfilPaginaIdList);
    public  List<String> getPaginaFuncionalidades(int perfilId);
    public  List<PerfilPagina> getPerfilPagina(Integer perfilId, List<Integer> paginasListId);
    public Pagina getNombrePagina(String obj);
}
