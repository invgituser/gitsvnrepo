## Create Procedure rbsa.get_hodidays_price_data

USE `rbsa`;
DROP procedure IF EXISTS `get_hodidays_price_data`;

DELIMITER $$
USE `rbsa`$$
CREATE PROCEDURE `get_hodidays_price_data`(in p_startdt varchar(10),in p_enddt varchar(10))
begin
 DECLARE done INT DEFAULT FALSE;
 DECLARE curcnt INT;
 declare p_businessdate,p_previousbdate varchar(10);
 -- DECLARE cur1 CURSOR FOR  select weekday(rtrd.businessdate) as daycnt,rtrd.businessdate from rbsa.tmp_rbsa_daily_a rtrd where weekday(rtrd.businessdate) <5 ;
 Declare cur1 CURSOR FOR Select  p.businessdate,p.prev_businessdate From invdb.inv_date_table p left join rbsa.tmp_rbsa_daily s on ( s.businessdate=p.businessdate)
 Where s.businessdate is null and  p.businessdate >= p_startdt and p.businessdate <=p_enddt ;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

 set curcnt=0;
 OPEN cur1;

 read_loop: LOOP
 FETCH cur1 INTO p_businessdate,p_previousbdate;


 IF done THEN
 LEAVE read_loop;
 END IF;


 SET curcnt=curcnt+1;

  -- update rbsa.tmp_rbsa_daily set prev_businessdate=p_businessdate where ticker='BIL' and prev_businessdate=p_previousbdate;

  insert into rbsa.tmp_rbsa_daily (dest_currency,ticker,businessdate,open_price,close_price,high_price,low_price,adjusted_price,converted_adjusted_price,volume,
prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,converted_prev_adjusted,converted_prev_monthly_adjusted)
select dest_currency,ticker,p_businessdate,open_price,close_price,high_price,low_price,adjusted_price,converted_adjusted_price,volume,
prev_businessdate,prev_close_price,daily_return,prev_month_businessdate,prev_monthly_adjusted,monthly_return,converted_prev_adjusted,converted_prev_monthly_adjusted
 from rbsa.tmp_rbsa_daily  where businessdate=p_previousbdate;

 -- SELECT p_businessdate, p_previousbdate;



 -- set @jsonTableQuery :=concat('',@jsonTableQuery,' -', curcnt) ;

 END LOOP;

 CLOSE cur1;
 SELECT curcnt AS 'MISSING DAYS COUNT';
 end$$

DELIMITER ;

