package com.admon.bss.seguridad;

import com.admon.bss.BaseBss;
import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.bss.admon.OrganizacionUsuarioBss;
import com.admon.bss.admon.UsuarioBss;
import com.admon.bss.util.Envio;
import com.admon.entity.admon.ConfiguracionParametro;
import com.admon.entity.admon.OrganizacionUsuario;
import com.admon.entity.admon.Usuario;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

public class RecuperarContrasenaBssImpl extends BaseBss implements RecuperarContrasenaBss {

    /*
* Inyección de dependencias con Spring. Estas dependencias se configuran en el applicationContext.xml, y
* además necesitan un método setter por cada variable que se anexa al final de la clase.
     */
    public UsuarioBss usuarioBss;
    public ConfiguracionParametroBss configuracionParametroBss;
    private OrganizacionUsuarioBss organizacionUsuarioBss;

    public RecuperarContrasenaBssImpl() {
    }

    /*
* Método para recuperar la contraseña de un usuario por su nombre de usuario. La contraseña es enviada
* por correo electronico a la dirección especificada para ese usuario.
*
* @param user (String) es el nombre de usuario.
* @return Regresa un string con un mensaje sobre el resultado del método
     */
    @Override
    public String recuperarContrasena(String user) {

        // Configuración de correo
        List<ConfiguracionParametro> configuracionCorreo = obtieneConfiguraciones(CONFIGURACION_CORREO);

        // Mensajes de recuperación de contraseña
        List<ConfiguracionParametro> mensajesRecuperarContrasena = obtieneConfiguraciones(CONFIGURACION_MENSAJESRECUPERARCONTRASENA);

        // Obtener el destinatario
        Usuario usuario = obtieneUsuario(user);
        if (usuario == null) {
            return obtieneConfiguracionParametro(mensajesRecuperarContrasena, "error usuario").replace("[user]", user);
        }

        // Datos del destinatario
        String contrasena = desencripta(usuario.getContrasena());
        String destinatario = usuario.getCorreo();
        String destinatarioNombre = formateaNombre(usuario);

        // Datos del remitente
        String remitente = obtieneConfiguracionParametro(configuracionCorreo, "remitente");
        String remitenteContrasena = obtieneConfiguracionParametro(configuracionCorreo, "contrasena");

        // Correo
        String titulo = obtieneConfiguracionParametro(mensajesRecuperarContrasena, "envio titulo");
        String mensaje = obtieneConfiguracionParametro(mensajesRecuperarContrasena, "envio mensaje").replace("[password]", contrasena);

        // Configuración
        String clienteSMTP = obtieneConfiguracionParametro(configuracionCorreo, "smtp");
        String puerto = obtieneConfiguracionParametro(configuracionCorreo, "puerto");
        String autenticacion = obtieneConfiguracionParametro(configuracionCorreo, "autenticacion");

        // Envio
        Envio envio = new Envio();
        boolean resultado = envio.envioMail(titulo, "", mensaje, remitente, remitenteContrasena, destinatario, destinatarioNombre, clienteSMTP, puerto, autenticacion, null, null);
        if (resultado) {
            return obtieneConfiguracionParametro(mensajesRecuperarContrasena, "envio ok").replace("[email]", destinatario);
        } else {
            return obtieneConfiguracionParametro(mensajesRecuperarContrasena, "error envio");
        }
    }

    @Override
    public String cambiarContrasena(String user) {

        // Configuración de correo
        List<ConfiguracionParametro> configuracionCorreo = obtieneConfiguraciones(CONFIGURACION_CORREO);

        // Mensajes de recuperación de contraseña
        List<ConfiguracionParametro> mensajesRecuperarContrasena = obtieneConfiguraciones(CONFIGURACION_MENSAJESRECUPERARCONTRASENA);

        // Obtener el destinatario
        Usuario usuario = obtieneUsuario(user);
        if (usuario == null) {
            return obtieneConfiguracionParametro(mensajesRecuperarContrasena, "error usuario").replace("[user]", user);
        }

        // Datos del destinatario
        String contrasena = desencripta(usuario.getContrasena());
        String destinatario = usuario.getCorreo();
        String destinatarioNombre = formateaNombre(usuario);

        // Datos del remitente
        String remitente = obtieneConfiguracionParametro(configuracionCorreo, "remitente");
        String remitenteContrasena = obtieneConfiguracionParametro(configuracionCorreo, "contrasena");

        // Correo
        String titulo = obtieneConfiguracionParametro(mensajesRecuperarContrasena, "envio titulo");
        String mensaje = obtieneConfiguracionParametro(mensajesRecuperarContrasena, "envio mensaje contrasena").replace("[password]", contrasena);

        // Configuración
        String clienteSMTP = obtieneConfiguracionParametro(configuracionCorreo, "smtp");
        String puerto = obtieneConfiguracionParametro(configuracionCorreo, "puerto");
        String autenticacion = obtieneConfiguracionParametro(configuracionCorreo, "autenticacion");

        // Envio
        Envio envio = new Envio();
        boolean resultado = envio.envioMail(titulo,"", mensaje, remitente, remitenteContrasena, destinatario, destinatarioNombre, clienteSMTP, puerto, autenticacion, null, null);
        if (resultado) {
            return obtieneConfiguracionParametro(mensajesRecuperarContrasena, "envio ok").replace("[email]", destinatario);
        } else {
            return obtieneConfiguracionParametro(mensajesRecuperarContrasena, "error envio");
        }
    }

    @Override
    public String confirmarCuenta(String user) {

        // Configuración de correo
        List<ConfiguracionParametro> configuracionCorreo = obtieneConfiguraciones(CONFIGURACION_CORREO);

        // Mensajes de confirmacion
        List<ConfiguracionParametro> mensajesConfirmacion = obtieneConfiguraciones(CONFIGURACION_MENSAJESCONFIRMACION);

        // Obtener el destinatario
        OrganizacionUsuario usuario = obtieneOrganizacionUsuario(user);
        if (usuario == null) {
            return obtieneConfiguracionParametro(mensajesConfirmacion, "error usuario").replace("[user]", user);
        }
        
        // Datos del destinatario
        String contrasena = "";
        List<ConfiguracionParametro> contrasenas = obtieneConfiguraciones(PASSWORD);
        for (ConfiguracionParametro configuracionParametro : contrasenas) {
            if(configuracionParametro.getNombre().equals("PASSWORD_DECRYPT_MODE")){
                contrasena = configuracionParametro.getValor();
                break;
            }
        }
        //desencripta(usuario.getContrasena());
        String destinatario = usuario.getCorreo();
        String destinatarioNombre = formateaNombre(usuario);
        String login = usuario.getUsuarioAdmon();
        // Datos del remitente
        String remitente = obtieneConfiguracionParametro(configuracionCorreo, "remitente");
        String remitenteContrasena = obtieneConfiguracionParametro(configuracionCorreo, "contrasena");

        // Correo
        String titulo = obtieneConfiguracionParametro(mensajesConfirmacion, "envio titulo");
        String subject = obtieneConfiguracionParametro(mensajesConfirmacion, "subject envio");

        String mensaje = obtieneConfiguracionParametroTemplate(mensajesConfirmacion, "Envio mensaje confirmacion","Footer");
        mensaje = mensaje.replace("[password]", contrasena);
        mensaje = mensaje.replace("[usuario]", login);
        mensaje = mensaje.replace("[email]", destinatario);
        mensaje = mensaje.replace("[nombre]", destinatarioNombre);

        // Configuración
        String clienteSMTP = obtieneConfiguracionParametro(configuracionCorreo, "smtp");
        String puerto = obtieneConfiguracionParametro(configuracionCorreo, "puerto");
        String autenticacion = obtieneConfiguracionParametro(configuracionCorreo, "autenticacion");

        // Envio
        Envio envio = new Envio();
        boolean resultado = envio.envioMail(titulo,subject, mensaje, remitente, remitenteContrasena, destinatario, destinatarioNombre, clienteSMTP, puerto, autenticacion, null, null);
//        if (resultado) {
//            return obtieneConfiguracionParametro(mensajesConfirmacion, "envio ok").replace("[email]", destinatario);
//        } else {
//            return obtieneConfiguracionParametro(mensajesConfirmacion, "error envio");
//        }
            return "TRUE";
    }

    /*
* Método que obtiene las configuraciones para el envio de correo.
*
* @return Regresa una lista de objetos ConfiguracionParametro con la información de la consulta
     */
    @Override
    public List<ConfiguracionParametro> obtieneConfiguraciones(int configuracionId) {
        DetachedCriteria configuracionCorreo = configuracionParametroBss.createDetachedCriteria();
        configuracionCorreo.add(Expression.eq("configuracionId", configuracionId));
        configuracionCorreo.add(Expression.eq("estatusId", ACTIVO));
        configuracionCorreo.add(Expression.eq("eliminadoId", NOELIMINADO));
        List<ConfiguracionParametro> parametros = configuracionParametroBss.findByCriteria(configuracionCorreo);
        return parametros;
    }

    /*
* Método que busca en la base de datos los datos del usuario con el usuario especificado.
*
* @param user Es el nombre de usuario
* @return Regresa un objeto Usuario con la información de la consulta o null si no se encontró el usuario.
     */
    @Override
    public Usuario obtieneUsuario(String user) {
        DetachedCriteria criteria = usuarioBss.createDetachedCriteria();
        criteria.add(Expression.eq("usuario", user));
        criteria.add(Expression.eq("estatusId", ACTIVO));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        List<Usuario> list = usuarioBss.findByCriteria(criteria);
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public OrganizacionUsuario obtieneOrganizacionUsuario(String user) {
        DetachedCriteria criteria = organizacionUsuarioBss.createDetachedCriteria();
        criteria.add(Expression.eq("usuarioAdmon", user));
        criteria.add(Expression.eq("estatusId", ACTIVO));
        criteria.add(Expression.eq("eliminadoId", NOELIMINADO));
        List<OrganizacionUsuario> list = organizacionUsuarioBss.findByCriteria(criteria);
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /*
* Inyección de dependencias con Spring, cada referencia definida al inicio de la clase requiere un método
* Setter.
     */
    public void setUsuarioBss(UsuarioBss usuarioBss) {
        this.usuarioBss = usuarioBss;
    }

    public void setConfiguracionParametroBss(ConfiguracionParametroBss configuracionParametroBss) {
        this.configuracionParametroBss = configuracionParametroBss;
    }
    
    public void setOrganizacionUsuarioBss(OrganizacionUsuarioBss organizacionUsuarioBss) {
        this.organizacionUsuarioBss = organizacionUsuarioBss;
}
    
    
}