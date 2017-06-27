DROP PROCEDURE IF EXISTS `temp`.`sp_upload_sec_fixedmodel_projection`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_sec_fixedmodel_projection`()
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
    SELECT  `theme`, `model`, count(*) as num
    FROM `temp`.`tmp_sec_fixedmodel_projection`
    GROUP BY `theme`, `model`;
    
    
        
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
    
	
	
    IF (toktoProceed)
    THEN
    
		DELETE FROM `invdb`.`sec_fixed_projectionchart`
        WHERE `theme` in (SELECT DISTINCT `theme` from `tmp_theme_list`);
        
		INSERT INTO `invdb`.`sec_fixed_projectionchart`
		(`theme`,
		`model`,
		`year`,
		`5percent`,
		`25percent`,
		`50percent`,
		`75percent`,
		`95percent`)
        SELECT 
			`theme`,
			`model`,
			`year`,
			`5percent`/tbasevalue,
			`25percent`/tbasevalue,
			`50percent`/tbasevalue,
			`75percent`/tbasevalue,
			`95percent`/tbasevalue
		FROM `tmp_sec_fixedmodel_projection`;


    END IF;
    
    
END$$
DELIMITER ;
