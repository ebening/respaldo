package com.admon.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.io.FilenameUtils;

import com.admon.bss.admon.ConfiguracionParametroBss;
import com.admon.entity.admon.CatalogoParametro;

public class ImagenUtils {
	
	public static final String MARKED_FOR_DELETION = ".del";
	public static final String TEMPORARY_PREFIX = "TMP-";
	public static final String SAVED_PREFIX = "O-";
	
	public static final String CATALOGO_PARAMETRO_PREFIX="-CP-";

	public static String saveTempImage(String fileName, byte[] content) throws IOException{
		String savePath = System.getProperty("java.io.tmpdir");
		String ext = FilenameUtils.getExtension(fileName);
        fileName = TEMPORARY_PREFIX+System.currentTimeMillis()+"."+ext;
        String filePath = savePath + File.separator + fileName;
        FileOutputStream fos = new FileOutputStream(filePath);
    	fos.write(content);
    	fos.flush();
    	fos.close();
        return fileName;
	}
	
	public static Boolean moveTempImageToRepository(String tempFileName, String repositoryPath, String fileName){
		String savedPath = System.getProperty("java.io.tmpdir");
		String tempFilePath = savedPath + File.separator + tempFileName;
		File f = new File(tempFilePath);
		if(f.exists()){
			File uploadDir = new File(repositoryPath);
	        if (!uploadDir.exists()) {
	    		throw new RuntimeException("La ruta del repositorio de im\u00E1genes no est\u00E1 creada.");

	        }
	        if(!uploadDir.canWrite()){
	        	throw new RuntimeException("La ruta del repositorio de im\u00E1genes no tiene permisos de escritura.");
	        }
	        if(!repositoryPath.endsWith(File.separator)){
	        	repositoryPath+=File.separator;
	        }
	        String pathNewFile = repositoryPath + fileName;
	        File newFile = new File(pathNewFile);
	        try{
	        	FileInputStream fis = new FileInputStream(f);
	        	FileOutputStream fos = new FileOutputStream(newFile);
	        	byte[] buffer = new byte[1024];
	            int length;
	            while ((length = fis.read(buffer)) > 0) {
	                fos.write(buffer, 0, length);
	            }
	            fos.flush();
	            fos.close();
	            fis.close();
	            f.delete();
	        }catch(Exception e){
	        	e.printStackTrace();
	        	throw new RuntimeException("La imagen no se pudo mover: "+e.getMessage(), e);
	        }
	        newFile = new File(pathNewFile);
			if(!newFile.exists()){
	        	throw new RuntimeException("La imagen no pudo guargarse.");
			}
			return true;
		}else{
			throw new RuntimeException("La imagen temporal ya no existe.");
		}
	}
	
	public static Boolean isMarkedForDeletion(String fileName){
		if(fileName.endsWith(MARKED_FOR_DELETION)){
			return true;
		}
		return false;
	}
	
	public static Boolean isTemporaryImage(String fileName){
		if(fileName!=null && fileName.startsWith(TEMPORARY_PREFIX)){
			return true;
		}
		return false;
	}
	
	public static Boolean isSavedImage(String fileName){
		if(fileName!=null && fileName.startsWith(SAVED_PREFIX)){
			return true;
		}
		return false;
	}
	
	public static Boolean deleteFromRepository(String repositoryPath, String fileName){
		File f = new File(repositoryPath + File.separator + fileName);
		if(f.exists()){
			f.delete();
			return true;
		}
		return false;
		
	}
	
	public static String generateImageFileNameCatalogoParametro(CatalogoParametro cp){
		String fileName = SAVED_PREFIX;
		if(cp.getCatalogoParametroPK()!=null){
			fileName +=  cp.getCatalogoParametroPK().getOrganizacionId()+CATALOGO_PARAMETRO_PREFIX+cp.getCatalogoParametroPK().getCatalogoParametroId();
		}
		return fileName;
	}
	
	public static void procesaImagenCatalogoParametro(ResourceBundle codigos, ConfiguracionParametroBss bss, CatalogoParametro catalogoParametro){
		String pathRepositorio = bss.getValor(Integer.valueOf(codigos.getString("key_rutas")), codigos.getString("key_imagenes"));
		if(pathRepositorio==null||pathRepositorio.length()==0){
    		throw new RuntimeException("La ruta del repositorio de im\u00E1genes no est\u00E1 definida.");
    	}
		if(ImagenUtils.isTemporaryImage(catalogoParametro.getImagen())){
        	String repositoryImageName = ImagenUtils.generateImageFileNameCatalogoParametro(catalogoParametro)+"."+FilenameUtils.getExtension(catalogoParametro.getImagen());
        	ImagenUtils.moveTempImageToRepository(catalogoParametro.getImagen(), pathRepositorio, repositoryImageName);
        	catalogoParametro.setImagen(repositoryImageName);
        }else if(ImagenUtils.isSavedImage(catalogoParametro.getImagen())){
        	if(isMarkedForDeletion(catalogoParametro.getImagen())){
        		String toDelete = catalogoParametro.getImagen().replaceAll(MARKED_FOR_DELETION, "");
        		deleteFromRepository(pathRepositorio, toDelete);
        		catalogoParametro.setImagen(null);
        	}
        }
	}
	
}
