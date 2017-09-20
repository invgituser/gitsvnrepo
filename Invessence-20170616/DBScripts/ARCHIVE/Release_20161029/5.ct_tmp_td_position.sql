CREATE TABLE `temp`.`tmp_td_position` (
  `accountNumber` varchar(20) NOT NULL DEFAULT '',
  `accountType` varchar(20) DEFAULT NULL,
  `securityType` varchar(20) DEFAULT NULL,
  `symbolCUSIP` varchar(20) NOT NULL DEFAULT '',
  `quantity` varchar(20) DEFAULT NULL,
  `amount` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`accountNumber`,`symbolCUSIP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
