INSERT INTO `service`.`service_config_details` 
(`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`, `created`, `updated`) 
VALUES 
('PROD', 'BUILDINGBENJAMINS', 'EMAIL-SERVICE', 'INVESSENCE-GMAIL', 'L1_SUPPORT_EMAIL', 'abhangp@invessence.com', 'N', '2016-11-12 14:50:06', 'NULL'),
('PROD', 'BUILDINGBENJAMINS', 'EMAIL-SERVICE', 'INVESSENCE-GMAIL', 'L2_SUPPORT_EMAIL', 'abhangp@mindcraft.in', 'N', '2016-11-12 14:50:06', 'NULL'),
('PROD', 'BUILDINGBENJAMINS', 'EMAIL-SERVICE', 'INVESSENCE-GMAIL', 'SENDER_EMAIL', 'no-reply@invessence.com', 'N', '2016-11-12 14:50:06', 'NULL'),
('PROD', 'BUILDINGBENJAMINS', 'EMAIL-SERVICE', 'BB-GMAIL', 'L1_SUPPORT_EMAIL', 'supportL1@invessence.com', 'N', '2016-11-12 14:50:06', 'NULL'),
('PROD', 'BUILDINGBENJAMINS', 'EMAIL-SERVICE', 'BB-GMAIL', 'L2_SUPPORT_EMAIL', 'supportL2@invessence.com', 'N', '2016-11-12 14:50:06', 'NULL'),
('PROD', 'BUILDINGBENJAMINS', 'EMAIL-SERVICE', 'BB-GMAIL', 'SENDER_EMAIL', 'no-reply@invessence.com', 'N', '2016-11-12 14:50:06', 'NULL'),
('PROD', 'INVESSENCE', 'EMAIL-SERVICE', 'INVESSENCE-GMAIL', 'L1_SUPPORT_EMAIL', 'abhangp@invessence.com', 'N', '2016-11-12 14:50:06', 'NULL'),
('PROD', 'INVESSENCE', 'EMAIL-SERVICE', 'INVESSENCE-GMAIL', 'L2_SUPPORT_EMAIL', 'abhangp@mindcraft.in', 'N', '2016-11-12 14:50:06', 'NULL'),
('PROD', 'INVESSENCE', 'EMAIL-SERVICE', 'INVESSENCE-GMAIL', 'SENDER_EMAIL', 'no-reply@invessence.com', 'N', '2016-11-12 14:50:06', 'NULL');

UPDATE `service`.`service_config_details` 
SET `value`='sp_td_eod_process' 
WHERE `mode`='PROD' and`company`='BUILDINGBENJAMINS' 
and`service`='DOWNLOAD-SERVICES' and`vendor`='TD' 
and`name`='POST_PROCESSOR';
