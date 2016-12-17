DROP PROCEDURE IF EXISTS `invdb`.`save_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`save_user_trade_profile`(
	IN	p_logonid	bigint(20),
	INOUT	p_acctnum	bigint(20),
	IN  p_portfolioName VARCHAR(60),
    IN  p_advisor    VARCHAR(20),
    IN  p_rep	     VARCHAR(20),
    IN  p_firstname    VARCHAR(40),
    IN  p_lastname    VARCHAR(40),
    IN  p_theme		 VARCHAR(30),
    IN  p_goal       varchar(30),
	IN	p_acctType	varchar(30),
	IN	p_taxable	varchar(1),
	IN	p_age		integer,
	IN	p_horizon	integer,
	IN	p_initialInvestment	integer,
	IN	p_recurringInvestment	integer,
	IN	p_experience	tinyint,
	IN	p_objective		tinyint,
	IN	p_investmentplan	tinyint,
	IN	p_charitablegoals	integer,
	IN  p_keepLiquid 	integer,
	IN  p_riskIndex 	  integer,
	IN  p_riskCalcMethod  VARCHAR(1),
	IN  p_allocIndex 	  integer,
	IN  p_portfolioIndex  integer,
    IN  p_goalDesired 	  double
)
BEGIN 

	DECLARE t_found INTEGER;
    DECLARE t_status VARCHAR(1);

	BEGIN
		IF (IFNULL(p_acctnum,0) > 0)
			THEN
				SELECT COUNT(*)
				INTO t_found
				FROM `user_trade_profile`
				WHERE acctnum = p_acctnum;
			ELSE 
				set t_found = 0;
		END IF;
	END;

   BEGIN
	   IF (IFNULL(t_found,0) = 0) THEN
		   BEGIN
           
            set t_status = 
                CASE 
					WHEN (p_logonid is null) THEN 'V'
                    WHEN (p_logonid <= 0) THEN 'V'
                    ELSE 'N'
				END;

			INSERT INTO `user_trade_profile` (
				`portfolioName`,
			    `advisor`,
                `rep`,
                `firstname`,
                `lastname`,
				`theme`,
				`goal`,
				`acctType`,
				`age`,
				`horizon`,
				`initialInvestment`,
				`recurringInvestment`,
				`keepLiquid`,
				`experience`,
 				`longTermGoal`,
				`stayInvested`,
				`charitablegoals`,
				`riskIndex`,
				`taxable`,
				`assetIndex`,
				`portfolioIndex`,
                `goalAmount`,
                `managed`,
                `status`,
				`created`
			)
			VALUES (
				IFNULL(p_portfolioName,IFNULL(p_goal,'Retirement')),
				IFNULL(p_advisor,'Invessence'),
                p_rep,
                p_firstname,
                p_lastname,
				p_theme,  
				IFNULL(p_goal,'Retirement')	,
				IFNULL(p_acctType,'IRA'),
				IFNULL(p_age,30)	,
				IFNULL(p_horizon,35)	,
				IFNULL(p_initialInvestment,1000000)	,
				p_recurringInvestment	,
				IFNULL(p_keepLiquid,0),
				p_experience	,
				p_objective	,
				p_investmentplan	,
				p_charitablegoals,
				IFNULL(p_riskIndex,10),
				IFNULL(p_taxable,'N'),
				p_allocIndex,
				p_portfolioIndex,
                p_goalDesired,
                'N',
                t_status,
				now()
			);

			select last_insert_id() into p_acctnum;
            
			call `invdb`.`sp_user_profile_manage` (p_acctnum, t_status);

		   END;
	   ELSE
		   BEGIN
                        
			 UPDATE  `user_trade_profile`
			 SET
				`portfolioName` = IFNULL(p_portfolioName,`portfolioName`),
				`advisor` = IFNULL(p_advisor,`advisor`),
                `rep`     = p_rep,
                `firstname` = IFNULL(p_firstname,`firstname`),
                `lastname` = IFNULL(p_lastname,`lastname`),
				`theme` = IFNULL(p_theme,`theme`),
				`goal`	 =	IFNULL(p_goal,`goal`)	,
				`acctType`	 =	IFNULL(p_acctType,`acctType`)	,
				`age`	 =	IFNULL(p_age,`age`)	,
				`horizon`	 =	IFNULL(p_horizon,`horizon`)	,
				`initialInvestment`	 =	IFNULL(p_initialInvestment,`initialInvestment`)	,
				`recurringInvestment`	 =	p_recurringInvestment	,
				`experience`	 =	p_experience	,
				`longTermGoal`	 =	p_objective	,
				`stayInvested`	 =	p_investmentplan	,
				`charitablegoals`	 =	p_charitablegoals	,
				`riskIndex`      =  IFNULL(p_riskIndex,`riskIndex`),
				`keepLiquid`	 =  IFNULL(p_keepLiquid,`keepLiquid`),
				`taxable`        =  IFNULL(p_taxable,`taxable`),
				`assetIndex`	 =  p_allocIndex,
				`portfolioIndex` =  p_portfolioIndex,
				`goalAmount`	 =  p_goalDesired,
				`lastupdated`    = now()
			 WHERE
				`acctnum` = p_acctnum;
		   END;
	   END IF;
	END;

	IF (p_acctnum is null)
		then set p_acctnum = 0;
	else
		if (IFNULL(p_logonid,0) > 0)
        then
			call `sp_login_access_add_mod`( p_logonid, p_acctnum, 'OWNER', 'W');
        end if;
	end if;
    

END$$
DELIMITER ;
