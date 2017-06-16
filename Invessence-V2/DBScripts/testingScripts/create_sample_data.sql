DROP PROCEDURE IF EXISTS testing.create_sample_data;

DELIMITER $$
CREATE PROCEDURE testing.create_sample_data()
BEGIN

  DECLARE updt_done Boolean DEFAULT FALSE;
  DECLARE newacctnum BIGINT;
  DECLARE newclientID VARCHAR(20);
  DECLARE tacctnum	BIGINT;
  DECLARE tclientAccountID VARCHAR(20);

  DECLARE cur1 CURSOR FOR 
	SELECT DISTINCT
			acctnum, clientAccountID
	FROM testing.ext_acct_info;
    
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET updt_done = TRUE;


	DROP TABLE IF EXISTS testing.ext_acct_info;
	create table testing.ext_acct_info
	as select * from invdb.ext_acct_info where status = 'A'
	limit 100;

	DROP TABLE IF EXISTS testing.user_trade_profile;
	create table testing.user_trade_profile
	as select * from invdb.user_trade_profile 
	where acctnum in (select acctnum from testing.ext_acct_info)
	limit 100;
    
	DROP TABLE IF EXISTS testing.user_risk_questions;
	create table testing.user_risk_questions
	as select * from invdb.user_risk_questions 
	where acctnum in (select acctnum from testing.user_trade_profile)
	limit 100;

	DROP TABLE IF EXISTS testing.acct_financial;
	create table testing.acct_financial
	as select * from invdb.acct_financial 
	where acctnum in (select acctnum from testing.user_trade_profile)
	limit 100;

	DROP TABLE IF EXISTS testing.ext_position;
	create table testing.ext_position
	as 
	select * from invdb.ext_position 
	where clientAccountID in (select clientAccountID from testing.user_trade_profile)
	and reportDate = invdb.FUNCT_GET_SWITCH('BROKER_BDATE');

	DROP TABLE IF EXISTS testing.ext_nav;
	create table testing.ext_nav
	as 
	select * from invdb.ext_nav 
	where clientAccountID in (select clientAccountID from testing.user_trade_profile)
	and reportDate = invdb.FUNCT_GET_SWITCH('BROKER_BDATE');

	DROP TABLE IF EXISTS testing.user_access_role;
	create table testing.user_access_role
	as 
	select * from invdb.user_access_role 
	where acctnum in (select acctnum from testing.user_trade_profile);

	DROP TABLE IF EXISTS testing.user_logon;
	create table testing.user_logon
	as 
	select * from invdb.user_logon 
	where logonid in (select logonid from testing.user_access_role);
    
    COMMIT;
    
    OPEN cur1;
 
	SET newacctnum = 0;
	the_loop: LOOP
 
	FETCH cur1 INTO tacctnum, tclientAccountID;
	
	IF updt_done THEN
		LEAVE the_loop;
	END IF;
    
		set newacctnum = newacctnum + 1;
        set newclientID = CONCAT('TESTING',newacctnum);
        
		UPDATE testing.ext_acct_info
			set acctnum = newacctnum, clientAccountID = newclientID 
		where acctnum = tacctnum;
        
		UPDATE testing.user_trade_profile
			set acctnum = newacctnum, clientAccountID = newclientID, managed='A', `status` = 'A' 
		where acctnum = tacctnum;
        
		UPDATE testing.user_risk_questions
			set acctnum = newacctnum
		where acctnum = tacctnum;

		UPDATE testing.acct_financial
			set acctnum = newacctnum
		where acctnum = tacctnum;
        
		UPDATE testing.ext_position
			set acctnum = newacctnum, clientAccountID = newclientID 
		where acctnum = tacctnum;
        
		UPDATE testing.ext_nav
			set clientAccountID = newclientID 
		where clientAccountID = tclientAccountID;
    
		UPDATE testing.user_access_role
			set acctnum = newacctnum 
		where acctnum = tacctnum;
	END LOOP the_loop;
    
 
    CLOSE cur1;
    
	COMMIT;

    
    -- NOW MOVE ALL DATA TO invdb.
    DELETE FROM invdb.ext_acct_info
    where acctnum <=100;
    insert into invdb.ext_acct_info
    select * from testing.ext_acct_info;
    
    DELETE FROM invdb.user_trade_profile
    where acctnum <=100;
    insert into invdb.user_trade_profile
    select * from testing.user_trade_profile;
    
    DELETE FROM invdb.user_risk_questions
    where acctnum <=100;
    insert into invdb.user_risk_questions
    select * from testing.user_risk_questions;
    
	DELETE FROM invdb.acct_financial
    where acctnum <=100;
    insert into invdb.acct_financial
    select * from testing.acct_financial;
   
	DELETE FROM invdb.ext_position
    where acctnum <=100;
    insert into invdb.ext_position
    select * from testing.ext_position;
    
	DELETE FROM invdb.ext_nav
    where clientAccountID like 'TESTING%';
    insert into invdb.ext_nav
    select * from testing.ext_nav;
    
    -- Now insert new records in user_access_role table
	DELETE FROM invdb.user_access_role
    where acctnum <=100;
    insert into invdb.user_access_role
    select * from testing.user_access_role;
    
	DELETE FROM invdb.user_logon
    where logonid in (select logonid from testing.user_logon);
    insert into invdb.user_logon
    select * from testing.user_logon;


	DELETE FROM invdb.trade_process_identifier
    where acctnum <= 100;
    INSERT INTO `invdb`.`trade_process_identifier`
		(`acctnum`,`tradeStatus`,`processStatus`,`reason`,`created`,`updated`)
	SELECT
		`acctnum`,
		CASE WHEN (MOD(`acctnum`,3) = 0) THEN 'A'
			 WHEN (MOD(`acctnum`,3) = 1) THEN 'N'
             WHEN (MOD(`acctnum`,3) = 2) THEN 'D'
             ELSE 'V'
		END,
        null,'TESTING',now(),null
	FROM testing.ext_acct_info;
        
END$$

DELIMITER ;



