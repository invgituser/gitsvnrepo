DROP PROCEDURE IF EXISTS `invdb`.`report_visitors`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`report_visitors`(
)
BEGIN

	SELECT user_trade_profile.acctnum
		   ,IFNULL(user_trade_profile.firstname, user_logon.firstname) as firstname
		   ,IFNULL(user_trade_profile.lastname, user_logon.lastname) as firstname
		   ,user_logon.email as email
           ,user_trade_profile.created as datetested
           ,user_trade_profile.goal as goal
           ,user_trade_profile.portfolioName as strategy
           ,user_trade_profile.initialInvestment as strategy
           ,CASE (user_trade_profile.status)
				WHEN 'V' THEN 'Visitor'
                WHEN 'N' THEN 'Pending'
                WHEN 'P' THEN 'Processing'
                ELSE 'Pending'
			END as `status`
		   ,IFNULL(`user_advisor_info`.`companyname`,`user_trade_profile`.`advisor`) as advisor
		   ,IFNULL(`user_advisor_info`.`displayname`,`user_trade_profile`.`rep`) as rep           
	FROM user_trade_profile
		LEFT JOIN user_access_role
        ON (user_trade_profile.acctnum = user_access_role.acctnum)
		LEFT JOIN user_logon
        ON (user_access_role.logonid = user_logon.logonid)
        LEFT JOIN `user_advisor_info`
        ON (`user_trade_profile`.`advisor` = `user_advisor_info`.`advisor`)
        AND CASE WHEN (`user_trade_profile`.`rep` = '') THEN 'CATCHALL'
				 ELSE (`user_trade_profile`.`rep`)
			END = `user_advisor_info`.`rep`
	WHERE user_trade_profile.managed = 'N'
    AND  user_trade_profile.created > DATE_ADD(now(),INTERVAL -7 DAY)
    ;
 
END$$
DELIMITER ;
