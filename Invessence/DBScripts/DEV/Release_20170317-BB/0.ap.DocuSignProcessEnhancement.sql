
ALTER TABLE `invdb`.`dc_request_audit` 
ADD COLUMN `product` VARCHAR(50) NULL AFTER `id`,
ADD COLUMN `mode` VARCHAR(20) NULL AFTER `product`;


USE `invdb`;
DROP procedure IF EXISTS `dc_request_auditrial`;

DELIMITER $$
USE `invdb`$$
CREATE DEFINER=`admin`@`192.168.100.92` PROCEDURE `dc_request_auditrial`(
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


