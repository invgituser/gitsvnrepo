DROP TABLE IF EXISTS `invdb`.`tmp_sec_master` ;
DROP TABLE IF EXISTS `temp`.`tmp_sec_master` ;

CREATE TABLE `temp`.`tmp_sec_master` (
  `status` varchar(1) DEFAULT 'A',
  `ticker` varchar(20) NOT NULL,
  `cusip` varchar(12) DEFAULT NULL,
  `isin` varchar(15) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `assetclass` varchar(40) DEFAULT NULL,
  `subclass` varchar(40) DEFAULT NULL,
  `expenseRatio` double DEFAULT NULL,
  `yield` double DEFAULT NULL,
  `securityRiskSTD` double DEFAULT NULL,
  PRIMARY KEY (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
