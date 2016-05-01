-- Step#1
Update sec_master
set assetclass = 'Equities'
where assetclass = 'Equity';

Update sec_master
set assetclass = 'Bonds'
where assetclass = 'Bond';

Update sec_master
set assetclass = 'Commodities'
where assetclass = 'Commodity';

-- Step #2
update asset_mapping
set assetclass = 'Equities'
where assetclass = 'Equity';

Update asset_mapping
set assetclass = 'Bonds'
where assetclass = 'Bond';

Update asset_mapping
set assetclass = 'Commodities'
where assetclass = 'Commodity';
