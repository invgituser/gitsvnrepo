DROP PROCEDURE IF EXISTS `invdb`.`sel_TradeRebalDetail`;


DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_TradeRebalDetail`(
	p_acctnum BIGINT(20)
)
BEGIN
	IF (p_acctnum is not null)
		then
			begin

				SELECT
					IFNULL(`sec_asset_mapping`.`assetsortorder`*10000+`sec_asset_mapping`.`subclasssortorder`,999999999) as sortorder,
					IFNULL(TRIM(`rebal`.`holdingTicker`),`rebal`.`ticker`) as sortTicker,
					`rebal`.`holdingQty` as sortQty,
					`rebal`.`acctnum` as `acctnum`,
					`rebal`.`clientAccountID` as `clientAccountID`,
					`ext_acct_info`.`applicantLName` as `lastName`,
					`ext_acct_info`.`applicantFName` as `firstName`,
					`user_trade_profile`.`taxable`,
					`user_trade_profile`.`goal`,
					`rebal`.`processed`,
					`rebal`.`ticker`,
					IFNULL(`sec_asset_mapping`.`assetName`,`rebal`.`assetclass`) as assetclass,
					IFNULL(`sec_asset_mapping`.`subclassName`,`rebal`.`subclass`) as subclass,
					`rebal`.`curQty`,
					`rebal`.`curPrice`,
					`rebal`.`curValue`,
					`rebal`.`holdingTicker` as holdingTicker,
					`rebal`.`holdingQty` as holdingQty,
					`rebal`.`holdingPrice` as holdingPrice,
					`rebal`.`holdingValue` as holdingValue,
					`rebal`.`newQty`,
					`rebal`.`newValue`,
					`rebal`.`tradeType`,
					`rebal`.`reason`,
					DATE_FORMAT(`rebal`.`created`,'%Y-%m-%d %T' ) as `created`,
					`sec_asset_mapping`.`assetcolor` as `color`,
                    `sec_master`.`name`
				FROM `rebalance_trade` as `rebal`
				INNER JOIN `user_trade_profile` 
					ON (`rebal`.`acctnum` = `user_trade_profile`.`acctnum`)
				INNER JOIN `ext_acct_info`
					ON (`rebal`.`acctnum` = `ext_acct_info`.`acctnum`)
 				LEFT JOIN `sec_master`
					ON (`rebal`.`ticker` = `sec_master`.`ticker`)
 				LEFT JOIN `sec_asset_mapping`
 					ON (`rebal`.`ticker` = `sec_asset_mapping`.`ticker`
 					AND `user_trade_profile`.`theme` = `sec_asset_mapping`.`theme`)
				WHERE `rebal`.`processed` = 'N'
				AND  `rebal`.`acctnum` = p_acctnum
				ORDER BY 1,2,3
				;
			end;
	END IF;
END$$
DELIMITER ;
