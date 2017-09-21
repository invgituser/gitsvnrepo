DELETE FROM `invdb`.`web_menu` where `url` in ('uwealth');


INSERT INTO `invdb`.`web_menu`
( `url`, `access`, `permission`, `label`, `icon`, `level`, `sublevel`, `seq`, `status`, `destdir`, `htmlpage`, `command`, `created`)
VALUES
 ('uwealth', 'User',      'User', 		'Account',		null, 1, 0, 1, 'A', 'consumer',	 'cadd.xhtml?app=N',	 null, now())
,('uwealth', 'User',      'User', 		'Aggregation',	null, 1, 0, 2, 'A', 'consumer',	 'aggregration.xhtml',	 null, now())
,('uwealth', 'User',      'User', 		'Help Desk',	null, 1, 0, 3, 'A', null,		  'http://www.teamviewer.com/link/?url=505374&amp;id=35279493', null, now())
,('uwealth', 'Advisor',   'marketing', 	'Marketing',	null, 1, 0, 1, 'A', 'advisor',	 'marketing.xhtml',		 null, now())
,('uwealth', 'Advisor',   'Operations', 'Trade',		null, 1, 1, 2, 'A', 'advisor',	 'trade.xhtml',			 null, now())
,('uwealth', 'Advisor',   'Operations', 'Model',		null, 1, 2, 3, 'A', 'advisor' ,	 'assetworkflow.xhtml',	 null, now())
;

insert into `invdb`.`web_menu`
( `url`, `access`, `permission`, `label`, `icon`, `level`, `sublevel`, `seq`, `status`, `destdir`, `htmlpage`, `command`, `created`)
SELECT
'uwealth' 
, `master`.`access`
, `master`.`permission`
, `master`.`label`
, `master`.`icon`
, `master`.`level`
, `master`.`sublevel`
, `master`.`seq`
, `master`.`status` 
, `master`.`destdir`
, `master`.`htmlpage`
, `master`.`command`
, now()
FROM `invdb`.`web_menu` `master`
WHERE `master`.`url` = 'master'
AND (`master`.`access` not in ( 'none' )
	OR `master`.`status` = 'A')
;