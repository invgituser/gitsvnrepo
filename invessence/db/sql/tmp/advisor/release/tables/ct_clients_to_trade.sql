CREATE TABLE `clients_to_trade` (
  `acctnum` BIGINT(20) NOT NULL,
  `clientAccountID` varchar(8) NOT NULL,
  `processStatus` varchar(1) NOT NULL,
  `tradedate` datetime not NULL,
  `reason` varchar(1) null,
  `lastTraded` varchar(10) default null,
  `position` double default null,
  `actualAvailable` double default null,
  `currentAllocation` double default null,
  `assetclass` varchar(20) default null,
  `requiredAllocation` double default null,
  `assetAllocationOffset` double default null,
   `created` datetime default null,
  `lastupdated` datetime default null,
PRIMARY KEY (`acctnum`,`tradedate`,`reason`,`assetclass`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
