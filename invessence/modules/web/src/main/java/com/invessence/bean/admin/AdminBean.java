package com.invessence.bean.admin;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.Const;
import com.invessence.data.admin.*;
import com.invessence.io.TradeWriter;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.*;


import com.invessence.bo.GoalsBo;
import com.invessence.dao.AdminDAO;
import com.invessence.data.*;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.data.Portfolio;

import static javax.faces.context.FacesContext.getCurrentInstance;

public class AdminBean extends AdminData implements Serializable
{
   private static final long serialVersionUID = -8092341641946521519L;
   private AdminDAO adminDAO;
   private GoalsBo goalsBo;
   private TradeWriter tw = new TradeWriter();

   private List<AdminTradeClient> filteredTradeList;
   //private Map<String,List<TradeDetails>> tradesDetails = new HashMap<String,List<TradeDetails>>();

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

   @PostConstruct
   public void init()
   {
      try
      {
         if (getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM) == null)
         {
            getCurrentInstance().getExternalContext().redirect("/login.xhtml");
         }
         //collectData();
      }
      catch (Exception ex)
      {

      }
   }

   private void collectData()
   {
      String IBfilter = "All";
      try
      {

         IBfilter = getFilter();

         if (IBfilter == null || IBfilter.isEmpty())
         {
            IBfilter = "All";
         }
         setIbList(adminDAO.collectIBData(IBfilter));
         setInvessenceList(adminDAO.collectInvData(IBfilter));
      }
      catch (Exception ex)
      {
         System.out.println("Error in fetching list for admin match function");
         ex.printStackTrace();
      }
   }

   public void refreshButton()
   {
      try
      {
         collectData();
      }
      catch (Exception ex)
      {
         System.out.println("Refresh failed:" + ex.getMessage());
      }
   }

   public void saveMatch()
   {
      adminDAO.addDelIBPair("A", getAcctnum(), getIBStatus(), getClientAccountID());
      String msg = "IB Account# (" + getClientAccountID() + ") and Invessence Account# (" + getAcctnum() + ") are linked.";
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

   }

   public void saveRisk()
   {
      String msg;
      if (getInvestment() == null || getInvestment() == 0)
      {
         msg = "Investment $ cannot be empty or zero.";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
      }
      else
      {
         if (getRiskIndex() == null)
         {
            msg = "Risk Index cannot be empty.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         }
         else
         {
            //adminDAO.updateProfile(getAcctnum(), getRiskIndex(), getInvestment());
            msg = "Invessence Account# (" + getAcctnum() + ") date is updated.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
         }
      }
   }

   public void deleteIBData()
   {
      adminDAO.addDelIBPair("D", getAcctnum(), getIBStatus(), getClientAccountID());
      String msg = "IB Account# (" + getClientAccountID() + ") and Invessence Account# (" + getAcctnum() + ") link is deleted.";
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
   }

   public void viewPortfolio()
   {
      Integer status;
      String msg;
      status = collectPortfolioData();
      if (status <= 0)
      {
         if (status == 0)
         {
            msg = "This account is already processed.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
         }
         else
         {
            msg = "Account is required.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
         }

      }
   }


   public void redoPortfolio()
   {
      String msg;
      if ((getAcctnum() != null) && (!getAcctnum().toString().isEmpty()))//if (dataFound)
      {
         loadProfileData();
         collectPortfolioData();
         msg = "Invessence Account# (" + getAcctnum() + ") portfolio is created.";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
      }
      else
      {
         msg = "Cannot update till, you view the data.";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
      }
   }

   public void loadProfileData()
   {
      ManageGoals profile = new ManageGoals();

      if (getAcctnum() != null && getAcctnum() > 0)
      {
         //dataFound = true;
         profile.setAcctnum(getAcctnum());
         ManageGoals newgoals = goalsBo.findGoals(profile.getInstance());
         profile.copyData(newgoals);
         //profile.setAcctnum(acctnum);
         ManageGoals goals2 = goalsBo.findRiskTolerance(profile.getInstance());
         profile.setSelectedchoice1(goals2.getSelectedchoice1());
         profile.setSelectedchoice2(goals2.getSelectedchoice2());
         profile.setSelectedchoice3(goals2.getSelectedchoice3());
         profile.setSelectedchoice4(goals2.getSelectedchoice4());
         profile.setSelectedchoice5(goals2.getSelectedchoice5());
         profile.setSelectedchoice6(goals2.getSelectedchoice6());
         profile.setSelectedchoice7(goals2.getSelectedchoice7());
         createAssetPlan(profile.getInstance());
      }
   }

   private List<Position> positionList;
   private Double totalvalue;
   private Double totalvalue2;

   public Integer collectPortfolioData()
   {
      try
      {
         if (getAcctnum() != null)
         {
            this.positionList = adminDAO.loadVirtualPorfolio(getAcctnum());
            if (this.positionList.size() > 0)
            {
               setClientAccountID(this.positionList.get(0).getIBacctnum());
               setName(this.positionList.get(0).getFirstname() + " " + this.positionList.get(0).getLastname());
               addTotals();
               return (1);
            }
            else
            {
               return (0);
            }
         }
      }
      catch (Exception ex)
      {
         String msg = "Error in collect data from DB :" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
      }
      return (-1);

   }

   public void findVP(Long acctnum)
   {
      try
      {
         setAcctnum(acctnum);
         collectData();
      }
      catch (Exception e)
      {
         String msg = "Error in collecting data from DB:" + e.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
      }
   }

   public void onEdit(RowEditEvent event) {
      String msg;
      try {
         InvessenceData invdata = (InvessenceData) event.getObject();
         if (invdata == null) {
            msg = "Unable to save data, event returned empty data.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         }
         else {
            adminDAO.updateProfile(invdata);
            msg= "Invessence Account# (" + invdata.getAcctnum() + ") date is updated.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
         }
      }
      catch (Exception ex) {
         msg= "Unable to save data, Check log...";
         ex.printStackTrace();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
      }
   }

   public void onCancel(RowEditEvent event) {
      FacesMessage msg = new FacesMessage("Save Cancelled.", ((InvessenceData) event.getObject()).getAcctnum().toString());
      FacesContext.getCurrentInstance().addMessage(null, msg);
   }

   private void addTotals()
   {
      Integer rows;
      try
      {
         this.totalvalue = 0.0;
         this.totalvalue2 = 0.0;
         if (positionList == null)
         {
            return;
         }
         rows = positionList.size();
         for (int loop = 0; loop < rows; loop++)
         {
            Position position = positionList.get(loop);
            this.totalvalue = this.totalvalue + position.getOrigInvested();
            this.totalvalue2 = this.totalvalue2 + position.getInvested();
         }
      }
      catch (Exception ex)
      {
         String msg = "Error when attempting to addTotal" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
      }

   }

   public List<Position> getPositionList()
   {
      return positionList;
   }

   public void createAssetPlan(ManageGoals goals)
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      try
      {

         aamc = getAllocModel().getAssetDistribution((ProfileData) goals);
         goals.setNumOfAllocation(1);
         goals.setAssetData(aamc);

         goals.setNumOfPortfolio(1);
         pfclass = getPortfolioModel().getDistributionList(aamc,
                                                           (ProfileData) goals);
         goals.setPortfolioData(pfclass);
         goalsBo.saveAllocation(goals.getInstance());
         goalsBo.addPortfolio(goals.getInstance());

      }
      catch (Exception ex)
      {
         String msg = "Error creating/Saving Asset Allocation and Portfolio" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
      }
   }

   public String formatMoney(Double money)
   {
      if (money != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,###.00");
         return df.format(money);
      }
      return "";
   }

   public String getPopulatetotalvalue()
   {
      if (totalvalue != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,###.00");
         return df.format(totalvalue);
      }
      return "";
   }

   public String getPopulatetotalvalue2()
   {
      if (totalvalue2 != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,###.00");
         return df.format(totalvalue2);
      }
      return "";
   }

   public String getAllocatedPortfolioWeight(Double invested)
   {
      if (totalvalue != null)
      {
         DecimalFormat df = new DecimalFormat("###,####,###.00");
         Double value = (invested / totalvalue) * 100;
         return df.format(value);
      }
      return "";
   }

   private void collectClientData2Trade(String filter)
   {
      try
      {
         if (filter == null)
         {
            filter = "P";
         }
         setAdminTradeClientList(adminDAO.getProfileData(filter));
         filterClientData2Display();
      }
      catch (Exception ex)
      {
         String msg = "Error in collecting Clients To Trade" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         ex.printStackTrace();
      }
   }

   private void filterClientData2Display() {
      Integer numOptions=0;
      Boolean skip;
      try {
         if (getFilteredOption() == null || getFilteredOption().size() == 0) {
            filteredTradeList = getAdminTradeClientList();
         }
         else {
            numOptions = getFilteredOption().size();
            filteredTradeList = new ArrayList<AdminTradeClient>();

            for (Integer loop=0; loop < getAdminTradeClientList().size(); loop++) {
               skip=false;
               Integer opt=0;
               String filter;
               while (! skip && opt < numOptions) {
                  if (getAdminTradeClientList().get(loop).getReason().startsWith(getFilteredOption().get(opt)))
                     skip=true;
                  else
                     opt++;
               }
               if (!skip)
                  filteredTradeList.add(getAdminTradeClientList().get(loop));
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public List<AdminTradeClient> getFilteredTradeList() {
      return  filteredTradeList;
   }

   public void viewTradeClient() {
      setAcctnum(getSingleTradeAccount().getAcctnum());
      setClientAccountID(getSingleTradeAccount().getClientAccountID());
      setName(getSingleTradeAccount().getName());
      collectPortfolioData();
   }

   public void refreshTradeClient() {
      setSelectedTradeAccounts(null);
      collectClientData2Trade("P");
   }

   public void saveNextRebalDate()
   {
      String saveDate;
      try
      {
        saveDate = getNextRebalDate().substring(6,10) + getNextRebalDate().substring(0,2) + getNextRebalDate().substring(3,5);
         adminDAO.saveNextRebalDate(saveDate);
      }
      catch (Exception ex)
      {
         String msg = "Error in saving Next Rebalance Date" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         ex.printStackTrace();

      }

   }

   public void redoRebalance()
   {
      setSelectedTradeAccounts(null);
      adminDAO.reloadClientData();
      collectClientData2Trade("P");
      String msg = "Data collected..." ;
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
   }

   public void createTrades()
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      AdminTradeClient data;
      try
      {
         if (getSelectedTradeAccounts() != null)
         {
            Integer numClients = getSelectedTradeAccounts().length;
            for (Integer loop = 0; loop < numClients; loop++)
            {
               data = getSelectedTradeAccounts()[loop];
               aamc = getAllocModel().getAssetDistribution((ProfileData) data);
               data.setNumOfAllocation(1);
               data.setAssetData(aamc);

               data.setNumOfPortfolio(1);
               pfclass = getPortfolioModel().getDistributionList(aamc,
                                                                 (ProfileData) data);
               data.setPortfolioData(pfclass);
               adminDAO.saveAllocation(data.getInstance());
               adminDAO.savePortfolio(data.getInstance());
               adminDAO.createTrades(data.getAcctnum());
            }
            if (numClients > 0) {
               String msg = numClients.toString() + " account(s) were processed, successfully";
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
            }
            else {
               String msg = " No customers were seletected to create trades.  Action ignored!";
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
            }

         }

      }
      catch (Exception ex)
      {
         String msg = "Error in creating trades" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         ex.printStackTrace();
      }
   }

   public void displayTrades()
   {
      setTradedetails(adminDAO.loadTradesDetails(null));
   }

   private String getFileNameWithDateAndTime(String prefix)
   {
      Date date = new Date();
      SimpleDateFormat dateFormatWithDateAndTime = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss");
      return dateFormatWithDateAndTime.format(date) + prefix + ".csv";
   }

   String tradeFile1, tradeFile2, allocFile1, allocFile2;
   public void generateTrades()
   {
      try {
         String userHomeDir = null;
         Properties prop = new Properties();
         InputStream propInput = null;
         HttpServletRequest req = (HttpServletRequest) getCurrentInstance().getExternalContext().getRequest();
         propInput = req.getSession().getServletContext().getResourceAsStream("/WEB-INF/classes/invessence.properties");
         prop.load(propInput);
         userHomeDir = prop.getProperty("doc.location");
         System.out.println("Data File Location :" + userHomeDir);

         List<Map<String, Object>> data;
         data = adminDAO.getTradesAllocationData();
         tradeFile1 = getFileNameWithDateAndTime("_TRADES_BUY");
         tradeFile1 = userHomeDir + tradeFile1;
         allocFile1 = getFileNameWithDateAndTime("_ALLOC_BUY");
         allocFile1 =  userHomeDir + allocFile1;
         tw.downloadTradesAllocationFile(data, tradeFile1, allocFile1, "BUY");
         tradeFile2 = getFileNameWithDateAndTime("_TRADES_SELL");
         tradeFile2 = userHomeDir + tradeFile2;
         allocFile2 = getFileNameWithDateAndTime("_ALLOC_SELL");
         allocFile2 =  userHomeDir + allocFile2;
         tw.downloadTradesAllocationFile(data, tradeFile2, allocFile2, "SELL");
         adminDAO.updateExecutedTrades();
         String msg = "Trade and Allocation files produced in (" + userHomeDir + ")";
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
      }
      catch (Exception ex) {
         String msg = "Error when producing file" + ex.getMessage();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
         ex.printStackTrace();
      }
   }

   public StreamedContent getTrade1()
   {
      return downloadFile(tradeFile1, "TradeBUY.csv");
   }

   public StreamedContent getTrade2()
   {
      return downloadFile(tradeFile2, "TradeSELL.csv");
   }

   public StreamedContent getAlloc1()
   {
      return downloadFile(allocFile1, "AllocBUY.csv");
   }

   public StreamedContent getAlloc2()
   {
      return downloadFile(allocFile2, "AllocSELL.csv");
   }

   private StreamedContent downloadFile(String name, String outputName) {
      StreamedContent file = null;
      try {
         InputStream stream = new FileInputStream(new File(name))
         {
            @Override
            public int read() throws IOException
            {
               return 0;
            }
         };
         file = new DefaultStreamedContent(stream, "application/csv", outputName);
         return file;
      }
      catch (Exception ex) {

      }
      return file;
   }
}