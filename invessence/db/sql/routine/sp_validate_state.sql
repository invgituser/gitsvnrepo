DROP PROCEDURE if exists `sp_validate_state`;

DELIMITER $$
CREATE PROCEDURE `sp_validate_state` (
	IN p_logonid   BIGINT(20),
	IN p_state     VARCHAR(2)
)
BEGIN
	DECLARE t_state  varchar(2);
	DECLARE licensed varchar(2);
    DECLARE maxAllowed INTEGER;
	DECLARE currentCount Integer;

	IF (p_logonid is not null)
		then
			SELECT state
			INTO t_state
			FROM user_logon
			WHERE logonid = p_logonid;
		else
			SET t_state = p_state;
	END IF;

	IF (t_state is not null)
	then
		SET licensed = null;

		SELECT 
			`state_mapping`.`licensed`,
			`state_mapping`.`max_allowed`
		INTO licensed, maxAllowed
		FROM `state_mapping`
		WHERE `state_mapping`.`abbreviation` = UPPER(t_state);


		IF (licensed is null)
			then
				set licensed = 'N';
				set maxAllowed = 5;
		END IF;

		if (licensed = 'Y')
		  then
			SELECT 'licensed' as license;
		end if;

		if (licensed = 'N')
			then
				SELECT count(*)
				INTO currentCount
				FROM user_logon ulog
				WHERE ulog.state = t_state;

				if (IFNULL(currentCount,0) >= maxAllowed)
					then
						SELECT 'Quota' as license;
					else
						SELECT 'Open' as license;
				end if;
		end if;
	else -- If no state was defined...
		SELECT 'Quota' as license;
	end if;
end
$$