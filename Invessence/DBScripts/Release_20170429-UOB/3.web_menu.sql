DROP TABLE IF EXISTS `invdb`.`web_menu`;

CREATE TABLE `invdb`.`web_menu` (
  `url` 		varchar(45) NOT NULL,
  `access` 		varchar(20) DEFAULT NULL,
  `label` 		varchar(30) NOT NULL,
  `level` 		INTEGER NOT NULL,
  `toplevel` 	INTEGER NOT NULL,
  `seq`     	INTEGER,
  `status` 		varchar(1) DEFAULT NULL,
  `destdir`  	varchar(20) NULL,
  `htmlpage` 	varchar(60) NULL,
  `command` 	varchar(60) NULL,
  `created` 	datetime DEFAULT NULL,
  `updated` 	datetime DEFAULT NULL,
  PRIMARY KEY (`url`, `access`, `label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`web_menu` 
( `url`, `access`, `label`, `level`, `toplevel`, `seq`, `status`, `destdir`, `htmlpage`, `command`, `created`)
VALUES 
 ('localhost', 'User',      'Account',		 1, null, 1, 'A', 'consumer',	 'cadd.xhtml?app=N',	 null, now())
,('localhost', 'User',      'Aggregation',	 1, null, 2, 'A', 'consumer',	 'aggregration.xhtml',	 null, now())
,('localhost', 'User',      'Help Desk',	 1, null, 3, 'A', null,			 'http://www.teamviewer.com/link/?url=505374&amp;id=35279493', null, now())
,('localhost', 'Advisor',   'Marketing',	 1, null, 1, 'A', 'advisor',	 'marketing.xhtml',		 null, now())
,('localhost', 'Operation', 'Trade',		 1, null, 2, 'A', 'advisor',	 'trade.xhtml',			 null, now())
,('localhost', 'Marketing', 'Model',		 1, null, 3, 'A', 'advisor' ,	 'assetworkflow.xhtml',	 null, now())
;


