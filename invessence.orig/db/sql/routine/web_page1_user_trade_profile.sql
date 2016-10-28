DROP PROCEDURE IF EXISTS `web_page1_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE `web_page1_user_trade_profile`(
	IN  p_addmodflag VARCHAR(1),
    IN  p_logonid    BIGINT(20),
	INOUT	p_acctnum	bigint(20),
    IN  p_advisor    VARCHAR(20),
    IN  p_theme		 VARCHAR(30),
    IN  p_goal       varchar(30),
	IN	p_acctType	varchar(30),
	IN	p_age	integer,
	IN	p_horizon	integer,
	IN	p_initialInvestment	integer,
	IN	p_recurringInvestment	integer,
	IN	p_experience	tinyint,
	IN	p_objective	tinyint,
	IN	p_investmentplan	tinyint,
	IN	p_charitablegoals	integer,
	IN  p_riskIndex integer
)
BEGIN 

	DECLARE t_found INTEGER;

	BEGIN
		SELECT COUNT(*)
		INTO t_found
		FROM `user_trade_profile`
		WHERE acctnum = p_acctnum;
	END;
   BEGIN
	   IF (t_found is not NULL and t_found = 0) THEN
		   BEGIN

			INSERT INTO `user_trade_profile` (
			    `advisor`,
				`theme`,
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
				`riskIndex`,
				`created`
			)
			VALUES (
				IFNULL(p_advisor,'Invessence'),
				IFNULL(p_theme,'DEFAULT'),
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
				p_riskIndex,
				now()
			);

			select last_insert_id() into p_acctnum;
			call sp_user_access_add_mod('A', p_logonid, p_acctnum, NULL, NULL, NULL);
			-- Assign default accounts to Invessence Advisor.
			call sp_user_access_add_mod('A', 0, p_acctnum, NULL, 'ADVISOR', 'A');
			
		   END;
	   ELSE
		   BEGIN
			 UPDATE  `user_trade_profile`
			 SET
				`advisor` = IFNULL(p_advisor,'Invessence'),
				`theme` = IFNULL(p_theme,'DEFAULT'),
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
				`riskIndex`      =  p_riskIndex,
				`lastupdated` = now()
			 WHERE
				`acctnum` = p_acctnum;
		   END;
	   END IF;
	END;

	IF (p_acctnum is null)
		then set p_acctnum = 0;
	end if;

END$$
DELIMITER ;
