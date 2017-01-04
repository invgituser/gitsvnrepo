DROP TABLE IF EXISTS `temp`.`tmp_fixedmodel_robo_loader`;

CREATE TABLE `temp`.`tmp_fixedmodel_robo_loader` (
  `modelname` 	varchar(60) NOT NULL,
  `ticker` 	  	varchar(30) NOT NULL,
  `allocation`  double DEFAULT NULL,
  PRIMARY KEY (`modelname`,`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `temp`.`tmp_fixedmodel_robo_mapping`;

CREATE TABLE `temp`.`tmp_fixedmodel_robo_mapping` (
  `theme`		varchar(30) NOT NULL,
  `level` 	  	varchar(30) NOT NULL,
  `modelname` 	varchar(60) NOT NULL,
  `displayName` varchar(60) NOT NULL,
  PRIMARY KEY (`theme`, `level`, `modelname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;