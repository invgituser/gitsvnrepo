DROP FUNCTION IF EXISTS `get_business_date`;

DELIMITER $$
CREATE FUNCTION `get_business_date`
(
        p_date       DATE,
		p_num_days           int
) RETURNS DATE
    DETERMINISTIC
BEGIN
	DECLARE tDate DATE;
	DECLARE counter INT Default 0;
	DECLARE days INT;
	DECLARE nextDay Boolean;

	SET days = ABS(p_num_days);
	IF (p_num_days > 0)
		then
			set nextDay = true;
		else
			set nextDay = false;
	END IF;

	set tDate = p_date;

	simple_loop: LOOP
         SET counter=counter+1;
			IF (nextDay)
				then
					set tDate = CASE DAYOFWEEK(tDate)
									WHEN 1 THEN tDate + INTERVAL 1 DAY
									WHEN 7 THEN tDate + INTERVAL 2 DAY
									WHEN 6 THEN tDate + INTERVAL 3 DAY
									ELSE tDate + INTERVAL 1 DAY
								END;
				else
					SET tDate = CASE DAYOFWEEK(tDate)
									WHEN 1 THEN tDate - INTERVAL 2 DAY
									WHEN 7 THEN tDate - INTERVAL 1 DAY
									WHEN 2 THEN tDate - INTERVAL 3 DAY
									ELSE tDate - INTERVAL 1 DAY
								END;
			END IF;

         IF counter=days THEN
            LEAVE simple_loop;
         END IF;
   END LOOP simple_loop;
   RETURN tDate;
END$$
DELIMITER ;