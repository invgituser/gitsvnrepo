DROP PROCEDURE IF EXISTS `invdb`.`sp_upload_sec_asset_mapping`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sp_upload_sec_asset_mapping`()
BEGIN

	DELETE FROM `invdb`.`sec_asset_mapping`;
    
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
	order by 1,2,3,6
	;

	INSERT INTO `invdb`.`sec_asset_mapping`
	(`theme`,`ticker`
	,`assetclass`,`assetName`,`assetcolor`,`assetsortorder`
	,`subclass`,`subclassName`, `subclasscolor`, `subclasssortorder` 
	,`created`,`lastUpdated`)
	SELECT distinct `assetclass`.`theme`, `subclass`.`keyname`
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
	AND `subclass`.`status` in ('A')
	AND  `assetclass`.`status` in ('A')
	GROUP BY 
	`assetclass`.`theme`, `subclass`.`keyname`
	, `assetclass`.`asset`, `assetclass`.`assetname`
	, `subclass`.`keyname`, `subclass`.`keydescription` 
	order by 1,2,3,6;
 
END$$
DELIMITER ;
