CREATE TABLE `tmp_date_table` (
  `businessdate` datetime NOT NULL,
  `prev_businessdate` date DEFAULT NULL,
  PRIMARY KEY (`businessdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE Table `tmp_date_table`
( ticker varchar(20) NOT NULL
 ,businessdate date  NOT NULL
);
