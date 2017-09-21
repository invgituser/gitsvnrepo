DROP PROCEDURE IF EXISTS `invdb`.`report_historical_data`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`report_historical_data`(
	IN p_theme	varchar(20)
)
BEGIN

	DROP TEMPORARY TABLE IF EXISTS tmp_unique_tickers;
	CREATE TEMPORARY TABLE tmp_unique_tickers
	(
		ticker 				VARCHAR(20),
        displayName			VARCHAR(20)
	);


	INSERT INTO tmp_unique_tickers
		(ticker,displayName)
	SELECT distinct 
		   `sec_asset_mapping`.`ticker`,
		   'Portfolio' as performance_ticker
	FROM `invdb`.`sec_asset_mapping`
	WHERE lower(`sec_asset_mapping`.`ticker`) not in ('cash')
    AND   upper(`sec_asset_mapping`.`theme`) = upper(p_theme)
	;
    
	INSERT INTO tmp_unique_tickers
		(ticker,displayName)
	SELECT distinct 
		   `sec_performance_mapping`.`ticker`,
		   `sec_performance_mapping`.`displayName`
	FROM `invdb`.`sec_performance_mapping`
	WHERE lower(`sec_performance_mapping`.`ticker`) not in ('cash')
    AND   upper(`sec_performance_mapping`.`theme`) = upper(p_theme)
	;
    

	DROP TEMPORARY TABLE IF EXISTS tmp_historical_data;
	CREATE TEMPORARY TABLE tmp_historical_data
	(
		ticker 				VARCHAR(20),
        performance_ticker	VARCHAR(20),
	    min_businessdate	VARCHAR(10),
	    max_businessdate	VARCHAR(10)
	);

    
    -- Add all perfomance measuring data into this table.
    INSERT INTO tmp_historical_data
 	SELECT `daily`.`ticker`,
		   `tmp_unique_tickers`.`displayName`,
		   min(`daily`.`businessdate`) as min_businessdate, 
		   max(`daily`.`businessdate`) as max_businessdate
	FROM `tmp_unique_tickers`
	   , `rbsa`.`rbsa_daily` daily
	WHERE `daily`.`ticker` = `tmp_unique_tickers`.`ticker`
	group by `daily`.`ticker`, `tmp_unique_tickers`.`displayName`;
   
    SELECT 
		  `tmp_unique_tickers`.`ticker`
		, `tmp_unique_tickers`.`displayName`
		, daily.businessdate
		, IFNULL(monthly_return,0) as monthly_return
	FROM  `tmp_unique_tickers`
		, `rbsa`.`rbsa_daily` daily
        , (SELECT max(min_businessdate) as min_date, min(max_businessdate) as max_date from tmp_historical_data) as tmp
	WHERE businessdate >= tmp.min_date
	AND   businessdate <= tmp.max_date
	AND   daily.ticker = `tmp_unique_tickers`.`ticker`
	AND   daily.businessdate in (select last_businessdate from invdb.inv_monthly_date_table)
	ORDER BY 1, 2, 3 desc;

END$$
DELIMITER ;
