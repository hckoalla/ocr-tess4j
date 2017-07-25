package core;

import java.io.File;
import java.util.ResourceBundle;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

class FileToText {
	
	static String convert(File file) throws Exception{
		String ocr = null;
		try{
			ITesseract instance = new Tesseract();
			
			ResourceBundle properties = Util.getProperties();
			
			instance.setLanguage( properties.getString( "TESS4J_LANGUAGE" ) );
			
			String TESS4J_TESSDATA_FILEPATH = properties.getString( "TESS4J_TESSDATA_FILEPATH" );
			if( !Util.isEmptyOrNull( TESS4J_TESSDATA_FILEPATH ) ){
				instance.setDatapath( TESS4J_TESSDATA_FILEPATH );
			}
			
			ocr = instance.doOCR( file );
		} catch (Exception e) {
			Log.print("Houve um erro ao converter o arquivo", e);
			throw e;
		}
		return ocr;
	}

}