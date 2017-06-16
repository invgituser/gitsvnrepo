DROP PROCEDURE IF EXISTS invdb.`edit_user_trade_profile`;

DELIMITER $$
CREATE PROCEDURE invdb.`edit_user_trade_profile`(
	IN	p_logonid	bigint(20),
	IN  p_acctnum	bigint(20),
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

			call `invdb`.`save_user_trade_profile_audit`(p_acctnum);
			call `invdb`.`save_user_risk_questions_audit`(p_acctnum);
	
			 UPDATE  `invdb`.`user_trade_profile`
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
                
		IF (p_acctnum is null)
			then set p_acctnum = 0;
		else
			if (IFNULL(p_logonid,0) > 0)
			then
			call `invdb`.`sp_login_access_add_mod`( p_logonid, p_acctnum, 'OWNER', 'W');
			end if;
		end if;
        
         call `invdb`.`sp_user_profile_manage`(p_acctnum,'R');

END$$
DELIMITER ;