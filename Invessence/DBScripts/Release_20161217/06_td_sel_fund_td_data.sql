use invdb;
DROP PROCEDURE IF EXISTS `invdb`.`sel_fund_td_data`;
DELIMITER $$
CREATE DEFINER=`admin`@`localhost` PROCEDURE `sel_fund_td_data`(
	 `p_acctnum` bigint(20)
  )
BEGIN
	SELECT `dc_td_transfer_details`.`acctnum`,
		`dc_td_transfer_details`.`reqId`,
		`dc_td_transfer_details`.`accountTitle`,
		`dc_td_transfer_details`.`firmName`,
		`dc_td_transfer_details`.`primaryContact`,
		`dc_td_transfer_details`.`priorFirmName`,
        `dc_td_transfer_details`.`firmAccountNo`,
		`dc_td_transfer_details`.`retailAccountNumber`,
		`dc_td_transfer_details`.`advisorID`,
		`dc_td_transfer_details`.`removeAdvisor`,
		`dc_td_transfer_details`.`addAdvisor`,
		`dc_td_transfer_details`.`ssn`,
		`dc_td_transfer_details`.`createdBy`
	FROM `dc_td_transfer_details`
	WHERE `dc_td_transfer_details`.`acctnum` = `p_acctnum`  ;

 END$$
DELIMITER ;