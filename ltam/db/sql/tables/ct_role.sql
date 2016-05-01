DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `logonid` BIGINT NOT NULL,
  `role` varchar(20) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`logonid`, `role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
