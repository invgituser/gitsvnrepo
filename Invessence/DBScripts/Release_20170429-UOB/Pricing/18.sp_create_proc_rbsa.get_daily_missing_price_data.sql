## Create Procedure rbsa.get_daily_missing_price_data

USE rbsa;
DROP procedure IF EXISTS get_daily_missing_price_data;

DELIMITER $$
USE rbsa$$
CREATE PROCEDURE get_daily_missing_price_data(in p_startdt varchar(10),in p_ticker varchar(10))
begin 
declare p_previousbdate varchar(10);
 
select prev_businessdate into p_previousbdate from invdb.inv_date_table where businessdate=p_startdt;
   
insert into rbsa.tmp_rbsa_daily (ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,prev_close_price,daily_return,volume,monthly_return) select ticker,p_startdt,open_price,close_price,high_price,low_price,adjusted_price,prev_close_price,daily_return,volume,monthly_return from rbsa.rbsa_daily  where businessdate=p_previousbdate and ticker=p_ticker;
  
end$$

DELIMITER ;