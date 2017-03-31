update `invdb`.`user_logon` set logonid=logonid+100 where logonid in(6,7,8,9);
update `invdb`.`user_advisor_info` set logonid=logonid+100 where logonid in(6,7,8,9);
update `invdb`.`user_advisor_access` set logonid=logonid+100 where logonid in(6,7,8,9);

DELETE FROM `invdb`.`user_logon` where logonid <100;
DELETE FROM `invdb`.`user_advisor_info`  where logonid <100;
DELETE FROM `invdb`.`user_advisor_access`  where logonid <100;

INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`stateRegistered`,`advisor`,`rep`,`emailmsgtype`,`access`,`created`)
VALUES
(1,'bb.advisor','c39680887e67fa9860fd197f1279805d','A','BBDemo','Advisor','demoadvisor@invessence.com','NJ','BB','','HTML','advisor',now()),
(2,'tcmintrep.advisor','c39680887e67fa9860fd197f1279805d','A','TCMIntRep100','Advisor','tcmintrep@invessence.com','NJ','BB-TCM','100','HTML','advisor',now()),
(3,'tcmextrep.advisor','c39680887e67fa9860fd197f1279805d','A','TCMExtRep200','Advisor','tcmextrep@invessence.com','NJ','BB-TCM','200','HTML','advisor',now()),
(4,'bb.catchall','c39680887e67fa9860fd197f1279805d','A','BBDemo','Advisor','catchallbbadvisor@invessence.com','NJ','BB','CATCHALL','HTML','advisor',now()),
(5,'tcm.catchall','c39680887e67fa9860fd197f1279805d','A','BBDemo','Advisor','catchalltcmadvisor@invessence.com','NJ','BB-TCM','CATCHALL','HTML','advisor',now())
;

INSERT INTO `invdb`.`user_advisor_info` (`logonid`, `advisor`, `rep`, `accttype`, `companyname`, `displayName`, `created`) 
VALUES 
('5', 'BB-TCM', 'CATCHALL', 'REP', 'Tradition Adviseres', 'TCMExtRep200 Advisor', now()),
('4', 'BB', 'CATCHALL', 'REP', 'Building Benjamins', 'BBDemo Advisor', now()),
('1', 'BB', '', 'REP', 'Building Benjamins', 'BBDemo Advisor', now()),
('2', 'BB-TCM', '100', 'REP', 'Tradition Adviseres', 'TCMIntRep100 Advisor', now()),
('3', 'BB-TCM', '200', 'REP', 'Tradition Adviseres', 'TCMExtRep200 Advisor', now());


INSERT INTO `invdb`.`user_advisor_access` (`logonid`, `advisor`, `rep`, `privileges`, `created`) 
VALUES 
('1', 'BB', '%', 'V', now()),
('2', 'BB-TCM', '100', 'V', now()),
('3', 'BB-TCM', '200', 'V', now()),
('4', 'BB', '%', 'V', now()),
('5', 'BB-TCM', '%', 'V', now());



INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`advisor`,`rep`,`stateRegistered`,`emailmsgtype`,`access`,`created`)
VALUES
(50,'mobile.bb','c39680887e67fa9860fd197f1279805d','A','Access','Mobile','mobilebb@invessence.com','BB','','NJ','HTML','user',now()),
(51,'mobile.tcm','c39680887e67fa9860fd197f1279805d','A','Access','Mobile','mobiletcm@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(61,'bb.user','c39680887e67fa9860fd197f1279805d','A','BB','User','bbuser@invessence.com','BB','','NJ','HTML','user',now()),
(62,'tcmintrep.user','c39680887e67fa9860fd197f1279805d','A','TCMIntRep','User','tcmintrepuser@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(63,'tcmextrep.user','c39680887e67fa9860fd197f1279805d','A','TCMExtRep','User','tcmextrepuser@invessence.com','BB-TCM','200','NJ','HTML','user',now()),
(64,'bbqa.user','c39680887e67fa9860fd197f1279805d','A','BBQA','User','bbqauser@invessence.com','BB','','NJ','HTML','user',now()),
(65,'tcmintrepqa.user','c39680887e67fa9860fd197f1279805d','A','TCMIntRepQa','User','tcmintrepqauser@invessence.com','BB-TCM','100','NJ','HTML','user',now()),
(66,'tcmextrepqa.user','c39680887e67fa9860fd197f1279805d','A','TCMExtRepQa','User','tcmextrepqauser@invessence.com','BB-TCM','200','NJ','HTML','user',now());


DELETE FROM `invdb`.`user_basket_access`  where advisor in('BB','BB-TCM');

INSERT INTO `invdb`.`user_basket_access`
(`advisor`,`theme`,`status`,`displayname`,`sortorder`,`primary`,`taxable`,`created`)
VALUES 
('BB', '0.BB', 'A', 'BB Strategy', '0', 'Y', 'N', now()),
('BB', 'T.0.BB', 'A', 'BB Strategy', '0', 'N', 'Y', now()),
('BB-TCM', '0.TA', 'A', 'TCM Strategy', '0', 'Y', 'N', now()),
('BB-TCM', 'T.0.TA', 'A', 'TCM Strategy', '0', 'N', 'Y', now());


