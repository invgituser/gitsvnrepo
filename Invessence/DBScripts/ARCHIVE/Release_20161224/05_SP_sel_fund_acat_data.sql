use invdb;

DROP PROCEDURE IF EXISTS `invdb`.`sel_fund_acat_data`;

DELIMITER $$
CREATE PROCEDURE `sel_fund_acat_data`(
 `p_acctnum` bigint(20)
  )
BEGIN
SELECT `dc_acct_transfer_details`.`acctnum`,
		`dc_acct_transfer_details`.`reqId`,
		`dc_acct_transfer_details`.`accountTitle`,
		`dc_acct_transfer_details`.`ssn`,
		`dc_acct_transfer_details`.`accountType`,
		`dc_acct_transfer_details`.`eePlanType`,
		`dc_acct_transfer_details`.`otherAccountType`,
		`dc_acct_transfer_details`.`fromAccountTitle`,
		`dc_acct_transfer_details`.`accountNumber2`,
		`dc_acct_transfer_details`.`fromFirmAddress`,
		`dc_acct_transfer_details`.`fromFirmPhoneNumber`,
		`dc_acct_transfer_details`.`fromAccountType`,
		`dc_acct_transfer_details`.`fromEEPlanType`,
		`dc_acct_transfer_details`.`simpleFunded`,
		`dc_acct_transfer_details`.`fromOtherAccountType`,
		`dc_acct_transfer_details`.`transferTypeId`,
		case when `dc_acct_transfer_details`.`contraFirmList` in (select value from dc_m_firm_lookup)
        then (select displayName from dc_m_firm_lookup where value=`dc_acct_transfer_details`.`contraFirmList`)
        else `dc_acct_transfer_details`.`contraFirmList` end as contraFirmList,
		`dc_acct_transfer_details`.`createdBy`,
        `dc_acct_transfer_details`.`othercontraFirmList`
	FROM `dc_acct_transfer_details` where  `dc_acct_transfer_details`.`acctnum`=`p_acctnum`  ; 
 END$$
DELIMITER ;