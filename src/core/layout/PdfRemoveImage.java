package core.layout;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFOperator;

public class PdfRemoveImage {
	
	public static void removeImageFromPdf(String pdfFile, String pdfFileOut) throws Exception {

		PDDocument doc = PDDocument.load(pdfFile);

		List pages = doc.getDocumentCatalog().getAllPages();
		for( int i=0; i<pages.size(); i++ ) {
			PDPage page = (PDPage)pages.get( i );

			// added
			COSDictionary newDictionary = new COSDictionary(page.getCOSDictionary());

			PDFStreamParser parser = new PDFStreamParser(page.getContents());
			parser.parse();
			List tokens = parser.getTokens();
			List newTokens = new ArrayList();
			for(int j=0; j<tokens.size(); j++) {
				Object token = tokens.get( j );

				if( token instanceof PDFOperator ) {
					PDFOperator op = (PDFOperator)token;
					if( op.getOperation().equals( "Do") ) {
						//remove the one argument to this operator
						// added
						COSName name = (COSName)newTokens.remove( newTokens.size() -1 );
						// added
						deleteObject(newDictionary, name);
						System.out.println( name.getName() );
						continue;
					}
				}
				newTokens.add( token );
			}
			PDStream newContents = new PDStream( doc );
			ContentStreamWriter writer = new ContentStreamWriter( newContents.createOutputStream() );
			writer.writeTokens( newTokens );
			newContents.addCompression();

			page.setContents( newContents );

			// added
			PDResources newResources = new PDResources(newDictionary);
			page.setResources(newResources);
		}

		doc.save(pdfFileOut);
		doc.close();
	}

	// added
	private static boolean deleteObject(COSDictionary d, COSName name) {
		for(COSName key : d.keySet()) {
			if( name.equals(key) ) {
				d.removeItem(key);
				return true;
			}
			COSBase object = d.getDictionaryObject(key); 
			if(object instanceof COSDictionary) {
				if( deleteObject((COSDictionary)object, name) ) {
					return true;
				}
			}
		}
		return false;
	}
	
}