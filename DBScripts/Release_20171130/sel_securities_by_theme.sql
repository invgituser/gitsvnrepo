DROP PROCEDURE IF EXISTS `invdb`.`sel_securities_by_theme`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_securities_by_theme` (
	  IN p_advisor	VARCHAR(20)
    , IN p_theme	VARCHAR(50)
)
BEGIN

    SELECT DISTINCT
        IFNULL(`user_basket_access`.`advisor`,
                'Invessence') AS `advisor`,
        IFNULL(`user_basket_access`.`model`, 'F') AS `model`,
        IFNULL(`user_basket_access`.`theme`, 'Unused') AS `theme`,
        `sec_asset_mapping`.`assetclass` AS `assetclass`,
        `sec_asset_mapping`.`ticker` AS `primeassetclass`,
        `sec_asset_mapping`.`subclassName` AS `subassetName`,
        IFNULL(`sec_rbsa`.`ticker`,
                `sec_asset_mapping`.`ticker`) AS `ticker`,
        `sec_master`.`name` AS `name`,
        `sec_master`.`type` AS `type`,
        `sec_master`.`style` AS `style`,
        `sec_master`.`status` AS `status`,
		`sec_rbsa`.`base_currency`  AS `baseCurrency`,
        (CASE
            WHEN (UCASE(`sec_master`.`ticker`) = UCASE('Cash')) THEN 1.00
            ELSE IFNULL(IFNULL(`sd`.`converted_adjusted_price`,`sd`.`adjusted_price`), 0.00)
        END) AS `price`,
        IFNULL(`sec_rbsa`.`dest_currency`, `user_basket_access`.`baseCurrency`) AS `destCurrency`,
        (CASE
            WHEN (UCASE(`sec_master`.`ticker`) = UCASE('Cash')) THEN 1.00
            ELSE IFNULL(`sd`.`adjusted_price`, 0.00)
        END) AS `localprice`,
        (CASE
            WHEN (UCASE(`sec_master`.`ticker`) = UCASE('Cash')) THEN 1.00
            -- ELSE IFNULL(`sd`.`adjusted_price`, 0.00)
            ELSE 1.00
        END) AS `exchangeRate`,
        IFNULL(`sec_rbsa`.`weight`, 1.0) AS `rbsaweight`,
        ((`sec_asset_mapping`.`assetsortorder` * 10000) + `sec_asset_mapping`.`subclasssortorder`) AS `sortorder`,
        `sec_asset_mapping`.`assetcolor` AS `assetcolor`,
        `sec_asset_mapping`.`subclasscolor` AS `primeassetcolor`,
        `sec_master`.`assetclass` AS `secAssetClass`,
        `sec_master`.`subclass` AS `secSubAssetClass`,
        `sec_master`.`isin` AS `isin`,
        `sec_master`.`cusip` AS `cusip`,
        `sec_master`.`ric` AS `ric`
    FROM
        ((((`user_basket_access`
        JOIN `sec_asset_mapping` ON ((`user_basket_access`.`theme` = `sec_asset_mapping`.`theme`)))
        JOIN `sec_master` ON (((`sec_asset_mapping`.`ticker` = `sec_master`.`ticker`)
            AND (`sec_master`.`status` = 'A'))))
        LEFT JOIN `sec_rbsa` ON (((`sec_asset_mapping`.`theme` = `sec_rbsa`.`theme`)
            AND (`sec_asset_mapping`.`ticker` = `sec_rbsa`.`ticker`)
            )))
        LEFT JOIN `sec_daily_info` `sd` ON (((`sec_rbsa`.`ticker` = `sd`.`ticker`)
			AND (`user_basket_access`.`baseCurrency` = `sd`.`dest_currency`)
            AND (DATE_FORMAT(`sd`.`businessdate`, '%Y%m%d') = FUNCT_GET_SWITCH('PRICE_DATE')))))
	WHERE `user_basket_access`.`advisor` = p_advisor
    AND   `user_basket_access`.`theme` = p_theme
    ;
END $$

DELIMITER ;
