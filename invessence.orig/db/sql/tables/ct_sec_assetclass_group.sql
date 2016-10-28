CREATE TABLE `sec_assetclass_group` (
  `theme` varchar(20) NOT NULL,
  `status` varchar(1) NOT NULL DEFAULT 'A',
  `assetclass` varchar(20) NOT NULL,
  `displayName` varchar(30) NOT NULL,
  `ticker` varchar(20) NOT NULL,
  `sortorder` int(10) unsigned DEFAULT NULL,
  `lowerBound` double DEFAULT NULL,
  `upperBound` double DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `averageReturn` double DEFAULT NULL,
  `riskAdjustment` double DEFAULT NULL,
  `endAllocation` double DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`assetclass`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
