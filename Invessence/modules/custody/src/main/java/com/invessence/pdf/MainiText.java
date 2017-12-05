package com.invessence.pdf;

import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


/**
 * Created by abhangp on 3/11/2016.
 */
public class MainiText
{
   public static void main(String[] args) throws Exception {
      PdfReader reader = new PdfReader("D:\\Project\\Abhang\\UOB\\Trade Account Opeoning PDF files\\UOBKH Individual & Joint Application for ROBO Advisor Account _New Client_ 27 Oct 2017.pdf");
      PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("HelloWorldStamper2.pdf"));
      Image img = Image.getInstance("watermark.png");
      img.setAbsolutePosition(200, 400);
      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
      PdfContentByte under, over;
      int total = reader.getNumberOfPages() + 1;
      for (int i = 1; i < total; i++) {
         stamper.setRotateContents(false);
         under = stamper.getUnderContent(i);
         under.addImage(img);
         over = stamper.getOverContent(i);
         over.beginText();
         over.setFontAndSize(bf, 12);
         over.setTextMatrix(80, 635);
         over.showText("page " + i);
         over.endText();
//         ColumnText ct = new ColumnText(over);
//// this are the coordinates where you want to add text
//// if the text does not fit inside it will be cropped
//         ct.setSimpleColumn(50,500,500,50);
//         Phrase p=new Phrase();
//        // p.set(1,"Abhang");
//         ct.setText(new Phrase());
//         //ct.setText(new Phrase(str, titulo1));
//         ct.go();
      }
      stamper.close();
   }
}
