DROP TABLE IF EXISTS `service`.`web_site_info`;

CREATE TABLE `service`.`web_site_info` (
  `mode` varchar(20) NOT NULL,
  `company` varchar(45) NOT NULL,
  `service` varchar(45) NOT NULL,
  `vendor` varchar(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `value` varchar(220) DEFAULT NULL,
  `encrFlag` varchar(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`mode`,`company`,`service`,`vendor`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
