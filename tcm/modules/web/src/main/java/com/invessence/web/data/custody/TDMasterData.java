package com.invessence.web.data.custody;

import java.io.Serializable;
import java.util.*;

import com.invessence.web.constant.USMaps;
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

   Boolean ownerSPF, ownerShare, ownerBD;
   Boolean jointSPF, jointShare, jointBD;
   Integer fundType;
   Boolean fundNow, recurringFlag;
   Double initialInvestment;

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
   ElectronicFundDetails electroicBankDetail;
   FedwireAcctDetails fedwireAcctDetail;
   InternalTransferDetails internalTransferDetail;
   ArrayList<BenefiaciaryDetails> benefiaciaryDetailsList;
   BenefiaciaryDetails tmpBenefiaciaryDetail;

   public TDMasterData()
   {

      acctnum = null;
      accttype = 0; // 1 - Individual , 2 Number of joint acct.
      jointhasDifferent = acctholderhasMailing = jointhasMailing = false;
      ownerSPF = ownerShare = ownerBD = false;
      jointSPF = jointShare = jointBD = false;
      showBeneficiaryForm = false;
      newBeneficiaryForm = false;
      fundNow = false;
      recurringFlag = false;
      fundType = 0;
      initialInvestment = null;
      usmaps = USMaps.getInstance();

      request = new Request();
      acctdetail = new Acctdetails();
      advisorDetails = new AdvisorDetails();
      acctOwnersDetail = new AcctOwnersDetails();
      jointAcctOwnersDetail = new AcctOwnersDetails();
      owneremploymentDetail = new EmploymentDetails ();
      jointEmploymentDetail= new EmploymentDetails ();
      benefiaciaryDetailsList = new ArrayList<BenefiaciaryDetails>();
      mapMovemoneyPaymethod = new MapMovemoneyPaymethod();
      achBankDetail = new AchBankDetail();
      acatDetails = new ACATDetails();
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
         request = new Request(null, acctnum);
         acctdetail = new Acctdetails(acctnum);
         acctOwnersDetail = new AcctOwnersDetails(acctnum, 1, "AOPRIMARY");
         jointAcctOwnersDetail = new AcctOwnersDetails(acctnum, 2, "AOJOINT");
         owneremploymentDetail = new EmploymentDetails(acctnum, 1, 1);
         jointEmploymentDetail = new EmploymentDetails(acctnum, 2, 1);
      }

   }

   public Long getAcctnum()
   {
      return acctnum;
   }


   public Boolean getIsJointAcct() {
      if (accttype == 2) {
         return true;
      }
      return false;
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

   public Integer getFundType()
   {
      return fundType;
   }

   public void setFundType(Integer fundType)
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

   public void setAccttype(Integer accttype)
   {
      this.accttype = accttype;
      if (accttype != null) {
         switch (accttype) {
            case 0:
               acctdetail.setAcctTypeId("ACCSTD");
               break;
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
               acctdetail.setAcctTypeId("ACIRA");
               break;
            case 5:
               acctdetail.setAcctTypeId("ACIRA");
               break;
            case 6:
               acctdetail.setAcctTypeId("ACIRA");
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

   public void saveBenefiaciaryDetails() {
      if (benefiaciaryDetailsList == null) {
         benefiaciaryDetailsList = new ArrayList<BenefiaciaryDetails>();
      }

      if (tmpBenefiaciaryDetail == null)
         return;

      if (tmpBenefiaciaryDetail.getBeneId() == null) {
         tmpBenefiaciaryDetail.setBeneId(benefiaciaryDetailsList.size());
      }
      benefiaciaryDetailsList.add(tmpBenefiaciaryDetail);
      showBeneficiaryForm = false;
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
      tmpBenefiaciaryDetail = new BenefiaciaryDetails(acctnum, beneNum+1);
   }

   public void setSelectedBeneficiary(BenefiaciaryDetails thisBenefificiary) {
      tmpBenefiaciaryDetail = thisBenefificiary;
   }

   public void editBeneficiary() {
      showBeneficiaryForm = true;
   }

   public void cancelEditBeneficiary() {
      showBeneficiaryForm = false;
   }

   public void deleteBeneficiary(Integer beneID) {
      if (beneID == null)
         return;

      if (beneID > benefiaciaryDetailsList.size())
         return;

      benefiaciaryDetailsList.remove(beneID.intValue());
      for (int i=0; i < benefiaciaryDetailsList.size(); i++) {
         benefiaciaryDetailsList.get(i).setBeneId(i);  // Reset all seq#.
      }
   }
}
