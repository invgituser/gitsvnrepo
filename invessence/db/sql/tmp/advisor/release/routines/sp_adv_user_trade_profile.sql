DROP PROCEDURE IF EXISTS `sp_adv_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE `sp_adv_user_trade_profile`(
    IN  p_logonid		BIGINT(20),
	INOUT p_acctnum		bigint(20),
	IN  p_advisor		varchar(20),
	IN  p_theme			varchar(30),
	IN  p_email         varchar(60),
	IN  p_firstname     varchar(40),
	IN  p_lastname      varchar(40),
	IN	p_goal			varchar(30),
	IN	p_accttype	    varchar(30),
	IN	p_age			integer,
	IN	p_horizon		integer,
	IN	p_initialInvestment		integer,
	IN	p_recurringInvestment	integer,
	IN	p_riskIndex		double
)
BEGIN 

   DECLARE t_clientlogon BIGINT(20);
   DECLARE t_addmodflag  VARCHAR(1);
   DECLARE t_ibacct		 VARCHAR(8);

   BEGIN
		IF (p_acctnum is null or p_acctnum < 0) 
			then
				set t_addmodflag = 'A';
			else
				set t_addmodflag = 'M';
		END IF;
		
		IF (t_addmodflag = 'A') THEN			
			CALL `sp_login_add_mod`
			(
			   'A',
			   t_clientlogon,
			   p_email, -- userid
			   p_email,
			   'Default123', -- password
			   'I', -- logonstatus (Inactive)
			   p_lastname,
			   p_firstname,
			   null, -- p_state,
			   null, -- p_emailalt,
			   'Advisor', -- p_leadsource,
			   null, -- p_question1,
			   null, -- p_answer1
			   null, -- IN p_question2 varchar(60),
			   null, -- IN p_answer2 varchar(40),
			   null, -- IN p_question3 varchar(60),
			   null, -- IN p_answer3 varchar(40),
			   null, -- IN p_ip varchar(20),
			   null, -- IN p_macaddress varchar(20),
			   null, -- IN p_resetID varchar(8),
			   null -- IN p_cookieID varchar(5)
			);

			SET t_addmodflag = 'A';
		else
			SELECT MIN(IB_acctnum)
			INTO t_ibacct
			FROM IB_Accounts
			WHERE acctnum = p_acctnum;

			IF (t_ibacct is null)
			then
				SELECT MIN(logonid)
				INTO t_clientlogon
				FROM user_access_role
				WHERE acctnum = p_acctnum
				AND   role = 'OWNER';

				SET t_addmodflag = 'M';

			else
				SET t_addmodflag = 'X';
			END IF;
		END IF;

		IF (t_addmodflag = 'A')
		then

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
				`dependent`,
				`riskIndex`,
				`tradePreference`,
				`keepLiquid`,
				`created`
			)
			VALUES (
				p_advisor,
				p_theme,
				p_goal	,
				p_accttype,
				p_age	,
				p_horizon	,
				p_initialInvestment	,
				p_recurringInvestment	,
				0, -- p_experience	,
				0, -- p_longTermGoal	,
				1, -- p_stayInvested,
				0, -- p_charitablegoals	,
				0, -- p_dependent	,
				p_riskIndex,
				'A', -- tradePreference (Automatic)
				0, -- p_keepLiquid
				now()
			);

			select last_insert_id() into p_acctnum;
			-- Add two roles,  one for Advisor
			-- Other for Owner
			call sp_user_access_add_mod('A', p_logonid, p_acctnum, null, 'ADVISOR', 'A');
			call sp_user_access_add_mod('A', t_clientlogon, p_acctnum, null, 'OWNER', 'F');
		END IF;

		IF (t_addmodflag = 'M')
		then
			UPDATE `user_trade_profile`
				SET
				`advisor` = p_advisor,
				`theme` = p_theme,
				`goal` = p_goal,
				`acctType` = p_accttype,
				`age` = p_age,
				`horizon` = p_horizon,
				`initialInvestment` = p_initialInvestment,
				`recurringInvestment` = p_recurringInvestment,
				`riskIndex` = p_riskIndex,
				`lastupdated` = now()
			 WHERE acctnum = p_acctnum
			 ;
				
		END IF;
   END;

END$$
DELIMITER ;
