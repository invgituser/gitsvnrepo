DROP PROCEDURE IF EXISTS `sel_TradeRebalDetail`;
DROP PROCEDURE IF EXISTS `sel_displayTradeDetail`;

DELIMITER $$
CREATE PROCEDURE `sel_TradeRebalDetail`(
	p_acctnum BIGINT(20)
)
BEGIN
	IF (p_acctnum is not null)
		then
			begin

				SELECT
					IFNULL(`sec_asset_master`.`sortorder`,999999999) as sortorder,
					IFNULL(TRIM(`rebal`.`holdingTicker`),`rebal`.`ticker`) as sortTicker,
					`rebal`.`holdingQty` as sortQty,
                    `rebal`.`acctnum`,
                    `ext_acct_info`.`clientAccountID`,
					`ext_acct_info`.`applicantLName` as `lastName`,
					`ext_acct_info`.`applicantFName` as `firstName`,
					`user_trade_profile`.`advisor`,
					`user_trade_profile`.`rep`,
					`user_trade_profile`.`taxable`,
					`user_trade_profile`.`goal`,
					`rebal`.`processed`,
					`rebal`.`ticker`,
					`rebal`.`assetclass` as assetclass,
					`rebal`.`subclass` as subclass,
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
					`rebal`.`created`,
					`sec_asset_master`.`assetcolor` as `color`,
					`sec_master`.`name`
				FROM `rebalance_trade` as `rebal`
				INNER JOIN `user_trade_profile` 
					ON (`rebal`.`acctnum` = `user_trade_profile`.`acctnum`)
				INNER JOIN `ext_acct_info`
					ON (`rebal`.`acctnum` = `ext_acct_info`.`acctnum`)
 				INNER JOIN `sec_asset_master`
 					ON (`rebal`.`assetclass` = `sec_asset_master`.`assetclass`
 					AND `user_trade_profile`.`theme` = `sec_asset_master`.`theme`)
 				LEFT JOIN `sec_master`
					ON (`rebal`.`ticker` = `sec_master`.`ticker`)
				WHERE `rebal`.`processed` = 'N'
				AND  `rebal`.`acctnum` = p_acctnum
				ORDER BY 1,2,3
				;
			end;
	END IF;
END$$
DELIMITER ;
