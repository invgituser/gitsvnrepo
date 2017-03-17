-- EXAMPLES:

CALL `testing`.`sp_emulate_notification`(
	  'BB' -- `p_advisor`,
	  ,'ACTIVE' -- `p_messageType`,
	  ,'Y' -- `p_includeAdvisor`,
	  ,'Account Activated' -- `p_advisorsubject`,
	  ,'Y' -- `p_includeAdvisorEmail`,
	  ,'Account Activated' --  `p_emailAdvisorSubject`,
	  ,null -- `p_emailAdvisorRecepient`,
	  ,null -- `p_includeUser`,
	  ,null -- `p_userSubject`,
	  ,null -- `p_includeUserEmail`,
	  ,null -- `p_emailUserSubject`
);

call `testing`.`sp_emulate_td_openaccount`(2398);
call `testing`.`sp_emulate_td_activateaccount`(2398,3000);
