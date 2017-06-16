DROP PROCEDURE IF EXISTS `invdb`.`sel_taxHarvestingSecurities`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_taxHarvestingSecurities`()
BEGIN
	SELECT 
		`tlh`.`ticker`,
		`tlh`.`tlhticker`,
		`tlh`.`active`,
		`tlh`.`weight`,
        IFNULL(`alt_security`.`type`,`primary_sec`.`type`) AS `type`,
        IFNULL(`alt_security`.`style`,`primary_sec`.`style`) AS `style`,
        IFNULL(`alt_security`.`assetclass`,`primary_sec`.`assetclass`) AS `assetclass`,
        IFNULL(`alt_security`.`subclass`,`primary_sec`.`subclass`) AS `subclass`,
        `alt_security`.`expenseRatio` AS `expenseRatio`,
        null AS `lowerBoundReturn`,
        null AS `upperBoundReturn`,
        null AS `taxableReturn`,
        null AS `nontaxableReturn`,
        null AS `adv3months`,
        null AS `aum`,
        null AS `beta`,
        null AS `securityRiskSTD`,
        null AS `yield`,
        (case
            when (ucase(`alt_security`.`ticker`) = ucase('Cash')) then 1.00
            else ifnull(`sd`.`close_price`, 0.00)
        end) AS `price`,
 		DATE_FORMAT(`tlh`.`created`,'%Y%m%d') as created
	FROM `sec_tax_loss_harvesting` as `tlh`
		INNER JOIN `sec_master` as `primary_sec`
        ON (`tlh`.`ticker` = `primary_sec`.`ticker`
			AND `primary_sec`.`status` = 'A')
        INNER JOIN  `sec_master` as `alt_security`
        ON (`tlh`.`tlhticker` = `alt_security`.`ticker`
			AND    `alt_security`.`status` = 'A')
         LEFT JOIN `sec_daily_info` as `sd`
         ON (`tlh`.`tlhticker` = `sd`.`ticker`
			AND    date_format(`sd`.`businessdate`, '%Y%m%d') = funct_get_switch('PRICE_DATE'))
 	where `tlh`.`active` = 'A'
    ;
END$$
DELIMITER ;
