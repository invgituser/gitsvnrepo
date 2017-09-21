DROP PROCEDURE IF EXISTS validate_asset_allocation;

DELIMITER $$
create procedure validate_asset_allocation()
BEGIN

	SELECT DISTINCT theme, `level`, null as asset, null as subasset,  null as `percent`, 'Introduced a new theme (on new data, but no mapping on master table)' as validate
    FROM tmp_sec_fixedmodel_subasset chk
    WHERE concat(chk.`theme`,chk.`level`) not in (select distinct concat(t.`theme`,t.`level`) from tmp_sec_fixedmodel t where `status` = 'A')
    UNION
	SELECT theme, `level`,  asset, null as subasset, null as `percent`, 'Introduced a new asset' as validate
    FROM tmp_sec_fixedmodel_subasset
    WHERE concat(theme,`level`,asset) not in (select concat(am.theme,am.`level`,am.asset) from tmp_sec_fixedmodel_asset am where `status` = 'A')
    UNION
	SELECT theme, `level`,  asset, null as subasset, null as `percent`, 'Introducing Security (not on Security Master)' as validate
    FROM tmp_sec_fixedmodel_subasset
    WHERE ticker not in (select sec.ticker from sec_master sec where `status` = 'A')
    UNION
	SELECT DISTINCT t.theme, t.`level`, null as asset, null as subasset,  null as `percent`, 'Theme missing mappings (on master table, missing from new data)' as validate
    FROM tmp_sec_fixedmodel t
    WHERE concat(t.`theme`,t.`level`) not in (select distinct concat(chk.`theme`,chk.`level`) from tmp_sec_fixedmodel_subasset chk where `status` = 'A')
    UNION
	SELECT theme, `level`,  asset, null as subasset, null as `percent`, 'Theme missing assets (on master table, missing from new data' as validate
    FROM tmp_sec_fixedmodel_asset
    WHERE concat(theme,`level`,asset) not in (select concat(am.theme,am.`level`,am.asset) from tmp_sec_fixedmodel_subasset am where `status` = 'A')
    UNION
    SELECT theme, `level`, null as asset, null as subasset, sum(allocation) as `percent`, 'Does not total to 100%'
    FROM tmp_sec_fixedmodel_subasset
    GROUP BY theme, `level`
    HAVING 
    sum(allocation) <> 1.0;
    
END$$

DELIMITER ;