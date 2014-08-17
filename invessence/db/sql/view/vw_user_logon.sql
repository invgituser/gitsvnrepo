DROP VIEW IF EXISTS `vw_user_logon`;

CREATE VIEW `vw_user_logon` AS
    select 
		`ui`.`logonid` 		as logonid, 
		`ui`.`userid` 		as userid, 
		`ui`.`email` 		as email,
		`ui`.`pwd` 			as pwd, 
		`ui`.`logonstatus`	as logonstatus,
		`ui`.`attempts` 	as attempts,
		`ui`.`ip` 			as ip,
		`ui`.`macaddress`	as macaddress,
		`ui`.`cookieID`		as cookieID,
		`ui`.`resetID`		as resetID,
		`ui`.`accttype`		as accttype,
		`ai`.`logo` 		as logo,
		`ai`.`groupname`    as groupname
	from (`user_logon` `ui`
        left join `advisor_info` `ai`
			ON (`ui`.`logonid` = `ai`.`logonid`)
		)
    where
        (`ui`.`logonstatus` not in ('S'));
