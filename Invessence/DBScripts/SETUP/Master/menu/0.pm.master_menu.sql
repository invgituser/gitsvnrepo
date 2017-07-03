DELETE FROM `invdb`.`web_menu` where `url` = 'master';

INSERT INTO `invdb`.`web_menu`
( `url`, `access`, `permission`, `label`, `icon`, `level`, `sublevel`, `seq`, `status`, `destdir`, `htmlpage`, `command`, `created`)
VALUES
 ('master', 'User',      'User', 		'Account',		null, 1, 0, 1, 'A', 'consumer',	 'cadd.xhtml?app=N',	 null, now())
,('master', 'User',      'User', 		'Aggregation',	null, 1, 0, 2, 'A', 'consumer',	 'aggregration.xhtml',	 null, now())
,('master', 'User',      'User', 		'Help Desk',	null, 1, 0, 3, 'A', null,		  'http://www.teamviewer.com/link/?url=505374&amp;id=35279493', null, now())
,('master', 'Advisor',   'marketing', 	'Marketing',	null, 1, 0, 1, 'A', 'advisor',	 'marketing.xhtml',		 null, now())
,('master', 'Advisor',   'Operations', 'Trade',		null, 1, 1, 2, 'A', 'advisor',	 'trade.xhtml',			 null, now())
,('master', 'Advisor',   'Operations', 'Model',		null, 1, 2, 3, 'A', 'advisor' ,	 'assetworkflow.xhtml',	 null, now())
;

update `invdb`.`web_menu`
set status = 'I', access = 'none'
WHERE `url` = 'master';


