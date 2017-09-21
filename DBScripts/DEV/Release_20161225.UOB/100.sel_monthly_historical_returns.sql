DROP PROCEDURE IF EXISTS `invdb`.`sel_monthly_historical_returns`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`sel_monthly_historical_returns`(
)
BEGIN

	DROP TEMPORARY TABLE IF EXISTS tmp_historical_dates;
	CREATE TEMPORARY TABLE tmp_historical_dates
	(
		ticker VARCHAR(20),
	    min_businessdate VARCHAR(10),
	    max_businessdate VARCHAR(10)
	);

	INSERT INTO tmp_historical_dates
	SELECT ticker, 
		   min(daily.businessdate) as min_businessdate, 
		   max(daily.businessdate) as max_businessdate
	FROM rbsa.rbsa_daily daily
	WHERE upper(daily.ticker) not in ('CASH')
	AND  daily.ticker in (select ticker from invdb.sec_prime_asset_group)
	group by daily.ticker;

	SELECT daily.ticker, 
		   daily.businessdate,
		   IFNULL(daily.daily_return,0) as daily_return
	FROM rbsa.rbsa_daily daily,
		 (SELECT max(min_businessdate) as min_date, min(max_businessdate) as max_date from tmp_historical_dates) as tmp
	WHERE daily.businessdate >= tmp.min_date
	AND   daily.businessdate <= tmp.max_date
	AND   daily.ticker in (select ticker from invdb.sec_prime_asset_group)
	AND   daily.businessdate in (select last_businessdate from invdb.inv_monthly_date_table)
	AND   upper(daily.ticker) not in ('CASH')
	ORDER BY 1, 2;
	

END$$
DELIMITER ;
