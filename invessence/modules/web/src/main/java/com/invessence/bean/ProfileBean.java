package com.invessence.bean;

import java.util.*;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.*;
import com.invessence.dao.AccountDAO;
import com.invessence.data.*;
import com.invessence.util.*;

@ManagedBean(name = "profileBean")
@RequestScoped
public class ProfileBean extends UserData
{

   private String beanUserID;
   private AccountDAO accountDAO;
   private EmailMessage messageText;

   public AccountDAO getAccountDAO()
   {
      return accountDAO;
   }

   public void setAccountDAO(AccountDAO accountDAO)
   {
      this.accountDAO = accountDAO;
   }

   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public String getBeanUserID()
   {
      return beanUserID;
   }

   public void setBeanUserID(String beanUserID)
   {
      this.beanUserID = beanUserID;
   }

   public void preRenderView()
   {

      if (!FacesContext.getCurrentInstance().isPostback())
      {
         accountDAO.getUserByEmail(beanUserID);

         if ((Util.isNull(beanUserID)))
         {
            String msg = "Cannot set profile data, this userid is unknown or locked.";


            try
            {
               FacesContext.getCurrentInstance().getExternalContext().redirect("message.xhtml?message=" + msg);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      }
   }


   public String submit()
   {
      MsgData data = new MsgData();
      //String websiteUrl = messageSource.getMessage("website.url", new Object[]{}, null);

      try
      {
         if (messageText == null)
         {
            System.out.println("Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (signup failure)");
            return "Error";
         }

         String secCode = MsgDigester.getMessageDigest(getPassword());
         setSecCode(secCode);
         // String pwd = MsgDigester.getMessageDigest(getEmailID());
         setPassword(secCode);
         // secCode = "Default123";

         // Save data to database....
         //long loginID = accountDAO.addUserInfo(getInstance());

         return "success";
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         data.setMsg(messageText.getMessagetext("profile.failure", new Object[]{stackTrace}));
         messageText.writeMessage("Error", data);
         return "failed";
      }
   }


}