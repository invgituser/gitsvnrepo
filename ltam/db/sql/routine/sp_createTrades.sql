DROP PROCEDURE IF EXISTS `sp_createTrades`;

DELIMITER $$
CREATE PROCEDURE `sp_createTrades`(
	p_acctnum BIGINT(20))
BEGIN

	IF (p_acctnum is null)
		THEN
			INSERT INTO `user_trade_log`
			(`acctnum`,
			`clientAccountID`,
			`tradeStatus`,
			`tradedate`,
			`ticker`,
			`action`,
			`sectype`,
			`exchange`,
			`currency`,
			`timeinforce`,
			`qty`,
			`tradeprice`,
			`investmentamount`,
			`tradeID`,
			`ordertype`,
			`confirmationID`,
			`created`,
			`lastupdated`)
			SELECT 
				`acctnum`,
				`clientAccountID`,
				'P' as tradeStatus,
				IFNULL(`created`,now()) as `tradedate`,
				`ticker` as ticker,
				case when (`tradeQty` >= 0) THEN 'BUY'
					 else 'SELL'
				end as action,
				'STK' as sectype,
				'ANY' as exchange,
				'USD' as currency,
				'DAY' as timeinforce,
				`curQty` as qty,
				CASE WHEN (upper(ticker) = 'CASH')
							THEN 1.00
					 ELSE `curPrice`
				END as price,
				`curValue` as investmentamount,
				concat(`ticker`,DATE_FORMAT(now(),'%Y-%m-%d')) as `tradeID`,
				'LMT' as ordertype,
				null as confirmationID,
				now() as created,
				null as lastupdated
			FROM rebalance_trade
			WHERE `processed` = 'N';
		ELSE 
			INSERT INTO `user_trade_log`
			(`acctnum`,
			`clientAccountID`,
			`tradeStatus`,
			`tradedate`,
			`ticker`,
			`action`,
			`sectype`,
			`exchange`,
			`currency`,
			`timeinforce`,
			`qty`,
			`tradeprice`,
			`investmentamount`,
			`tradeID`,
			`ordertype`,
			`confirmationID`,
			`created`,
			`lastupdated`)
			SELECT 
				`acctnum`,
				`clientAccountID`,
				'P' as tradeStatus,
				IFNULL(`created`,now()) as `tradedate`,
				`ticker` as ticker,
				case when (`tradeQty` >= 0) THEN 'BUY'
					 else 'SELL'
				end as action,
				'STK' as sectype,
				'ANY' as exchange,
				'USD' as currency,
				'DAY' as timeinforce,
				`curQty` as qty,
				CASE WHEN (upper(ticker) = 'CASH')
							THEN 1.00
					 ELSE `curPrice`
				END as price,
				`curValue` as investmentamount,
				concat(`ticker`,DATE_FORMAT(now(),'%Y-%m-%d')) as `tradeID`,
				'LMT' as ordertype,
				null as confirmationID,
				now() as created,
				null as lastupdated
			FROM rebalance_trade
			WHERE `processed` = 'N'
			AND acctnum = p_acctnum;
	END IF;

	CALL `sel_displayTrades2Execute`();

END$$
DELIMITER ;
