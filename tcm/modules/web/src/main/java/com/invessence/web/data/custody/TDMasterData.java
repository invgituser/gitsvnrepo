package com.invessence.web.data.custody;

import java.io.Serializable;
import java.util.ArrayList;
import com.invessence.web.bean.custody.*;
import com.invessence.converter.SQLData;
import com.invessence.web.constant.USMaps;
import com.invessence.web.dao.custody.CustodySaveDAO;

import javax.faces.bean.*;
import javax.faces.event.ActionEvent;

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
   Boolean addBeneficiary;

   Boolean ownerSPF, ownerShare, ownerBD;
   Boolean jointSPF, jointShare, jointBD;
   Integer fundType;
   Boolean recurringFlag;
   Double initialInvestment;

   private USMaps usmaps;
   // TD information
   TDRequest request;
   TDAcctdetails  acctdetail;
   AdvisorDetails advisorDetails;
   AcctOwnersDetails acctOwnersDetail;
   AcctOwnersDetails jointAcctOwnersDetail;
   EmploymentDetails owneremploymentDetail;
   EmploymentDetails jointEmploymentDetail;
   BenefiaciaryDetails benefiaciaryDetailses;
   MapMovemoneyPaymethod mapMovemoneyPaymethod;
   AchBankDetail achBankDetail;
   ElectronicFundDetails electroicBankDetail;
   FedwireAcctDetails fedwireAcctDetail;
   InternalTransferDetails internalTransferDetail;
   BenefiaciaryDetails benefiaciaryDetails;

   public TDMasterData()
   {

      acctnum = null;
      accttype = 0; // 1 - Individual , 2 Number of joint acct.
      jointhasDifferent = acctholderhasMailing = jointhasMailing = false;
      ownerSPF = ownerShare = ownerBD = false;
      jointSPF = jointShare = jointBD = false;
      addBeneficiary = false;
      recurringFlag = false;
      fundType = 0;
      initialInvestment = null;
      usmaps = USMaps.getInstance();

      request = new TDRequest();
      acctdetail = new TDAcctdetails();
      advisorDetails = new AdvisorDetails();
      acctOwnersDetail = new AcctOwnersDetails();
      jointAcctOwnersDetail = new AcctOwnersDetails();
      owneremploymentDetail = new EmploymentDetails ();
      jointEmploymentDetail= new EmploymentDetails ();
      benefiaciaryDetailses = new BenefiaciaryDetails();
      mapMovemoneyPaymethod = new MapMovemoneyPaymethod();
      achBankDetail = new AchBankDetail();
      electroicBankDetail = new ElectronicFundDetails();
      fedwireAcctDetail = new FedwireAcctDetails();
      internalTransferDetail = new InternalTransferDetails();

   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
      benefiaciaryDetails.setAcctnum(this.acctnum);

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

   public TDRequest getRequest()
   {
      return request;
   }

   public void setRequest(TDRequest request)
   {
      this.request = request;
   }

   public TDAcctdetails getAcctdetail()
   {
      return acctdetail;
   }

   public void setAcctdetail(TDAcctdetails acctdetail)
   {
      this.acctdetail = acctdetail;
   }

   public void setAccttype(Integer accttype)
   {
      this.accttype = accttype;
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

   public Boolean getAddBeneficiary()
   {
      return addBeneficiary;
   }

   public void setAddBeneficiary(Boolean addBeneficiary)
   {
      this.addBeneficiary = addBeneficiary;
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

}
