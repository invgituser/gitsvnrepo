DROP TABLE IF EXISTS `clients_to_trade`;

CREATE TABLE `clients_to_trade` (
  `acctnum` bigint(20) NOT NULL,
  `clientAccountID` varchar(8) NOT NULL,
  `processStatus` varchar(1) NOT NULL,
  `tradedate` datetime NOT NULL,
  `reason` varchar(10) NOT NULL DEFAULT '',
  `lastTraded` varchar(10) DEFAULT NULL,
  `position` double DEFAULT NULL,
  `actualAvailable` double DEFAULT NULL,
  `currentAllocation` double DEFAULT NULL,
  `assetclass` varchar(20) NOT NULL DEFAULT '',
  `requiredAllocation` double DEFAULT NULL,
  `assetAllocationOffset` double DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`acctnum`,`tradedate`,`reason`,`assetclass`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
