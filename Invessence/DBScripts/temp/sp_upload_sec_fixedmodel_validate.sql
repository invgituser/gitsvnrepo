DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_fixedmodel_validate`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_fixedmodel_validate`()
BEGIN

	SELECT DISTINCT theme, `level`, null as asset, null as subasset,  null as `percent`, 'Introduced a new theme (on new data, but no mapping on master table)' as validate
    FROM `temp`.tmp_sec_fixedmodel_subasset chk
    WHERE concat(chk.`theme`,chk.`level`) not in (select distinct concat(t.`theme`,t.`level`) from `temp`.tmp_sec_fixedmodel t where `status` = 'A')
    UNION
	SELECT theme, `level`,  asset, null as subasset, null as `percent`, 'Introduced a new asset' as validate
    FROM `temp`.tmp_sec_fixedmodel_subasset
    WHERE concat(theme,`level`,asset) not in (select concat(am.theme,am.`level`,am.asset) from `temp`.tmp_sec_fixedmodel_asset am where `status` = 'A')
    UNION
	SELECT theme, `level`,  asset, null as subasset, null as `percent`, 'Introducing Security (not on Security Master)' as validate
    FROM `temp`.tmp_sec_fixedmodel_subasset
    WHERE keyname not in (select sec.ticker from `invdb`.sec_master sec where `status` = 'A')
    UNION
	SELECT DISTINCT t.theme, t.`level`, null as asset, null as subasset,  null as `percent`, 'Theme missing mappings (on master table, missing from new data)' as validate
    FROM `temp`.tmp_sec_fixedmodel t
    WHERE concat(t.`theme`,t.`level`) not in (select distinct concat(chk.`theme`,chk.`level`) from `temp`.tmp_sec_fixedmodel_subasset chk where chk.`status` = 'A')
    UNION
	SELECT theme, `level`,  asset, null as subasset, null as `percent`, 'Theme missing assets (on master table, missing from new data' as validate
    FROM `temp`.tmp_sec_fixedmodel_asset
    WHERE tmp_sec_fixedmodel_asset.status = 'A'
    AND concat(theme,`level`,asset) not in (select concat(am.theme,am.`level`,am.asset) from `temp`.tmp_sec_fixedmodel_subasset am where `status` = 'A')
    UNION
    SELECT theme, `level`, null as asset, null as subasset, round(sum(allocation*100.0)) as `percent`, 'Does not total to 100%'
    FROM `temp`.tmp_sec_fixedmodel_subasset
    GROUP BY theme, `level`
    HAVING 
    round(sum(allocation*100)) < 100.0 or round(sum(allocation*100)) > 100.0;
    
END$$
DELIMITER ;
