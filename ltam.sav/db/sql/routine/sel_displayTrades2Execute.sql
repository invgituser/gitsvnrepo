DROP PROCEDURE IF EXISTS `sel_displayTrades2Execute`;

DELIMITER $$
CREATE PROCEDURE `sel_displayTrades2Execute`(
)
BEGIN
	begin
		SELECT `user_trade_log`.`acctnum`,
			`user_trade_log`.`clientAccountID`,
			`user_trade_log`.`tradeStatus`,
			`user_trade_log`.`tradedate`,
			`user_trade_log`.`ticker`,
			`user_trade_log`.`action`,
			`user_trade_log`.`sectype`,
			`user_trade_log`.`exchange`,
			`user_trade_log`.`currency`,
			`user_trade_log`.`timeinforce`,
			`user_trade_log`.`qty`,
			`user_trade_log`.`tradeprice`,
			`user_trade_log`.`investmentamount`,
			`user_trade_log`.`tradeID`,
			`user_trade_log`.`ordertype`,
			`user_trade_log`.`confirmationID`,
			`user_trade_log`.`created`,
			`user_trade_log`.`lastupdated`
		FROM `user_trade_log`
		WHERE `user_trade_log`.`tradeStatus` = 'P'
		AND `user_trade_log`.`qty` <> 0
		AND upper(`user_trade_log`.ticker) not in ('CASH');
	end;
END$$
DELIMITER ;
