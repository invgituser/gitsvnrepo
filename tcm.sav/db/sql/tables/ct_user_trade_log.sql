DROP TABLE IF EXISTS `user_trade_log`;

CREATE TABLE `user_trade_log` (
  `acctnum` BIGINT(20) NOT NULL,
  `clientAccountID` varchar(20) default null,
  `tradeStatus` varchar(1) NOT NULL,
  `tradedate` datetime not NULL,
  `ticker` varchar(20) not null,
  `action` varchar(10) default 'Buy',
  `sectype` varchar(5) default null,
  `exchange` varchar(20) default null,
  `currency` varchar(3) default 'USD',
  `timeinforce` varchar(5) default 'DAY',
  `qty` int(11) not null,
  `tradeprice` float default null,
  `investmentamount` double default null,
  `tradeID` varchar(25) DEFAULT NULL,
  `ordertype` varchar(10) default 'Limit',
  `confirmationID` varchar(25) DEFAULT NULL,
  `created` datetime default null,
  `lastupdated` datetime default null,
PRIMARY KEY (`acctnum`,`tradedate`,`ticker`,`tradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
