DELETE FROM `invdb`.`user_logon`;
DELETE FROM `invdb`.`user_advisor_info`;
DELETE FROM `invdb`.`user_advisor_access`;


INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`stateRegistered`,`advisor`,`rep`,`emailmsgtype`,`access`,`created`)
VALUES
(1,'demo.advisor','c39680887e67fa9860fd197f1279805d','A','Demo','Advisor','demoadvisor@invessence.com','NJ','Invessence','Demo','HTML','admin',now()),
(2,'mindcraft.advisor','c39680887e67fa9860fd197f1279805d','A','Mindcraft','Advisor','mindcraft@invessence.com','NJ','Invessence','Mindcraft','HTML','admin',now()),
(3,'prashant.advisor','c39680887e67fa9860fd197f1279805d','A','Mehta','Prashant','prashant@invessence.com','NJ','Invessence','001','HTML','admin',now()),
(4,'jigar.advisor','c39680887e67fa9860fd197f1279805d','A','Vyas','Jigar','jigar@invessence.com','NJ','Invessence','002','HTML','admin',now()),
(5,'chris.advisor','c39680887e67fa9860fd197f1279805d','A','Lengle','Chris','chris@invessence.com','CT','Invessence','003','HTML','admin',now())
;

INSERT INTO `invdb`.`user_advisor_info`
(`logonid`,`advisor`,`rep`,`accttype`,`displayName`,`logo`,`advisor_css`,`created`)
VALUES
(1,'Invessence','Demo','REP','Demo Advisor',null,null,now()),
(2,'Invessence','Mindcraft','REP','Mindcraft Advisor',null,null,now()),
(3,'Invessence','001','REP','Prashant Mehta',null,null,now()),
(4,'Invessence','002','REP','Jigar Vyas',null,null,now()),
(5,'Invessence','003','REP','Chris Lengle',null,null,now())
;

INSERT INTO `invdb`.`user_advisor_access`
(`logonid`,`advisor`,`rep`,`created`)
VALUES
(1,'Invessence','Demo',now()), 
(2,'Invessence','Mindcraft',now()),
(3,'%','%',now()),
(4,'%','%',now()),
(5,'%','%',now())
;


INSERT INTO `invdb`.`user_logon`
(`logonid`,`userid`,`pwd`,`logonstatus`,`lastname`,`firstname`,`email`,`advisor`,`rep`,`stateRegistered`,`emailmsgtype`,`access`,`created`)
VALUES
(10,'mobile.test','c39680887e67fa9860fd197f1279805d','A','Access','Mobile','mobile@invessence.com','Invessence','Demo','NJ','HTML','user',now()),
(11,'demo.user','c39680887e67fa9860fd197f1279805d','A','Demo','User','demo@invessence.com','Invessence','Demo','NJ','HTML','user',now()),
(12,'test.mindcraft','c39680887e67fa9860fd197f1279805d','A','Mindcraft','Test','support@invessence.com','Invessence','Mindcraft','NJ','HTML','user',now()),
(13,'prashant.mehta','c39680887e67fa9860fd197f1279805d','A','Mehta','Prashant','prashant.mehta001@gmail.com','Invessence','001','NJ','HTML','user',now()),
(14,'jigar.vyas','c39680887e67fa9860fd197f1279805d','A','Vyas','Jigar','javyas@gmail.com','Invessence','002','NJ','HTML','user',now()),
(15,'chris.Lengle','c39680887e67fa9860fd197f1279805d','A','Lengle','Chris','chris@gmail.com','Invessence','003','CT','HTML','user',now()),
(16,'michael.frank','c39680887e67fa9860fd197f1279805d','A','Frank','Michael','mookie07078@verizon.net','Invessence','001','NJ','HTML','user',now()),
(17,'marylou','c39680887e67fa9860fd197f1279805d','A','Giustini','Mary Lou','Mlg416@gmail.com','Invessence','001','IL','HTML','user',now())
;


