DROP PROCEDURE IF EXISTS `sp_upload_unbndld_commission`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_unbndld_commission`(
)
BEGIN 

   begin
		delete from `unbundld_commission`
		where concat(`clientAccountID`, `symbol`, `reportDate`, `tradeID`) 
				in (select concat(`clientAccountID`,`symbol`,replace(substr(`tmp_unbundld_commission`.`reportDate`,1,10),'-',''),`tradeID`)
					from `tmp_unbundld_commission`)
		;

				INSERT INTO `unbundld_commission`
				(`clientAccountID`,
				`currencyPrimary`,
				`assetClass`,
				`fxRateToBase`,
				`symbol`,
				`description`,
				`reportDate`,
				`reportTime`,
				`exchange`,
				`buy_sell`,
				`quantity`,
				`price`,
				`tradeID`,
				`totalcommission`,
				`brokerExecutionCharge`,
				`brokerClearingCharge`,
				`thirdPartyExecutionCharge`,
				`thirdPartyRegulatoryCharge`,
				`other`)
				SELECT `tmp_unbundld_commission`.`clientAccountID`,
					`tmp_unbundld_commission`.`currencyPrimary`,
					`tmp_unbundld_commission`.`assetClass`,
					`tmp_unbundld_commission`.`fxRateToBase`,
					`tmp_unbundld_commission`.`symbol`,
					`tmp_unbundld_commission`.`description`,
					replace(substr(`tmp_unbundld_commission`.`reportDate`,1,10),'-','') as reportDate,
					trim(substring(`tmp_unbundld_commission`.`reportTime`,12)) as reportTime,
					`tmp_unbundld_commission`.`exchange`,
					`tmp_unbundld_commission`.`buy_sell`,
					`tmp_unbundld_commission`.`quantity`,
					`tmp_unbundld_commission`.`price`,
					`tmp_unbundld_commission`.`tradeID`,
					`tmp_unbundld_commission`.`totalcommission`,
					`tmp_unbundld_commission`.`brokerExecutionCharge`,
					`tmp_unbundld_commission`.`brokerClearingCharge`,
					`tmp_unbundld_commission`.`thirdPartyExecutionCharge`,
					`tmp_unbundld_commission`.`thirdPartyRegulatoryCharge`,
					`tmp_unbundld_commission`.`other`
				FROM `tmp_unbundld_commission`;

   end;

END$$
DELIMITER ;
