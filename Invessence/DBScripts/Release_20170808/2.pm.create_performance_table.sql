DROP TABLE IF EXISTS `invdb`.`sec_performance_mapping`;

CREATE TABLE `invdb`.`sec_performance_mapping` (
  `theme` 		varchar(20) NOT NULL,
  `ticker` 		varchar(20) NOT NULL,
  `displayName` varchar(40) NOT NULL,
  `status` 		varchar(1) NOT NULL,
  `color` 		varchar(9) DEFAULT NULL,
  `created` 	datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`theme`,`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
