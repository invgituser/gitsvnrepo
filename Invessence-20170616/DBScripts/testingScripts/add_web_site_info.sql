delete from invdb.web_site_info 
where url like 'localhost:8080' or url like 'pre%' or url like 'uat%' or url like 'demo%';

insert into invdb.web_site_info
SELECT 'csibb',
    `web_site_info`.`name`,
    `web_site_info`.`status`,
    `web_site_info`.`value`,
    `web_site_info`.`encrFlag`,
    `web_site_info`.`created`,
    `web_site_info`.`updated`
FROM `invdb`.`web_site_info`
WHERE url = 'buildingbenjamins';

select * from invdb.web_site_info where url like 'pre%' and name like 'URL%';


update invdb.web_site_info
set value = 'UAT'
where (
url like 'localhost%' or url like 'pre%' or url like 'uat%' or url like 'demo%')
and (name like 'SERVICE%MODE' or name in ('WEB.MODE'));

update invdb.web_site_info
set value = CONCAT('http://',url,'.invessence.com:8080')
where (url like 'localhost%' or url like 'pre%' or url like 'uat%' or url like 'demo%')
and (name in ('URL.SECURE', 'URL.WEBSITE', 'URL.MOBILE'));

update invdb.web_site_info
set value = CONCAT('http://',url)
where url like 'localhost%'
and name in ('URL.SECURE', 'URL.WEBSITE', 'URL.MOBILE');


INSERT INTO 
`invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) 
VALUES 
('localhost:8080', 'URL.MOBILE', 'A', 'http://localhost:8080', 'N', '2016-12-16 22:20:19', '0000-00-00 00:00:00');

select * from `invdb`.`web_site_info`
where url = 'buildingbenjamins'
and `name` not in (select `name` from `invdb`.`web_site_info` where url = 'uob')


