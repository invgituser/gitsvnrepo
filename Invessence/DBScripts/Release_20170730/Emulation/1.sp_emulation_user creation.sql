# Script for assigning developer role to qa USER

use invdb;

DELETE FROM `invdb`.`user_logon` where logonid in(13,14,15);
DELETE FROM `invdb`.`user_advisor_info`  where logonid  in(13,14,15);
DELETE FROM `invdb`.`user_advisor_access`  where logonid  in(13,14,15);

INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`stateRegistered`,`advisor`,`rep`,`emailmsgtype`,`access`,`created`)
VALUES
(13,'bb.superadmin','c39680887e67fa9860fd197f1279805d','A','BBDemo','SuperAdmin','bb.superadmin@invessence.com','NJ','BB','','HTML','SuperAdmin',now()),
(14,'tcm.superadmin','c39680887e67fa9860fd197f1279805d','A','TCMDemo','SuperAdmin','tcm.superadmin@invessence.com','NJ','BB-TCM','','HTML','SuperAdmin',now()),
(15,'uob.superadmin','c39680887e67fa9860fd197f1279805d','A','UOB','SuperAdmin','uob.superadmin@invessence.com','NJ','UOB','','HTML','SuperAdmin',now());


INSERT INTO `invdb`.`user_advisor_info` (`logonid`, `advisor`, `rep`, `accttype`, `companyname`, `displayName`, `created`)
VALUES
('13', 'BB', 'CATCHALL', 'REP', 'Building Benjamins', 'BBDemo Advisor', now()),
('14', 'BB-TCM', 'CATCHALL', 'REP', 'Tradition Advisors', 'Tradition Advisor', now()),
('15', 'UOB', 'CATCHALL', 'REP', 'UOB Kay Hain', 'UOB Kay Hain', now());


INSERT INTO `invdb`.`user_advisor_access` (`logonid`, `advisor`, `rep`, `privileges`, `created`)
VALUES
('13', 'BB%', '%', 'V', now()),
('14', 'BB-TCM', '%', 'V', now()),
('15', 'UOB', '%', 'V', now());

insert into invdb.role(logonid,role,status)select logonid,'Operations','A' from invdb.user_logon where userid='uob.advisor';