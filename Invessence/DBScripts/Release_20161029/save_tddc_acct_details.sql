DROP PROCEDURE IF EXISTS `invdb`.`save_tddc_acct_details`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`save_tddc_acct_details`(
  `p_acctnum` bigint(20),
  `p_clientAccountID` varchar(45),
  `p_caseNumber` varchar(45),
  `p_advisorId` bigint(20),
  `p_acctTypeId` varchar(45),
  `p_cashSweepVehicleChoiceId` varchar(45),
  `p_divIntPrefId` varchar(45),
  `p_monthStmtId` varchar(45),
  `p_tradConfId` varchar(45),
  `p_dupStatement` char(1),
  `p_dupTradeConfirm` char(1),
  `p_proxyAuthorizationId` varchar(45),
  `p_optoutRegulatory` char(1),
  `p_optoutBeneficiary` char(1),
  `p_optoutFunding` char(1),
  `p_optoutRecurring` char(1),
  `p_createdBy` varchar(45)
  )
BEGIN
		INSERT INTO `dc_acct_details`
			(`acctnum`,
			`clientAccountID`,
			`caseNumber`,
			`advisorId`,
			`acctTypeId`,
			`cashSweepVehicleChoiceId`,
			`divIntPrefId`,
			`monthStmtId`,
			`tradConfId`,
			`dupStatement`,
			`dupTradeConfirm`,
			`proxyAuthorizationId`,
            `optoutRegulatory`, 
            `optoutBeneficiary`, 
            `optoutFunding`, 
            `optoutRecurring`,
			`created`,
			`createdBy`)
		VALUES
			(`p_acctnum`,
			`p_clientAccountID`,
			`p_caseNumber`,
			IFNULL(`p_advisorId`,1),
			`p_acctTypeId`,
			`p_cashSweepVehicleChoiceId`,
			`p_divIntPrefId`,
			`p_monthStmtId`,
			`p_tradConfId`,
			`p_dupStatement`,
			`p_dupTradeConfirm`,
			`p_proxyAuthorizationId`,
			`p_optoutRegulatory`, 
            `p_optoutBeneficiary`, 
            `p_optoutFunding`, 
            `p_optoutRecurring`,
			now(),
			`p_createdBy`
            )
		ON DUPLICATE KEY UPDATE
			`clientAccountID`	= `p_clientAccountID`
			,`caseNumber`		= `p_caseNumber`
			,`advisorId`		= IFNULL(`p_advisorId`,1)
			,`acctTypeId`		= `p_acctTypeId`
			,`cashSweepVehicleChoiceId` = `p_cashSweepVehicleChoiceId`
			,`divIntPrefId`		= `p_divIntPrefId`
			,`monthStmtId`		= `p_monthStmtId`
			,`tradConfId`		= `p_tradConfId`
			,`dupStatement`		= `p_dupStatement`
			,`dupTradeConfirm`	= `p_dupTradeConfirm`
			,`proxyAuthorizationId` = `p_proxyAuthorizationId`
            ,`optoutRegulatory`=`p_optoutRegulatory`
            ,`optoutBeneficiary`=`p_optoutBeneficiary`
            ,`optoutFunding`=`p_optoutFunding`
            ,`optoutRecurring`=`p_optoutRecurring`
			,`updated`			= now()
			,`updatedBy`		= `p_createdBy`
		;
			
	  


END$$
DELIMITER ;