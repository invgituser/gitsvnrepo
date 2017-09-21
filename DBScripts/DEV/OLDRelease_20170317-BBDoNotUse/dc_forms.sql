CREATE TABLE `invdb`.`dc_forms` (
  `advisor` varchar(20) NOT NULL,
  `formname` varchar(45) NOT NULL,
  `seqnum` int(3) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  PRIMARY KEY (`advisor`, `formname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
