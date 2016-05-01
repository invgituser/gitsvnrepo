CREATE TABLE `sec_master_group` (
  `groupname` varchar(20) NOT NULL,
  `theme` varchar(30) NOT NULL DEFAULT 'Balance' COMMENT 'Value, Income, Growth, Balance, etc.',
  `status` varchar(1) DEFAULT NULL,
  `instrumentid` bigint(20) NOT NULL,
  `sortorder` int(11) DEFAULT '0',
  `color` varchar(10) DEFAULT NULL,
  `lowerBound` double DEFAULT NULL,
  `upperBound` double DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`groupname`,`theme`,`instrumentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Group Name and associated tickers they can trade.';
