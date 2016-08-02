package com.invessence.web.data.custody;

import java.util.ArrayList;

import com.invessence.web.data.common.UserData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/1/16
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class Custody
{
   private long     acctnum;
   private String   accttype;
   private String   movemoneyMethod;
   private UserData userdata;  // Used to collect the Userlogon info
   private ArrayList<AccountHolder> acctholderlist;
   private ArrayList<Employment> employmentlist;
   private ArrayList<Beneficiary> beneficiarylist;
   private ArrayList<ACH> achlist;
   private ArrayList<ACAT> acatlist;

   public Custody()
   {
      userdata = new UserData();

   }
}
