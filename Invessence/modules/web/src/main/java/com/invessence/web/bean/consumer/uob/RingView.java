package com.invessence.web.bean.consumer.uob;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;

/**
 * Created by Prachi on 11/13/2017.
 */

@ManagedBean(name = "ringView")
@SessionScoped
public class RingView implements Serializable
{

   private List<lstobj> cars;
   private lstobj selectedCar;

   public void preRenderView()
   {
      cars = new ArrayList<lstobj>();

      cars.add(new lstobj("1", "Ford", new Long(2000), "Black"));
      cars.add(new lstobj("2", "Audi", new Long(2003), "Orange"));
      cars.add(new lstobj("4", "BMW", new Long(2012), "Red"));
      cars.add(new lstobj("5", "Fiat", new Long(2001), "Yellow"));
      cars.add(new lstobj("6", "Mercedes", new Long(2014), "Blue"));

   }

   public List<lstobj> getCars()
   {
      return cars;
   }

   public void setCars(List<lstobj> cars)
   {
      this.cars = cars;
   }

   public lstobj getSelectedCar()
   {
      return selectedCar;
   }

   public void setSelectedCar(lstobj selectedCar)
   {
      this.selectedCar = selectedCar;
   }
   public class lstobj{

   private String ID;
   private String Brand;
   private Long Year;
   private String Color;

   public lstobj(String ID,String Brand, Long Year, String Color){
      ID=this.ID;
      Brand=this.Brand;
      Year=this.Year;
      Color=this.Color;
   }

   public String getID()
   {
      return ID;
   }

   public void setID(String ID)
   {
      this.ID = ID;
   }

   public String getBrand()
   {
      return Brand;
   }

   public void setBrand(String brand)
   {
      Brand = brand;
   }

   public Long getYear()
   {
      return Year;
   }

   public void setYear(Long year)
   {
      Year = year;
   }

   public String getColor()
   {
      return Color;
   }

   public void setColor(String color)
   {
      Color = color;
   }
}
}
