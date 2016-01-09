DROP PROCEDURE IF EXISTS `sel_AccountProfile`;

DELIMITER $$
CREATE PROCEDURE `sel_AccountProfile`(
	IN p_logonid BIGINT,
	IN p_acctnum BIGINT
)
BEGIN

	DECLARE filterType VARCHAR(20);
	DECLARE tAdvisorType VARCHAR(25);
	DECLARE tData      INTEGER;

	CASE WHEN (p_logonid is null and p_acctnum is null)
			THEN set filterType = 'DoNothing';
		 WHEN (p_logonid is not null and p_acctnum = 0)
			THEN set filterType = 'New';
		 WHEN (p_logonid is not null and p_acctnum > 0)
			THEN set filterType = 'Load';
		 ELSE set filterType = 'DoNothing';
	END CASE;
		
	IF (filterType = 'New')
		THEN
			select 
			user_logon.logonid,
			user_logon.email,
			user_logon.userid,
			user_logon.lastname,
			user_logon.firstname,
			user_logon.state
			from
				user_logon
			where
				user_logon.logonid = p_logonid
			;
		ELSE
			IF (filterType = 'Load')
				THEN
					SELECT COUNT(*)
					INTO tData
					FROM  user_access_role, advisor_info
					WHERE user_access_role.acctnum = p_acctnum
					AND   user_access_role.logonid = advisor_info.logonid
					AND   advisor_info.groupname = (select groupname from advisor_info where advisor_info.logonid = p_logonid)
					;

					IF (tData > 0)
						THEN
							CALL sel_ClientProfileData2(p_logonid,p_acctnum);
					END IF;
			END IF;
	END IF;

END$$
DELIMITER ;
