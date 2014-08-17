DROP FUNCTION if exists funct_convert_timestamp;

DELIMITER $$
CREATE FUNCTION `funct_convert_timestamp`(
        p_date       TIMESTAMP
) RETURNS varchar(40) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE tFmt VARCHAR(1);
	DECLARE tVal VARCHAR(20);
	BEGIN
		IF (p_date is NULL)
		   THEN RETURN NULL;
		END IF;

		SELECT DATE_FORMAT(p_date,'%m/%d/%Y %T')
		INTO tVal;

		RETURN tVal;
	END;
END$$
DELIMITER ;
