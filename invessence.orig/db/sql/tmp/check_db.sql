DROP PROCEDURE IF EXISTS `validate_db`;

DELIMITER $$
CREATE PROCEDURE `validate_db`(
    in db    varchar(60)
)
begin
	declare counter	integer;

	DROP TABLE IF EXISTS dbStatus;

	CREATE TEMPORARY TABLE dbStatus (
		`comment` VARCHAR(100),
		`obj_name` VARCHAR(80)
	);

    -- Check tables
	begin
		INSERT INTO dbStatus
			( `comment`, `obj_name` )
		SELECT CONCAT('Missing ', obj_type),
				obj_name
		FROM `invessence_db_info`
		WHERE obj_type in ( 'BASE TABLE', 'VIEWS')
		AND obj_name not in (	SELECT table_name
								FROM INFORMATION_SCHEMA.TABLES
								WHERE TABLE_SCHEMA = db);
	end;

	-- Check routines
	begin
		INSERT INTO dbStatus
			( `comment`, `obj_name` )
		SELECT CONCAT('Missing ', obj_type),
			   obj_name
		FROM `invessence_db_info`
		WHERE obj_type IN ('PROCEDURE', 'FUNCTION')
		AND obj_name not in (	SELECT ROUTINE_NAME
								FROM INFORMATION_SCHEMA.ROUTINES
							WHERE ROUTINE_SCHEMA = db);
	end;

	begin
		SELECT count(*)
		INTO counter
		FROM invessence_switch
		WHERE name in (  'BROKER_BDATE'
						,'BUSINESS_DATE'
						,'PREVIOUS_MONTH'
						,'PRICE_DATE');
		
		IF (counter < 4)
			THEN
				INSERT INTO dbStatus
					( `comment`, `obj_name` )
				VALUES 
					('Missing one of the Key dates', null);
		END IF;		
	end;

	begin
		SELECT count(*)
		INTO counter
		FROM vw_list_of_securities2;

		IF (counter = 0)
			THEN
				INSERT INTO dbStatus
					( `comment`, `obj_name` )
				VALUES 
					('vw_list_of_securities2 return zero rows', null);
		END IF;
	end;

	begin
		SELECT count(*)
		INTO counter
		FROM vw_list_of_securities2
		WHERE price = 0.00;

		IF (counter > 0)
			THEN
				INSERT INTO dbStatus
					( `comment`, `obj_name` )
				VALUES 
					('vw_list_of_securities2 return invalid prices', null);
		END IF;
	end;

	begin
		SELECT * from dbStatus;
	end;
end$$
DELIMITER ;
