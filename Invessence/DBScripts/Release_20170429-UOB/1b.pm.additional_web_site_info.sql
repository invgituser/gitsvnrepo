DELETE FROM `invdb`.`web_site_info` WHERE `name` = 'URL.MOBILE' and `url` in ('uwealth', 'buildingbenjamins', 'traditionadvisers');

INSERT INTO 
`invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) 
VALUES 
('uwealth', 'URL.MOBILE', 'A', 'https://www.uwealth.com', 'N', now(), null);
INSERT INTO 
`invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) 
VALUES 
('buildingbenjamins', 'URL.MOBILE', 'A', 'https://buildingbenjamins.com', 'N', now(), null);
INSERT INTO 
`invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`) 
VALUES 
('traditionadvisers', 'URL.MOBILE', 'A', 'https://traditionadvisers.com/login.xhtml', 'N', now(), null);
