package com.invessence.price.FIS.bean;

import javax.xml.bind.annotation.*;

/**
 * Created by sagar on 4/6/2017.
 */

//@XmlRootElement
@XmlRootElement( namespace = "http://91.212.43.32/XML/", name = "INFOTECXML")

public class DailyData
{
   private DailyRates dailyRate;

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "QUOTE" )

   public DailyRates getDailyRate()
   {
      return dailyRate;
   }

   public void setDailyRate(DailyRates dailyRate)
   {
      this.dailyRate = dailyRate;
   }

   @Override
   public String toString()
   {
      return "DailyData{" +
         "dailyRate=" + dailyRate +
         '}';
   }
}