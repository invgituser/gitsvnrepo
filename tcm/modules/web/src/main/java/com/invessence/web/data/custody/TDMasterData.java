package com.invessence.web.data.custody;

import java.util.ArrayList;

import com.invessence.converter.SQLData;
import com.invessence.web.constant.USMaps;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/13/16
 * Time: 10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class TDMasterData
{
   Long acctnum;
   Integer accttype; // 1 - Individual , 2 joint acct.
   Boolean acctholderhasMailing, jointhasMailing, jointhasDifferent;
   Boolean ownerSPF, ownerShare, ownerBD;
   Boolean jointSPF, jointShare, jointBD;
   Integer fundType;
   Boolean recurringFlag;
   Double initialInvestment;

   private USMaps usmaps;
   // TD information
   TDRequest request;
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

   public TDMasterData()
   {
      acctnum = null;
      accttype = 5; // 0 - Individual , 1+ Number of joint acct.
      jointhasDifferent = acctholderhasMailing = jointhasMailing = false;
      ownerSPF = ownerShare = ownerBD = false;
      jointSPF = jointShare = jointBD = false;
      recurringFlag = false;
      fundType = 0;
      initialInvestment = null;
      usmaps = USMaps.getInstance();

      request = new TDRequest();
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
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public String getCheckedImage(Integer which)
   {
      String defaultImage = "/javax.faces.resource/images/checkedN.png.xhtml?ln=tcm";
      String selectedImage = "/javax.faces.resource/images/checkedY.png.xhtml?ln=tcm";
      which = (which == null) ? 0 : which;
      if (accttype != null) {
         if (accttype == which) {
             return selectedImage;
         }
      }
      return defaultImage;

   }

   public Boolean getIsJointAcct() {
      if (accttype == 5) {
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
