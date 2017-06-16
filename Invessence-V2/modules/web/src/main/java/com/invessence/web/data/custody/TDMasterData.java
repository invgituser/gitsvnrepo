package com.invessence.web.data.custody;

import java.io.Serializable;
import java.util.*;

import com.invessence.web.bean.custody.TdCto;
import com.invessence.web.constant.USMaps;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.data.custody.td.*;
import com.invessence.web.util.Impl.PagesImpl;
import org.primefaces.event.RowEditEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/13/16
 * Time: 10:31 PM
 * To change this template use File | Settings | File Templates.
 */

public class TDMasterData implements Serializable
{
   private static final long serialVersionUID = 1L;
   Long acctnum;
   Integer accttype; // 1 - Individual , 2 joint acct.
   Boolean acctholderhasMailing, jointhasMailing, jointhasDifferent;
   Boolean showBeneficiaryForm;
   Boolean newBeneficiaryForm;
   Boolean editBeneficiaryForm=false;

   Boolean senoirPolitical, ownerShare, ownerBD;
   Boolean jointSPF, jointShare, jointBD;
   String fundType;
   Boolean fundNow, recurringFlag,optFund;
   Boolean isNewRecurring;
   Double initialInvestment;
   Integer totalbeneficiaryShares,tmptottalShares;
   private Boolean optoutRegulatory,optoutBeneficiary,optoutFunding,optoutRecurring;
   Boolean copyAchInstructions;

   private USMaps usmaps;
   // TD information
   Request request;
   Acctdetails acctdetail;
   AdvisorDetails advisorDetails;
   AcctOwnersDetails acctOwnersDetail;
   AcctOwnersDetails jointAcctOwnersDetail;
   AcctOwnersDetails acctOwnersDetailHistory;
   AcctOwnersDetails jointAcctOwnersDetailHistory;
   EmploymentDetails owneremploymentDetail;
   EmploymentDetails jointEmploymentDetail;
   BenefiaciaryDetails benefiaciaryDetailses;
   MapMovemoneyPaymethod mapMovemoneyPaymethod;
   AchBankDetail achBankDetail;
   ACATDetails acatDetails;
   TDTransferDetails tdTransferDetails;
   ElectronicFundDetails electroicBankDetail;
   FedwireAcctDetails fedwireAcctDetail;
   InternalTransferDetails internalTransferDetail;
   ArrayList<BenefiaciaryDetails> benefiaciaryDetailsList;
   BenefiaciaryDetails tmpBenefiaciaryDetail;
   Map<String,MastACATFirm> acatFirmMap;
   List<String> acatFirmList;


   CustomerData customerData;
   PagesImpl pageMgr;

   Boolean submitButton=false;

   public TDMasterData(PagesImpl pm, Long acctnum)
   {

      this.acctnum = acctnum;
      this.pageMgr = pm;

      customerData = new CustomerData();
      accttype = 0; // 1 - Individual , 2 Number of joint acct.acctholderhasMailing
      jointhasDifferent = acctholderhasMailing = jointhasMailing = false;
      copyAchInstructions=false;
      optoutRegulatory=optoutBeneficiary=optoutFunding=optoutRecurring=false;
      senoirPolitical=false;
      ownerShare = ownerBD = false;
      jointSPF = jointShare = jointBD = false;
      showBeneficiaryForm = false;
      newBeneficiaryForm = false;
      fundNow = false;
      optFund=false;
      recurringFlag = false;
      fundType = "PMACH";
      isNewRecurring=true;
      initialInvestment = null;
      totalbeneficiaryShares = 0;
      tmptottalShares=0;
      usmaps = USMaps.getInstance();

      request = new Request();
      request = new Request(null, acctnum);
      acctdetail = new Acctdetails(acctnum);
      acctOwnersDetail = new AcctOwnersDetails(acctnum, 1, "AOPRIMARY");
      jointAcctOwnersDetail = new AcctOwnersDetails(acctnum, 2, "AOJOINT");
      owneremploymentDetail = new EmploymentDetails(acctnum, 1, 1);
      jointEmploymentDetail = new EmploymentDetails(acctnum, 2, 1);
      benefiaciaryDetailsList = new ArrayList<BenefiaciaryDetails>();
      mapMovemoneyPaymethod = new MapMovemoneyPaymethod();
      achBankDetail = new AchBankDetail();
      acatDetails = new ACATDetails();
      tdTransferDetails = new TDTransferDetails();
      electroicBankDetail = new ElectronicFundDetails();
      fedwireAcctDetail = new FedwireAcctDetails();
      internalTransferDetail = new InternalTransferDetails();
      tmpBenefiaciaryDetail = new BenefiaciaryDetails();
   }

   public void setAcctnum(Long acctnum)
   {
      if (acctnum != null) {
         if (this.acctnum != null && this.acctnum == acctnum)
            return;

         this.acctnum = acctnum;

      }

   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public CustomerData getCustomerData()
   {
      return customerData;
   }

   public void setCustomerData(CustomerData customerData)
   {
      this.customerData = customerData;
   }

   public Boolean getIsJointAcct() {
      if (accttype == 2 || accttype == 3) {
         if(accttype == 3 && this.pageMgr.getPage()== 6 || accttype == 3 && this.pageMgr.getPage()== 7){
            return false;
         }else {
            return true;
         }
      }
      return false;
   }

   public String getTitlePrimary() {
      if ( accttype == 3 ) {
         return "Custodian Holder";
      }
      return "Primary Holder";
   }
   public String getTitleSecondary() {
      if ( accttype == 3 ) {
         return "Minor Holder";
      }
      return "Joint Holder";
   }


   public Integer getTotalbeneficiaryShares()
   {
      return totalbeneficiaryShares;
   }

   public void setTotalbeneficiaryShares(Integer totalbeneficiaryShares)
   {
      this.totalbeneficiaryShares = totalbeneficiaryShares;
   }

   public Integer getTmptottalShares()
   {
      return tmptottalShares;
   }

   public void setTmptottalShares(Integer tmptottalShares)
   {
      this.tmptottalShares = tmptottalShares;
   }

   public Integer getAccttype()
   {
      return accttype;
   }

   public Boolean getAcctholderhasMailing()
   {
      return acctholderhasMailing;
   }

   public void setAcctholderhasMailing(Boolean acctholderhasMailing)
   {
      this.acctholderhasMailing = acctholderhasMailing;
   }

   public void resetAcctholderhasMailing()
   {
      if(!acctholderhasMailing)
      {
         acctOwnersDetail.setMailingAddressStreet("");
         acctOwnersDetail.setMailingAddressCity("");
         acctOwnersDetail.setMailingAddressState("");
         acctOwnersDetail.setMailingAddressZipCode("");
      }
   }

   public Boolean getJointhasMailing()
   {
      return jointhasMailing;
   }

   public void setJointhasMailing(Boolean jointhasMailing)
   {
      this.jointhasMailing = jointhasMailing;
   }

   public void resetJointhasMailing()
   {
      if(!jointhasMailing)
      {
         jointAcctOwnersDetail.setMailingAddressStreet("");
         jointAcctOwnersDetail.setMailingAddressCity("");
         jointAcctOwnersDetail.setMailingAddressState("");
         jointAcctOwnersDetail.setMailingAddressZipCode("");
      }
   }

   public Boolean getJointhasDifferent()
   {
      return jointhasDifferent;
   }

   public void setJointhasDifferent(Boolean jointhasDifferent)
   {
      this.jointhasDifferent = jointhasDifferent;
   }
   public void resetJointDifferentAddress()
   {
      if(!jointhasDifferent)
      {
         jointhasMailing = false;
         jointAcctOwnersDetail.setPhysicalAddressStreet("");
         jointAcctOwnersDetail.setPhysicalAddressCity("");
         jointAcctOwnersDetail.setPhysicalAddressState("");
         jointAcctOwnersDetail.setPhysicalAddressZipCode("");
         jointAcctOwnersDetail.setMailingAddressStreet("");
         jointAcctOwnersDetail.setMailingAddressCity("");
         jointAcctOwnersDetail.setMailingAddressState("");
         jointAcctOwnersDetail.setMailingAddressZipCode("");
      }
   }

   public Boolean getHasachinstruction()
   {
      // True if ACHDetail has routing instructions.
      return (getAchBankDetail() != null &&
              getAchBankDetail().getBankABARouting() != null &&
              ! getAchBankDetail().getBankABARouting().isEmpty())
      ;
   }

   public Boolean getCopyAchInstructions()
   {
      return copyAchInstructions;
   }

   public void setCopyAchInstructions(Boolean copyAchInstructions)
   {
      this.copyAchInstructions = copyAchInstructions;
   }

   public Boolean getSenoirPolitical()
   {
      return senoirPolitical;
   }

   public void setSenoirPolitical(Boolean senoirPolitical)
   {
      this.senoirPolitical = senoirPolitical;

   }
   public void resetSeniorPolitical()
   {
      if (!senoirPolitical) {
        // clearErrorMsg();
         acctOwnersDetail.setSpfDetail(null);
         acctOwnersDetail.setSPF(null);
         acctOwnersDetail.setSpfCountry(null);
         acctOwnersDetail.setSpfName(null);
         acctOwnersDetail.setSpfRelationship(null);
         acctOwnersDetail.setSpfTitle(null);
      }
   }

   public Boolean getOwnerShare()
   {
      return ownerShare;
   }

   public void setOwnerShare(Boolean ownerShare)
   {
      this.ownerShare = ownerShare;

   }

   public Boolean getNewRecurring()
   {
      return isNewRecurring;
   }

   public void setNewRecurring(Boolean newRecurring)
   {
      isNewRecurring = newRecurring;
   }

   public void resetContraFirmList()
   {
      String firmname=acatDetails.getContraFirmList();
      if(acatFirmMap.containsKey(firmname))
      {
         acatDetails.setFromFirmAddress(acatFirmMap.get(firmname).getAddress());
         acatDetails.setFromFirmPhoneNumber(acatFirmMap.get(firmname).getPhoneNumber());
      }
      else {
         acatDetails.setFromFirmAddress("");
         acatDetails.setFromFirmPhoneNumber("");
      }


   }
   public void resetoptoutBeneficiary()
   {
      if(optoutBeneficiary)
      {
         benefiaciaryDetailsList= new ArrayList<BenefiaciaryDetails>();
         tmpBenefiaciaryDetail=new BenefiaciaryDetails();
         totalbeneficiaryShares=0;
         tmptottalShares=0;
         showBeneficiaryForm=false;
         editBeneficiaryForm=false;
      }
   }
   public void resetoptoutRegulatory()
   {
      if(optoutRegulatory)
      {
         senoirPolitical=false;
         ownerBD=false;
         ownerShare=false;
         resetSeniorPolitical();
         resetOwnereBd();
         resetOwnerShare();
      }
   }
   public void resetOwnerShare()
   {
      if (!ownerShare) {
         //clearErrorMsg();
         acctOwnersDetail.setShareholderCompany(null);
         acctOwnersDetail.setShareholderAddress(null);
         acctOwnersDetail.setShareholderCity(null);
         acctOwnersDetail.setShareholderState(null);
         acctOwnersDetail.setDirectorShareholderDetail(null);
         acctOwnersDetail.setDirectorShareholder(null);
      }

   }

   public Boolean getOwnerBD()
   {
      return ownerBD;
   }

   public void setOwnerBD(Boolean ownerBD)
   {
      this.ownerBD = ownerBD;

   }

   public void resetOwnereBd()
   {
      if (!ownerBD) {
         clearErrorMsg();
         acctOwnersDetail.setBdDetail(null);
         acctOwnersDetail.setBd(null);
      }
   }
   public Boolean getJointSPF()
   {
      return jointSPF;
   }

   public void setJointSPF(Boolean jointSPF)
   {
      this.jointSPF = jointSPF;
      if (!jointSPF) {
         clearErrorMsg();
         jointAcctOwnersDetail.setSpfDetail(null);
         jointAcctOwnersDetail.setSPF(null);
         jointAcctOwnersDetail.setSpfCountry(null);
         jointAcctOwnersDetail.setSpfName(null);
         jointAcctOwnersDetail.setSpfRelationship(null);
         jointAcctOwnersDetail.setSpfTitle(null);
      }

   }

   public Boolean getJointShare()
   {
      return jointShare;
   }

   public void setJointShare(Boolean jointShare)
   {
      this.jointShare = jointShare;
      if (!jointShare) {
         clearErrorMsg();
         jointAcctOwnersDetail.setShareholderCompany(null);
         jointAcctOwnersDetail.setShareholderAddress(null);
         jointAcctOwnersDetail.setShareholderCity(null);
         jointAcctOwnersDetail.setShareholderState(null);
         jointAcctOwnersDetail.setDirectorShareholderDetail(null);
      }
   }

   public Boolean getJointBD()
   {
      return jointBD;
   }

   public void setJointBD(Boolean jointBD)
   {
      this.jointBD = jointBD;
      if (!jointBD) {
         clearErrorMsg();
         jointAcctOwnersDetail.setBdDetail(null);
         jointAcctOwnersDetail.setBd(null);
      }
   }

   public Boolean getFundNow()
   {
      return fundNow;
   }

   public void setFundNow(Boolean fundNow)
   {
      this.fundNow = fundNow;
     /* if (! fundNow) {
         setFundType(null);
         mapMovemoneyPaymethod = new MapMovemoneyPaymethod();
         achBankDetail = new AchBankDetail();
         acatDetails = new ACATDetails();
         tdTransferDetails = new TDTransferDetails();
      }*/
   }

   public Boolean getOptFund()
   {
      return optFund;
   }

   public void setOptFund(Boolean optFund)
   {
      this.optFund = optFund;
   }

   public Boolean getRecurringFlag()
   {
      return recurringFlag;
   }

   public void setRecurringFlag(Boolean recurringFlag)
   {
      this.recurringFlag = recurringFlag;

      /*if (!recurringFlag) {
         electroicBankDetail = new ElectronicFundDetails();
      }*/
   }

   public String getFundType()
   {
      return fundType;
   }

   public void setFundType(String fundType)
   {
      this.fundType = fundType;
   }

   public void resetFundNowData()
   {
      initialInvestment=null;
      achBankDetail=new AchBankDetail();
      this.acatDetails=new ACATDetails();
      tdTransferDetails=new TDTransferDetails();
      electroicBankDetail=new ElectronicFundDetails();

      if(fundNow)
      {
         copyAchInstructions=false;
         recurringFlag=true;
         submitButton = true;
         optoutFunding=true;
         optoutRecurring=true;
      }
      else
      {
         copyAchInstructions=false;
         recurringFlag=false;
         submitButton = false;
         optoutFunding=false;
         optoutRecurring=false;
      }
   }
   public void resetFundTypeData()
   {
         if(fundType.equals("PMACH"))
         {
            initialInvestment = getCustomerData().getInitialInvestment().doubleValue();
            acatDetails=new ACATDetails();
            tdTransferDetails=new TDTransferDetails();
         }
         else if(fundType.equals("PMFEDW"))
         {
            initialInvestment=null;
            achBankDetail=new AchBankDetail();
            tdTransferDetails=new TDTransferDetails();
         }
         else if(fundType.equals("TDTRF"))
         {
            initialInvestment=null;
            achBankDetail=new AchBankDetail();
            acatDetails=new ACATDetails();
         }
   }
   public void resetRetailFlag()
   {
      if(tdTransferDetails.getRetilFlag().equals(""))
      {
         tdTransferDetails.setPriorFirmName("");
         tdTransferDetails.setRetailAccountNumber("");
         tdTransferDetails.setAdvisorID("");
         tdTransferDetails.setFirmAccountNo("");
      }
      else if(tdTransferDetails.getRetilFlag().equals("Y"))
      {
         tdTransferDetails.setRetailAccountNumber("");
         tdTransferDetails.setAdvisorID("");
      }
      else if(tdTransferDetails.getRetilFlag().equals("N"))
      {
         tdTransferDetails.setPriorFirmName("");
         tdTransferDetails.setFirmAccountNo("");
      }

   }
   public void resetRecurringFlagData()
   {
      copyAchInstructions=false;
      if(recurringFlag)
      {
         submitButton=true;
         optoutRecurring=true;
      }
      else
      {
         submitButton=false;
         optoutRecurring=false;
      }
   }
   public Double getInitialInvestment()
   {
      return initialInvestment;
   }

   public void setInitialInvestment(Double initialInvestment)
   {
      this.initialInvestment = initialInvestment;
   }

   public USMaps getUsmaps()
   {
      return usmaps;
   }

   public Request getRequest()
   {
      return request;
   }

   public void setRequest(Request request)
   {
      this.request = request;
   }

   public Acctdetails getAcctdetail()
   {
      return acctdetail;
   }

   public void setAcctdetail(Acctdetails acctdetail)
   {
      this.acctdetail = acctdetail;
   }

   private void clearErrorMsg() {
      if (this.pageMgr != null) {
         pageMgr.clearAllErrorMessage();
      }
   }

   public void loadAcctType(String strtype) {
      if (strtype != null) {
         if (strtype.equalsIgnoreCase("ACINDIV"))
            accttype = 1;
         else if (strtype.equalsIgnoreCase("ACJOINT"))
            accttype = 2;
         else if (strtype.equalsIgnoreCase("ACCSTD"))
            accttype = 3;
         else if (strtype.equalsIgnoreCase("IRATRAD"))
            accttype = 4;
         else if (strtype.equalsIgnoreCase("IRAROTH"))
            accttype = 5;
         else if (strtype.equalsIgnoreCase("IRAROOV"))
            accttype = 6;
         else if (strtype.equalsIgnoreCase("IRABENE"))
            accttype = 7;
      }
   }

   public void setAccttype(Integer accttype)
   {
      this.accttype = accttype;
      if (accttype != null) {
         switch (accttype) {
            case 1:
               acctdetail.setAcctTypeId("ACINDIV");
               break;
            case 2:
               acctdetail.setAcctTypeId("ACJOINT");
               break;
            case 3:
               acctdetail.setAcctTypeId("ACCSTD");
               break;
            case 4:
               acctdetail.setAcctTypeId("IRATRAD");
               break;
            case 5:
               acctdetail.setAcctTypeId("IRAROTH");
               break;
            case 6:
               acctdetail.setAcctTypeId("IRAROOV");
               break;
            case 7:
               acctdetail.setAcctTypeId("IRABENE");
               break;
            default:
               acctdetail.setAcctTypeId("ACIRA");
         }
      }
   }

   public AdvisorDetails getAdvisorDetails()
   {
      return advisorDetails;
   }

   public void setAdvisorDetails(AdvisorDetails advisorDetails)
   {
      this.advisorDetails = advisorDetails;
   }

   public AcctOwnersDetails getAcctOwnersDetail()
   {
      return acctOwnersDetail;
   }

   public void setAcctOwnersDetail(AcctOwnersDetails acctOwnersDetail)
   {
      this.acctOwnersDetail = acctOwnersDetail;
   }

   public AcctOwnersDetails getJointAcctOwnersDetail()
   {
      return jointAcctOwnersDetail;
   }

   public void setJointAcctOwnersDetail(AcctOwnersDetails jointAcctOwnersDetail)
   {
      this.jointAcctOwnersDetail = jointAcctOwnersDetail;
   }

   public EmploymentDetails getOwneremploymentDetail()
   {
      return owneremploymentDetail;
   }

   public void setOwneremploymentDetail(EmploymentDetails owneremploymentDetail)
   {
      this.owneremploymentDetail = owneremploymentDetail;
   }

   public void resetOwnerEmployment()
   {
      if(owneremploymentDetail.getEmplTypeId().startsWith("EMPL") || owneremploymentDetail.getEmplTypeId().startsWith("SLFEMPL"))
      {
         owneremploymentDetail.setSourceOfIncome("");
      }
      else
      {
         owneremploymentDetail.setEmployerName("");
         owneremploymentDetail.setOccupation("");
         owneremploymentDetail.setTypeOfBusiness("");
         owneremploymentDetail.setEmployerStreetAddress("");
         owneremploymentDetail.setEmployerCity("");
         owneremploymentDetail.setEmployerState("");
         owneremploymentDetail.setEmployerZipCode("");
      }
   }
   public void resetJointEmployment()
   {
      if(jointEmploymentDetail.getEmplTypeId().startsWith("EMPL") || jointEmploymentDetail.getEmplTypeId().startsWith("SLFEMPL"))
      {
         jointEmploymentDetail.setSourceOfIncome("");
      }
      else
      {
         jointEmploymentDetail.setEmployerName("");
         jointEmploymentDetail.setOccupation("");
         jointEmploymentDetail.setTypeOfBusiness("");
         jointEmploymentDetail.setEmployerStreetAddress("");
         jointEmploymentDetail.setEmployerCity("");
         jointEmploymentDetail.setEmployerState("");
         jointEmploymentDetail.setEmployerZipCode("");
      }
   }
   public EmploymentDetails getJointEmploymentDetail()
   {
      return jointEmploymentDetail;
   }

   public void setJointEmploymentDetail(EmploymentDetails jointEmploymentDetail)
   {
      this.jointEmploymentDetail = jointEmploymentDetail;
   }

   public BenefiaciaryDetails getBenefiaciaryDetailses()
   {
      return benefiaciaryDetailses;
   }

   public void setBenefiaciaryDetailses(BenefiaciaryDetails benefiaciaryDetailses)
   {
      this.benefiaciaryDetailses = benefiaciaryDetailses;
   }

   public Boolean getShowBeneficiaryForm()
   {
      return showBeneficiaryForm;
   }

   public void setShowBeneficiaryForm(Boolean showBeneficiaryForm)
   {
      this.showBeneficiaryForm = showBeneficiaryForm;
   }

   public MapMovemoneyPaymethod getMapMovemoneyPaymethod()
   {
      return mapMovemoneyPaymethod;
   }

   public void setMapMovemoneyPaymethod(MapMovemoneyPaymethod mapMovemoneyPaymethod)
   {
      this.mapMovemoneyPaymethod = mapMovemoneyPaymethod;
   }

   public AchBankDetail getAchBankDetail()
   {
      return achBankDetail;
   }

   public void setAchBankDetail(AchBankDetail achBankDetail)
   {
      this.achBankDetail = achBankDetail;
   }

   public ACATDetails getAcatDetails()
   {
      return acatDetails;
   }

   public void setAcatDetails(ACATDetails acatDetails)
   {
      this.acatDetails = acatDetails;
   }

   public TDTransferDetails getTdTransferDetails()
   {
      return tdTransferDetails;
   }

   public void setTdTransferDetails(TDTransferDetails tdTransferDetails)
   {
      this.tdTransferDetails = tdTransferDetails;
   }

   public ElectronicFundDetails getElectroicBankDetail()
   {
      return electroicBankDetail;
   }

   public void setElectroicBankDetail(ElectronicFundDetails electroicBankDetail)
   {
      this.electroicBankDetail = electroicBankDetail;
   }

   public FedwireAcctDetails getFedwireAcctDetail()
   {
      return fedwireAcctDetail;
   }

   public void setFedwireAcctDetail(FedwireAcctDetails fedwireAcctDetail)
   {
      this.fedwireAcctDetail = fedwireAcctDetail;
   }

   public InternalTransferDetails getInternalTransferDetail()
   {
      return internalTransferDetail;
   }

   public void setInternalTransferDetail(InternalTransferDetails internalTransferDetail)
   {
      this.internalTransferDetail = internalTransferDetail;
   }

   public ArrayList<BenefiaciaryDetails> getBenefiaciaryDetailsList()
   {
      return benefiaciaryDetailsList;
   }

   public void setBenefiaciaryDetailsList(ArrayList<BenefiaciaryDetails> benefiaciaryDetailsList)
   {
      this.benefiaciaryDetailsList = benefiaciaryDetailsList;
   }

   public void saveBenefiaciaryDetails() {
      if (benefiaciaryDetailsList == null) {
         benefiaciaryDetailsList = new ArrayList<BenefiaciaryDetails>();
      }

      if (tmpBenefiaciaryDetail == null)
         return;

      if (tmpBenefiaciaryDetail.getBeneId() == null) {
         tmpBenefiaciaryDetail.setBeneId(benefiaciaryDetailsList.size());
      }
      ArrayList<BenefiaciaryDetails> tmpbenefiaciaryDetailsList=benefiaciaryDetailsList;

      if(editBeneficiaryForm)
      {
         for (int i=0; i < tmpbenefiaciaryDetailsList.size(); i++)
         {
            BenefiaciaryDetails tmpb=tmpbenefiaciaryDetailsList.get(i);
            if(tmpBenefiaciaryDetail.getBeneId().intValue()==tmpb.getBeneId().intValue())
               benefiaciaryDetailsList.remove(i);
         }

      }

      totalbeneficiaryShares = ((totalbeneficiaryShares == null) ? 0 : totalbeneficiaryShares) + tmpBenefiaciaryDetail.getSharePerc().intValue();
      tmptottalShares=totalbeneficiaryShares;
      benefiaciaryDetailsList.add(tmpBenefiaciaryDetail);
      showBeneficiaryForm = false;
      editBeneficiaryForm=false;
   }

   public BenefiaciaryDetails getTmpBenefiaciaryDetail()
   {
      return tmpBenefiaciaryDetail;
   }

   public Boolean getNewBeneficiaryForm()
   {
      return newBeneficiaryForm;
   }

   public void setNewBeneficiaryForm(Boolean newBeneficiaryForm)
   {
      this.newBeneficiaryForm = newBeneficiaryForm;
      this.showBeneficiaryForm = true;
      Integer beneNum = benefiaciaryDetailsList.size();
      if(editBeneficiaryForm)
         tmpBenefiaciaryDetail = new BenefiaciaryDetails(acctnum, tmpBenefiaciaryDetail.getBeneId());
      else
      {
         tmpBenefiaciaryDetail = new BenefiaciaryDetails(acctnum, beneNum + 1);
         tmpBenefiaciaryDetail.setSharePerc(100-totalbeneficiaryShares.longValue());
      }
   }

   public void setSelectedBeneficiary(BenefiaciaryDetails thisBenefificiary) {
      tmptottalShares=((totalbeneficiaryShares) - (thisBenefificiary.getSharePerc().intValue()));
      tmpBenefiaciaryDetail = thisBenefificiary;
   }

      public void editBeneficiary() {
         showBeneficiaryForm = true;
         editBeneficiaryForm=true;
      }

   public void addnewBeneficiary() {
      tmptottalShares=totalbeneficiaryShares;
      tmpBenefiaciaryDetail.setSharePerc(100-totalbeneficiaryShares.longValue());
      editBeneficiaryForm=false;
   }

   public void existingAccount() {
      if(copyAchInstructions  && getFundType().equalsIgnoreCase("PMACH"))
      {
         electroicBankDetail.setBankAcctType(achBankDetail.getBankAcctType());
         electroicBankDetail.setBankName(achBankDetail.getBankName());
         electroicBankDetail.setBankAcctName(achBankDetail.getBankAcctName());
         electroicBankDetail.setBankCityState(achBankDetail.getBankCityState());
         electroicBankDetail.setBankPhoneNumber(getAchBankDetail().getBankPhoneNumber());
         electroicBankDetail.setBankABARouting(getAchBankDetail().getBankABARouting());
         electroicBankDetail.setBankAcctNumber(getAchBankDetail().getBankAcctNumber());
      }
      else
      {
         electroicBankDetail.setBankAcctType("");
         electroicBankDetail.setBankName("");
         electroicBankDetail.setBankAcctName("");
         electroicBankDetail.setBankCityState("");
         electroicBankDetail.setBankPhoneNumber("");
         electroicBankDetail.setBankABARouting("");
         electroicBankDetail.setBankAcctNumber("");
         copyAchInstructions = false;
      }
   }

   public void cancelEditBeneficiary() {
      showBeneficiaryForm = false;
   }

   public void deleteBeneficiary(BenefiaciaryDetails thisBeneficiary) {
      if (thisBeneficiary == null)
         return;

      if (thisBeneficiary.getBeneId() > benefiaciaryDetailsList.size())
         return;

      if (benefiaciaryDetailsList.size() > 0) {
         totalbeneficiaryShares = 0;
         ListIterator listIterator = benefiaciaryDetailsList.listIterator();
         int i=0;
         while (listIterator.hasNext()) {
            listIterator.next();
            if (benefiaciaryDetailsList.get(i).getBeneId().equals(thisBeneficiary.getBeneId()) ) {
               listIterator.remove();
               break;
            }
            else {
               i++;
            }
         }

         for (i=0; i < benefiaciaryDetailsList.size(); i++) {
               totalbeneficiaryShares += benefiaciaryDetailsList.get(i).getSharePerc().intValue();
               benefiaciaryDetailsList.get(i).setBeneId(i+1);  // Reset all seq#.
         }
      }
      else {
         totalbeneficiaryShares = 0;
      }
      showBeneficiaryForm = false;
      editBeneficiaryForm=false;
   }

   public Boolean getEditBeneficiaryForm()
   {
      return editBeneficiaryForm;
   }

   public void setEditBeneficiaryForm(Boolean editBeneficiaryForm)
   {
      this.editBeneficiaryForm = editBeneficiaryForm;
   }

   public Boolean getSubmitButton()
   {
      return submitButton;
   }

   public void setSubmitButton(Boolean submitButton)
   {
      this.submitButton = submitButton;
   }

   public Boolean getOptoutRegulatory()
   {
      return optoutRegulatory;
   }

   public void setOptoutRegulatory(Boolean optoutRegulatory)
   {
      this.optoutRegulatory = optoutRegulatory;
   }

   public Boolean getOptoutBeneficiary()
   {
      return optoutBeneficiary;
   }

   public void setOptoutBeneficiary(Boolean optoutBeneficiary)
   {
      this.optoutBeneficiary = optoutBeneficiary;
   }

   public Boolean getOptoutFunding()
   {
      return optoutFunding;
   }

   public void setOptoutFunding(Boolean optoutFunding)
   {
      this.optoutFunding = optoutFunding;
   }

   public Boolean getOptoutRecurring()
   {
      return optoutRecurring;
   }

   public void setOptoutRecurring(Boolean optoutRecurring)
   {
      this.optoutRecurring = optoutRecurring;
   }

   public List<String> getAcatFirmList()
   {
      return acatFirmList;
   }

   public void setAcatFirmList(List<String> acatFirmList)
   {
      this.acatFirmList = acatFirmList;
   }

   public Map<String, MastACATFirm> getAcatFirmMap()
   {
      return acatFirmMap;
   }

   public void setAcatFirmMap(Map<String, MastACATFirm> acatFirmMap)
   {
      this.acatFirmMap = acatFirmMap;
   }

   public List<String> lookUpfirm(String enterString) {

      List<String> MySortStrings =new ArrayList<String>();
      if(enterString.length()>2)
      {
         for (int i = 0; i < acatFirmList.size(); i++)
         {
            if (acatFirmList.get(i).toLowerCase().contains(enterString.toLowerCase()))
            {
               MySortStrings.add(acatFirmList.get(i));
            }
         }
      }
      return MySortStrings;

   }

   public AcctOwnersDetails getJointAcctOwnersDetailHistory()
   {
      return jointAcctOwnersDetailHistory;
   }

   public void setJointAcctOwnersDetailHistory(AcctOwnersDetails jointAcctOwnersDetailHistory)
   {
      this.jointAcctOwnersDetailHistory = jointAcctOwnersDetailHistory;
   }

   public AcctOwnersDetails getAcctOwnersDetailHistory()
   {
      return acctOwnersDetailHistory;
   }

   public void setAcctOwnersDetailHistory(AcctOwnersDetails acctOwnersDetailHistory)
   {
      this.acctOwnersDetailHistory = acctOwnersDetailHistory;
   }
}
