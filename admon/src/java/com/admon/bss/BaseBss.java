package com.admon.bss;

import com.admon.bss.util.Encrypt;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.OrganizacionUsuario;
import com.admon.entity.admon.Pagina;
import com.admon.entity.admon.StrutsAction;
import com.admon.entity.admon.Usuario;
import com.admon.model.util.SessionVar;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.Dispatcher;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.hibernate.HibernateException;

public class BaseBss {

    public BaseBss() {
    }

    /*
* Estos códigos se utilizan en las consultas a la BD con los objetos Criteria para agregarles parámetros
* de búsqueda. Anteriormente se hardcodeaba por ejemplo: criteria.add(Expression.eq("eliminadoId", 3));
* Ahora se utilizan constantes asi: criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
     */
    public ResourceBundle codigos = ResourceBundle.getBundle("com.admon.bss.resources.CodesBss");
    public final Integer ACTIVO = new Integer(codigos.getString("key_estatus_activo"));
    public final Integer INACTIVO = new Integer(codigos.getString("key_estatus_inactivo"));
    public final Integer ELIMINADO = new Integer(codigos.getString("key_eliminado_eliminado"));
    public final Integer NOELIMINADO = new Integer(codigos.getString("key_eliminado_noeliminado"));
    public final Integer CONFIGURACION_CORREO = new Integer(codigos.getString("key_configuracion_correo"));
    public final Integer CONFIGURACION_MENSAJESRECUPERARCONTRASENA = new Integer(codigos.getString("key_configuracion_mensajesrecuperarcontrasena"));
    public final Integer CONFIGURACION_MENSAJESCONFIRMACION = new Integer(codigos.getString("key_configuracion_mensajesconfirmacion"));
    public final Integer LENGUAJE = new Integer(codigos.getString("key_tipo_lenguaje"));
    public ResourceBundle version = ResourceBundle.getBundle("version");
    public final String BUILD_NUMBER = version.getString("build.number");
    public final String BUILD_TIMESTAMP = version.getString("build.timestamp").replace("\"", "").replace("\\", "");
    public final String SEGURIDAD_APLICACION_PARAMETRO = codigos.getString("key_seguridad_aplicacion_parametro");
    public final String SEGURIDAD_MODULO_PARAMETROS = codigos.getString("key_seguridad_modulo_parametro");
    public final String SEGURIDAD_OPERACION_PARAMETROS = codigos.getString("key_seguridad_operacion_parametro");
    public final String SEGURIDAD_OPER_FUNC_PARAMETROS = codigos.getString("key_seguridad_oper_func_parametro");
    public final String APLICACION = codigos.getString("key_properti_aplicacion");
    public final String MODULO = codigos.getString("key_properti_modulo");
    public final String OPERACION = codigos.getString("key_properti_operacion");
    public final String FUNCIONALIDAD = codigos.getString("key_properti_funcionalidad");
    public final String ASC = codigos.getString("key_properti_asc");
    public final String DESC = codigos.getString("key_properti_des");
    public final Integer PASSWORD = new Integer(codigos.getString("key_password"));
    public final String CONTENT = codigos.getString("key_content");
    public final String FOOTER = codigos.getString("key_footer");
    public final String NO = codigos.getString("key_no");
    public final String SI = codigos.getString("key_si");
    public final String PAYPAL = codigos.getString("key_paypal");
    public final String PAYNET = codigos.getString("key_paynet");
//    public final String IVA = codigos.getString("key_iva");
    
    public Logger log = Logger.getLogger(this.getClass());

    /*Error al procesar la recuperación de contraseña, reintente nuevamente.Si el problema persiste consulte con el administrador del sistema.
* Método que busca un archivo y lo elimina si lo encuentra. Generalmente
* usado para eliminar archivos upload vinculados a un registro.
*
* @param path Ruta del archivo
* @param filename Es el nombre del archivo
     */
    public void deleteFile(String path, String filename) {
        StringBuilder sb = new StringBuilder();
        if (path.endsWith("/")) {
            sb.append(path).append(filename);
        } else {
            sb.append(path).append("/").append(filename);
        }
        try {
            File file = new File(sb.toString());
            if (file.delete()) {
                System.out.println(filename + " fue eliminado");
            } else {
                System.out.println(filename + " no se pudo eliminar");
            }
        } catch (Exception e) {
            System.out.println(filename + " error al eliminar");
        }
    }

    public Integer lastPage(int resultadosTotales, int maxResult) {
        int lastPage = (new Double(java.lang.Math.ceil(
                new Double(resultadosTotales).doubleValue() / new Double(maxResult).doubleValue())).intValue());
        if (lastPage == 0) {
            lastPage = 1;
        }
        return lastPage;
    }

    public <E> E getFirst(List<E> eList) {
        if (eList.isEmpty()) {
            return null;
        } else {
            return eList.get(0);
        }
    }

    /**
     * Método que resuelve si un usuario puede ver toda la información o solo la
     * suya.
     *
     * @param criteria es el objeto que se utiliza para realizar la consulta
     * @param clazz
     * @return regresa el objeto criteria modificado
     */
    public DetachedCriteria resolvePrivacy(DetachedCriteria criteria, Class clazz) {
        Pagina pagina = findPageByActionClass(clazz);
        if (pagina == null) {
            // criteria.add(Expression.eq("creacionUsuario", getCurrentUserId()));
        } else {
            if (pagina.getPrivado() == null || pagina.getPrivado() == 0) {
                return criteria;
            } else {
                criteria.add(Expression.eq("creacionUsuario", getCurrentUserId()));
                return criteria;
            }
        }
        return criteria;
    }

    public Boolean hasGrid(Class clazz) {
        Pagina pagina = findPageByActionClass(clazz);
        if (pagina == null) {
            return true;
        } else {
            if (pagina.getSinGrid() == null || pagina.getSinGrid() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Boolean isIndividual(Class clazz) {
        Pagina pagina = findPageByActionClass(clazz);
        if (pagina == null) {
            return true;
        } else {
            if (pagina.getIndividual() == null || pagina.getIndividual() == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Busca en las paginas del usuario la que haga match con la clase action
     * que es mandada como parametro
     *
     * @param clazz clase action de struts
     * @return regresa la pagina o null si no encuentra ninguna coincidencia
     */
    public Pagina findPageByActionClass(Class clazz) {
        List<Pagina> paginaList = getPaginasByUsuario();
        List<StrutsAction> strutsActionList = getStrutsActions();
        for (Pagina pagina : paginaList) {
            for (StrutsAction strutsAction : strutsActionList) {
                if (pagina.getUrl() != null) {
                    if (pagina.getUrl().contains(strutsAction.getActionName())
                            && strutsAction.getClassName().equals(clazz.getCanonicalName())) {
                        return pagina;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Método que obtiene el objeto de sesión de usuario.
     *
     * @return regresa el objeto de sesión, si no lo encuentra regresa null
     */
    public SessionVar getUsuarioSession() {
        try {
            ServletRequestAttributes attr
                    = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            return (SessionVar) attr.getRequest().getSession(true)
                    .getAttribute("SESSION_PORTAL");
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    /**
     * Obtiene todas las url a las que el usuario tiene acceso
     *
     * @return
     */
    public List<String> getUrls() {
        List<String> urlList = new ArrayList();
        SessionVar sessionVar = getUsuarioSession();
        if (sessionVar != null) {
            List<Pagina> paginaList = sessionVar.getPagina();
            for (Pagina pagina : paginaList) {
                urlList.add(pagina.getUrl());
            }
        }
        return urlList;
    }

    public Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public List<Pagina> getPaginasByUsuario() {
        List<Pagina> paginaList = new ArrayList();
        SessionVar sessionVar = getUsuarioSession();
        if (sessionVar == null) {
            return paginaList;
        }
        return sessionVar.getPagina();
    }

    public Integer getCurrentUserId() {
        Integer currentUserId = 0;
        SessionVar sessionVar = getUsuarioSession();
        if (sessionVar != null) {
            return sessionVar.getUsuarioId();
        }
        return currentUserId;
    }

    public List<StrutsAction> getStrutsActions() {
        List<StrutsAction> strutsActionList = new ArrayList();
        List<String> packageList = getStrutsPackageNames();
        for (String packageName : packageList) {
            strutsActionList.addAll(getStrutsActions(packageName));
        }
        return strutsActionList;
    }

    /**
     * Obtiene las urls mappeades a una determinada clase del archivo xml de
     * struts
     *
     * @param packageName es el nombre del paquete
     * @param clazz es la clase
     * @return
     */
    public List<StrutsAction> getStrutsActions(String packageName) {
        List<StrutsAction> strutsActionList = new ArrayList();
        Map<String, ActionConfig> actionConfigList = Dispatcher.getInstance().
                getConfigurationManager().getConfiguration().
                getPackageConfig(packageName).getActionConfigs();
        for (Map.Entry<String, ActionConfig> entry : actionConfigList.entrySet()) {
            try {
                StrutsAction strutsAction = new StrutsAction();
                strutsAction.setClassName(entry.getValue().getClassName());
                strutsAction.setLocation(entry.getValue().getResults().
                        get("success").getParams().get("location").toString());
                strutsAction.setPackageName(packageName);
                strutsAction.setActionName("/" + packageName + "/" + entry.getKey() + ".action");
                strutsActionList.add(strutsAction);
            } catch (Exception e) {
                log.error(e);
            }
        }
        return strutsActionList;
    }

    /**
     * Obtiene el nombre de todos los paquetes del archivo de mapeo de actions
     * del xml de struts
     *
     * @return regresa una lista con el nombre de los paquetes
     */
    public List<String> getStrutsPackageNames() {
        return new ArrayList(Dispatcher.getInstance().getConfigurationManager().
                getConfiguration().getPackageConfigNames());
    }

    /*
* Método que obtiene el valor configuración especifica (identificada mediante el nombre)
*
* @param parametros
* @param parametroKey Es el nombre de la configuración del cual se queire obtener su valor
* @return
     */
    public String obtieneConfiguracionParametro(List<ConfiguracionParametro> parametros, String parametroKey) {
        for (int i = 0; i < parametros.size(); i++) {
            String parametroNombre = parametros.get(i).getNombre().replace(" ", "").toLowerCase();
            String key = parametroKey.replace(" ", "").toLowerCase();
            if (parametroNombre.equalsIgnoreCase(key)) {
                return parametros.get(i).getValor();
            }
        }
        return null;
    }

     public String obtieneConfiguracionParametroTemplate(List<ConfiguracionParametro> parametros, String parametroKey, String parametroFooterKey) {
        String template = "";
        String content = null;
        String footer = null;
        for (int i = 0; i < parametros.size(); i++) {
            String parametroNombre = parametros.get(i).getNombre().replace(" ", "").toLowerCase();
            String key = parametroKey.replace(" ", "").toLowerCase();
            String parametroFooter = parametroFooterKey.replace(" ", "").toLowerCase();
            if (parametroNombre.equalsIgnoreCase(key) || parametroNombre.equalsIgnoreCase(parametroFooter)) {
                if(parametroNombre.equals(CONTENT)){
                    content = new String(parametros.get(i).getValor());
                }
                if(parametroNombre.equals(FOOTER)){
                   footer = new String(parametros.get(i).getValor());
                }
                if(content != null && footer != null){
                    template = getTemplate(content, footer);
                    return template;
                }
            }
        }
        return null;
    }
    
    private String getTemplate(String content, String footer){
            String template = "<!doctype html>\n" +
"<html>\n" +
"  <head>\n" +
"    <meta name=\"viewport\" content=\"width=device-width\">\n" +
"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"    <title>Taasgo Email</title>\n" +
"    <style>\n" +
"    /* -------------------------------------\n" +
"        INLINED WITH htmlemail.io/inline\n" +
"    ------------------------------------- */\n" +
"    /* -------------------------------------\n" +
"        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
"    ------------------------------------- */\n" +
"    @media only screen and (max-width: 620px) {\n" +
"      table[class=body] h1 {\n" +
"        font-size: 28px !important;\n" +
"        margin-bottom: 10px !important;\n" +
"      }\n" +
"      table[class=body] p,\n" +
"            table[class=body] ul,\n" +
"            table[class=body] ol,\n" +
"            table[class=body] td,\n" +
"            table[class=body] span,\n" +
"            table[class=body] a {\n" +
"        font-size: 16px !important;\n" +
"      }\n" +
"      table[class=body] .wrapper,\n" +
"            table[class=body] .article {\n" +
"        padding: 10px !important;\n" +
"      }\n" +
"      table[class=body] .content {\n" +
"        padding: 0 !important;\n" +
"      }\n" +
"      table[class=body] .container {\n" +
"        padding: 0 !important;\n" +
"        width: 100% !important;\n" +
"      }\n" +
"      table[class=body] .main {\n" +
"        border-left-width: 0 !important;\n" +
"        border-radius: 0 !important;\n" +
"        border-right-width: 0 !important;\n" +
"      }\n" +
"      table[class=body] .btn table {\n" +
"        width: 100% !important;\n" +
"      }\n" +
"      table[class=body] .btn a {\n" +
"        width: 100% !important;\n" +
"      }\n" +
"      table[class=body] .img-responsive {\n" +
"        height: auto !important;\n" +
"        max-width: 100% !important;\n" +
"        width: auto !important;\n" +
"      }\n" +
"    }\n" +
"    /* -------------------------------------\n" +
"        PRESERVE THESE STYLES IN THE HEAD\n" +
"    ------------------------------------- */\n" +
"    @media all {\n" +
"      .ExternalClass {\n" +
"        width: 100%;\n" +
"      }\n" +
"      .ExternalClass,\n" +
"            .ExternalClass p,\n" +
"            .ExternalClass span,\n" +
"            .ExternalClass font,\n" +
"            .ExternalClass td,\n" +
"            .ExternalClass div {\n" +
"        line-height: 100%;\n" +
"      }\n" +
"      .apple-link a {\n" +
"        color: inherit !important;\n" +
"        font-family: inherit !important;\n" +
"        font-size: inherit !important;\n" +
"        font-weight: inherit !important;\n" +
"        line-height: inherit !important;\n" +
"        text-decoration: none !important;\n" +
"      }\n" +
"      .btn-primary table td:hover {\n" +
"        background-color: #34495e !important;\n" +
"      }\n" +
"      .btn-primary a:hover {\n" +
"        background-color: #34495e !important;\n" +
"        border-color: #34495e !important;\n" +
"      }\n" +
"    }\n" +
"    </style>\n" +
"  </head>\n" +
"  <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
"      <tr>\n" +
"        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
"        <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
"          <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
"\n" +
"            <!-- START CENTERED WHITE CONTAINER -->\n" +
"            <span class=\"preheader\" style=\"color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;\"> </span>\n" +
"            <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
"\n" +
"              <!-- START MAIN CONTENT AREA -->\n" +
"              <tr>\n" +
"                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
"                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
"                    <tr>\n" +
"                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" + content +
"                      </td>\n" +
"                    </tr>\n" +
"                  </table>\n" +
"                </td>\n" +
"              </tr>\n" +
"\n" +
"            <!-- END MAIN CONTENT AREA -->\n" +
"            </table>\n" +
"\n" +
"            <!-- START FOOTER -->\n" +
"            <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" + footer +
"            </div>\n" +
"            <!-- END FOOTER -->\n" +
"\n" +
"          <!-- END CENTERED WHITE CONTAINER -->\n" +
"          </div>\n" +
"        </td>\n" +
"        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
"      </tr>\n" +
"    </table>\n" +
"  </body>\n" +
"</html>";
        return template;
    }

    /*
* Método que desencripta una cadena.
*
* @param texto la cadena a desencriptar.
* @return Regresa la cadena desencriptada o null si no se pudo desencriptar.
     */
    public String desencripta(String texto) {
        try {
            Encrypt ncrypt = new Encrypt();
            return ncrypt.decrypt(texto);
        } catch (GeneralSecurityException ex) {
            return null;
        }
    }

    /*
* Método que encripta una cadena.
*
* @param texto la cadena a encriptar.
* @return Regresa la cadena encriptada o null si no se pudo encriptar.
     */
    public String encripta(String texto) {
        try {
            Encrypt ncrypt = new Encrypt();
            return ncrypt.encrypt(texto);
        } catch (GeneralSecurityException ex) {
            return null;
        }
    }

    /*
* Método que agrega un elemento dummy al inicio de la lista. El elemento se agrega en el indice 0 con el
* nombre "--Seleccione--". El objeto se agrega mediante java reflection y debe contener los metodos set +
* nombreEntitu + Id y setNombre.
*
* @param <E> Es el tipo de objeto
* @param list es la lista de objetos E
* @param e es un objeto nuevo
* @param c es la clase del objeto
* @return regresa la lista con elemento dummy agregado.
     */
    public <E> List<E> addDummy(List<E> list, E e, Class c) {
        try {
            String className = e.getClass().getSimpleName();
            Method metodoA = e.getClass().getMethod("set" + className + "Id", Integer.class);
            metodoA.invoke(e, 0);
            Method metodoB = e.getClass().getMethod("setNombre", String.class);
            metodoB.invoke(e, "--Seleccione--");
            Method[] metodos = e.getClass().getMethods();
            for (Method m : metodos){
                if (m.getName().equalsIgnoreCase("setValor")){
                    Method metodoC = e.getClass().getMethod("setValor", String.class);
                    metodoC.invoke(e, "-1");
                }
            }
            list.add(0, e);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Método que formatea una fecha con formato estandar
     *
     * @param fecha es la fecha que se va a formatear
     * @return regresa la fecha formateada como string
     */
    public String formatDate(String fecha) {
        String newString = "";
        try {
            newString = new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha));
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newString;
    }

    /**
     * Método que formatea una fecha con formato estandar
     *
     * @param fecha es la fecha que se va a formatear
     * @return regresa la fecha formateada como string
     */
    public String formatDate(Timestamp fecha) {
        if (fecha == null) {
            return "";
        } else {
            return formatDate(fecha.toString());
        }
    }

    /**
     * Método que agrega un elemento dummy al inicio de la lista. El elemento se
     * agrega en el indice 0 con el nombre que es enviado como parametro. El
     * objeto se agrega mediante java reflection y debe contener los metodos set
     * + nombreEntitu + Id y setNombre.
     *
     * @param <E> Es el tipo de objeto
     * @param list es la lista de objetos E
     * @param e es un objeto nuevo
     * @param c es la clase del objeto
     * @param dummyText es el texto del elemento dummy
     * @return regresa la lista con elemento dummy agregado.
     */
    public <E> List<E> addDummy(List<E> list, E e, Class c, String dummyText) {
        try {
            String className = e.getClass().getSimpleName();
            Method metodoA = e.getClass().getMethod("set" + className + "Id", Integer.class);
            metodoA.invoke(e, 0);
            Method metodoB = e.getClass().getMethod("setNombre", String.class);
            metodoB.invoke(e, dummyText);
            list.add(0, e);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            java.util.logging.Logger.getLogger(BaseBss.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /*
* Método para concatenar nombre y apellidos del usuario
*
* @param usuario objeto de tipo Usuario del cual se obtiene el nombre
*
* @return nombre String con el nombre concatenado
     */
    public String formateaNombre(Usuario usuario) {
        StringBuilder nombreCompleto = new StringBuilder();
        nombreCompleto.append(usuario.getNombre()).append(" ");
        if (usuario.getApellidoPaterno() != null) {
            nombreCompleto.append(usuario.getApellidoPaterno()).append(" ");
        }
        if (usuario.getApellidoMaterno() != null) {
            nombreCompleto.append(usuario.getApellidoMaterno());
        }
        return nombreCompleto.toString();
    }

        /*
* Método para concatenar nombre y apellidos del usuario
*
* @param usuario objeto de tipo Usuario del cual se obtiene el nombre
*
* @return nombre String con el nombre concatenado
     */
    public String formateaNombre(OrganizacionUsuario usuario) {
        StringBuilder nombreCompleto = new StringBuilder();
        nombreCompleto.append(usuario.getNombre()).append(" ");
        if (usuario.getApellidoPaterno() != null) {
            nombreCompleto.append(usuario.getApellidoPaterno()).append(" ");
        }
        if (usuario.getApellidoMaterno() != null) {
            nombreCompleto.append(usuario.getApellidoMaterno());
        }
        return nombreCompleto.toString();
    }

    /**
     * Concatena el nombre de usuario mas el nombre real
     *
     * @param usuarioList lista de objetos usuario
     * @return
     */
    public List<Usuario> usuarioYNombreCompleto(List<Usuario> usuarioList) {
        for (Usuario usuario : usuarioList) {
            if (usuario.getUsuarioId() == 0) {
                usuario.setUsuarioYNombreCompleto(usuario.getNombre());
            } else {
                usuario.setUsuarioYNombreCompleto(usuario.getUsuario() + " - "
                        + usuario.getNombreCompleto());
            }
        }
        return usuarioList;
    }

    /**
     * Método que setea el nombre completo a una lista de objetos usuario
     *
     * @param usuarioList lista de objetos usuario
     * @return
     */
    public List<Usuario> nombreCompleto(List<Usuario> usuarioList) {
        for (Usuario usuario : usuarioList) {
            usuario.setNombreCompleto(formateaNombre(usuario));
        }
        return usuarioList;
    }

    /**
     * Obtiene el encabezado del reporte PDF con los datos de nombre la
     * aplicación, nombre de cliente y nombre de reporte. Cualquier modificación
     * a la estructura de este método afectará a todos los reportes
     *
     * @param nombreReporte es el nombre del reporte
     * @return regresa un string HTML que representa el encanbezado del reporte
     */
    public String getReporteHTMLHeader(String nombreReporte, String clienteNombre, String sistemaNombre, String rutaLogo) {
        StringBuilder content = new StringBuilder();
        content.append("<table cellpadding='0' width='100%' border='0'>\n");
        content.append("<tr>\n");
        content.append("<td width='50px'>\n");
        content.append("<img src='").append(rutaLogo).append("' />\n");
        content.append("</td>\n");
        content.append("<td>\n");
        content.append("<div class='title-strong' >").append(clienteNombre.toUpperCase()).append("</div>");
        content.append("<div class='title' >").append(sistemaNombre.toUpperCase()).append("</div>");
        content.append("<div class='title' >REPORTE ").append(nombreReporte.toUpperCase()).append("</div>");
        content.append("</td>\n");
        content.append("<td style='text-align:right;'>\n");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        content.append(dateFormat.format(date));
        content.append("</td>\n");
        content.append("</tr>\n");
        content.append("</table>\n");
        return content.toString();
    }

    /**
     * Método auxiliar para la creación de reportes PDF a base de HTML. Genera
     * el inicio de un documento de HTML con estilos CSS básicos. Cualquier
     * modificación a la estructura de este método afectará a todos los reportes
     *
     * @return regresa un string con codigo HTML
     */
    public String getReporteHTMLDocument() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<meta name=\"http-equiv\" content=\"Content-type: text/html; charset=ISO-8859-1\" />\n");
        // Estilos CSS
        sb.append("<style>\n");
        sb.append("th {background-color: #D6D6D6;}\n");
        sb.append(".title-strong {font-size: 13px; font-family: Arial,Helvetica,sans-serif; font-weight: bold; }\n");
        sb.append(".title {font-size: 13px; font-family: Arial,Helvetica,sans-serif;}\n");
        sb.append("table { page-break-inside:auto; border-collapse: collapse; font-family:sans-serif; font-size: 12px; }\n");
        sb.append("tr    { page-break-inside:avoid; page-break-after:auto }\n");
        sb.append("thead { display:table-header-group }\n");
        sb.append("tfoot { display:table-footer-group }\n");
        sb.append("</style>\n");
        sb.append("</head>\n");
        return sb.toString();
    }

    public String addReportDataToSession(List list) {
        SessionVar sessionVar = getUsuarioSession();
        String reportKey = new Timestamp(System.currentTimeMillis()).toString();
        sessionVar.getReportData().put(reportKey, list);
        return reportKey;
    }

    public DetachedCriteria copy(DetachedCriteria criteria) {
        try {
            ByteArrayOutputStream baostream = new ByteArrayOutputStream();
            ObjectOutputStream oostream = new ObjectOutputStream(baostream);
            oostream.writeObject(criteria);
            oostream.flush();
            oostream.close();
            ByteArrayInputStream baistream = new ByteArrayInputStream(baostream.toByteArray());
            ObjectInputStream oistream = new ObjectInputStream(baistream);
            DetachedCriteria copy = (DetachedCriteria) oistream.readObject();
            oistream.close();
            return copy;
        } catch (Throwable t) {
            throw new HibernateException(t);
        }
    }
}