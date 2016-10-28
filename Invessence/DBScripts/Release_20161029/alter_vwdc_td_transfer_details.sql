use invdb;

drop view invdb.vwdc_td_transfer_details;

create view invdb.vwdc_td_transfer_details as
select 
 ttd.acctnum, ttd.reqId, getTitleRegi(ttd.acctnum)accountTitle,
 ttd.firmName, 
 case when (isnull(ttd.firmAccountNo) or trim(ttd.firmAccountNo)='') then 'N/A' else ttd.firmAccountNo end naAcctNumber,
 ttd.primaryContact, ttd.priorFirmName, ttd.retailAccountNumber, 
ttd.isBrokerAcct, ttd.advisorID, ttd.removeAdvisor, ttd.addAdvisor, ttd.ssn
 from invdb.dc_td_transfer_details ttd, invdb.dc_acct_details ad, invdb.dc_acct_owners_details aod 
 where ad.acctnum=ttd.acctnum and aod.acctnum=ttd.acctnum and aod.ownership in ('AOPRIMARY','AOCUSTODIAN') order by acctnum desc;
