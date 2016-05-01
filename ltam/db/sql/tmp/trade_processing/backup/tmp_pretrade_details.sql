DROP TABLE IF EXISTS `tmp_pretrade_details`;

CREATE TABLE `tmp_pretrade_details` (
  `acctnum` BIGINT(20) NOT NULL,
  `clientAccountID` varchar(20) default null,
  `name`    VARCHAR(60) DEFAULT NULL,
  `tradedate` datetime not NULL,
  `ticker` varchar(20) not null,
  `accttype` varchar(40) default null,
  `posqty` int(11) default null,
  `newqty` int(11) not null,
  `tradeqty` int(11) not null,
  `pnl`        double default null,
  `priceperShare` double default null,
  `gainloss` double default null,
  `adjustedQty` int(11) default null,
  `tradeprice` double default null,
  `posamount` double default null,
  `newamount` double default null,
  `tradeamount` double default null,
PRIMARY KEY (`acctnum`,`ticker`,`tradedate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
