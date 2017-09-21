DROP PROCEDURE IF EXISTS `temp`.`sp_upload_ext_acct_info`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_ext_acct_info`(
)
BEGIN
    
    UPDATE `invdb`.`ext_acct_info`, `temp`.`tmp_client_data` as `client_data`
    SET
		`ext_acct_info`.`rep` = `client_data`.`advisorID`,
		
		`ext_acct_info`.`accountType` = `client_data`.`accountType`,
		`ext_acct_info`.`applicantFName` = `client_data`.`firstName`,
		
		`ext_acct_info`.`applicantLName` = `client_data`.`lastName`,
		`ext_acct_info`.`address1` = `client_data`.`street`,
		`ext_acct_info`.`address2` = `client_data`.`address2`,
		`ext_acct_info`.`address3` = `client_data`.`address3`,
		`ext_acct_info`.`city` = `client_data`.`city`,
		`ext_acct_info`.`state` = `client_data`.`state`,
		`ext_acct_info`.`zipcode` = `client_data`.`zipCode`,
		
		`ext_acct_info`.`primaryPhoneNbr` = `client_data`.`phoneNumber`,
		
		
		`ext_acct_info`.`faxNbr` = `client_data`.`faxNumber`,
		`ext_acct_info`.`ssn` = `client_data`.`SSNOrTaxID`,
		`ext_acct_info`.`dob` = `invdb`.`funct_strdate2inv_date`(`client_data`.`birthDate`,'%m/%d/%Y'),
		`ext_acct_info`.`taxable` = `client_data`.`taxable`,
		`ext_acct_info`.`objective` = `client_data`.`objective`,
        
		
		`ext_acct_info`.`lastUpdated` = now()
	WHERE `ext_acct_info`.`clientAccountID` = `client_data`.`clientAccountID`;
    
	
	INSERT INTO `invdb`.`ext_acct_info`
	(
		`clientAccountID`,
		`acctnum`,
		`status`,
		`rep`,
		`email`,
        `accountType`,
		`applicantFName`,
		`applicantMName`,
		`applicantLName`,
		`address1`,
		`address2`,
		`address3`,
		`city`,
		`state`,
		`zipcode`,
		`country`,
		`primaryPhoneNbr`,
		`secondayPhoneNbr`,
		`workPhoneNbr`,
		`faxNbr`,
		`ssn`,
		`dob`,
        `acctType`,
		`taxable`,
		`objective`,
        `dateOpened`,
		`created`,
		`lastUpdated`
    )
    SELECT
		`client_data`.`clientAccountID`, 
		`client_data`.`acctnum`, 
		'O', 
		`client_data`.`advisorID`, 
		null, 
        `client_data`.`accountType`, 
		`client_data`.`firstName`, 
		null, 
		`client_data`.`lastName`, 
		`client_data`.`street`, 
		`client_data`.`address2`, 
		`client_data`.`address3`, 
		`client_data`.`city`, 
		`client_data`.`state`, 
		`client_data`.`zipCode`, 
		null,  
		`client_data`.`phoneNumber`,  
		null,  
		null, 
		`client_data`.`faxNumber`, 
		`client_data`.`SSNOrTaxID`, 
		`invdb`.`funct_strdate2inv_date`(`client_data`.`birthDate`,'%m/%d/%Y'), 
		`client_data`.`accountType`,
		`client_data`.`taxable`, 
		`client_data`.`objective`, 
		CASE WHEN (`client_data`.`performanceInceptionDate` is NULL)
			THEN `invdb`.`funct_date2inv_date`(now())
				WHEN (trim(`client_data`.`performanceInceptionDate`) = '')
			THEN `invdb`.`funct_date2inv_date`(now())
			ELSE `invdb`.`funct_strdate2inv_date`(`client_data`.`performanceInceptionDate`,'%m/%d/%Y')
		END as `performanceInceptionDate`, 
		now(), 
		null 
	FROM `temp`.`tmp_client_data` as `client_data`
    WHERE `client_data`.`acctnum` not in (select `clientAccountID` from `invdb`.`ext_acct_info`)
    ;
    
    UPDATE `invdb`.`user_trade_profile`, `invdb`.`ext_acct_info`
		set `user_trade_profile`.`clientAccountID` = `ext_acct_info`.`clientAccountID`,
			`user_trade_profile`.`firstname` = `ext_acct_info`.`applicantFName`,
			`user_trade_profile`.`lastname` = `ext_acct_info`.`applicantLName`,
            `user_trade_profile`.`managed` = CASE WHEN (`ext_acct_info`.`status` in ('A', 'O')) THEN 'A'
												  ELSE 'X'
											 END,
            `user_trade_profile`.`status` = `ext_acct_info`.`status`,
            `user_trade_profile`.`lastUpdated` = now()
	WHERE `user_trade_profile`.`acctnum` = `ext_acct_info`.`acctnum`;

END$$
DELIMITER ;
