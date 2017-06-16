DROP PROCEDURE IF EXISTS `invdb`.`sel_daily_prime_historical_returns`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_daily_prime_historical_returns`(
)
BEGIN

	DROP TEMPORARY TABLE IF EXISTS tmp_historical_dates;
	CREATE TEMPORARY TABLE tmp_historical_dates
	(
		ticker VARCHAR(20),
	    min_businessdate VARCHAR(10),
	    max_businessdate VARCHAR(10)
	);

	DROP TEMPORARY TABLE IF EXISTS tmp_ticker_list;
	CREATE TEMPORARY TABLE tmp_ticker_list
	(
		ticker VARCHAR(20)
	);

-- 	INSERT INTO tmp_ticker_list
-- 	SELECT DISTINCT 
-- 		   ticker 
-- 	FROM invdb.sec_theme;
 
	INSERT INTO tmp_ticker_list
	SELECT DISTINCT 
		   ticker 
	FROM invdb.sec_prime_asset_group
	WHERE upper(sec_prime_asset_group.ticker) not in ('CASH');

	-- DELETE FROM tmp_ticker_list
	-- WHERE upper(ticker) = 'CASH';

	INSERT INTO tmp_historical_dates
	SELECT daily.ticker, 
		   min(businessdate) as min_businessdate, 
		   max(businessdate) as max_businessdate
	FROM `rbsa`.`rbsa_daily` daily,
		 tmp_ticker_list
	WHERE daily.ticker = tmp_ticker_list.ticker
	group by daily.ticker;

	SELECT daily.ticker, count(*) as maxrows
	FROM 
		 tmp_ticker_list,
		 `rbsa`.`rbsa_daily` daily,
		 (SELECT max(min_businessdate) as min_date, min(max_businessdate) as max_date from tmp_historical_dates) as tmp
	WHERE businessdate >= tmp.min_date
	AND   businessdate <= tmp.max_date
	AND   daily.ticker = tmp_ticker_list.ticker
	AND   businessdate in (select businessdate from invdb.inv_date_table)
	GROUP BY daily.ticker; 
	
	SELECT daily.ticker, 
		   daily.businessdate,
		   IFNULL(daily.daily_return,0) as daily_return
	FROM 
		 tmp_ticker_list,
		 `rbsa`.`rbsa_daily` daily,
		 (SELECT max(min_businessdate) as min_date, min(max_businessdate) as max_date from tmp_historical_dates) as tmp
	WHERE daily.businessdate >= tmp.min_date
	AND   daily.businessdate <= tmp.max_date
	AND   daily.ticker = tmp_ticker_list.ticker
	AND   businessdate in (select businessdate from invdb.inv_date_table)
	ORDER BY 1, 2 desc;
	

END$$
DELIMITER ;
