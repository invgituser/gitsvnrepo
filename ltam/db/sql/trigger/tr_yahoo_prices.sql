
DROP TRIGGER IF EXISTS tr_yahoo_prices;
DELIMITER $$

CREATE TRIGGER `tr_yahoo_prices` 
AFTER INSERT ON `yahoo_prices` FOR EACH ROW
-- Edit trigger body code below this line. Do not edit lines above this one
BEGIN
   CALL  `sp_sec_daily_info_add_mod`

	( 'A' -- p_addmodflag
    , NEW.`ticker` -- p_ticker
	, STR_TO_DATE(NEW.`trade_date`,'%m/%d/%Y') -- p_businessdate
	, CAST(NEW.open_price as decimal(15,8)) -- p_open
    , CAST(NEW.high_price as decimal(15,8))  -- p_high
	, CAST(NEW.low_price as decimal(15,8))  -- p_low
    , CAST(NEW.close_price as decimal(15,8)) -- p_close
    , CAST(NEW.volume as unsigned)           -- p_volume
    , NULL  -- p_adjusted
	, NULL -- p_prev_businessdate
	, CAST(NEW.prev_close as decimal(15,8)) -- p_prev_close
    );

 END
$$
DELIMITER ;
