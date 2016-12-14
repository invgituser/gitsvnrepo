use invdb;
DROP PROCEDURE IF EXISTS `invdb`.`save_tddc_TD_transfer_details`;
DELIMITER $$
CREATE PROCEDURE `save_tddc_TD_transfer_details`(
  `p_acctnum` bigint(20),
  `p_reqId` bigint(20),
  `p_accountTitle` varchar(45),
  `p_firmName` varchar(45),
  `p_primaryContact` varchar(45),
  `p_priorFirmName` varchar(100),
  `p_firmAccountNo` varchar(100),
  `p_retailAccountNumber` varchar(45),
  `p_advisorID` varchar(40),
  `p_removeAdvisor` varchar(1),
  `p_addAdvisor` varchar(1),
  `p_ssn` varchar(40),
  `p_createdBy` varchar(45)
)
BEGIN
  DECLARE tfound Integer;
  
  SELECT count(*)
  INTO tfound
  FROM `dc_td_transfer_details`
  WHERE `acctnum` = `p_acctnum`;
  
  
  IF (IFNULL(tfound,0) = 0)
  THEN
	  INSERT INTO `dc_td_transfer_details`
			(`acctnum`,
			   `reqId`,
				`accountTitle`,
				`firmName`,
				`primaryContact`,
				`priorFirmName`,
                `firmAccountNo`,
				`retailAccountNumber`,
				`advisorID`,
				`removeAdvisor`,
				`addAdvisor`,
				`ssn`,
				`created`,
				`createdBy`)
		VALUES
			(`p_acctnum`,
			`p_reqId`,
			`p_accountTitle`,
			`p_firmName`,			
			`p_primaryContact`,
			`p_priorFirmName`,
            `p_firmAccountNo`,
			`p_retailAccountNumber`,
			`p_advisorID`,
			`p_removeAdvisor`,
			`p_addAdvisor`,
            `p_ssn`,
			now(),
			`p_createdBy`);

  ELSE
		  UPDATE `dc_td_transfer_details`
			SET
				`accountTitle`	= `p_accountTitle`,
				`firmName`	= `p_firmName`,				
				`primaryContact`	= `p_primaryContact`,
				`priorFirmName` = `p_priorFirmName`,
                `firmAccountNo` = `p_firmAccountNo`,
				`retailAccountNumber`	= `p_retailAccountNumber`,
				`advisorID`		= `p_advisorID`,
				`removeAdvisor`	= `p_removeAdvisor`,
				`addAdvisor`	= `p_addAdvisor`,
                `ssn`	= `p_ssn`,
				`updated`			= now(),
				`updatedBy`			= `p_createdBy`,
                `reqId`=`p_reqId`
			WHERE `acctnum`	= `p_acctnum` 
            ;

		
  END IF;
  

 END$$
DELIMITER ;