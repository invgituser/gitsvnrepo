insert into `service`.`web_site_info`
SELECT `web_site_info`.`mode`,
    'UOB',
    `web_site_info`.`service`,
    `web_site_info`.`vendor`,
    `web_site_info`.`name`,
    `web_site_info`.`status`,
    `web_site_info`.`value`,
    `web_site_info`.`encrFlag`,
    `web_site_info`.`created`,
    `web_site_info`.`updated`
FROM `service`.`web_site_info`
where company = 'BUILDINGBENJAMINS'
;

UPDATE `service`.`web_site_info` 
SET `value`='UOB Kay Hain' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='COMPANYNAME';
UPDATE `service`.`web_site_info` 
SET `value`='uob' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='CONSUMER.DIR';
UPDATE `service`.`web_site_info` 
SET `value`='UOB Kay Hain' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='COPYRIGHT';
UPDATE `service`.`web_site_info` 
SET `value`='uob' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='CSS.DIR';
UPDATE `service`.`web_site_info` 
SET `value`='css/uob.css' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='CUSTODY.CSS';
UPDATE `service`.`web_site_info` 
SET `value`='' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='CUSTODY.DIR';
UPDATE `service`.`web_site_info` 
SET `value`='EXTERNAL' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='CUSTODY.PROCESS';
UPDATE `service`.`web_site_info` 
SET `value`='css/uob.css' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='CUSTOM.CSS';
UPDATE `service`.`web_site_info` 
SET `value`='UOB' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='DEFAULT.ADVISOR';
UPDATE `service`.`web_site_info` 
SET `value`='Welcome to UOB Kay Hain – Activate Your Account' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='EMAIL.ACTIVATE.SUBJECT';
UPDATE `service`.`web_site_info` 
SET `value`='UOB Kay Hain – Account is locked' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='EMAIL.LOCKED.SUBJECT';
UPDATE `service`.`web_site_info` 
SET `value`='UOB Kay Hain – Reset Your Password' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='EMAIL.RESET.SUBJECT';
UPDATE `service`.`web_site_info` 
SET `value`='noreply@uob.com' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='EMAIL.USER';
UPDATE `service`.`web_site_info` 
SET `value`='Welcome to UOB Kay Hain – Activate Your Account' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='EMAIL.WELCOME.SUBJECT';
UPDATE `service`.`web_site_info` 
SET `value`='' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='GOOGLE.ANALYTICS';
UPDATE `service`.`web_site_info` 
SET `value`='uob-Forgot.htm' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='HTML.FORGOT';
UPDATE `service`.`web_site_info` 
SET `value`='uob-Locked.htm' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='HTML.LOCKED';
UPDATE `service`.`web_site_info` 
SET `value`='uob-Reset.htm' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='HTML.RESET';
UPDATE `service`.`web_site_info` 
SET `value`='uob-Welcome.htm' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='HTML.WELCOME';
UPDATE `service`.`web_site_info` 
SET `value`='uob-Welcome.htm' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='HTML.WELCOME.ADV';
UPDATE `service`.`web_site_info` 
SET `value`='images/logo/uobkh.png' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='LOGO';
UPDATE `service`.`web_site_info` 
SET `value`='info@uob.com' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='MAIN.EMAIL';
UPDATE `service`.`web_site_info` 
SET `value`='(XXX) XXX-XXXX' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='MAIN.PHONE';
UPDATE `service`.`web_site_info` 
SET `value`='https://uob.invessence.com:8080' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='SECURE.URL';
UPDATE `service`.`web_site_info` 
SET `value`='support@uob.com' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='SUPPORT.EMAIL';
UPDATE `service`.`web_site_info` 
SET `value`='(XXX) XXX-XXXX' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='SUPPORT.PHONE';
UPDATE `service`.`web_site_info` 
SET `value`='http:/uob.invessence.com:8080' WHERE `company`='UOB' and`service`='WEBSITE' and`vendor`='0' and`name`='WEBSITE.URL';

