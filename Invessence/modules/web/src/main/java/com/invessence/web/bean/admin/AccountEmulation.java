package com.invessence.web.bean.admin;

import java.io.IOException;
import javax.faces.bean.*;
import javax.servlet.ServletException;

import com.invessence.web.dao.admin.AdminEmulationSpDAO;
import com.invessence.web.util.WebUtil;

/**
 * Created by sagar on 6/13/2017.
 */

@ManagedBean(name = "acctEmulBean")
public class AccountEmulation
{
   private long accountNumber;
   private double amount;
   private String errorMessage;
   @ManagedProperty("#{adminEmulationSpDAO}")
   private AdminEmulationSpDAO adminEmulationSpDAO;
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   public String processAccount() throws ServletException, IOException
   {
      String response="";
      try
      {
         System.out.print("processAccount Account Number " + accountNumber);
         System.out.print("processAccount Amount " + amount);
         errorMessage=getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_emulate_step1_openaccount", 0);
         this.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.acctType.requiredMsg",response, null));
      }
      catch (Exception e)
      {
         System.out.println("Error e");
      }
      return "failed";
   }

   public void openAccount() throws ServletException, IOException
   {
      try
      {
         System.out.print("openAccount Account Number " + accountNumber);
         System.out.print("openAccount Amount " + amount);
         getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_emulate_step2_openaccount", 0);
      }
      catch (Exception e)
      {
         System.out.println("Error e");
      }
   }

   public void activeAccount() throws ServletException, IOException
   {
      try
      {
         System.out.print("activeAccount Account Number " + accountNumber);
         System.out.print("activeAccount Amount " + amount);
         getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_emulate_step3_activateaccount", 1);
      }
      catch (Exception e)
      {
         System.out.println("Error e");
      }
   }

   public void fundAccount() throws ServletException, IOException
   {
      try
      {
         System.out.print("fundAccount Account Number " + accountNumber);
         System.out.print("fundAccount Amount " + amount);
         getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_emulate_step4_funding", 1);
      }
      catch (Exception e)
      {
         System.out.println("Error e");
      }
   }

   public void createAccountPosition() throws ServletException, IOException
   {
      try
      {
         System.out.print("createAccountPosition Account Number " + accountNumber);
         System.out.print("createAccountPosition Amount " + amount);
         getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_test1", 0);
      }
      catch (Exception e)
      {
         System.out.println("Error e");
      }
   }

   public void emulateTrade() throws ServletException, IOException
   {
      try
      {
         System.out.print("emulateTrade Account Number " + accountNumber);
         System.out.print("emulateTrade Amount " + amount);
         getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_test1", 0);
      }
      catch (Exception e)
      {
         System.out.println("Error e");
      }
   }

   public long getAccountNumber()
   {
      return accountNumber;
   }

   public void setAccountNumber(long accountNumber)
   {
      this.accountNumber = accountNumber;
   }

   public double getAmount()
   {
      return amount;
   }

   public void setAmount(double amount)
   {
      this.amount = amount;
   }

   public AdminEmulationSpDAO getAdminEmulationSpDAO()
   {
      return adminEmulationSpDAO;
   }

   public void setAdminEmulationSpDAO(AdminEmulationSpDAO adminEmulationSpDAO)
   {
      this.adminEmulationSpDAO = adminEmulationSpDAO;
   }

   public String getErrorMessage()
   {
      return errorMessage;
   }

   public void setErrorMessage(String errorMessage)
   {
      this.errorMessage = errorMessage;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }
}
