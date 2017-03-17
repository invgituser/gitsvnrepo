DROP FUNCTION IF EXISTS `invdb`.`FUNCT_GET_WEB_SITE_INFO`;

DELIMITER $$
CREATE FUNCTION `invdb`.`FUNCT_GET_WEB_SITE_INFO`(
        vURL		VARCHAR(45),
        vKey		VARCHAR(45)
) RETURNS varchar(220) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE tVal VARCHAR(40);
	BEGIN
		BEGIN
        
			SELECT `value` INTO tVal
			FROM `web_site_info`
			WHERE url = lower(vURL)
            AND   name = UPPER(vKey)
            ;
		END;

		IF (tVal is NOT NULL)
			THEN RETURN tVal;
			ELSE RETURN NULL;
		END IF;
	END;
END$$
DELIMITER ;
