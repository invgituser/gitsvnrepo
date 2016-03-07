
DROP FUNCTION IF EXISTS `funct_get_switch`;
delimiter $$

CREATE FUNCTION `funct_get_switch`(
        vKey       VARCHAR(20)
) RETURNS varchar(40) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE tFmt VARCHAR(1);
	DECLARE tVal VARCHAR(40);
	BEGIN
		IF (vKey is NULL)
		   THEN RETURN NULL;
		END IF;

		BEGIN
			SELECT format, `value` INTO tFmt, tVal
			FROM `invessence_switch`
			WHERE UPPER(name) = UPPER(vKey);
		END;

		IF (tVal is NOT NULL)
			THEN RETURN tVal;
			ELSE RETURN NULL;
		END IF;
		RETURN NULL;
	END;
END

$$
delimiter ;

