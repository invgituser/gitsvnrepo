package com.invessence.web.bean.admin;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
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

   public void processAccount() throws ServletException, IOException
   {
      String response = "";
      try
      {
         if (accountNumber > 0)
         {
            System.out.print("processAccount Account Number " + accountNumber);
            System.out.print("processAccount Amount " + amount);
            errorMessage = getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_emulate_step1_process_account", 0);
         }
         else
         {
            errorMessage = "Account number is required";
         }
      }
      catch (Exception e)
      {
         System.out.println("Error e");
         e.printStackTrace();
      }
   }

   public void openAccount() throws ServletException, IOException
   {
      try
      {
         if (accountNumber > 0)
         {
            System.out.print("openAccount Account Number " + accountNumber);
            System.out.print("openAccount Amount " + amount);
            errorMessage = getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_emulate_step2_openaccount", 0);
         }
         else
         {
            errorMessage = "Account number is required";
         }
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
         if (accountNumber == 0l)
         {
            errorMessage = "Account number is required";
         }
         else if (amount == 0l)
         {
            errorMessage = "Amount is required";
         }
         else
         {
            System.out.print("activeAccount Account Number " + accountNumber);
            System.out.print("activeAccount Amount " + amount);
            errorMessage = getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_emulate_step3_activateaccount", 1);
         }
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
         if (accountNumber == 0l)
         {
            errorMessage = "Account number is required";
         }
         else if (amount == 0l)
         {

            errorMessage = "Amount is required";
         }
         else
         {
            System.out.print("fundAccount Account Number " + accountNumber);
            System.out.print("fundAccount Amount " + amount);
            errorMessage = getAdminEmulationSpDAO().processAccountRequest(accountNumber, amount, "testing.sp_emulate_step4_funding", 1);
         }
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
