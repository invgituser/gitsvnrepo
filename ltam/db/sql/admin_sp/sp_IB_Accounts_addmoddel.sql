DROP PROCEDURE IF EXISTS `sp_IB_Accounts_addmoddel`;

DELIMITER $$
CREATE PROCEDURE `sp_IB_Accounts_addmoddel`(
	IN p_addmod	   varchar(1),
	IN p_acctnum   bigint,
	IN p_status	   varchar(60),
	IN p_ibacctnum varchar(12)
)
BEGIN
	DECLARE t_lastname, t_firstname VARCHAR(60);
	DECLARE t_dateOpened VARCHAR(12);
	DECLARE t_ibemail   VARCHAR(60);
	DECLARE t_email   VARCHAR(60);
	DECLARE t_found     INT;

	#If this sp was called and account is in Verify mode, then delete the mapping. 
			delete from `IB_Accounts`
		    where `IB_acctnum` = p_ibacctnum
			AND   `accountStatus` = 'Pending'
			;


	IF (p_addmod = 'A' or p_addmod = 'M')
	THEN
	BEGIN
		begin
			SELECT COUNT(*)
			INTO t_found
			FROM IB_Accounts
			WHERE IB_Acctnum = p_ibacctnum
			;
		end;

	IF (t_found = 0)
		THEN
		begin
			begin
				SELECT lastname, firstname, email
				INTO t_lastname, t_firstname, t_email
				FROM pending_user_info
				WHERE acctnum = p_acctnum
				LIMIT 1
				;
			end;

			begin
				SELECT dateOpened, emailPrimary
				INTO t_dateOpened, t_ibemail
				FROM tmp_IB_Accounts
				WHERE clientAccountID = p_ibacctnum
				LIMIT 1
				;
			end;

			begin
				INSERT INTO `IB_Accounts`
				(`acctnum`,
				`IB_acctnum`,
				`email`,
				`lastName`,
				`firstName`,
				`accountStatus`,
				`dateOpened`,
				`dateClosed`,
				`created`)
				VALUES
				(
				p_acctnum,
				p_ibacctnum,
				IFNULL(t_email,t_ibemail),
				t_lastname,
				t_firstname,
				p_status,
				t_dateOpened,
				null,
			    now()
				);
			end;
		  end;
		else
		  begin
			update `IB_Accounts`
				set `acctnum` = p_acctnum
				  , `accountStatus` = p_status
			WHERE `IB_acctnum` = p_ibacctnum;
		  end;
		end if;
	end;
	END IF;
	
END;
$$
