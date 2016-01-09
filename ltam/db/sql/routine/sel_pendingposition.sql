DROP PROCEDURE IF EXISTS `sel_pendingposition`;

DELIMITER $$
CREATE PROCEDURE `sel_pendingposition`(
	IN p_acctnum  bigint(20)
)
BEGIN
	select
       t.acctnum as acctnum,
       s.instrumentid as instrumentid,
       s.ticker as ticker,
	   s.assetclass as assetclass,
	   s.subclass as subclass,
	   s.name as name,
       am.color as color,
       -- t.p_s_indicator as p_s_indicator,
	   t.qty as qty,
	   s.price as price,
	   CASE 
		 WHEN (s.assetclass = 'CASH') THEN t.investmentvalue
		 ELSE round ((t.qty * s.price),2) 
       END as invested,
	   s.sortorder as sortorder
    from virtual_portfolio t
    left join `vw_list_of_securities2` s
		  on (s.instrumentid = t.instrumentid)
    left join `asset_mapping` `am` 
		  on((s.assetclass = `am`.`assetclass`) 
		  and (`am`.`asset_level` = 1)) 
    where t.acctnum = p_acctnum
	order by 11;
END$$
DELIMITER ;

