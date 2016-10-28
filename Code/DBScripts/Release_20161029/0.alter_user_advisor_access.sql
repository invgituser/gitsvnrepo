ALTER TABLE `invdb`.`user_advisor_access` 
ADD COLUMN `privileges` VARCHAR(1) NULL COMMENT '' AFTER `rep`;

update `invdb`.`user_advisor_access`
set `privileges` = 'V';
