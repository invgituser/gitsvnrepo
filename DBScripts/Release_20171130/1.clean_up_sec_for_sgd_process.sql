INSERT INTO `invdb`.`sec_source_mapping`
(`sec_ticker`,`ticker_source_name`,`tickersource`,`pricing_required`,`exchange_required`,`base_currency`,`dest_currency`,`currency_exchange_cd`,`multiplying_factor`,`return_threshold`)
select ticker, ticker, 'FIS', 'Y', 'N', 'USD', 'SGD', 'USDSGD',1, '0.5'
from sec_master
where ticker in (
'EWS.N'); 

update invdb.sec_master
set sec_master.base_currency = (select distinct base_currency from invdb.sec_source_mapping where sec_master.ticker = sec_source_mapping.sec_ticker)
;

update invdb.sec_master
set base_currency = 'USD'
where base_currency is null;

update invdb.sec_master
set ric = ticker
;

update invdb.sec_master
set exchange = 'NYSE'
where ticker like '%.N';

update invdb.sec_master
set exchange = 'LSE'
where ticker like '%.L';

update invdb.sec_master
set exchange = 'SGX'
where ticker like '%.SI';

UPDATE `invdb`.`sec_master` 
SET `exchange`='MSTA' 
WHERE ticker like '%.MSTA';

update invdb.sec_assetclass_group
set ticker = 'EWS.N'
where ticker in ('ES3.SI')
and theme like '%SGWealthSGD';

update invdb.sec_prime_asset_group
set ticker = 'EWS.N'
where ticker in ('ES3.SI')
and theme like '%SGWealthSGD';

update invdb.sec_rbsa
set ticker = 'EWS.N'
where ticker in ('ES3.SI')
and theme like '%SGWealthSGD';

update invdb.sec_rbsa
set primeasset_ticker = 'EWS.N'
where primeasset_ticker in ('ES3.SI')
and theme like '%SGWealthSGD';

update invdb.sec_asset_mapping
set ticker = 'EWS.N'
where ticker in ('ES3.SI')
and theme like '%SGWealthSGD';

update invdb.sec_asset_mapping
set sec_type = 'EQ'
where assetName like '%Equi%'; 

update invdb.sec_asset_mapping
set sec_type = 'FX'
where assetName like '%Fix%' or assetName like '%Bond%' or assetName like '%Comm%' or assetName like 'Cash' ; 






