package com.invessence.web.bean.custody;

import java.lang.reflect.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.custody.uob.UOBDataMaster;
import com.invessence.custody.uob.data.*;
import com.invessence.service.util.*;
import com.invessence.util.AddressSplitter;
import com.invessence.web.service.custody.CustodyService;
import com.invessence.web.util.Impl.PagesImpl;
import com.invessence.web.util.WebUtil;
import com.invessence.service.bean.Generic.Country;
import org.apache.commons.collections.iterators.ObjectArrayIterator;
import org.apache.commons.logging.*;

/**
 * Created by abhangp on 11/10/2017.
 */
@ManagedBean(name = "uobCustodyBean")
@SessionScoped
public class UOBCustodyBean
{
   protected final Log logger = LogFactory.getLog(getClass());
   @ManagedProperty("#{uobCustodyService}")
   private CustodyService custodyService;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   private UOBDataMaster uobDataMaster;
   private PagesImpl pagemanager = new PagesImpl(8);
   private long beanAcctNum;
   private boolean dsplExtIndAcctCat = false, dsplExtIndAcctInp = false, dsplExtJntAcctCat = false, dsplExtJntAcctInp = false, dsplAcctTyp = false;
   private boolean dspExtAcctPnl = false, dspNewAcctPnl = false, dspIntroAcctPnl = false, dsblSubmtBtn = true, dspJntTab = false;
   private String acctCat, extAcctInd, extAcctJnt, clientAcctId, slsPrsnNm, selIndAcctTyp, selJntAcctTyp;
   private Integer activeTab = 0;
   private boolean  dsplOtrCntry = false, dsplIcNoInp = false, dsplPspNoInp = false,dsplSrcIncOtrs=false;
   private Date dtPriHldrDob;
   private boolean dsplNwPriRelDtl = false, dsplNwPriInfDtl = false, dsplNwPriCtrlDtl = false;
   String introError,taxError;
   private String priHldrEmpAddr;
   private  boolean dsplNwPriEmpOtrDtlPnl=false,dsplNwPriEmpMnPnl=false;
   private String priHldrPhyAddr,priHldrMlAddr;
   private boolean dsplPriHldrMlPnl=false,dsplSingNricInp=false,dsplNricInp=false;
   private boolean dsplPriHldrObjPnl1=false,dsplPriHldrObjPnl2=false,dsplPriHldrObjPnl3=false,dsplPriHldrObjPnl4=false;
   private OwnerTaxationDetails owTaxDtls=null;
   private boolean dsplPriHldrTaxTab=false,dsplPriHldrTaxDtl=false,dsplPriHldrTaxRsn=false,dsplPriHldrTaxTin=false,dsplPriHldrTaxRsnPnl=false,disTaxBtn=false,dispTaxAddBtn=false,dispTaxUpdBtn=true;;
   Map<String,Country> countryDetails=(Map<String,Country>) ServiceDetails.genericDetails.get(Constant.GENERIC_DETAILS.COUNTRY.toString());
   private List <String> countries;
   public void cleanUpAll()
   {
      dsplPriHldrMlPnl=false;
      priHldrPhyAddr=null;
      priHldrMlAddr=null;
      dsplExtIndAcctCat = false;
      dsplExtIndAcctInp = false;
      dsplExtJntAcctCat = false;
      dsplExtJntAcctInp = false;
      dsplAcctTyp = false;
      dspExtAcctPnl = false;
      dspNewAcctPnl = false;
      dspIntroAcctPnl = false;
      dsblSubmtBtn = true;
      dspJntTab = false;
      dsplNricInp = false;
      dsplSingNricInp=false;
      dsplOtrCntry = false;
      dsplIcNoInp = false;
      dsplPspNoInp = false;
      acctCat = null;
      extAcctInd = null;
      extAcctJnt = null;
      clientAcctId = null;
      slsPrsnNm = null;
      selIndAcctTyp = null;
      selJntAcctTyp = null;
      introError = null;
      activeTab = 0;
      dtPriHldrDob = new Date();
      dspIntroAcctPnl = true;
      dsblSubmtBtn = true;
      dspJntTab = false;
      dsplSrcIncOtrs=false;
      dsplNwPriRelDtl = false;
      dsplNwPriInfDtl = false;
      dsplNwPriCtrlDtl = false;
      dsplNwPriEmpOtrDtlPnl=false;
      dsplNwPriEmpMnPnl=false;
      priHldrEmpAddr=null;
      dsplPriHldrObjPnl1 = false;
      dsplPriHldrObjPnl2 = false;
      dsplPriHldrObjPnl3 = false;
      dsplPriHldrObjPnl4 = false;
      owTaxDtls=new OwnerTaxationDetails();
      dsplPriHldrTaxTab=false;
      dsplPriHldrTaxDtl=false;
      dsplPriHldrTaxRsn=false;
      dsplPriHldrTaxTin=false;
      dsplPriHldrTaxRsnPnl=false;
      disTaxBtn=false;
      dispTaxAddBtn=false;
      dispTaxUpdBtn=false;
   }

   public void initCustody()
   {
      String msgheader;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (getBeanAcctNum() == 0)
            {
               msgheader = "dctd.100";
               getWebutil().redirecttoMessagePage("ERROR", "Access Denied", msgheader);
               return;
            }
            cleanUpAll();
            uobDataMaster = custodyService.fetch(getBeanAcctNum(), false);
            uobDataMaster.getAccountDetails().setAcctnum(getBeanAcctNum());
            onChngNation();
            onChngOtrCntry();
            loadIntroPage();
            onChngRelDtls();
            onChngEmplt();
            onChngAddr();
            onChngObj();
            onChngSrcOfInc();
            owTaxDtls=new OwnerTaxationDetails();
            countries=new ArrayList<String>();
//            countries=(List)countryDetails.keySet();
            countries= new ArrayList<String>(countryDetails.keySet());

            if (uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() != null &&
               uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails().size() > 0)
            {
               dsplPriHldrTaxTab = true;
               disTaxBtn=false;
            }else if (uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() == null ||
               uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails().size() == 0)
            {
               dsplPriHldrTaxDtl = true;
               dispTaxAddBtn=true;
            }

            priHldrEmpAddr=getCstmaddrEmp(uobDataMaster.getIndividualOwnersDetails().getOwnerEmploymentDetails());
            priHldrPhyAddr=getCstmaPhyAddr(uobDataMaster.getIndividualOwnersDetails());
            priHldrMlAddr=getCstmaMalAddr(uobDataMaster.getIndividualOwnersDetails());

            if (uobDataMaster.getIndividualOwnersDetails().getDob() == null ||
               uobDataMaster.getIndividualOwnersDetails().getDob().equalsIgnoreCase(""))
            {
               dtPriHldrDob = null;
            }
            else
            {
               DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
               dtPriHldrDob = df.parse(uobDataMaster.getIndividualOwnersDetails().getDob());
            }
         }
      }
      catch (Exception ex)
      {
         logger.info("Exception: raised during Starting TD CTO process.");
      }
   }

   public String getCstmaddrEmp(OwnerEmploymentDetails ownerEmploymentDetails){
      StringBuilder sb = new StringBuilder();
      if (ownerEmploymentDetails.getEmployerStreetAddress1() != null)
      {
         sb.append(ownerEmploymentDetails.getEmployerStreetAddress1() + "");
      }
      if (ownerEmploymentDetails.getEmployerStreetAddress2() != null)
      {
         sb.append(ownerEmploymentDetails.getEmployerStreetAddress2() + "");
      }
      if (ownerEmploymentDetails.getEmployerStreetAddress3() != null)
      {
         sb.append(ownerEmploymentDetails.getEmployerStreetAddress3() + "");
      }
      if(sb.length()>0){
         return sb.toString();
      }
      return null;
   }

   public String getCstmaPhyAddr(OwnerDetails ownerDetails){
      StringBuilder sb = new StringBuilder();
      if (ownerDetails.getPhysicalAddressStreet1() != null)
      {
         sb.append(ownerDetails.getPhysicalAddressStreet1() + "");
      }
      if (ownerDetails.getPhysicalAddressStreet2() != null)
      {
         sb.append(ownerDetails.getPhysicalAddressStreet2() + "");
      }
      if (ownerDetails.getPhysicalAddressStreet3() != null)
      {
         sb.append(ownerDetails.getPhysicalAddressStreet3() + "");
      }
      if(sb.length()>0){
         return sb.toString();
      }
      return null;
   }

   public String getCstmaMalAddr(OwnerDetails ownerDetails){
      StringBuilder sb = new StringBuilder();
      if (ownerDetails.getMailingAddressStreet1() != null)
      {
         sb.append(ownerDetails.getMailingAddressStreet1() + "");
      }
      if (ownerDetails.getMailingAddressStreet2() != null)
      {
         sb.append(ownerDetails.getMailingAddressStreet2() + "");
      }
      if (ownerDetails.getMailingAddressStreet3() != null)
      {
         sb.append(ownerDetails.getMailingAddressStreet3() + "");
      }
      if(sb.length()>0){
         return sb.toString();
      }
      return null;
   }
   public void onChangeValue()
   {
      try
      {
//         if (getAcctCat().equalsIgnoreCase("yes"))
//         {
//            dsplExtIndAcctCat = true;
//
//         }
//         else
//         {
//            dsplExtIndAcctCat = false;
//         }

         if (getAcctCat().equalsIgnoreCase("yes") && getExtAcctInd() != null)
         {
            dsplExtIndAcctCat = true;
            if (getExtAcctInd().equalsIgnoreCase("yes"))
            {
               dsplExtIndAcctInp = true;
               dsplExtJntAcctCat = false;
            }
            else
            {
               dsplExtIndAcctInp = false;
               dsplExtJntAcctCat = true;
            }
         }
      }
      catch (Exception e)
      {
         System.out.println("Error " + e);
         e.printStackTrace();
      }
   }

   public void onChangeValue0()
   {
      try
      {
         if (getAcctCat().equalsIgnoreCase("yes"))
         {
            dsplExtIndAcctCat = true;

         }
         else
         {
            dsplExtIndAcctCat = false;
         }
         extAcctInd=null;
         uobDataMaster.getAccountDetails().setClientAccountID(null);

//         dsplExtIndAcctCat = false;
         dsplExtIndAcctInp = false;
         dsplExtJntAcctCat = false;


      }
      catch (Exception e)
      {
         System.out.println("Error " + e);
         e.printStackTrace();
      }
   }

   public void loadIntroPage()
   {

      if (uobDataMaster.getAccountDetails().getAcctTypeId() != null && uobDataMaster.getAccountDetails().getAcctTypeId() != "")
      {
         if (uobDataMaster.getAccountDetails().getClientAccountID() == null || uobDataMaster.getAccountDetails().getClientAccountID() == "")
         {
            setAcctCat("No");
         }
         else
         {
            setAcctCat("Yes");
            setExtAcctInd("Yes");
         }
         setSelIndAcctTyp("Individual");
//         onChangeValue0();
         if (getAcctCat().equalsIgnoreCase("yes"))
         {
            dsplExtIndAcctCat = true;

         }
         else
         {
            dsplExtIndAcctCat = false;
         }
         onChangeValue();
      }
   }

   public void onSelAcctTypAsInd()
   {
      setSelJntAcctTyp(null);
      uobDataMaster.getAccountDetails().setAcctTypeId("ACINDIV");
      uobDataMaster.getIndividualOwnersDetails().setOwnership("Individual");
      System.out.println("selIndAcctTyp " + selIndAcctTyp);
      System.out.println("selJntAcctTyp " + selJntAcctTyp);
   }

   public void onChngObj(){
      try{
         if(!hasRequiredData(uobDataMaster.getIndividualOwnersDetails().getOwnersFinancialDetails().getPreviousInvestingExperience(),"No")){
            dsplPriHldrObjPnl1=false;
         }else{
            dsplPriHldrObjPnl1=true;
         }

         if(!hasRequiredData(uobDataMaster.getIndividualOwnersDetails().getOwnersFinancialDetails().getInvestmentObjectives(),"Others")){
            dsplPriHldrObjPnl2=true;
            if(uobDataMaster.getIndividualOwnersDetails().getOwnersFinancialDetails().getInvestmentObjectives().equalsIgnoreCase("Select")){
               dsplPriHldrObjPnl2=false;
            }
         }else{
            dsplPriHldrObjPnl2=false;
         }

         if(!hasRequiredData(uobDataMaster.getIndividualOwnersDetails().getOwnersFinancialDetails().getAreYouUnableToPayYouDebts(),"No")){
            dsplPriHldrObjPnl3=false;
         }else{
            dsplPriHldrObjPnl3=true;
         }

         if(!hasRequiredData(uobDataMaster.getIndividualOwnersDetails().getOwnersFinancialDetails().getDoYouHaveAnyDisputedAccount(),"No")){
            dsplPriHldrObjPnl4=false;
         }else{
            dsplPriHldrObjPnl4=true;
         }

      }catch (Exception e){

      }
   }

   public void onChngTin()
   {
      try
      {
         if (owTaxDtls.getIsTINAvailable() == null || owTaxDtls.getIsTINAvailable().equalsIgnoreCase("Yes"))
         {
            dsplPriHldrTaxTin = true;
            dsplPriHldrTaxRsnPnl=false;
         }
         else
         {
            dsplPriHldrTaxTin = false;
            dsplPriHldrTaxRsnPnl=true;
         }
      }
      catch (Exception e)
      {

      }
   }

   public void onChngRsn()
   {
      try
      {
         if (owTaxDtls.getTinUnavailableReason() != null && owTaxDtls.getTinUnavailableReason().equalsIgnoreCase("B"))
         {
            dsplPriHldrTaxRsn = true;
         }
         else
         {
            dsplPriHldrTaxRsn = false;
         }
      }
      catch (Exception e)
      {

      }
   }

   public void showTaxDtlPnl()
   {
      try
      {
         dsplPriHldrTaxDtl = true;
         onChngTin();
         onChngRsn();
         if (uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() != null &&
            uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails().size() > 0)
         {
            disTaxBtn=true;
         }
         dispTaxAddBtn=true;
         dispTaxUpdBtn=false;
      }
      catch (Exception e)
      {
      }
   }

   public void hideTaxDtlPnl()
   {
      try
      {
         dsplPriHldrTaxTab = true;
         dsplPriHldrTaxDtl = false;
         owTaxDtls = new OwnerTaxationDetails();
         dsplPriHldrTaxTin=false;
         disTaxBtn=false;
         onChngTin();
         onChngRsn();
         owTaxDtls = new OwnerTaxationDetails();
         dsplPriHldrTaxTin=false;

         if (uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() != null &&
            uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails().size() > 0)
         {
            dsplPriHldrTaxTab = true;
            dsplPriHldrTaxDtl = false;
            disTaxBtn=false;
         }else if (uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() == null ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails().size() == 0)
         {
            dsplPriHldrTaxDtl = true;
            dsplPriHldrTaxTab = false;
         }
      }
      catch (Exception e)
      {
      }
   }

   public void onSelAcctTypAsJnt()
   {
      setSelIndAcctTyp(null);
      System.out.println("selIndAcctTyp " + selIndAcctTyp);
      System.out.println("selJntAcctTyp " + selJntAcctTyp);
   }

   public void introNextPage()
   {
      if (validateIntroPage())
      {
         custodyService.saveAcctDetails(uobDataMaster.getAccountDetails(), "12");
         dspIntroAcctPnl = false;
         getPagemanager().setPage(0);
         if (getAcctCat().equalsIgnoreCase("yes"))
         {
            dspExtAcctPnl = true;
            dspNewAcctPnl = false;
            setPagemanager(new PagesImpl(2));
            Integer currentPage = getPagemanager().getPage();
            getPagemanager().initPage();
//         resetActiveTab(0);
            getPagemanager().setLastPageVisited(currentPage);
            getPagemanager().clearAllErrorMessage();

         }
         else if (getAcctCat().equalsIgnoreCase("no"))
         {

            setPagemanager(new PagesImpl(8));
            Integer currentPage = getPagemanager().getPage();
            getPagemanager().initPage();
//         resetActiveTab(0);
            getPagemanager().setLastPageVisited(currentPage);
            getPagemanager().clearAllErrorMessage();
            dspExtAcctPnl = false;
            dspNewAcctPnl = true;
         }
         else
         {
            System.out.println("Need to add in validation condition");
         }
      }
   }

   public boolean validateIntroPage()
   {
      Boolean dataOK = true;
      StringBuilder sb = new StringBuilder();
      if (acctCat == null || acctCat == "")
      {
         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.acctType.exist", "Please select Existing securities trading account on UOBKH Yes/No!", null));
         sb.append("Please select Existing securities trading account on UOBKH Yes/No.<br/>");
      }
      if (acctCat != null && acctCat.equalsIgnoreCase("yes"))
      {
         if (extAcctInd == null || extAcctInd == "")
         {
            dataOK = false;
            sb.append("Please select Is it Individual Account Yes/No.<br/>");
         }
         if ((extAcctInd != null || extAcctInd != "") && extAcctInd.equalsIgnoreCase("yes"))
         {
            if (uobDataMaster.getAccountDetails().getClientAccountID() == null || uobDataMaster.getAccountDetails().getClientAccountID().trim().equalsIgnoreCase(""))
            {
               dataOK = false;
               sb.append("Enter your existing  Securities Trading account number is required.<br/>");
            }
         }
         else if ((extAcctInd != null || extAcctInd != "") && extAcctInd.equalsIgnoreCase("No"))
         {
            if (uobDataMaster.getAccountDetails().getClientAccountID() == null || uobDataMaster.getAccountDetails().getClientAccountID().trim().equalsIgnoreCase(""))
            {
               dataOK = false;
               sb.append("Enter your existing Joint UOBKH account number is required.<br/>");
            }
         }
      }
      if (uobDataMaster.getAccountDetails().getRepId() == null || uobDataMaster.getAccountDetails().getRepId().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         sb.append("Sales Person Name is required.<br/>");
      }
      if (uobDataMaster.getAccountDetails().getAcctTypeId() == null || uobDataMaster.getAccountDetails().getAcctTypeId() == "")
      {
         dataOK = false;
         sb.append("Type of account like to open is required.<br/>");
      }
      if (sb.length() > 0)
      {
         dataOK = false;
         introError = sb.toString();
      }
      else
      {
         dataOK = true;
         introError = null;
      }

      return dataOK;
   }

   public void updateTaxPnl(){
      if(validateTaxPnl()){
//         uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() Need to remove this code by object
         custodyService.deleteSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(),1,"Taxation");
         List<OwnerTaxationDetails>  lst=uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails();
//         lst.add(owTaxDtls.getId()-1,owTaxDtls);
//         lst.add(owTaxDtls);
         for(int i=0;i<lst.size();i++){
            OwnerTaxationDetails owTaxDtl=lst.get(i);
            if(i==owTaxDtls.getId()-1){
               owTaxDtl=owTaxDtls;
            }
            if(hasRequiredData(owTaxDtl.getIsTINAvailable())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","isTINAvailable",owTaxDtl.getIsTINAvailable());
            }
            if(hasRequiredData(owTaxDtl.getJurisdictionOfTaxResidence())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","jurisdictionOfTaxResidence",owTaxDtl.getJurisdictionOfTaxResidence());
            }
            if(hasRequiredData(owTaxDtl.getTaxIdentificationNumber())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","taxIdentificationNumber",owTaxDtl.getTaxIdentificationNumber());
            }
            if(hasRequiredData(owTaxDtl.getTinUnavailableReason())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","tinUnavailableReason",owTaxDtl.getTinUnavailableReason());
            }
            if(hasRequiredData(owTaxDtl.getTinUnavailableValue())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","tinUnavailableValue",owTaxDtl.getTinUnavailableValue());
            }
         }

         dsplPriHldrTaxTab = true;
         dsplPriHldrTaxDtl = false;
         owTaxDtls = new OwnerTaxationDetails();
         dsplPriHldrTaxTin=false;
         disTaxBtn=false;
      }

   }

   public void saveTaxPnl(){
      if(validateTaxPnl()){
//         uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() Need to remove this code by object
         custodyService.deleteSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(),1,"Taxation");
         List<OwnerTaxationDetails>  lst=uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails();
         if(lst!=null && lst.size()>0)
         {
            owTaxDtls.setId(lst.size()+1);
         }else{
            owTaxDtls.setId(1);
         }
         lst.add(owTaxDtls);
         for(int i=0;i<lst.size();i++){
            OwnerTaxationDetails owTaxDtl=lst.get(i);
            if(hasRequiredData(owTaxDtl.getIsTINAvailable())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","isTINAvailable",owTaxDtl.getIsTINAvailable());
            }
            if(hasRequiredData(owTaxDtl.getJurisdictionOfTaxResidence())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","jurisdictionOfTaxResidence",owTaxDtl.getJurisdictionOfTaxResidence());
            }
            if(hasRequiredData(owTaxDtl.getTaxIdentificationNumber())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","taxIdentificationNumber",owTaxDtl.getTaxIdentificationNumber());
            }
            if(hasRequiredData(owTaxDtl.getTinUnavailableReason())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","tinUnavailableReason",owTaxDtl.getTinUnavailableReason());
            }
            if(hasRequiredData(owTaxDtl.getTinUnavailableValue())){
               custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1,i+1,"Taxation","tinUnavailableValue",owTaxDtl.getTinUnavailableValue());
            }
         }

         dsplPriHldrTaxTab = true;
         dsplPriHldrTaxDtl = false;
         owTaxDtls = new OwnerTaxationDetails();
         dsplPriHldrTaxTin=false;
         disTaxBtn=false;
      }

   }

   public boolean validateTaxPnl()
   {
      Boolean dataOK = true;
      StringBuilder sb = new StringBuilder();
      if (!hasRequiredData(owTaxDtls.getJurisdictionOfTaxResidence()) || owTaxDtls.getJurisdictionOfTaxResidence().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         sb.append("Country / Jurisdiction of tax residence is required.<br/>");
      }
      if(!hasRequiredData(owTaxDtls.getIsTINAvailable()) ){
         dataOK = false;
         sb.append("Tax Identification Number (Yes / No ) is required.<br/>");
      }
      if(owTaxDtls.getIsTINAvailable()!=null && owTaxDtls.getIsTINAvailable().equalsIgnoreCase("Yes") && !hasRequiredData(owTaxDtls.getTaxIdentificationNumber())){
         dataOK = false;
         sb.append("Tax Identification Number is required.<br/>");
      }
      if(owTaxDtls.getIsTINAvailable()!=null && owTaxDtls.getIsTINAvailable().equalsIgnoreCase("No") && !hasRequiredData(owTaxDtls.getTinUnavailableReason())){
         dataOK = false;
         sb.append("Reason for no Tax identification number is required.<br/>");
      }

      if(owTaxDtls.getIsTINAvailable()!=null && owTaxDtls.getIsTINAvailable().equalsIgnoreCase("No") && !hasRequiredData(owTaxDtls.getTinUnavailableReason())){
         dataOK = false;
         sb.append("Reason for no Tax identification number is required.<br/>");
      }

      if (sb.length() > 0)
      {
         dataOK = false;
         taxError = sb.toString();
      }
      else
      {
         dataOK = true;
         taxError = null;
      }
      return dataOK;
   }

   public void nextPage()
   {
      System.out.println("page current" + getPagemanager().getPage());
      if (validate(getPagemanager().getPage(), getAcctCat(), false))
      {
         saveDetails(getPagemanager().getPage(), getAcctCat(), false);

         getPagemanager().clearAllErrorMessage();
         if(getPagemanager().isLastPage()){
            dsblSubmtBtn=false;
         }else{
            getPagemanager().nextPage();
            dsblSubmtBtn=true;
         }
         System.out.println("page next" + getPagemanager().getPage());
         activeTab = getPagemanager().getPage();
      }
   }

   public void prevPage()
   {
      System.out.println("page current" + getPagemanager().getPage());
      getPagemanager().prevPage();
      System.out.println("page prev" + getPagemanager().getPage());
      activeTab = getPagemanager().getPage();
   }

   public void onChngNation()
   {
      try
      {
         if (uobDataMaster.getIndividualOwnersDetails().getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Singapore"))
         {
            dsplNricInp = true;
            dsplOtrCntry = false;
            dsplIcNoInp = false;
            dsplPspNoInp = false;
            dsplSingNricInp=false;
         }else if(uobDataMaster.getIndividualOwnersDetails().getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Singapore Pr")){
            dsplNricInp = false;
            dsplOtrCntry = false;
            dsplIcNoInp = false;
            dsplPspNoInp = false;
            dsplSingNricInp=true;
         }
         else
         {
            dsplNricInp = false;
            dsplSingNricInp=false;
            dsplOtrCntry = true;
            dsplIcNoInp = false;
            dsplPspNoInp = false;
         }
      }
      catch (Exception e)
      {
      }
   }

   public void onChngEmplt(){
      try{
         if(uobDataMaster.getIndividualOwnersDetails().getOwnerEmploymentDetails().getEmplTypeId()==null ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerEmploymentDetails().getEmplTypeId().equalsIgnoreCase("UnEmployed")){
            dsplNwPriEmpMnPnl=false;

            uobDataMaster.getIndividualOwnersDetails().getOwnerEmploymentDetails().setOccupation(null);
            uobDataMaster.getIndividualOwnersDetails().getOwnerEmploymentDetails().setEmployerName(null);
            priHldrEmpAddr=null;
            uobDataMaster.getIndividualOwnersDetails().getOwnerEmploymentDetails().setEmployerZipCode(null);
            uobDataMaster.getIndividualOwnersDetails().getOwnerEmploymentDetails().setEmployerCity(null);
            uobDataMaster.getIndividualOwnersDetails().getOwnerEmploymentDetails().setEmployerZipCountry(null);
         }else{
            dsplNwPriEmpMnPnl=true;
         }

         if(uobDataMaster.getIndividualOwnersDetails().getOwnerMiscDetails().getQualifications()!=null &&
            uobDataMaster.getIndividualOwnersDetails().getOwnerMiscDetails().getQualifications().equalsIgnoreCase("Others")){
            dsplNwPriEmpOtrDtlPnl=true;
         }else{
            dsplNwPriEmpOtrDtlPnl=false;
         }
      }catch (Exception e){}
   }

   public void onChngAddr(){
      try
      {
         if(uobDataMaster.getIndividualOwnersDetails().getOwnerMiscDetails().getMailAddressSameAsPhysical()==null ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerMiscDetails().getMailAddressSameAsPhysical().equalsIgnoreCase("Yes"))
         {
            dsplPriHldrMlPnl=false;
         }else{
            dsplPriHldrMlPnl=true;
         }

      }catch (Exception e){

      }
   }


   public void onChngSrcOfInc()
   {
      try
      {
         if (uobDataMaster.getIndividualOwnersDetails().getOwnersFinancialDetails().getSourceOfFundsWealth().equalsIgnoreCase("Others") )
         {
            dsplSrcIncOtrs = true;
         }
         else
         {
            dsplSrcIncOtrs = false;
         }
      }
      catch (Exception e)
      {
      }
   }

   public void onChngRelDtls(){
      try{
         if(uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getRelatedToAnyEmplfUOB()== null ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getRelatedToAnyEmplfUOB().trim().equalsIgnoreCase("") ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getRelatedToAnyEmplfUOB().equalsIgnoreCase("No")){
            dsplNwPriRelDtl=false;
         }else{
            dsplNwPriRelDtl=true;
         }

         if(uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getControlOverUOBAcct()== null ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getControlOverUOBAcct().trim().equalsIgnoreCase("") ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getControlOverUOBAcct().equalsIgnoreCase("No")){
            dsplNwPriInfDtl=false;
         }else{
            dsplNwPriInfDtl=true;
         }

         if(uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcct()== null ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcct().trim().equalsIgnoreCase("") ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcct().equalsIgnoreCase("No")){
            dsplNwPriCtrlDtl=false;
         }else{
            dsplNwPriCtrlDtl=true;
         }
      }catch(Exception e){
      }
   }


   public void onChngOtrCntry()
   {
      try
      {
         dsplNricInp = false;
         dsplSingNricInp=false;
         if (uobDataMaster.getIndividualOwnersDetails().getOwnerCitizenshipDetails().getNationalitySpecify().equalsIgnoreCase("malaysia"))
         {
            dsplIcNoInp = true;
            dsplPspNoInp = false;
         }
         else
         {
            dsplIcNoInp = false;
            dsplPspNoInp = true;
         }
      }
      catch (Exception e)
      {
      }
   }

   public Boolean validate(int pagenum, String isNewForm, boolean isJoint)
   {
      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);
      OwnerDetails OwnDtls=null;
      if (!isJoint)
      {
         OwnDtls=uobDataMaster.getIndividualOwnersDetails();
      }else{
         OwnDtls=uobDataMaster.getJointOwnersDetails();
      }
      if (isNewForm.equalsIgnoreCase("NO"))
      {
         switch (pagenum)
         {
            case 0:// New Account ACCOUNT HOLDER
               dataOK = validateAcctHldr(OwnDtls, dtPriHldrDob);
               break;
            case 1:// New Account ADDRESS
               dataOK=validateAddrDtls(OwnDtls);
               break;
            case 2:// New Account TAX RESIDENCE INFORMATION
               dataOK=validateTaxMnPnl(OwnDtls);
               break;
            case 3:// New Account EMPLOYMENT
               dataOK=validateEmpDtls(OwnDtls);
               break;
            case 4:// New Account SECURITY QUESTION
               dataOK=validateSecDtls(OwnDtls);
               break;
            case 5:// New Account FINANCIAL INFORMATION
               dataOK=validateFinDtls(OwnDtls);
               break;
            case 6:// New Account ACCOUNT RELATIONSHIP DETAILS
               dataOK=validateRelDtls(OwnDtls);
               break;
            case 7:// New Account TRADING HISTORY/OBJECTIVES
               dataOK=validateObjDtls(OwnDtls);
               break;
            default:
               break;
         }
      }
      else
      {
         switch (pagenum)
         {
            case 0:// Existing Account ACCOUNT HOLDER
               dataOK = validateAcctHldr1(OwnDtls, dtPriHldrDob);
               break;
            case 1:// Existing Account TAX RESIDENCE INFORMATION
               dataOK = validateTaxMnPnl(OwnDtls);
               break;
            default:
               break;
         }

      }
      return dataOK;
   }

   public Boolean saveDetails(int pagenum, String isNewForm, boolean isJoint)
   {
      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);
      OwnerDetails ownDtls=null;
      if (!isJoint)
      {
         ownDtls=uobDataMaster.getIndividualOwnersDetails();
      }else{
         ownDtls=uobDataMaster.getJointOwnersDetails();
      }
      if (isNewForm.equalsIgnoreCase("NO"))
      {
         switch (pagenum)
         {
            case 0:// New Account ACCOUNT HOLDER
               if (!isJoint)
               {
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                  uobDataMaster.getIndividualOwnersDetails().setDob(sdf.format(dtPriHldrDob));
                  saveAcctHldrDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, "", ownDtls);
               }
               else
               {
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                  uobDataMaster.getIndividualOwnersDetails().setDob(sdf.format(dtPriHldrDob));
                  saveAcctHldrDtls(uobDataMaster.getAccountDetails().getAcctnum(), 2, "", ownDtls);
               }
               break;
            case 1:// New Account ADDRESS
               saveAddrDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, "", ownDtls);
               break;
            case 2:// New Account TAX RESIDENCE INFORMATION
               break;
            case 3:// New Account EMPLOYMENT
               saveEmpDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, "", ownDtls);
               break;
            case 4:// New Account SECURITY QUESTION
//               saveSecDtls(ownDtls); Need changes for joint
               saveSecDtls( uobDataMaster.getAccountDetails().getAcctnum(),  1, ownDtls);
               break;
            case 5:// New Account FINANCIAL INFORMATION
               saveFinDtls( uobDataMaster.getAccountDetails().getAcctnum(),  1, ownDtls);
               break;
            case 6:// New Account ACCOUNT RELATIONSHIP DETAILS
               saveRelDtls( uobDataMaster.getAccountDetails().getAcctnum(),  1, ownDtls);
               break;
            case 7:// New Account TRADING HISTORY/OBJECTIVES
               saveObjDtls( uobDataMaster.getAccountDetails().getAcctnum(),  1, ownDtls);
               break;
            default:
               break;
         }
      }
      else
      {
         switch (pagenum)
         {
            case 0:// Existing Account ACCOUNT HOLDER
               if (!isJoint)
               {
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                  uobDataMaster.getIndividualOwnersDetails().setDob(sdf.format(dtPriHldrDob));
                  saveAcctHldrDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, "", ownDtls);
               }
               else
               {
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                  uobDataMaster.getIndividualOwnersDetails().setDob(sdf.format(dtPriHldrDob));
                  saveAcctHldrDtls(uobDataMaster.getAccountDetails().getAcctnum(), 2, "", ownDtls);
               }
               break;
            case 1:// Existing Account TAX RESIDENCE INFORMATION
               break;
            default:
               break;
         }

      }
      return dataOK;
   }

   public boolean saveAcctHldrDtls(Long acctNum, int acctOwnerId, String p_logonId, OwnerDetails ownerDetails)
   {
      custodyService.saveAccountHolderDtls(acctNum, acctOwnerId, p_logonId, ownerDetails);
//      uobCustodyBean.uobDataMaster.individualOwnersDetails.ownerContactDetails
      saveAdditionalDtls(ownerDetails.getOwnerContactDetails(), acctNum, acctOwnerId, "ao_owners_contact_details");
      saveAdditionalDtls(ownerDetails.getOwnerCitizenshipDetails(), acctNum, acctOwnerId, "ao_owners_citizenship_details");
      saveAdditionalDtls(ownerDetails.getOwnerIdentificationDetails(), acctNum, acctOwnerId, "ao_owners_indentification_details");


      return true;
   }
   public boolean saveEmpDtls(Long acctNum, int acctOwnerId, String p_logonId, OwnerDetails ownerDetails)
   {
      if (hasRequiredData(priHldrEmpAddr))
      {
         AddressSplitter addressSplitter = new AddressSplitter();

         String[] addrdtls = addressSplitter.addressSplitter(priHldrEmpAddr, 3, 40);

         ownerDetails.getOwnerEmploymentDetails().setEmployerStreetAddress1(addrdtls[0]);
         ownerDetails.getOwnerEmploymentDetails().setEmployerStreetAddress2(addrdtls[1]);
         ownerDetails.getOwnerEmploymentDetails().setEmployerStreetAddress3(addrdtls[2]);
      }

      custodyService.saveEmploymentDtls(acctNum, acctOwnerId, p_logonId, ownerDetails);
      saveAdditionalDtls(ownerDetails.getOwnerMiscDetails(), acctNum, acctOwnerId, "ao_owners_misc_details");
      return true;
   }

   public boolean saveAddrDtls(Long acctNum, int acctOwnerId, String p_logonId, OwnerDetails ownerDetails)
   {
//      priHldrPhyAddr=getCstmaPhyAddr(uobDataMaster.getIndividualOwnersDetails());
//      priHldrMlAddr=getCstmaMalAddr(uobDataMaster.getIndividualOwnersDetails());
      if (hasRequiredData(priHldrPhyAddr))
      {
         AddressSplitter addressSplitter = new AddressSplitter();

         String[] addrdtls = addressSplitter.addressSplitter(priHldrPhyAddr, 3, 40);

         ownerDetails.setPhysicalAddressStreet1(addrdtls[0]);
         ownerDetails.setPhysicalAddressStreet2(addrdtls[1]);
         ownerDetails.setPhysicalAddressStreet3(addrdtls[2]);
      }
      if (hasRequiredData(priHldrMlAddr))
      {
         AddressSplitter addressSplitter = new AddressSplitter();

         String[] addrdtls = addressSplitter.addressSplitter(priHldrMlAddr, 3, 40);

         ownerDetails.setMailingAddressStreet1(addrdtls[0]);
         ownerDetails.setMailingAddressStreet2(addrdtls[1]);
         ownerDetails.setMailingAddressStreet3(addrdtls[2]);
      }
      custodyService.saveAddressDtls(acctNum, acctOwnerId, p_logonId, ownerDetails);
      saveAdditionalDtls(ownerDetails.getOwnerMiscDetails(), acctNum, acctOwnerId, "ao_owners_misc_details");
      return true;
   }

   public boolean saveSecDtls(Long acctNum, int acctOwnerId,OwnerDetails ownerDetails)
   {
      saveAdditionalDtls(ownerDetails.getOwnerMiscDetails(), acctNum, acctOwnerId, "ao_owners_misc_details");
      return true;
   }

   public boolean saveFinDtls(Long acctNum, int acctOwnerId,OwnerDetails ownerDetails)
   {
      saveAdditionalDtls(ownerDetails.getOwnersFinancialDetails(), acctNum, acctOwnerId, "ao_owners_finacial_details");
      return true;
   }

   public boolean saveRelDtls(Long acctNum, int acctOwnerId,OwnerDetails ownerDetails)
   {
      saveAdditionalDtls(ownerDetails.getOwnerRegularityDetails(), acctNum, acctOwnerId, "ao_owners_regularity_details");
      return true;
   }

   public boolean saveObjDtls(Long acctNum, int acctOwnerId,OwnerDetails ownerDetails)
   {
      saveAdditionalDtls(ownerDetails.getOwnersFinancialDetails(), acctNum, acctOwnerId, "ao_owners_finacial_details");
      return true;
   }

   public void saveAdditionalDtls(Object obj, Long acctNum, int acctOwnerId, String table)
   {
      try
      {
         Map<String, Object> obMap = getFieldNames(obj, false);
         for (Map.Entry<String, Object> entry : obMap.entrySet())
         {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (value == null)
            {
               custodyService.saveAdditionalDtls(acctNum, acctOwnerId, name, null, table);
            }
            else
            {
               custodyService.saveAdditionalDtls(acctNum, acctOwnerId, name, value.toString(), table);
            }
         }

      }
      catch (Exception e)
      {

      }
   }

   public Boolean validateAcctHldr1(OwnerDetails ownerDetails, Date dob)
   {
      Boolean dataOK = true;
      if (ownerDetails.getFullName() == null || ownerDetails.getFullName().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.fullName", "Full Name is required!", null));
      }
      if (acctCat.equalsIgnoreCase("No"))
      {
         if (ownerDetails.getGender() == null || ownerDetails.getGender().equalsIgnoreCase("select"))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.gender", "Gender is required!", null));
         }
//      Pending for Date of birth
         if (dob == null)
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.dob", "Date of birth is required!", null));
         }
         if (ownerDetails.getCountryOfBirth() == null || ownerDetails.getCountryOfBirth().equalsIgnoreCase("select"))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryOfBrth", "Country of birth is required!", null));
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() == null ||
            ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.natioanlity", "Nationality is required!", null));
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (!ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Others")
               && (ownerDetails.getOwnerIdentificationDetails().getNric() == null ||
               ownerDetails.getOwnerIdentificationDetails().getNric().trim().equalsIgnoreCase("")))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.nric", "NRIC number is required!", null));
            }
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Others")
               && (ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify() == null ||
               ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify().equalsIgnoreCase("Select")))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.otrNatCtry", "Other nationality country is required!", null));
            }
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Others")
               && ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify() != null &&
               ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify().equalsIgnoreCase("Malaysia")
               && (ownerDetails.getOwnerIdentificationDetails().getIcno() == null ||
               ownerDetails.getOwnerIdentificationDetails().getIcno().trim().equalsIgnoreCase("")))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.malayIcNum", "Malaysia IC number is required is required!", null));
            }
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Others")
               && ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify() != null &&
               !ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify().equalsIgnoreCase("Malaysia")
               && (ownerDetails.getOwnerIdentificationDetails().getPassport() == null ||
               ownerDetails.getOwnerIdentificationDetails().getPassport().trim().equalsIgnoreCase("")))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.psprtNum", "Passport number is required is required!", null));
            }
         }
      }
      else
      {
         if (ownerDetails.getOwnerIdentificationDetails().getNric() == null ||
            ownerDetails.getOwnerIdentificationDetails().getNric().trim().equalsIgnoreCase(""))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.nric", "NRIC number is required!", null));
         }
      }

//      if (ownerDetails.getOwnerContactDetails().getHomeTelNumberCD() == null ||
//         ownerDetails.getOwnerContactDetails().getHomeTelNumberCD().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdHmNum", "Country code for home tel number is required!", null));
//      }
//      if (ownerDetails.getOwnerContactDetails().getHomeTelNumber() == null ||
//         ownerDetails.getOwnerContactDetails().getHomeTelNumber().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.hmNum", "Home tel number is required!", null));
//      }
//
      if (ownerDetails.getOwnerContactDetails().getMobNumberCD() == null ||
         ownerDetails.getOwnerContactDetails().getMobNumberCD().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdMblNum", "Country code for mobile number is required!", null));
      }

      if (ownerDetails.getOwnerContactDetails().getMobNumber() == null ||
         ownerDetails.getOwnerContactDetails().getMobNumber().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.mblNum", "Mobile number is required!", null));
      }

//      if (ownerDetails.getOwnerContactDetails().getOfficeTelNumberCD() == null ||
//         ownerDetails.getOwnerContactDetails().getOfficeTelNumberCD().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdOffNum", "Country code for office tel number is required!", null));
//      }
//
//      if (ownerDetails.getOwnerContactDetails().getOfficeTelNumber() == null ||
//         ownerDetails.getOwnerContactDetails().getOfficeTelNumber().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.offNum", "Office tel number is required!", null));
//      }

//      if (ownerDetails.getOwnerContactDetails().getFaxNumberCD() == null ||
//         ownerDetails.getOwnerContactDetails().getFaxNumberCD().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdFaxNum", "Country code for fax number is required!", null));
//      }
//
//      if (ownerDetails.getOwnerContactDetails().getFaxNumber() == null ||
//         ownerDetails.getOwnerContactDetails().getFaxNumber().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.faxNum", "Fax number is required!", null));
//      }

      if (!hasRequiredData(ownerDetails.getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.email.requiredMsg", "Email address is required!", null));
      }
      if (validateEmailPattern(ownerDetails.getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.emlAddrs", "Valid email address is required!", null));
      }
      System.out.println("validtion output " + dataOK);
      return dataOK;
   }

   public Boolean validateAcctHldr(OwnerDetails ownerDetails, Date dob)
   {
      Boolean dataOK = true;
      if (ownerDetails.getTitle() == null || ownerDetails.getTitle().equalsIgnoreCase("select"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.title", "Title is required!", null));
      }
      if (ownerDetails.getFullName() == null || ownerDetails.getFullName().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.fullName", "Full Name is required!", null));
      }
      if (acctCat.equalsIgnoreCase("No"))
      {
         if (ownerDetails.getGender() == null || ownerDetails.getGender().equalsIgnoreCase("select"))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.gender", "Gender is required!", null));
         }
//      Pending for Date of birth
         if (dob == null)
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.dob", "Date of birth is required!", null));
         }
         if (ownerDetails.getCountryOfBirth() == null || ownerDetails.getCountryOfBirth().equalsIgnoreCase("select"))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryOfBrth", "Country of birth is required!", null));
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() == null ||
            ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.natioanlity", "Nationality is required!", null));
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Singapore Pr")
               && ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify().trim().equalsIgnoreCase(""))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctry", "Country of nationality is required!", null));
            }
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (!ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Others")
               && (ownerDetails.getOwnerIdentificationDetails().getNric() == null ||
               ownerDetails.getOwnerIdentificationDetails().getNric().trim().equalsIgnoreCase("")))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.nric", "NRIC number is required!", null));
            }
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Others")
               && (ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify() == null ||
               ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify().trim().equalsIgnoreCase("")))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.otrNatCtry", "Other nationality country is required!", null));
            }
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Others")
               && ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify() != null &&
               ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify().equalsIgnoreCase("Malaysia")
               && (ownerDetails.getOwnerIdentificationDetails().getIcno() == null ||
               ownerDetails.getOwnerIdentificationDetails().getIcno().trim().equalsIgnoreCase("")))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.malayIcNum", "Malaysia IC number is required is required!", null));
            }
         }
         if (ownerDetails.getOwnerCitizenshipDetails().getNationality() != null &&
            !ownerDetails.getOwnerCitizenshipDetails().getNationality().trim().equalsIgnoreCase(""))
         {
            if (ownerDetails.getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Others")
               && ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify() != null &&
               !ownerDetails.getOwnerCitizenshipDetails().getNationalitySpecify().equalsIgnoreCase("Malaysia")
               && (ownerDetails.getOwnerIdentificationDetails().getPassport() == null ||
               ownerDetails.getOwnerIdentificationDetails().getPassport().trim().equalsIgnoreCase("")))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.psprtNum", "Passport number is required is required!", null));
            }
         }
      }
      else
      {
         if (ownerDetails.getOwnerIdentificationDetails().getNric() == null ||
            ownerDetails.getOwnerIdentificationDetails().getNric().trim().equalsIgnoreCase(""))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.nric", "NRIC number is required!", null));
         }
      }

//      if (ownerDetails.getOwnerContactDetails().getHomeTelNumberCD() == null ||
//         ownerDetails.getOwnerContactDetails().getHomeTelNumberCD().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdHmNum", "Country code for home tel number is required!", null));
//      }
//      if (ownerDetails.getOwnerContactDetails().getHomeTelNumber() == null ||
//         ownerDetails.getOwnerContactDetails().getHomeTelNumber().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.hmNum", "Home tel number is required!", null));
//      }
//
      if (ownerDetails.getOwnerContactDetails().getMobNumberCD() == null ||
         ownerDetails.getOwnerContactDetails().getMobNumberCD().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdMblNum", "Country code for mobile number is required!", null));
      }

      if (ownerDetails.getOwnerContactDetails().getMobNumber() == null ||
         ownerDetails.getOwnerContactDetails().getMobNumber().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.mblNum", "Mobile number is required!", null));
      }

//      if (ownerDetails.getOwnerContactDetails().getOfficeTelNumberCD() == null ||
//         ownerDetails.getOwnerContactDetails().getOfficeTelNumberCD().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdOffNum", "Country code for office tel number is required!", null));
//      }
//
//      if (ownerDetails.getOwnerContactDetails().getOfficeTelNumber() == null ||
//         ownerDetails.getOwnerContactDetails().getOfficeTelNumber().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.offNum", "Office tel number is required!", null));
//      }

//      if (ownerDetails.getOwnerContactDetails().getFaxNumberCD() == null ||
//         ownerDetails.getOwnerContactDetails().getFaxNumberCD().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdFaxNum", "Country code for fax number is required!", null));
//      }
//
//      if (ownerDetails.getOwnerContactDetails().getFaxNumber() == null ||
//         ownerDetails.getOwnerContactDetails().getFaxNumber().trim().equalsIgnoreCase(""))
//      {
//         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.faxNum", "Fax number is required!", null));
//      }

      if (!hasRequiredData(ownerDetails.getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.email.requiredMsg", "Email address is required!", null));
      }
      if (validateEmailPattern(ownerDetails.getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.emlAddrs", "Valid email address is required!", null));
      }
      System.out.println("validtion output " + dataOK);
      return dataOK;
   }

   public boolean validateSecDtls(OwnerDetails ownerDetails){
      Boolean dataOK = true;
      if (ownerDetails.getOwnerMiscDetails().getMotherMaidenName() == null || ownerDetails.getOwnerMiscDetails().getMotherMaidenName().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.momNm", "Mother's Maiden Name is required!", null));
      }
      if (ownerDetails.getOwnerMiscDetails().getFatherName() == null || ownerDetails.getOwnerMiscDetails().getFatherName().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.dadNm", "Father's Name is required!", null));
      }
      if (ownerDetails.getOwnerMiscDetails().getNameOfPrimarySchool() == null || ownerDetails.getOwnerMiscDetails().getNameOfPrimarySchool().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.sclNm", "Name of Primary School Attend is required!", null));
      }
      return  dataOK;
   }

   public boolean validateTaxMnPnl(OwnerDetails ownerDetails){
      Boolean dataOK = true;
      if (ownerDetails.getOwnerTaxationDetails()==null || ownerDetails.getOwnerTaxationDetails().size()==0)
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.txtMnPnl", "Taxation detail is required!", null));
      }

      return  dataOK;
   }

   public boolean validateEmpDtls(OwnerDetails ownerDetails){
      Boolean dataOK = true;
      if (!hasRequiredData(ownerDetails.getOwnerEmploymentDetails().getEmplTypeId()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.empStat", "Employment status is required!", null));
      }

      if (!hasRequiredData(ownerDetails.getOwnerMiscDetails().getQualifications()) || ownerDetails.getOwnerMiscDetails().getQualifications().equalsIgnoreCase("Select"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.eduQual", "Education qualification is required!", null));
      }
      if (ownerDetails.getOwnerMiscDetails().getQualifications()!=null && ownerDetails.getOwnerMiscDetails().getQualifications().equalsIgnoreCase("Others")
         && !hasRequiredData(ownerDetails.getOwnerMiscDetails().getQualificationsSpecify()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.eduQualOtr", "Other qualification detail is required!", null));
      }

      if(hasRequiredData(ownerDetails.getOwnerEmploymentDetails().getEmplTypeId()) && !ownerDetails.getOwnerEmploymentDetails().getEmplTypeId().equalsIgnoreCase("UnEmployed"))
      {
         if (!hasRequiredData(ownerDetails.getOwnerEmploymentDetails().getOccupation()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.empOcc", "Occupation is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getOwnerEmploymentDetails().getEmployerName()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.empNm", "Name of Employer is required!", null));
         }
         if(!hasRequiredData(priHldrEmpAddr)){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.empAddr", "Employer Address is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getOwnerEmploymentDetails().getEmployerZipCode()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.empPoCd", "Postal code is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getOwnerEmploymentDetails().getEmployerCity()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.empCty", "City/State is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getOwnerEmploymentDetails().getEmployerZipCountry()) ||ownerDetails.getOwnerEmploymentDetails().getEmployerZipCountry().trim().equalsIgnoreCase(""))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.empCtry", "Country is required!", null));
         }
      }

      return  dataOK;
   }

   public boolean validateAddrDtls(OwnerDetails ownerDetails)
   {
      Boolean dataOK = true;
      if (!hasRequiredData(priHldrPhyAddr))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.phyAddr", "Residential Address is required!", null));
      }
      if (!hasRequiredData(ownerDetails.getPhysicalAddressZipCode()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.phyPoCd", "Residential postal code is required!", null));
      }

      if (!hasRequiredData(ownerDetails.getPhysicalAddressCity()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.phyCty", "Residential City / State is required!", null));
      }
      if (!hasRequiredData(ownerDetails.getPhysicalAddressCountry()) || ownerDetails.getPhysicalAddressCountry().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.phyCtry", "Residential country is required!", null));
      }
      if (!hasRequiredData(ownerDetails.getOwnerMiscDetails().getMailAddressSameAsPhysical()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.malFlag", "Mailing address same as Residential address is required!", null));
      }

      if (ownerDetails.getOwnerMiscDetails().getMailAddressSameAsPhysical() != null && ownerDetails.getOwnerMiscDetails().getMailAddressSameAsPhysical().equalsIgnoreCase("No"))
      {
         if (!hasRequiredData(priHldrMlAddr))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.malAddr", "Mailing Address is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getMailingAddressZipCode()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.malPoCd", "Mailing postal code is required!", null));
         }

         if (!hasRequiredData(ownerDetails.getMailingAddressCity()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.malCty", "Mailing City / State is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getMailingAddressCountry()) || ownerDetails.getMailingAddressCountry().trim().equalsIgnoreCase(""))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.malCtry", "Mailing country is required!", null));
         }
      }
      return dataOK;
   }

   public boolean validateFinDtls(OwnerDetails ownerDetails){
      Boolean dataOK = true;
      if (ownerDetails.getOwnersFinancialDetails().getAnnualIncome() == null ||
         ownerDetails.getOwnersFinancialDetails().getAnnualIncome().equalsIgnoreCase("select"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.anlInc", "Mother's Maiden Name is required!", null));
      }
      if (ownerDetails.getOwnersFinancialDetails().getNetworth() == null ||
         ownerDetails.getOwnersFinancialDetails().getNetworth().equalsIgnoreCase("select"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.netWrth", "NetWorth is required!", null));
      }
      if (ownerDetails.getOwnersFinancialDetails().getSourceOfFundsWealth() == null ||
         ownerDetails.getOwnersFinancialDetails().getSourceOfFundsWealth().equalsIgnoreCase("select"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.src", "Source of Funds / Wealth is required!", null));
      }
      if (ownerDetails.getOwnersFinancialDetails().getSourceOfFundsWealth() != null &&
         ownerDetails.getOwnersFinancialDetails().getSourceOfFundsWealth().equalsIgnoreCase("Others") &&
         (ownerDetails.getOwnersFinancialDetails().getSourceOfFundsWealthSpecify()==null ||
            ownerDetails.getOwnersFinancialDetails().getSourceOfFundsWealthSpecify().trim().equalsIgnoreCase("")))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.srcOtrs", "Please specify Source of Funds/Weath!", null));
      }
      return  dataOK;
   }

   public boolean validateRelDtls(OwnerDetails ownerDetails){
      Boolean dataOK = true;
      if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getRelatedToAnyEmplfUOB()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.relChc",
                                                                                "Related to any employee / trading details are required!", null));
      }
      if (ownerDetails.getOwnerRegularityDetails().getRelatedToAnyEmplfUOB() != null &&
         ownerDetails.getOwnerRegularityDetails().getRelatedToAnyEmplfUOB().trim().equalsIgnoreCase("Yes"))
      {
         if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getRelatedToAnyEmplName1()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.relNm1",
                                                                                   "Name related to any employee / trading details is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getRelatedToAnyEmplRelation1()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.relNum1",
                                                                                   "Account Number related to any employee / trading details is required!", null));
         }
         if(hasRequiredData(ownerDetails.getOwnerRegularityDetails().getRelatedToAnyEmplName2()) &&
            !hasRequiredData(ownerDetails.getOwnerRegularityDetails().getRelatedToAnyEmplRelation2())){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.relNum1",
                                                                                   "Account Number related to any employee / trading details is required!", null));
         }
      }

      if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getControlOverUOBAcct()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.inflAct",
                                                                                "Details of trading account(s) in UOB Kay Hian in which you have control or influence is required!", null));
      }

      if (ownerDetails.getOwnerRegularityDetails().getControlOverUOBAcct() != null &&
         ownerDetails.getOwnerRegularityDetails().getControlOverUOBAcct().trim().equalsIgnoreCase("Yes"))
      {
         if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getControlOverUOBAcctName1()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.inflNm1",
                                                                                   "Name related to trading account(s) in UOB Kay Hian in which you have control or influence is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getControlOverUOBAcctNumber1()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.inflNum1",
                                                                                   "Account Number related to trading account(s) in UOB Kay Hian in which you have control or influence is required!", null));
         }
         if(hasRequiredData(ownerDetails.getOwnerRegularityDetails().getControlOverUOBAcctName2()) &&
            !hasRequiredData(ownerDetails.getOwnerRegularityDetails().getControlOverUOBAcctNumber2())){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.inflNum2",
                                                                                   "Account Number related to trading account(s) in UOB Kay Hian in which you have control or influence is required!", null));
         }
      }


      if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcct()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctrlAct",
                                                                                "Details related account holder in UOB Kay Hian, who has control or influence over this trading account is required!", null));
      }

      if (ownerDetails.getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcct() != null &&
         ownerDetails.getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcct().trim().equalsIgnoreCase("Yes"))
      {
         if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcctName1()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctrlNm1",
                                                                                   "Name related to trading account(s) in UOB Kay Hian in which you have control or influence is required!", null));
         }
         if (!hasRequiredData(ownerDetails.getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcctNumber1()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctrlNum1",
                                                                                   "Account Number related to account holder in UOB Kay Hian, who has control or influence over this trading account is required!", null));
         }
         if(hasRequiredData(ownerDetails.getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcctName2()) &&
            !hasRequiredData(ownerDetails.getOwnerRegularityDetails().getUobAcctHolderControlOverTradeAcctNumber2())){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctrlNum2",
                                                                                   "Account Number related to account holder in UOB Kay Hian, who has control or influence over this trading account is required!", null));
         }
      }

      return  dataOK;
   }


   public boolean validateObjDtls(OwnerDetails ownerDetails){
      Boolean dataOK = true;
      if (!hasRequiredData(ownerDetails.getOwnersFinancialDetails().getPreviousInvestingExperience()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.preExp",
                                                                                "Previous experience in investing in financial product(s) is required!", null));
      }
      if (ownerDetails.getOwnersFinancialDetails().getPreviousInvestingExperience()!=null  &&
         ownerDetails.getOwnersFinancialDetails().getPreviousInvestingExperience().equalsIgnoreCase("Yes") &&
         !hasRequiredData(ownerDetails.getOwnersFinancialDetails().getPreviousInvestingExperienceSpecify()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.preExpSpf",
                                                                                "Please specify previous experience in investing in financial product(s) is required!", null));
      }
      if (!hasRequiredData(ownerDetails.getOwnersFinancialDetails().getInvestmentObjectives(),"Select"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.instObj",
                                                                                "Investment objective is required!", null));
      }
      if (ownerDetails.getOwnersFinancialDetails().getInvestmentObjectives()!=null &&
         ownerDetails.getOwnersFinancialDetails().getInvestmentObjectives().equalsIgnoreCase("Others") &&
         !hasRequiredData(ownerDetails.getOwnersFinancialDetails().getInvestmentObjectiveSpecify()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.instObjDtl",
                                                                                "Other investment objective detail is required!", null));
      }
      if (!hasRequiredData(ownerDetails.getOwnersFinancialDetails().getNameOfOtherFinancialInstitution()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.namFinIst",
                                                                                "Name of other financial institution is required!", null));
      }
      if (!hasRequiredData(ownerDetails.getOwnersFinancialDetails().getAreYouUnableToPayYouDebts()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.debt",
                                                                                "Debt detail is required!", null));
      }
      if ( ownerDetails.getOwnersFinancialDetails().getAreYouUnableToPayYouDebts()!=null &&
         ownerDetails.getOwnersFinancialDetails().getAreYouUnableToPayYouDebts().equalsIgnoreCase("Yes") &&
         !hasRequiredData(ownerDetails.getOwnersFinancialDetails().getAreYouUnableToPayYouDebtsDescribe()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.debtDsc",
                                                                                "Debt specification is required!", null));
      }
      if (!hasRequiredData(ownerDetails.getOwnersFinancialDetails().getDoYouHaveAnyDisputedAccount()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.disp",
                                                                                "Disputed account detail is required!", null));
      }
      if (ownerDetails.getOwnersFinancialDetails().getDoYouHaveAnyDisputedAccount()!=null  &&
         ownerDetails.getOwnersFinancialDetails().getDoYouHaveAnyDisputedAccount().equalsIgnoreCase("Yes") &&
         !hasRequiredData(ownerDetails.getOwnersFinancialDetails().getDoYouHaveAnyDisputedAccountDescribe()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.dispDsc",
                                                                                "Disputed account specification is required!", null));
      }
      return  dataOK;
   }



   public Boolean hasRequiredData(String value)
   {

      if (value == null || value.isEmpty())
      {
         return false;
      }

      return true;
   }

   public Boolean hasRequiredData(String element,String defValue)
   {

      if (element == null || element.isEmpty() || element.equalsIgnoreCase(defValue))
      {
         return false;
      }

      return true;
   }

   public String getErrorMessage(Integer pagenum)
   {
      return pagemanager.getErrorMessage(pagenum);
   }

   public Boolean isAccordianDisabled(Integer pageno)
   {
      if (pageno != null)
      {
         if (pageno > 0 && pageno < getPagemanager().getMaxNoofPages())
         {
            if (getPagemanager().getLastPageVisited() >= pageno)
            {
               return false;
            }
         }
      }
      return true;
   }

   private void resetActiveTab(Integer pagenum)
   {
//      Integer nextTab = pageControl(pagenum);
//      activeTab = nextTab;
   }

   public static Map<String, Object> getFieldNames(final Object obj, boolean publicOnly)
      throws IllegalArgumentException, IllegalAccessException
   {
      StringBuilder sb = new StringBuilder();
      Class<? extends Object> c1 = obj.getClass();
      Map<String, Object> lst = new HashMap<String, Object>();
      Field[] fields = c1.getDeclaredFields();
      for (int i = 0; i < fields.length; i++)
      {
         String name = fields[i].getName();
         if (publicOnly)
         {
            if (Modifier.isPublic(fields[i].getModifiers()))
            {
               Object value = fields[i].get(obj);
               lst.put(name, value);
//               lst.add(name);
            }
         }
         else
         {
            fields[i].setAccessible(true);
            Object value = fields[i].get(obj);
//            lst.add(name);
            lst.put(name, value);
         }
      }
      return lst;
   }

   public Boolean validateEmailPattern(String emailID)
   {

      final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
         "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
         "(\\.[A-Za-z]{2,})$";

      Pattern pattern;
      Matcher matcher;
      pattern = Pattern.compile(EMAIL_PATTERN);
      Boolean msg = false;
      if ((emailID == null) || (emailID.trim().equals("")) ||
         (emailID.indexOf('.') == -1) ||
         (emailID.indexOf('@') == -1))
      {
         msg = true;
      }

      matcher = pattern.matcher(emailID);
      if (!matcher.matches())
      {

         msg = true;
      }
      return msg;
   }
   public List<String> completeText(String query) {
      List<String> MySortStrings =new ArrayList<String>();
      if(query.length()>2)
      {
         for (int i = 0; i < countries.size(); i++)
         {
            if (countries.get(i).toLowerCase().contains(query.toLowerCase()))
            {
               MySortStrings.add(countries.get(i));
            }
         }
      }
      return MySortStrings;
   }

   public void updTaxDtls(OwnerTaxationDetails ownerTaxationDetails){
      try
      {
         System.out.println("updTaxDtls "+ownerTaxationDetails);
         owTaxDtls=ownerTaxationDetails;
         dsplPriHldrTaxTab = true;
         dsplPriHldrTaxDtl = true;
         onChngTin();
         onChngRsn();
         dispTaxAddBtn=false;
         dispTaxUpdBtn=true;
         disTaxBtn=true;
      }catch (Exception e){

      }
   }

   public void delTaxDtls(OwnerTaxationDetails ownerTaxationDetails){
      try
      {
         System.out.println("delTaxDtls "+ownerTaxationDetails);

         custodyService.deleteSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(),1,"Taxation");
         List<OwnerTaxationDetails>  lst=uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails();
         if(lst!=null && lst.size()>0)
         {
//            uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails().remove(ownerTaxationDetails.getId()-1);
            lst.remove(ownerTaxationDetails.getId()-1);
            for (int i = 0; i < lst.size(); i++)
            {
               OwnerTaxationDetails owTaxDtl = lst.get(i);
               if (hasRequiredData(owTaxDtl.getIsTINAvailable()))
               {
                  custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, i + 1, "Taxation", "isTINAvailable", owTaxDtl.getIsTINAvailable());
               }
               if (hasRequiredData(owTaxDtl.getJurisdictionOfTaxResidence()))
               {
                  custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, i + 1, "Taxation", "jurisdictionOfTaxResidence", owTaxDtl.getJurisdictionOfTaxResidence());
               }
               if (hasRequiredData(owTaxDtl.getTaxIdentificationNumber()))
               {
                  custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, i + 1, "Taxation", "taxIdentificationNumber", owTaxDtl.getTaxIdentificationNumber());
               }
               if (hasRequiredData(owTaxDtl.getTinUnavailableReason()))
               {
                  custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, i + 1, "Taxation", "tinUnavailableReason", owTaxDtl.getTinUnavailableReason());
               }
               if (hasRequiredData(owTaxDtl.getTinUnavailableValue()))
               {
                  custodyService.saveSetMiscDtls(uobDataMaster.getAccountDetails().getAcctnum(), 1, i + 1, "Taxation", "tinUnavailableValue", owTaxDtl.getTinUnavailableValue());
               }
            }
         }

//         dsplPriHldrTaxTab = true;
//         dsplPriHldrTaxDtl = false;
         owTaxDtls = new OwnerTaxationDetails();
         dsplPriHldrTaxTin=false;

         if (uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() != null &&
            uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails().size() > 0)
         {
            dsplPriHldrTaxTab = true;
            dsplPriHldrTaxDtl = false;
            disTaxBtn=false;
         }else if (uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails() == null ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerTaxationDetails().size() == 0)
         {
            dsplPriHldrTaxDtl = true;
            dsplPriHldrTaxTab = false;
         }
      }catch (Exception e){
      }
   }

   public void selectedTaxDtls(OwnerTaxationDetails ownerTaxationDetails){
      owTaxDtls=ownerTaxationDetails;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public long getBeanAcctNum()
   {
      return beanAcctNum;
   }

   public void setBeanAcctNum(long beanAcctNum)
   {
      this.beanAcctNum = beanAcctNum;
   }

   public CustodyService getCustodyService()
   {
      return custodyService;
   }

   public void setCustodyService(CustodyService custodyService)
   {
      this.custodyService = custodyService;
   }

   public boolean isDsplExtIndAcctCat()
   {
      return dsplExtIndAcctCat;
   }

   public void setDsplExtIndAcctCat(boolean dsplExtIndAcctCat)
   {
      this.dsplExtIndAcctCat = dsplExtIndAcctCat;
   }

   public boolean isDsplExtJntAcctCat()
   {
      return dsplExtJntAcctCat;
   }

   public void setDsplExtJntAcctCat(boolean dsplExtJntAcctCat)
   {
      this.dsplExtJntAcctCat = dsplExtJntAcctCat;
   }

   public String getAcctCat()
   {
      return acctCat;
   }

   public void setAcctCat(String acctCat)
   {
      this.acctCat = acctCat;
   }

   public boolean isDsplAcctTyp()
   {
      return dsplAcctTyp;
   }

   public void setDsplAcctTyp(boolean dsplAcctTyp)
   {
      this.dsplAcctTyp = dsplAcctTyp;
   }

   public String getExtAcctInd()
   {
      return extAcctInd;
   }

   public void setExtAcctInd(String extAcctInd)
   {
      this.extAcctInd = extAcctInd;
   }

   public String getExtAcctJnt()
   {
      return extAcctJnt;
   }

   public void setExtAcctJnt(String extAcctJnt)
   {
      this.extAcctJnt = extAcctJnt;
   }

   public boolean isDsplExtIndAcctInp()
   {
      return dsplExtIndAcctInp;
   }

   public void setDsplExtIndAcctInp(boolean dsplExtIndAcctInp)
   {
      this.dsplExtIndAcctInp = dsplExtIndAcctInp;
   }

   public boolean isDsplExtJntAcctInp()
   {
      return dsplExtJntAcctInp;
   }

   public void setDsplExtJntAcctInp(boolean dsplExtJntAcctInp)
   {
      this.dsplExtJntAcctInp = dsplExtJntAcctInp;
   }

   public String getClientAcctId()
   {
      return clientAcctId;
   }

   public void setClientAcctId(String clientAcctId)
   {
      this.clientAcctId = clientAcctId;
   }

   public String getSlsPrsnNm()
   {
      return slsPrsnNm;
   }

   public void setSlsPrsnNm(String slsPrsnNm)
   {
      this.slsPrsnNm = slsPrsnNm;
   }

   public String getSelIndAcctTyp()
   {
      return selIndAcctTyp;
   }

   public void setSelIndAcctTyp(String selIndAcctTyp)
   {
      this.selIndAcctTyp = selIndAcctTyp;
   }

   public String getSelJntAcctTyp()
   {
      return selJntAcctTyp;
   }

   public void setSelJntAcctTyp(String selJntAcctTyp)
   {
      this.selJntAcctTyp = selJntAcctTyp;
   }

   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }

   public void setPagemanager(PagesImpl pagemanager)
   {
      this.pagemanager = pagemanager;
   }

   public boolean isDspExtAcctPnl()
   {
      return dspExtAcctPnl;
   }

   public void setDspExtAcctPnl(boolean dspExtAcctPnl)
   {
      this.dspExtAcctPnl = dspExtAcctPnl;
   }

   public boolean isDspNewAcctPnl()
   {
      return dspNewAcctPnl;
   }

   public void setDspNewAcctPnl(boolean dspNewAcctPnl)
   {
      this.dspNewAcctPnl = dspNewAcctPnl;
   }

   public boolean isDspIntroAcctPnl()
   {
      return dspIntroAcctPnl;
   }

   public void setDspIntroAcctPnl(boolean dspIntroAcctPnl)
   {
      this.dspIntroAcctPnl = dspIntroAcctPnl;
   }

   public boolean isDsblSubmtBtn()
   {
      return dsblSubmtBtn;
   }

   public void setDsblSubmtBtn(boolean dsblSubmtBtn)
   {
      this.dsblSubmtBtn = dsblSubmtBtn;
   }

   public boolean isDspJntTab()
   {
      return dspJntTab;
   }

   public void setDspJntTab(boolean dspJntTab)
   {
      this.dspJntTab = dspJntTab;
   }

   public Integer getActiveTab()
   {
      return activeTab;
   }

   public void setActiveTab(Integer activeTab)
   {
      this.activeTab = activeTab;
   }

   public UOBDataMaster getUobDataMaster()
   {
      return uobDataMaster;
   }

   public void setUobDataMaster(UOBDataMaster uobDataMaster)
   {
      this.uobDataMaster = uobDataMaster;
   }

   public boolean isDsplNricInp()
   {
      return dsplNricInp;
   }

   public void setDsplNricInp(boolean dsplNricInp)
   {
      this.dsplNricInp = dsplNricInp;
   }

   public boolean isDsplOtrCntry()
   {
      return dsplOtrCntry;
   }

   public void setDsplOtrCntry(boolean dsplOtrCntry)
   {
      this.dsplOtrCntry = dsplOtrCntry;
   }

   public boolean isDsplIcNoInp()
   {
      return dsplIcNoInp;
   }

   public void setDsplIcNoInp(boolean dsplIcNoInp)
   {
      this.dsplIcNoInp = dsplIcNoInp;
   }

   public boolean isDsplPspNoInp()
   {
      return dsplPspNoInp;
   }

   public void setDsplPspNoInp(boolean dsplPspNoInp)
   {
      this.dsplPspNoInp = dsplPspNoInp;
   }

   public Date getDtPriHldrDob()
   {
      return dtPriHldrDob;
   }

   public void setDtPriHldrDob(Date dtPriHldrDob)
   {
      this.dtPriHldrDob = dtPriHldrDob;
   }

   public String getIntroError()
   {
      return introError;
   }

   public void setIntroError(String introError)
   {
      this.introError = introError;
   }

   public boolean isDsplSrcIncOtrs()
   {
      return dsplSrcIncOtrs;
   }

   public void setDsplSrcIncOtrs(boolean dsplSrcIncOtrs)
   {
      this.dsplSrcIncOtrs = dsplSrcIncOtrs;
   }

   public boolean isDsplNwPriRelDtl()
   {
      return dsplNwPriRelDtl;
   }

   public void setDsplNwPriRelDtl(boolean dsplNwPriRelDtl)
   {
      this.dsplNwPriRelDtl = dsplNwPriRelDtl;
   }

   public boolean isDsplNwPriInfDtl()
   {
      return dsplNwPriInfDtl;
   }

   public void setDsplNwPriInfDtl(boolean dsplNwPriInfDtl)
   {
      this.dsplNwPriInfDtl = dsplNwPriInfDtl;
   }

   public boolean isDsplNwPriCtrlDtl()
   {
      return dsplNwPriCtrlDtl;
   }

   public void setDsplNwPriCtrlDtl(boolean dsplNwPriCtrlDtl)
   {
      this.dsplNwPriCtrlDtl = dsplNwPriCtrlDtl;
   }

   public String getPriHldrEmpAddr()
   {
      return priHldrEmpAddr;
   }

   public void setPriHldrEmpAddr(String priHldrEmpAddr)
   {
      this.priHldrEmpAddr = priHldrEmpAddr;
   }

   public boolean isDsplNwPriEmpMnPnl()
   {
      return dsplNwPriEmpMnPnl;
   }

   public void setDsplNwPriEmpMnPnl(boolean dsplNwPriEmpMnPnl)
   {
      this.dsplNwPriEmpMnPnl = dsplNwPriEmpMnPnl;
   }

   public boolean isDsplNwPriEmpOtrDtlPnl()
   {
      return dsplNwPriEmpOtrDtlPnl;
   }

   public void setDsplNwPriEmpOtrDtlPnl(boolean dsplNwPriEmpOtrDtlPnl)
   {
      this.dsplNwPriEmpOtrDtlPnl = dsplNwPriEmpOtrDtlPnl;
   }

   public String getPriHldrPhyAddr()
   {
      return priHldrPhyAddr;
   }

   public void setPriHldrPhyAddr(String priHldrPhyAddr)
   {
      this.priHldrPhyAddr = priHldrPhyAddr;
   }

   public String getPriHldrMlAddr()
   {
      return priHldrMlAddr;
   }

   public void setPriHldrMlAddr(String priHldrMlAddr)
   {
      this.priHldrMlAddr = priHldrMlAddr;
   }

   public boolean isDsplPriHldrMlPnl()
   {
      return dsplPriHldrMlPnl;
   }

   public void setDsplPriHldrMlPnl(boolean dsplPriHldrMlPnl)
   {
      this.dsplPriHldrMlPnl = dsplPriHldrMlPnl;
   }

   public boolean isDsplPriHldrObjPnl1()
   {
      return dsplPriHldrObjPnl1;
   }

   public void setDsplPriHldrObjPnl1(boolean dsplPriHldrObjPnl1)
   {
      this.dsplPriHldrObjPnl1 = dsplPriHldrObjPnl1;
   }

   public boolean isDsplPriHldrObjPnl2()
   {
      return dsplPriHldrObjPnl2;
   }

   public void setDsplPriHldrObjPnl2(boolean dsplPriHldrObjPnl2)
   {
      this.dsplPriHldrObjPnl2 = dsplPriHldrObjPnl2;
   }

   public boolean isDsplPriHldrObjPnl3()
   {
      return dsplPriHldrObjPnl3;
   }

   public void setDsplPriHldrObjPnl3(boolean dsplPriHldrObjPnl3)
   {
      this.dsplPriHldrObjPnl3 = dsplPriHldrObjPnl3;
   }

   public boolean isDsplPriHldrObjPnl4()
   {
      return dsplPriHldrObjPnl4;
   }

   public void setDsplPriHldrObjPnl4(boolean dsplPriHldrObjPnl4)
   {
      this.dsplPriHldrObjPnl4 = dsplPriHldrObjPnl4;
   }

   public OwnerTaxationDetails getOwTaxDtls()
   {
      return owTaxDtls;
   }

   public void setOwTaxDtls(OwnerTaxationDetails owTaxDtls)
   {
      this.owTaxDtls = owTaxDtls;
   }

   public boolean isDsplPriHldrTaxTab()
   {
      return dsplPriHldrTaxTab;
   }

   public void setDsplPriHldrTaxTab(boolean dsplPriHldrTaxTab)
   {
      this.dsplPriHldrTaxTab = dsplPriHldrTaxTab;
   }

   public boolean isDsplPriHldrTaxDtl()
   {
      return dsplPriHldrTaxDtl;
   }

   public void setDsplPriHldrTaxDtl(boolean dsplPriHldrTaxDtl)
   {
      this.dsplPriHldrTaxDtl = dsplPriHldrTaxDtl;
   }

   public boolean isDsplPriHldrTaxRsn()
   {
      return dsplPriHldrTaxRsn;
   }

   public void setDsplPriHldrTaxRsn(boolean dsplPriHldrTaxRsn)
   {
      this.dsplPriHldrTaxRsn = dsplPriHldrTaxRsn;
   }

   public boolean isDsplPriHldrTaxTin()
   {
      return dsplPriHldrTaxTin;
   }

   public void setDsplPriHldrTaxTin(boolean dsplPriHldrTaxTin)
   {
      this.dsplPriHldrTaxTin = dsplPriHldrTaxTin;
   }

   public boolean isDsplPriHldrTaxRsnPnl()
   {
      return dsplPriHldrTaxRsnPnl;
   }

   public void setDsplPriHldrTaxRsnPnl(boolean dsplPriHldrTaxRsnPnl)
   {
      this.dsplPriHldrTaxRsnPnl = dsplPriHldrTaxRsnPnl;
   }

   public String getTaxError()
   {
      return taxError;
   }

   public void setTaxError(String taxError)
   {
      this.taxError = taxError;
   }

   public Map<String, Country> getCountryDetails()
   {
      return countryDetails;
   }

   public void setCountryDetails(Map<String, Country> countryDetails)
   {
      this.countryDetails = countryDetails;
   }

   public List<String> getCountries()
   {
      return countries;
   }

   public void setCountries(List<String> countries)
   {
      this.countries = countries;
   }

   public boolean isDsplSingNricInp()
   {
      return dsplSingNricInp;
   }

   public void setDsplSingNricInp(boolean dsplSingNricInp)
   {
      this.dsplSingNricInp = dsplSingNricInp;
   }

   public boolean isDisTaxBtn()
   {
      return disTaxBtn;
   }

   public void setDisTaxBtn(boolean disTaxBtn)
   {
      this.disTaxBtn = disTaxBtn;
   }

   public boolean isDispTaxAddBtn()
   {
      return dispTaxAddBtn;
   }

   public void setDispTaxAddBtn(boolean dispTaxAddBtn)
   {
      this.dispTaxAddBtn = dispTaxAddBtn;
   }

   public boolean isDispTaxUpdBtn()
   {
      return dispTaxUpdBtn;
   }

   public void setDispTaxUpdBtn(boolean dispTaxUpdBtn)
   {
      this.dispTaxUpdBtn = dispTaxUpdBtn;
   }
}
