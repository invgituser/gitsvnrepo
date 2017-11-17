package com.invessence.web.bean.custody;

import java.lang.reflect.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.custody.uob.UOBDataMaster;
import com.invessence.web.service.custody.CustodyService;
import com.invessence.web.util.Impl.PagesImpl;
import com.invessence.web.util.WebUtil;
import org.apache.commons.collections.iterators.ObjectArrayIterator;
import org.apache.commons.logging.*;
import com.invessence.custody.uob.data.OwnerDetails;

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
   private boolean dsplNricInp = false, dsplOtrCntry = false, dsplIcNoInp = false, dsplPspNoInp = false;
   private  Date dtPriHldrDob;
   String introError;

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
            dspIntroAcctPnl = true;
            dsblSubmtBtn = true;
            dspJntTab = false;
            uobDataMaster = custodyService.fetch(getBeanAcctNum(),false);
            uobDataMaster.getAccountDetails().setAcctnum(getBeanAcctNum());
            onChngNation();
            onChngOtrCntry();
            loadIntroPage();

         if(uobDataMaster.getIndividualOwnersDetails().getDob()==null ||
            uobDataMaster.getIndividualOwnersDetails().getDob().equalsIgnoreCase("") ){
            dtPriHldrDob=new Date();
//            SimpleDateFormat dt1 = new SimpleDateFormat("d MMM yyyy");
//            dtPriHldrDob=dt1.format(dtPriHldrDob);
         }else{
//            DateFormat df = new SimpleDateFormat("d MMM yyyy");
//            dtPriHldrDob = df.parse(uobDataMaster.getIndividualOwnersDetails().getDob());
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
//            Date date = new Date();
            dtPriHldrDob = df.parse(uobDataMaster.getIndividualOwnersDetails().getDob());
//            SimpleDateFormat dt1 = new SimpleDateFormat("d MMM yyyy");
//            dtPriHldrDob = dt1.format(date);
         }
//            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//            Date date = new Date();
//         date = df.parse("");
//            SimpleDateFormat dt1 = new SimpleDateFormat("d MMM yyyy");
//            dtPriHldrDob=dt1.format(date);
//            System.out.println("dd "+dt1.format(date));
//            // clear all data.
//            resetBaseTD();
//
//            setPagemanager(new PagesImpl(10));
//            setTdMasterData(new TDMasterData(getPagemanager(), getLongBeanacctnum()));
//            String loadVal = loadData();
//            Boolean check = false;
//
//            // If account is managed
//            if (loadVal.equalsIgnoreCase("ACCOUNTMANAGED"))
//            {
//               msgheader = "dctd.103";
//               getWebutil().redirecttoMessagePage("ERROR", "Access Denied", msgheader);
//               return;
//            }
//
//            if (!loadVal.equalsIgnoreCase("success"))
//            {
//               // msgheader = "dctd.102";
//               // webutil.redirecttoMessagePage("ERROR", "Access Denied", msgheader);
//               getWebutil().accessdenied();
//               return;
//            }
//
//            // load firm list for ACAt details
//            loadACATFirmList();
//            // Check all data to find out where they left off last time.
//            // Just don't display error. Just go to that page.
//            // Start fresh, clean and start from top page.
//            Boolean status = validateAllPage();
//            Integer currentPage = getPagemanager().getPage();
//            getPagemanager().initPage();
//            resetActiveTab(0);
//            getPagemanager().setLastPageVisited(currentPage);
//            getPagemanager().clearAllErrorMessage();
//            saveandOpenError = null;
         }
      }
      catch (Exception ex)
      {
         logger.info("Exception: raised during Starting TD CTO process.");
      }
   }

   public void onChangeValue()
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
      }catch (Exception e){
         System.out.println("Error "+e);
         e.printStackTrace();
      }
   }
   public void loadIntroPage(){
      if(uobDataMaster.getAccountDetails().getAcctTypeId()!=null || uobDataMaster.getAccountDetails().getAcctTypeId()!="")
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

   public void onSelAcctTypAsJnt()
   {
      setSelIndAcctTyp(null);
      System.out.println("selIndAcctTyp " + selIndAcctTyp);
      System.out.println("selJntAcctTyp " + selJntAcctTyp);
   }

   public void introNextPage()
   {
      if(validateIntroPage())
      {
         custodyService.saveAcctDetails(uobDataMaster.getAccountDetails(),"12");
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

   public boolean validateIntroPage(){
      Boolean dataOK = true;
      StringBuilder sb=new StringBuilder();
      if (acctCat == null || acctCat== "")
      {
         dataOK = false;
//         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.acctType.exist", "Please select Existing securities trading account on UOBKH Yes/No!", null));
         sb.append("Please select Existing securities trading account on UOBKH Yes/No.<br/>");
      }
      if (acctCat != null && acctCat.equalsIgnoreCase("yes"))
      {
         if (extAcctInd == null || extAcctInd== "")
         {
            dataOK = false;
            sb.append("Please select Is it Individual Account Yes/No.<br/>");
         }
         if ((extAcctInd != null ||extAcctInd!="") && extAcctInd.equalsIgnoreCase("yes"))
         {
            if (uobDataMaster.getAccountDetails().getClientAccountID() == null || uobDataMaster.getAccountDetails().getClientAccountID().trim().equalsIgnoreCase(""))
            {
               dataOK = false;
               sb.append("Enter your existing  Securities Trading account number is required.<br/>");
            }
         }
         else if ((extAcctInd != null || extAcctInd!="") && extAcctInd.equalsIgnoreCase("No"))
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
      if ( uobDataMaster.getAccountDetails().getAcctTypeId() == null || uobDataMaster.getAccountDetails().getAcctTypeId()=="")
      {
         dataOK = false;
         sb.append("Type of account like to open is required.<br/>");
      }
      if(sb.length()>0){
         dataOK = false;
         introError=sb.toString();
      }else{
         dataOK = true;
         introError=null;
      }

      return dataOK;
   }
   public void nextPage()
   {
      System.out.println("page current" + getPagemanager().getPage());
      if(validate(getPagemanager().getPage(),getAcctCat(),false))
      {
         saveDetails(getPagemanager().getPage(),getAcctCat(),false);
         getPagemanager().nextPage();
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
         if (uobDataMaster.getIndividualOwnersDetails().getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Singapore") ||
            uobDataMaster.getIndividualOwnersDetails().getOwnerCitizenshipDetails().getNationality().equalsIgnoreCase("Singapore Pr"))
         {
            dsplNricInp = true;
            dsplOtrCntry = false;
            dsplIcNoInp = false;
            dsplPspNoInp = false;
         }
         else
         {
            dsplNricInp = false;
            dsplOtrCntry = true;
            dsplIcNoInp = false;
            dsplPspNoInp = true;
         }
      }catch(Exception e){}
   }

   public void onChngOtrCntry(){
      try{
      if(uobDataMaster.getIndividualOwnersDetails().getOwnerCitizenshipDetails().getNationalitySpecify().equalsIgnoreCase("malaysia")){
         dsplNricInp = false;
         dsplIcNoInp =true;
         dsplPspNoInp = false;
      }else{
         dsplNricInp = false;
         dsplIcNoInp =false;
         dsplPspNoInp = true;
      }
   }catch(Exception e){}
   }

   public Boolean validate(int pagenum ,String isNewForm,boolean isJoint){
      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);
      if(isNewForm.equalsIgnoreCase("NO"))
      {
         switch (pagenum)
         {
            case 0:// New Account ACCOUNT HOLDER
                  if(!isJoint)
                  {
                     dataOK = validateAcctHldr(uobDataMaster.getIndividualOwnersDetails(),dtPriHldrDob);
                  }else{
                     dataOK = validateAcctHldr(uobDataMaster.getJointOwnersDetails(),dtPriHldrDob);
                  }
               break;
            case 1:// New Account ADDRESS
               break;
            case 2:// New Account TAX RESIDENCE INFORMATION
               break;
            case 3:// New Account EMPLOYMENT
               break;
            case 4:// New Account SECURITY QUESTION
               break;
            case 5:// New Account FINANCIAL INFORMATION
               break;
            case 6:// New Account ACCOUNT RELATIONSHIP DETAILS
               break;
            case 7:// New Account TRADING HISTORY/OBJECTIVES
               break;
            default:
               break;
         }
      }else{
         switch (pagenum)
         {
            case 0:// Existing Account ACCOUNT HOLDER
               if(!isJoint)
               {
                  dataOK = validateAcctHldr(uobDataMaster.getIndividualOwnersDetails(),dtPriHldrDob);
               }else{
                  dataOK = validateAcctHldr(uobDataMaster.getJointOwnersDetails(),dtPriHldrDob);
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

   public Boolean saveDetails(int pagenum ,String isNewForm,boolean isJoint){
      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);
      if(isNewForm.equalsIgnoreCase("NO"))
      {
         switch (pagenum)
         {
            case 0:// New Account ACCOUNT HOLDER
               if(!isJoint)
               {
//                  DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                  uobDataMaster.getIndividualOwnersDetails().setDob(sdf.format(dtPriHldrDob));
                  saveAcctHldrDtls(uobDataMaster.getAccountDetails().getAcctnum(),1,"",uobDataMaster.getIndividualOwnersDetails());
               }else{
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                  uobDataMaster.getIndividualOwnersDetails().setDob(sdf.format(dtPriHldrDob));
                  saveAcctHldrDtls(uobDataMaster.getAccountDetails().getAcctnum(),2,"",uobDataMaster.getJointOwnersDetails());
               }
               break;
            case 1:// New Account ADDRESS
               break;
            case 2:// New Account TAX RESIDENCE INFORMATION
               break;
            case 3:// New Account EMPLOYMENT
               break;
            case 4:// New Account SECURITY QUESTION
               break;
            case 5:// New Account FINANCIAL INFORMATION
               break;
            case 6:// New Account ACCOUNT RELATIONSHIP DETAILS
               break;
            case 7:// New Account TRADING HISTORY/OBJECTIVES
               break;
            default:
               break;
         }
      }else{
         switch (pagenum)
         {
            case 0:// Existing Account ACCOUNT HOLDER
               if(!isJoint)
               {
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                  uobDataMaster.getIndividualOwnersDetails().setDob(sdf.format(dtPriHldrDob));
                  saveAcctHldrDtls(uobDataMaster.getAccountDetails().getAcctnum(),1,"",uobDataMaster.getIndividualOwnersDetails());
               }else{
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                  uobDataMaster.getIndividualOwnersDetails().setDob(sdf.format(dtPriHldrDob));
                  saveAcctHldrDtls(uobDataMaster.getAccountDetails().getAcctnum(),2,"",uobDataMaster.getJointOwnersDetails());
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

   public boolean saveAcctHldrDtls(Long acctNum, int acctOwnerId , String p_logonId, OwnerDetails ownerDetails){
      custodyService.saveAccountHolderDtls(acctNum, acctOwnerId, p_logonId, ownerDetails);
//      uobCustodyBean.uobDataMaster.individualOwnersDetails.ownerContactDetails
      saveAdditionalDtls(ownerDetails.getOwnerContactDetails(),acctNum,acctOwnerId,"ao_owners_contact_details");
      saveAdditionalDtls(ownerDetails.getOwnerCitizenshipDetails(),acctNum,acctOwnerId,"ao_owners_citizenship_details");
      saveAdditionalDtls(ownerDetails.getOwnerIdentificationDetails(),acctNum,acctOwnerId,"ao_owners_indentification_details");


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

   public Boolean validateAcctHldr(OwnerDetails ownerDetails,Date dob) {
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
            &&(ownerDetails.getOwnerIdentificationDetails().getIcno()==null ||
            ownerDetails.getOwnerIdentificationDetails().getIcno().trim().equalsIgnoreCase("") ))
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
            &&(ownerDetails.getOwnerIdentificationDetails().getPassport()==null ||
            ownerDetails.getOwnerIdentificationDetails().getPassport().trim().equalsIgnoreCase("") ))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.psprtNum", "Passport number is required is required!", null));
         }
      }


      if (ownerDetails.getOwnerContactDetails().getHomeTelNumberCD() == null ||
         ownerDetails.getOwnerContactDetails().getHomeTelNumberCD().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdHmNum", "Country code for home tel number is required!", null));
      }
      if (ownerDetails.getOwnerContactDetails().getHomeTelNumber() == null ||
         ownerDetails.getOwnerContactDetails().getHomeTelNumber().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.hmNum", "Home tel number is required!", null));
      }

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

      if (ownerDetails.getOwnerContactDetails().getOfficeTelNumberCD() == null ||
         ownerDetails.getOwnerContactDetails().getOfficeTelNumberCD().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdOffNum", "Country code for office tel number is required!", null));
      }

      if (ownerDetails.getOwnerContactDetails().getOfficeTelNumber() == null ||
         ownerDetails.getOwnerContactDetails().getOfficeTelNumber().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.offNum", "Office tel number is required!", null));
      }

      if (ownerDetails.getOwnerContactDetails().getFaxNumberCD() == null ||
         ownerDetails.getOwnerContactDetails().getFaxNumberCD().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.ctryCdFaxNum", "Country code for fax number is required!", null));
      }

      if (ownerDetails.getOwnerContactDetails().getFaxNumber() == null ||
         ownerDetails.getOwnerContactDetails().getFaxNumber().trim().equalsIgnoreCase(""))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.faxNum", "Fax number is required!", null));
      }
      if (hasRequiredData(ownerDetails.getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.email.requiredMsg", "Email address is required!", null));
      }
      if (validateEmailPattern(ownerDetails.getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.new.pri.acctHldr.emlAddrs", "Valid email address is required!", null));
      }
      System.out.println("validtion output "+dataOK);
      return dataOK;
   }

   public Boolean hasRequiredData(String value)
   {

      if (value == null || value.isEmpty())
         return false;

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

   public static Map<String,Object> getFieldNames(final Object obj, boolean publicOnly)
      throws IllegalArgumentException, IllegalAccessException
   {
      StringBuilder sb = new StringBuilder();
      Class<? extends Object> c1 = obj.getClass();
      Map<String,Object> lst = new HashMap<String,Object>();
      Field[] fields = c1.getDeclaredFields();
      for (int i = 0; i < fields.length; i++)
      {
         String name = fields[i].getName();
         if (publicOnly)
         {
            if (Modifier.isPublic(fields[i].getModifiers()))
            {
               Object value = fields[i].get(obj);
               lst.put(name,value);
//               lst.add(name);
            }
         }
         else
         {
            fields[i].setAccessible(true);
            Object value = fields[i].get(obj);
//            lst.add(name);
            lst.put(name,value);
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
}
