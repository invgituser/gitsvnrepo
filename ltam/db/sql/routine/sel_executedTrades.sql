DROP PROCEDURE IF EXISTS `sel_executedTrades`;

DELIMITER $$
CREATE PROCEDURE `sel_executedTrades`(
 	IN p_acctnum  bigint(20)
)
BEGIN
	SELECT `t`.`clientAccountID`,
		`t`.`currencyPrimary`,
		`t`.`assetClass`,
		`t`.`fxRateToBase`,
		`t`.`symbol`,
		`t`.`description`,
		`t`.`tradeID`,
		`t`.`reportDate`,
		`t`.`quantity`,
		`t`.`tradeprice`,
		`t`.`proceed`,
		`t`.`ibcommission`,
		datediff(now(),STR_TO_DATE(t.reportDate,'%Y%m%d')) as days_last_executed
	FROM `trades` as `t`,
		 IB_Accounts ib
	WHERE ib.IB_acctnum = `t`.`clientAccountID`
	AND   ib.acctnum = p_acctnum
	ORDER BY `t`.`symbol`, `t`.`reportDate` desc
	;

END$$
DELIMITER ;
