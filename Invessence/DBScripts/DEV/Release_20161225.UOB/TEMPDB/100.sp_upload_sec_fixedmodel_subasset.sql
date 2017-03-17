DROP PROCEDURE IF EXISTS `invdb`.`sp_upload_sec_fixedmodel_subasset`;
DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_fixedmodel_subasset`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_fixedmodel_subasset`()
BEGIN

	DECLARE tLevel VARCHAR(10);
    
	DELETE FROM `invdb`.`sec_fixedmodel_subasset`
    WHERE concat(theme,level,asset,keyname) in 
    (select concat(tmp.theme,tmp.level,tmp.asset,tmp.keyname) from `temp`.`tmp_sec_fixedmodel_subasset` `tmp`);
    
	UPDATE `invdb`.`sec_fixedmodel_subasset`
		set status = 'I', updated = now()
	WHERE `sec_fixedmodel_subasset`.`status` = 'A'
    AND   `sec_fixedmodel_subasset`.`theme` in (select tmp.theme from `temp`.`tmp_sec_fixedmodel_subasset` `tmp`);

	INSERT INTO `invdb`.`sec_fixedmodel_subasset`
	(
		`theme`,
		`level`,
		`asset`,
		`keyname`,
		`keydescription`,
		`status`,
		`allocation`,
		`sortorder`,
		`created`,
		`updated`)
	SELECT 
		`theme`,
		`level`,
		`asset`,
		`keyname`,
		`keydescription`,
		`status`,
		`allocation`,
		`sortorder`,
		now(),
		now()
	FROM `temp`.`tmp_sec_fixedmodel_subasset`;
	
	DROP TEMPORARY TABLE IF EXISTS `tmp_asset_allocation`;
    CREATE TEMPORARY TABLE `tmp_asset_allocation`
    ( `theme`	varchar(20),
      `level`   varchar(20),
      `asset`   varchar(30),
      `allocation` double
	);
    
    INSERT INTO `tmp_asset_allocation`
    ( `theme`,
      `level`,
      `asset`,
      `allocation`
	)
    SELECT 
		`theme`,
		`level`,
		`asset`,
        sum(`allocation`) as `allocation`
	FROM `temp`.`tmp_sec_fixedmodel_subasset`
    GROUP BY 
		`theme`,
		`level`,
		`asset`
	;
    
    UPDATE `invdb`.`sec_fixedmodel_asset`
		set `allocation` = 0;
	
    UPDATE `invdb`.`sec_fixedmodel_asset`, `tmp_asset_allocation` `tmp`
		set `sec_fixedmodel_asset`.`allocation` = `tmp`.`allocation` 
	WHERE `sec_fixedmodel_asset`.`theme` = `tmp`.`theme`
	and   `sec_fixedmodel_asset`.`level` = `tmp`.`level`
    and   `sec_fixedmodel_asset`.`asset` = `tmp`.`asset`
	;
    
    DELETE FROM `invdb`.`sec_theme`
    WHERE `theme` in (select distinct `tmp`.`theme` from `temp`.`tmp_sec_fixedmodel_subasset` `tmp`);
    
	INSERT INTO `invdb`.`sec_theme`
	(`theme`,
	`ticker`,
	`status`,
	`created`,
	`lastUpdated`)
	SELECT DISTINCT 
     `tmp`.`theme`,
     `tmp`.`keyname`,
	 'A',
     now(),
     null
	FROM `temp`.`tmp_sec_fixedmodel_subasset` `tmp`
    WHERE `tmp`.`status` = 'A'
    ORDER BY 1,2
    ;
    
     BEGIN
		DELETE FROM `invdb`.sec_subasset_master
		where theme in (SELECT distinct theme from `temp`.`tmp_sec_fixedmodel_subasset`);
        
        SELECT MIN(level)
        into tLevel
        FROM `temp`.`tmp_sec_fixedmodel_subasset`;
        
        INSERT INTO `invdb`.`sec_subasset_master`
			(`theme`,`subassetclass`,`status`,`subassetName`,`subassetcolor`,`sortorder`,`created`,`lastUpdated`)
		select distinct `tmp`.`theme`, `tmp`.`keyname`, 'A', IFNULL(`tmp`.`keydescription`,`tmp`.`keyname`), 
        IFNULL(`tasset`.`color`,'#f5f5f5'), `tmp`.`sortorder`, now(), null
		from `temp`.`tmp_sec_fixedmodel_subasset` `tmp`,
			 `temp`.`tmp_sec_fixedmodel_asset` `tasset`
		where `tmp`.`level` = tLevel
        AND   `tmp`.`theme` = `tasset`.`theme`
        AND   `tmp`.`asset` = `tasset`.`asset`
        AND   `tmp`.`level` = `tasset`.`level`
        ;
	END;
   
 
END$$
DELIMITER ;
