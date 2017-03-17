CREATE TABLE `temp`.`tmp_td_price` (
  `symbolCusip` varchar(12) NOT NULL,
  `securityType` varchar(5) DEFAULT NULL,
  `priceDate` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `priceOrFactor` double DEFAULT NULL,
  PRIMARY KEY (`symbolCusip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
