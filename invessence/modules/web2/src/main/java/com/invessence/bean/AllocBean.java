package com.invessence.bean;

import java.io.Serializable;
import javax.faces.bean.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.invessence.data.*;
import org.primefaces.model.chart.PieChartModel;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/26/14
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "allocBean")
@SessionScoped
public class AllocBean implements Serializable {
   private List<Car> cars;
   private String hide="true";
   private PieChartModel pieModel;

   @ManagedProperty("#{carService}")
   private CarService service;

   @PostConstruct
   public void init() {
      cars = service.createCars(10);
   }

   public List<Car> getCars() {
      return cars;
   }

   public void setService(CarService service) {
      this.service = service;
   }

   public String getHide()
   {
      return hide;
   }

   public void setHide(String hide)
   {
      this.hide = hide;
   }

   public PieChartModel getPieModel()
   {
      pieModel = new PieChartModel();
      pieModel.set("One", 38.68);
      pieModel.set("Two", 30.11);
      pieModel.set("One", 28.08);
      pieModel.set("One", 2.68);
      pieModel.set("Five", 0.33);
      return pieModel;
   }

   public double getSumPrice() {
      double sum = 0;

      for(Car s : cars) {
         sum += s.getPrice();
      }

      return sum;
   }



}