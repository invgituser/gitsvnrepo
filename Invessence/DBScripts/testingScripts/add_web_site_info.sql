insert into invdb.web_site_info
SELECT 'uattcm',
    `web_site_info`.`name`,
    `web_site_info`.`status`,
    `web_site_info`.`value`,
    `web_site_info`.`encrFlag`,
    `web_site_info`.`created`,
    `web_site_info`.`updated`
FROM `invdb`.`web_site_info`
WHERE url = 'traditionadvisers';

select * from invdb.web_site_info where url like 'localhost%' and name like 'URL%';

UPDATE `invdb`.`web_site_info` SET `value`='https://sguat.uobkayhian.com/robo/uwealth_signup.jsp' WHERE `url`='localhost:8080' and`name`='CUSTODY.URL';


update invdb.web_site_info
set value = 'UAT'
where (url like 'uat%' or url like 'localhost%')
and (name like 'SERVICE%MODE' or name in ('WEB.MODE'));

update invdb.web_site_info
set value = CONCAT('http://',url,'.invessence.com:8080')
where (url like 'uat%')
and (name in ('URL.SECURE', 'URL.WEBSITE'));

update invdb.web_site_info
set value = CONCAT('http://',url,'')
where url like 'localhost%'
and name in ('URL.SECURE', 'URL.WEBSITE');






