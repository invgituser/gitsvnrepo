package com.invessence.bean;

import java.util.*;
import javax.faces.bean.*;

import com.invessence.constant.Const;
import com.invessence.data.MsgData;
import com.invessence.util.EmailMessage;
import org.primefaces.model.chart.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/20/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "testBean2")
@SessionScoped
public class TestBean2
{

   private String text1, text2, text3, text4;
   private int imageSelected;
   private Integer slider1 = 30, slider2 = 100000, slider3 = 10;

   public String getText1()
   {
      return text1;
   }

   public void setText1(String text1)
   {
      this.text1 = text1;
   }

   public String getText2()
   {
      return text2;
   }

   public void setText2(String text2)
   {
      this.text2 = text2;
   }

   public String getText3()
   {
      return text3;
   }

   public void setText3(String text3)
   {
      this.text3 = text3;
   }

   public String getText4()
   {
      return text4;
   }

   public void setText4(String text4)
   {
      this.text4 = text4;
   }

   public int getImageSelected()
   {
      return imageSelected;
   }

   public Integer getSlider1()
   {
      return slider1;
   }

   public void setSlider1(Integer slider1)
   {
      this.slider1 = slider1;
   }

   public Integer getSlider2()
   {
      return slider2;
   }

   public void setSlider2(Integer slider2)
   {
      this.slider2 = slider2;
   }

   public Integer getSlider3()
   {
      return slider3;
   }

   public void setSlider3(Integer slider3)
   {
      this.slider3 = slider3;
   }

   public void actionGoal(Integer which) {
      if (which == null)
         which = 0;

      imageSelected = which;
      switch (imageSelected) {
         case 0:
            text1 = "Retirement";
            break;
         case 1:
            text1 = "Growth";
            break;
         case 2:
            text1 = "Income";
            break;
         default:
            text1 = "Retirement";
      }

   }
   public MeterGaugeChartModel getMeterGaugeModels() {
      List<Number> intervals = new ArrayList<Number>(){{
         add(1);
         add(2);
         add(3);
         add(4);
      }};

      MeterGaugeChartModel meterGaugeModel =  new MeterGaugeChartModel(2, intervals);
      return meterGaugeModel;
   }

   public DonutChartModel getDonutModels() {
      DonutChartModel donutModel1 = initDonutModel();

      return donutModel1;
   }

   private DonutChartModel initDonutModel() {
      DonutChartModel model = new DonutChartModel();

      Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
      circle1.put("Brand 1", 150);
      circle1.put("Brand 2", 400);
      circle1.put("Brand 3", 200);
      circle1.put("Brand 4", 10);
      model.addCircle(circle1);

      Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
      circle2.put("Brand 1", 540);
      circle2.put("Brand 2", 125);
      circle2.put("Brand 3", 702);
      circle2.put("Brand 4", 421);
      model.addCircle(circle2);

      Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
      circle3.put("Brand 1", 40);
      circle3.put("Brand 2", 325);
      circle3.put("Brand 3", 402);
      circle3.put("Brand 4", 421);
      model.addCircle(circle3);

      return model;
   }
}
