DROP PROCEDURE IF EXISTS `sp_user_save_settings`;

DELIMITER $$
CREATE PROCEDURE `sp_user_save_settings`(
	IN p_logonid   BIGINT(20),
	IN p_function  VARCHAR(1),
	IN p_userid	   VARCHAR(60),
	IN p_email	   VARCHAR(60),
	IN p_pwd	   VARCHAR(60),
	IN p_q1		   VARCHAR(60),
	IN p_a1		   VARCHAR(60),
	IN p_q2		   VARCHAR(60),
	IN p_a2		   VARCHAR(60),
	IN p_q3		   VARCHAR(60),
	IN p_a3		   VARCHAR(60)
)
BEGIN
	IF (p_function = 'U')
		THEN 
			update user_logon
				set emailalt = IFNULL(p_email,emailalt)
				  , pwd = IFNULL(p_pwd, pwd)
				  -- , userid = IFNULL(p_userid,userid)
			where logonid = p_logonid;
	END IF;
	IF (p_function = 'Q')
		THEN 
			update user_logon
				set question1 = IFNULL(p_q1,question1)
				  , answer1 = IFNULL(p_a1, answer1)
				  , question2 = IFNULL(p_q2, question2)
				  , answer2 = IFNULL(p_a1, answer1)
				  , question3 = IFNULL(p_q3, question3)
				  , answer3 = IFNULL(p_a3, answer3)
			where logonid = p_logonid;
	END IF;
 
end$$
DELIMITER ;
