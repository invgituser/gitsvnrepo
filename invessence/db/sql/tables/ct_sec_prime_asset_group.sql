CREATE TABLE `sec_prime_asset_group` (
  `theme` varchar(20) NOT NULL,
  `assetclass` varchar(30) NOT NULL,
  `primeassetclass` varchar(30) NOT NULL,
  `ticker` varchar(20) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `sortorder` int(11) DEFAULT '0',
  `color` varchar(10) DEFAULT NULL,
  `lowerBound` double DEFAULT NULL,
  `upperBound` double DEFAULT NULL,
  `expectedReturn` double DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`assetclass`,`primeassetclass`,`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
