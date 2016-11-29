CREATE or REPLACE
VIEW `invdb`.`vw_fees_upload` AS
    SELECT 
        `fees`.`clientAccountID` AS `clientAccountID`,
        `fees`.`invoiceFee` AS `invoiceFee`
    FROM
        `fees_charged` `fees`
    WHERE
        ((`fees`.`processed` IN ('Y' , 'N'))
            AND (SUBSTR(`fees`.`fromDate`, 1, 6) = FUNCT_GET_SWITCH('PREVIOUS_MONTH'))
            AND (`fees`.`invessenceFee` > 0)
            AND (`fees`.`invoiceFee` > 0));
