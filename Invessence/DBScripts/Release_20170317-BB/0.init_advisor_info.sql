DROP TABLE user_advisor_mapping;

ALTER TABLE `invdb`.`user_advisor_info` 
ADD COLUMN `companyName` VARCHAR(60) NULL DEFAULT NULL AFTER `accttype`;


UPDATE `invdb`.`user_advisor_info` SET `rep`='901', `companyName`='Tradition Capital' WHERE `logonid`='1';
UPDATE `invdb`.`user_advisor_info` SET `rep`='902', `companyName`='Invessence' WHERE `logonid`='2';
UPDATE `invdb`.`user_advisor_info` SET `rep`='903', `companyName`='Invessence' WHERE `logonid`='3';
UPDATE `invdb`.`user_advisor_info` SET `rep`='904', `companyName`='Invessence' WHERE `logonid`='4';
UPDATE `invdb`.`user_advisor_info` SET `rep`='905', `companyName`='Invessence' WHERE `logonid`='5';
UPDATE `invdb`.`user_advisor_info` SET `rep`='100', `companyName`='Tradition Capital' WHERE `logonid`='6';
UPDATE `invdb`.`user_advisor_info` SET `rep`='102', `companyName`='Tradition Capital' WHERE `logonid`='7';
UPDATE `invdb`.`user_advisor_info` SET `rep`='101', `companyName`='Tradition Capital' WHERE `logonid`='8';
UPDATE `invdb`.`user_advisor_info` SET `rep`='103', `companyName`='Tradition Capital' WHERE `logonid`='9';
UPDATE `invdb`.`user_advisor_info` SET `rep`='001', `companyName`='Tradition Capital' WHERE `logonid`='1';
UPDATE `invdb`.`user_advisor_info` SET `rep`='002', `companyName`='Invessence' WHERE `logonid`='2';
UPDATE `invdb`.`user_advisor_info` SET `rep`='003', `companyName`='Invessence' WHERE `logonid`='3';
UPDATE `invdb`.`user_advisor_info` SET `rep`='004', `companyName`='Invessence' WHERE `logonid`='4';
UPDATE `invdb`.`user_advisor_info` SET `rep`='005', `companyName`='Invessence' WHERE `logonid`='5';

UPDATE `invdb`.`web_advisor_mapping` SET `value`='buildingbenjamins' WHERE `advisor`='BB' and`name`='WEB.URL';
UPDATE `invdb`.`web_advisor_mapping` SET `value`='invessence' WHERE `advisor`='INVESSENCE' and`name`='WEB.URL';
UPDATE `invdb`.`web_advisor_mapping` SET `value`='uob' WHERE `advisor`='UOB' and`name`='WEB.URL';


