delimiter $$

CREATE TABLE `invessence_switch` (
  `name` varchar(20) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  `format` varchar(10) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `lastupdated` datetime DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

