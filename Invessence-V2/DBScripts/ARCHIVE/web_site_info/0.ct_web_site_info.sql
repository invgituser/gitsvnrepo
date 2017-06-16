DROP TABLE IF EXISTS `invdb`.`web_site_info`;

CREATE TABLE `invdb`.`web_site_info` (
  `url` varchar(45) NOT NULL,
  `name` 	varchar(45) NOT NULL,
  `status` 	varchar(1)	DEFAULT NULL,
  `value` 	varchar(220) DEFAULT NULL,
  `encrFlag` varchar(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  primary key (`url`, `name`)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

