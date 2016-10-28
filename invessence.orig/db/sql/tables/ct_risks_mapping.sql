delimiter $$

CREATE TABLE `risks_mapping` (
  `risktype` varchar(45) NOT NULL,
  `charvalue` varchar(60) DEFAULT NULL,
  `numvalue` double(21,8) DEFAULT NULL,
  `datevalue` date DEFAULT NULL,
  PRIMARY KEY (`risktype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

