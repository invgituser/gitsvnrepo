## Creatting procedrue for generating daily missing exchange data

USE rbsa;
DROP procedure IF EXISTS get_daily_missing_exchange_rate_data;

DELIMITER $$
USE rbsa$$
CREATE PROCEDURE get_daily_missing_exchange_rate_data(in p_startdt varchar(10),in p_symbol varchar(10))
begin 
 declare p_previousbdate varchar(10);
  
 select prev_businessdate into p_previousbdate from invdb.inv_date_table where businessdate=p_startdt;
    
 insert into rbsa.sec_exchangerate_data (symbol,exchangeDate,exchangeRate,reverseExchangeRate,created) 
 select symbol,p_startdt,exchangeRate,reverseExchangeRate,now() from rbsa.sec_exchangerate_data  where exchangeDate=p_previousbdate and symbol=p_symbol;
   
 end$$

DELIMITER ;

