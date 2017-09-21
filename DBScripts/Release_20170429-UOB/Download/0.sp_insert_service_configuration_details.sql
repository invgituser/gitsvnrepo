## Script for inserting pre process parameter VALUE
UPDATE service.service_config_details SET value='sp_td_start_process'
 WHERE mode='UAT' and company='BUILDINGBENJAMINS' and service='DOWNLOAD-SERVICES' and vendor='TD' and name='PRE_PROCESSOR';

UPDATE service.service_config_details SET value='sp_td_start_process'
 WHERE mode='PROD' and company='BUILDINGBENJAMINS' and service='DOWNLOAD-SERVICES' and vendor='TD' and name='PRE_PROCESSOR';


