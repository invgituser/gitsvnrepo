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
				  IFNULL(IB_acctNum,"-") as IB_Account,
				  0 as functionid,
				  'Owner' as role,
				  'A' as privileges,
				  profile.`goal` as goal,
				  profile.`acctType` as accttype,
				  IFNULL(ib.`accountStatus`,'Pending') as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		   STRAIGHT_JOIN `user_trade_profile` profile
					ON uar.acctnum = profile.acctnum
		   LEFT JOIN `IB_Accounts` ib
					ON profile.acctnum = ib.acctnum
		   WHERE   uar.logonid in (select ul.logonid from user_logon ul where ul.userid = p_userid);
        else
		   SELECT uar.`logonid`,
				  uar.`acctnum`,
				  IFNULL(IB_acctNum,"-") as IB_Account,
				  0 as functionid,
				  'Owner' as role,
				  'A' as privileges,
				  profile.`goal` as goal,
				  profile.`acctType` as accttype,
				  IFNULL(ib.`accountStatus`,'Pending') as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		   STRAIGHT_JOIN `user_trade_profile` profile
					ON uar.acctnum = profile.acctnum
		   LEFT JOIN `IB_Accounts` ib
					ON profile.acctnum = ib.acctnum
		   WHERE   1 = 2;
    end if;
  else
		   SELECT uar.`logonid`,
				  uar.`acctnum`,
				  IFNULL(IB_acctNum,"-") as IB_Account,
				  0 as functionid,
				  'Owner' as role,
				  'A' as privileges,
				  profile.`goal` as goal,
				  profile.`acctType` as accttype,
				  IFNULL(ib.`accountStatus`,'Pending') as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		   STRAIGHT_JOIN `user_trade_profile` profile
					ON uar.acctnum = profile.acctnum
		   LEFT JOIN `IB_Accounts` ib
					ON profile.acctnum = ib.acctnum
		   WHERE uar.logonid = p_logonid;
   end if;

END$$
DELIMITER ;
