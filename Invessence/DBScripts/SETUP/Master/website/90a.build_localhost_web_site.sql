DELETE FROM `invdb`.`web_site_info` WHERE `web_site_info`.`url` = 'localhost:8080';
INSERT INTO `invdb`.`web_site_info`
SELECT 'localhost:8080',
    `web_site_info`.`name`,
    `web_site_info`.`status`,
    `web_site_info`.`value`,
    `web_site_info`.`encrFlag`,
    `web_site_info`.`created`,
    `web_site_info`.`updated`
FROM `invdb`.`web_site_info`
WHERE `web_site_info`.`url` = 'buildingbenjamins';

update invdb.web_site_info
set value = 'UAT'
where (
url like 'localhost%')
and (name like 'SERVICE%MODE' or name in ('WEB.MODE'));

update invdb.web_site_info
set value = CONCAT('http://',url)
where (url like 'localhost%' )
and (name in ('URL.SECURE', 'URL.WEBSITE', 'URL.MOBILE'));
