package com.admon.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.admon.model.BaseModel;
import com.opensymphony.xwork2.ActionContext;

public class DownloadAction extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private HttpServletRequest servletRequest;
    private InputStream fileInputStream;
    public String file;

    @Override
    public String execute() {
        ResourceBundle codigos = ResourceBundle.getBundle("com.admon.bss.resources.CodesBss");

        // Obtener la ruta raiz donde se esta el archivo que se va a descargar
        SessionVar sessionVar = (SessionVar) ActionContext.getContext().getSession().get("SESSION_PORTAL");
        Map<String, String> configuraciones = sessionVar.getConfiguraciones();
        String path = configuraciones.get(codigos.getString("key_imagenes"));

        // Obtener el nombre del archivo
        setFile(servletRequest.getParameter("file_path"));
        // Descarga
        try {
            StringBuilder sb = new StringBuilder();
            if (path.endsWith("/")) {
                sb.append(path).append(getFile());
            } else {
                sb.append(path).append("/").append(getFile());
            }
            File descarga = new File(sb.toString());
            if (descarga.exists()) {
                fileInputStream = new FileInputStream(descarga);
            } else {
                return ERROR;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DownloadAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}