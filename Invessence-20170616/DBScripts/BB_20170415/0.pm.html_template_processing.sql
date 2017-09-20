DELETE FROM `invdb`.`web_site_info` WHERE `name` = 'HTML.BASE.PATH';

INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`) VALUES 
('traditionadvisers', 'HTML.BASE.PATH', 'A', '/inv/www/invessence/ROOT/template/html/', 'N', '2017-04-10'),
('demo', 'HTML.BASE.PATH', 'A', '/inv/www/invessence/ROOT/template/html/', 'N', '2017-04-10'),
('uattcm', 'HTML.BASE.PATH', 'A', '/inv/www/invessence/ROOT/template/html/', 'N', '2017-04-10')
;
INSERT INTO `invdb`.`web_site_info` (`url`, `name`, `status`, `value`, `encrFlag`, `created`) VALUES 
('buildingbenjamins', 'HTML.BASE.PATH', 'A', '/inv/www/invessence/ROOT/template/html/', 'N', '2017-04-10'),
('preuat', 'HTML.BASE.PATH', 'A', '/inv/www/invessence/ROOT/template/html/', 'N', '2017-04-10'),
('uatbb', 'HTML.BASE.PATH', 'A', '/inv/www/invessence/ROOT/template/html/', 'N', '2017-04-10'),
('localhost', 'HTML.BASE.PATH', 'A', 'D:/inv/www/invessence/ROOT/template/html/', 'N', '2017-04-10')
;

update `invdb`.`web_site_info`
set value = REPLACE(value,'.htm', '.html')
WHERE `name` like 'HTML%';