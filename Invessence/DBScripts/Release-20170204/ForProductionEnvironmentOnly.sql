update `service`.`dc_template_details` set status='I' where tempCode='BB_TCM_ADV_2AB';
update `service`.`dc_template_details` set status='I' where tempCode='BB_TCM_ADV_AGREE';
update `service`.`dc_template_details` set status='I' where tempCode='BB_TCM_PRIVACY_NOTICE';



INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`)
 VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_2AB', '151f2c41-3cc9-49ad-8d09-a84f8d57a86e', 'TCM ADV 2AB', 'Y', 'A');

INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`)
VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_AGREE', '5ed1d17e-b087-400e-8c03-2f43a88b88e6', 'Building Benjamins Tradition Advisory Agreeme', 'Y', 'A');

INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`)
VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_PRIVACY_NOTICE', '1759ddf4-f6fe-4ef4-b62f-a0bd30e44b23', 'TCM Privacy Notice', 'Y', 'A');


