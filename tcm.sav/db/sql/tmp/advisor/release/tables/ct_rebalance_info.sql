CREATE TABLE `rebalance_info` (
  `acctnum` BIGINT(20) NOT NULL,
  `clientAccountID` varchar(8) NOT NULL,
  `processStatus` varchar(1) NOT NULL,
  `tradedate` datetime not NULL,
  `reason` varchar(1) null,
  `lastTraded` varchar(10) default null,
  `varianceBreak` double default null,
  `created` datetime default null,
  `lastupdated` datetime default null,
PRIMARY KEY (`acctnum`,`tradedate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
