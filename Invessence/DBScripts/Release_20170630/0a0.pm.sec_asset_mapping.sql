DROP TABLE IF EXISTS `invdb`.`sec_asset_mapping`;

CREATE TABLE `invdb`.`sec_asset_mapping` (
  `theme`			varchar(20) NOT NULL,
  `ticker`			varchar(40) NOT NULL,
  `assetclass`		varchar(40) NOT NULL,
  `assetName`		varchar(60) DEFAULT NULL,
  `assetcolor`		varchar(9) DEFAULT NULL,
  `assetsortorder`		int(11) NOT NULL DEFAULT '0',
  `subclass`		varchar(40) NOT NULL,
  `subclassName`	varchar(60) DEFAULT NULL,
  `subclasscolor`	varchar(9) DEFAULT NULL,
  `subclasssortorder` int(11) NOT NULL DEFAULT '0',
  `created` 		datetime DEFAULT NULL,
  `lastUpdated`		datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

UPDATE `invdb`.`sec_fixedmodel_asset` SET `sortorder`='9999' WHERE `asset`='Cash';
UPDATE `invdb`.`sec_fixedmodel_subasset` SET `sortorder`='9999' WHERE `asset`='Cash' and`keyname`='Cash';
UPDATE `invdb`.`sec_assetclass_group` SET `sortorder`='9999' WHERE `assetclass`='Cash';
UPDATE `invdb`.`sec_prime_asset_group` SET `sortorder`='9999' WHERE `assetclass`='Cash' and`ticker`='Cash';

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
UNION
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

select * from `invdb`.`sec_asset_mapping`;

