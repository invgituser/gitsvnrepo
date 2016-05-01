CREATE TABLE `asset_mapping` (
  `assetclass` varchar(20) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  `asset_level` int(11) DEFAULT NULL,
  `sortorder` int(10) unsigned DEFAULT NULL,
  `index_fund` varchar(20) DEFAULT NULL,
  `lower_bound` double DEFAULT NULL,
  `upper_bound` double DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `index_lower_bound` double DEFAULT NULL,
  `index_upper_bound` double DEFAULT NULL,
  `avg_performace` double DEFAULT NULL,
  `risk_adjustment` double DEFAULT NULL,
  `end_allocation` double DEFAULT NULL,
  PRIMARY KEY (`assetclass`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
