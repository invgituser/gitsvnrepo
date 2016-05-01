delimiter $$

CREATE TABLE `historical_index` (
  `index` varchar(20) NOT NULL,
  `seqno` int(11) NOT NULL,
  `monthly_return` double NOT NULL,
  PRIMARY KEY (`index`,`seqno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

