DROP PROCEDURE IF EXISTS `admin_sel_collectIBData`;

DELIMITER $$
CREATE PROCEDURE `admin_sel_collectIBData`(
	IN p_filter	VARCHAR(20)
)
BEGIN
	IF (p_filter is null)
		THEN set p_filter = 'ALL';
		ELSE set p_filter = upper(p_filter);
	END IF;

	IF (p_filter = 'ALL')
		then
			SELECT `IB_Accounts`.`acctnum`,
				`IB_Accounts`.`IB_acctnum` as `clientAccountID`,
				`IB_Accounts`.`IB_acctnum`,
				`IB_Accounts`.`email`,
				`IB_Accounts`.`lastName`,
				`IB_Accounts`.`firstName`,
				`IB_Accounts`.`accountName` as `name`,
				`IB_Accounts`.`accountStatus` as `status`,
				`IB_Accounts`.`dateOpened`,
				`IB_Accounts`.`dateClosed`,
				`IB_Accounts`.`customerType`,
				`IB_Accounts`.`accountCapabilities`,
				`IB_Accounts`.`residentState` as `state`,
				`cash_report`.`startingCash`,
				`IB_Accounts`.`created`,
				`IB_Accounts`.`updated`
			FROM `IB_Accounts`
				 LEFT JOIN cash_report
				 ON ( `IB_Accounts`.`IB_acctnum` = `cash_report`.`clientAccountID`
				 AND `cash_report`.toDate = (select value from invessence_switch where name = 'BROKER_BDATE'))
			WHERE `IB_Accounts`.`IB_acctnum` like 'U%'
			;
	end if;
	if (p_filter = 'NEW')
		then
			SELECT `IB_Accounts`.`acctnum`,
				`IB_Accounts`.`IB_acctnum` as `clientAccountID`,
				`IB_Accounts`.`IB_acctnum`,
				`IB_Accounts`.`email`,
				`IB_Accounts`.`lastName`,
				`IB_Accounts`.`firstName`,
				`IB_Accounts`.`accountName` as `name`,
				`IB_Accounts`.`accountStatus` as `status`,
				`IB_Accounts`.`dateOpened`,
				`IB_Accounts`.`dateClosed`,
				`IB_Accounts`.`customerType`,
				`IB_Accounts`.`accountCapabilities`,
				`IB_Accounts`.`residentState` as `state`,
				`cash_report`.`startingCash`,
				`IB_Accounts`.`created`,
				`IB_Accounts`.`updated`
			FROM `IB_Accounts`
				 LEFT JOIN cash_report
				 ON ( `IB_Accounts`.`IB_acctnum` = `cash_report`.`clientAccountID`
				 AND `cash_report`.toDate = (select value from invessence_switch where name = 'BROKER_BDATE'))
			WHERE `IB_Accounts`.`IB_acctnum` like 'U%'
			AND  upper(IFNULL(IB_Accounts.accountStatus,'Pending')) in ('PENDING', 'VERIFY', 'APPROVED');
	end if;
	if (p_filter = 'TRADED' or p_filter = 'ACTIVE')
		then
			SELECT `IB_Accounts`.`acctnum`,
				`IB_Accounts`.`IB_acctnum` as `clientAccountID`,
				`IB_Accounts`.`IB_acctnum`,
				`IB_Accounts`.`email`,
				`IB_Accounts`.`lastName`,
				`IB_Accounts`.`firstName`,
				`IB_Accounts`.`accountName` as `name`,
				`IB_Accounts`.`accountStatus` as `status`,
				`IB_Accounts`.`dateOpened`,
				`IB_Accounts`.`dateClosed`,
				`IB_Accounts`.`customerType`,
				`IB_Accounts`.`accountCapabilities`,
				`IB_Accounts`.`residentState` as `state`,
				`cash_report`.`startingCash`,
				`IB_Accounts`.`created`,
				`IB_Accounts`.`updated`
			FROM `IB_Accounts`, `cash_report`
			WHERE `IB_Accounts`.`IB_acctnum` like 'U%'
			AND   `IB_Accounts`.`IB_acctnum` = `cash_report`.`clientAccountID`
			AND   `cash_report`.`toDate` = (select value from invessence_switch where name = 'BROKER_BDATE')
			AND  upper(`IB_Accounts`.`accountStatus`) in ('ACTIVE');
	end if;
	if (p_filter = 'FUNDED')
		then
			SELECT `IB_Accounts`.`acctnum`,
				`IB_Accounts`.`IB_acctnum` as `clientAccountID`,
				`IB_Accounts`.`IB_acctnum`,
				`IB_Accounts`.`email`,
				`IB_Accounts`.`lastName`,
				`IB_Accounts`.`firstName`,
				`IB_Accounts`.`accountName` as `name`,
				`IB_Accounts`.`accountStatus` as `status`,
				`IB_Accounts`.`dateOpened`,
				`IB_Accounts`.`dateClosed`,
				`IB_Accounts`.`customerType`,
				`IB_Accounts`.`accountCapabilities`,
				`IB_Accounts`.`residentState` as `state`,
				`IB_Cash_Info`.`startingCash`,
				`IB_Accounts`.`created`,
				`IB_Accounts`.`updated`
			FROM `IB_Accounts`
				 LEFT JOIN IB_Cash_Info
				 ON ( `IB_Accounts`.`IB_acctnum` = IB_Cash_Info.`clientAccountID`
				 AND IB_Cash_Info.toDate = (select value from invessence_switch where name = 'BROKER_BDATE'))
			WHERE `IB_Accounts`.`IB_acctnum` like 'U%'
			AND  IB_Accounts.accountStatus in (p_filter);
	end if;
	if (p_filter = 'CLOSED')
		then
			SELECT `IB_Accounts`.`acctnum`,
				`IB_Accounts`.`IB_acctnum` as `clientAccountID`,
				`IB_Accounts`.`IB_acctnum`,
				`IB_Accounts`.`email`,
				`IB_Accounts`.`lastName`,
				`IB_Accounts`.`firstName`,
				`IB_Accounts`.`accountName` as `name`,
				`IB_Accounts`.`accountStatus` as `status`,
				`IB_Accounts`.`dateOpened`,
				`IB_Accounts`.`dateClosed`,
				`IB_Accounts`.`customerType`,
				`IB_Accounts`.`accountCapabilities`,
				`IB_Accounts`.`residentState` as `state`,
				`cash_report`.`startingCash`,
				`IB_Accounts`.`created`,
				`IB_Accounts`.`updated`
			FROM `IB_Accounts`
				 LEFT JOIN cash_report
				 ON ( `IB_Accounts`.`IB_acctnum` = `cash_report`.`clientAccountID`
				 AND `cash_report`.toDate = (select value from invessence_switch where name = 'BROKER_BDATE'))
			WHERE `IB_Accounts`.`IB_acctnum` like 'U%'
			AND  upper(IB_Accounts.accountStatus) in (p_filter);
	end if;
END$$
DELIMITER ;
