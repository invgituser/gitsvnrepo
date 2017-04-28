## Create Procedure rbsa.exchange_rate_processor

USE rbsa;
DROP procedure IF EXISTS exchange_rate_processor;

DELIMITER $$
USE rbsa$$
CREATE  PROCEDURE exchange_rate_processor(in p_ticker varchar(20),in p_direction varchar(10),in p_exchngsymbol varchar(10),in p_insrtexchngsymbol varchar(20),in p_multiplying_factor double)
begin
  DECLARE done INT DEFAULT FALSE;
  DECLARE curcnt INT;
 declare currExcngFactor,p_businessdate varchar(10);
 -- declare p_businessdate,p_previousbdate varchar(10);
 declare p_open_price,p_close_price,p_high_price,p_low_price,p_adjusted_price,p_exchangeRate,p_reverseExchangeRate double;
 Declare cur1 CURSOR FOR Select open_price,close_price,high_price,low_price,adjusted_price,businessdate,exchangeRate,reverseExchangeRate From rbsa.tmp_rbsa_daily trd join rbsa.sec_exchangerate_data sed on(trd.businessdate=sed.exchangeDate) where sed.symbol=p_exchngsymbol;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  set curcnt=0;
  OPEN cur1;

  read_loop: LOOP
  FETCH cur1 INTO p_open_price,p_close_price,p_high_price,p_low_price,p_adjusted_price,p_businessdate,p_exchangeRate,p_reverseExchangeRate;


  IF done THEN
  LEAVE read_loop;
  END IF;


  SET curcnt=curcnt+1;
  if(p_direction='forward') then
 UPDATE rbsa.tmp_rbsa_daily
 SET
   --  open_price = (p_exchangeRate * (p_open_price)),
    -- close_price = (p_exchangeRate * (p_close_price)),
   --  high_price = (p_exchangeRate * (p_high_price)),
    -- low_price = (p_exchangeRate * (p_low_price)),
    -- adjusted_price = (p_exchangeRate * (p_adjusted_price)),
    converted_adjusted_price=((p_exchangeRate*p_multiplying_factor) * (p_adjusted_price))
     -- exchange_symbol=p_insrtexchngsymbol

 WHERE
     ticker = p_ticker
         AND businessdate = p_businessdate;
         else
 UPDATE rbsa.tmp_rbsa_daily
 SET
   --  open_price = (p_reverseExchangeRate * (p_open_price)),
   --  close_price = (p_reverseExchangeRate * (p_close_price)),
   --  high_price = (p_reverseExchangeRate * (p_high_price)),
  --   low_price = (p_reverseExchangeRate * (p_low_price)),
  --   adjusted_price = (p_reverseExchangeRate * (p_adjusted_price)),
   --  exchange_symbol=p_insrtexchngsymbol

    converted_adjusted_price=((p_reverseExchangeRate*p_multiplying_factor) * (p_adjusted_price))
     -- exchange_symbol=p_insrtexchngsymbol
 WHERE
     ticker = p_ticker
         AND businessdate = p_businessdate;
         end if;

  END LOOP;

  CLOSE cur1;
 SELECT curcnt AS 'UPDATED DATA COUNT';
 -- select currency_exchange_cd into currExcngFactor from invdb.sec_source_mapping where ticker= p_ticker;

 end$$

DELIMITER ;

