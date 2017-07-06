DROP PROCEDURE IF EXISTS `invdb`.`sel_TradeSummaryDetail`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_TradeSummaryDetail`(
	p_logonid 	BIGINT(20),
    p_filter	VARCHAR(1)
)
BEGIN
	begin
    
       SELECT 
			`trade_process_identifier`.`acctnum`,
            `ext_acct_info`.`clientAccountID`,
            `ext_acct_info`.`applicantFName`,
            `ext_acct_info`.`applicantLName`,
			`trade_process_identifier`.`tradeStatus`,
			`trade_process_identifier`.`processStatus`,
            `ext_nav`.`total`  as `totalInvestment`,
			SUM(`rebal`.`curQty`) sumCurQty,
			SUM(`rebal`.`curValue`) as sumCurValue,
			SUM(`rebal`.`holdingQty`) sumholdingQty,
			SUM(`rebal`.`holdingValue`) as sumholdingValue,
			SUM(`rebal`.`newQty`) sumNewQty,
			SUM(`rebal`.`newValue`) as sumNewValue
		FROM `trade_process_identifier`
        INNER JOIN `user_trade_profile`
        ON (`user_trade_profile`.`acctnum` = `trade_process_identifier`.`acctnum`)
        INNER JOIN `ext_acct_info`
        ON (`ext_acct_info`.`acctnum` = `trade_process_identifier`.`acctnum`
			AND `ext_acct_info`.`status` in ('A'))
		INNER JOIN `ext_nav`
        ON (`ext_nav`.`clientAccountID` = `ext_acct_info`.`clientAccountID`
        AND `ext_nav`.`reportDate` = `FUNCT_GET_SWITCH`('BROKER_BDATE'))
        LEFT JOIN `user_trade_log`
        ON (`user_trade_log`.`acctnum` = `trade_process_identifier`.`acctnum`)
        LEFT JOIN `rebalance_trade` as `rebal`
        ON (`rebal`.`acctnum` = `trade_process_identifier`.`acctnum`)
        WHERE IFNULL(`trade_process_identifier`.`processStatus`,'N') = p_filter
        AND   lower(IFNULL(`user_trade_profile`.`advisor`,'Invessence')) like (select lower(`user_advisor_access`.`advisor`) 
																		 from `user_advisor_access` 
                                                                         where `user_advisor_access`.`logonid` = p_logonid)
        GROUP BY
			`trade_process_identifier`.`acctnum`,
            `ext_acct_info`.`clientAccountID`,
            `ext_acct_info`.`applicantFName`,
            `ext_acct_info`.`applicantLName`,
			`trade_process_identifier`.`tradeStatus`,
			`trade_process_identifier`.`processStatus`,
            `ext_nav`.`total`
    	;

    end;
END$$
DELIMITER ;
