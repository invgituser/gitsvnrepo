use `invdb`;

CREATE or REPLACE
VIEW `vw_securities_by_theme` AS
    SELECT DISTINCT
        IFNULL(`user_basket_access`.`advisor`,
                'Invessence') AS `advisor`,
        IFNULL(`user_basket_access`.`model`, 'F') AS `model`,
        IFNULL(`user_basket_access`.`theme`, 'Unused') AS `theme`,
        `sec_asset_master`.`assetclass` AS `assetclass`,
        `sec_subasset_master`.`ticker` AS `primeassetclass`,
        `sec_subasset_master`.`subassetName` AS `subassetName`,
        IFNULL(`sec_rbsa`.`ticker`,
                `sec_subasset_master`.`ticker`) AS `ticker`,
        `sec_master`.`name` AS `name`,
        `sec_master`.`type` AS `type`,
        `sec_master`.`style` AS `style`,
        `sec_master`.`status` AS `status`,
        (CASE
            WHEN (UCASE(`sec_master`.`ticker`) = UCASE('Cash')) THEN 1.00
            ELSE IFNULL(`sd`.`close_price`, 0.00)
        END) AS `price`,
        IFNULL(`sec_rbsa`.`weight`, 1.0) AS `rbsaweight`,
        `sec_asset_master`.`sortorder` AS `sortorder`,
        `sec_asset_master`.`assetcolor` AS `assetcolor`,
        `sec_subasset_master`.`subassetcolor` AS `primeassetcolor`,
        `sec_master`.`assetclass` AS `secAssetClass`,
        `sec_master`.`subclass` AS `secSubAssetClass`,
        `sec_master`.`isin` AS `isin`,
        `sec_master`.`cusip` AS `cusip`,
        `sec_master`.`ric` AS `ric`	
    FROM
        (((((`user_basket_access`
        JOIN `sec_subasset_master` ON ((`user_basket_access`.`theme` = `sec_subasset_master`.`theme`)))
        JOIN `sec_master` ON (((`sec_subasset_master`.`ticker` = `sec_master`.`ticker`)
            AND (`sec_master`.`status` = 'A'))))
        LEFT JOIN `sec_rbsa` ON (((`sec_subasset_master`.`theme` = `sec_rbsa`.`theme`)
            AND (`sec_subasset_master`.`ticker` = `sec_rbsa`.`ticker`))))
        LEFT JOIN `sec_asset_master` ON (((`sec_subasset_master`.`theme` = `sec_asset_master`.`theme`)
            AND (`sec_subasset_master`.`assetclass` = `sec_asset_master`.`assetclass`))))
        LEFT JOIN `sec_daily_info` `sd` ON (((`sec_master`.`ticker` = `sd`.`ticker`)
            AND (DATE_FORMAT(`sd`.`businessdate`, '%Y%m%d') = FUNCT_GET_SWITCH('PRICE_DATE')))));
