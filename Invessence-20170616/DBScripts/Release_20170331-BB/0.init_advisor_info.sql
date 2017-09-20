ALTER TABLE `invdb`.`user_advisor_info`
ADD COLUMN `companyName` VARCHAR(60) NULL DEFAULT NULL AFTER `accttype`;

UPDATE `invdb`.`web_advisor_mapping` SET `value`='buildingbenjamins' WHERE `advisor`='BB' and`name`='WEB.URL';
UPDATE `invdb`.`web_advisor_mapping` SET `value`='traditionadvisers' WHERE `advisor`='BB-TCM' and`name`='WEB.URL';
UPDATE `invdb`.`web_advisor_mapping` SET `value`='invessence' WHERE `advisor`='INVESSENCE' and`name`='WEB.URL';
UPDATE `invdb`.`web_advisor_mapping` SET `value`='uob' WHERE `advisor`='UOB' and`name`='WEB.URL';


