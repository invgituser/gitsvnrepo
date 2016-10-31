DROP TABLE IF EXISTS `temp`.`sec_td_mapping_master`;

CREATE TABLE `temp`.`sec_td_mapping_master` (
  `securityType` varchar(5) NOT NULL,
  `assetclass` varchar(40) NOT NULL,
  `subclass` varchar(40) NOT NULL,
  `created` datetime DEFAULT NULL,
  `lastUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`securityType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('EQ', 'Equity', 'Equity');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('ET', 'Equity', 'Exchange Traded Fund');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('FI', 'Fixed income', 'Fixed income');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('TB', 'Bond', 'T-bill');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('MU', 'Mutual fund', 'Mutual fund');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('CD', 'CD', 'Commercial paper');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('OP', 'Option', 'Option');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('UI', 'Unit trust', 'Unit trust');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('MB', 'Mortgaged backed', 'Mortgaged backed');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('OT', 'User defined', 'User defined');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('MF', 'Money', 'Money');
INSERT INTO `temp`.`sec_td_mapping_master` (`securityType`, `assetclass`, `subclass`) VALUES ('IN', 'Index', 'Index');
