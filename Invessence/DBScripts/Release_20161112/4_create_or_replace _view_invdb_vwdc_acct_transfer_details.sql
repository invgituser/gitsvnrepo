create or replace  view invdb.vwdc_acct_transfer_details as
select atd.acctnum, reqId, getTitleRegi(atd.acctnum) accountTitle, aod.ssn,
get_acct_type(acctTypeId) accountType,
eePlanType, otherAccountType, fromAccountTitle, accountNumber2,
get_acct_type(fromOtherAccountType)fromAccountType,
 fromFirmAddress, fromFirmPhoneNumber, fromEEPlanType, simpleFunded, ''fromOtherAccountType,
 get_lookup_value(transferTypeId)transferTypeId,
 contraFirmList , aod.ownership
 from dc_acct_transfer_details atd, dc_acct_details ad, dc_acct_owners_details aod
 where ad.acctnum=atd.acctnum and aod.acctnum=atd.acctnum and aod.ownership in ('AOPRIMARY','AOCUSTODIAN') order by acctnum desc;
