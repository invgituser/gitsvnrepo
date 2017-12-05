package com.invessence.web.bean.custody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.converter.JavaUtil;
import com.invessence.web.constant.*;
import com.invessence.web.dao.common.CommonDAO;
import com.invessence.web.dao.consumer.ConsumerListDataDAO;
import com.invessence.web.dao.custody.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.data.custody.TDMasterData;
import com.invessence.web.data.custody.td.*;
import com.invessence.web.util.Impl.PagesImpl;
import com.invessence.web.util.*;
import com.invessence.ws.bean.*;
import com.invessence.web.service.docuSign.service.DCWebLayer;
import com.invessence.ws.service.ServiceLayerImpl;
import org.apache.commons.logging.*;
import org.primefaces.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/7/16
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */

public class BaseTD
{
   protected final Log logger = LogFactory.getLog(getClass());
   private String beanacctnum;
   private String beanlogonID;
   private TDMasterData tdMasterData = new TDMasterData(null, 0L);
   private PagesImpl pagemanager = new PagesImpl(11);
   private String defaultCheckedImage = "/javax.faces.resource/images/checkedN.png.xhtml?ln=tcm";
   private String selectedCheckedImage = "/javax.faces.resource/images/checkedY.png.xhtml?ln=tcm";

   private String saveandOpenError;
   private ArrayList<BenefiaciaryDetails> beneTempList = new ArrayList<BenefiaciaryDetails>();

   public TDMasterData getTdMasterData()
   {
      return tdMasterData;
   }

   public void setTdMasterData(TDMasterData tdMasterData)
   {
      this.tdMasterData = tdMasterData;
   }

   public Log getLogger()
   {
      return logger;
   }

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }
   public WebUtil getWebutil()
   {
      return webutil;
   }



   @ManagedProperty("#{uiLayout}")
   private UILayout uiLayout;
   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }
   public UILayout getUiLayout()
   {
      return uiLayout;
   }

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO consumerListDAO;
   public void setConsumerListDAO(ConsumerListDataDAO consumerListDAO)
   {
      this.consumerListDAO = consumerListDAO;
   }
   public ConsumerListDataDAO getConsumerListDAO()
   {
      return consumerListDAO;
   }

   @ManagedProperty("#{custodyListDAO}")
   private CustodyListDAO custodyListDAO;
   public void setCustodyListDAO(CustodyListDAO custodyListDAO)
   {
      this.custodyListDAO = custodyListDAO;
   }
   public CustodyListDAO getCustodyListDAO()
   {
      return custodyListDAO;
   }
   public void setListDAO(CustodyListDAO listDAO)
   {
      this.custodyListDAO = listDAO;
   }

   @ManagedProperty("#{dcWebLayer}")
   private DCWebLayer dcWebLayer;

   public DCWebLayer getDcWebLayer()
   {
      return dcWebLayer;
   }

   public void setDcWebLayer(DCWebLayer dcWebLayer)
   {
      this.dcWebLayer = dcWebLayer;
   }
   //   @ManagedProperty("#{serviceLayer}")
//   private ServiceLayerImpl serviceLayer;
//   public ServiceLayerImpl getServiceLayer()
//   {
//      return serviceLayer;
//   }
//   public void setServiceLayer(ServiceLayerImpl serviceLayer)
//   {
//      this.serviceLayer = serviceLayer;
//   }

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;
   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }
   public CommonDAO getCommonDAO()
   {
      return commonDAO;
   }

   @ManagedProperty("#{custodySaveDAO}")
   private CustodySaveDAO custodySaveDAO;
   public void setSaveDAO(CustodySaveDAO saveDAO)
   {
      this.custodySaveDAO = saveDAO;
   }
   public CustodySaveDAO getCustodySaveDAO()
   {
      return custodySaveDAO;
   }
   public void setCustodySaveDAO(CustodySaveDAO custodySaveDAO)
   {
      this.custodySaveDAO = custodySaveDAO;
   }

   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }
   public void setPagemanager(PagesImpl pagemanager)
   {
      this.pagemanager = pagemanager;
   }

   public String getDefaultCheckedImage()
   {
      return defaultCheckedImage;
   }
   public String getSelectedCheckedImage()
   {
      return selectedCheckedImage;
   }
   public String getBeanacctnum()
   {
      return beanacctnum;
   }
   public String getBeanlogonID()
   {
      return beanlogonID;
   }
   public void setBeanlogonID(String beanlogonID)
   {
      this.beanlogonID = beanlogonID;
   }
   public Long getLongBeanacctnum()
   {
      if (beanacctnum == null)
         return 0L;

      if (beanacctnum.isEmpty())
         return 0L;

      return (webutil.converter.getLongData(beanacctnum));
   }
   public Long getLongBeanlogonid()
   {
      if (beanlogonID == null)
         return 0L;

      if (beanlogonID.isEmpty())
         return 0L;

      return (webutil.converter.getLongData(beanlogonID));
   }

   public String getSaveandOpenError()
   {
      return saveandOpenError;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public ArrayList<BenefiaciaryDetails> getBeneTempList()
   {
      return beneTempList;
   }

   public String getErrorMessage(Integer pagenum)
   {
      return pagemanager.getErrorMessage(pagenum);
   }

   public void selectAcctType(Integer type)
   {
      tdMasterData.setAccttype(type);
      tdMasterData.setOptoutBeneficiary(false);
   }

   public String getCheckedImage(Integer which)
   {
      which = (which == null) ? 0 : which;
      if (tdMasterData.getAccttype() != null)
      {
         if (tdMasterData.getAccttype() == which)
         {
            return selectedCheckedImage;
         }
      }
      return defaultCheckedImage;

   }

   public Boolean hasRequiredData(String value)
   {

      if (value == null || value.isEmpty())
         return false;

      return true;
   }

   public Boolean hasRequiredData(Double value)
   {

      if (value == null)
         return false;

      return true;
   }

   public Boolean hasRequiredData(Integer value)
   {
      if (value == null)
         return false;

      return true;
   }

   public void saveBenefiaciaryDetails()
   {
      ArrayList<BenefiaciaryDetails> benefiaciaryDetailsList = tdMasterData.getBenefiaciaryDetailsList();
      if (validateBenificieryPage(tdMasterData.getTmpBenefiaciaryDetail(), 8))
      {
         if (tdMasterData.getBenefiaciaryDetailsList() == null)
         {
            benefiaciaryDetailsList = new ArrayList<BenefiaciaryDetails>();
         }

         if (tdMasterData.getTmpBenefiaciaryDetail() == null)
            return;

         if (tdMasterData.getTmpBenefiaciaryDetail().getBeneId() == null)
         {
            tdMasterData.getTmpBenefiaciaryDetail().setBeneId(benefiaciaryDetailsList.size());
         }
         ArrayList<BenefiaciaryDetails> tmpbenefiaciaryDetailsList = benefiaciaryDetailsList;

         if (tdMasterData.getEditBeneficiaryForm())
         {
            for (int i = 0; i < tmpbenefiaciaryDetailsList.size(); i++)
            {
               BenefiaciaryDetails tmpb = tmpbenefiaciaryDetailsList.get(i);
               if (tdMasterData.getTmpBenefiaciaryDetail().getBeneId().intValue() == tmpb.getBeneId().intValue())
               {
                  benefiaciaryDetailsList.remove(i);

                  tdMasterData.setTotalbeneficiaryShares((tdMasterData.getTotalbeneficiaryShares()) - (tmpb.getSharePerc().intValue()));
               }
            }

         }
         tdMasterData.setTotalbeneficiaryShares(((tdMasterData.getTotalbeneficiaryShares() == null) ? 0 : tdMasterData.getTotalbeneficiaryShares()) + tdMasterData.getTmpBenefiaciaryDetail().getSharePerc().intValue());
         tdMasterData.setTmptottalShares(tdMasterData.getTotalbeneficiaryShares());
         benefiaciaryDetailsList.add(tdMasterData.getTmpBenefiaciaryDetail());
         tdMasterData.setBenefiaciaryDetailsList(benefiaciaryDetailsList);
         tdMasterData.setShowBeneficiaryForm(false);
         tdMasterData.setEditBeneficiaryForm(false);
      }

   }

   public boolean validateBenificieryPage(BenefiaciaryDetails benefiaciaryDetail, Integer pagenum)
   {

      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);

      if (!hasRequiredData(benefiaciaryDetail.getBeneFirstName()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.firstName.requiredMsg", "First Name is required!", null));
      }
      if (!benefiaciaryDetail.getBeneDOB().equals("") && !JavaUtil.isValidDate(benefiaciaryDetail.getBeneDOB(), "MM/dd/yyyy"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.formatMsg", "Enter valid Date of Birth!", null));
      }
      if (!hasRequiredData(benefiaciaryDetail.getBeneRel()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.beneRelation.requiredMsg", "Benefiaciary Relationship is required!", null));
      }
      if (!hasRequiredData(benefiaciaryDetail.getTypeOfBeneficiary()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.beneType.requiredMsg", "Type of Beneficiary is required!", null));
      }

      if (!hasRequiredData("" + benefiaciaryDetail.getSharePerc()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.beneShare.requiredMsg", "Share is required!", null));
      }
      else if (benefiaciaryDetail.getSharePerc() <= 0)
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.beneSharegreter.requiredMsg", "Share should be greater than 0!", null));
      }
      else
      {
         double shareHolderPer = 0;
         ArrayList<BenefiaciaryDetails> tmpbenefiaciaryDetailsList = tdMasterData.getBenefiaciaryDetailsList();
         for (int i = 0; i < tmpbenefiaciaryDetailsList.size(); i++)
         {
            BenefiaciaryDetails tmpb = tmpbenefiaciaryDetailsList.get(i);
            shareHolderPer = tmpb.getSharePerc() + shareHolderPer;
         }
         shareHolderPer = tdMasterData.getTmptottalShares() + tdMasterData.getTmpBenefiaciaryDetail().getSharePerc().intValue();

         if (shareHolderPer > 100)
         {
            dataOK = false;
            pagemanager.setErrorMessage("Share holder total percentage should be 100%");
         }
      }

      return dataOK;
   }

   public Boolean validateAcctType() {
      Boolean dataOK = true;
      if (tdMasterData.getAccttype() == null || tdMasterData.getAccttype() == 0)
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.acctType.requiredMsg", "Account Type is required!", null));
      }
      return dataOK;
   }

   public Boolean validatePrimaryAcctHolder() {
      Boolean dataOK = true;
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getFirstName()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.firstName.requiredMsg", "First Name is required!", null));
      }
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getLastName()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.lastName.requiredMsg", "Last Name is required!", null));
//               pagemanager.setErrorMessage("Last Name is required!");
         //webutil.getMessageText().getDisplayMessage(msgheader, "This Email is already registered!", null);
      }
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getDob()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.requiredMsg", "Date of Birth is required!", null));
      }
      else if (!JavaUtil.isValidDate(tdMasterData.getAcctOwnersDetail().getDob(), "MM/dd/yyyy"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.formatMsg", "Enter valid Date of Birth!", null));
      }
      else if (!JavaUtil.compareDate(tdMasterData.getAcctOwnersDetail().getDob()).equalsIgnoreCase("B"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validdobother.formatMsg", "Birth date should be less than today date!", null));
      }
      else if (JavaUtil.compareDate(tdMasterData.getAcctOwnersDetail().getDob()).equalsIgnoreCase("B") && JavaUtil.checkYear(tdMasterData.getAcctOwnersDetail().getDob()).equalsIgnoreCase("F"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validyear.formatMsg", "Birth date not before than 130 year", null));
      }

      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getSsn()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.requiredMsg", "Social Security is required!", null));
      }
      else if (!JavaUtil.isValidSSN(tdMasterData.getAcctOwnersDetail().getSsn()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.formatMsg", "Enter valid Social Security Number!", null));
      }
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getCitizenshiId()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.citizenship.requiredMsg", "Must of US Citizen!", null));
      }
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhoneNumber()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.phoneno.requiredMsg", "Phone Number is required!", null));
      }
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.email.requiredMsg", "Email Address is required!", null));
      }
      else
      {
         if (validateEmailPattern(tdMasterData.getAcctOwnersDetail().getEmailAddress()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validemail.requiredMsg", "Enter valid  Email address!", null));
         }
      }
      return dataOK;
   }

   public Boolean validateJointAccountHolder() {
      Boolean dataOK = true;
      if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getFirstName()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.firstName.requiredMsg", "First Name is required!", null));
      }
      if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getLastName()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.lastName.requiredMsg", "Last Name is required!", null));
      }
      if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getDob()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.requiredMsg", "Date of Birth is required!", null));
      }
      else if (!JavaUtil.isValidDate(tdMasterData.getJointAcctOwnersDetail().getDob(), "MM/dd/yyyy"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.dob.formatMsg", "Enter valid Date of Birth!", null));
      }
      else if (tdMasterData.getAccttype() == 3 && JavaUtil.compareDate(tdMasterData.getJointAcctOwnersDetail().getDob()).equalsIgnoreCase("A"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validdobminor.formatMsg", "Birth date should not be greater than today date!", null));
      }
      else if (tdMasterData.getAccttype() != 3 && !JavaUtil.compareDate(tdMasterData.getJointAcctOwnersDetail().getDob()).equalsIgnoreCase("B"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validdobother.formatMsg", "Birth date should be less than today date!", null));
      }
      else if (JavaUtil.compareDate(tdMasterData.getAcctOwnersDetail().getDob()).equalsIgnoreCase("B") && JavaUtil.checkYear(tdMasterData.getAcctOwnersDetail().getDob()).equalsIgnoreCase("F"))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validyear.formatMsg", "Birth date not before than 130 year!", null));
      }
      if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getSsn()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.requiredMsg", "Social Security Number is required!", null));
      }
      else if (!JavaUtil.isValidSSN(tdMasterData.getJointAcctOwnersDetail().getSsn()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.ssn.formatMsg", "Enter valid Social Security Number!", null));
      }
      if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getCitizenshiId()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.citizenship.requiredMsg", "Must be US Citizen!", null));
      }
      if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhoneNumber()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.phoneno.requiredMsg", "Phone Number is required!", null));
      }
      if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.email.requiredMsg", "Email Address is required!", null));
      }
      else
      {
         if (validateEmailPattern(tdMasterData.getJointAcctOwnersDetail().getEmailAddress()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validemail.requiredMsg", "Enter valid  Email address!", null));
         }
      }
      return dataOK;
   }

   public Boolean validatePrimaryAddress() {
      Boolean dataOK = true;
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhysicalAddressStreet()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.resstreet.requiredMsg", "Residence Street Address is required!", null));
      }
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhysicalAddressCity()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
      }
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhysicalAddressState()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
      }
      if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getPhysicalAddressZipCode()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
      }
      else if (!JavaUtil.isValidZipCode(tdMasterData.getAcctOwnersDetail().getPhysicalAddressZipCode()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
      }
      if (tdMasterData.getAcctholderhasMailing())
      {
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getMailingAddressStreet()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.mailstreet.requiredMsg", "Mailing Street Address is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getMailingAddressCity()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getMailingAddressState()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getMailingAddressZipCode()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
         }
         else if (!JavaUtil.isValidZipCode(tdMasterData.getAcctOwnersDetail().getMailingAddressZipCode()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
         }
      }

      return dataOK;
   }

   public Boolean validateJointAddress() {
      Boolean dataOK = true;
      if (tdMasterData.getAccttype() == 2)
      {
         if (tdMasterData.getJointhasDifferent())
         {
            if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressStreet()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.resstreet.requiredMsg", "Residence Street Address is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressCity()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressState()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressZipCode()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
            }
            else if (!JavaUtil.isValidZipCode(tdMasterData.getJointAcctOwnersDetail().getPhysicalAddressZipCode()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
            }

            if (tdMasterData.getJointhasMailing())
            {
               if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getMailingAddressStreet()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.mailstreet.requiredMsg", "Mailing Street Address is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getMailingAddressCity()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getMailingAddressState()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getJointAcctOwnersDetail().getMailingAddressZipCode()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
               }
               else if (!JavaUtil.isValidZipCode(tdMasterData.getJointAcctOwnersDetail().getMailingAddressZipCode()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
               }
            }
         }
      }

      return dataOK;
   }

   public Boolean validateRegulatory() {
      Boolean dataOK = true;
      if (tdMasterData.getSenoirPolitical())
      {
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getSpfName()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.spfname.requiredMsg", "Name of SPF is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getSpfRelationship()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.spfrelation.requiredMsg", "Relationship of SPF is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getSpfTitle()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.spftitle.requiredMsg", "Political title is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getSpfCountry()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.spfcountry.requiredMsg", "Country of office is required!", null));
         }
      }
      if (tdMasterData.getOwnerShare())
      {
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getShareholderCompany()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sharecompany.requiredMsg", "Company Name is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getShareholderAddress()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.shareaddress.requiredMsg", "Company Adddress is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getShareholderCity()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sharecity.requiredMsg", "Company city is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getShareholderState()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sharestate.requiredMsg", "Company state is required!", null));
         }
      }
      if (tdMasterData.getOwnerBD())
      {
         if (!hasRequiredData(tdMasterData.getAcctOwnersDetail().getBdDetail()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bdentity.requiredMsg", "Entity is required!", null));
         }
      }

      return dataOK;
   }


   public Boolean validatePrimaryEmployment() {
      Boolean dataOK = true;
      if (!hasRequiredData(tdMasterData.getOwneremploymentDetail().getEmplTypeId()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.empstatus.requiredMsg", "Employment status is required!", null));
      }
      else if (tdMasterData.getOwneremploymentDetail().getEmplTypeId().startsWith("EMPL") ||
         tdMasterData.getOwneremploymentDetail().getEmplTypeId().startsWith("SLFEMPL"))
      {
         if (!hasRequiredData(tdMasterData.getOwneremploymentDetail().getEmployerName()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.empname.requiredMsg", "Employer name is required!", null));
         }
         if (!hasRequiredData(tdMasterData.getOwneremploymentDetail().getOccupation()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.occupation.requiredMsg", "Occupation is required!", null));
         }
         if(hasRequiredData(tdMasterData.getOwneremploymentDetail().getEmployerZipCode())){
            if (!JavaUtil.isValidZipCode(tdMasterData.getOwneremploymentDetail().getEmployerZipCode()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
            }
         }

      }
      else
      {
         if (!hasRequiredData(tdMasterData.getOwneremploymentDetail().getSourceOfIncome()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sourceincome.requiredMsg", "Source of income is required!", null));
         }
      }


      return dataOK;
   }

   public Boolean validateJointEmployment() {
      Boolean dataOK = true;
      if (tdMasterData.getAccttype() == 2)
      {
         if (!hasRequiredData(tdMasterData.getJointEmploymentDetail().getEmplTypeId()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.empstatus.requiredMsg", "Employment status is required!", null));
         }
         else if (tdMasterData.getJointEmploymentDetail().getEmplTypeId().startsWith("EMPL") ||
            tdMasterData.getJointEmploymentDetail().getEmplTypeId().startsWith("SLFEMPL"))
         {
            if (!hasRequiredData(tdMasterData.getJointEmploymentDetail().getEmployerName()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.empname.requiredMsg", "Employer name is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getJointEmploymentDetail().getOccupation()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.occupation.requiredMsg", "Occupation is required!", null));
            }
            if(hasRequiredData(tdMasterData.getJointEmploymentDetail().getEmployerZipCode())){
               if (!JavaUtil.isValidZipCode(tdMasterData.getJointEmploymentDetail().getEmployerZipCode()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
               }
            }
         }
         else
         {
            if (!hasRequiredData(tdMasterData.getJointEmploymentDetail().getSourceOfIncome()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sourceincome.requiredMsg", "Source of income is required!", null));
            }
         }
      }
      return dataOK;
   }

   public Boolean validateBeneficiary()
   {
      Boolean dataOK = true;
      if (tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRAROTH") ||
         tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRAROOV") ||
         tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRATRAD") ||
         tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRABENE"))
      {
         if (tdMasterData.getBenefiaciaryDetailsList().size() == 0)
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.benedetails.requiredMsg", "Beneficiary details is required!", null));
         }
      }

      if (tdMasterData.getBenefiaciaryDetailsList().size() > 0)
      {
         double shareHolderPer = 0;
         ArrayList<BenefiaciaryDetails> tmpbenefiaciaryDetailsList = tdMasterData.getBenefiaciaryDetailsList();
         for (int i = 0; i < tmpbenefiaciaryDetailsList.size(); i++)
         {
            BenefiaciaryDetails tmpb = tmpbenefiaciaryDetailsList.get(i);
            shareHolderPer = tmpb.getSharePerc() + shareHolderPer;
         }
         if (shareHolderPer == 100)
            dataOK = true;
         else
         {
            dataOK = false;
            pagemanager.setErrorMessage("Share holder percentage should be 100%");
         }
      }
      return dataOK;
   }

   public Boolean validateFunding()
   {
      Boolean dataOK = true;
      if (tdMasterData.getFundNow() == false) // flag if opt of for funcindg is false
      {
         if (!hasRequiredData(tdMasterData.getFundType()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.fundtype.requiredMsg", "Fund type is required!", null));
         }

         else if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("PMACH"))
         {
            if (!hasRequiredData(tdMasterData.getInitialInvestment()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investamt.requiredMsg", "Investment amount is required!", null));
            }
            else if (tdMasterData.getInitialInvestment() < 2000)
            {
               dataOK = false;
               //String minInvestment = webutil.getDataDisplayConverter().displayAsMoney(tdMasterData.getCustomerData().getInitialInvestment());
               String minInvestment = "2000";
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investamtless.requiredMsg", "Min " + minInvestment + " investment required", new Object[]{minInvestment}));
            }

            if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankAcctType()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccounttype.requiredMsg", "Account type is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankName()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankname.requiredMsg", "Bank Name is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankAcctName()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccountname.requiredMsg", "Name on bank account is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankCityState()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankcityState.requiredMsg", "Bank City/State is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankPhoneNumber()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankphoneno.requiredMsg", "Bank Phone number is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankABARouting()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.abaroutingno.requiredMsg", "ABA Routing Number is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAchBankDetail().getBankAcctNumber()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccountno.requiredMsg", "Bank Account Number is required!", null));
            }
         }
         else if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("PMFEDW"))
         {
            if (!hasRequiredData(tdMasterData.getAcatDetails().getFromAccountTitle()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccounttitle.requiredMsg", "Account Title is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAcatDetails().getAccountNumber2()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccountno.requiredMsg", "Account Number is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAcatDetails().getContraFirmList()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.deliveryfirm.requiredMsg", "Name of delivery firm is required!", null));
            }
                     /*if (tdMasterData.getAcatDetails().getContraFirmList().equalsIgnoreCase("OTH") && !hasRequiredData(tdMasterData.getAcatDetails().getOtherContraFirmList()))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.deliveryfirmother.requiredMsg", "Name of other delivery firm is required!", null));
                     }*/

            if (!hasRequiredData(tdMasterData.getAcatDetails().getFromOtherAccountType()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.accttype.requiredMsg", "Account Type is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getAcatDetails().getTransferTypeId()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.fullpartial.requiredMsg", "Full/Partial transfer is required!", null));
            }
         }

         else if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("TDTRF"))
         {
            if (tdMasterData.getTdTransferDetails().getRetilFlag() == null || tdMasterData.getTdTransferDetails().getRetilFlag().equals(""))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.brokerfirmnamefalg.requiredMsg", "Is this a Retail or Advisor managed account is required!", null));
            }
            else if (tdMasterData.getTdTransferDetails().getRetilFlag().equals("Y"))
            {
               if (!hasRequiredData(tdMasterData.getTdTransferDetails().getPriorFirmName()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.brokerfirmname.requiredMsg", "Existing Advisory Firm Name is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getTdTransferDetails().getFirmAccountNo()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.firmaccountno.requiredMsg", "Firm Account Number is required!", null));
               }
            }
            else if (tdMasterData.getTdTransferDetails().getRetilFlag().equals("N"))
            {
               if (!hasRequiredData(tdMasterData.getTdTransferDetails().getRetailAccountNumber()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.brokerAccno.requiredMsg", "TD Ameritrade Retail Brokerage Account Number is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getTdTransferDetails().getAdvisorID()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.advisorId.requiredMsg", "Advisor ID is required!", null));
               }
            }
         }
      }
         return dataOK;
   }

   public Boolean validateRecurring()
   {
      Boolean dataOK = true;
      if (tdMasterData.getFundNow() == false) // flag if opt of for funding is false
      {
         if (tdMasterData.getRecurringFlag() == false) //
         {
            if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getTranFreqId()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.transfrequency.requiredMsg", "Frequency is required!", null));
            }
            if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getTranAmount()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.transamt.requiredMsg", "Amount is required!", null));
            }
            else if (tdMasterData.getElectroicBankDetail().getTranAmount() < 50)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.transamtless.requiredMsg", "Transaction Amount should be minimum $50.", null));
            }

            if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getTranStartDate()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.transstartdate.requiredMsg", "Start date is requied!", null));
            }
            else if (!tdMasterData.getElectroicBankDetail().getTranStartDate().equals("") && !JavaUtil.isValidDate(tdMasterData.getElectroicBankDetail().getTranStartDate(), "MM/dd/yyyy"))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validateStartDate.requiredMsg", "Enter valid Start Date (mm/dd/yyyy)!", null));
            }
            else if (!tdMasterData.getElectroicBankDetail().getTranStartDate().equals("") && !JavaUtil.checkdate(tdMasterData.getElectroicBankDetail().getTranStartDate()))
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.validateStartDate.formatMsg", "Start date should be greater than today date!", null));
            }

            if (tdMasterData.getFundType().equalsIgnoreCase("PMACH") && tdMasterData.getCopyAchInstructions())
            {

            }
            else
            {
               if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankAcctType()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.accounttype.requiredMsg", "* Bank Account Type is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankName()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankname.requiredMsg", "Bank Name is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankAcctName()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.banktitle.requiredMsg", "Bank Account title is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankCityState()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankcityState.requiredMsg", "Bank City/State is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankPhoneNumber()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankphoneno.requiredMsg", "Bank Phone number is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankABARouting()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.abaroutingno.requiredMsg", "ABA Routing Number is required!", null));
               }
               if (!hasRequiredData(tdMasterData.getElectroicBankDetail().getBankAcctNumber()))
               {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.bankaccountno.requiredMsg", "Bank Account Number is required!", null));
               }
            }
         }
      }
      return dataOK;
   }

   public Boolean validatePage(Integer pagenum)
   {

      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);

      if (pagenum == null)
         return false;

      switch (pagenum)
      {
         case 0: // Accttype page
            dataOK = validateAcctType();
            break;
         case 1: // Account Holder
            dataOK = validatePrimaryAcctHolder();
            break;
         case 2: // Joint Holder
            dataOK = validateJointAccountHolder();
            break;
         case 3: // Address
            dataOK = validatePrimaryAddress();
            break;
         case 4: // Joint Address
            dataOK = validateJointAddress();
            break;
         case 5: // Regulatory
            dataOK = validateRegulatory();
            break;
         case 6: // Employment
            dataOK = validatePrimaryEmployment();
            break;
         case 7: // Joint Employment
            dataOK = validateJointEmployment();
            break;
         case 8: // Benefitiary
            dataOK = validateBeneficiary();
            break;
         case 9: // Funding Page 1
            dataOK = validateFunding();
            break;
         case 10: // Funding Recurring Page 2
            dataOK = validateRecurring();
            break;

      }
      return dataOK;
   }

   public void loadACATFirmList() {
      // Make sure that account-number and logonid are set
      // tdMasterData.getCustomerData().setAcctnum(acctnum);
      // tdMasterData.getCustomerData().setLogonid(logonid);
      custodyListDAO.getAcatFirmList(tdMasterData);
   }

   public void loadCustomerProfileData() {
      // Make sure that account-number and logonid are set
      // tdMasterData.getCustomerData().setAcctnum(acctnum);
      // tdMasterData.getCustomerData().setLogonid(logonid);
      consumerListDAO.getProfileData(tdMasterData.getCustomerData());
   }

   public void loadTDAccountDetails() {
      // Make sure that account-number and logonid are set
      custodyListDAO.getTDAccountDetails(tdMasterData);
      custodyListDAO.getTDAccountHolder(tdMasterData);
   }

   public void loadTDEmployment() {
      // Make sure that account-number and logonid are set
      custodyListDAO.getTDEmployment(tdMasterData);
   }

   public void loadTDBeneficiary() {
      // Make sure that account-number and logonid are set
      if (tdMasterData.getOptoutBeneficiary())
      {
         custodySaveDAO.deleteBenefiaciaryDetails(tdMasterData);
      }
      custodyListDAO.getTDBeneficiary(tdMasterData);
   }

   public void loadTDFunding() {
      // Make sure that account-number and logonid are set
      if (!tdMasterData.getOptoutFunding())
      {
         custodyListDAO.getfundingData(tdMasterData);
         if (tdMasterData.getOptoutRecurring())
         {
            tdMasterData.setRecurringFlag(true);
         }
      }
      else
      {
         tdMasterData.setFundNow(true);
         tdMasterData.setRecurringFlag(true);
      }
   }

   public void loadTDACHData() {
      // Make sure that account-number and logonid are set
      custodyListDAO.getFundACHData(tdMasterData);
   }

   public AdvisorDetails loadAdvisorData()
   {
      return null;
   }

   public AcctOwnersDetails setOwnerData(String dataFlag, TDMasterData tdMasterData)
   {
      TDMasterData mstData = tdMasterData;
      AcctOwnersDetails acctOwnersDetail = tdMasterData.getAcctOwnersDetail();
      AcctOwnersDetails jointAcctOwnersDetail = tdMasterData.getJointAcctOwnersDetail();
      if (dataFlag.equals("add"))
      {
         jointAcctOwnersDetail.setPhysicalAddressStreet(acctOwnersDetail.getPhysicalAddressStreet());
         jointAcctOwnersDetail.setPhysicalAddressCity(acctOwnersDetail.getPhysicalAddressCity());
         jointAcctOwnersDetail.setPhysicalAddressState(acctOwnersDetail.getPhysicalAddressState());
         jointAcctOwnersDetail.setPhysicalAddressZipCode(acctOwnersDetail.getPhysicalAddressZipCode());
         jointAcctOwnersDetail.setMailingAddressStreet(acctOwnersDetail.getMailingAddressStreet());
         jointAcctOwnersDetail.setMailingAddressCity(acctOwnersDetail.getMailingAddressCity());
         jointAcctOwnersDetail.setMailingAddressState(acctOwnersDetail.getMailingAddressState());
         jointAcctOwnersDetail.setMailingAddressZipCode(acctOwnersDetail.getMailingAddressZipCode());
      }
      return jointAcctOwnersDetail;
   }

   public AcctOwnersDetails setRegulatoryData(TDMasterData tdMasterData)
   {
      TDMasterData mstData = tdMasterData;
      AcctOwnersDetails acctOwnersDetail = tdMasterData.getAcctOwnersDetail();
      if (mstData.getSenoirPolitical())
      {
         acctOwnersDetail.setSPF("Y");
         acctOwnersDetail.setSpfDetail(acctOwnersDetail.getSpfName() + "," + acctOwnersDetail.getSpfRelationship() + "," + acctOwnersDetail.getSpfTitle() + "," + acctOwnersDetail.getSpfCountry());
      }
      if (mstData.getOwnerShare())
      {
         acctOwnersDetail.setDirectorShareholder("Y");
         acctOwnersDetail.setDirectorShareholderDetail(acctOwnersDetail.getShareholderCompany() + "," + acctOwnersDetail.getShareholderAddress() + "," + acctOwnersDetail.getShareholderCity() + "," + acctOwnersDetail.getShareholderState());
      }
      if (mstData.getOwnerBD())
      {
         acctOwnersDetail.setBd("Y");
      }
      return acctOwnersDetail;
   }

   public void saveTDAccountDetail() {
      custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(), tdMasterData);
   }

   public void saveTDPrimaryAccountOwner() {
      custodySaveDAO.tdSaveAccountOwner(tdMasterData.getAcctOwnersDetail());
   }

   public void saveTDJointAccountOwner() {
      custodySaveDAO.tdSaveAccountOwner(tdMasterData.getJointAcctOwnersDetail());
   }

   public void saveTDPrimaryAddress() {
      custodySaveDAO.tdSaveAccountOwner(tdMasterData.getAcctOwnersDetail());
   }

   public void saveTDJointAddress() {
      if (tdMasterData.getJointhasDifferent())
         custodySaveDAO.tdSaveAccountOwner(tdMasterData.getJointAcctOwnersDetail());
      else
         custodySaveDAO.tdSaveAccountOwner(setOwnerData("add", tdMasterData));
   }

   public void saveTDRegulatory() {
      custodySaveDAO.tdSaveAccountOwner(setRegulatoryData(tdMasterData));
      custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(), tdMasterData);
   }

   public void saveTDPrimaryEmployment() {
      custodySaveDAO.tdSaveEmployment(tdMasterData.getOwneremploymentDetail());
   }

   public void saveTDJointEmployment() {
      custodySaveDAO.tdSaveEmployment(tdMasterData.getJointEmploymentDetail());
   }

   public void saveTDBeneficiary() {
      if (tdMasterData.getOptoutBeneficiary())
      {
         custodySaveDAO.deleteBenefiaciaryDetails(tdMasterData);
      }
      custodySaveDAO.saveBenefiaciaryDetails(tdMasterData.getBenefiaciaryDetailsList());
      custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(), tdMasterData);
   }

   public void saveTDFunding() {

      // custodySaveDAO.tdsaveACHData(tdMasterData);
      if (tdMasterData.getOptoutFunding())
      {
         custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(), tdMasterData);
      }
      else
      {
         custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(), tdMasterData);
         if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("PMACH"))// for ACH acocunt
         {
            custodySaveDAO.tdsaveACHData(tdMasterData, "ACH","ADD");
            if (tdMasterData.getCopyAchInstructions() && tdMasterData.getFundType().equalsIgnoreCase("PMACH"))
            {
               tdMasterData.getElectroicBankDetail().setBankAcctType(tdMasterData.getAchBankDetail().getBankAcctType());
               tdMasterData.getElectroicBankDetail().setBankName(tdMasterData.getAchBankDetail().getBankName());
               tdMasterData.getElectroicBankDetail().setBankAcctName(tdMasterData.getAchBankDetail().getBankAcctName());
               tdMasterData.getElectroicBankDetail().setBankCityState(tdMasterData.getAchBankDetail().getBankCityState());
               tdMasterData.getElectroicBankDetail().setBankPhoneNumber(tdMasterData.getAchBankDetail().getBankPhoneNumber());
               tdMasterData.getElectroicBankDetail().setBankABARouting(tdMasterData.getAchBankDetail().getBankABARouting());
               tdMasterData.getElectroicBankDetail().setBankAcctNumber(tdMasterData.getAchBankDetail().getBankAcctNumber());

            }
         }
         else if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("PMFEDW"))
            custodySaveDAO.tdSaveACAT(tdMasterData, tdMasterData.getAcctnum(), tdMasterData.getAcatDetails(),"ADD");

         else if (tdMasterData.getFundType() != null && tdMasterData.getFundType().equalsIgnoreCase("TDTRF"))
            custodySaveDAO.tdSaveTDTransferData(tdMasterData, tdMasterData.getAcctnum(), tdMasterData.getTdTransferDetails(),"ADD");
      }
      // custodySaveDAO.tdSaveACH("ACH",tdMasterData.getOwnerSPF(),tdMasterData.getAcctnum(),tdMasterData.getInitialInvestment(),tdMasterData.getFundType(),tdMasterData.getAchBankDetail());
   }

   public void saveTDRecurring() {
      if (tdMasterData.getOptoutRecurring())
      {
         custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(), tdMasterData);
      }
      else
      {
         if (!tdMasterData.getRecurringFlag())
         {
            custodySaveDAO.tdSaveAccountDetail(tdMasterData.getAcctdetail(), tdMasterData);
            custodySaveDAO.tdSaveElectronicPaymentData(tdMasterData);
         }
      }
      //custodySaveDAO.tdSaveElectronicPayment("REC",tdMasterData.getOwnerSPF(),tdMasterData.getAcctnum(),tdMasterData.getInitialInvestment(),tdMasterData.getFundType(),tdMasterData.getElectroicBankDetail());
   }

   public void sendAlertMessage(String flag) {
      custodySaveDAO.tdMangedUserProfile(getTdMasterData().getAcctnum(),flag,webutil.getUserInfoData().getLogonID());
   }

   public void saveTDNewRequest() {
      custodySaveDAO.tdCheckRequest(tdMasterData);  // check for fund and recuring tab is filled on save and open buttton

      if (!tdMasterData.getFundType().equalsIgnoreCase("TDTRF"))
      {
         Request data = new Request();
         data.setReqId(new Long(0));
         data.setEventNum(0);
         data.setAcctnum(tdMasterData.getAcctnum());
         data.setAction(DCConstants.ACTION_ACCT_OPEN);
         if (tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACINDIV") ||
            tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACJOINT") ||
            tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("ACCSTD"))
         {
            data.setReqType("ACCT_APPLI_NEW");
            data.setSubaction(DCConstants.SUB_ACTION_ACCT_APP_NEW);
         }else if (tdMasterData.getAcctdetail().getAcctTypeId().equalsIgnoreCase("IRABENE"))
         {
            data.setReqType("IRA_QRP_BENE_NEW");
            data.setSubaction(DCConstants.SUB_ACTION_IRA_QRP_BEN);
         } else
         {
            data.setReqType("IRA_APPLI_NEW");
            data.setSubaction(DCConstants.SUB_ACTION_IRA_NEW);
         }

         data.setEnvelopeHeading("Please sign account opening document.");
         custodySaveDAO.tdOpenAccount(data);
         tdMasterData.getRequest().setEventNum(data.getEventNum());
      }
   }

   public void saveData(Integer pagenum)
   {

      if (pagenum == null)
         return;

      if (getTdMasterData().getAccttype() == 3)
      {
         getTdMasterData().getAcctOwnersDetail().setOwnership("AOCUSTODIAN");
         getTdMasterData().getJointAcctOwnersDetail().setOwnership("AOMINOR");
      }
      else
      {
         getTdMasterData().getAcctOwnersDetail().setOwnership("AOPRIMARY");
         getTdMasterData().getJointAcctOwnersDetail().setOwnership("AOJOINT");
      }
      switch (pagenum)
      {
         case 0: // Account Type and create basic info
            saveTDAccountDetail();
            break;
         case 1: // Account Owner
            saveTDPrimaryAccountOwner();
            break;
         case 2:  // Joint Owner  (if Any)
            saveTDJointAccountOwner();
            break;
         case 3:  // Owner Address
            saveTDPrimaryAddress();
            break;
         case 4:  // Joint Owner  Address (if Any)
           // saveTDJointAccountOwner(); Function call commented by Sagar
            saveTDJointAddress();
            break;
         case 5: // Regulatory
            saveTDRegulatory();
            break;
         case 6:  // Owner Emplyment
            saveTDPrimaryEmployment();
            break;
         case 7: // Joint Emplyment  (If any)
            saveTDJointEmployment();
            break;
         case 8:
            saveTDBeneficiary();
            break;
         case 9: // funding
            saveTDFunding();
            break;
         case 10:
            saveTDRecurring();
            break;
         default:
            break;
      }

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


   //account openDate in yyyy-mm-dd
   public Map<String, String> getTaxYear(String accountOpenDate, String accountType)
   {
      SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");
      SimpleDateFormat formatNowmonth = new SimpleDateFormat("MM");
      SimpleDateFormat formatNowday = new SimpleDateFormat("dd");
      Date todayDate = new Date();
      Map<String, String> taxYear = new LinkedHashMap<String, String>();

      String currentYear = formatNowYear.format(todayDate);
      String openDateyear = accountOpenDate.substring(0, 4);
      String currentMonth = formatNowmonth.format(todayDate);
      String currentDay = formatNowday.format(todayDate);
      if (accountType.startsWith("IRA"))
      {
         if (Integer.parseInt(currentMonth) <= 3 || (Integer.parseInt(currentMonth) == 4 && Integer.parseInt(currentDay) <= 15))
         {
            if (Integer.parseInt(openDateyear) < Integer.parseInt(currentYear))
            {
               String strYear = String.valueOf(Integer.parseInt(currentYear) - 1);
               taxYear.put(strYear, strYear);
               taxYear.put(currentYear, currentYear);
            }
            else
            {
               taxYear.put(currentYear, currentYear);
            }
         }
         else
         {
            taxYear.put(currentYear, currentYear);
         }
      }
      else
      {
         taxYear.put(currentYear, currentYear);
      }
      return taxYear;

   }


   public Boolean validateChangeAddress(AcctOwnersDetails objAcctOwnersDetails,boolean bFlag) {
      Boolean dataOK = true;
      if (!hasRequiredData(objAcctOwnersDetails.getPhysicalAddressStreet()))
      {
         dataOK = false;

         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.resstreet.requiredMsg", "Residence Street Address is required!", null));
      }
      if (!hasRequiredData(objAcctOwnersDetails.getPhysicalAddressCity()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
      }
      if (!hasRequiredData(objAcctOwnersDetails.getPhysicalAddressState()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
      }
      if (!hasRequiredData(objAcctOwnersDetails.getPhysicalAddressZipCode()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
      }
      else if (!JavaUtil.isValidZipCode(objAcctOwnersDetails.getPhysicalAddressZipCode()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
      }
      if (!hasRequiredData(objAcctOwnersDetails.getPhoneNumber()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.phoneno.requiredMsg", "Phone Number is required!", null));
      }
      if (!hasRequiredData(objAcctOwnersDetails.getEmailAddress()))
      {
         dataOK = false;
         pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.email.requiredMsg", "Email Address is required!", null));
      }

      if (tdMasterData.getAcctholderhasMailing() && bFlag)
      {
         if (!hasRequiredData(objAcctOwnersDetails.getMailingAddressStreet()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.mailstreet.requiredMsg", "Mailing Street Address is required!", null));
         }
         if (!hasRequiredData(objAcctOwnersDetails.getMailingAddressCity()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.city.requiredMsg", "City is required!", null));
         }
         if (!hasRequiredData(objAcctOwnersDetails.getMailingAddressState()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.state.requiredMsg", "State is required!", null));
         }
         if (!hasRequiredData(objAcctOwnersDetails.getMailingAddressZipCode()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.requiredMsg", "Zip Code is required!", null));
         }
         else if (!JavaUtil.isValidZipCode(objAcctOwnersDetails.getMailingAddressZipCode()))
         {
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.zip.formatMsg", "Enter valid Zip Code!", null));
         }
      }

      return dataOK;
   }

   public void resetBaseTD() {
      tdMasterData = new TDMasterData(null, 0L);
      saveandOpenError = null;
      beneTempList = new ArrayList<BenefiaciaryDetails>();
   }

   public String processDCRequest(String advisorName, String repId,Long acctnum,int eventNo,String action){
      return custodySaveDAO.processDCRequest(advisorName,repId,acctnum,eventNo,action);
   }

}
