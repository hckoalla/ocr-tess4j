package core;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PdfToText {
	
	public static String convert(File file) throws Exception{
		
		List<File> fileList;
		
		ResourceBundle properties = Util.getProperties();
		String CONVERT_TO_IMAGE = properties.getString( "CONVERT_TO_IMAGE" );
		if( !Util.isEmptyOrNull( CONVERT_TO_IMAGE ) && !Boolean.parseBoolean( CONVERT_TO_IMAGE ) ){
			fileList = PdfToImage.convert( file );
		} else {
			fileList = Arrays.asList( file );
		}
		
		StringBuilder ocr = new StringBuilder();
		for( File f : fileList ){
			if( fileList.indexOf( f ) != 0 ){
				ocr = ocr.append( Util.BREAK_PAGE_CHARACTER );
			}
			ocr = ocr.append( FileToText.convert( f ) );
		}
		
		return ocr.toString();
	}
	
}