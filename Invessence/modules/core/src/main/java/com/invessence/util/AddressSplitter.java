package com.invessence.util;

import java.util.regex.*;

/**
 * Created by abhangp on 11/21/2017.
 */
public class AddressSplitter
{
   public static void main(String[] args)
   {
//      String address="304, Prime, Apt., Plot No.215-217, 248-250, Sector 36, Seawoods,  Mala Bara Nahi Watat Ahe, Asach Kahitari Takalay, Nerul, Navi Mumbai-400706";
      String address="asd asd asd asd asd asd asd asad asd asdd asd assd asad asd asd sad sadsa dsa dsa";
      new AddressSplitter().addressSplitter(address, 3, 40);
   }

   public String[] addressSplitter(String address, int splitCnt, int splitLen){
      String addressArr[] = new String[splitCnt];
      String actualStr=address;
      String tempStr, tempStr2;
      for (int i=0; i<splitCnt; i++){
         tempStr2 = actualStr.substring(0, actualStr.length() < splitLen ? actualStr.length() : splitLen);
         if(i<splitCnt-1){

            tempStr = actualStr.substring(0, getIndexOfLastSpecialChar(tempStr2));
            addressArr[i] = tempStr;
            actualStr = actualStr.substring(getIndexOfLastSpecialChar(tempStr), actualStr.length());
            System.out.println("tempStr = " + tempStr);
            System.out.println("actualStr = " + actualStr);
         }else{
            addressArr[i] = tempStr2;
         }
      }

      System.out.println("******************************");
      for (int j=0; j<addressArr.length;j++)
      {
         System.out.println(addressArr[j]);
      }

      return addressArr;
   }

   private int getIndexOfLastSpecialChar(String str){
      Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
      Matcher m = p.matcher(str);
      int lastPosi=str.length();
      while (m.find()) {
         lastPosi=m.start()+1;
      }
      return  lastPosi;
   }
}
