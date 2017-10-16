CREATE TABLE IF NOT EXISTS `invdb`.`ext_transaction_20170929`
as select * from `invdb`.`ext_transaction`;

DROP TABLE IF EXISTS `invdb`.`ext_transaction`;

CREATE TABLE `invdb`.`ext_transaction` (
  `acctnum` bigint(20) DEFAULT NULL,
  `clientAccountID` varchar(12) NOT NULL DEFAULT '',
  `tickerISIN` varchar(20) NOT NULL DEFAULT '',
  `confirmNumber` varchar(40) NOT NULL DEFAULT '',
  `transactionSource` varchar(30) NOT NULL DEFAULT '',
  `transactionType` varchar(50) NOT NULL DEFAULT '',
  `transactionStatus` varchar(15) NOT NULL DEFAULT '',
  `controlNumber` varchar(60) DEFAULT NULL,
  `quantity` integer DEFAULT NULL,
  `price` double DEFAULT NULL,
  `netAmount` double DEFAULT NULL,
  `commission` double DEFAULT NULL,
  `otherFees` double DEFAULT NULL,
  `tradeDate` varchar(10) NOT NULL,
  `settDate` varchar(10) DEFAULT NULL,
  `voidDate` varchar(10) DEFAULT NULL,
  `comment` varchar(120) DEFAULT NULL,
  `tradedCurrency` varchar(3) DEFAULT 'USD',
  `fxRateToBase` double DEFAULT '1',
  `baseCurrency` varchar(3) DEFAULT 'USD',
  `convertedNetAmount` double DEFAULT NULL,
  `convertedFees` double DEFAULT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`clientAccountID`,`tradeDate`, `tickerISIN`,`transactionType`,`confirmNumber`),
  KEY `AK1_ext_transaction` (`acctnum`, `tradeDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
