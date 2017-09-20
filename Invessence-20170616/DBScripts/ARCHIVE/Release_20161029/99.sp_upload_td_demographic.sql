DROP PROCEDURE IF EXISTS `temp`.`sp_upload_td_demographic`;

DELIMITER $$
CREATE PROCEDURE `temp`.`sp_upload_td_demographic`(
)
BEGIN

  DECLARE tacctnum, tfirstname, tlastname, tssn, tdob, temail, tphone, taccttype varchar(60);
  DECLARE updt_done Boolean DEFAULT FALSE;

  DECLARE cur1 CURSOR FOR 
	SELECT DISTINCT
			`dc_acct_owners_details`.`acctnum`
		  , upper(`dc_acct_owners_details`.`firstName`) as firstname
		  , upper(`dc_acct_owners_details`.`lastName`) as lastname
		  , replace(`dc_acct_owners_details`.`ssn`,'-','') as ssn
		  , `invdb`.`funct_strdate2inv_date`(`dc_acct_owners_details`.`dob`, '%m/%d/%Y') as dob
		  , `dc_acct_owners_details`.`emailAddress`
          , `dc_acct_owners_details`.`phoneNumber`
          , case WHEN (`dc_acct_details`.`acctTypeId` like '%IRA%') THEN '%IRA%'
                 WHEN (`dc_acct_details`.`acctTypeId` like '%IND%') THEN '%IND%'
                 WHEN (`dc_acct_details`.`acctTypeId` like '%JOI%') THEN '%JOI%'
                 ELSE '%IRA%'
			END as acctTypeId
  FROM `invdb`.`dc_acct_owners_details`, `invdb`.`dc_acct_details`, `invdb`.`dc_requests`
  WHERE `dc_requests`.`acctnum` = `dc_acct_details`.`acctnum`
  AND   `dc_requests`.`acctnum` = `dc_acct_owners_details`.`acctnum`
  AND	`dc_requests`.`status` = 'S'
  AND   `dc_requests`.`created` > `invdb`.`get_business_date`(`dc_requests`.`created`, -30)
  AND   replace(`dc_acct_owners_details`.`ssn`,'-','') in (SELECT `ext_acct_info`.`ssn` from `invdb`.`ext_acct_info` where acctnum is null)
  ;

  DECLARE CONTINUE HANDLER FOR NOT FOUND SET updt_done = TRUE;

    -- First update all existing accounts
    UPDATE `invdb`.`ext_acct_info`, `temp`.`tmp_td_demographic` as `td_profile`
    SET
		`ext_acct_info`.`rep` = `td_profile`.`advisorID`,
		-- `email` = `td_profile`.`emailAddress`,
		`ext_acct_info`.`accountType` = `td_profile`.`accountType`,
		`ext_acct_info`.`applicantFName` = `td_profile`.`firstName`,
		-- `applicantMName` = <{applicantMName: }>,
		`ext_acct_info`.`applicantLName` = `td_profile`.`lastName`,
		`ext_acct_info`.`address1` = `td_profile`.`street`,
		`ext_acct_info`.`address2` = `td_profile`.`address2`,
		`ext_acct_info`.`address3` = `td_profile`.`address3`,
		`ext_acct_info`.`city` = `td_profile`.`city`,
		`ext_acct_info`.`state` = `td_profile`.`state`,
		`ext_acct_info`.`zipcode` = `td_profile`.`zipCode`,
		-- `country` = <{country: }>,
		`ext_acct_info`.`primaryPhoneNbr` = `td_profile`.`phoneNumber`,
		-- `secondayPhoneNbr` = <{secondayPhoneNbr: }>,
		-- `workPhoneNbr` = <{workPhoneNbr: }>,
		`ext_acct_info`.`faxNbr` = `td_profile`.`faxNumber`,
		`ext_acct_info`.`ssn` = `td_profile`.`SSNOrTaxID`,
		`ext_acct_info`.`dob` = `invdb`.`funct_strdate2inv_date`(`td_profile`.`birthDate`,'%m/%d/%Y'),
		`ext_acct_info`.`taxable` = `td_profile`.`taxable`,
		`ext_acct_info`.`objective` = `td_profile`.`objective`,
        `ext_acct_info`.`acctType`  = `td_profile`.`accountType`,
        -- `dateOpened` = `invdb`.`funct_strdate2inv_date`(`td_profile`.`performanceInceptionDate`,'%m/%d/%Y'),
		-- `created` = <{created: }>,
		`ext_acct_info`.`lastUpdated` = now()
	WHERE `clientAccountID` = `td_profile`.`accountNumber`;
    
	-- Now insert anything that is not on DB.
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
		`td_profile`.`accountNumber`, -- `clientAccountID`,
		null, -- `acctnum`,
		'P', -- `status`, -- (P= Pending)
		`td_profile`.`advisorID`, -- `rep`,
		null, -- `td_profile`.`emailAddress`, (Not provided by TD)
        `td_profile`.`accountType`, -- `accountType`,
		`td_profile`.`firstName`, -- `applicantFName`,
		null, -- `applicantMName`, -- (Not provided by TD)
		`td_profile`.`lastName`, -- `applicantLName`,
		`td_profile`.`street`, -- `address1`,
		`td_profile`.`address2`, -- `address2`,
		`td_profile`.`address3`, -- `address3`
		`td_profile`.`city`, -- `city`
		`td_profile`.`state`, -- `state`
		`td_profile`.`zipCode`, -- `zipCode`
		null,  -- `country`, -- (Not provided by TD)
		`td_profile`.`phoneNumber`,  -- `primaryPhoneNbr`,
		null,  -- `secondayPhoneNbr`,
		null, -- `workPhoneNbr`,
		`td_profile`.`faxNumber`, -- `faxNbr`,
		`td_profile`.`SSNOrTaxID`, -- `ssn`,
		`invdb`.`funct_strdate2inv_date`(`td_profile`.`birthDate`,'%m/%d/%Y'), -- `dob`,
		`td_profile`.`accountType`,
		`td_profile`.`taxable`, -- `taxable`,
		`td_profile`.`objective`, -- `objective`,
		CASE WHEN (`td_profile`.`performanceInceptionDate` is NULL)
			THEN `invdb`.`funct_date2inv_date`(now())
				WHEN (trim(`td_profile`.`performanceInceptionDate`) = '')
			THEN `invdb`.`funct_date2inv_date`(now())
			ELSE `invdb`.`funct_strdate2inv_date`(`td_profile`.`performanceInceptionDate`,'%m/%d/%Y')
		END as `performanceInceptionDate`, -- `dateOpened`,
		now(), -- `created`,
		null -- `lastUpdated`
	FROM `temp`.`tmp_td_demographic` as `td_profile`
    WHERE `td_profile`.`accountNumber` not in (select `clientAccountID` from `invdb`.`ext_acct_info`)
    ;
    
    -- Find associated account number from DB.
      OPEN cur1;
 
  
	  the_loop: LOOP
 
    
	  FETCH cur1 INTO tacctnum, tfirstname, tlastname, tssn, tdob, temail, tphone, taccttype;
	
      IF updt_done THEN
        LEAVE the_loop;
      END IF;

    
	  update `invdb`.`ext_acct_info`
	  set 
			`ext_acct_info`.`acctnum` = tacctnum
		  , `ext_acct_info`.`email` = IFNULL(`ext_acct_info`.`email`, temail)
          , `ext_acct_info`.`primaryPhoneNbr` = IFNULL(`ext_acct_info`.`primaryPhoneNbr`, tphone)
          , `ext_acct_info`.`status` = 'O'
		  , `ext_acct_info`.`lastUpdated` = now()
	  where  upper(`ext_acct_info`.`applicantLName`)  = tlastname
	  AND    upper(`ext_acct_info`.`applicantFName`)  = tfirstname 
	  AND    `ext_acct_info`.`ssn` = tssn
      AND    `ext_acct_info`.`dob` = tdob
      AND    `ext_acct_info`.`acctType` like taccttype
	  ;
	END LOOP the_loop;
 
    CLOSE cur1;

END$$
DELIMITER ;
