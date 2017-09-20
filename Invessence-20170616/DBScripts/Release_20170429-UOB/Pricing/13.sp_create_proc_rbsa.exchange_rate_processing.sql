## Create Procedure rbsa.exchange_rate_processing

USE `rbsa`;
DROP procedure IF EXISTS `exchange_rate_processing`;

DELIMITER $$
USE `rbsa`$$
CREATE PROCEDURE `exchange_rate_processing`(in p_ticker varchar(20),in p_dest_currency varchar(20))
begin
  declare p_exchngsymbol,p_frstcd,p_scndcd,p_fromCurrency,p_toCurrency varchar(10);
  declare p_exchngavailable int;
  declare p_multiplying_factor double;

  set p_exchngavailable=0;
  select currency_exchange_cd,multiplying_factor into p_exchngsymbol,p_multiplying_factor from invdb.sec_source_mapping where ticker_source_name=p_ticker and dest_currency=p_dest_currency;

  select count(*),fromCurrency,toCurrency into p_exchngavailable,p_fromCurrency,p_toCurrency from invdb.sec_exchangerate_master where symbol=p_exchngsymbol;

  if(p_exchngavailable>0) then
  select p_exchngsymbol,p_exchngavailable,'1',p_toCurrency;
  delete from rbsa.tmp_rbsa_daily where businessdate<(select min(exchangeDate) from rbsa.sec_exchangerate_data sed where sed.symbol=p_exchngsymbol );
  	 	 call rbsa.exchange_rate_processor(p_ticker,'forward',p_exchngsymbol,p_exchngsymbol,p_multiplying_factor);
      else

  	set p_exchngavailable=0;
      SELECT SUBSTRING(p_exchngsymbol,1,3) into p_frstcd;
      SELECT SUBSTRING(p_exchngsymbol,4,6) into p_scndcd;
      set p_exchngsymbol=concat(p_scndcd,p_frstcd);
  	select count(*) into p_exchngavailable from invdb.sec_exchangerate_master where symbol=p_exchngsymbol;

  		if(p_exchngavailable>0) then
             set p_fromCurrency=p_scndcd;
  			select '2',p_exchngavailable,p_exchngsymbol,p_fromCurrency;
             delete from rbsa.tmp_rbsa_daily where businessdate<(select min(exchangeDate) from rbsa.sec_exchangerate_data sed where sed.symbol=p_exchngsymbol );
  	  	 call rbsa.exchange_rate_processor(p_ticker,'backward',p_exchngsymbol,p_exchngsymbol,p_multiplying_factor);
  		end if;
  end if;

  end$$

DELIMITER ;



