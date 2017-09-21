UPDATE `invdb`.`notification_message_lookup` SET `emailAdvisorRecepient`='operations@traditioncm.com' WHERE `advisor`='BB' and`messageType`='ACTIVE';
UPDATE `invdb`.`notification_message_lookup` SET `emailAdvisorRecepient`='operations@traditioncm.com' WHERE `advisor`='BB' and`messageType`='CHNGADDRS';
UPDATE `invdb`.`notification_message_lookup` SET `emailAdvisorRecepient`='operations@traditioncm.com' WHERE `advisor`='BB' and`messageType`='FUNDED';
UPDATE `invdb`.`notification_message_lookup` SET `emailAdvisorRecepient`='operations@traditioncm.com' WHERE `advisor`='BB' and`messageType`='OPENED';
UPDATE `invdb`.`notification_message_lookup` SET `emailAdvisorRecepient`='operations@traditioncm.com' WHERE `advisor`='BB' and`messageType`='PROCESSED';
UPDATE `invdb`.`notification_message_lookup` SET `emailAdvisorRecepient`='operations@traditioncm.com' WHERE `advisor`='BB' and`messageType`='REBALANCE';
DELETE FROM `invdb`.`notification_message_lookup` where advisor = 'BB-TCM';
insert into `invdb`.`notification_message_lookup`
SELECT 'BB-TCM', -- `notification_message_lookup`.`advisor`,
    `notification_message_lookup`.`messageType`,
    `notification_message_lookup`.`includeAdvisor`,
    `notification_message_lookup`.`advisorsubject`,
    `notification_message_lookup`.`includeAdvisorEmail`,
    `notification_message_lookup`.`emailAdvisorSubject`,
    `notification_message_lookup`.`emailAdvisorRecepient`,
    `notification_message_lookup`.`includeUser`,
    `notification_message_lookup`.`userSubject`,
    `notification_message_lookup`.`includeUserEmail`,
    `notification_message_lookup`.`emailUserSubject`,
    now(),
    null
FROM `invdb`.`notification_message_lookup`
WHERE `notification_message_lookup`.`advisor` = 'BB';


