DROP PROCEDURE IF EXISTS `report_historical_data`;

DELIMITER $$
CREATE PROCEDURE `report_historical_data`(
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
	SELECT daily.ticker, 
		   min(businessdate) as min_businessdate, 
		   max(businessdate) as max_businessdate
	FROM `rbsa`.`rbsa_daily` daily,
		 invdb.sec_asset_mapping
	WHERE lower(sec_asset_mapping.ticker) not in ('cash')
    AND   daily.ticker = sec_asset_mapping.ticker
	group by daily.ticker;
    
    
    SELECT 
		`sec_asset_mapping`.`theme`
		,`sec_asset_mapping`.`ticker`
		,`sec_asset_mapping`.`assetclass`
		,`sec_asset_mapping`.`assetName`
		,`sec_asset_mapping`.`assetcolor`
		,`sec_asset_mapping`.`assetsortorder`
		,`sec_asset_mapping`.`subclass`
		,`sec_asset_mapping`.`subclassName`
		,`sec_asset_mapping`.`subclasscolor`
		,`sec_asset_mapping`.`subclasssortorder`
		, daily.businessdate
		, IFNULL(monthly_return,0) as monthly_return
	FROM `invdb`.`sec_asset_mapping`
		, `rbsa`.`rbsa_daily` daily
        ,  (SELECT max(min_businessdate) as min_date, min(max_businessdate) as max_date from tmp_historical_dates) as tmp
	WHERE daily.businessdate >= tmp.min_date
	AND   daily.businessdate <= tmp.max_date
	AND   daily.ticker = sec_asset_mapping.ticker
	AND   daily.businessdate in (select last_businessdate from invdb.inv_monthly_date_table)
	ORDER BY 1, 2 desc;

END$$
DELIMITER ;
