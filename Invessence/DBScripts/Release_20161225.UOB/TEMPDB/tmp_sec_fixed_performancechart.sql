DROP TABLE IF EXISTS `invdb`.`tmp_sec_fixed_performancechart`;
DROP TABLE IF EXISTS `temp`.`tmp_sec_fixed_performancechart`;

CREATE TABLE `temp`.`tmp_sec_fixed_performancechart` (
  `model` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `lower2` double DEFAULT NULL,
  `mid` double DEFAULT NULL,
  `lower1` double DEFAULT NULL,
  `upper1` double DEFAULT NULL,
  `upper2` double DEFAULT NULL,
  PRIMARY KEY (`model`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
