USE invdb;

CREATE OR REPLACE
VIEW `invdb`.`vw_rbsa_securities` AS
    SELECT 
        IFNULL(`sec_rbsa`.`theme`, 'Unused') AS `theme`,
        `invdb`.`sec_master`.`status` AS `status`,
        `invdb`.`sec_master`.`ticker` AS `ticker`,
        `invdb`.`sec_master`.`cusip` AS `cusip`,
        `invdb`.`sec_master`.`isin` AS `isin`,
        `invdb`.`sec_master`.`name` AS `name`,
        `invdb`.`sec_master`.`type` AS `type`,
        `invdb`.`sec_master`.`style` AS `style`,
        `invdb`.`sec_master`.`assetclass` AS `assetclass`,
        `invdb`.`sec_rbsa`.`ticker` AS `primeassetclass`,
        `invdb`.`sec_master`.`subclass` AS `subclass`,
        `invdb`.`sec_master`.`expenseRatio` AS `expenseRatio`,
        null AS `nontaxableReturn`,
        null AS `adv3months`,
        null AS `aum`,
        null AS `beta`,
        `invdb`.`sec_master`.`securityRiskSTD` AS `securityRiskSTD`,
        null AS `yield`,
        (CASE
            WHEN (UCASE(`invdb`.`sec_master`.`ticker`) = UCASE('Cash')) THEN 1.00
            ELSE IFNULL(`sd`.`close_price`, 0.00)
        END) AS `price`,
        IFNULL(`invdb`.`sec_rbsa`.`weight`, 1.0) AS `rbsaweight`,
        0 AS `sortorder`
    FROM
		`invdb`.`sec_master`
        LEFT JOIN  `invdb`.`sec_rbsa`
        ON (`sec_master`.`ticker` = `sec_rbsa`.`ticker`) 
        LEFT JOIN `invdb`.`sec_daily_info` `sd`
        ON (`sec_master`.`ticker` = `sd`.`ticker`)
    WHERE `sec_master`.`status` = 'A'
		AND DATE_FORMAT(`sd`.`businessdate`, '%Y%m%d') = FUNCT_GET_SWITCH('PRICE_DATE')
	;