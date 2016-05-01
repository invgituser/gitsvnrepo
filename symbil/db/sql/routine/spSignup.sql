DELIMITER $$
CREATE PROCEDURE `spSignup`(
    in pUserID    varchar(100),
    in pEmailID    varchar(100),
    in pPassword  varchar(100),
    out oLogonID int)
begin

	if exists( select 1 from user_logon where userid = pUserID) then
        set oLogonID = -1;
    else 
        insert into user_logon (userid, email, pwd, created, lastupdated ) 
    			     values (pUserID,pEmailID, pPassword, now(), now());

       select last_insert_id() into oLogonID;
   end if;

end$$
DELIMITER ;