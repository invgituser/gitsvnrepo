CREATE TABLE `state_mapping` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `abbreviation` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `licensed` varchar(1) DEFAULT NULL,
  `census_region` varchar(255) DEFAULT NULL,
  `census_division` varchar(255) DEFAULT NULL,
  `max_allowed` integer,
  PRIMARY KEY (`name`,`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


