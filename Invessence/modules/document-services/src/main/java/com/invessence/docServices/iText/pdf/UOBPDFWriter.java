package com.invessence.docServices.iText.pdf;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import com.invessence.custody.uob.UOBDataMaster;
import com.invessence.service.bean.documentServices.iText.PDFFileRules;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by abhangp on 22/11/2017.
 */

@Component
public class UOBPDFWriter
{
   @Autowired
   PDFWriter pdfWriter;
   public void  writPDF(Map<String, List<PDFFileRules>> pdfRules, Object dataObject)
   {
// List of Files Object Parsing..
      System.out.println("pdfRules = [" + pdfRules + "], dataObject = [" + dataObject + "]");
      try
      {
         UOBDataMaster uobDataMaster = (UOBDataMaster) dataObject;
         PdfReader reader = new PdfReader("D:\\Project\\Abhang\\work\\trunc\\Invessence\\UOBKH Individual & Joint Application for ROBO Advisor Account _New Client_ 27 Oct 2017.pdf");

         PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(uobDataMaster.getAccountDetails().getAcctnum()+".pdf"));
         Iterator<Map.Entry<String,List<PDFFileRules>>> entries6 = pdfRules.entrySet().iterator();
         while (entries6.hasNext())
         {

            Map.Entry<String, List<PDFFileRules>>entry6=entries6.next();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(entry6.getKey());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

            if (entry6.getKey().equals("Client"))
            {
               Iterator<PDFFileRules> entries7 = entry6.getValue().iterator();
               pdfWriter.writPDF(stamper, entry6.getValue(), new Object[]{uobDataMaster.getAccountDetails(), uobDataMaster.getIndividualOwnersDetails()});
            }
            else  if (entry6.getKey().equals("Joint")){
               Iterator<PDFFileRules> entries7 = entry6.getValue().iterator();
               pdfWriter.writPDF(stamper, entry6.getValue(), new Object[]{uobDataMaster.getAccountDetails(), uobDataMaster.getJointOwnersDetails()});
            }
         }
         stamper.close();

       }catch (Exception e){
         e.printStackTrace();
      }
   }

   private static HashMap<String, Object> getMemberFields(Object obj, HashMap<String, Object> fieldValues) throws IllegalAccessException,
      NoSuchFieldException
   {
      if(fieldValues==null){fieldValues = new HashMap<String, Object>();}
      Class<?> objClass = obj.getClass();

      Field[] fields = objClass.getDeclaredFields();
      for (Field field : fields)
      {
         field.setAccessible(true);
         if (field.getType().getName().contains("com.invessence.custody.uob.data"))
         {
            if (field.get(obj) != null)
            {
               getMemberFields(field.get(obj), fieldValues);
            }
         }
         else if (field.getType().getName().contains("java.lang"))
         {

            fieldValues.put(field.getName(), field.get(obj));
         }
         if (field.getType().getName().contains("java.util.List"))
         {
            System.out.println("Its :List");
         }
      }
      return fieldValues;
   }

   boolean finished = false;
   private Object abhang(Object obj, String dbColumn) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException
   {
      Object returnVal=null;
      boolean isFound=false;
      Class<?> objClass = obj.getClass();
      System.out.println("obj: " + obj.getClass());


      Field[] fields = objClass.getDeclaredFields();
      List<Field> fLst = Arrays.asList(fields);
      for (Field field : fields)
      {
         field.setAccessible(true);
//         System.out.println("Field: " + field.getName() + " value: " + field.get(obj));

         Field[] innerFields = {};
         if (field.getType().getName().contains("java.lang"))
         {
//            Method meth = objClass.getMethod(, null);
            innerFields = field.getType().getDeclaredFields();
            System.out.println("Its Normal field :" + field.getName());
            if (field.getName().equals(dbColumn))
            {
               returnVal= field.get(obj);
               isFound=true;
               finished=true;

            }
         }
         else if (field.getType().getName().contains("com.invessence.custody.uob.data"))
         {
            innerFields = field.getType().getDeclaredFields();
            System.out.println("Its Object field :" + field.getName());
            String fieldName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            Method method1 = objClass.getMethod(fieldName, null);

            Object returnValue = null;
            try
            {
               returnValue = method1.invoke(obj, null);
               if(!finished){

                  abhang(returnValue, dbColumn);

               }else{
                  break;
               }

            }
            catch (InvocationTargetException e)
            {
               System.out.println(e.getMessage());
               //e.printStackTrace();
            }
         }
         else
         {
            System.out.println("");
         }
         if(isFound){
            break;
         }

      }
      return returnVal;
   }



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
