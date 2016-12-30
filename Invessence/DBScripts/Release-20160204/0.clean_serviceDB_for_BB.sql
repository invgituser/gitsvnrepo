DELETE FROM `service`.`service_config_details` 
WHERE `company`='BUILDINGBENJAMINS' and`service`='BROKER-WEBSERVICES' and`vendor`='TD' and`name`='';

INSERT INTO `service`.`service_config_details` 
(`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) 
VALUES 
('PROD', 'BUILDINGBENJAMINS', 'BROKER-WEBSERVICES', 'TD', 'SERVICE', 'Active', 'N'),
('UAT', 'BUILDINGBENJAMINS', 'BROKER-WEBSERVICES', 'TD', 'SERVICE', 'Active', 'N'),
('DEMO', 'BUILDINGBENJAMINS', 'BROKER-WEBSERVICES', 'TD', 'SERVICE', 'Active', 'N'),
('TEST', 'BUILDINGBENJAMINS', 'BROKER-WEBSERVICES', 'TD', 'SERVICE', 'Active', 'N');
