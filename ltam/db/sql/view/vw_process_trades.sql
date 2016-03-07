DROP VIEW IF EXISTS `vw_process_trades`;

CREATE 
VIEW `vw_process_trades` AS
    select
		`virtual_portfolio`.`acctnum` AS `InvAcctnum`,
        `IB_Accounts`.`IB_acctnum` AS `acctnum`,
		`IB_Accounts`.`firstName` as `firstname`,
		`IB_Accounts`.`lastname` as `lastname`,
        `virtual_portfolio`.`itemnum` AS `itemnum`,
        `virtual_portfolio`.`instrumentid` AS `instrumentid`,
        `virtual_portfolio`.`ticker` AS `ticker`,
        `virtual_portfolio`.`active` AS `active`,
        `virtual_portfolio`.`qty` AS `qty`,
        `virtual_portfolio`.`weight` AS `weight`,
        `virtual_portfolio`.`tradeprice` AS `tradeprice`,
        `virtual_portfolio`.`investmentvalue` AS `investmentvalue`,
        `virtual_portfolio`.`created` AS `created`,
        `virtual_portfolio`.`lastupdated` AS `lastupdated`
    from
        (`virtual_portfolio`
        join `IB_Accounts`)
    where
        ((`virtual_portfolio`.`acctnum` = `IB_Accounts`.`acctnum`)
            and (`IB_Accounts`.`accountStatus` in ('Funded' , 'Active'))
            and (`virtual_portfolio`.`active` = 'A')
            and (ucase(`virtual_portfolio`.`ticker`) <> 'CASH'));


