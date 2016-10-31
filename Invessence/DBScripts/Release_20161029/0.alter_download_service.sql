-- Update BROKER-SERVICES to DOWNLOAD-SERVICES

update service.service_config_details set service='DOWNLOAD-SERVICES' where service='BROKER-SERVICES';
update service.service_master set service='DOWNLOAD-SERVICES' where service='BROKER-SERVICES';

INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `encrFlag`) VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'PRE_PROCESSOR', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `encrFlag`) VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'POST_PROCESSOR', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `encrFlag`) VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'PRE_SCRIPT', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `encrFlag`) VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'POST_SCRIPT', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOWNLOAD-SERVICES', 'TD', 'LOCAL_SRC_DIRECTORY', '/data/eodfiles', 'N');
