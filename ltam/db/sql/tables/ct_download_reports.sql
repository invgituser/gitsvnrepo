
CREATE TABLE `download_reports` (
  `source` 		varchar(20) NOT NULL,
  `reportname` 	varchar(20) NOT NULL,
  `format` 		varchar(7)  NOT NULL,
  `available`   varchar(7) NOT NULL,
  `created` 	datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`source`,`reportname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
