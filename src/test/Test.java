package test;

import java.io.File;

import core.Log;
import core.PdfToText;

public class Test {

	public static void main(String[] args) {
		File pdfFile = new File( "/temp/my_pdf.pdf" );
		
		String ocr = null;
		try {
			ocr = PdfToText.convert( pdfFile );
		} catch (Exception e) {
			Log.print( "Não foi possivel converter o Pdf para texto." , e );
		}
		
		System.out.println( ocr );
	}

}