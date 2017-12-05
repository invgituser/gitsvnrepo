package com.invessence.docServices.iText.pdf;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

/**
 * Created by abhangp on 11/17/2017.
 */
public class GetSigPos {
   public static void main(String[] args) throws IOException {
      String pdfFile = "HelloWorldStamper2.pdf";
      PdfReader reader = new PdfReader(pdfFile);

      AcroFields fields = reader.getAcroFields();

      for(String signame : fields.getBlankSignatureNames()) {
         List<AcroFields.FieldPosition> positions = fields.getFieldPositions(signame);
         Rectangle rect = positions.get(0).position; // In points:
         float left   = rect.getLeft();
         float bTop   = rect.getTop();
         float width  = rect.getWidth();
         float height = rect.getHeight();

         int page = positions.get(0).page;
         Rectangle pageSize = reader.getPageSize(page);
         float pageHeight = pageSize.getTop();
         float top = pageHeight - bTop;

         System.out.print(signame + "::" + page + "::" + left + "::" + top + "::" + width + "::" + height + "\n");
      }
   }
}
