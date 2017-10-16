update invdb.sec_master
set ticker = concat(ticker,'.N')
where ticker in (
'IEF','IAU','SPY','JNK','VEA','VNQ','TLT','SHY','MDY','PFF','IWM','EMLC','VCLT','VWO','MLN','MUB','SHM','IVW','IVE','VTV','EFA','ITM'
,'CWB','EEM','EMB','HYG','IJR','IVV','IWB','IWR','IYR','LQD','LTPZ','STPZ','SUB','TIPZ','VEU','VGK','VOO','SCZ','VCIT','VCSH'
,'VSS','VTI','VUG','VYM','IDA12','VB','EWS','IEUR','EPP','EWJ','IEMG','AGG','IWS','SCHB','SCHF','VBR');

update invdb.sec_assetclass_group
set ticker = concat(ticker,'.N')
where ticker in (
'IEF','IAU','SPY','JNK','VEA','VNQ','TLT','SHY','MDY','PFF','IWM','EMLC','VCLT','VWO','MLN','MUB','SHM','IVW','IVE','VTV','EFA','ITM'
,'CWB','EEM','EMB','HYG','IJR','IVV','IWB','IWR','IYR','LQD','LTPZ','STPZ','SUB','TIPZ','VEU','VGK','VOO','SCZ','VCIT','VCSH'
,'VSS','VTI','VUG','VYM','IDA12','VB','EWS','IEUR','EPP','EWJ','IEMG','AGG','IWS','SCHB','SCHF','VBR');


update invdb.sec_prime_asset_group
set ticker = concat(ticker,'.N')
where ticker in (
'IEF','IAU','SPY','JNK','VEA','VNQ','TLT','SHY','MDY','PFF','IWM','EMLC','VCLT','VWO','MLN','MUB','SHM','IVW','IVE','VTV','EFA','ITM'
,'CWB','EEM','EMB','HYG','IJR','IVV','IWB','IWR','IYR','LQD','LTPZ','STPZ','SUB','TIPZ','VEU','VGK','VOO','SCZ','VCIT','VCSH'
,'VSS','VTI','VUG','VYM','IDA12','VB','EWS','IEUR','EPP','EWJ','IEMG','AGG','IWS','SCHB','SCHF','VBR');


update invdb.sec_prime_asset_group
set ticker = concat(ticker,'.N')
where ticker in (
'IEF','IAU','SPY','JNK','VEA','VNQ','TLT','SHY','MDY','PFF','IWM','EMLC','VCLT','VWO','MLN','MUB','SHM','IVW','IVE','VTV','EFA','ITM'
,'CWB','EEM','EMB','HYG','IJR','IVV','IWB','IWR','IYR','LQD','LTPZ','STPZ','SUB','TIPZ','VEU','VGK','VOO','SCZ','VCIT','VCSH'
,'VSS','VTI','VUG','VYM','IDA12','VB','EWS','IEUR','EPP','EWJ','IEMG','AGG','IWS','SCHB','SCHF','VBR');


update invdb.sec_rbsa
set ticker = concat(ticker,'.N')
where ticker in (
'IEF','IAU','SPY','JNK','VEA','VNQ','TLT','SHY','MDY','PFF','IWM','EMLC','VCLT','VWO','MLN','MUB','SHM','IVW','IVE','VTV','EFA','ITM'
,'CWB','EEM','EMB','HYG','IJR','IVV','IWB','IWR','IYR','LQD','LTPZ','STPZ','SUB','TIPZ','VEU','VGK','VOO','SCZ','VCIT','VCSH'
,'VSS','VTI','VUG','VYM','IDA12','VB','EWS','IEUR','EPP','EWJ','IEMG','AGG','IWS','SCHB','SCHF','VBR');


update invdb.sec_rbsa
set primeasset_ticker = concat(primeasset_ticker,'.N')
where primeasset_ticker in (
'IEF','IAU','SPY','JNK','VEA','VNQ','TLT','SHY','MDY','PFF','IWM','EMLC','VCLT','VWO','MLN','MUB','SHM','IVW','IVE','VTV','EFA','ITM'
,'CWB','EEM','EMB','HYG','IJR','IVV','IWB','IWR','IYR','LQD','LTPZ','STPZ','SUB','TIPZ','VEU','VGK','VOO','SCZ','VCIT','VCSH'
,'VSS','VTI','VUG','VYM','IDA12','VB','EWS','IEUR','EPP','EWJ','IEMG','AGG','IWS','SCHB','SCHF','VBR');


update invdb.sec_asset_mapping
set ticker = concat(ticker,'.N')
where ticker in (
'IEF','IAU','SPY','JNK','VEA','VNQ','TLT','SHY','MDY','PFF','IWM','EMLC','VCLT','VWO','MLN','MUB','SHM','IVW','IVE','VTV','EFA','ITM'
,'CWB','EEM','EMB','HYG','IJR','IVV','IWB','IWR','IYR','LQD','LTPZ','STPZ','SUB','TIPZ','VEU','VGK','VOO','SCZ','VCIT','VCSH'
,'VSS','VTI','VUG','VYM','IDA12','VB','EWS','IEUR','EPP','EWJ','IEMG','AGG','IWS','SCHB','SCHF','VBR')
and (theme not like '%BB'
or theme not like '%TA');

INSERT INTO `invdb`.`sec_source_mapping`
(`sec_ticker`,`ticker_source_name`,`tickersource`,`pricing_required`,`exchange_required`,`base_currency`,`dest_currency`,`currency_exchange_cd`,`multiplying_factor`,`return_threshold`)
select ticker, ticker, 'FIS', 'Y', 'N', 'USD', 'USD', '',1, '0.5'
from sec_master
where ticker in (
'IEF','IAU','SPY','JNK','VEA','VNQ','TLT','SHY','MDY','PFF','IWM','EMLC','VCLT','VWO','MLN','MUB','SHM','IVW','IVE','VTV','EFA','ITM'
,'CWB','EEM','EMB','HYG','IJR','IVV','IWB','IWR','IYR','LQD','LTPZ','STPZ','SUB','TIPZ','VEU','VGK','VOO','SCZ','VCIT','VCSH'
,'VSS','VTI','VUG','VYM','IDA12','VB','EWS','IEUR','EPP','EWJ','IEMG','AGG','IWS','SCHB','SCHF','VBR');


-- Delete OLD BIL and GLD as new is already created with BIL.N' and GLD.N ---

delete from invdb.sec_master
where ticker in ('BIL','GLD');

update invdb.sec_assetclass_group
set ticker = concat(ticker,'.N')
where ticker in ('BIL','GLD');

update invdb.sec_prime_asset_group
set ticker = concat(ticker,'.N')
where ticker in ('BIL','GLD');

update invdb.sec_prime_asset_group
set ticker = concat(ticker,'.N')
where ticker in ('BIL','GLD');

update invdb.sec_rbsa
set ticker = concat(ticker,'.N')
where ticker in ('BIL','GLD');

update invdb.sec_rbsa
set primeasset_ticker = concat(primeasset_ticker,'.N')
where primeasset_ticker in ('BIL','GLD');

delete from invdb.sec_asset_mapping
where ticker in ('BIL','GLD')
and (theme not like '%BB'
or theme not like '%TA');

INSERT INTO `invdb`.`sec_source_mapping`
(`sec_ticker`,`ticker_source_name`,`tickersource`,`pricing_required`,`exchange_required`,`base_currency`,`dest_currency`,`currency_exchange_cd`,`multiplying_factor`,`return_threshold`)
select ticker, ticker, 'FIS', 'Y', 'N', 'USD', 'USD', '',1, '0.5'
from sec_master
where ticker in (
'BIL.N','GLD.N'); 

delete from `invdb`.`sec_master`
where ticker like '%SGD';

update `invdb`.`sec_source_mapping`
set multiplying_factor = 1 
where multiplying_factor = 0


