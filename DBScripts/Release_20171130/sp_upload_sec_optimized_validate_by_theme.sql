DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_optimized_validate_by_theme`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_optimized_validate_by_theme`(
	in p_new_theme varchar(50)
    ,in p_crnt_theme varchar(50)
    )
BEGIN

	-- Check for Theme Name consistency (Already in production)
	SELECT DISTINCT 
		concat('Theme is already in production environment') as validate
        , chk. theme, null as assetclass, null as primeassetclass, count(*) as value
	FROM temp.tmp_sec_assetclass_group chk
	WHERE chk.theme=p_new_theme
    AND chk.`status` in ('A')
	AND chk.theme in (select distinct t.theme from invdb.sec_assetclass_group t where t.`status` = 'A')
    GROUP BY
		chk.theme
	HAVING count(*) > 0
	UNION ALL

	-- Is this Theme defined in our asssetclass table 
	SELECT DISTINCT 
		concat('Theme is NOT defined on Asset Class table') as validate
        , chk. theme, null as assetclass, null as primeassetclass, count(*) as value
	FROM temp.tmp_sec_assetclass_group chk
	WHERE chk.theme not in (p_new_theme)
    AND chk.`status` in ('A')
    GROUP BY
		chk.theme
	HAVING count(*) > 0
	UNION ALL

	-- Is this Theme defined in our asssetclass table 
	SELECT DISTINCT 
		concat('Theme is NOT defined Prime Asset Class table') as validate
        , chk. theme, null as assetclass, null as primeassetclass, count(*) as value
	FROM temp.tmp_sec_prime_asset_group chk
	WHERE chk.theme not in (p_new_theme)
    AND chk.`status` in ('A')
    GROUP BY
		chk. theme
	HAVING count(*) = 0
	UNION ALL

	-- Check for Theme Name consistency
	SELECT DISTINCT 
		concat('Asset Class and Prime Asset Class entries for theme do not match!') as validate
        , chk. theme, null as assetclass, null as primeassetclass, count(*) as value
	FROM temp.tmp_sec_assetclass_group chk
	WHERE chk.theme=p_new_theme
    AND chk.`status` in ('A')
	AND chk.theme not in (select distinct t.theme from temp.tmp_sec_prime_asset_group t where t.`status` = 'A')
    GROUP BY
		chk.theme
	HAVING count(*) > 0
	UNION ALL
    
	-- Check for Rever match using Primeasset as primary and Assetclass as secondary
	SELECT DISTINCT 
		concat('Asset Class and Prime Asset Class entries for theme do not match!') as validate
        , chk. theme, null as assetclass, null as primeassetclass, count(*) as value
	FROM temp.tmp_sec_prime_asset_group chk
	WHERE chk.theme=p_new_theme
    AND chk.`status` in ('A')
	AND chk.theme not in (select distinct t.theme from temp.tmp_sec_assetclass_group t where t.`status` = 'A')
    GROUP BY
		chk.theme
	HAVING count(*) > 0
	UNION ALL

	-- Check for Asset Class consistency
   	SELECT 
		concat('Asset Class and Prime Asset Class entries for asset class [', chk.assetclass,' ] do not match!') as validate
        , chk. theme, null as assetclass, null as primeassetclass, count(*) as value
	FROM temp.tmp_sec_assetclass_group chk
	WHERE chk.theme=p_new_theme 
	AND chk.`status` in ('A')
	and concat(chk.theme,chk.assetclass) not in (select concat(t.theme,t.assetclass) from temp.tmp_sec_prime_asset_group t where t.`status` = 'A')
    GROUP BY
		chk. theme
	HAVING count(*) > 0
    UNION ALL
    
	-- Check for New Security in Asset class consistency
   	SELECT 
		concat('Security used in Asset class for optimization [', chk.ticker,' ] not on system!') as validate
        , chk. theme, null as assetclass, null as primeassetclass, count(*) as value
	FROM temp.tmp_sec_assetclass_group chk
	WHERE theme=p_new_theme 
	AND chk.`status` in ('A')
    and chk.ticker not in (select t.ticker from invdb.sec_master t where t.`status` = 'A')
    GROUP BY
		chk.theme
	HAVING count(*) > 0
    UNION ALL
    
	-- Check for New Security in Prime assetclass consistency
   	SELECT 
		concat('Security used in Prime assetclass for optimization [', chk.ticker,' ] not on system!') as validate
        , chk. theme, null as assetclass, null as primeassetclass, count(*) as value
	FROM temp.tmp_sec_prime_asset_group chk
	WHERE theme=p_new_theme 
	AND chk.`status` in ('A')
    and chk.ticker not in (select t.ticker from invdb.sec_master t where t.`status` = 'A')
    GROUP BY
		chk.theme
	HAVING count(*) > 0
    UNION
    
    
	-- Check for Asset Class lowerbound
   	SELECT 
		concat('SUM of ALL Assetclass lower bound [', chk.theme,' ] has to be less then 1!') as validate
        , chk. theme, null as assetclass, null as primeassetclass, sum(lowerbound) as value
	FROM temp.tmp_sec_assetclass_group chk
	WHERE theme=p_new_theme 
	AND chk.`status` in ('A')
    GROUP BY
		chk.theme
	HAVING sum(lowerbound) >= 1
    UNION
    
	-- Check for Asset Class upperbound
   	SELECT 
		concat('SUM of ALL Assetclass upper bound [', chk.theme,' ] has to be greater then 1!') as validate
        , chk. theme, null as assetclass, null as primeassetclass, sum(upperbound) as value
	FROM temp.tmp_sec_assetclass_group chk
	WHERE theme=p_new_theme 
	AND chk.`status` in ('A')
    GROUP BY
		chk.theme
	HAVING sum(upperbound) < 1
    UNION
    
 	-- Check for Prime Asset Class upperbound
   	SELECT 
		concat('For this Prime Asset class SUM of this lower bound [', chk.theme, '->', chk.assetclass, ' ] has to less then 1!') as validate
        , chk. theme, chk.assetclass as assetclass, null as primeassetclass, sum(chk.lowerbound) as value
	FROM temp.tmp_sec_prime_asset_group chk
	WHERE theme=p_new_theme 
	AND chk.`status` in ('A')
    GROUP BY
		chk.theme, chk.assetclass
	HAVING sum(lowerbound) >= 1
    UNION
 	-- Check for Prime Asset Class upperbound
   	SELECT 
		concat('For this Prime Asset class SUM of this  upper bound [', chk.theme, '->', chk.assetclass, ' ] has to greater then 1!') as validate
        , chk. theme, chk.assetclass as assetclass, null as primeassetclass, sum(chk.upperbound) as value
	FROM temp.tmp_sec_prime_asset_group chk
	WHERE theme=p_new_theme 
	AND chk.`status` in ('A')
    GROUP BY
		chk.theme, chk.assetclass
	HAVING sum(upperbound) < 1
    ;
    
    

END$$
DELIMITER ;
