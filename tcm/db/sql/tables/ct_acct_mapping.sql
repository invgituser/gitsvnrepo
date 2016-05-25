DROP TABLE `account_mapping`;

CREATE TABLE `account_mapping` (
  `accttype` integer NOT NULL,
  `short_descr` varchar(6) DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `sort_order` integer,
  PRIMARY KEY (`accttype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;
