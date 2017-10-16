create table `temp`.`tmp_sec_assetclass_group`
as 
SELECT * FROM invdb.sec_assetclass_group
where theme = '0.SGWealthSGD';

create table `temp`.`tmp_sec_prime_asset_group`
as 
SELECT * FROM invdb.sec_prime_asset_group
where theme = '0.SGWealthSGD';

select * from `temp`.`tmp_sec_assetclass_group`;
select * from `temp`.`tmp_sec_prime_asset_group`;

update `temp`.`tmp_sec_assetclass_group`
set theme = '0.PRASH'

call `temp`.`sp_upload_sec_optimized_validate_by_theme`('0.Prash', '0.SGWealthSGD');

select * from invdb.sec_assetclass_group;
select * from invdb.sec_rbsa;


   	SELECT 
        chk. theme, chk.assetclass as assetclass, null as primeassetclass, sum(chk.upperbound) as upper, sum(chk.lowerbound) as lower
	FROM temp.tmp_sec_prime_asset_group chk
	WHERE theme='0.PRASH' 
	AND chk.`status` in ('A')
    GROUP BY
		chk.theme, chk.assetclass


update `temp`.`tmp_sec_assetclass_group`
set lowerbound=1
where theme = '0.PRASH' and ticker = 'BIL.N'

update `temp`.`tmp_sec_assetclass_group`
set upperBound=.01
where theme = '0.PRASH' and ticker = 'IUAG.L'

update `temp`.`tmp_sec_prime_asset_group`
set status = 'A'
where theme = '0.PRASH'

update `temp`.`tmp_sec_prime_asset_group`
set lowerBound = 1
where theme = '0.PRASH' and ticker = 'IMEU.L'


update `temp`.`tmp_sec_prime_asset_group`
set upperbound = 0.014
where theme = '0.PRASH' and ticker = 'F0HKG062UI.MSTA'
