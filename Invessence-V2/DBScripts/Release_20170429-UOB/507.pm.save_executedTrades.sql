DROP PROCEDURE IF EXISTS `invdb`.`save_executedTrades`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`save_executedTrades`(
	p_acctnum 	BIGINT(20)
)
BEGIN
	DECLARE today	VARCHAR(10);
	begin
            
		INSERT INTO `invdb`.`user_trade_log`
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
		`firmAccount`,
		`created`,
		`lastupdated`)
        SELECT 
			`trade_process_identifier`.`acctnum`,
            `ext_acct_info`.`clientAccountID`,
            'P' as tradeStatus,
            DATE_FORMAT(now(),'%Y%m%d') as tradedate,
            `rebal`.`ticker`,
            CASE WHEN (`rebal`.`curQty` < 0) THEN 'SELL'
				 ELSE 'BUY'
			END as `action`,
            'STK' as `sectype`,
            `sec_master`.`exchange`,
            `sec_master`.`base_currency` as `currency`,
            'DAY' as `timeinforce`,
            ABS(`rebal`.`curQty`) as `qty`,
            `rebal`.`curPrice` as `tradeprice`,
            ABS(`rebal`.`curValue`) as `investmentamount`,
            CONCAT(`rebal`.`ticker`,DATE_FORMAT(now(),'%Y%m%d%H%i%s')) as `tradeID`,
            'LMT' as `ordertype`,
            null as `confirmationID`,
            null as `firmAccount`,
            now() as `created`,
            null as `lastupdated`
		FROM `trade_process_identifier`
        INNER JOIN `ext_acct_info`
        ON (`ext_acct_info`.`acctnum` = `trade_process_identifier`.`acctnum`
			AND `ext_acct_info`.`status` in ('A'))
		INNER JOIN `rebalance_trade` as `rebal`
        ON (`rebal`.`acctnum` = `trade_process_identifier`.`acctnum`)
        INNER JOIN `sec_master`
        ON (`sec_master`.`ticker` = `rebal`.`ticker`)
        WHERE IFNULL(`trade_process_identifier`.`processStatus`,'N') in ('R')
        AND  `trade_process_identifier`.`acctnum` = p_acctnum
    	;

    end;
    
    begin
		update save_trade_process_identifier
			set tradeStatus = 'V', processStatus = 'S'
		where acctnum = p_acctnum;
    end;
END$$
DELIMITER ;
