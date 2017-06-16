DROP PROCEDURE IF EXISTS `invdb`.`invite_user`;

DELIMITER $$
CREATE PROCEDURE `invdb`.`invite_user`(
   p_acctnum		BIGINT(20)  -- Optional  (If provided, then user_access_role is created as User
  ,p_userid			VARCHAR(60)
  ,p_email			VARCHAR(60)
  ,p_firstname		VARCHAR(25)
  ,p_lastname		VARCHAR(25)
  ,p_advisor		VARCHAR(20)
  ,p_rep			VARCHAR(20)
)
BEGIN

    DECLARE tAccount		BIGINT(20);
    DECLARE tlogonid		BIGINT(20);
            
	DELETE FROM `invdb`.`user_logon`
	WHERE `user_logon`.`email` = p_email
    AND   `user_logon`.`logonstatus` not in ('A', 'L');
	
	INSERT INTO `invdb`.`user_logon`
		(`userid`, `pwd`, `logonstatus`, `firstname`,`lastname`,  `email`, `advisor`, `rep`, `emailmsgtype`,`access`,`atstart`)
	VALUES
		(p_userid, 'Default', 'T', p_firstname, p_lastname, p_email, p_advisor, p_rep, 'HTML', 'user', 'setting');
		
   select last_insert_id() into tlogonid;

	IF (p_acctnum is not null)
    THEN
		INSERT INTO `invdb`.`user_access_role`
		(`logonid`, `acctnum`, `role`,`privileges`,`created`)
		VALUES
		(tlogonid, p_acctnum, 'USER', 'V', now()) 
		;
    END IF;
    
END$$
DELIMITER ;
