-- DROP TABLE `virtual_portfolio`;


CREATE TABLE `virtual_portfolio` (
  `acctnum` bigint(20) NOT NULL,
  `itemnum` int(10) unsigned NOT NULL,
  `instrumentid` bigint(20)  NOT NULL,
  `ticker`  varchar(20)  NOT NULL,
  `active` varchar(1) NOT NULL COMMENT 'Valid\\\\\\\\nA - Active\\\\\\\\nI - Inactive\\\\\\\\n',
  `qty` int(11) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `tradeprice` float DEFAULT NULL,
  `investmentvalue` double DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`instrumentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
