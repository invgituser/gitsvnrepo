DROP TABLE IF EXISTS `invdb`.`sec_asset_master`;

CREATE TABLE `invdb`.`sec_asset_master` (
  `theme` varchar(20) NOT NULL,
  `assetclass` varchar(40) NOT NULL,
  `status` varchar(1) DEFAULT 'A',
  `assetName` varchar(60) DEFAULT NULL,
  `assetcolor` varchar(9) NOT NULL,
  `sortorder` int(11) NOT NULL DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`assetclass`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `invdb`.`sec_asset_master`
(`theme`,`assetclass`,`status`,`assetName`,`assetcolor`,`sortorder`,`created`,`lastUpdated`)
SELECT `optimized`.`theme`, `optimized`.`assetclass`,'A',`optimized`.`displayName`,`optimized`.`color`, `optimized`.`sortorder`, now(), null
FROM `invdb`.`sec_assetclass_group` as `optimized` WHERE `status` = 'A'
UNION
SELECT `predefined`.`theme`, `predefined`.`asset`,'A',`predefined`.`assetname`,`predefined`.`color`, `predefined`.`sortorder`, now(), null
FROM `invdb`.`sec_fixedmodel_asset` as `predefined` WHERE `status` = 'A'and predefined.theme not like '%0.CORE%'
order by 1,2;

