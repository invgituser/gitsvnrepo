package com.invessence.data;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.model.*;

import com.invessence.converter.JavaUtil;
import org.primefaces.model.chart.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/21/15
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class SamplerData
{
   private JavaUtil jutil = new JavaUtil();
   private String portfolioName = "Enter Portfolio Name";
   private String goal = "Retirement";
   private String investmentType = "Type";
   private String salesPerson = "Prashant Mehta" ;
   private String phoneNo = "156-890-1240";
   private Integer riskMeter = 5;
   private Integer investment = 100000;
   private Integer age = 30;
   private Integer horizon = 35;
   private Integer choice1, choice2, choice3, choice4, choice5;

   private List<SelectItem> investmentChoice;
   private List<Car> cars;
   private Car selectedCar;

   private List<Portfolio> portfolio;
   private Portfolio selectedPortfolio;

   private String text1, text2, text3, text4, text5, text6;
   private Integer num1, num2, num3, num4, num5, num6;

   @PostConstruct
   public void init() {
      cars = new ArrayList<Car>();

      cars.add(new Car("1", "Risk", 2000, "Black", 20784,false));
      cars.add(new Car("2", "Financial", 2003, "Orange", 43784,false));
      cars.add(new Car("4", "Products", 2012, "Red", 37784,false));
      cars.add(new Car("5", "News", 2001, "Yellow", 25784,false));
      cars.add(new Car("6", "Other", 2014, "Blue", 32878,true));

      portfolio = new ArrayList();
      portfolio.add(new Portfolio("SPY", "SPDR S&P 500", "Domestic", "Large Cap", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("IWM", "iShares Russell 2000", "Domestic", "Small Cap", 118.17, 0.12, 12000.00));
      portfolio.add(new Portfolio("VWO", "Vanguard FTSE Emerging Markets", "International", "Emerging Market", 40.38, 0.12, 12000.00));
      portfolio.add(new Portfolio("VEU", "Vanguard FTSE All-World ex-US", "International", "Large Cap", 48.16, 0.12, 12000.00));
      portfolio.add(new Portfolio("EMLC", "Market Vector EM Local Currency\tBond", "Bond", "Emerging Market", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("JNK", "SPDR Barclays High Yield Bond ETF", "Bond", "High Yield", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("VCLT", "Vanguard Long Term Corporate", "Bond", "Investment Grade", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("LTPZ", "BofA ML 15+ Year US Inflation Linked Treasury", "Bond", "TIPS", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("TIPZ", "BofA ML US Inflation Linked Treasury", "Bond", "TIPS", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("SHM", "SPDR Nuveen Barclays ST Muni Bd", "Bond", "TIPS", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("MLN", "Market Vectors Long Municipal\tBond", "Bond", "Municipal", 206.47, 0.12, 12000.00));

      portfolio.add(new Portfolio("ITM", "Market Vectors Intermediate Muni\tBond", "Bond", "Large Cap", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("IEF", "iShares 7-10 Year Treasury Bond", "Bond", "Large Cap", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("BIL", "Barclays 1-3 Month US Treasury", "Bond", "Large Cap", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("TLT", "iShares 20+ Year Treasury Bond", "Bond", "Large Cap", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("IAU", "Gold Trust", "Commodity", "Precious Metal", 206.47, 0.12, 12000.00));
      portfolio.add(new Portfolio("Cash", "Money Markets", "Cash", "Cash", 206.47, 0.12, 12000.00));

      SelectItemGroup g1 = new SelectItemGroup("Retirement");
      g1.setSelectItems(new SelectItem[] {
         new SelectItem("IRA", "IRA"),
         new SelectItem("SEP", "SEP"),
         new SelectItem("ROTH", "ROTH")
      });

      SelectItemGroup g2 = new SelectItemGroup("Savings");
      g2.setSelectItems(new SelectItem[] {
         new SelectItem("Short Term", "Short-Term"),
         new SelectItem("Long Term", "Long-Term")
      });

      investmentChoice = new ArrayList<SelectItem>();
      investmentChoice.add(g1);
      investmentChoice.add(g2);

   }

   public List<SelectItem> getInvestmentChoice()
   {
      return investmentChoice;
   }

   public void setInvestmentChoice(List<SelectItem> investmentChoice)
   {
      this.investmentChoice = investmentChoice;
   }

   public List<Car> getCars() {
      return cars;
   }

   public void setCars(List<Car> cars) {
      this.cars = cars;
   }

   public Car getSelectedCar()
   {
      return selectedCar;
   }

   public void setSelectedCar(Car selectedCar)
   {
      this.selectedCar = selectedCar;
   }

   public List<Portfolio> getPortfolio()
   {
      return portfolio;
   }

   public Portfolio getSelectedPortfolio()
   {
      return selectedPortfolio;
   }

   public void setSelectedPortfolio(Portfolio selectedPortfolio)
   {
      this.selectedPortfolio = selectedPortfolio;
   }

   public String getPortfolioName()
   {
      return portfolioName;
   }

   public void setPortfolioName(String portfolioName)
   {
      this.portfolioName = portfolioName;
   }

   public String getSalesPerson()
   {
      return salesPerson;
   }

   public String getGoal()
   {
      return goal;
   }

   public void setGoal(String goal)
   {
      this.goal = goal;
   }

   public String getInvestmentType()
   {
      return investmentType;
   }

   public void setInvestmentType(String investmentType)
   {
      this.investmentType = investmentType;
   }

   public void setSalesPerson(String salesPerson)
   {
      this.salesPerson = salesPerson;
   }

   public String getPhoneNo()
   {
      return phoneNo;
   }

   public void setPhoneNo(String phoneNo)
   {
      this.phoneNo = phoneNo;
   }

   public Integer getRiskMeter()
   {
      return riskMeter;
   }

   public void setRiskMeter(Integer riskMeter)
   {
      this.riskMeter = riskMeter;
   }

   public String getDisplayInvestment() {
      String strformat = jutil.displayFormat(investment,"###,###,##0");
      if (strformat.length() > 0)
         return "$ " + strformat;
      else
         return "$ 0";
   }
   public Integer getInvestment()
   {
      return investment;
   }

   public void setInvestment(Integer investment)
   {
      this.investment = investment;
   }

   public Integer getAge()
   {
      return age;
   }

   public String getDisplayAge() {
      return age.toString();
   }
   public void setAge(Integer age)
   {
      this.age = age;
   }

   public Integer getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Integer horizon)
   {
      this.horizon = horizon;
   }

   public DonutChartModel getDonutModel1()
   {
      DonutChartModel model = new DonutChartModel();

      Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
      circle1.put("Domestic", 320);
      circle1.put("International", 210);
      circle1.put("Bond", 470);
      circle1.put("Commodity", 50);
      circle1.put("Cash", 50);
      model.addCircle(circle1);

      Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
      circle2.put("Large Cap", 180);
      circle2.put("Mid Cap", 70);
      circle2.put("Small Cap", 60);
      circle2.put("Emerging", 80);
      circle2.put("Internation LC", 120);
      circle2.put("Bond Emerging", 60);
      circle2.put("High Yield", 60);
      circle2.put("Preferred", 60);
      circle2.put("Investment Grade", 60);
      circle2.put("Treasury", 240);
      circle2.put("Gold", 50);
      circle2.put("Treasury", 50);
      model.addCircle(circle2);
      return model;
   }


   public MeterGaugeChartModel getMeterGaugeModel()
   {
      List<Number> intervals = new ArrayList<Number>(){{
         add(2.5);
         add(5.0);
         add(7.5);
         add(10.0);
      }};

      MeterGaugeChartModel meterGaugeModel2 =  new MeterGaugeChartModel(50, intervals);
/*
      meterGaugeModel2.setTitle("Risk Meter");
      meterGaugeModel2.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
      meterGaugeModel2.setGaugeLabel("Risk");
      meterGaugeModel2.setGaugeLabelPosition("bottom");
      meterGaugeModel2.setShowTickLabels(true);
      meterGaugeModel2.setLabelHeightAdjust(110);
      meterGaugeModel2.setIntervalOuterRadius(100);
*/
      return meterGaugeModel2;
   }

/*  public LineChartModel getAnimatedModel1() {

      LineChartModel model = new LineChartModel();

      LineChartSeries series1 = new LineChartSeries();
      series1.setLabel("Performance");

      series1.set(1, 2);
      series1.set(2, 1);
      series1.set(3, 3);
      series1.set(4, 6);
      series1.set(5, 8);

      LineChartSeries series2 = new LineChartSeries();
      series2.setLabel("Index Fund");

      series2.set(1, 6);
      series2.set(2, 3);
      series2.set(3, 2);
      series2.set(4, 7);
      series2.set(5, 9);

      model.addSeries(series1);
      model.addSeries(series2);

      model.setTitle("Historical Chart");
      model.setAnimate(true);
      model.setLegendPosition("se");
      Axis yAxis = model.getAxis(AxisType.Y);
      yAxis.setMin(0);
      yAxis.setMax(10);

      return model;

   }
   */


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

   public String getText5()
   {
      return text5;
   }

   public void setText5(String text5)
   {
      this.text5 = text5;
   }

   public String getText6()
   {
      return text6;
   }

   public void setText6(String text6)
   {
      this.text6 = text6;
   }

   public Integer getNum1()
   {
      return num1;
   }

   public void setNum1(Integer num1)
   {
      this.num1 = num1;
   }

   public Integer getNum2()
   {
      return num2;
   }

   public void setNum2(Integer num2)
   {
      this.num2 = num2;
   }

   public Integer getNum3()
   {
      return num3;
   }

   public void setNum3(Integer num3)
   {
      this.num3 = num3;
   }

   public Integer getNum4()
   {
      return num4;
   }

   public void setNum4(Integer num4)
   {
      this.num4 = num4;
   }

   public Integer getNum5()
   {
      return num5;
   }

   public void setNum5(Integer num5)
   {
      this.num5 = num5;
   }

   public Integer getNum6()
   {
      return num6;
   }

   public void setNum6(Integer num6)
   {
      this.num6 = num6;
   }

   public Integer getChoice1()
   {
      return choice1;
   }

   public void setChoice1(Integer choice1)
   {
      this.choice1 = choice1;
   }

   public Integer getChoice2()
   {
      return choice2;
   }

   public void setChoice2(Integer choice2)
   {
      this.choice2 = choice2;
   }

   public Integer getChoice3()
   {
      return choice3;
   }

   public void setChoice3(Integer choice3)
   {
      this.choice3 = choice3;
   }

   public Integer getChoice4()
   {
      return choice4;
   }

   public void setChoice4(Integer choice4)
   {
      this.choice4 = choice4;
   }

   public Integer getChoice5()
   {
      return choice5;
   }

   public void setChoice5(Integer choice5)
   {
      this.choice5 = choice5;
   }
}
