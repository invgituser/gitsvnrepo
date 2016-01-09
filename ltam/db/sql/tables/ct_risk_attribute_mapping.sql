CREATE TABLE `risk_attribute_mapping` (
  `attributename` varchar(45) NOT NULL,
  `charvalue` varchar(60) DEFAULT NULL,
  `numvalue` double(21,8) DEFAULT NULL,
  `datevalue` date DEFAULT NULL,
  PRIMARY KEY (`attributename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;
