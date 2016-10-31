CREATE TABLE `monthly_date_table` (
  `businessdateYYYYMM` varchar(8) NOT NULL,
  `first_businessdate` varchar(10) NOT NULL,
  `last_businessdate` varchar(10) NOT NULL,
  PRIMARY KEY (`businessdateYYYYMM`),
  KEY `first_businessdate` (`first_businessdate`),
  KEY `last_businessdate` (`last_businessdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
