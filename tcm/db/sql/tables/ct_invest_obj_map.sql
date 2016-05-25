delimiter $$

CREATE TABLE `invest_obj_map` (
  `investobjective` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `sortorder` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`investobjective`),
  UNIQUE KEY `InvestObjective_UNIQUE` (`investobjective`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
$$

