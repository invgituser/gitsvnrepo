DROP PROCEDURE IF EXISTS `sp_upload_sec_asset_mapping`;

DELIMITER $$
CREATE PROCEDURE `sp_upload_sec_asset_mapping`(
	IN p_theme	VARCHAR(20)
    )
BEGIN

	DELETE FROM `invdb`.`sec_asset_mapping` where theme = p_theme;
    
	INSERT INTO `invdb`.`sec_asset_mapping`
	(`theme`,`ticker`
	,`assetclass`,`assetName`,`assetcolor`,`assetsortorder`
	,`subclass`,`subclassName`, `subclasscolor`, `subclasssortorder` 
	,`created`,`lastUpdated`)
	SELECT `assetclass`.`theme`, `subclass`.`ticker`
	, `assetclass`.`assetclass`, `assetclass`.`displayName`,`assetclass`.`color`, `assetclass`.`sortorder`
	,`subclass`.`primeassetclass`, `subclass`.`primeassetclass`,`subclass`.`color`, `subclass`.`sortorder`
	, now(), null
	FROM `invdb`.`sec_assetclass_group` as `assetclass` 
	INNER JOIN `invdb`.`sec_prime_asset_group` as `subclass`
	ON (`subclass`.`theme` = `assetclass`.`theme`
		AND `subclass`.`assetclass` = `assetclass`.`assetclass`)
	WHERE `assetclass`.`theme` = p_theme
	order by 1,2,3,6
	;

	INSERT INTO `invdb`.`sec_asset_mapping`
	(`theme`,`ticker`
	,`assetclass`,`assetName`,`assetcolor`,`assetsortorder`
	,`subclass`,`subclassName`, `subclasscolor`, `subclasssortorder` 
	,`created`,`lastUpdated`)
	SELECT distinct `subclass`.`theme`, `subclass`.`keyname`
	, `assetclass`.`asset`, `assetclass`.`assetname`, min(`assetclass`.`color`) as `assetcolor`, min(`assetclass`.`sortorder`) as `assetsortorder`
	, `subclass`.`keyname`, `subclass`.`keydescription`, null as `subclasscolor`, min(`subclass`.`sortorder`) as `subclasssortorder`
	, now(), null
	FROM `invdb`.`sec_fixedmodel_subasset` as `subclass`
	INNER JOIN `invdb`.`sec_fixedmodel_asset` as `assetclass` 
	ON (`subclass`.`theme` = `assetclass`.`theme`
		AND `subclass`.`level` = `assetclass`.`level`
		AND `subclass`.`asset` = `assetclass`.`asset`)
	WHERE CONCAT(`subclass`.`theme`, `subclass`.`level`, `subclass`.`keyname`) in 
		(SELECT DISTINCT CONCAT(`mastersub`.`theme`, `mastersub`.`level`, `mastersub`.`keyname`) 
		 FROM `invdb`.`sec_fixedmodel_subasset` as `mastersub`)
	AND `subclass`.`theme` = p_theme
	GROUP BY 
	`assetclass`.`theme`, `subclass`.`keyname`
	, `assetclass`.`asset`, `assetclass`.`assetname`
	, `subclass`.`keyname`, `subclass`.`keydescription` 
	order by 1,2,3,6;
 
END$$
DELIMITER ;
