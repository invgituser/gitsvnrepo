
ALTER TABLE `invdb`.`dc_request_audit` 
ADD COLUMN `product` VARCHAR(50) NULL AFTER `id`,
ADD COLUMN `mode` VARCHAR(20) NULL AFTER `product`;


USE `invdb`;
DROP procedure IF EXISTS `dc_request_auditrial`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `dc_request_auditrial`(
in p_id numeric(10),
in p_product varchar(50),
in p_mode varchar(20),
in p_requestIds varchar(100),
in p_acctNum varchar(12),
in p_eventNum varchar(12),  
in p_envelopId varchar(100),         
in p_status varchar(1),       
in p_dcRequest varchar(5000),             
in p_dcResponce  varchar(1000),            
in p_reqTime  datetime,           
in p_resTime   datetime,          
in p_remarks varchar(1000),
in p_opt varchar(20), 
out op_msgCode int(3),out op_msg varchar(20))
BEGIN

if(p_opt='INSERT') then
	Insert into dc_request_audit(product, mode, requestIds,acctnum,eventNum, dcRequest, dcResponce, status, remarks, reqTime, resTime) 
    value(p_product, p_mode, p_requestIds,p_acctNum, p_eventNum, p_dcRequest, p_dcResponce, p_status, p_remarks, p_reqTime, p_resTime);
    
    if(p_status='S') then
		update dc_requests set status='S' , envelopeId =p_envelopId where acctnum=p_acctNum and eventNum=p_eventNum and status='I';
    elseif(p_status='E') then
		update dc_requests set status='E' where acctnum=p_acctNum and eventNum=p_eventNum and status='I';    
    else 
		update dc_requests set status='X' where acctnum=p_acctNum and eventNum=p_eventNum and status='I';
    
    end if;
    
	SELECT p_opt, 1 INTO op_msg , op_msgCode;

end if;
END$$

DELIMITER ;




DROP VIEW `vwdc_requests`;

CREATE 
VIEW `vwdc_requests` AS
      SELECT 
        `dc_requests_final`.`reqId` AS `reqId`,
        `dc_requests_final`.`acctnum` AS `acctnum`,
        `dc_requests_final`.`eventNum` AS `eventNum`,
        `dc_requests_final`.`envelopeHeading` AS `envelopeHeading`,
        `dc_requests_final`.`reqType` AS `reqType`,
        `dc_requests_final`.`envelopeId` AS `envelopeId`,
        `dc_requests_final`.`status` AS `status`,
        `dc_requests_final`.`refReqId` AS `refReqId`,
        `dc_requests_final`.`seqno` AS `seqNo`,
        `dc_requests_final`.`formType` AS `formType`
        
    FROM
        `dc_requests_final`
    ORDER BY acctnum, eventNum,seqNo;


USE `invdb`;
DROP procedure IF EXISTS `dc_request_auditrial`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `dc_request_auditrial`(
in p_id numeric(10),
in p_product varchar(50),
in p_mode varchar(20),
in p_requestIds varchar(100),
in p_acctNum varchar(12),
in p_eventNum varchar(12),  
in p_envelopId varchar(100),         
in p_status varchar(1),       
in p_dcRequest varchar(5000),             
in p_dcResponce  varchar(1000),            
in p_reqTime  datetime,           
in p_resTime   datetime,          
in p_remarks varchar(1000),
in p_opt varchar(20), 
out op_msgCode int(3),out op_msg varchar(20))
BEGIN

if(p_opt='INSERT') then
	Insert into dc_request_audit(product, mode, requestIds,acctnum,eventNum, dcRequest, dcResponce, status, remarks, reqTime, resTime) 
    value(p_product, p_mode, p_requestIds,p_acctNum, p_eventNum, p_dcRequest, p_dcResponce, p_status, p_remarks, p_reqTime, p_resTime);
    
    if(p_status='S') then
		update dc_requests_final set status='S' , envelopeId =p_envelopId where acctnum=p_acctNum and eventNum=p_eventNum and status='I';
        update dc_requests set status='S' , envelopeId =p_envelopId where reqId in(select distinct(refReqId) from dc_requests_final where acctnum=p_acctNum and eventNum=p_eventNum);
    elseif(p_status='E') then
		update dc_requests_final set status='E' where acctnum=p_acctNum and eventNum=p_eventNum and status='I';   
        update dc_requests set status='E' where reqId in(select distinct(refReqId) from dc_requests_final where acctnum=p_acctNum and eventNum=p_eventNum);
    else 
		update dc_requests_final set status='X' where acctnum=p_acctNum and eventNum=p_eventNum and status='I';
        update dc_requests set status='X' where reqId in(select distinct(refReqId) from dc_requests_final where acctnum=p_acctNum and eventNum=p_eventNum);
    
    end if;
/*UPDATE invdb.dc_requests 
SET 
    status = 'I'
WHERE
    acctnum = 2334 AND status = 'S';*/
	SELECT p_opt, 1 INTO op_msg , op_msgCode;

end if;
END$$

DELIMITER ;


INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'ACAT_OTHER_NEW', 'DOCUSIGN', 'A', '0', 'BB_ACAT_OTHER');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'ACCT_ADV_FORM', 'DOCUSIGN', 'A', '0', 'BB_ACCT_ADV');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'ACCT_APPLI_NEW', 'DOCUSIGN', 'A', '0', 'BB_ACCT_APPLI');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'ACCT_CHNG_ADDR', 'DOCUSIGN', 'A', '0', 'BB_CHNG_ADDRS');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'ACCT_TRAN_NEW', 'DOCUSIGN', 'A', '0', 'BB_ACCT_TRANS');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_AGREE', 'DOCUSIGN', 'A', '0', 'BB_TCM_ADV_AGREE');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'ELEC_FUND_TRAN_CHANGE', 'DOCUSIGN', 'A', '0', 'BB_ELECT_FUND_TRANS');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'ELEC_FUND_TRAN_NEW', 'DOCUSIGN', 'A', '0', 'BB_ELECT_FUND_TRANS');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'ELEC_FUND_TRAN_REPLACE', 'DOCUSIGN', 'A', '0', 'BB_ELECT_FUND_TRANS');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'IRA_APPLI_NEW', 'DOCUSIGN', 'A', '0', 'BB_IRA_APPLI');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'IRA_MOVE_MONEY_CHANGE', 'DOCUSIGN', 'A', '0', 'BB_MOVE_MONEY_IRA');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'IRA_MOVE_MONEY_NEW', 'DOCUSIGN', 'A', '0', 'BB_MOVE_MONEY_IRA');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'IRA_MOVE_MONEY_REMOVE', 'DOCUSIGN', 'A', '0', 'BB_MOVE_MONEY_IRA');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'IRA_QRP_BENE_NEW', 'DOCUSIGN', 'A', '0', 'BB_IRAQRP_BENE');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'MOVE_MONEY_CHANGE', 'DOCUSIGN', 'A', '0', 'BB_MOVE_MONEY');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'MOVE_MONEY_NEW', 'DOCUSIGN', 'A', '0', 'BB_MOVE_MONEY');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'MOVE_MONEY_REMOVE', 'DOCUSIGN', 'A', '0', 'BB_MOVE_MONEY');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'TCM_ADV_2AB', 'DOCUSIGN', 'A', '0', 'BB_TCM_ADV_2AB');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'TCM_PRIVACY_NOTICE', 'DOCUSIGN', 'A', '0', 'BB_TCM_PRIVACY_NOTICE');
INSERT INTO `service`.`service_operation_details` (`company`, `service`, `operation`, `vendor`, `status`, `priority`, `refValue`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'TD_TRAN_NEW', 'DOCUSIGN', 'A', '0', 'BB_LPOA');



INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'ACCOUNT_ID', '18036', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'BASE_URL', 'https://demo.docusign.net/restapi', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'CCMAIL', 'docusign@invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'CCMAILNAME', 'Building Benjamins', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'DOC_PATH', 'D:\\Project\\Abhang\\Project Work\\TCM\\documents', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_AFTER', '60', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_ENABLED', 'false', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_WARN', '5', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'ID_CHECK_CONF_NAME', 'ID Check $', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'INTEGRATOR_KEY', 'TDAM-d7feb45c-e88d-4c20-b5bd-1dcd9a9d6f56', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'PASSWORD', 'Inv3ss3nc3!', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_DELAY', '1', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_ENABLED', 'false', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_FREQUENCY', '2', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'USERNAME', 'prashant@invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'USE_ACCT_DEFAULT_NOTIFICATION', 'true', 'N');

INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'ACCOUNT_ID', '18036', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'BASE_URL', 'https://demo.docusign.net/restapi', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'CCMAIL', 'docusign@invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'CCMAILNAME', 'Building Benjamins', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'DOC_PATH', 'D:\\Project\\Abhang\\Project Work\\TCM\\documents', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_AFTER', '60', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_ENABLED', 'false', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'EXPIRE_WARN', '5', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'ID_CHECK_CONF_NAME', 'ID Check $', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'INTEGRATOR_KEY', 'TDAM-d7feb45c-e88d-4c20-b5bd-1dcd9a9d6f56', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'PASSWORD', 'Inv3ss3nc3!', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_DELAY', '1', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_ENABLED', 'false', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'REMINDER_FREQUENCY', '2', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'USERNAME', 'prashant@invessence.com', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'DOCUSIGN', 'USE_ACCT_DEFAULT_NOTIFICATION', 'true', 'N');

INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('PROD', 'TCM', 'BROKER-WEBSERVICES', 'TD', 'SERVICE', 'Active', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'TCM', 'BROKER-WEBSERVICES', 'TD', 'SERVICE', 'Active', 'N');




INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'service', 'status', 'vendor');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'AGGREGATION-SERVICES', 'A', 'MX');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'AGGREGATION-SERVICES', 'I', 'YODLEE');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'BROKER-WEBSERVICES', 'I', 'GEMINI');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'BROKER-WEBSERVICES', 'A', 'TD');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'DOCUSIGN-SERVICES', 'A', 'DOCUSIGN');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'DOWNLOAD-SERVICES', 'I', 'GEMINI');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'DOWNLOAD-SERVICES', 'A', 'TD');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'EMAIL-SERVICE', 'A', 'INVESSENCE-GMAIL');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'PRICING', 'I', 'YAHOO');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('TCM', 'TRADE-PROCESS', 'A', 'VENDOR');



INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ACAT_OTHER', 'ca47568b-70d6-42c4-b10f-0e10545e1a31', 'Account Transfer Form Other', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ACCT_ADV', 'c3818a4d-320f-4a6c-8181-5c0f45206d69', 'Account ADV Forms', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ACCT_APPLI', 'e21c6a62-8527-40d8-a006-26845ca2a1d5', 'Account Application', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ACCT_TRANS', 'de101955-89c5-4cad-99b8-ec04fda60790', 'Account Transfer Form', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_CHNG_ADDRS', 'bba60794-5788-4656-8bb7-5857a228a52a', 'Change Address', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ELECT_FUND_TRANS', '7c19f254-3616-402e-ac0b-3279a241153b', 'Electronic Funds Transfer Form', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_IRAQRP_BENE', '0752806c-994e-4a48-b709-58ae78973882', 'IRA/QRP Beneficiary Account App', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_IRA_APPLI', 'ff8ec806-45e4-499d-846d-4ad84e628295', 'IRA Application', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_LPOA', '314a6465-44c1-4460-a31a-c10cc92ae886', 'LPOA', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_MOVE_MONEY', 'c607c4a9-09ee-4aea-8aff-e9e74d7a43e6', 'Move Money', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_MOVE_MONEY_IRA', 'a8e87128-8c05-4080-8fb9-c842709eada8', 'Move Money IRA', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_2AB', 'f5fd0a0d-1fca-486e-9de8-92386f322201', 'TCM ADV 2AB', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_AGREE', 'c0d642a7-7e19-486a-9b64-4e3d724f9965', 'Building Benjamins Tradition Advisory Agreeme', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'BB_TCM_PRIVACY_NOTICE', 'ce93131f-68f2-4cf7-80e0-51a0636de5f2', 'TCM Privacy Notice', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ACAT_OTHER', 'ca47568b-70d6-42c4-b10f-0e10545e1a31', 'Account Transfer Form Other', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ACCT_ADV', 'c3818a4d-320f-4a6c-8181-5c0f45206d69', 'Account ADV Forms', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ACCT_APPLI', 'e21c6a62-8527-40d8-a006-26845ca2a1d5', 'Account Application', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ACCT_TRANS', 'de101955-89c5-4cad-99b8-ec04fda60790', 'Account Transfer Form', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_CHNG_ADDRS', 'bba60794-5788-4656-8bb7-5857a228a52a', 'Change Address', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_ELECT_FUND_TRANS', '7c19f254-3616-402e-ac0b-3279a241153b', 'Electronic Funds Transfer Form', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_IRAQRP_BENE', '0752806c-994e-4a48-b709-58ae78973882', 'IRA/QRP Beneficiary Account App', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_IRA_APPLI', 'ff8ec806-45e4-499d-846d-4ad84e628295', 'IRA Application', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_LPOA', '314a6465-44c1-4460-a31a-c10cc92ae886', 'LPOA', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_MOVE_MONEY', 'c607c4a9-09ee-4aea-8aff-e9e74d7a43e6', 'Move Money', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_MOVE_MONEY_IRA', 'a8e87128-8c05-4080-8fb9-c842709eada8', 'Move Money IRA', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_2AB', 'f5fd0a0d-1fca-486e-9de8-92386f322201', 'TCM ADV 2AB', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_TCM_ADV_AGREE', 'c0d642a7-7e19-486a-9b64-4e3d724f9965', 'Building Benjamins Tradition Advisory Agreeme', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'BB_TCM_PRIVACY_NOTICE', 'ce93131f-68f2-4cf7-80e0-51a0636de5f2', 'TCM Privacy Notice', 'Y', 'A');



USE `invdb`;
DROP procedure IF EXISTS `save_tddc_acct_details`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `save_tddc_acct_details`(
  `p_acctnum` bigint(20),
  `p_clientAccountID` varchar(45),
  `p_caseNumber` varchar(45),
  `p_advisorId` bigint(20),
  `p_acctTypeId` varchar(45),
  `p_cashSweepVehicleChoiceId` varchar(45),
  `p_divIntPrefId` varchar(45),
  `p_monthStmtId` varchar(45),
  `p_tradConfId` varchar(45),
  `p_dupStatement` char(1),
  `p_dupTradeConfirm` char(1),
  `p_proxyAuthorizationId` varchar(45),
  `p_optoutRegulatory` char(1),
  `p_optoutBeneficiary` char(1),
  `p_optoutFunding` char(1),
  `p_optoutRecurring` char(1),
  `p_createdBy` varchar(45)
  )
BEGIN

	DECLARE tAcctType	VARCHAR(30);

    declare v_advisorId int;

    declare v_advId,v_repId varchar(45);
    select advisor, rep into v_advId,v_repId from user_trade_profile
where acctnum=p_acctnum;

    select id into v_advisorId from invdb.dc_advisor_details where advisorName= v_advId
    and repId=
(CASE WHEN v_repId is null or v_repId ='' THEN 'CATCHALL'
        ELSE v_repId
    END);

		INSERT INTO `dc_acct_details`
			(`acctnum`,
			`clientAccountID`,
			`caseNumber`,
			`advisorId`,
			`acctTypeId`,
			`cashSweepVehicleChoiceId`,
			`divIntPrefId`,
			`monthStmtId`,
			`tradConfId`,
			`dupStatement`,
			`dupTradeConfirm`,
			`proxyAuthorizationId`,
            `optoutRegulatory`,
            `optoutBeneficiary`,
            `optoutFunding`,
            `optoutRecurring`,
			`created`,
			`createdBy`)
		VALUES
			(`p_acctnum`,
			`p_clientAccountID`,
			`p_caseNumber`,
			IFNULL(v_advisorId,1),
			`p_acctTypeId`,
			`p_cashSweepVehicleChoiceId`,
			`p_divIntPrefId`,
			`p_monthStmtId`,
			`p_tradConfId`,
			`p_dupStatement`,
			`p_dupTradeConfirm`,
			`p_proxyAuthorizationId`,
			`p_optoutRegulatory`,
            `p_optoutBeneficiary`,
            `p_optoutFunding`,
            `p_optoutRecurring`,
			now(),
			`p_createdBy`
            )
		ON DUPLICATE KEY UPDATE
			`clientAccountID`	= `p_clientAccountID`
			,`caseNumber`		= `p_caseNumber`
			,`advisorId`		= IFNULL(`v_advisorId`,1)
			,`acctTypeId`		= `p_acctTypeId`
			,`cashSweepVehicleChoiceId` = `p_cashSweepVehicleChoiceId`
			,`divIntPrefId`		= `p_divIntPrefId`
			,`monthStmtId`		= `p_monthStmtId`
			,`tradConfId`		= `p_tradConfId`
			,`dupStatement`		= `p_dupStatement`
			,`dupTradeConfirm`	= `p_dupTradeConfirm`
			,`proxyAuthorizationId` = `p_proxyAuthorizationId`
            ,`optoutRegulatory`=`p_optoutRegulatory`
            ,`optoutBeneficiary`=`p_optoutBeneficiary`
            ,`optoutFunding`=`p_optoutFunding`
            ,`optoutRecurring`=`p_optoutRecurring`
			,`updated`			= now()
			,`updatedBy`		= `p_createdBy`
		;

        -- Save the Account Type in User Trade Profile Table for easy display.
        SELECT `displayName`
        INTO `tAcctType`
        FROM `dc_m_lookup`
        WHERE `lookupSet` = 'ACCTTYPE'
        AND   `lookupCode` = `p_acctTypeId`
        LIMIT 1;


        UPDATE `user_trade_profile`
			set `user_trade_profile`.`acctType` = IFNULL(`tAcctType`, `user_trade_profile`.`acctType`)
		WHERE `user_trade_profile`.`acctnum` = `p_acctnum`;

END$$

DELIMITER ;




USE `service`;
CREATE
 OR REPLACE VIEW `vw_service_config_details_new` AS
    SELECT
        `scd`.`company` AS `company`,
        `scd`.`service` AS `service`,
        `scd`.`vendor` AS `vendor`,
        `scd`.`mode` AS `mode`,
        `scd`.`name` AS `name`,
        `scd`.`value` AS `value`,
        `scd`.`encrFlag` AS `encrFlag`
    FROM
        (`service_master` `sm`
        JOIN `service_config_details` `scd`)
    WHERE
        ((`sm`.`company` = `scd`.`company`)
            AND (`sm`.`service` = `scd`.`service`)
            AND (`sm`.`status` = 'A'))
    ORDER BY `scd`.`company` , `scd`.`service` , `scd`.`vendor` , `scd`.`mode` , `scd`.`name`;




USE `service`;
CREATE TABLE `service_error_external` (
  `service` varchar(45) NOT NULL,
  `vendor` varchar(20) NOT NULL,
  `displayErrMsg` varchar(250) NOT NULL,
  `vendorErrCode` varchar(45) NOT NULL,
  `vendorErrMsg` varchar(250) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`service`,`vendor`,`vendorErrCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `service_error_internal` (
  `errCode` varchar(45) NOT NULL,
  `errMsg` varchar(250) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`errCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



USE `service`;
CREATE
 OR REPLACE VIEW `vw_service_error_external` AS
    SELECT
        `service_error_external`.`service` AS `service`,
        `service_error_external`.`vendor` AS `vendor`,
        `service_error_external`.`displayErrMsg` AS `displayErrMsg`,
        `service_error_external`.`vendorErrCode` AS `vendorErrCode`,
        `service_error_external`.`vendorErrMsg` AS `vendorErrMsg`,
        `service_error_external`.`status` AS `status`
    FROM
        `service_error_external`;


USE `service`;
CREATE
 OR REPLACE VIEW `vw_service_error_internal` AS
    SELECT
        `service_error_internal`.`errCode` AS `errCode`,
        `service_error_internal`.`errMsg` AS `errMsg`,
        `service_error_internal`.`status` AS `status`
    FROM
        `service_error_internal`;



        USE `service`;
DROP procedure IF EXISTS `sel_service_common_details`;

DELIMITER $$
USE `service`$$
CREATE PROCEDURE `sel_service_common_details`(
IN p_product  varchar(50),
IN p_service  varchar(50),
IN p_type  varchar(50)
)
BEGIN

if(p_service ='TRADE-PROCESS' and p_type='TRADE_FILE_DETAILS')then

	select vendor, fileName, fileType, fileExtension, delimeter, containsHeader,
	active, uploadDir, dbStoredProc,  preInstruction, postInstruction
	from service.trade_process_file_details where vendor= p_product;
elseif(p_service ='DOCUSIGN-SERVICES' and p_type='DOCUSIGN_MAPPING')then

	select * from service.dc_template_mapping where (dbColumn IS NOT NULL or dbColumn != '')order by tempCode, role, tab;
end if;

END$$

DELIMITER ;


USE `service`;
DROP procedure IF EXISTS `sel_service_details`;

DELIMITER $$
USE `service`$$
CREATE  PROCEDURE `sel_service_details`(
IN p_product  varchar(50),
IN p_service  varchar(50),
IN p_type  varchar(50),
IN p_info varchar(50)
)
BEGIN

if(p_service ='TRADE-PROCESS' and p_type='COMMON_DETAILS' and p_info='TRADE_FILE_DETAILS')then

	select vendor, fileName, fileType, fileExtension, delimeter, containsHeader,
	active, uploadDir, dbStoredProc,  preInstruction, postInstruction
	from service.trade_process_file_details
    where vendor= p_product;
elseif(p_service ='DOCUSIGN-SERVICES' and p_type='COMMON_DETAILS' and p_info='DOCUSIGN_MAPPING')then

	select * from service.dc_template_mapping
    where (dbColumn IS NOT NULL or dbColumn != '')
    order by tempCode, role, tab;
 elseif(p_service ='DOCUSIGN-SERVICES' and p_type='ADDITIONAL_DETAILS' and p_info='TEMPLATE_DETAILS')then

	select * from service.dc_template_details
    where company= p_product
    order by company,mode, tempCode;

elseif(p_type='OPERATION_DETAILS' and p_info='OPERATION_DETAILS')then

	select * from service.service_operation_details
    where  status='A' and company=p_product and service=p_service
	order by operation;
end if;

END$$

DELIMITER ;


INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'TAI_ADV_2AB', '01ee8896-5340-460c-87d8-b95fe9cf1b0d', 'TCM ADV 2AB', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'TAI_ADV_AGREE', 'c31f1730-84be-4ee5-bf6d-318b200d7e14', 'Tradition Advisory Agreement', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'TAI_PRIVACY_NOTICE', 'aa56763c-5fba-43c0-85cd-eabc75b0eeeb', 'TCM Privacy Notice', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'TAE_ADV_2AB', '09123936-8876-4263-aa1b-d741654e9505', 'TCM ADV 2AB', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'TAE_ADV_AGREE', 'd5a1c41b-21ce-4c99-9c8b-4c9ad403320a', 'Tradition Advisory Agreement', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('UAT', 'TCM', 'DOCUSIGN-SERVICES', 'TAE_PRIVACY_NOTICE', '7efde06a-1180-4001-a0d6-c995440d7a17', 'TCM Privacy Notice', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'TAI_ADV_2AB', '01ee8896-5340-460c-87d8-b95fe9cf1b0d', 'TCM ADV 2AB', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'TAI_ADV_AGREE', 'c31f1730-84be-4ee5-bf6d-318b200d7e14', 'Tradition Advisory Agreement', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'TAI_PRIVACY_NOTICE', 'aa56763c-5fba-43c0-85cd-eabc75b0eeeb', 'TCM Privacy Notice', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'TAE_ADV_2AB', '09123936-8876-4263-aa1b-d741654e9505', 'TCM ADV 2AB', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'TAE_ADV_AGREE', 'd5a1c41b-21ce-4c99-9c8b-4c9ad403320a', 'Tradition Advisory Agreement', 'Y', 'A');
INSERT INTO `service`.`dc_template_details` (`mode`, `company`, `service`, `tempCode`, `tempId`, `tempName`, `authRequired`, `status`) VALUES ('PROD', 'TCM', 'DOCUSIGN-SERVICES', 'TAE_PRIVACY_NOTICE', '7efde06a-1180-4001-a0d6-c995440d7a17', 'TCM Privacy Notice', 'Y', 'A');



INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('TAI_ADV_AGREE', 'Textbox', 'AdvisorName', 'firmName', 'Client', 'N');
INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('TAI_ADV_AGREE', 'Textbox', 'FullName', 'fullName', 'Client', 'N');
INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('TAI_ADV_AGREE', 'Textbox', 'JointAHFullName', 'fullName', 'Joint', 'N');
INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('TAE_ADV_AGREE', 'Textbox', 'AdvisorName', 'firmName', 'Client', 'N');
INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('TAE_ADV_AGREE', 'Textbox', 'FullName', 'fullName', 'Client', 'N');
INSERT INTO `service`.`dc_template_mapping` (`tempCode`, `tab`, `lable`, `dbColumn`, `role`, `isDisabled`) VALUES ('TAE_ADV_AGREE', 'Textbox', 'JointAHFullName', 'fullName', 'Joint', 'N');

