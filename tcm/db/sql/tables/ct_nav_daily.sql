DROP TABLE IF EXISTS `nav_daily`;

CREATE TABLE `nav_daily` (
  `clientAccountID` varchar(8) NOT NULL DEFAULT '',
  `reportDate` varchar(10) NOT NULL DEFAULT '',
  `cash` Double DEFAULT NULL,
  `stock` Double DEFAULT NULL,
  `funds` Double DEFAULT NULL,
  `interestAccrual` Double DEFAULT NULL,
  `dividentAccrual` Double DEFAULT NULL,
  `total` Double DEFAULT NULL,
  PRIMARY KEY(`clientAccountID`,`reportDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

