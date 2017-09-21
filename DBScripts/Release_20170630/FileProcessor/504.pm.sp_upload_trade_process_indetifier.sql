DROP PROCEDURE `invdb`.`sp_upload_trade_process_indetifier`;


DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_upload_trade_process_indetifier`(
)
BEGIN

	-- Dont delete trades that are marked as 'N' keep them for the original date it was inserted.
	DELETE FROM `invdb`.`trade_process_indetifier`
    WHERE tradeStatus not in ('N');
    
    INSERT INTO `invdb`.`trade_process_identifier`
	(`acctnum`,`tradeStatus`,`processStatus`,`reason`,`created`)
	SELECT DISTINCT
			`ext_acct_info`.`acctnum`
		  , 'N' `tradeStatus`
		  , 'N' `processStatus`
          , 'Funded' `reason`
          , now()
    FROM  `invdb`.`ext_acct_info`
    WHERE `ext_acct_info`.`status` = 'O'
    AND   DATE_FORMAT(`ext_acct_info`.`created`,'%Y%m%d') > `invdb`.`FUNCT_GET_SWITCH`('BROKER_BDATE')
    AND   `ext_acct_info`.`clientAccountID` in (select `ext_nav_daily`.`clientAccountID` from  `invdb`.`ext_nav_daily`)
    AND   `ext_acct_info`.`acctnum` not in (select `trade_process_identifier`.`acctnum` FROM `trade_process_identifier`)
    ;
  
  
END$$
DELIMITER ;
