CREATE OR REPLACE
VIEW `vw_primeassets` AS
    SELECT 
        IFNULL(`sec_prime`.`theme`, 'Invessence') AS `theme`,
        `sec_prime`.`assetclass` AS `assetclass`,
        `sec_prime`.`ticker` AS `ticker`,
        `sec_prime`.`status` AS `status`,
        IFNULL(`sec_prime`.`lowerBound`, 0) AS `lowerBound`,
        IFNULL(`sec_prime`.`upperBound`, 1) AS `upperBound`,
        NULL AS `expenseRatio`,
        `sec_prime`.`expectedReturn` AS `expectedReturn`,
        NULL AS `adv3months`,
        NULL AS `aum`,
        NULL AS `beta`,
        NULL AS `securityRiskSTD`,
        NULL AS `yield`,
        IFNULL(`sec_prime`.`sortorder`, 999999) AS `sortorder`
    FROM
        `sec_prime_asset_group` `sec_prime`,
        `user_basket_access`
    WHERE
		`user_basket_access`.`theme` = `sec_prime`.`theme`
	AND  `user_basket_access`.`status` In ('A')
	AND  `sec_prime`.`status` in ('A')
    ORDER BY `sec_prime`.`theme` , IFNULL(`sec_prime`.`sortorder`, 999999);
