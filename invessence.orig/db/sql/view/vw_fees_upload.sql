DROP VIEW IF EXISTS  `vw_fees_upload`;

CREATE 
VIEW `vw_fees_upload` AS
    select 
        `fees`.`clientAccountID` AS `clientAccountID`,
        `fees`.`invoiceFee` AS `invoiceFee`
    from
        `fees_charged` `fees`
    where
        ((`fees`.`processed` = 'N')
	and (`fees`.`invessenceFee` > 0)
	and (`fees`.`invoiceFee` > 0));
