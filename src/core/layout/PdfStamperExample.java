package core.layout;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfStamperExample {

	public static void main(String[] args) {
		try {
			PdfReader pdfReader = new PdfReader("C:\\Users\\a.story\\Desktop\\ocr_test\\test\\source_pdf.pdf");

			PdfStamper pdfStamper = new PdfStamper(pdfReader,
					new FileOutputStream("C:\\Users\\a.story\\Desktop\\ocr_test\\test\\source_pdf_stamp.pdf"));

			Image image = Image.getInstance("C:\\Users\\a.story\\Desktop\\ocr_test\\test\\replacer.png");

			for(int i=1; i<= pdfReader.getNumberOfPages(); i++){
//				PdfContentByte content = pdfStamper.getUnderContent(i);
//                image.setAbsolutePosition(100f, 150f);
//                content.addImage(image);
				
				PdfContentByte content = pdfStamper.getUnderContent(i);
				pdfStamper.getImportedPage(pdfReader, i).getYTLM();
				content = pdfStamper.getOverContent(i);
				image.setAbsolutePosition(40f, 725f);
//				image.scaleAbsolute(200,160);   
				content.addImage(image);
				
				//Text over the existing page
//                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
//                        BaseFont.WINANSI, BaseFont.EMBEDDED);
//                content.beginText();
//                content.setFontAndSize(bf, 18);
//                content.showTextAligned(PdfContentByte.ALIGN_LEFT,"Page No: " + i,430,15,0);
//                content.endText();
			}

			pdfStamper.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}