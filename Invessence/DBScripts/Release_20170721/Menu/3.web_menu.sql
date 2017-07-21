DROP TABLE IF EXISTS `invdb`.`web_menu`;

CREATE TABLE `invdb`.`web_menu` (
  `url` 	varchar(45) NOT NULL,
  `access` 		varchar(20) not NULL,
  `permission`  varchar(20) not NULL,
  `label` 		varchar(30) NOT NULL,
  `icon`		varchar(40) DEFAULT NULL,
  `level` 		INTEGER NOT NULL,
  `sublevel` 	INTEGER NOT NULL,
  `seq`     	INTEGER,
  `status` 		varchar(1) DEFAULT NULL,
  `availOnMobile` 	varchar(1) DEFAULT NULL,
  `destdir`  	varchar(20) NULL,
  `htmlpage` 	varchar(60) NULL,
  `command` 	varchar(60) NULL,
  `created` 	datetime DEFAULT NULL,
  `updated` 	datetime DEFAULT NULL,
  PRIMARY KEY (`url`, `access`, `permission`, `label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`web_menu` 
( `url`, `access`, `permission`, `label`, `icon`, `level`, `sublevel`, `seq`, `status`, `availOnMobile`, `destdir`, `htmlpage`, `command`, `created`)
VALUES 
 ('master', 'User',      'User', 		'Account',		null, 0, 0, 1, 'A', 'Y', 'consumer',	 'cadd.xhtml?app=N',	 null, now())
,('master', 'User',      'User', 		'Aggregation',	null, 0, 0, 2, 'A', 'Y','consumer',	 'aggregration.xhtml',	 null, now())
,('master', 'User',      'User', 		'Help Desk',	null, 0, 0, 3, 'A', 'N', null,		  'http://www.teamviewer.com/link/?url=505374&amp;id=35279493', null, now())
,('master', 'Advisor',   'marketing', 	'Marketing',	null, 0, 0, 4, 'A', 'Y','advisor',	 'marketing.xhtml',		 null, now())
,('master', 'Advisor',   'Operations', 'Opeations',		null, 0, 1, 5, 'A', 'N', null,	 null,			 null, now())
-- ,('master', 'Advisor',   'Operations', 'Trade',		null, 1, 1, 2, 'A', 'Y', 'advisor',	 'trade.xhtml',			 null, now())
-- ,('master', 'Advisor',   'Operations', 'Model',		null, 1, 2, 3, 'A', 'Y', 'advisor' ,	 'assetworkflow.xhtml',	 null, now())
;


