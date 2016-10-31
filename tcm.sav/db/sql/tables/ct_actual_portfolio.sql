-- DROP TABLE `virtual_portfolio`;


CREATE TABLE `actual_portfolio` (
  `acctnum` bigint(20) NOT NULL,
  `instrumentid` bigint(20)  NOT NULL,
  `indicator` varchar(1) NOT NULL COMMENT 'Valid\\\\\\\\nP - Purchase\\\\\\\\nS - Sell\\\\\\\\n\\\\\\\\nD - Divident\\\\\\\\n',
  `ticker`  varchar(20)  NOT NULL,
  `active` varchar(1) NOT NULL COMMENT 'Valid\\\\\\\\nA - Active\\\\\\\\nI - Inactive\\\\\\\\n',
  `qty` int(11) DEFAULT NULL,
  `origprice` float DEFAULT NULL,
  `investedvalue` double DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`instrumentid`,`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
