DELETE FROM `invdb`.`web_menu` where `url`like 'demo%';

insert into `invdb`.`web_menu`
( `url`, `access`, `permission`, `label`, `icon`, `level`, `sublevel`, `seq`, `status`, `destdir`, `htmlpage`, `command`, `created`)
SELECT
'demobb' 
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
WHERE `master`.`url` = 'buildingbenjamins'
AND (`master`.`access` not in ( 'none' )
	OR `master`.`status` = 'A')
;

insert into `invdb`.`web_menu`
( `url`, `access`, `permission`, `label`, `icon`, `level`, `sublevel`, `seq`, `status`, `destdir`, `htmlpage`, `command`, `created`)
SELECT
'demotcm' 
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
WHERE `master`.`url` = 'traditionadvisers'
AND (`master`.`access` not in ( 'none' )
	OR `master`.`status` = 'A')
;

insert into `invdb`.`web_menu`
( `url`, `access`, `permission`, `label`, `icon`, `level`, `sublevel`, `seq`, `status`, `destdir`, `htmlpage`, `command`, `created`)
SELECT
'demouob' 
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
WHERE `master`.`url` = 'uwealth'
AND (`master`.`access` not in ( 'none' )
	OR `master`.`status` = 'A')
;

