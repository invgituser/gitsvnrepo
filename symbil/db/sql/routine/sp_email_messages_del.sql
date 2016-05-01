delimiter $$
DROP PROCEDURE `sp_email_messages_del`
$$

CREATE PROCEDURE `sp_email_messages_del`(
	IN p_messageid bigint(20)
)
BEGIN 

     DELETE  FROM email_messages
     WHERE
           messageid = p_messageid;
END
$$

