package com.invessence.report.testing;

import java.io.*;

import com.google.common.io.ByteStreams;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/20/14
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */


public class TestPDF
{
   public static void main(String[] args)
   {
      String outputLocation;
      try {
         if (args.length > 0) {
            outputLocation = args[0];
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputLocation));
            document.open();

            document.setMargins(1.0f, 1.0f, 1.0f, 1.0f);
            writeLogo(document,"InvessenceLogo.jpg",92,18);
            writeLogo(document,"invessence_logo_RGB_92x32.jpg",null,null);
            writeLogo(document,"invessence_logo_RGB_94x32.jpg",null,null);
            writeLogo(document,"invessence_logo_RGB_92x32.png",null,null);
            writeLogo(document,"invessence_logo_RGB_94x32.png",null,null);
            writeLogo(document,"InvessenceLogo.jpg",92,16);
            document.close();
         }

      }
      catch (Exception ex) {

      }
   }

   private static void writeLogo(Document document, String logoFile, Integer width, Integer height) throws IOException, DocumentException
   {
      PdfPTable tbl = new PdfPTable(2);
      Font font = new Font(FontFamily.HELVETICA, 8.0f, Font.ITALIC, BaseColor.BLACK);

      GetImage image = new GetImage(logoFile);
      Image logo = image.getImage();

      String txt = logoFile;
      if (width != null) {
         txt = txt + "[w x h] (" + width.toString() + "x" + height.toString() +")";
         logo.scaleAbsolute(width, height);
      }
      PdfPCell leftCell = new PdfPCell(new Paragraph(txt, font));
      leftCell.disableBorderSide(Rectangle.BOX);
      leftCell.setVerticalAlignment(Element.ALIGN_CENTER);

      logo.setAlignment(Image.LEFT);

      PdfPCell logoCell = new PdfPCell(logo);
      logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
      logoCell.disableBorderSide(Rectangle.BOX);

      tbl.addCell(leftCell);
      tbl.addCell(logoCell);
      document.add(tbl);
   }



}
