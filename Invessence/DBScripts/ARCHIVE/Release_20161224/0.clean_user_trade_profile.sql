update `invdb`.`user_trade_profile`
set `user_trade_profile`.`status` = 'V'
where `user_trade_profile`.`acctnum` not in (select `acctnum` from `invdb`.`user_access_role` where `role` = 'OWNER');

update `invdb`.`user_trade_profile`
set `user_trade_profile`.`status` = 'N'
where `user_trade_profile`.`status` = 'V'
and `user_trade_profile`.`acctnum` in (select `acctnum` from `invdb`.`user_access_role` where `role` = 'OWNER');

update `invdb`.`user_trade_profile`, `invdb`.`dc_acct_owners_details`
	set `user_trade_profile`.`firstname` = `dc_acct_owners_details`.`firstName`
	,   `user_trade_profile`.`lastname` = `dc_acct_owners_details`.`lastName`
Where `user_trade_profile`.`acctnum` = `dc_acct_owners_details`.`acctnum`;

         
UPDATE `invdb`.`user_trade_profile`, `invdb`.`dc_acct_details`, `invdb`.`dc_m_lookup`
	set `user_trade_profile`.`acctType` = IFNULL(`dc_m_lookup`.`displayName`, `user_trade_profile`.`acctType`)
WHERE `user_trade_profile`.`acctnum` = `dc_acct_details`.`acctnum`
AND `dc_m_lookup`.`lookupSet` = 'ACCTTYPE'
AND `dc_m_lookup`.`lookupCode` = `dc_acct_details`.`acctTypeId`;

Update `invdb`.`user_trade_profile`
set `user_trade_profile`.`acctType` = null
where  `user_trade_profile`.`acctType` = 'Non-Taxable'

