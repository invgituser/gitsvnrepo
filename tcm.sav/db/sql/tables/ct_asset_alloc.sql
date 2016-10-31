delimiter $$

CREATE TABLE `asset_alloc` (
  `acctnum` bigint(20) NOT NULL,
  `assetclass` varchar(20) NOT NULL,
  `themecode` varchar(20) NOT NULL DEFAULT 'ETF',
  `allocationmodel` varchar(1) NOT NULL COMMENT 'Valid Values (In order of preference)\\\\\\\\nD - Default\\\\\\\\nC - Community\\\\\\\\nE - Expert\\\\\\\\nU - User\\\\\\\\n',
  `assetyear` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `active` varchar(1) DEFAULT NULL COMMENT 'Valid Values:\\\\\\\\nY - Active\\\\\\\\nN - Inactive (Data is for storage purpose only)',
  `sortnum` tinyint(4) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`assetclass`,`themecode`,`allocationmodel`,`assetyear`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

