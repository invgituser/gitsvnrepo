DROP PROCEDURE IF EXISTS `sp_add_IB_Accounts`;

DELIMITER $$
CREATE PROCEDURE `sp_add_IB_Accounts`(
	v_clientAccountID VARCHAR(80)
  , v_name VARCHAR(80)
  , v_dateOpened VARCHAR(80)
  , v_dateClosed VARCHAR(80)
)
BEGIN
  DECLARE v_acctnum BIGINT(20);
  DECLARE v_firstname, v_lastname, v_email varchar(60);
  DECLARE add_done INT DEFAULT FALSE;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET add_done = TRUE;

    IF (v_Name is not null) 
		then
			begin
				SELECT acctnum, firstname, lastname, email
				INTO v_acctnum, v_firstname, v_lastname, v_email			
				FROM vw_pending_user_info
				WHERE name = v_name
				LIMIT 1
				;

			    IF (v_acctnum is not null)
					then
					begin
						# Ok, we found the match, lets add it to the table.
						INSERT INTO `IB_Accounts`
						(`acctNum`,
						`IB_acctNum`,
						`email`,
						`lastName`,
						`firstName`,
						`accountStatus`,
						`dateOpened`)
						values (
						 v_acctnum,
						 v_clientAccountID,
						 v_email,
						 v_lastname,
						 v_firstname,
						 'Pending',
						 v_dateOpened
						);
					end;
				end if;
			end;
	end if;

END;
