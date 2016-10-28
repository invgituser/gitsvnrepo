package com.invessence.shared.io;

import java.io.*;

public class BufferedFileFactory
{
   public BufferedWriter getBufferedWriter(String fileName) throws IOException
   {
      return new BufferedWriter(new FileWriter(fileName));
   }

   public static void close(BufferedWriter writer)
   {
      if (writer != null)
      {
         try
         {
            writer.flush();
            writer.close();
         }
         catch (IOException e)
         {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
      }
   }
}
