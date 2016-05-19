DROP PROCEDURE IF Exists `sp_updateExecutedTrades`;


DELIMITER $$
CREATE PROCEDURE `sp_updateExecutedTrades`()
BEGIN

	begin
		update `user_trade_log`
			set tradeStatus = 'T'
			  , lastupdated = now()
		where tradeStatus = 'P';
	end;

END$$
DELIMITER ;
