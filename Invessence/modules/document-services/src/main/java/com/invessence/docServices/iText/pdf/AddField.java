package com.invessence.docServices.iText.pdf;

import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class AddField {

   public static final String SRC = "HelloWorldStamper2.pdf";
   public static final String DEST = "field_added.pdf";


   public static void main(String[] args) throws DocumentException, IOException {
      File file = new File(DEST);
//      file.getParentFile().mkdirs();
      new AddField().manipulatePdf(SRC, DEST);
   }

   public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
      PdfReader reader = new PdfReader(src);
      PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
      PushbuttonField button = new PushbuttonField(
         stamper.getWriter(), new Rectangle(36, 700, 72, 730), "post");
      button.setText("POST");
      button.setBackgroundColor(new GrayColor(0.7f));
      button.setVisibility(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
      PdfFormField submit = button.getField();
      submit.setAction(PdfAction.createSubmitForm(
         "http://itextpdf.com:8180/book/request", null,
         PdfAction.SUBMIT_HTML_FORMAT | PdfAction.SUBMIT_COORDINATES));
      stamper.addAnnotation(submit, 1);
      stamper.close();
   }
}