DROP TABLE IF EXISTS `invdb`.`ext_nav`;

CREATE TABLE `invdb`.`ext_nav` (
  `clientAccountID` varchar(15) NOT NULL DEFAULT '',
  `reportDate` varchar(10) NOT NULL DEFAULT '',
  `cash` double DEFAULT NULL,
  `stock` double DEFAULT NULL,
  `funds` double DEFAULT NULL,
  `interestAccrual` double DEFAULT NULL,
  `dividentAccrual` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`clientAccountID`,`reportDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
