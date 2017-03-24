DROP TABLE IF EXISTS `temp`.`tmp_sec_fixed_performancechart`;

CREATE TABLE `temp`.`tmp_sec_fixed_performancechart` (
  `theme` varchar(20) NOT NULL,
  `level` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `5percent` double DEFAULT NULL,
  `25percent` double DEFAULT NULL,
  `50percent` double DEFAULT NULL,
  `75percent` double DEFAULT NULL,
  `95percent` double DEFAULT NULL,
  PRIMARY KEY (`theme`,`level`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
