DROP PROCEDURE IF EXISTS `sel_collectTradeCustomerProfile`;

DELIMITER $$
CREATE PROCEDURE `sel_collectTradeCustomerProfile`(
	p_acctnum 	BIGINT(20)
)
BEGIN
	begin
        SELECT 
			`trade_process_identifier`.`acctnum`,
            `ext_acct_info`.`clientAccountID`,
            `ext_acct_info`.`applicantFName`,
            `ext_acct_info`.`applicantLName`,
            `user_trade_profile`.`advisor`,
            `user_trade_profile`.`theme`,
            `user_trade_profile`.`acctType`,
            `ext_nav`.`total` as `investment`,
            `user_trade_profile`.`keepLiquid`,
            `user_trade_profile`.`longTermGoal`,
            `user_trade_profile`.`stayInvested`,
			`user_trade_profile`.`calcModel`,
            `user_trade_profile`.`riskIndex`,
            `user_trade_profile`.`assetIndex`,
            `user_trade_profile`.`portfolioIndex`,
            `user_trade_profile`.`age`,
            `user_trade_profile`.`horizon`,
			`user_trade_profile`.`taxable`
		FROM `trade_process_identifier`
		INNER JOIN `user_trade_profile`
        ON (`user_trade_profile`.`acctnum` = `trade_process_identifier`.`acctnum`)
        INNER JOIN `ext_acct_info`
        ON (`ext_acct_info`.`acctnum` = `trade_process_identifier`.`acctnum`
			AND `ext_acct_info`.`status` in ('A'))
		INNER JOIN `ext_nav`
        ON (`ext_nav`.`clientAccountID` = `ext_acct_info`.`clientAccountID`
			AND `ext_nav`.`reportDate` = `FUNCT_GET_SWITCH`('BROKER_BDATE'))
		WHERE `trade_process_identifier`.`acctnum` = p_acctnum
     	;

    end;
END$$
DELIMITER ;
