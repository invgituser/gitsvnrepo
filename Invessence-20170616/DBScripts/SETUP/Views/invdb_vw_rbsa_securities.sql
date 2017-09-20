USE invdb;

CREATE OR REPLACE
VIEW `vw_rbsa_securities` AS
    SELECT 
        IFNULL(`sec_theme`.`theme`, 'Unused') AS `theme`,
        `sec_master`.`status` AS `status`,
        `sec_master`.`ticker` AS `ticker`,
        `sec_master`.`cusip` AS `cusip`,
        `sec_master`.`isin` AS `isin`,
        `sec_master`.`name` AS `name`,
        `sec_master`.`type` AS `type`,
        `sec_master`.`style` AS `style`,
        `sec_master`.`assetclass` AS `assetclass`,
        `sec_rbsa`.`ticker` AS `primeassetclass`,
        `sec_master`.`subclass` AS `subclass`,
        `sec_master`.`expenseRatio` AS `expenseRatio`,
        `sec_master`.`nontaxableReturn` AS `nontaxableReturn`,
        `sec_master`.`adv3months` AS `adv3months`,
        `sec_master`.`aum` AS `aum`,
        `sec_master`.`beta` AS `beta`,
        `sec_master`.`securityRiskSTD` AS `securityRiskSTD`,
        `sec_master`.`yield` AS `yield`,
        (CASE
            WHEN (UCASE(`sec_master`.`ticker`) = UCASE('Cash')) THEN 1.00
            ELSE IFNULL(`sd`.`close_price`, 0.00)
        END) AS `price`,
        IFNULL(`sec_rbsa`.`weight`, 1.0) AS `rbsaweight`,
        0 AS `sortorder`
    FROM
        (((`sec_theme`
        JOIN `sec_master`)
        JOIN `sec_rbsa`)
        JOIN `sec_daily_info` `sd`)
    WHERE
        ((`sec_theme`.`ticker` = `sec_master`.`ticker`)
            AND (`sec_master`.`ticker` = `sec_rbsa`.`ticker`)
            AND (`sec_master`.`ticker` = `sd`.`ticker`)
            AND (DATE_FORMAT(`sd`.`businessdate`, '%Y%m%d') = FUNCT_GET_SWITCH('PRICE_DATE'))
            AND (`sec_theme`.`status` = 'A')
            AND (`sec_master`.`status` = 'A'));
