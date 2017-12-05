package com.invessence.docServices.iText.pdf;

/**
 * Created by abhangp on 11/7/2017.
 */

import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class AddFieldAndKids {

   public static final String SRC = "HelloWorldStamper2.pdf";
   public static final String DEST = "hello_with_field_kids.pdf";

   public static void main(String[] args) throws IOException, DocumentException {
      File file = new File(DEST);
//      file.getParentFile().mkdirs();
      new AddFieldAndKids().manipulatePdf(SRC, DEST);
   }

   public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
      PdfReader reader = new PdfReader(src);
      PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
      PdfWriter writer = stamper.getWriter();
      PdfFormField personal = PdfFormField.createEmpty(writer);
      personal.setFieldName("personal");
      TextField name = new TextField(writer, new Rectangle(36, 760, 144, 790), "name");
      PdfFormField personal_name = name.getTextField();
      personal.addKid(personal_name);
      TextField password = new TextField(writer, new Rectangle(150, 760, 450, 790), "password");
      PdfFormField personal_password = password.getTextField();
      personal.addKid(personal_password);
      stamper.addAnnotation(personal, 1);
      stamper.close();
      reader.close();
   }
}
