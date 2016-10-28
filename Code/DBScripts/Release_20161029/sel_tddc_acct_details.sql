DROP PROCEDURE IF EXISTS `sel_tddc_acct_details`;

DELIMITER $$
CREATE  PROCEDURE `sel_tddc_acct_details`(
  `p_acctnum` bigint(20)
)
BEGIN
	SELECT 
		`dc_acct_details`.`acctnum`,
		`dc_acct_details`.`clientAccountID`,
		`dc_acct_details`.`caseNumber`,
		`dc_acct_details`.`advisorId`,
		`dc_acct_details`.`acctTypeId`,
		`dc_acct_details`.`cashSweepVehicleChoiceId`,
		`dc_acct_details`.`divIntPrefId`,
		`dc_acct_details`.`monthStmtId`,
		`dc_acct_details`.`tradConfId`,
		`dc_acct_details`.`dupStatement`,
		`dc_acct_details`.`dupTradeConfirm`,
		`dc_acct_details`.`proxyAuthorizationId`,
        `dc_acct_details`.`optoutRegulatory`,
        `dc_acct_details`.`optoutBeneficiary`,
        `dc_acct_details`.`optoutFunding`,
        `dc_acct_details`.`optoutRecurring`,
		`dc_acct_details`.`createdBy`
	FROM `dc_acct_details`
    WHERE `dc_acct_details`.`acctnum` = `p_acctnum`;
	  
END$$
DELIMITER ;