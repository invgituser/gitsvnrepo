DROP PROCEDURE IF EXISTS `invdb`.`sel_executedTrades`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_executedTrades`(
 	IN p_acctnum  bigint(20)
)
BEGIN

	SELECT `ext_transaction`.`acctnum`,
		`ext_transaction`.`clientAccountID`,
		`ext_transaction`.`ticker`,
		`ext_transaction`.`confirmNumber`,
		`ext_transaction`.`transactionSource`,
		`ext_transaction`.`transactionType`,
		`ext_transaction`.`transactionStatus`,
		`ext_transaction`.`controlNumber`,
		`ext_transaction`.`quantity`,
		`ext_transaction`.`price`,
		`ext_transaction`.`netAmount`,
		`ext_transaction`.`commission`,
		`ext_transaction`.`otherFees`,
		`ext_transaction`.`tradeDate`,
		`ext_transaction`.`settDate`,
		`ext_transaction`.`voidDate`,
		`ext_transaction`.`comment`,
		`ext_transaction`.`currencyPrimary`,
		`ext_transaction`.`fxRateToBase`,
		datediff(now(),STR_TO_DATE(`ext_transaction`.`tradeDate`,'%Y%m%d')) as days_last_executed
	FROM `invdb`.`ext_transaction`
    WHERE `ext_transaction`.`acctnum` = p_acctnum
    ORDER BY `ext_transaction`.`ticker`, `ext_transaction`.`tradeDate` desc;


END$$
DELIMITER ;
