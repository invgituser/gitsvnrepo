CREATE OR REPLACE
VIEW `vw_primeassets` AS
    SELECT 
        IFNULL(`sec_prime`.`theme`, 'Invessence') AS `theme`,
        IFNULL(`sec_prime`.`assetclass`,
                `sec_prime`.`assetclass`) AS `assetclass`,
        `sec_master`.`subclass` AS `subclass`,
        IFNULL(`sec_prime`.`primeassetclass`, 'UNUSED') AS `primeassetclass`,
        `sec_master`.`type` AS `type`,
        `sec_master`.`style` AS `style`,
        `sec_prime`.`ticker` AS `ticker`,
        `sec_prime`.`status` AS `status`,
        IFNULL(`sec_prime`.`lowerBound`,
                `sec_master`.`lowerbound`) AS `lowerBound`,
        IFNULL(`sec_prime`.`upperBound`,
                `sec_master`.`upperbound`) AS `upperBound`,
        `sec_master`.`expenseRatio` AS `expenseRatio`,
        `sec_prime`.`expectedReturn` AS `expectedReturn`,
        `sec_master`.`adv3months` AS `adv3months`,
        `sec_master`.`aum` AS `aum`,
        `sec_master`.`beta` AS `beta`,
        `sec_master`.`securityRiskSTD` AS `securityRiskSTD`,
        `sec_master`.`yield` AS `yield`,
        IFNULL(`sec_prime`.`sortorder`, 999999) AS `sortorder`
    FROM
        (`sec_prime_asset_group` `sec_prime`
        JOIN `sec_master`)
    WHERE
        ((`sec_prime`.`ticker` = `sec_master`.`ticker`)
            AND (`sec_prime`.`status` = 'A')
            AND (`sec_master`.`status` = 'A'))
    ORDER BY IFNULL(`sec_prime`.`sortorder`, 999999);
