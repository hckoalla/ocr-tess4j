package core;

import java.io.File;

public class ImageToText {
	
	public static String convert(File file) throws Exception{
		return FileToText.convert( file );
	}
	
}