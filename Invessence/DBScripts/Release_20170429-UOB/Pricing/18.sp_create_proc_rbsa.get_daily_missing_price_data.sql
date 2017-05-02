## Create Procedure rbsa.get_daily_missing_price_data

USE `rbsa`;
DROP procedure IF EXISTS `get_daily_missing_price_data`;

DELIMITER $$
USE `rbsa`$$
CREATE PROCEDURE `get_daily_missing_price_data`(in p_startdt varchar(10),in p_ticker varchar(10),in p_dest_currency varchar(20))
begin
declare p_previousbdate varchar(10);

select prev_businessdate into p_previousbdate from invdb.inv_date_table where businessdate=p_startdt;

insert into rbsa.tmp_rbsa_daily (dest_currency,ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,converted_adjusted_price,volume,
prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,converted_prev_adjusted,converted_prev_monthly_adjusted)
select dest_currency,ticker,p_startdt,open_price,close_price,high_price,low_price,adjusted_price,converted_adjusted_price,volume,
prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,converted_prev_adjusted,converted_prev_monthly_adjusted
 from rbsa.rbsa_daily  where businessdate=p_previousbdate and ticker=p_ticker and dest_currency=p_dest_currency;

end$$

DELIMITER ;



