CREATE TABLE `sec_theme` (
  `theme` varchar(30) NOT NULL,
  `ticker` varchar(20) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
