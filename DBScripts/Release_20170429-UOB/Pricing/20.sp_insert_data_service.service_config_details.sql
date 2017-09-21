## Insert Data service.service_config_details for BUILDINGBENJAMINS
delete from service.service_config_details where company='BUILDINGBENJAMINS' and  service='PRICING' and mode='UAT';

INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'DESTINATION.DIRECTORY', 'D:/PROJECT/SUDHIR/LOGS/CSIDATA/', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.HOST', 'preuat.invessence.com', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.PORT', '22', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.USER', 'abhangp', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.PASSWORD', 'T35t123', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'BUILDINGBENJAMINS', 'PRICING', 'CSIDATA', 'SFTP.DIRECTORY', '/home/abhangp/Prices/', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'BUILDINGBENJAMINS', 'PRICING', 'FIS', 'HISTORY.URL', 'http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1600&Fields=last,bid,ask,high,low,date,open,close,volume&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'BUILDINGBENJAMINS', 'PRICING', 'FIS', 'DAILY.URL', 'http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1&Fields=last,bid,ask,high,low,date,open,close,volume&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20', 'N');

## Insert Data service.service_config_details for UOB
delete from service.service_config_details where company='UOB' and  service='PRICING' and mode='UAT';

INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'UOB', 'PRICING', 'CSIDATA', 'DESTINATION.DIRECTORY', 'D:/PROJECT/SUDHIR/LOGS/CSIDATA/', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.HOST', 'preuat.invessence.com', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.PORT', '22', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.USER', 'abhangp', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.PASSWORD', 'T35t123', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'UOB', 'PRICING', 'CSIDATA', 'SFTP.DIRECTORY', '/home/abhangp/Prices/', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'UOB', 'PRICING', 'FIS', 'HISTORY.URL', 'http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1600&Fields=last,bid,ask,high,low,date,open,close,volume&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20', 'N');
INSERT INTO service.service_config_details (mode, company, service, vendor, name, value, encrFlag) VALUES ('UAT', 'UOB', 'PRICING', 'FIS', 'DAILY.URL', 'http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1&Fields=last,bid,ask,high,low,date,open,close,volume&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20', 'N');
