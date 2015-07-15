package com.invessence.data.consumer.CTO;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/14/15
 * Time: 9:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CTOData
{
   String email;
   String externalId;
   String accttype;
   String firstname, lastname, middle;
   String dob;
   String maritalStatus;
   Integer numDependents;

   String tincode, tintype; // TaxID
   String base_currency, margin, multicurrency;

   // String street1, street2, street3, city, state, postal_code, country;
   String mstreet1, mstreet2, mstreet3, mcity, mstate, mpostal_code, mcountry;
   String legalResidenceCountry, legalResidenceState, citizenship;
   String primary_phone, secondary_phone;

   String employmentType;
   String employer, occupation, employer_business;
   String estreet1, estreet2, estreet3, ecity, estate, epostal_code, ecountry;
   String employer_phone;

   String ownership;
   String acctholdertitlecode;

   Double net_income, liquid_net, net_worth;

   String stock, stocklevel;
   Integer stocktradeperyears, stocktradingyears;
   String options, optionslevel;
   Integer optionstradeperyears, optionstradingyears;
   String futures, futureslevel;
   Integer futurestradeperyears, futurestradingyears;
   String bond, bondlevel;
   Integer bondtradeperyears, bondtradingyears;
   String cash, cashlevel;
   Integer cashtradeperyears, cashtradingyears;


   ArrayList<String> objective;
   String exchange_group;
   String AdvisorWrapFees_strategy;
   String automated_fees_detailstype;
   Integer max_fee;

}
