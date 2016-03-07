
CREATE TABLE `sec_tax_loss_harvesting` (
  `ticker` varchar(20) NOT NULL DEFAULT '',
  `tlhticker` varchar(20) NOT NULL DEFAULT '',
  `active` varchar(1) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`ticker`,`tlhticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
