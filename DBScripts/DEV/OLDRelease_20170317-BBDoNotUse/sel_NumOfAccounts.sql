DROP PROCEDURE IF EXISTS `invdb`.`sel_NumOfAccounts`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_NumOfAccounts`(
    In p_logonid BIGINT(20)
)
BEGIN

select count(*) as numofaccounts
from invdb.user_access_role uar
,    invdb.ext_acct_info eai
where uar.acctnum = eai.acctnum
AND   uar.role = 'OWNER'
AND   uar.logonid = p_logonid;
	
END$$
DELIMITER ;
