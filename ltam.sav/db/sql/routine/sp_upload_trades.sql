DROP PROCEDURE IF EXISTS `sp_upload_trades`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_trades`(
)
BEGIN 

   begin
		delete from `trades`
		where concat(`clientAccountID`,`symbol`,`reportDate`,`tradeID`) 
				in (select concat(`clientAccountID`,`symbol`,replace(`tmp_trades`.`reportDate`,'-',''),`tradeID`)
					from `tmp_trades`)
		;

				INSERT INTO `trades`
				(`clientAccountID`,
				`currencyPrimary`,
				`assetClass`,
				`fxRateToBase`,
				`symbol`,
				`description`,
				`tradeID`,
				`reportDate`,
				`quantity`,
				`tradeprice`,
				`proceed`,
				`ibcommission`,
				`levelofDetail`)
				SELECT `tmp_trades`.`clientAccountID`,
					`tmp_trades`.`currencyPrimary`,
					`tmp_trades`.`assetClass`,
					`tmp_trades`.`fxRateToBase`,
					`tmp_trades`.`symbol`,
					`tmp_trades`.`description`,
					`tmp_trades`.`tradeID`,
					replace(`tmp_trades`.`reportDate`,'-',''),
					`tmp_trades`.`quantity`,
					`tmp_trades`.`tradeprice`,
					`tmp_trades`.`proceed`,
					`tmp_trades`.`ibcommission`,
					`tmp_trades`.`levelofDetail`
				FROM `tmp_trades`;

   end;

END$$
DELIMITER ;
