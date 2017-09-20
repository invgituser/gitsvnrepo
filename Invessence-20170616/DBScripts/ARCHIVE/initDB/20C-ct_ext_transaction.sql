DROP TABLE IF EXISTS `ext_transaction`;

CREATE TABLE `ext_transaction` (
  `acctnum` BIGINT(20) NOT NULL,
  `clientAccountID` varchar(12) NOT NULL DEFAULT '',
  `ticker` varchar(10) DEFAULT NULL,
  `confirmNumber` varchar(12) NOT NULL DEFAULT '',
  `transactionSource` varchar(30) NOT NULL DEFAULT '',
  `transactionType` varchar(50) NOT NULL DEFAULT '',
  `transactionStatus` varchar(15) NOT NULL DEFAULT '',
  `controlNumber` varchar(60) DEFAULT NULL,
  `quantity` varchar(15) DEFAULT NULL,
  `price` varchar(15) DEFAULT NULL,
  `netAmount` varchar(15) DEFAULT NULL,
  `dealerCommission` varchar(15) DEFAULT NULL,
  `otherFees` varchar(15) DEFAULT NULL,
  `tradeDate` varchar(10) NOT NULL DEFAULT '',
  `settDate` varchar(10) NOT NULL DEFAULT '',
  `voidDate` varchar(10) NOT NULL DEFAULT '',
  `comment` varchar(80) DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`acctnum`,`ticker`,`transactionType`,`tradeDate`,`settDate`, `confirmNumber`),
  KEY `AK1_ext_transaction` (`clientAccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;