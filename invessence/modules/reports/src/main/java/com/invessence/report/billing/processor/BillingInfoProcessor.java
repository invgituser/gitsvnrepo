package com.invessence.report.billing.processor;

import java.util.Map;

import com.invessence.report.billing.domain.*;

public class BillingInfoProcessor
{
   private String filepath;

   public String getFilepath()
   {
      return filepath;
   }

   public void setFilepath(String filepath)
   {
      this.filepath = filepath;
   }

   public AccountInfo process(Map<String, Object> billingInfoMap)
   {
      String filename;
      String clientAccountId = String.valueOf(billingInfoMap.get("clientAccountID"));
      String lastName = String.valueOf(billingInfoMap.get("lastname"));
      String firstname = String.valueOf(billingInfoMap.get("firstname"));
      Long   invesseneAccountId = (Long) (billingInfoMap.get("Invessence_acctnum"));
      String email = String.valueOf(billingInfoMap.get("email"));
      String advisor = String.valueOf(billingInfoMap.get("advisor"));

      String fromDate = String.valueOf(billingInfoMap.get("fromDate"));
      String toDate = String.valueOf(billingInfoMap.get("toDate"));
      String billingYear = String.valueOf(billingInfoMap.get("billingYear"));
      String currentMonth = String.valueOf(billingInfoMap.get("currentMonth"));
      String currentYear = String.valueOf(billingInfoMap.get("currentYear"));
      String priorMonth = String.valueOf(billingInfoMap.get("priorMonth"));
      String priorYear = String.valueOf(billingInfoMap.get("priorYear"));

      Double currentCash  = (Double) (billingInfoMap.get("cash"));
      Double currentVested  = (Double) (billingInfoMap.get("invested"));
      Double priorCash  = (Double) (billingInfoMap.get("priorcash"));
      Double priorVested  = (Double) (billingInfoMap.get("priorinvested"));

      Double commission  = (Double) (billingInfoMap.get("commission"));
      Double otherFee  = (Double) (billingInfoMap.get("otherFee"));
      Double totalAdvisorFee  = (Double) (billingInfoMap.get("totalAdvisorFee"));
      Double invoiceFee  = (Double) (billingInfoMap.get("invoiceFee"));

      Double ytdcommission  = (Double) (billingInfoMap.get("ytdcommission"));
      Double ytdotherFee  = (Double) (billingInfoMap.get("ytdotherFee"));
      Double ytdtotalAdvisorFee  = (Double) (billingInfoMap.get("ytdtotalAdvisorFee"));
      Double ytdinvoiceFee  = (Double) (billingInfoMap.get("ytdinvoiceFee"));

      if (getFilepath() != null) {
         filename = getFilepath() + "Billing." + clientAccountId + "." + fromDate + ".pdf";
      }
      else
         filename = "/tmp/" + "Billing." + clientAccountId + "." + fromDate + ".pdf";

      AccountInfo accountInfo = new AccountInfo(clientAccountId, firstname, lastName, email, advisor,
                                                invesseneAccountId, filename, toDate, currentMonth);

      // DateInfo dateinfo = new DateInfo(fromDate,toDate, billingYear, currentMonth,currentYear);
      // BillingInfo billingInfo = new BillingInfo(currentCash,currentVested,priorCash,priorVested,
      //                                          commission,otherFee,totalAdvisorFee,invoiceFee,
      //                                          ytdcommission,ytdotherFee,ytdtotalAdvisorFee,ytdinvoiceFee);

      BillingStatement billingStatement = new BillingStatement.Builder()
         .withOutputLocation(filename)
         .withUserDetails(firstname, lastName, clientAccountId)
         .withBillMonthAndYear(currentMonth, currentYear, priorMonth, priorYear)
         .withTotalFees(commission, ytdcommission,
                           otherFee, ytdotherFee,
                           totalAdvisorFee, ytdtotalAdvisorFee)
         .withTotalValue(currentCash, priorCash, currentVested, priorVested).build();

      try {
      billingStatement.generate();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

      return accountInfo;
   }

}
