DROP TABLE IF EXISTS `tmp_commission`;

CREATE TABLE `tmp_commission` (
  `clientAccountID` varchar(8) NOT NULL DEFAULT '',
  `fromDate` varchar(10) NOT NULL DEFAULT '',
  `toDate` varchar(10) NOT NULL DEFAULT '',
  `commission` Double DEFAULT NULL,
  `otherFee` Double DEFAULT NULL,
  `advisorFee` Double DEFAULT NULL,
  PRIMARY KEY(`clientAccountID`,`fromDate`,`toDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
