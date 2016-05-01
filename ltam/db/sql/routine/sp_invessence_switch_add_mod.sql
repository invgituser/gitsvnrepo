delimiter $$

DROP PROCEDURE `sp_invessence_switch_add_mod`
$$

CREATE PROCEDURE `sp_invessence_switch_add_mod`(
	IN p_addmodflag   VARCHAR(1),
	IN p_name varchar(20),
	IN p_description varchar(60),
	IN p_value varchar(20),
	IN p_format varchar(10)
)
BEGIN
   DECLARE t_count INTEGER;

   SELECT COUNT(*)
   INTO t_count
   FROM invessence_switch
   WHERE name = p_name;

   IF (t_count > 0) 
	THEN SELECT 'M' into p_addmodflag;
   END IF;

   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
   BEGIN
    INSERT INTO invessence_switch
         (
		name,
		description,
		value,
		format,
		created,
		lastupdated
         )
    VALUES 
         ( 
		p_name,
		p_description,
		p_value,
		p_format,
		now(),
		NULL
         ) ; 
   END;
   ELSE
   BEGIN
     UPDATE  invessence_switch
     SET 
		description = p_description,
		value = p_value,
		format = p_format,
		lastupdated = now()
     WHERE
		name = p_name;
   END;
   END IF;
END$$

