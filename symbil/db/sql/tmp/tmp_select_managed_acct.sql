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
