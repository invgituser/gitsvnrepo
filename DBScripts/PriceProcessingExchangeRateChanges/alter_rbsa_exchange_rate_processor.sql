drop procedure if exists rbsa.exchange_rate_processor;
delimiter $$
CREATE PROCEDURE rbsa.exchange_rate_processor(in p_ticker varchar(20),in p_direction varchar(10),in p_exchngsymbol varchar(10),in p_insrtexchngsymbol varchar(20),in p_multiplying_factor double,in bsCrncy varchar(10))
 begin
   DECLARE done INT DEFAULT FALSE;
   DECLARE curcnt INT;
  declare currExcngFactor,p_businessdate varchar(10);
  
  declare p_open_price,p_close_price,p_high_price,p_low_price,p_adjusted_price,p_exchangeRate,p_reverseExchangeRate,p_exchgRate double;
  Declare cur1 CURSOR FOR Select open_price,close_price,high_price,low_price,adjusted_price,businessdate,sed.exchangeRate,sed.reverseExchangeRate,ifnull(trd.exchangeRate,0.0)
	From rbsa.tmp_rbsa_daily trd join rbsa.sec_exchangerate_data sed on(trd.businessdate=sed.exchangeDate) where sed.symbol=p_exchngsymbol;
 
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 
   set curcnt=0;
   OPEN cur1;
 
   read_loop: LOOP
   FETCH cur1 INTO p_open_price,p_close_price,p_high_price,p_low_price,p_adjusted_price,p_businessdate,p_exchangeRate,p_reverseExchangeRate,p_exchgRate;
 
 
   IF done THEN
   LEAVE read_loop;
   END IF;
 
 
   SET curcnt=curcnt+1;
   if(p_direction='forward') then
  UPDATE rbsa.tmp_rbsa_daily rd
  SET    
     rd.converted_adjusted_price=((p_exchangeRate*p_multiplying_factor) * (p_adjusted_price))     
  ,rd.base_currency=bsCrncy
  ,rd.exchangeRate=p_exchgRate+(p_exchangeRate*p_multiplying_factor)
     WHERE
      rd.ticker = p_ticker
          AND rd.businessdate = p_businessdate;
          
          else
          
  UPDATE rbsa.tmp_rbsa_daily rd
  SET
  rd.converted_adjusted_price=((p_reverseExchangeRate*p_multiplying_factor) * (p_adjusted_price))
  ,rd.base_currency=bsCrncy
  ,rd.exchangeRate=p_exchgRate+(p_reverseExchangeRate*p_multiplying_factor)      
  WHERE
      rd.ticker = p_ticker
          AND rd.businessdate = p_businessdate;
          end if;
 
   END LOOP;
 
   CLOSE cur1;
  SELECT curcnt AS 'UPDATED DATA COUNT';
  
 
  end