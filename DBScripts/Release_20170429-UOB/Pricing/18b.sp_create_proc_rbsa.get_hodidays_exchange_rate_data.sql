## create procedrue for generating historical holiday exchange data

USE rbsa;
DROP procedure IF EXISTS get_hodidays_exchange_rate_data;

DELIMITER $$
USE rbsa$$
CREATE PROCEDURE get_hodidays_exchange_rate_data(in p_startdt varchar(10),in p_enddt varchar(10),in p_symbol varchar(20))
begin 
  DECLARE done INT DEFAULT FALSE;
  DECLARE curcnt INT default 0;
  declare p_businessdate,p_previousbdate varchar(10);
  -- DECLARE cur1 CURSOR FOR  select weekday(rtrd.businessdate) as daycnt,rtrd.businessdate from rbsa.tmp_rbsa_daily_a rtrd where weekday(rtrd.businessdate) <5 ;
  Declare cur1 CURSOR FOR 
 Select  p.businessdate,p.prev_businessdate From invdb.inv_date_table p left join rbsa.sec_exchangerate_data s on ( s.exchangeDate=p.businessdate  and  s.symbol=p_symbol) 
  Where s.exchangeDate is null and  p.businessdate >= p_startdt and p.businessdate <=p_enddt;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  set curcnt=0;
  OPEN cur1;
  
  read_loop: LOOP
  FETCH cur1 INTO p_businessdate,p_previousbdate;
  
  
  IF done THEN
  LEAVE read_loop;
  END IF;
  
  
  SET curcnt=curcnt+1;
   
   insert into rbsa.sec_exchangerate_data (symbol,exchangeDate,exchangeRate,reverseExchangeRate,created) 
 select symbol,p_businessdate,exchangeRate,reverseExchangeRate,now() from rbsa.sec_exchangerate_data  where exchangeDate=p_previousbdate and symbol=p_symbol;
  
  END LOOP;
  
  CLOSE cur1;
  SELECT curcnt AS 'MISSING DAYS COUNT';
  end$$

DELIMITER ;

