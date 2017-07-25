package core;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;

class PdfToImage {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");

	static List<File> convert(File filePDF) throws Exception{
		PDFDocument document = new PDFDocument();
		try {
			document.load( new FileInputStream( filePDF ) );
		} catch (IOException e) {
			//TODO log
			throw e;
		}

		SimpleRenderer renderer = new SimpleRenderer();
		renderer.setResolution( 300 );

		List<Image> renderedImageList = null;
		try {
			renderedImageList = renderer.render(document);
		} catch (Exception e) {
			//TODO log
			throw e;
		}
		
		ResourceBundle properties = Util.getProperties();
		
		List<File> fileImageList = new ArrayList<File>();
		try {
			for( Image i : renderedImageList ){
				File f = new File( properties.getString( "TEMP_FILEPATH" ) + File.separator + filePDF.getName() + "_" + renderedImageList.indexOf( i ) + "_" + sdf.format( new Date() ) + ".png" ); 
				ImageIO.write((RenderedImage) i, "png", f);
				fileImageList.add( f );
			}
		} catch (Exception e) {
			//TODO log
			throw e;
		}

		return fileImageList;
	}
	
}