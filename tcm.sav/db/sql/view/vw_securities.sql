DROP VIEW IF EXISTS `vw_securities`;

CREATE 
VIEW `vw_securities` AS
    select 
        `sec_master`.`instrumentid` AS `instrumentid`,
        ifnull(`sec_master_group`.`groupname`,
                'Invessence') AS `groupname`,
        ifnull(`sec_master_group`.`theme`, 'Unused') AS `theme`,
        `sec_master`.`status` AS `status`,
        `sec_master`.`ticker` AS `ticker`,
        `sec_master`.`cusip` AS `cusip`,
        `sec_master`.`isin` AS `isin`,
        `sec_master`.`name` AS `name`,
        `sec_master`.`type` AS `type`,
        `sec_master`.`style` AS `style`,
        `sec_master`.`assetclass` AS `assetclass`,
        `sec_master`.`subclass` AS `subclass`,
        `sec_master`.`expenseRatio` AS `expenseRatio`,
        `sec_master`.`lowerBoundReturn` AS `lowerBoundReturn`,
        `sec_master`.`upperBoundReturn` AS `upperBoundReturn`,
        ifnull(`sec_master_group`.`lowerBound`,
                `sec_master`.`lowerbound`) AS `lowerBound`,
        ifnull(`sec_master_group`.`upperBound`,
                `sec_master`.`upperbound`) AS `upperBound`,
        `sec_master`.`taxableReturn` AS `taxableReturn`,
        `sec_master`.`nontaxableReturn` AS `nontaxableReturn`,
        `sec_master`.`adv3months` AS `adv3months`,
        `sec_master`.`aum` AS `aum`,
        `sec_master`.`beta` AS `beta`,
        `sec_master`.`securityRiskSTD` AS `securityRiskSTD`,
        `sec_master`.`yield` AS `yield`,
        (case
            when (ucase(`sec_master`.`ticker`) = ucase('Cash')) then 1.00
            else ifnull(`sd`.`close_price`, 0.00)
        end) AS `price`,
        `sec_master_group`.`sortorder` AS `sortorder`
    from
        ((`sec_master`
        left join `sec_master_group` ON (((`sec_master`.`instrumentid` = `sec_master_group`.`instrumentid`)
            and (`sec_master_group`.`status` = 'A'))))
        left join `sec_daily_info` `sd` ON (((`sec_master`.`ticker` = `sd`.`ticker`)
            and (date_format(`sd`.`businessdate`, '%Y%m%d') = funct_get_switch('PRICE_DATE')))))
    where
        (`sec_master`.`status` = 'A')
    order by `sec_master_group`.`sortorder`;
