DROP PROCEDURE IF EXISTS admin_add_advisor;

DELIMITER $$
CREATE PROCEDURE `admin_add_advisor`(
	 IN p_userid	VARCHAR(20)
	,IN p_company	VARCHAR(40)
	,IN p_accronym	VARCHAR(20)
	,IN p_email		VARCHAR(40)
	,IN p_lastname	VARCHAR(20)
	,IN p_firstname	VARCHAR(20)
	,IN p_state		VARCHAR(2)
	,IN p_altemail	VARCHAR(40)
)
BEGIN
	-- 1) Add User Logon
	DECLARE p_logonid	BIGINT(20);

	CALL `sp_login_add_mod`('A' 
							,p_logonid 
							,p_userid 
							,p_email
							,p_pwd 
							,'A' -- logonstatus, 
							,p_lastname
							,p_firstname
							,p_state
							,p_altemail
							,'Advisor' -- p_leadsource 
							,null -- p_question1 , 
							,null -- p_ans1 , 
							,null -- p_question2 , 
							,null -- p_ans2 , 
							,null -- p_question3 , 
							,null -- p_ans3 , 
							,null -- p_macaddress , 
							,null -- p_resetID , 
							,null -- p_cookieID , 
							,null -- p_emailmsgtype , 
							);
	-- 2) Add role
	-- 3) Add to Advisor Info Table
	-- 4) Add Basket Info

END$$
DELIMITER ;
