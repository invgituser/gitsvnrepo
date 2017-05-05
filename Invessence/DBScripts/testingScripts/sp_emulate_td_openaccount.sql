DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_td_openaccount`;

DELIMITER $$
CREATE PROCEDURE `testing`.`sp_emulate_td_openaccount`(
	IN p_acctnum	BIGINT
)
BEGIN
	DECLARE tFound1 INTEGER;
    DECLARE tFound2	INTEGER;
	DECLARE tStatus	INTEGER;
    
    SELECT count(*)
    INTO tFound1
    FROM `invdb`.`dc_requests`
    WHERE `dc_requests`.`reqType` in ( 'ACCT_APPLI_NEW', 'TD_TRAN_NEW')
    AND `dc_requests`.`acctnum` = `p_acctnum`
    ;
    
    SELECT count(*)
    INTO tFound2
    FROM `invdb`.`ext_acct_info`
    WHERE `ext_acct_info`.`acctnum` = `p_acctnum`
    ;
    
    IF (IFNULL(tFound2,0) != 0)
    THEN
		SELECT 'This account# is already created.' as msg;
	ELSE
        IF (IFNULL(tFound1,0) = 0)
		THEN
			SELECT 'This account# NOT yet processed to TD' as msg;
		ELSE
        
			SELECT COUNT(*)
            INTO tFound2
            FROM `invdb`.`dc_acct_owners_details`
            WHERE `dc_acct_owners_details`.`acctnum` = `p_acctnum`
            ;
            
            IF (IFNULL(tFound2,0) > 0)
            THEN
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
					CONCAT('TST',`dc_acct_owners_details`.`acctnum`) as clientAccountID, -- `clientAccountID`,
					`dc_acct_owners_details`.`acctnum`, -- `acctnum`,
					'P', -- `status`, -- (P= Pending)
					null , -- `rep`,
					`dc_acct_owners_details`.`emailAddress` , -- `td_profile`.`emailAddress`, (Not provided by TD)
					`dc_acct_details`.`acctTypeId`, -- `accountType`,
					`dc_acct_owners_details`.`firstName`, -- `applicantFName`,
					null, -- `applicantMName`, -- (Not provided by TD)
					`dc_acct_owners_details`.`lastName`, -- `applicantLName`,
					`dc_acct_owners_details`.`physicalAddressStreet`, -- `address1`,
					null, -- `address2`,
					null, -- `address3`
					`dc_acct_owners_details`.`physicalAddressCity`, -- `city`
					`dc_acct_owners_details`.`physicalAddressState`, -- `state`
					`dc_acct_owners_details`.`physicalAddressZipCode`, -- `zipCode`
					'USA',  -- `country`, -- (Not provided by TD)
					`dc_acct_owners_details`.`phoneNumber`,  -- `primaryPhoneNbr`,
					`dc_acct_owners_details`.`secondPhoneNumber`,  -- `secondayPhoneNbr`,
					null, -- `workPhoneNbr`,
					null, -- `faxNbr`,
					`dc_acct_owners_details`.`ssn`, -- `ssn`,
					`invdb`.`funct_strdate2inv_date`(`dc_acct_owners_details`.`dob`,'%m/%d/%Y'), -- `dob`,
					`dc_m_lookup`.`displayName`,
					null, -- `taxable`,
					null, -- `objective`,
					`invdb`.`funct_date2inv_date`(now()) `performanceInceptionDate`, -- `dateOpened`,
					now(), -- `created`,
					null -- `lastUpdated`
				FROM `invdb`.`dc_acct_owners_details` as `dc_acct_owners_details`
					,`invdb`.`dc_acct_details`
					,`invdb`.`dc_m_lookup`
				WHERE `dc_acct_owners_details`.`acctnum` = `p_acctnum`
				AND   `dc_acct_owners_details`.`acctOwnerId` = 1
				AND   `dc_acct_owners_details`.`acctnum`  = `dc_acct_details`.`acctnum`
				AND   `dc_acct_details`.`acctTypeId` = `dc_m_lookup`.`lookupCode`
				AND   `dc_m_lookup`.`lookupSet` = 'ACCTTYPE'
				;
                
				call `invdb`.`sp_user_profile_manage`(`p_acctnum`, 'O');
 
				SELECT 'This account# was ADDED to ext_acct_info' as msg;
			ELSE
				SELECT 'This account# was NOT FOUND to dc_acct_owners_details' as msg;
            END IF;
		END IF;

	END IF;
    
    

END$$
DELIMITER ;
