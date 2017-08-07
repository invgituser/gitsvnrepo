package com.invessence.web.bean.advisor;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.constant.WebConst;
import com.invessence.web.data.common.UserData;
import com.invessence.web.data.consumer.tcm.TCMCustomer;

/**
 * Created by sagar on 7/7/2017.
 */

@ManagedBean(name = "usrMgmtBean")
@SessionScoped
public class UserMgmtBean extends TCMCustomer implements Serializable
{
   String msgheader, msg;

   public void gotoCustody()
   {
      if (registerUser())
      {
      }
   }

   private boolean registerUser()
   {
      try
      {
         UserData userdata = new UserData();
         userdata.setFirstName(getFirstname());
         userdata.setLastName(getLastname());
         userdata.setEmail(getEmail());
         userdata.setUserID(getEmail());
         userdata.setAcctnum(getAcctnum());

         if (userInfoDAO.validateUserID(userdata))
         {
            logger.debug("LOG: Validate UserID failed: " + getEmail());
            msgheader = "signup.U100";
            msg = webutil.getMessageText().getDisplayMessage(msgheader, "This Email is already registered!", null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
         }
         else
         {
            Integer myResetID = webutil.randomGenerator(0, 347896);
            userdata.setUserInfo(WebConst.ROLE_USER, getAdvisor(), getRep(), myResetID);
            long loginID = userInfoDAO.addUserInfo(userdata);

            if (loginID <= 0L)
            {
               logger.debug("ERROR: Had issue with this userid when attempting to save: " + loginID);
               msgheader = "signup.U106";
               msg = webutil.getMessageText().getDisplayMessage(msgheader, "There was some error when attempting to save this userid.  Please reach out to support desk.", null);
               webutil.redirecttoMessagePage("ERROR", msg, "Failed Signup" + msgheader);
               webutil.alertSupport("Userbean.saveUser", "Save -" + getEmail(), "Save Registration Error", null);
            }
            userdata.setLogonID(loginID);
            setLogonid(loginID);
            setDoesUserHavaLogonID(true);
            return true;
         }
         return false;
      }
      catch (Exception ex)
      {
          msgheader = "signup.EX.100";
          msg = webutil.getMessageText().getDisplayMessage(msgheader, "Exception: Create UserID/Pwd, problem attempting to create simpleuser", null);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
         ex.printStackTrace();
      }
      return false;
   }

   public String getMsgheader()
   {
      return msgheader;
   }

   public void setMsgheader(String msgheader)
   {
      this.msgheader = msgheader;
   }

   public String getMsg()
   {
      return msg;
   }

   public void setMsg(String msg)
   {
      this.msg = msg;
   }
}