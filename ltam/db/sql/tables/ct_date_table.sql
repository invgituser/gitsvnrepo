DROP TABLE `rbsa`.`date_table`;

CREATE TABLE `rbsa`.`date_table` (
  `businessdate` varchar(15) NOT NULL,
  `previousbdate` varchar(15) NOT NULL,
  PRIMARY KEY (`businessdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
