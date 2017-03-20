DROP FUNCTION IF EXISTS `invdb`.`FUNCT_WEB_INFO_BY_ADVISOR`;

DELIMITER $$
CREATE FUNCTION `invdb`.`FUNCT_WEB_INFO_BY_ADVISOR`(
        p_advisor	VARCHAR(20),
        vKey		VARCHAR(30)
) RETURNS varchar(60) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE tVal VARCHAR(60);
    DECLARE tURL VARCHAR(60);
    
	BEGIN
		IF (p_advisor is NULL)
		   THEN RETURN NULL;
 		END IF;

		BEGIN
			SELECT `value` 
            INTO tVal
			FROM `web_advisor_mapping`
			WHERE `advisor` = UPPER(p_advisor)
			AND   `name` = UPPER(vKey);
		END;

		IF (tVal is NOT NULL)
			THEN RETURN tVal;
		END IF;
        
        BEGIN
 			SELECT `value` 
            INTO tURL
			FROM `web_advisor_mapping`
			WHERE `advisor` = UPPER(p_advisor)
			AND   `name` = UPPER('WEB.URL');
            
			SELECT `value` 
            INTO tVal
			FROM `web_site_info`
			WHERE `url` = tURL
			AND   `name` = UPPER(vKey);
       
        END;
        
		IF (tVal is NOT NULL)
			THEN RETURN tVal;
		END IF;
 
		RETURN NULL;
	END;
END$$
DELIMITER ;
