ALTER TABLE `invdb`.`advisor_notification`
CHANGE COLUMN `message` `message` VARCHAR(1000) NOT NULL COMMENT '' ;
use service;
ALTER TABLE `service`.`dc_template_mapping`
ADD COLUMN `isDisabled` CHAR(1) NOT NULL DEFAULT 'N' AFTER `role` ;