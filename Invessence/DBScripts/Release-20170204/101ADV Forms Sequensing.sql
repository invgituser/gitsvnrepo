INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_AGREE', 'DOCUSIGN', 'A', '0', 'BB_TCM_ADV_AGREE');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'TCM_ADV_2AB', 'DOCUSIGN', 'A', '0', 'BB_TCM_ADV_2AB');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'TCM_PRIVACY_NOTICE', 'DOCUSIGN', 'A', '0', 'BB_TCM_PRIVACY_NOTICE');


INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_2AB', 'f5fd0a0d-1fca-486e-9de8-92386f322201', 'TCM ADV 2AB', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_AGREE', 'c0d642a7-7e19-486a-9b64-4e3d724f9965', 'Building Benjamins Tradition Advisory Agreeme', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_PRIVACY_NOTICE', 'ce93131f-68f2-4cf7-80e0-51a0636de5f2', 'TCM Privacy Notice', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_2AB', 'f5fd0a0d-1fca-486e-9de8-92386f322201', 'TCM ADV 2AB', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_AGREE', 'c0d642a7-7e19-486a-9b64-4e3d724f9965', 'Building Benjamins Tradition Advisory Agreeme', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'BUILDINGBENJAMINS', 'DOCUSIGN-SERVICES', 'BB_TCM_PRIVACY_NOTICE', 'ce93131f-68f2-4cf7-80e0-51a0636de5f2', 'TCM Privacy Notice', 'Y', 'A');


INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('BB_TCM_ADV_AGREE', 'Textbox', 'FullName', 'fullName', 'Client', 'N');
INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('BB_TCM_ADV_AGREE', 'Textbox', 'JointAHFullName', 'fullName', 'Joint', 'N');
INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('BB_TCM_ADV_AGREE', 'Textbox', 'AdvisorName', 'firmName', 'Client', 'N');



USE `invdb`;
CREATE
     OR REPLACE ALGORITHM = UNDEFINED
    SQL SECURITY DEFINER
VIEW `invdb`.`vwdc_requests` AS
    SELECT
        `invdb`.`dc_requests`.`reqId` AS `reqId`,
        `invdb`.`dc_requests`.`acctnum` AS `acctnum`,
        `invdb`.`dc_requests`.`eventNum` AS `eventNum`,
        `invdb`.`dc_requests`.`envelopeHeading` AS `envelopeHeading`,
        `invdb`.`dc_requests`.`reqType` AS `reqType`,
        `invdb`.`dc_requests`.`envelopeId` AS `envelopeId`,
        `invdb`.`dc_requests`.`status` AS `status`
    FROM
        `invdb`.`dc_requests`
        ORDER BY FIELD(reqType,'ACCT_APPLI_NEW','MOVE_MONEY_NEW','IRA_APPLI_NEW','IRA_MOVE_MONEY_NEW','ELEC_FUND_TRAN_NEW','ACCT_TRAN_NEW','TD_TRAN_NEW');