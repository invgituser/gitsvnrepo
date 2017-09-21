USE invdb;
CREATE OR REPLACE
VIEW `invdb`.`vw_securities_by_theme` AS
    SELECT 
        IFNULL(`user_basket_access`.`advisor`,
                'Invessence') AS `advisor`,
        IFNULL(`user_basket_access`.`theme`, '0.Core') AS `theme`,
        `sec_assetclass_group`.`assetclass` AS `assetclass`,
        `sec_prime_asset_group`.`ticker` AS `primeassetclass`,
        `sec_master`.`ticker` AS `ticker`,
        `sec_master`.`name` AS `name`,
        `sec_master`.`type` AS `type`,
        `sec_master`.`style` AS `style`,
        `sec_master`.`status` AS `status`,
        (CASE
            WHEN (UCASE(`sec_master`.`ticker`) = UCASE('Cash')) THEN 1.00
            ELSE IFNULL(IFNULL(`sd`.`converted_adjusted_price`,`sd`.`adjusted_price`), 0.00)
        END) AS `price`,
        `sec_rbsa`.`weight` AS `rbsaweight`,
        `sec_prime_asset_group`.`sortorder` AS `sortorder`,
        `sec_assetclass_group`.`color` AS `assetcolor`,
        NULL AS `primeassetcolor`,
        `sec_master`.`assetclass` AS `secAssetClass`,
        `sec_master`.`subclass` AS `secSubAssetClass`
    FROM
        (((((`user_basket_access`
        JOIN `sec_assetclass_group` ON (((`user_basket_access`.`theme` = `sec_assetclass_group`.`theme`)
            AND (`sec_assetclass_group`.`status` = 'A'))))
        JOIN `sec_prime_asset_group` ON (((`sec_assetclass_group`.`theme` = `sec_prime_asset_group`.`theme`)
            AND (`sec_assetclass_group`.`assetclass` = `sec_prime_asset_group`.`assetclass`)
            AND (`sec_prime_asset_group`.`status` = 'A'))))
        LEFT JOIN `sec_rbsa` ON (((`sec_rbsa`.`theme` = `sec_prime_asset_group`.`theme`)
            AND (`sec_rbsa`.`primeasset_ticker` = `sec_prime_asset_group`.`ticker`))))
        LEFT JOIN `sec_master` ON (((`sec_master`.`ticker` = IFNULL(`sec_rbsa`.`ticker`, `sec_prime_asset_group`.`ticker`))
            AND (`sec_master`.`status` = 'A'))))
        LEFT JOIN `sec_daily_info` `sd` ON (((`sec_master`.`ticker` = `sd`.`ticker`)
            AND (DATE_FORMAT(`sd`.`businessdate`, '%Y%m%d') = FUNCT_GET_SWITCH('PRICE_DATE')))));
