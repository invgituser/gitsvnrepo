DROP TABLE IF EXISTS `sec_asset_master`;

CREATE TABLE `sec_asset_master` (
  `theme`    varchar(20) NOT NULL,
  `assetclass` varchar(30) NOT NULL,
  `status`     varchar(1) DEFAULT 'A',
  `assetName` varchar(40) DEFAULT NULL,
  `assetcolor` varchar(9) NOT NULL,
  `sortorder` int(11) NOT NULL DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`assetclass`, `sortorder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sec_asset_master
select distinct
  `theme`,
  `asset`,
  'A',
  `assetName`,
  `color`,
  `sortorder`,
  now(),
  null
FROM `sec_fixedmodel_asset`
UNION
select distinct
  `theme`,
  `assetclass`,
  'A',
  `displayName`,
  `color`,
  `sortorder`,
  now(),
  null
FROM `sec_assetclass_group`
;
