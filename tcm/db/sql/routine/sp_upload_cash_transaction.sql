DROP PROCEDURE IF EXISTS `sp_upload_cash_transaction`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_cash_transaction`(
)
BEGIN 

   begin
		delete from cash_transaction
		where concat(`clientAccountID`,`type`,`reportDate`,`tradeID`,`description`) 
				in (select concat(`clientAccountID`,`type`,replace(`reportDate`,'-',''),`tradeID`,`description`)
																  from tmp_cash_transaction)
		;
		INSERT INTO `invdb`.`cash_transaction`
		(`clientAccountID`,
		`currencyPrimary`,
		`assetClass`,
		`fxRateToBase`,
		`symbol`,
		`description`,
		`reportDate`,
		`amount`,
		`type`,
		`tradeID`,
		`code`)
		SELECT `tmp_cash_transaction`.`clientAccountID`,
			`tmp_cash_transaction`.`currencyPrimary`,
			`tmp_cash_transaction`.`assetClass`,
			`tmp_cash_transaction`.`fxRateToBase`,
			`tmp_cash_transaction`.`symbol`,
			`tmp_cash_transaction`.`description`,
			replace(`tmp_cash_transaction`.`reportDate`,'-',''),
			`tmp_cash_transaction`.`amount`,
			`tmp_cash_transaction`.`type`,
			`tmp_cash_transaction`.`tradeID`,
			`tmp_cash_transaction`.`code`
		FROM `invdb`.`tmp_cash_transaction`;

   end;

END$$
DELIMITER ;
