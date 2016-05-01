DROP PROCEDURE `sp_upload_unbundld_commission`;

DELIMITER $$
CREATE  PROCEDURE `sp_upload_unbundld_commission`(
)
BEGIN 

   begin
		delete from `unbundld_commission`
		where concat(`clientAccountID`, `symbol`, `reportDate`, `tradeID`) 
				in (select concat(`clientAccountID`,`symbol`,replace(substr(`uc`.`reportDate`,1,10),'-',''),`tradeID`)
					from `tmp_unbundld_commissions` as `uc`)
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
				SELECT `uc`.`clientAccountID`,
					`uc`.`currencyPrimary`,
					`uc`.`assetClass`,
					`uc`.`fxRateToBase`,
					`uc`.`symbol`,
					`uc`.`description`,
					replace(substr(`uc`.`reportDate`,1,10),'-','') as reportDate,
					trim(substring(`uc`.`reportDate`,12,20)) as reportTime,
					`uc`.`exchange`,
					`uc`.`buy_sell`,
					`uc`.`quantity`,
					`uc`.`price`,
					`uc`.`tradeID`,
					`uc`.`totalcommission`,
					`uc`.`brokerExecutionCharge`,
					`uc`.`brokerClearingCharge`,
					`uc`.`thirdPartyExecutionCharge`,
					`uc`.`thirdPartyRegulatoryCharge`,
					`uc`.`other`
				FROM `tmp_unbundld_commissions` as `uc`;

   end;

END$$
DELIMITER ;