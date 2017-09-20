package com.invessence.web.bean.admin;

import java.io.IOException;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.servlet.ServletException;

/**
 * Created by sagar on 6/13/2017.
 */

@ManagedBean(name = "acctEmulBean")
public class AccountEmulation
{
   private String accountNumber;
   private long amount;

   public void emulateCreateAccount() throws ServletException, IOException
   {
      System.out.print("emulateCreateAccount Account Number "+accountNumber);
      System.out.print("emulateCreateAccount Amount "+amount);
   }
   public void emulateFundAccount()throws ServletException, IOException{
      System.out.print("emulateFundAccount Account Number "+accountNumber);
      System.out.print("emulateFundAccount Amount "+amount);
   }
   public void emulateTrade()throws ServletException, IOException{
      System.out.print("emulateTrade Account Number "+accountNumber);
      System.out.print("emulateTrade Amount "+amount);
   }

   public String getAccountNumber()
   {
      return accountNumber;
   }

   public void setAccountNumber(String accountNumber)
   {
      this.accountNumber = accountNumber;
   }

   public long getAmount()
   {
      return amount;
   }

   public void setAmount(long amount)
   {
      this.amount = amount;
   }
}
