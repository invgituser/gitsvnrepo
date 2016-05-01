CREATE TABLE `asset_mapping_group` (
  `groupname` varchar(20) NOT NULL,
  `classname` varchar(20) NOT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `asset_level` int(11) DEFAULT NULL,
  `sortorder` int(10) unsigned DEFAULT NULL,
  `index_fund` varchar(20) DEFAULT NULL,
  `lower_bound` double DEFAULT NULL,
  `upper_bound` double DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `avg_performace` double DEFAULT NULL,
  `risk_adjustment` double DEFAULT NULL,
  PRIMARY KEY (`groupname`,`classname`, `parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




