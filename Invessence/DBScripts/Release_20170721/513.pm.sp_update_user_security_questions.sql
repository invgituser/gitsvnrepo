DROP PROCEDURE IF EXISTS `sp_update_user_security_questions`;


DELIMITER $$
CREATE PROCEDURE `sp_update_user_security_questions`(
	IN p_logonid	BIGINT,
	IN p_q1		VARCHAR(60),
	IN p_a1		VARCHAR(60),
	IN p_q2		VARCHAR(60),
	IN p_a2		VARCHAR(60),
	IN p_q3		VARCHAR(60),
	IN p_a3		VARCHAR(60),
    IN p_logonstatus VARCHAR(1)
)
BEGIN

		update user_logon
			set question1 = IFNULL(p_q1,question1),
				answer1 = IFNULL(p_a1,answer1),
				question2 = IFNULL(p_q2,question2),
				answer2 = IFNULL(p_a2,answer2),
				question3 = IFNULL(p_q3,question3),
				answer3 = IFNULL(p_a3,answer3),
                logonstatus = IFNULL(p_logonstatus,logonstatus),
				lastupdated = now()
		where logonid = p_logonid;

END$$
DELIMITER ;
