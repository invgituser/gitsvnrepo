DROP PROCEDURE if exists `sel_trades`;

DELIMITER $$
DELIMITER $$
CREATE PROCEDURE `sel_trades`(
    IN p_logonid  bigint(20),
	IN p_acctnum  bigint(20)
)
BEGIN

 if (p_logonid is NULL or p_logonid = 0)
  then
	/* This select is to return just header record only */
	select
	   p_logonid as logonid,
       t.acctnum as acctnum,
       s.instrumentid as instrumentid,
       s.ticker as ticker,
	   s.assetclass as assetclass,
	   s.subclass as subclass,
	   s.name as name,
       -- t.p_s_indicator as p_s_indicator,
	   t.qty as qty,
	   s.price as price,
	   CASE 
		 WHEN (s.assetclass = 'CASH') THEN t.investmentvalue
		 ELSE round ((t.qty * s.price),2) 
       END as invested
    from virtual_portfolio t
    left join `vw_list_of_securities2` s
		  on (s.instrumentid = t.instrumentid)
    left join `asset_mapping` `am` 
		  on((s.assetclass = `am`.`assetclass`) 
		  and (`am`.`asset_level` = 1)) 
    where 1 = 2;
  else
	select
	   p_logonid as logonid,
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
       END as invested
    from virtual_portfolio t
    left join `vw_list_of_securities2` s
		  on (s.instrumentid = t.instrumentid)
    left join `asset_mapping` `am` 
		  on((s.assetclass = `am`.`assetclass`) 
		  and (`am`.`asset_level` = 1)) 
    where t.acctnum = p_acctnum;
  end if;
END$$
DELIMITER ;

DELIMITER ;
