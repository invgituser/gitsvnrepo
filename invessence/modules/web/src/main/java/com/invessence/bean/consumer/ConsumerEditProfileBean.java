package com.invessence.bean.consumer;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.invessence.constant.Const;
import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.*;
import com.invessence.data.advisor.AdvisorData;
import com.invessence.data.common.*;
import com.invessence.util.*;
import org.primefaces.event.SlideEndEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "cepb")
@SessionScoped
public class ConsumerEditProfileBean extends AdvisorData implements Serializable
{
   private Long  beanAcctnum;
   private Boolean disablegraphtabs = true
      , disabledetailtabs = true
      , disablesaveButton = true;

   private Boolean formEdit = true;

   private WebUtil webutil = new WebUtil();
   private Charts charts = new Charts();

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;

   public Long getBeanAcctnum()
   {
      return beanAcctnum;
   }

   public void setBeanAcctnum(Long beanAcctnum)
   {
      SQLData converter = new SQLData();
      this.beanAcctnum = converter.getLongData(beanAcctnum);
   }

   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   public void setSaveDAO(ConsumerSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public Boolean getDisablegraphtabs()
   {
      return disablegraphtabs;
   }

   public Boolean getDisabledetailtabs()
   {
      return disabledetailtabs;
   }

   public Boolean getDisablesaveButton()
   {
      return disablesaveButton;
   }

   public Charts getCharts()
   {
      return charts;
   }

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (getBeanAcctnum() != null && getBeanAcctnum() > 0L) {
               loadData(getBeanAcctnum());
            }
            else {
               loadNewClientData();
            }
            formEdit = false;
         }
      }
      catch (Exception e)
      {
         resetDataForm();
      }
   }

   @PostConstruct
   public void init()
   {
      try
      {
         webutil.validatePriviledge(Const.ROLE_OWNER);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue = null;
      String newValue = null;
      try
      {
         if (event.getNewValue() == null)
         {
            return;
         }
         else {
            if ( (getAge() != null) && (getAccountType() != null) && (getInitialInvestment() != null) && (getRiskIndex() != null))
               disablesaveButton = false;
            else
               formEdit = true;
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void selectedAccountType() {
      formEdit = true;

      if (getAccountType().toUpperCase().contains("RETIRE")) {
         if (getAge() == null)
            setHorizon(20);
         else if (getAge() < 65)
            setHorizon(65 - getAge());
         else
            setHorizon(2);
      }
      else {
         if (getAccountType().toUpperCase().contains("SAFETY"))
            setHorizon(3);
         else
            setHorizon(20);
      }
      loadBasketInfo();
      refresh();
   }

   public void selectedActionBasket() {
      getExcludedSubAsset().clear();
      createAssetPortfolio(1);
   }

   private void resetDataForm() {
      disablegraphtabs = true;
      disabledetailtabs = true;
      resetManagedGoalData();
   }

   private void loadBasketInfo() {
      if (getAccountTaxable())
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "T"));
      else
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "R"));
   }

   private void loadNewClientData() {

      resetAdvisorData();
      try {
         UserInfoData uid = webutil.getUserInfoData();
         if (uid != null) {
            setAdvisor(uid.getGroupname()); // Portfolio solves the null issue, or blank issue.
            setLogonid(uid.getLogonID());
         }
         listDAO.getNewClientProfileData((ManageGoals) this.getInstance());
         loadBasketInfo();
         createAssetPortfolio(getDefaultHorizon()); // Build default chart for the page...
         formEdit = false;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void loadData(Long acctnum) {

      resetAdvisorData();
      try {
         UserInfoData uid = webutil.getUserInfoData();
         if (uid != null) {
            setAdvisor(uid.getGroupname()); // Portfolio solves the null issue, or blank issue.
            setLogonid(uid.getLogonID());
         }
         setAcctnum(acctnum);
         listDAO.getProfileData((ManageGoals) this.getInstance());
         loadBasketInfo();

         createAssetPortfolio(getDefaultHorizon());
         formEdit = false;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void ageSlider(SlideEndEvent event) {
      setAge(event.getValue());
      createAssetPortfolio(getDefaultHorizon());
   }

   public void riskSlider(SlideEndEvent event) {
      setDefaultRiskIndex(event.getValue());
      createAssetPortfolio(getDefaultHorizon());
   }

   public void refresh() {
      createAssetPortfolio(getDefaultHorizon());
   }

   private void createAssetPortfolio(Integer noOfYears) {

      try {
         formEdit = true;
         setNumOfAllocation(noOfYears);
         setNumOfPortfolio(noOfYears);
         buildPortfolio();
         createCharts();
      }
      catch (Exception ex) {
           ex.printStackTrace();
      }
   }

   private void createCharts() {

      try {
         if (getAssetData() != null) {
               charts.createPieModel(getAssetData(),0);
         }

         if (getPortfolioData() != null) {
            charts.createLineModel(getPortfolioData(), getPortfolioData().length);
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public Boolean validateProfile() {
      try {
         String message = null;

         if (getAge() == null)
            message = "Age is required<br/>";
         if (getInitialInvestment() == null)
            message = "Initial Investment Amount needs to be defined<br/>";
         if (getRiskIndex() == null)
            message = "Risk has to be defined.<br/>";
         if (getEmail() == null)
            message = "Customer profile has to be created.<br/>";

         if (message != null) {
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Error", "Incomplete Form " + message));
            return false;
         }
      }
      catch (Exception ex) {
         FacesContext context = FacesContext.getCurrentInstance();

         context.addMessage(null, new FacesMessage("Error", "Serious Error " + "System Error: " + ex.getMessage()));
         return false;
      }
      return true;
   }

   public void saveProfile()
   {
      Boolean validate = false;
      try
      {
         validate = validateProfile();

         if (validate) {
            disablesaveButton = true;
            saveDAO.saveProfileData(this.getInstance());
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void NextPage()
   {

      try
      {
         if (validateProfile()) {
            saveProfile();
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


}

