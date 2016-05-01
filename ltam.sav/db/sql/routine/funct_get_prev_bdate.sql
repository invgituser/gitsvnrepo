DELIMITER $$

DROP FUNCTION `get_prev_bdate` $$

CREATE FUNCTION `get_prev_bdate`(
        p_current_date       DATE
		p_prev_days          int
) RETURNS DATE
    DETERMINISTIC
BEGIN
	DECLARE tDate DATE;
	BEGIN
		SET tDate = IF(DAYOFWEEK(p_current_date)=1
				, p_current_date - INTERVAL 2 DAY
				, IF(DAYOFWEEK(p_current_date)=2
						, p_current_date - INTERVAL 3 DAY
						, p_current_date - INTERVAL 1 DAY
						)
				);

		RETURN tDate;
	END;
END$$
DELIMITER ;
