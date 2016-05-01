DROP PROCEDURE IF EXISTS `admin_sel_collectIBData`;

DELIMITER $$
CREATE PROCEDURE `admin_sel_collectIBData`(
	IN p_filter	VARCHAR(20)
)
BEGIN
	IF (p_filter is null)
		THEN set p_filter = "All";
	END IF;

	IF (p_filter = "All")
		then
			SELECT `tmp_IB_Accounts`.`clientAccountID`,
				`tmp_IB_Accounts`.`accountAlias`,
				`tmp_IB_Accounts`.`currencyPrimary`,
				`tmp_IB_Accounts`.`name`,
				`tmp_IB_Accounts`.`accountType`,
				`tmp_IB_Accounts`.`customerType`,
				`tmp_IB_Accounts`.`accountCapabilities`,
				`tmp_IB_Accounts`.`tradingPermissions`,
				`tmp_IB_Accounts`.`dateOpened`,
				`tmp_IB_Accounts`.`dateClosed`,
				`tmp_IB_Accounts`.`street`,
				`tmp_IB_Accounts`.`street2`,
				`tmp_IB_Accounts`.`city`,
				`tmp_IB_Accounts`.`state`,
				`tmp_IB_Accounts`.`country`,
				`tmp_IB_Accounts`.`postalCode`,
				`tmp_IB_Accounts`.`emailPrimary`,
				`tmp_IB_Accounts`.`emailSecondary`,
				`tmp_IB_Accounts`.`phoneNo`,
				`tmp_IB_Accounts`.`altPhoneNo`,
				IB_Cash_Info.startingCash,
				IB_Accounts.accountStatus as `status`,
				IB_Accounts.acctnum as acctnum
			FROM `tmp_IB_Accounts`
				 LEFT JOIN IB_Accounts
				 ON `tmp_IB_Accounts`.`clientAccountID` = IB_Accounts.IB_Acctnum
				 LEFT JOIN IB_Cash_Info
				 ON ( `tmp_IB_Accounts`.`clientAccountID` = IB_Cash_Info.`clientAccountID`
				 AND IB_Cash_Info.toDate = (select value from invessence_switch where name = 'BROKER_BDATE'))
			WHERE `tmp_IB_Accounts`.`clientAccountID` like 'U%'
			;
	end if;
	if (p_filter = "New")
		then
			SELECT `tmp_IB_Accounts`.`clientAccountID`,
				`tmp_IB_Accounts`.`accountAlias`,
				`tmp_IB_Accounts`.`currencyPrimary`,
				`tmp_IB_Accounts`.`name`,
				`tmp_IB_Accounts`.`accountType`,
				`tmp_IB_Accounts`.`customerType`,
				`tmp_IB_Accounts`.`accountCapabilities`,
				`tmp_IB_Accounts`.`tradingPermissions`,
				`tmp_IB_Accounts`.`dateOpened`,
				`tmp_IB_Accounts`.`dateClosed`,
				`tmp_IB_Accounts`.`street`,
				`tmp_IB_Accounts`.`street2`,
				`tmp_IB_Accounts`.`city`,
				`tmp_IB_Accounts`.`state`,
				`tmp_IB_Accounts`.`country`,
				`tmp_IB_Accounts`.`postalCode`,
				`tmp_IB_Accounts`.`emailPrimary`,
				`tmp_IB_Accounts`.`emailSecondary`,
				`tmp_IB_Accounts`.`phoneNo`,
				`tmp_IB_Accounts`.`altPhoneNo`,
				IB_Cash_Info.startingCash,
				IB_Accounts.accountStatus as `status`,
				IB_Accounts.acctnum as acctnum
			FROM `tmp_IB_Accounts`
				 LEFT JOIN IB_Accounts
				 ON `tmp_IB_Accounts`.`clientAccountID` = IB_Accounts.IB_Acctnum
				 LEFT JOIN IB_Cash_Info
				 ON (`tmp_IB_Accounts`.`clientAccountID` = IB_Cash_Info.`clientAccountID`
				 AND IB_Cash_Info.toDate = (select value from invessence_switch where name = 'BROKER_BDATE'))
			WHERE `tmp_IB_Accounts`.`clientAccountID` like 'U%'
			AND  IFNULL(IB_Accounts.accountStatus,'Pending') in ('Pending', 'Verify', 'Approved');
	end if;
	if (p_filter = "Traded")
		then
			SELECT `tmp_IB_Accounts`.`clientAccountID`,
				`tmp_IB_Accounts`.`accountAlias`,
				`tmp_IB_Accounts`.`currencyPrimary`,
				`tmp_IB_Accounts`.`name`,
				`tmp_IB_Accounts`.`accountType`,
				`tmp_IB_Accounts`.`customerType`,
				`tmp_IB_Accounts`.`accountCapabilities`,
				`tmp_IB_Accounts`.`tradingPermissions`,
				`tmp_IB_Accounts`.`dateOpened`,
				`tmp_IB_Accounts`.`dateClosed`,
				`tmp_IB_Accounts`.`street`,
				`tmp_IB_Accounts`.`street2`,
				`tmp_IB_Accounts`.`city`,
				`tmp_IB_Accounts`.`state`,
				`tmp_IB_Accounts`.`country`,
				`tmp_IB_Accounts`.`postalCode`,
				`tmp_IB_Accounts`.`emailPrimary`,
				`tmp_IB_Accounts`.`emailSecondary`,
				`tmp_IB_Accounts`.`phoneNo`,
				`tmp_IB_Accounts`.`altPhoneNo`,
				IB_Cash_Info.startingCash,
				IB_Accounts.accountStatus as `status`,
				IB_Accounts.acctnum as acctnum
			FROM `tmp_IB_Accounts`
				 LEFT JOIN IB_Accounts
				 ON `tmp_IB_Accounts`.`clientAccountID` = IB_Accounts.IB_Acctnum
				 LEFT JOIN IB_Cash_Info
				 ON (`tmp_IB_Accounts`.`clientAccountID` = IB_Cash_Info.`clientAccountID`
				 AND IB_Cash_Info.toDate = (select value from invessence_switch where name = 'BROKER_BDATE'))
			WHERE `tmp_IB_Accounts`.`clientAccountID` like 'U%'
			AND  IB_Accounts.accountStatus in ('Active');
	end if;
	if (p_filter = "Funded")
		then
			SELECT `tmp_IB_Accounts`.`clientAccountID`,
				`tmp_IB_Accounts`.`accountAlias`,
				`tmp_IB_Accounts`.`currencyPrimary`,
				`tmp_IB_Accounts`.`name`,
				`tmp_IB_Accounts`.`accountType`,
				`tmp_IB_Accounts`.`customerType`,
				`tmp_IB_Accounts`.`accountCapabilities`,
				`tmp_IB_Accounts`.`tradingPermissions`,
				`tmp_IB_Accounts`.`dateOpened`,
				`tmp_IB_Accounts`.`dateClosed`,
				`tmp_IB_Accounts`.`street`,
				`tmp_IB_Accounts`.`street2`,
				`tmp_IB_Accounts`.`city`,
				`tmp_IB_Accounts`.`state`,
				`tmp_IB_Accounts`.`country`,
				`tmp_IB_Accounts`.`postalCode`,
				`tmp_IB_Accounts`.`emailPrimary`,
				`tmp_IB_Accounts`.`emailSecondary`,
				`tmp_IB_Accounts`.`phoneNo`,
				`tmp_IB_Accounts`.`altPhoneNo`,
				IB_Cash_Info.startingCash,
				IB_Accounts.accountStatus as `status`,
				IB_Accounts.acctnum as acctnum
			FROM `tmp_IB_Accounts`
				 LEFT JOIN IB_Accounts
				 ON `tmp_IB_Accounts`.`clientAccountID` = IB_Accounts.IB_Acctnum
				 LEFT JOIN IB_Cash_Info
				 ON (`tmp_IB_Accounts`.`clientAccountID` = IB_Cash_Info.`clientAccountID`
				 AND IB_Cash_Info.toDate = (select value from invessence_switch where name = 'BROKER_BDATE'))
			WHERE `tmp_IB_Accounts`.`clientAccountID` like 'U%'
			AND  IB_Accounts.accountStatus in ('Funded');
	end if;

END;

