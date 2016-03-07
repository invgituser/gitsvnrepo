CREATE TABLE `inv_monthly_date_table` (
  `businessdateYYYYMM` varchar(8) NOT NULL,
  `first_businessdate` varchar(10) NOT NULL,
  `last_businessdate` varchar(10) NOT NULL,
  `prev_first_bdate` varchar(10) DEFAULT NULL,
  `prev_last_bdate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`businessdateYYYYMM`),
  KEY `first_businessdate` (`first_businessdate`),
  KEY `last_businessdate` (`last_businessdate`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
