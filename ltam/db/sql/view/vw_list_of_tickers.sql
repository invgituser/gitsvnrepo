DROP VIEW IF EXISTS `vw_list_of_tickers`;

DELIMITER $$
CREATE VIEW `vw_list_of_tickers` AS
    select 
        `sm`.`ticker` AS `ticker`
    from
        `sec_master` `sm`
    where
        (`sm`.`status` = 'A')
    order by `sm`.`ticker`
$$

