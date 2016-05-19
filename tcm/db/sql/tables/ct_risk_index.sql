DROP TABLE `risk_index`;

CREATE TABLE `risk_index` (
  `instrumentid` integer NOT NULL,
  `indextype` varchar(10)  DEFAULT 'INDEXTYPE',
  `class` varchar(10)  NULL,
  `size_type` varchar(40)  NULL,
  `style` varchar(40)  NULL,
  `region_duration` varchar(40)  NULL,
  `region_specific` varchar(40)  NULL,
  `volatility` double DEFAULT 0.0,
  `ticker` varchar(20) NOT NULL,
  `index0` double DEFAULT 0.0,
  `index1` double DEFAULT 0.0,
  `index2` double DEFAULT 0.0,
  `index3` double DEFAULT 0.0,
  `index4` double DEFAULT 0.0,
  `index5` double DEFAULT 0.0,
  `index6` double DEFAULT 0.0,
  `index7` double DEFAULT 0.0,
  `index8` double DEFAULT 0.0,
  `index9` double DEFAULT 0.0,
  PRIMARY KEY (`instrumentid`, `indextype`),
  UNIQUE KEY `InstrID_UNIQUE` (`instrumentid`, `indextype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;



