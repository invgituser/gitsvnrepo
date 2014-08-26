DROP VIEW IF EXISTS `vw_advisors_security`;

CREATE 
VIEW `vw_advisors_security` AS
    select 
		IFNULL(`group`.`groupname`,'Invessence') as `groupname`,
		IFNULL(`group`.`theme`,'Core') as `theme`,
         `sec_master`.`instrumentid` AS `instrumentid`,
       `sec_master`.`status` AS `status`,
        `sec_master`.`ticker` AS `ticker`,
        `sec_master`.`cusip` AS `cusip`,
        `sec_master`.`isin` AS `isin`,
        `sec_master`.`name` AS `name`,
        `sec_master`.`primarymarket` AS `primarymarket`,
        `sec_master`.`type` AS `type`,
        `sec_master`.`subtype` AS `subtype`,
        `sec_master`.`style` AS `style`,
        `sec_master`.`assetclass` AS `assetclass`,
        ifnull(`am`.`description`,
                `sec_master`.`subclass`) AS `subclass`,
        `sec_master`.`region` AS `region`,
        `sec_master`.`region_specific` AS `region_specific`,
        `sec_master`.`expenseRatio` AS `expenseRatio`,
        IFNULL(`group`.`lowerBoundReturn`,`sec_master`.`lowerBoundReturn`) AS `lowerBoundReturn`,
        IFNULL(`group`.`lb1`,`sec_master`.`lb1`) AS `lb1`,
        IFNULL(`group`.`lb2`,`sec_master`.`lb2`) AS `lb2`,
        IFNULL(`group`.`lb3`,`sec_master`.`lb3`) AS `lb3`,
        IFNULL(`group`.`lb4`,`sec_master`.`lb4`) AS `lb4`,
        IFNULL(`group`.`lb5`,`sec_master`.`lb5`) AS `lb5`,
        IFNULL(`group`.`lb6`,`sec_master`.`lb6`) AS `lb6`,
        IFNULL(`group`.`lb7`,`sec_master`.`lb7`) AS `lb7`,
        IFNULL(`group`.`lb8`,`sec_master`.`lb8`) AS `lb8`,
        IFNULL(`group`.`lb9`,`sec_master`.`lb9`) AS `lb9`,
        IFNULL(`group`.`lb10`,`sec_master`.`lb10`) AS `lb10`,
        IFNULL(`group`.`upperBoundReturn`,`sec_master`.`upperBoundReturn`) AS `upperBoundReturn`,
        IFNULL(`group`.`ub1`,`sec_master`.`ub1`) AS `ub1`,
        IFNULL(`group`.`ub2`,`sec_master`.`ub2`) AS `ub2`,
        IFNULL(`group`.`ub3`,`sec_master`.`ub3`) AS `ub3`,
        IFNULL(`group`.`ub4`,`sec_master`.`ub4`) AS `ub4`,
        IFNULL(`group`.`ub5`,`sec_master`.`ub5`) AS `ub5`,
        IFNULL(`group`.`ub6`,`sec_master`.`ub6`) AS `ub6`,
        IFNULL(`group`.`ub7`,`sec_master`.`ub7`) AS `ub7`,
        IFNULL(`group`.`ub8`,`sec_master`.`ub8`) AS `ub8`,
        IFNULL(`group`.`ub9`,`sec_master`.`ub9`) AS `ub9`,
        IFNULL(`group`.`ub10`,`sec_master`.`ub10`) AS `ub10`,
        `sec_master`.`taxableReturn` AS `taxableReturn`,
        `sec_master`.`nontaxableReturn` AS `nontaxableReturn`,
        `sec_master`.`inception` AS `inception`,
        `sec_master`.`issuer` AS `issuer`,
        `sec_master`.`adv3months` AS `adv3months`,
        `sec_master`.`aum` AS `aum`,
        `sec_master`.`beta` AS `beta`,
        `sec_master`.`securityRiskSTD` AS `securityRiskSTD`,
        `sec_master`.`yield` AS `yield`,
        (case
            when (ucase(`sec_master`.`ticker`) = ucase('Cash')) then 1.00
            else ifnull(`sd`.`close_price`, 0.00)
        end) AS `price`,
        `am`.`sortorder` AS `sortorder`
    from
        (`sec_master`
		left join `sec_master_group` `group` 
				ON (
					`sec_master`.`instrumentID` = `group`.`instrumentID`
					 and `group`.`status` in ('A')
					)
        left join `sec_daily_info` `sd` 
				ON (
					`sec_master`.`ticker` = `sd`.`ticker`)
					 and (date_format(`sd`.`businessdate`, '%Y%m%d') = funct_get_switch('PRICE_DATE'))
					)
        left join `asset_mapping` `am` 
				ON (
					`sec_master`.`subclass` = `am`.`assetclass`
					and (`am`.`asset_level` > 1)
					)
    where
        (`sec_master`.`status` = 'A')
    order by `am`.`sortorder`;
