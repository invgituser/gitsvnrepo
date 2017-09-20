create or replace  view invdb.vwdc_acct_transfer_details as
select atd.acctnum, reqId, invdb.getTitleRegi(atd.acctnum) accountTitle, aod.ssn,
invdb.get_acct_type(acctTypeId) accountType,
eePlanType, otherAccountType, fromAccountTitle, accountNumber2,
invdb.get_acct_type(fromOtherAccountType)fromAccountType,
 fromFirmAddress, fromFirmPhoneNumber, fromEEPlanType, simpleFunded, ''fromOtherAccountType,
 invdb.get_lookup_value(transferTypeId)transferTypeId,
 contraFirmList , aod.ownership
 from invdb.dc_acct_transfer_details atd, invdb.dc_acct_details ad, invdb.dc_acct_owners_details aod
 where ad.acctnum=atd.acctnum and aod.acctnum=atd.acctnum and aod.ownership in ('AOPRIMARY','AOCUSTODIAN') order by acctnum desc;
