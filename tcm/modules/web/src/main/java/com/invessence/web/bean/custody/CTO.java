package com.invessence.web.bean.custody;

import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.emailer.data.MsgData;
import com.invessence.web.constant.*;
import com.invessence.web.dao.common.*;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.UserData;
import com.invessence.web.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/7/16
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "cto")
@SessionScoped
public class CTO
{
   private String beanacctnum;
   private UserData userdata;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{userInfoDAO}")
   private UserInfoDAO userInfoDAO;
   public void setUserinfoDAO(UserInfoDAO userInfoDAO)
   {
      this.userInfoDAO = userInfoDAO;
   }

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;
   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   @ManagedProperty("#{commonDAO}")
   private CommonDAO commonDAO;
   public void setCommonDAO(CommonDAO commonDAO)
   {
      this.commonDAO = commonDAO;
   }

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;

   public CTO()
   {
      userdata = new UserData();
   }

   public String getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public UserData getUserdata()
   {
      return userdata;
   }

   public void openCustodyAcct() {
/*
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      System.out.println("Registered by: " + userdata.getFullName() + ", EmailID: " + userdata.getEmail());
      try
      {

         // We are using the First Name, Last Name from UserData as entered.
         userdata.setUserID(userdata.getEmail());
         userdata.setEmailID(userdata.getEmail());
         String rndmPassword = PasswordGenerator.getSecCode();
         String tmpCode = MsgDigester.getMessageDigest(rndmPassword);
         String myIP = webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
         Integer myResetID = webutil.randomGenerator(0, 347896);
         String img = "123";
         Map<String, String> cookieInfo = new HashMap<String, String>();
         cookieInfo.put("img", img);
         //utl.setCookie(Const.COMPANY_NAME,"image",cookieInfo);
         userdata.setIp(myIP);
         userdata.setResetID(myResetID);
         userdata.setLogonstatus("T");
         userdata.setSecCode(tmpCode);
         userdata.setPassword(tmpCode);
         userdata.setCid("0");
         // secCode = "Default123";
         userdata.setEmailmsgtype("HTML");
         String supportInfo = webutil.getMessageText().buildInternalMessage("secure.url", new Object[]{});

         // Save data to database....
         long loginID = userInfoDAO.addUserInfo(userdata);

         if (loginID < 0L) {
            String msg="Could not register at this time.  Please try back later.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            webutil.emailsupport(userdata.getEmail(), userdata.getFirstName(), userdata.lastname);
         }
         else {
            // Now send email support.
            data.setSource("User");  // This is set to User to it insert into appropriate table.
            data.setSender(Const.MAIL_SENDER);
            data.setReceiver(getEmailID());
            data.setSubject(Const.COMPANY_NAME + " - Successfully registered");
            String secureUrl = messageText.buildInternalMessage("secure.url", new Object[]{});
            String name = getFirstName() + " " + getLastName();

            // System.out.println("MIME Type :" + getEmailmsgtype());
            if (getEmailmsgtype() == null || getEmailmsgtype().isEmpty())
               data.setMimeType("HTML");
            else
               data.setMimeType(getEmailmsgtype());

            String msg = messageText.buildMessage(getEmailmsgtype(), "signup.email.template", "signup.email", new Object[]{name, getUserID(), secureUrl, getEmailID(), getResetID().toString(), supportInfo});
            data.setMsg(msg);

            messageText.writeMessage("signup", data);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.LOGONID_PARAM, loginID);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/signupApproval.xhtml");
            return "success";
         }

      }
      catch (Exception ex)
      {
         String username = getUserID();
         String stackTrace = "User: " + username + " \n" + ex.getMessage();
         data.setMsg(messageText.buildInternalMessage("signup.failure", new Object[]{stackTrace}));
         messageText.writeMessage("Error", data);
         return "failed";
      }
*/

   }


}
