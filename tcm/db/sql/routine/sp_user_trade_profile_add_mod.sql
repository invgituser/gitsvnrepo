DROP PROCEDURE IF EXISTS `sp_user_trade_profile_add_mod`;

DELIMITER $$
CREATE PROCEDURE `sp_user_trade_profile_add_mod`(
	IN  p_addmodflag VARCHAR(1),
    IN  p_logonid    BIGINT(20),
	INOUT	p_acctnum	bigint(20),
	IN	p_profileName	varchar(30),
	IN	p_age	integer,
	IN	p_horizon	integer,
	IN	p_initialInvestment	integer,
	IN	p_recurringInvestment	integer,
	IN	p_experience	tinyint,
	IN	p_objective	tinyint,
	IN	p_investmentplan	tinyint,
	IN	p_charitablegoals	integer,
	IN	p_dependent	integer,
	IN	p_minorName	varchar(60),
	IN	p_minorage	integer,
	IN	p_mortgage	integer,
	IN	p_medical	integer,
	IN	p_autoloans	integer,
	IN	p_lifeInsurance	integer,
	IN	p_autoInsurance	integer,
	IN	p_creditCardDebt	integer,
	IN	p_otherdebts	double,
	IN	p_currentincome	integer,
	IN	p_taxrate	double,
	IN	p_annuities	integer,
	IN	p_investmentProperties	integer,
	IN	p_moneymarket	integer,
	IN	p_mutualfund	integer,
	IN	p_insuranceCashValue	integer,
    IN  p_insuranceCoverage integer,
	IN	p_otherSavings	integer,
	IN	p_riskIndex	double
)
BEGIN 

   BEGIN
	   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
		   BEGIN
			IF (p_acctnum is null) THEN
				set p_acctnum = funct_Nextval('ACCTNUM');
			END IF;

			INSERT INTO `user_trade_profile` (
				`profileName`,
				`age`,
				`horizon`,
				`initialInvestment`,
				`recurringInvestment`,
				`experience`,
				`objective`,
				`investmentplan`,
				`charitablegoals`,
				`dependent`,
				`minorName`,
				`minorage`,
				`mortgage`,
				`medical`,
				`autoloans`,
				`lifeInsurance`,
				`autoInsurance`,
				`creditCardDebt`,
				`otherdebts`,
				`currentincome`,
				`taxrate`,
				`annuities`,
				`investmentProperties`,
				`moneymarket`,
				`mutualfund`,
				`insuranceCashValue`,
				`insuranceCoverage`,
				`otherSavings`,
				`riskIndex`,
				`created`
			)
			VALUES (
				p_profileName	,
				p_age	,
				p_horizon	,
				p_initialInvestment	,
				p_recurringInvestment	,
				p_experience	,
				p_objective	,
				p_investmentplan	,
				p_charitablegoals	,
				p_dependent	,
				p_minorName	,
				p_minorage	,
				p_mortgage	,
				p_medical	,
				p_autoloans	,
				p_lifeInsurance	,
				p_autoInsurance	,
				p_creditCardDebt	,
				p_otherdebts	,
				p_currentincome	,
				p_taxrate	,
				p_annuities	,
				p_investmentProperties	,
				p_moneymarket	,
				p_mutualfund	,
				p_insuranceCashValue	,
				p_insuranceCoverage ,
				p_otherSavings	,
				p_riskIndex,
				now()
			);

			select last_insert_id() into p_acctnum;
			call sp_user_access_add_mod('A', p_logonid, p_acctnum, NULL, NULL, NULL);
		   END;
	   ELSE
		   BEGIN
			 UPDATE  `user_trade_profile`
			 SET 
				`profileName`	 =	p_profileName	,
				`age`	 =	p_age	,
				`horizon`	 =	p_horizon	,
				`initialInvestment`	 =	p_initialInvestment	,
				`recurringInvestment`	 =	p_recurringInvestment	,
				`experience`	 =	p_experience	,
				`objective`	 =	p_objective	,
				`investmentplan`	 =	p_investmentplan	,
				`charitablegoals`	 =	p_charitablegoals	,
				`dependent`	 =	p_dependent	,
				`minorName`	 =	p_minorName	,
				`minorage`	 =	p_minorage	,
				`mortgage`	 =	p_mortgage	,
				`medical`	 =	p_medical	,
				`autoloans`	 =	p_autoloans	,
				`lifeInsurance`	 =	p_lifeInsurance	,
				`autoInsurance`	 =	p_autoInsurance	,
				`creditCardDebt`	 =	p_creditCardDebt	,
				`otherdebts`	 =	p_otherdebts	,
				`currentincome`	 =	p_currentincome	,
				`taxrate`	 =	p_taxrate	,
				`annuities`	 =	p_annuities	,
				`investmentProperties`	 =	p_investmentProperties	,
				`moneymarket`	 =	p_moneymarket	,
				`mutualfund`	 =	p_mutualfund	,
				`insuranceCashValue`	 =	p_insuranceCashValue	,
				`insuranceCoverage` =   p_insuranceCoverage ,
				`otherSavings`	 =	p_otherSavings	,
				`riskIndex`	 =	p_riskIndex,	
				`lastupdated` = now()
			 WHERE
				`acctnum` = p_acctnum;
		   END;
	   END IF;
	END;

END$$
DELIMITER ;
