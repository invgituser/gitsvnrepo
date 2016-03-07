DROP TABLE `status_mapping`;

CREATE TABLE `status_mapping` (
  `table_name` varchar(30) NOT NULL,
  `status` varchar(2) NOT NULL,
  `short_descr` varchar(6) DEFAULT NULL,
  `description` varchar(60) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`table_name`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
