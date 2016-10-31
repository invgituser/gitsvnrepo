CREATE TABLE `asset_mapping_group` (
  `groupname` varchar(20) NOT NULL,
  `classname` varchar(20) NOT NULL,
  `parent` varchar(20) NOT NULL DEFAULT '',
  `description` varchar(60) DEFAULT NULL,
  `assetLevel` int(11) DEFAULT NULL,
  `sortOrder` int(10) unsigned DEFAULT NULL,
  `indexFund` varchar(20) DEFAULT NULL,
  `lowerBound` double DEFAULT NULL,
  `upperBound` double DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `averageReturn` double DEFAULT NULL,
  `riskAdjustment` double DEFAULT NULL,
  `endAllocation` double DEFAULT NULL,
  PRIMARY KEY (`groupname`,`classname`,`parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
