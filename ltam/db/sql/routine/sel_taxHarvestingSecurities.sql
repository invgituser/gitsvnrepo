DROP PROCEDURE IF EXISTS `sel_taxHarvestingSecurities`;

DELIMITER $$
CREATE PROCEDURE `sel_taxHarvestingSecurities`()
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
        `alt_security`.`lowerBoundReturn` AS `lowerBoundReturn`,
        `alt_security`.`upperBoundReturn` AS `upperBoundReturn`,
        `alt_security`.`taxableReturn` AS `taxableReturn`,
        `alt_security`.`nontaxableReturn` AS `nontaxableReturn`,
        `alt_security`.`adv3months` AS `adv3months`,
        `alt_security`.`aum` AS `aum`,
        `alt_security`.`beta` AS `beta`,
        `alt_security`.`securityRiskSTD` AS `securityRiskSTD`,
        `alt_security`.`yield` AS `yield`,
        (case
            when (ucase(`alt_security`.`ticker`) = ucase('Cash')) then 1.00
            else ifnull(`sd`.`close_price`, 0.00)
        end) AS `price`,
 		DATE_FORMAT(`tlh`.`created`,'%Y%m%d') as created
	FROM `sec_tax_loss_harvesting` as `tlh`,
		 `sec_master` as `primary_sec`,
	     `sec_master` as `alt_security`,
		 `sec_daily_info` as `sd`
 	where `tlh`.`active` = 'A'
	AND   `tlh`.`ticker` = `primary_sec`.`ticker`
	AND   `primary_sec`.`status` = 'A'
	AND    `tlh`.`tlhticker` = `alt_security`.`ticker`
	AND    `alt_security`.`status` = 'A'
	AND    `tlh`.`tlhticker` = `sd`.`ticker`
	AND    date_format(`sd`.`businessdate`, '%Y%m%d') = funct_get_switch('PRICE_DATE')
	;
END;
$$


