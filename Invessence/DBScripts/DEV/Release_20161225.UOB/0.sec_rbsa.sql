create table `invdb`.sec_rbsa_20170130 as
select * from sec_rbsa;

DROP TABLE IF EXISTS `invdb`.`sec_rbsa`;

CREATE TABLE `invdb`.`sec_rbsa` (
  `theme`			  varchar(20) NOT NULL,
  `primeasset_ticker` varchar(30) NOT NULL,
  `ticker` 			  varchar(20) NOT NULL,
  `weight`			  double DEFAULT NULL,
  `created`			  date DEFAULT NULL,
  PRIMARY KEY (`theme`,`primeasset_ticker`,`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `invdb`.`sec_rbsa`
(  `theme`, `primeasset_ticker`, `ticker`, `weight`, `created`)
select `theme`, `ticker`, `ticker`, 1.0, now()
from `invdb`.`sec_prime_asset_group`