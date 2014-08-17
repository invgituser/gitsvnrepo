DROP PROCEDURE IF EXISTS `admin_sel_virtual_portfolio`;

DELIMITER $$
CREATE PROCEDURE `admin_sel_virtual_portfolio`(
	IN p_acctnum	Long
)
BEGIN
	IF (p_acctnum is not null)
		THEN 
			begin
			SELECT
			   t.acctnum as acctnum,
			   t.itemnum as itemnum,
			   t.active as active,
			   s.instrumentid as instrumentid,
			   s.ticker as ticker,
			   s.assetclass as assetclass,
			   s.subclass as subclass,
			   s.name as name,
			   am.color as color,
			   t.qty as qty,
			   t.weight as weight,
			   t.tradeprice as origPrice,
			   s.price as price,
			   t.investmentvalue as origInvested,
			   CASE 
				 WHEN (s.assetclass = 'CASH') THEN t.investmentvalue
				 ELSE round ((t.qty * s.price),2) 
			   END as newValue,
			   user_logon.firstname,
			   user_logon.lastname,
			   IB.IB_acctnum
			from virtual_portfolio t
			left join `IB_Accounts` IB
				  on (IB.acctnum = t.acctnum)
			left join `vw_list_of_securities2` s
				  on (s.instrumentid = t.instrumentid)
			left join `asset_mapping` `am` 
				  on((s.assetclass = `am`.`assetclass`) 
				  and (`am`.`asset_level` = 1)) ,
				`user_logon`,
				`user_access_role`
			WHERE
				`user_logon`.`logonid` = `user_access_role`.`logonid`
			and `user_access_role`.`acctnum` = t.`acctnum`
			and t.acctnum = p_acctnum
			and t.`active` NOT IN ('X')
			ORDER BY t.`itemnum`;
			end;
	END IF;
END;

