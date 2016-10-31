
DROP PROCEDURE IF EXISTS `sp_user_access_add_mod`;
delimiter $$

CREATE PROCEDURE `sp_user_access_add_mod`(
        IN  p_addmodflag      VARCHAR(1),
		IN  p_loginid varchar(60),
		IN  p_acctnum bigint(20),
		IN  p_functionid tinyint(4),
        IN  p_role  varchar(45),
		IN  p_privileges varchar(2)
)
BEGIN 
 
   IF (p_addmodflag = 'A' or p_addmodflag is NULL) THEN
   BEGIN
    INSERT INTO user_access_role
         (logonid, 
          acctnum,
          functionid, 
		  role,
		  privileges,
          created,
          lastupdated
         )
    VALUES 
      ( 
		p_loginid,
        p_acctnum,
        IFNULL(p_functionid,0),
        IFNULL(p_role,'OWNER'),
		IFNULL(p_privileges,'F'),
        now(),
        NULL         
      ) ; 
   END;
   ELSE
   BEGIN
     UPDATE  user_access_role
     SET 
	    functionid = IFNULL(p_functionid,0),
		role = IFNULL(p_role,'OWNER'),
		privileges = IFNULL(p_privileges,'F'),
	    lastupdated = now()
     WHERE
	    logonid = p_loginid and
		acctnum = p_acctnum;
   END;
   END IF;
END
$$
delimiter ;

