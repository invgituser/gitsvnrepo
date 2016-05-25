package com.invessence.web.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.event.ValueChangeEvent;

import com.invessence.web.data.consumer.*;
import org.primefaces.model.chart.MeterGaugeChartModel;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/26/14
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "rqBean")
@SessionScoped
public class RiskQuestionBean implements Serializable {
   private RiskStaticData rsData = new RiskStaticData();
   private List<RiskQuestions> riskQuestions = rsData.getListOfQuestion();
   private Boolean formDirty;
   private Integer[] selected = new Integer[10];

   public List<RiskQuestions> getRiskQuestions()
   {
      return riskQuestions;
   }

   public void setRiskQuestions(List<RiskQuestions> riskQuestions)
   {
      this.riskQuestions = riskQuestions;
   }

   public Integer[] getSelected()
   {
      return selected;
   }

   public void setSelected(Integer[] selected)
   {
      this.selected = selected;
   }

   public void setSelected(String selected)
   {
      System.out.println(selected);
   }

   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue = null;
      String newValue = null;
      try
      {
         if (!this.formDirty)
         {
            if (event.getNewValue() == null)
            {
               return;
            }

            oldValue = "";
            if (event.getOldValue() != null)
            {
               oldValue = event.getOldValue().toString();
            }

            try
            {
               newValue = event.getNewValue().toString();
               Integer decimalPosition = newValue.indexOf(".0");
               if (decimalPosition > 0)
               {
                  newValue = newValue.substring(0, decimalPosition);
               }
            }
            catch (Exception ex)
            {
               newValue = event.getNewValue().toString();
            }
            // This is to ignore all already selected items.
            if (!(oldValue.equals(newValue)))
            {
               this.formDirty = true;
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   private MeterGaugeChartModel meterGaugeModel;

   public MeterGaugeChartModel getMeterGaugeModel()
   {
      return meterGaugeModel;
   }

   public void setMeterGaugeModel(MeterGaugeChartModel meterGaugeModel)
   {
      this.meterGaugeModel = meterGaugeModel;
   }

   @PostConstruct
   public void init() {
      createMeterGaugeModels();
   }

   private MeterGaugeChartModel initMeterGaugeModel() {
      List<Number> intervals = new ArrayList<Number>(){{
         add(2);
         add(5);
         add(7);
         add(10);
      }};

      return new MeterGaugeChartModel(140, intervals);
   }

   private void createMeterGaugeModels() {

      meterGaugeModel = initMeterGaugeModel();
   }

}
