package com.invessence.report.testing;

import java.io.InputStream;

import com.google.common.io.ByteStreams;
import com.itextpdf.text.Image;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/20/14
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetImage
{
   String imagename;

   public GetImage(String imagename)
   {
      this.imagename = imagename;
   }

   public Image getImage() {
      Image logo = null;
      try {
         if (imagename != null) {
            InputStream resourceAsStream = getClass().getResourceAsStream("/" + imagename);
            byte[] bytes = ByteStreams.toByteArray(resourceAsStream);
            logo = Image.getInstance(bytes);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return logo;
   }
}
