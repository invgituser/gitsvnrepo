UPDATE `invdb`.`dc_m_lookup` 
	SET `displayName`='Joint (JTWROS)' 
WHERE `lookupSet`='ACCTTYPE' and`lookupCode`='ACJOINT';

update `invdb`.`user_trade_profile`, `invdb`.`dc_acct_details`, `invdb`.`dc_m_lookup`
 set `user_trade_profile`.`acctType` = upper(`dc_m_lookup`.`displayName`)
WHERE `dc_acct_details`.`acctnum` = `user_trade_profile`.`acctnum`
AND   `dc_acct_details`.`acctTypeId` = `dc_m_lookup`.`lookupCode` 
AND   `dc_m_lookup`.`lookupSet` = 'ACCTTYPE'
AND   `user_trade_profile`.`managed` not in ('N')
;

update `invdb`.`user_trade_profile`
set accttype = 'Joint (JTWROS)'
where accttype like 'JOINT%';


update `invdb`.`user_trade_profile`
	set `status` = `managed`
;

update `invdb`.`user_trade_profile`
	set `managed` = 'A'
where `managed` not in ('N')
;

update `invdb`.`user_trade_profile`, `invdb`.`ext_acct_info`
	set `user_trade_profile`.`clientAccountID` =  `ext_acct_info`.`clientAccountID`
where `user_trade_profile`.`acctnum` = `ext_acct_info`.`acctnum`;