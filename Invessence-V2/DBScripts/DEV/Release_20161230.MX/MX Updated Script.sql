USE `invdb`;

CREATE TABLE `aggr_user_logon` (
  `logonid` bigint(20) NOT NULL,
  `userid` varchar(100) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  `created` datetime NOT NULL,
  `createdby` bigint(20) NOT NULL,
  `updated` datetime DEFAULT NULL,
  `updatedby` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`logonid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    SQL SECURITY DEFINER
VIEW `vwaggr_user_details` AS
    SELECT 
        `ul`.`logonid` AS `logonid`,
        `aul`.`userid` AS `aggrUserId`,
        `aul`.`pwd` AS `aggrPwd`,
        `aul`.`status` AS `aggrStatus`,
        `ul`.`userid` AS `userid`,
        `ul`.`logonstatus` AS `logonstatus`,
        `ul`.`prefix` AS `prefix`,
        `ul`.`lastname` AS `lastname`,
        `ul`.`firstname` AS `firstname`,
        `ul`.`middlename` AS `middlename`,
        `ul`.`suffix` AS `suffix`,        
        `ul`.`email` AS `email`
    FROM
        (`user_logon` `ul`
        LEFT JOIN `aggr_user_logon` `aul` ON ((`ul`.`logonid` = `aul`.`logonid`)));


USE `invdb`;
DROP procedure IF EXISTS `spaggr_user_mgmt`;

DELIMITER $$
USE `invdb`$$
CREATE PROCEDURE `spaggr_user_mgmt`(in p_logonid numeric(10), 
in p_userid varchar(100), 
in p_pwd varchar(100), 
in p_email varchar(100), 
in p_status varchar(1), 
in p_created datetime, 
in p_createdby varchar(100),
in p_opt varchar(20), 
out op_msgCode int(3),out op_msg varchar(20))
BEGIN
DECLARE r_count INTEGER;
if(p_opt='INSERT') then
select count(1) into r_count from aggr_user_logon where logonid=p_logonid; 
	if(r_count>0)then
		update aggr_user_logon set
		updated=p_created, 
		updatedby=p_createdby
		where logonid=p_logonid and userid=p_logonid;
		
	else
		Insert into aggr_user_logon(logonid, userid, pwd, email, status, created, createdby) 
		value(p_logonid, p_userid, p_pwd, p_email, p_status, p_created, p_createdby);  

	end if;    
		SELECT p_opt, 1 INTO op_msg , op_msgCode;
    
elseif(p_opt='UPDATE') then

	update aggr_user_logon set
	pwd=p_pwd, email=p_email, 
	status=p_status, 
	updated=p_created, 
	updatedby=p_createdby
	where logonid=p_logonid and userid=p_logonid;
    
	SELECT p_opt, 1 INTO op_msg , op_msgCode;
    
end if;
END$$

DELIMITER ;



ALTER TABLE `service`.`service_config_details` 
CHANGE COLUMN `value` `value` VARCHAR(100) NULL ;


-- drop table service.advisor_details;
CREATE TABLE service.advisor_details (
  `productId` varchar(45) NOT NULL,
  `advisorId` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `userId` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`productId`,`advisorId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- drop table service.advisor_service_mapping;
CREATE TABLE service.advisor_service_mapping (
  `productId` varchar(45) NOT NULL,
  `advisorId` varchar(45) NOT NULL,
  `service` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `vendor` varchar(45) NOT NULL,
  PRIMARY KEY (`productId`,`advisorId`,`service`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'A', 'MX');
INSERT INTO `service`.`service_master` (`company`, `service`, `status`, `vendor`) VALUES ('BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'I', 'YODLEE');

INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'MDX_REAL_TIME_API', 'https://int-live.moneydesktop.com/$$CLIENT_ID$$/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'SSO_API_URI', 'https://int-sso.moneydesktop.com/$$CLIENT_ID$$/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'USER_REGISTER_URL', '/users/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'WIDGET_URL', '/users/$$USER_ID$$/urls/master_widget/', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'MD_API_KEY', '66c8b276799a15d090a8a1d522d60acbbc3132e5', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'CONTENT_TYPE', 'application/vnd.moneydesktop.sso.v3+json', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'ACCEPT', 'application/vnd.moneydesktop.sso.v3+json', 'N');
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'CLIENT_ID', 'invessence', 'N');
UPDATE `service`.`service_config_details` SET `value`='https://int-live.moneydesktop.com/invessence/' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='MDX_REAL_TIME_API';
UPDATE `service`.`service_config_details` SET `value`='https://int-sso.moneydesktop.com/invessence/' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='SSO_API_URI';

UPDATE `service`.`service_config_details` SET `value`='https://int-live.moneydesktop.com/$$CLIENT_ID$$/$$USER_ID$$/' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='MDX_REAL_TIME_API';
UPDATE `service`.`service_config_details` SET `value`='https://int-sso.moneydesktop.com/$$CLIENT_ID$$/' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='SSO_API_URI';
UPDATE `service`.`service_config_details` SET `value`='https://int-sso.moneydesktop.com/$$CLIENT_ID$$/' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='SSO_API_URI';

UPDATE `service`.`service_config_details` SET `value`='https://int-live.moneydesktop.com/$$CLIENT_ID$$' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='MDX_REAL_TIME_API';
UPDATE `service`.`service_config_details` SET `value`='https://int-sso.moneydesktop.com/$$CLIENT_ID$$' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='SSO_API_URI';
UPDATE `service`.`service_config_details` SET `value`='/users' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='USER_REGISTER_URL';
UPDATE `service`.`service_config_details` SET `value`='/users/$$USER_ID$$/urls/master_widget' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='WIDGET_URL';


UPDATE `service`.`service_config_details` SET `name`='MAST_WIDGET_URL' WHERE `mode`='UAT' and`company`='BUILDINGBENJAMINS' and`service`='AGGREGATION-SERVICES' and`vendor`='MX' and`name`='WIDGET_URL';
INSERT INTO `service`.`service_config_details` (`mode`, `company`, `service`, `vendor`, `name`, `value`, `encrFlag`) VALUES ('UAT', 'BUILDINGBENJAMINS', 'AGGREGATION-SERVICES', 'MX', 'MOB_MAST_WIDGET_URL', '/users/$$USER_ID$$/urls/mobile_master_widget', 'N');



USE `service`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    SQL SECURITY DEFINER
VIEW `vw_service_config_details` AS
    SELECT 
        `scd`.`mode` AS `mode`,
        `scd`.`company` AS `company`,
        `scd`.`service` AS `service`,
        `scd`.`vendor` AS `vendor`,
        `scd`.`name` AS `name`,
        `scd`.`value` AS `value`,
        `scd`.`encrFlag` AS `encrFlag`,
        `scd`.`created` AS `created`,
        `scd`.`updated` AS `updated`
    FROM
        (`service`.`service_master` `sm`
        JOIN `service`.`service_config_details` `scd`)
    WHERE
        ((`sm`.`company` = (SELECT 
                `vw_invessence_switch`.`value`
            FROM
                `service`.`vw_invessence_switch`
            WHERE
                (`vw_invessence_switch`.`name` = 'COMPANY_NAME')))
            AND (`sm`.`company` = `scd`.`company`)
            AND (`sm`.`service` = `scd`.`service`)
            AND (`sm`.`vendor` = `scd`.`vendor`)
            AND (`sm`.`status` = 'A'))
    ORDER BY `scd`.`mode`, `scd`.`company`, `scd`.`service` , `scd`.`vendor`;


create or replace view service.vw_service_config_details_new as 
select scd.company, scd.service, scd.vendor, scd.mode, scd.name, scd.value, scd.encrFlag from service.service_master sm,service.service_config_details scd 
where  sm.company = scd.company
AND sm.service = scd.service
and  sm.status='A' order by scd.company, scd.service, scd.vendor, scd.mode;






INSERT INTO `invdb`.`aggr_user_logon` (`logonid`, `userid`, `pwd`, `email`, `status`, `created`, `createdby`) VALUES ('10', 'INV-10', 'XXXX', 'mobile@invessence.com', 'A', '2016-12-27 05:58:41', '10');
INSERT INTO `invdb`.`aggr_user_logon` (`logonid`, `userid`, `pwd`, `email`, `status`, `created`, `createdby`) VALUES ('11', 'INV-DEMO', 'XXXX', 'demo@invessence.com', 'A', '2016-12-21 12:09:21', '11');
INSERT INTO `invdb`.`aggr_user_logon` (`logonid`, `userid`, `pwd`, `email`, `status`, `created`, `createdby`) VALUES ('12', 'INV-12', 'XXXX', 'support@invessence.com', 'A', '2016-12-22 09:15:31', '12');


