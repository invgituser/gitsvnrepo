delimiter $$

DROP VIEW `vw_historical_returns`
$$

CREATE VIEW `vw_historical_returns` AS
    select 
        `historical_index`.`index` AS `indexFund`,
        `historical_index`.`seqno` AS `seqno`,
        `historical_index`.`monthly_return` AS `monthly_return`
    from
        `historical_index`
    order by `historical_index`.`index` , `historical_index`.`seqno`
$$

