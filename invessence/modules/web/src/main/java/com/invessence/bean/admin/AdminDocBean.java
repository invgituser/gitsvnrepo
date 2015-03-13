package com.invessence.bean.admin;


import com.invessence.bo.*;
import com.invessence.dao.admin.AdminDAO;
import com.invessence.data.*;


import com.invmodel.riskCalculator.RiskIndex;
import org.primefaces.model.StreamedContent;

import org.w3c.dom.Document;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


@SessionScoped
public class AdminDocBean implements Serializable
{
   private static final long serialVersionUID = -8092341641946521649L;
   private GoalsBo goalsBo;

   private Long acctnum;
   private Long IBacctnum;
   private String filter = "Pending";
   private List<InvessenceData> invessenceData;

   private AdminDAO adminDAO;
   public AdminDAO getAdminDAO()
   {
      return adminDAO;
   }

   public void setAdminDAO(AdminDAO adminDAO)
   {
      this.adminDAO = adminDAO;
   }

   public GoalsBo getGoalsBo()
   {
      return goalsBo;
   }

   public void setGoalsBo(GoalsBo goalsBo)
   {
      this.goalsBo = goalsBo;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Long getIBacctnum()
   {
      return IBacctnum;
   }

   public void setIBacctnum(Long IBacctnum)
   {
      this.IBacctnum = IBacctnum;
   }

   public String getFilter()
   {
      return filter;
   }

   public void setFilter(String filter)
   {
      this.filter = filter;
   }

   public List<InvessenceData> getInvessenceData()
   {
      return invessenceData;
   }

   public void setInvessenceData(List<InvessenceData> invessenceData)
   {
      this.invessenceData = invessenceData;
   }

   private StreamedContent file;

   public StreamedContent getFile() {
      return file = generatePDF(getAcctnum());
   }

   public void setFile(StreamedContent file)
   {
      this.file = file;
   }

   @PostConstruct
   public void init() {
      //collectData();
   }

   private void collectData() {
      String myFilter;
      try
      {
         myFilter = getFilter();
         if (myFilter == null || myFilter.isEmpty()) {
            myFilter = "All";
         }
         invessenceData = adminDAO.collectInvData(myFilter);
      }
      catch (Exception ex)
      {
         System.out.println("Error in collecting data on Invessence Page:" + ex.getMessage());
      }

   }

   public void refreshButton() {
      try {
         collectData();
      }
      catch (Exception ex) {
         System.out.println("Refresh failed:" + ex.getMessage());
      }
   }

   public StreamedContent generatePDF(Long acctnum){

      ByteArrayInputStream input = null;
      InputStream xslInput = null;
      InputStream propInput = null;
      String xslFilePath = null;
      StreamedContent file = null;
      ManageGoals  pdfData = new ManageGoals();
      RiskIndex rIndex = new RiskIndex();

      try {
         pdfData.setAcctnum(acctnum);
         pdfData = goalsBo.findGoals(pdfData.getInstance());
         ManageGoals pdfData2 = goalsBo.findRiskTolerance(pdfData.getInstance());
         String []question = new String[rIndex.getNumOfQuestions()];
         String []ans = new String[rIndex.getNumOfQuestions()];
         for (int i=0; i<rIndex.getNumOfQuestions(); i++) {
            question[i] = rIndex.getQuestion(i);
            switch (i) {
               case 0:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice1()));
                  break;
               case 1:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice2()));
                  break;
               case 2:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice3()));
                  break;
               case 3:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice4()));
                  break;
               case 4:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice5()));
                  break;
               case 5:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice6()));
                  break;
               case 6:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice7()));
                  break;
               case 7:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice8()));
                  break;
               case 8:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice9()));
                  break;
               case 9:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice10()));
                  break;
               case 10:
                  ans[i] = rIndex.getChoiceText(i,Integer.parseInt(pdfData2.getSelectedchoice11()));
                  break;
               default:
                  ans[i] = "Undefined Answer.";
                  break;
            }
         }

         String htmlTemplate =
            "<html><title>Exihibit 1</title><body> " +
               "Name: " + pdfData.getFullName() +
               "</br>Goal: " + pdfData.getGoal() +
               "</br>Account Type: " + pdfData.getAccountType() +
               "</br>Age: " + pdfData.getAge() +
               "</br>Time Horizon: " + pdfData.getHorizon() +
               "</br> Initial Investment:  " + formatMoney(pdfData.getInitialInvestment()) +
               "</br>Recurring Investment (per year): " + formatMoney(pdfData.getRecurringInvestment()) +
               "</br>What is your investment goal?: " + pdfData.getStrObjective() +
               "</br>Investment Experience: " + pdfData.getStrExperience() +
               "</br>What do you plan to do with your money at the end of your investment horizon?: " + pdfData.getStrStayInvested() +
               "</br>&nbsp;</br>&nbsp;</br>" +
               "</br>Number of Dependents: " + pdfData.getDependent();
/*
               "</br>&nbsp;</br>&nbsp;</br>";
               "</br><b>Income</b> " +
               "</br>Monthly Income: " + formatMoney(pdfData.getHouseholdwages()) +
               "</br>Other Income (Monthly): " + formatMoney(pdfData.getOtherIncome()) +
               "</br>&nbsp;</br>&nbsp;</br>" +
               "</br><b>Expense</b> " +
               "</br>Mortgage/Rent: " + formatMoney(pdfData.getMortgageLoan()) +
               "</br>Other Expenses: " + formatMoney(pdfData.getOtherExpense()) +
               "</br>&nbsp;</br>&nbsp;</br>" +
               "</br>&nbsp;</br>&nbsp;</br>" +
               "</br><b>Asset</b> " +
               "</br>Money Market: " + formatMoney(pdfData.getMoneymarket()) +
               "</br>Investment: " + formatMoney(pdfData.getInvestment()) +
               "</br>Other Assets: " + formatMoney(pdfData.getOtherSavings()) +
               "</br><b>Debt</b> " +
               "</br>Automobile Loan: " + formatMoney(pdfData.getAutoLoan()) +
               "</br>Mortgage Loan: " + formatMoney(pdfData.getMortgageLoan()) +
               "</br>Other Debt: " + formatMoney(pdfData.getOtherDebt());
*/

         htmlTemplate = htmlTemplate +
            "</br>&nbsp;</br>&nbsp;</br>";
         for (int i=0; i<rIndex.getNumOfQuestions(); i++) {
            htmlTemplate = htmlTemplate +
               "</br>&nbsp;</br>&nbsp;</br>";
            htmlTemplate = htmlTemplate +
               "#" + String.valueOf(i+1) + ": " + question[i];
            htmlTemplate = htmlTemplate +
               "</br>Answer: " + ans[i];
         }
         htmlTemplate = htmlTemplate + "</body></html>";

         Document foDoc = null;

         String pdfLocation = null;
         try {

            input = new ByteArrayInputStream(htmlTemplate.getBytes());
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            xslInput = req.getSession().getServletContext().getResourceAsStream("/resources/html2fo.xsl") ;
            xslFilePath = req.getSession().getServletContext().getRealPath("/resources/html2fo.xsl");

            Properties prop = new Properties();
            propInput = req.getSession().getServletContext().getResourceAsStream("/WEB-INF/classes/invessence.properties");
            prop.load(propInput);
            pdfLocation = prop.getProperty("pdf.location");
            System.out.println("pdfLocation :" + pdfLocation);
         }
         catch(Exception ex){
            System.out.println("XSL File not found: ");
         }

      }
      catch (Exception ex) {

      }
      return file;
   }

   public String formatMoney(Integer money)
   {
      if (money != null) {
         DecimalFormat df = new DecimalFormat("$###,####,###");
         String strValue = df.format(money);
         return strValue;
      }
      return "";
   }

   public String formatMoney(Double money)
   {
      if (money != null) {
         DecimalFormat df = new DecimalFormat("$###,####,###.00");
         String strValue = df.format(money);
         return strValue;
      }
      return "";
   }


}