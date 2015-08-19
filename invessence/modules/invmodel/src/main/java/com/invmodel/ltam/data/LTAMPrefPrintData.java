package com.invmodel.ltam.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/11/15
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMPrefPrintData
{
   String indexname;
   String color;
   String header1,header2,header3,header4,header5,header6,header7,header8,header9,header10,header11,header12;
   Double value1,value2,value3,value4,value5,value6,value7,value8,value9,value10,value11,value12;

   public String getIndexname()
   {
      return indexname;
   }

   public String getColor()
   {
      return color;
   }

   public String getHeader1()
   {
      return header1;
   }

   public String getHeader2()
   {
      return header2;
   }

   public String getHeader3()
   {
      return header3;
   }

   public String getHeader4()
   {
      return header4;
   }

   public String getHeader5()
   {
      return header5;
   }

   public String getHeader6()
   {
      return header6;
   }

   public String getHeader7()
   {
      return header7;
   }

   public String getHeader8()
   {
      return header8;
   }

   public String getHeader9()
   {
      return header9;
   }

   public String getHeader10()
   {
      return header10;
   }

   public String getHeader11()
   {
      return header11;
   }

   public String getHeader12()
   {
      return header12;
   }

   public Double getValue1()
   {
      return value1;
   }

   public Double getValue2()
   {
      return value2;
   }

   public Double getValue3()
   {
      return value3;
   }

   public Double getValue4()
   {
      return value4;
   }

   public Double getValue5()
   {
      return value5;
   }

   public Double getValue6()
   {
      return value6;
   }

   public Double getValue7()
   {
      return value7;
   }

   public Double getValue8()
   {
      return value8;
   }

   public Double getValue9()
   {
      return value9;
   }

   public Double getValue10()
   {
      return value10;
   }

   public Double getValue11()
   {
      return value11;
   }

   public Double getValue12()
   {
      return value12;
   }

   public void addData(String index, String color) {
      indexname = index;
      this.color = color;
   }

   public void addData(String header, Double value, Integer position) {
      try {
           switch (position) {
              case 0:
                 break;
              case 1:
                 header1 = header;
                 value1 =  value;
                 break;
              case 2:
                 header2 = header;
                 value2 =  value;
                 break;
              case 3:
                 header3 = header;
                 value3 =  value;
                 break;
              case 4:
                 header4 = header;
                 value4 =  value;
                 break;
              case 5:
                 header5 = header;
                 value5 =  value;
                 break;
              case 6:
                 header6 = header;
                 value6 =  value;
                 break;
              case 7:
                 header7 = header;
                 value7 =  value;
                 break;
              case 8:
                 header8 = header;
                 value8 =  value;
                 break;
              case 9:
                 header9 = header;
                 value9 =  value;
                 break;
              case 10:
                 header10 = header;
                 value10 =  value;
                 break;
              case 11:
                 header11 = header;
                 value11 =  value;
                 break;
              case 12:
                 header12 = header;
                 value12 =  value;
                 break;
              default:
                 break;
           }
      }
      catch (Exception ex) {

      }
   }
}
