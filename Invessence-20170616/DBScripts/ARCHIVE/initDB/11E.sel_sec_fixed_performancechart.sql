DROP TABLE IF EXISTS `tmp_sec_fixed_performancechart`;
CREATE TABLE `tmp_sec_fixed_performancechart` (
  `model` varchar(20) NOT NULL,
  `year` integer NOT NULL,
  `lower2` double,
  `mid` double,
  `lower1` double,
  `upper1` double,
  `upper2` double,
  PRIMARY KEY (`model`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sec_fixed_performancechart`;
CREATE TABLE `sec_fixed_performancechart` (
  `model` varchar(20) NOT NULL,
  `year` integer NOT NULL,
  `lower2` double,
  `mid` double,
  `lower1` double,
  `upper1` double,
  `upper2` double,
  PRIMARY KEY (`model`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
