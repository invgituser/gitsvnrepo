DROP PROCEDURE IF EXISTS `web_page1_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE `web_page1_user_trade_profile`(
	IN  p_addmodflag VARCHAR(1),
    IN  p_logonid    BIGINT(20),
	INOUT	p_acctnum	bigint(20),
    IN  p_goal       varchar(30),
	IN	p_acctType	varchar(30),
	IN	p_age	integer,
	IN	p_horizon	integer,
	IN	p_initialInvestment	integer,
	IN	p_recurringInvestment	integer,
	IN	p_experience	tinyint,
	IN	p_objective	tinyint,
	IN	p_investmentplan	tinyint,
	IN	p_charitablegoals	integer
)
BEGIN 

   BEGIN
	   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
		   BEGIN

			INSERT INTO `user_trade_profile` (
				`goal`,
				`acctType`,
				`age`,
				`horizon`,
				`initialInvestment`,
				`recurringInvestment`,
				`experience`,
 				`longTermGoal`,
				`stayInvested`,
				`charitablegoals`,
				`created`
			)
			VALUES (
				p_goal	,
				p_acctType,
				p_age	,
				p_horizon	,
				p_initialInvestment	,
				p_recurringInvestment	,
				p_experience	,
				p_objective	,
				p_investmentplan	,
				p_charitablegoals,
				now()
			);

			select last_insert_id() into p_acctnum;
			call sp_user_access_add_mod('A', p_logonid, p_acctnum, NULL, NULL, NULL);
			
		   END;
	   ELSE
		   BEGIN
			 UPDATE  `user_trade_profile`
			 SET 
				`goal`	 =	p_goal	,
				`acctType`	 =	p_acctType	,
				`age`	 =	p_age	,
				`horizon`	 =	p_horizon	,
				`initialInvestment`	 =	p_initialInvestment	,
				`recurringInvestment`	 =	p_recurringInvestment	,
				`experience`	 =	p_experience	,
				`longTermGoal`	 =	p_objective	,
				`stayInvested`	 =	p_investmentplan	,
				`charitablegoals`	 =	p_charitablegoals	,
				`lastupdated` = now()
			 WHERE
				`acctnum` = p_acctnum;
		   END;
	   END IF;
	END;

END$$
DELIMITER ;
