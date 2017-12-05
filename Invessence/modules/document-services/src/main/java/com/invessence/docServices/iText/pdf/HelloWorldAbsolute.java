package com.invessence.docServices.iText.pdf;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * Created by abhangp on 11/7/2017.
 */
public class HelloWorldAbsolute {

   public static void main(String[] args) throws Exception {
      Document.compress = false;
      Document document = new Document(PageSize.A4);
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
         "UOBKH Individual & Joint Application for ROBO Advisor Account _New Client_ 27 Oct 2017.pdf"));
      document.open();
      PdfContentByte cb = writer.getDirectContent();
      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252,
                                        BaseFont.NOT_EMBEDDED);
      cb.saveState();
      cb.beginText();
      cb.moveText(36, 806);
      cb.moveText(0, -18);
      cb.setFontAndSize(bf, 12);
      cb.showText("Hello World");
      cb.endText();
      cb.restoreState();
      document.close();
   }
}
