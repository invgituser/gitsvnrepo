DELETE FROM `invdb`.`user_logon`;
DELETE FROM `invdb`.`user_advisor_info`;
DELETE FROM `invdb`.`user_advisor_access`;


INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`stateRegistered`,`advisor`,`rep`,`emailmsgtype`,`access`,`created`)
VALUES
(1,'demo.advisor','c39680887e67fa9860fd197f1279805d','A','Demo','Advisor','demoadvisor@invessence.com','NJ','UOB','Demo','HTML','admin',now()),
(2,'mindcraft.advisor','c39680887e67fa9860fd197f1279805d','A','Mindcraft','Advisor','mindcraft@invessence.com','NJ','UOB','Mindcraft','HTML','admin',now()),
(3,'prashant.advisor','c39680887e67fa9860fd197f1279805d','A','Mehta','Prashant','prashant@invessence.com','NJ','UOB','001','HTML','admin',now()),
(4,'jigar.advisor','c39680887e67fa9860fd197f1279805d','A','Vyas','Jigar','jigar@invessence.com','NJ','UOB','002','HTML','admin',now()),
(5,'chris.advisor','c39680887e67fa9860fd197f1279805d','A','Lengle','Chris','chris@invessence.com','CT','UOB','003','HTML','admin',now())
;

INSERT INTO `invdb`.`user_advisor_info`
(`logonid`,`advisor`,`rep`,`accttype`,`displayName`,`logo`,`advisor_css`,`created`)
VALUES
(1,'UOB','Demo','REP','Demo Advisor',null,null,now()),
(2,'UOB','Mindcraft','REP','Mindcraft Advisor',null,null,now()),
(3,'UOB','001','REP','Prashant Mehta',null,null,now()),
(4,'UOB','002','REP','Jigar Vyas',null,null,now()),
(5,'UOB','003','REP','Chris Lengle',null,null,now())
;

INSERT INTO `invdb`.`user_advisor_access`
(`logonid`,`advisor`,`rep`,`created`)
VALUES
(1,'UOB','Demo',now()), 
(2,'UOB','Mindcraft',now()),
(3,'%','%',now()),
(4,'%','%',now()),
(5,'%','%',now())
;


INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`advisor`,`rep`,`stateRegistered`,`emailmsgtype`,`access`,`created`)
VALUES
(10,'mobile.test','c39680887e67fa9860fd197f1279805d','A','Access','Mobile','mobile@invessence.com','UOB','Demo','NJ','HTML','user',now()),
(11,'demo.user','c39680887e67fa9860fd197f1279805d','A','Demo','User','demo@invessence.com','UOB','Demo','NJ','HTML','user',now()),
(12,'test.mindcraft','c39680887e67fa9860fd197f1279805d','A','Mindcraft','Test','support@invessence.com','UOB','Mindcraft','NJ','HTML','user',now()),
(13,'prashant.mehta','c39680887e67fa9860fd197f1279805d','A','Mehta','Prashant','prashant.mehta001@gmail.com','UOB','001','NJ','HTML','user',now()),
(14,'jigar.vyas','c39680887e67fa9860fd197f1279805d','A','Vyas','Jigar','javyas@gmail.com','UOB','002','NJ','HTML','user',now()),
(15,'chris.Lengle','c39680887e67fa9860fd197f1279805d','A','Lengle','Chris','chris@gmail.com','UOB','003','CT','HTML','user',now()),
(16,'michael.frank','c39680887e67fa9860fd197f1279805d','A','Frank','Michael','mookie07078@verizon.net','UOB','001','NJ','HTML','user',now()),
(17,'marylou','c39680887e67fa9860fd197f1279805d','A','Giustini','Mary Lou','Mlg416@gmail.com','UOB','001','IL','HTML','user',now())
;


