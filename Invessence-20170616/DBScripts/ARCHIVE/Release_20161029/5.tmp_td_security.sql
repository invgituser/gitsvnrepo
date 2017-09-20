CREATE TABLE `temp`.`tmp_td_security` (
  `symbol` varchar(12) NOT NULL,
  `securityType` varchar(5) DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `maturity` varchar(10) DEFAULT NULL,
  `callDate` varchar(10) DEFAULT NULL,
  `callPrice` double DEFAULT NULL,
  `issueDate` varchar(10) DEFAULT NULL,
  `firstCoupon` varchar(10) DEFAULT NULL,
  `interestRate` double DEFAULT NULL,
  `sharePerContract` int(11) DEFAULT NULL,
  `annualIncomeAmount` double DEFAULT NULL,
  `comment` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
