DROP TABLE IF EXISTS `invdb`.`web_site_info`;

CREATE TABLE `invdb`.`web_site_info` (
  `url` varchar(45) NOT NULL,
  `name` 	varchar(45) NOT NULL,
  `status` 	varchar(1)	DEFAULT NULL,
  `value` 	varchar(220) DEFAULT NULL,
  `encrFlag` varchar(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  primary key (`url`, `name`)
) ENGINE=Innodb DEFAULT CHARSET=utf8;

INSERT INTO `invdb`.`web_site_info`
	(`url`, `name`, `status`, `value`, `encrFlag`, `created`, `updated`)
SELECT 
	`company`,
	`name`,
	`status`,
	`value`,
	`encrFlag`,
	now(),
	null
FROM `service`.`web_site_info`
where mode = 'PROD';

INSERT INTO `invdb`.`web_site_info` 
(`url`, `name`, `status`, `value`, `encrFlag`, `created`) 
VALUES 
('BUILDINGBENJAMINS', 'EMAIL.OPERATION', 'A', 'operations@BuildingBenjamins.com', 'N', now()),
('BUILDINGBENJAMINS', 'INVESTMENT.MIN1ST', 'A', '2000', 'N', now()),
('BUILDINGBENJAMINS', 'INVESTMENT.MIN2ND', 'A', '50', 'N', now()),
('BUILDINGBENJAMINS', 'INVESTMENT.RECURRING1ST', 'A', '50', 'N', now()),
('BUILDINGBENJAMINS', 'INVESTMENT.RECURRING2ND', 'A', '50', 'N', now()),
('BUILDINGBENJAMINS', 'ARCHIVE.UNOPENED', 'A', '30', 'N', now()),
('BUILDINGBENJAMINS', 'ARCHIVE.INACTIVE', 'A', '30', 'N', now()),
('BUILDINGBENJAMINS', 'ARCHIVE.CLOSED', 'A', '10', 'N', now());

DELETE FROM `invdb`.`web_site_info` WHERE `url`='BUILDINGBENJAMINS' and`name`='CUSTODY.PROCESS';

UPDATE `invdb`.`web_site_info` SET `name`='DIR.CONSUMER' WHERE `url`='BUILDINGBENJAMINS' and`name`='CONSUMER.DIR';
UPDATE `invdb`.`web_site_info` SET `name`='DIR.CUSTODY' WHERE `url`='BUILDINGBENJAMINS' and`name`='CUSTODY.DIR';
UPDATE `invdb`.`web_site_info` SET `name`='DIR.TEMPLATE' WHERE `url`='BUILDINGBENJAMINS' and`name`='TEMPLATE.DIR';
UPDATE `invdb`.`web_site_info` SET `name`='CSS.DIR' WHERE `url`='BUILDINGBENJAMINS' and`name`='CSS.DIR';
UPDATE `invdb`.`web_site_info` SET `name`='CSS.CUSTODY' WHERE `url`='BUILDINGBENJAMINS' and`name`='CUSTODY.CSS';
UPDATE `invdb`.`web_site_info` SET `name`='CSS.CUSTOM' WHERE `url`='BUILDINGBENJAMINS' and`name`='CUSTOM.CSS';
UPDATE `invdb`.`web_site_info` SET `name`='CUSTODY.SERVICE' WHERE `url`='BUILDINGBENJAMINS' and`name`='FORWARD.SERVICE';
UPDATE `invdb`.`web_site_info` SET `name`='EMAIL.MAIN' WHERE `url`='BUILDINGBENJAMINS' and`name`='MAIN.EMAIL';
UPDATE `invdb`.`web_site_info` SET `name`='EMAIL.SUPPORT' WHERE `url`='BUILDINGBENJAMINS' and`name`='SUPPORT.EMAIL';
UPDATE `invdb`.`web_site_info` SET `name`='PHONE.MAIN' WHERE `url`='BUILDINGBENJAMINS' and`name`='MAIN.PHONE';
UPDATE `invdb`.`web_site_info` SET `name`='PHONE.SUPPORT' WHERE `url`='BUILDINGBENJAMINS' and`name`='SUPPORT.PHONE';
UPDATE `invdb`.`web_site_info` SET `name`='URL.SECURE' WHERE `url`='BUILDINGBENJAMINS' and`name`='SECURE.URL';
UPDATE `invdb`.`web_site_info` SET `name`='URL.WEBSITE' WHERE `url`='BUILDINGBENJAMINS' and`name`='WEBSITE.URL';
UPDATE `invdb`.`web_site_info` SET `name`='DEFAULT.MODEL' WHERE `url`='BUILDINGBENJAMINS' and`name`='DEFAULT.ADVISOR';
UPDATE `invdb`.`web_site_info` SET `name`='SUBJECT.EMAIL.ACTIVATE' WHERE `url`='BUILDINGBENJAMINS' and`name`='EMAIL.ACTIVATE.SUBJECT';
UPDATE `invdb`.`web_site_info` SET `name`='SUBJECT.EMAIL.LOCKED', `status`='A' WHERE `url`='BUILDINGBENJAMINS' and`name`='EMAIL.LOCKED.SUBJECT';
UPDATE `invdb`.`web_site_info` SET `name`='SUBJECT.EMAIL.RESET' WHERE `url`='BUILDINGBENJAMINS' and`name`='EMAIL.RESET.SUBJECT';
UPDATE `invdb`.`web_site_info` SET `name`='SUBJECT.EMAIL.WELCOME' WHERE `url`='BUILDINGBENJAMINS' and`name`='EMAIL.WELCOME.SUBJECT';
UPDATE `invdb`.`web_site_info` SET `name`='WEB.COMPANYNAME' WHERE `url`='BUILDINGBENJAMINS' and`name`='COMPANYNAME';
UPDATE `invdb`.`web_site_info` SET `name`='WEB.COPYRIGHT' WHERE `url`='BUILDINGBENJAMINS' and`name`='COPYRIGHT';
UPDATE `invdb`.`web_site_info` SET `name`='WEB.LOGO' WHERE `url`='BUILDINGBENJAMINS' and`name`='LOGO';
UPDATE `invdb`.`web_site_info` SET `name`='WEB.LOGOLIB' WHERE `url`='BUILDINGBENJAMINS' and`name`='LOGO.LIB';
UPDATE `invdb`.`web_site_info` SET `name`='WEB.LOGOSIZE' WHERE `url`='BUILDINGBENJAMINS' and`name`='LOGO.SIZE';
UPDATE `invdb`.`web_site_info` SET `name`='WEB.GOOGLEANALYTICS' WHERE `url`='BUILDINGBENJAMINS' and`name`='GOOGLE.ANALYTICS';
UPDATE `invdb`.`web_site_info` SET `name`='WEB.THEME' WHERE `url`='BUILDINGBENJAMINS' and`name`='THEME';
UPDATE `invdb`.`web_site_info` SET `name`='WEB.THEMELIB' WHERE `url`='BUILDINGBENJAMINS' and`name`='THEME.LIB';


UPDATE `invdb`.`web_site_info` SET `value`='' WHERE `url`='BUILDINGBENJAMINS' and`name`='CUSTODY.URL';
