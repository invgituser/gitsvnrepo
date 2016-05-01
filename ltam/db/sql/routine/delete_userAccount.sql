DROP PROCEDURE IF EXISTS `delete_userAccount`;

DELIMITER $$
CREATE PROCEDURE `delete_userAccount`(
	IN p_acctnum BIGINT(20))
BEGIN
	DECLARE numOfAccounts INTEGER;
	DECLARE t_logonid     BIGINT;

	begin
		SELECT count(*)
		INTO numOfAccounts
		FROM user_access_role uar
		WHERE uar.logonid = (select uar2.logonid from user_access_role uar2
							 WHERE uar2.acctnum = p_acctnum
							 AND   uar2.role = 'OWNER');


		if (p_acctnum is not NULL)
			then
			begin
				delete from user_risk_questions
				where acctnum = p_acctnum;

				delete from user_trade_profile
				where acctnum = p_acctnum;

				delete from asset_alloc
				where acctnum = p_acctnum;
				
				delete from virtual_portfolio
				where acctnum = p_acctnum;


				select uar2.logonid
				into t_logonid
				from user_access_role uar2
				WHERE uar2.acctnum = p_acctnum
				AND   uar2.role = 'OWNER';

				delete from user_access_role
				where acctnum = p_acctnum;

				IF (numOfAccounts = 1)
					THEN
						delete from user_logon
						where logonid = t_logonid;
				END IF;

				commit;

			end;
		end if;		
	end;

END$$
DELIMITER ;
