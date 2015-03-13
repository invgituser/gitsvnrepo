DROP VIEW IF EXISTS `vw_user_logon`;

CREATE 
VIEW `vw_user_logon` AS
    select 
        `ui`.`logonid` AS `logonid`,
        `ui`.`userid` AS `userid`,
        `ui`.`email` AS `email`,
        `ui`.`pwd` AS `pwd`,
        `ui`.`logonstatus` AS `logonstatus`,
        `ui`.`attempts` AS `attempts`,
        `ui`.`ip` AS `ip`,
        `ui`.`macaddress` AS `macaddress`,
        `ui`.`cookieID` AS `cookieID`,
		`ui`.`state` as state,
        `ui`.`resetID` AS `resetID`,
        ifnull(`ai`.`accttype`, 'OWNER') AS `accttype`,
        `ai`.`logo` AS `logo`,
        `ai`.`groupname` AS `groupname`,
        `ui`.`emailmsgtype` AS `emailmsgtype`,
        ifnull(`ui`.`access`, 'User') AS `access`
    from
        (`user_logon` `ui`
        left join `advisor_info` `ai` ON ((`ui`.`logonid` = `ai`.`logonid`)));
