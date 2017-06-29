DELETE FROM `invdb`.`web_site_info` WHERE `web_site_info`.`url` = 'devopsdevbb';
INSERT INTO `invdb`.`web_site_info`
SELECT 'devopsdevbb',
    `web_site_info`.`name`,
    `web_site_info`.`status`,
    `web_site_info`.`value`,
    `web_site_info`.`encrFlag`,
    `web_site_info`.`created`,
    `web_site_info`.`updated`
FROM `invdb`.`web_site_info`
WHERE `web_site_info`.`url` = 'buildingbenjamins';

DELETE FROM `invdb`.`web_site_info` WHERE `web_site_info`.`url` = 'devopsdevtcm';
INSERT INTO `invdb`.`web_site_info`
SELECT 'devopsdevtcm',
    `web_site_info`.`name`,
    `web_site_info`.`status`,
    `web_site_info`.`value`,
    `web_site_info`.`encrFlag`,
    `web_site_info`.`created`,
    `web_site_info`.`updated`
FROM `invdb`.`web_site_info`
WHERE `web_site_info`.`url` = 'traditionadvisers';

DELETE FROM `invdb`.`web_site_info` WHERE `web_site_info`.`url` = 'devopsdevuob';
INSERT INTO `invdb`.`web_site_info`
SELECT 'devopsdevuob',
    `web_site_info`.`name`,
    `web_site_info`.`status`,
    `web_site_info`.`value`,
    `web_site_info`.`encrFlag`,
    `web_site_info`.`created`,
    `web_site_info`.`updated`
FROM `invdb`.`web_site_info`
WHERE `web_site_info`.`url` = 'uwealth';

update invdb.web_site_info
set value = 'UAT'
where (
url like 'devops%')
and (name like 'SERVICE%MODE' or name in ('WEB.MODE'));

update invdb.web_site_info
set value = CONCAT('http://',url,'.invessence.com:8080')
where (url like 'devops%' )
and (name in ('URL.SECURE', 'URL.WEBSITE', 'URL.MOBILE'));
