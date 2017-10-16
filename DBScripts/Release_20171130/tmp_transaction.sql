DROP TABLE IF EXISTS `temp`.`tmp_transaction`;

CREATE TABLE `temp`.`tmp_transaction` (
  `clientAccountID` varchar(20) DEFAULT '',
  `tradeDate` 		varchar(20) DEFAULT NULL,
  `symbolSIN` 		varchar(20) DEFAULT '',
  `transactionType` varchar(20) DEFAULT '',
  `statusFlag` 		varchar(1) DEFAULT NULL,
  `quantity` 		varchar(20) DEFAULT NULL,
  `price`    		varchar(20) DEFAULT NULL,
  `netAmount` 		varchar(20) DEFAULT NULL,
  `brokerFee` 		varchar(20) DEFAULT NULL,
  `otherFees` 		varchar(20) DEFAULT NULL,
  `settleDate` 		varchar(20) DEFAULT NULL,
  `comments` 		varchar(120) DEFAULT NULL,
  `executionCurrency` varchar(3) DEFAULT 'USD',
  `localCurrency` 	varchar(3) DEFAULT 'USD',
  `exchangeRate` 	varchar(20) DEFAULT '1.0',
  `confirmNumber`	varchar(20)	DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`, `tradeDate`, `symbolSIN`, `settleDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `temp`.`uob_ext_position`;

CREATE TABLE `temp`.`uob_ext_position` (
  `clientAccountID` varchar(12) NOT NULL DEFAULT '',
  `reportDate` varchar(8) NOT NULL DEFAULT '',
  `symbol` varchar(12) NOT NULL,
  `currency` varchar(3) DEFAULT 'USD',
  `quantity` double DEFAULT NULL,
  `costBasisPrice` double NOT NULL DEFAULT '0',
  `costBasisMoney` double DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`,`reportDate`,`symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `temp`.`uob_cash`;
CREATE TABLE `temp`.`uob_cash` (
  `clientAccountID` varchar(12) NOT NULL DEFAULT '',
  `reportDate` varchar(8) NOT NULL DEFAULT '',
  `value` double DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`sec_source_mapping` 
(`sec_ticker`, `ticker_source_name`, `tickersource`, `pricing_required`, `exchange_required`, `base_currency`, `dest_currency`, `multiplying_factor`, `return_threshold`) 
VALUES 
('F0HKG062UI.MSTA', 'F0HKG062UI.MSTA', 'FIS', 'Y', 'N', 'SGD', 'SGD', '1', '0.5');

INSERT INTO `invdb`.`sec_master`
(`instrumentid`,`status`,`securityStatus`,`ticker`,`isin`,`name`,`assetclass`,`subclass`,`exchange`,`base_currency`)
VALUES
(1004,'A','A','F0HKG062UI.MSTA','SG9999003412','United Singapore Bond Fund','Fixed Income','Singapore Equities','MSTA', 'SGD');
