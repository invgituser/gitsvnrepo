DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_fixedmodel_perfomancedata`;
DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_fixedmodel_perfomance`;
DROP PROCEDURE IF EXISTS `invdb`.`sp_upload_sec_fixedmodel_perfomance`;


DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_fixedmodel_perfomance`()
BEGIN

	DECLARE tnumofdata	INTEGER;
	DECLARE tnumoflevels	INTEGER;
    DECLARE toktoProceed	BOOLEAN;
    DECLARE tbasevalue INTEGER;
    
    set  tbasevalue = 1;
    
	DROP TABLE IF EXISTS tmp_theme_list;
	CREATE TEMPORARY TABLE tmp_theme_list(
		`theme`  varchar(20),
        `level` varchar(20),
        `numofdata` INTEGER
	);
    
    INSERT INTO tmp_theme_list
		(`theme`, `level`, `numofdata`)
    SELECT  `theme`, `level`, count(*) as num
    FROM `tmp_sec_fixedmodel_performance`
    GROUP BY `theme`, `level`;
    
    
        
    SELECT MIN(numofdata)
    INTO tnumofdata
    FROM tmp_theme_list;
    
    set toktoProceed = true;
    IF (IFNULL(tnumofdata,0) < 35)
		THEN	set toktoProceed = false;
				SELECT `theme`, `level`, `numofdata`,
                CASE WHEN (`numofdata` < 35) THEN 'Cannot proceed: Not sufficient data in these model'
					 ELSE 'OK To load'
                END as msg
                FROM tmp_theme_list;
	END IF;
    
	SELECT count(*)
	INTO tnumofdata
	FROM tmp_theme_list;
	
	SELECT count(*)
	INTO tnumoflevels
	FROM `tmp_theme_list`, `invdb`.`sec_fixedmodel`
	WHERE `tmp_theme_list`.`theme` = `sec_fixedmodel`.`theme`
    AND   `tmp_theme_list`.`level` = `sec_fixedmodel`.`level`;
	
	IF (tnumofdata <> tnumoflevels)
	THEN
		set toktoProceed = false;
		SELECT `theme`, `leve;`, 'Model not found' as msg
		FROM `invdb`.`sec_fixedmodel`
		WHERE CONCAT(`sec_fixedmodel`.`theme`,`sec_fixedmodel`.`level`) not in (SELECT CONCAT(`theme`,`level`) from `tmp_theme_list`)
		;
	END IF;
    
    IF (toktoProceed)
    THEN
    
		DELETE FROM `invdb`.`sec_fixed_performancechart`
        WHERE `theme` in (SELECT DISTINCT `theme` from `tmp_theme_list`);
        
		INSERT INTO `invdb`.`sec_fixed_performancechart`
		(`theme`,
		`level`,
		`year`,
		`5percent`,
		`25percent`,
		`50percent`,
		`75percent`,
		`95percent`)
        SELECT 
			`theme`,
			`level`,
			`year`,
			`5percent`/tbasevalue,
			`25percent`/tbasevalue,
			`50percent`/tbasevalue,
			`75percent`/tbasevalue,
			`95percent`/tbasevalue
		FROM `tmp_sec_fixedmodel_performance`;


    END IF;
    
    
END$$
DELIMITER ;
