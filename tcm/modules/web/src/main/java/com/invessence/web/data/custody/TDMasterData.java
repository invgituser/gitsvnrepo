package com.invessence.web.data.custody;

import java.io.Serializable;
import java.util.*;

import com.invessence.web.bean.custody.TdCto;
import com.invessence.web.constant.USMaps;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.data.custody.td.*;
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

   Boolean ownerSPF, senoirPolitical, ownerShare, ownerBD;
   Boolean jointSPF, jointShare, jointBD;
   String fundType;
   Boolean fundNow, recurringFlag;
   Double initialInvestment;
   Integer totalbeneficiaryShares,tmptottalShares;

   private USMaps usmaps;
   // TD information
   Request request;
   Acctdetails acctdetail;
   AdvisorDetails advisorDetails;
   AcctOwnersDetails acctOwnersDetail;
   AcctOwnersDetails jointAcctOwnersDetail;
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

   CustomerData customerData;

   public TDMasterData(Long acctnum)
   {

      this.acctnum = acctnum;

      customerData = new CustomerData();
      accttype = 0; // 1 - Individual , 2 Number of joint acct.acctholderhasMailing
      jointhasDifferent = acctholderhasMailing = jointhasMailing = false;
      //ownerSPF=false;
      senoirPolitical=false;
      ownerShare = ownerBD = false;
      jointSPF = jointShare = jointBD = false;
      showBeneficiaryForm = false;
      newBeneficiaryForm = false;
      fundNow = false;
      recurringFlag = false;
      fundType = "PMACH";
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
      if (accttype == 2 || accttype == 3 ) {
         return true;
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

   public Boolean getJointhasMailing()
   {
      return jointhasMailing;
   }

   public void setJointhasMailing(Boolean jointhasMailing)
   {
      this.jointhasMailing = jointhasMailing;
   }

   public Boolean getJointhasDifferent()
   {
      return jointhasDifferent;
   }

   public void setJointhasDifferent(Boolean jointhasDifferent)
   {
      this.jointhasDifferent = jointhasDifferent;
   }

   public Boolean getOwnerSPF()
   {
      return ownerSPF;
   }

   public void setOwnerSPF(Boolean ownerSPF)
   {
      this.ownerSPF = ownerSPF;
   }

   public Boolean getSenoirPolitical()
   {
      return senoirPolitical;
   }

   public void setSenoirPolitical(Boolean senoirPolitical)
   {
      this.senoirPolitical = senoirPolitical;
   }

   public Boolean getOwnerShare()
   {
      return ownerShare;
   }

   public void setOwnerShare(Boolean ownerShare)
   {
      this.ownerShare = ownerShare;
   }

   public Boolean getOwnerBD()
   {
      return ownerBD;
   }

   public void setOwnerBD(Boolean ownerBD)
   {
      this.ownerBD = ownerBD;
   }

   public Boolean getJointSPF()
   {
      return jointSPF;
   }

   public void setJointSPF(Boolean jointSPF)
   {
      this.jointSPF = jointSPF;
   }

   public Boolean getJointShare()
   {
      return jointShare;
   }

   public void setJointShare(Boolean jointShare)
   {
      this.jointShare = jointShare;
   }

   public Boolean getJointBD()
   {
      return jointBD;
   }

   public void setJointBD(Boolean jointBD)
   {
      this.jointBD = jointBD;
   }

   public Boolean getFundNow()
   {
      return fundNow;
   }

   public void setFundNow(Boolean fundNow)
   {
      this.fundNow = fundNow;
   }

   public Boolean getRecurringFlag()
   {
      return recurringFlag;
   }

   public void setRecurringFlag(Boolean recurringFlag)
   {
      this.recurringFlag = recurringFlag;
   }

   public String getFundType()
   {
      return fundType;
   }

   public void setFundType(String fundType)
   {
      this.fundType = fundType;
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
         tmpBenefiaciaryDetail = new BenefiaciaryDetails(acctnum, beneNum+1);
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
      editBeneficiaryForm=false;
   }

   public void existingAccount() {
      if(ownerSPF  && getFundType().equalsIgnoreCase("PMACH"))
      {
         System.out.println("Checked");

      }
      else
      {
         System.out.println("Uncheck");
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

      benefiaciaryDetailsList.remove(thisBeneficiary.getBeneId().intValue());
      if (benefiaciaryDetailsList.size() > 0) {
         totalbeneficiaryShares = 0;
         for (int i=0; i < benefiaciaryDetailsList.size(); i++) {
            totalbeneficiaryShares += benefiaciaryDetailsList.get(i).getSharePerc().intValue();
            benefiaciaryDetailsList.get(i).setBeneId(i+1);  // Reset all seq#.
         }
      }
      else {
         totalbeneficiaryShares = null;
      }
   }

   public Boolean getEditBeneficiaryForm()
   {
      return editBeneficiaryForm;
   }

   public void setEditBeneficiaryForm(Boolean editBeneficiaryForm)
   {
      this.editBeneficiaryForm = editBeneficiaryForm;
   }
}
