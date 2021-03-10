package com.admon.servlet;
 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.admon.model.util.ImagenUtils;
import com.google.gson.JsonObject;
 
public class FileUploadServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject r = new JsonObject();
        try{
        	MultiPartRequestWrapper req = (MultiPartRequestWrapper)request;
            File[] files = req.getFiles("img");
	        for(int i=0;i<files.length;i++){
	        	File f = files[i];
	        	String fileName = req.getFileNames("img")[i];
	        	byte[] content = Files.readAllBytes(f.toPath());
	        	r.addProperty("fileName", fileName);
	        	r.addProperty("fileResult", ImagenUtils.saveTempImage(fileName, content));
	        }
        } catch (Exception e) {
        	e.printStackTrace();
            r.addProperty("error", e.getMessage());
        }
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(r.toString());
        writer.flush();
    }
    
}