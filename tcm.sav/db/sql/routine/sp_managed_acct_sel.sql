DROP PROCEDURE IF EXISTS `sp_managed_acct_sel`;

DELIMITER $$
CREATE PROCEDURE `sp_managed_acct_sel`(
    IN p_logonid bigint(20),
	IN p_acctnum bigint(20)
)
BEGIN

 if (p_acctnum is NULL)
  then
	if (p_logonid is NOT NULL)
		then
		   SELECT uar.`logonid`,
				  uar.`acctnum`,
				  uar.`functionid`,
				  uar.`role`,
				  uar.`privileges`,
				  act.`accttype`,
				  act.`prefix` || ' ' || act.`firstname` || ' ' || act.middlename || ' ' || act.lastname || ' ' || act.suffix as name,
				  act.acctstate as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		   ,    `acct_info` act
		   WHERE uar.acctnum = act.acctnum
		   AND   uar.logonid = p_logonid;
        else
		   SELECT uar.`logonid`,
				  uar.`acctnum`,
				  uar.`functionid`,
				  uar.`role`,
				  uar.`privileges`,
				  act.`accttype`,
				  act.`prefix` || ' ' || act.`firstname` || ' ' || act.middlename || ' ' || act.lastname || ' ' || act.suffix as name,
				  act.acctstate as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		   ,    `acct_info` act
		   WHERE uar.acctnum = act.acctnum
		   AND   1 = 2;
    end if;
  else
		   SELECT uar.`logonid`,
				  uar.`acctnum`,
				  uar.`functionid`,
				  uar.`role`,
				  uar.`privileges`,
				  act.`accttype`,
				  act.`prefix` || ' ' || act.`firstname` || ' ' || act.middlename || ' ' || act.lastname || ' ' || act.suffix as name,
				  act.acctstate as acctstate,
				  funct_convert_timestamp(uar.`created`) as created,
				  funct_convert_timestamp(uar.`lastupdated`) as lastupdated
		   FROM `user_access_role` uar
		   ,    `acct_info` act
		   WHERE uar.acctnum = act.acctnum
		   AND   uar.acctnum = p_acctnum;
   end if;

END
$$
DELIMITER ;
