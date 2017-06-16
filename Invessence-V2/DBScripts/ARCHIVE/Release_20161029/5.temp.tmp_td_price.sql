DROP TABLE IF EXISTS `temp`.`tmp_td_price`;

CREATE TABLE `temp`.`tmp_td_price` (
  `symbolCusip` varchar(12) NOT NULL,
  `securityType` varchar(5) DEFAULT NULL,
  `priceDate` varchar(10) DEFAULT NULL,
  `price` varchar(15) DEFAULT NULL,
  `priceOrFactor` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`symbolCusip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
