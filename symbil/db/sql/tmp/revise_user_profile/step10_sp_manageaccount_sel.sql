DROP PROCEDURE IF EXISTS `sp_manageaccount_sel`;

DELIMITER $$
CREATE PROCEDURE `sp_manageaccount_sel`(
    IN p_logonid bigint(20),
	IN p_userid  varchar(60)
)
BEGIN

 if (p_logonid is NULL or p_logonid = 0)
  then
	if (p_userid is NOT NULL)
		then
		   SELECT uar.`logonid`,
				  uar.`acctnum`,
				  IFNULL(uar.`functionid`,0) as functionid,
				  IFNULL(uar.`role`,'Admin') as role,
				  IFNULL(uar.`privileges`,'A') as privileges,
				  IFNULL(am.`description`,'Pending') as accttype,
				  profile.`acctType` as name,
				  IFNULL(sm.description,'Pending') as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		         STRAIGHT_JOIN `user_trade_profile` profile
					ON uar.acctnum = profile.acctnum
		         LEFT JOIN `acct_info` act
					ON uar.acctnum = act.acctnum
				 LEFT JOIN account_mapping am
					ON act.accttype=am.accttype
				 LEFT JOIN status_mapping sm
					ON act.acctstate = sm.status AND sm.table_name = 'ACCT'
		   WHERE   uar.logonid in (select ul.logonid from user_logon ul where ul.userid = p_userid);
        else
		   SELECT uar.`logonid`,
				  uar.`acctnum`,
				  IFNULL(uar.`functionid`,0) as functionid,
				  IFNULL(uar.`role`,'Admin') as role,
				  IFNULL(uar.`privileges`,'A') as privileges,
				  IFNULL(am.`description`,'Pending') as accttype,
				  profile.`profilename` as name,
				  IFNULL(sm.description,'Pending') as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		         STRAIGHT_JOIN `user_trade_profile` profile
					ON uar.acctnum = profile.acctnum
		         LEFT JOIN `acct_info` act
					ON uar.acctnum = act.acctnum
				 LEFT JOIN account_mapping am
					ON act.accttype=am.accttype
				 LEFT JOIN status_mapping sm
					ON act.acctstate = sm.status AND sm.table_name = 'ACCT'
		   WHERE   1 = 2;
    end if;
  else
		   SELECT uar.`logonid`,
				  uar.`acctnum`,
				  IFNULL(uar.`functionid`,0) as functionid,
				  IFNULL(uar.`role`,'Admin') as role,
				  IFNULL(uar.`privileges`,'A') as privileges,
				  IFNULL(am.`description`,'Pending') as accttype,
				  profile.`profilename` as name,
				  IFNULL(sm.description,'Pending') as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		         STRAIGHT_JOIN `user_trade_profile` profile
					ON uar.acctnum = profile.acctnum
		         LEFT JOIN `acct_info` act
					ON uar.acctnum = act.acctnum
				 LEFT JOIN account_mapping am
					ON act.accttype=am.accttype
				 LEFT JOIN status_mapping sm
					ON act.acctstate = sm.status AND sm.table_name = 'ACCT'
		   WHERE uar.logonid = p_logonid;
   end if;

END$$
DELIMITER ;
