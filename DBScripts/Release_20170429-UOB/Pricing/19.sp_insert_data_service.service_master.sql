## Insert Data service.service_master for BUILDINGBENJAMINS

delete from service.service_master where company='BUILDINGBENJAMINS' and  service='PRICING';

INSERT INTO service.service_master (company, service, status, vendor) VALUES ('BUILDINGBENJAMINS', 'PRICING', 'A', 'YAHOO');
INSERT INTO service.service_master (company, service, status, vendor) VALUES ('BUILDINGBENJAMINS', 'PRICING', 'A', 'XIGNITE');
INSERT INTO service.service_master (company, service, status, vendor) VALUES ('BUILDINGBENJAMINS', 'PRICING', 'A', 'CSIDATA');
INSERT INTO service.service_master (company, service, status, vendor) VALUES ('BUILDINGBENJAMINS', 'PRICING', 'A', 'FIS');

## Insert Data service.service_master for UOB

delete from service.service_master where company='UOB' and  service='PRICING';

INSERT INTO service.service_master (company, service, status, vendor) VALUES ('UOB', 'PRICING', 'A', 'YAHOO');
INSERT INTO service.service_master (company, service, status, vendor) VALUES ('UOB', 'PRICING', 'A', 'XIGNITE');
INSERT INTO service.service_master (company, service, status, vendor) VALUES ('UOB', 'PRICING', 'A', 'CSIDATA');
INSERT INTO service.service_master (company, service, status, vendor) VALUES ('UOB', 'PRICING', 'A', 'FIS');