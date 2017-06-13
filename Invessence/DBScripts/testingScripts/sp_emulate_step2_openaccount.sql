DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_openaccount`;
DROP PROCEDURE IF EXISTS `testing`.`sp_emulate_step2_openaccount`;

DELIMITER $$
CREATE PROCEDURE `testing`.`sp_emulate_step2_openaccount`(
  IN p_acctnum BIGINT
)
  BEGIN
    DECLARE tFound1 INTEGER;
    DECLARE tFound2 INTEGER;
    DECLARE tStatus INTEGER;
    DECLARE tClientAccountID VARCHAR(10);

    SELECT count(*)
    INTO tFound1
    FROM `invdb`.`user_trade_profile`
    WHERE `user_trade_profile`.`acctnum` = `p_acctnum`;

    SELECT count(*)
    INTO tFound2
    FROM `invdb`.`ext_acct_info`
    WHERE `ext_acct_info`.`acctnum` = `p_acctnum`;

    IF (IFNULL(tFound2, 0) != 0)
    THEN
      SELECT 'This account# is already created.' AS msg;
    ELSE
      IF (IFNULL(tFound1, 0) = 0)
      THEN
        SELECT 'This account# found on DB' AS msg;
      ELSE

        SELECT COUNT(*)
        INTO tFound2
        FROM `invdb`.`dc_acct_owners_details`
        WHERE `dc_acct_owners_details`.`acctnum` = `p_acctnum`;

        set tClientAccountID = CONCAT('TST', `p_acctnum`);
        
		IF (IFNULL(tFound2, 0) > 0)
        THEN
        INSERT INTO `invdb`.`ext_acct_info`
		(`clientAccountID`,
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
		`lastUpdated`)
            SELECT
              tClientAccountID AS clientAccountID,
              `dc_acct_owners_details`.`acctnum`,
              'P',
              NULL,
              `dc_acct_owners_details`.`emailAddress`,
              `dc_acct_details`.`acctTypeId`,
              `dc_acct_owners_details`.`firstName`,
              NULL,
              `dc_acct_owners_details`.`lastName`,
              `dc_acct_owners_details`.`physicalAddressStreet`,
              NULL,
              NULL,
              `dc_acct_owners_details`.`physicalAddressCity`,
              `dc_acct_owners_details`.`physicalAddressState`,
              `dc_acct_owners_details`.`physicalAddressZipCode`,
              'USA',
              `dc_acct_owners_details`.`phoneNumber`,
              `dc_acct_owners_details`.`secondPhoneNumber`,
              NULL, -- worknumber
              NULL, -- faxnum
              `dc_acct_owners_details`.`ssn`,
              `invdb`.`funct_strdate2inv_date`(`dc_acct_owners_details`.`dob`, '%m/%d/%Y'),
              `dc_m_lookup`.`displayName`,-- acctType,
              NULL, -- taxable
              NULL, -- objective
              `invdb`.`funct_date2inv_date`(now()) as `performanceInceptionDate`, -- dateOpened 
              now(),
              NULL
            FROM `invdb`.`dc_acct_owners_details` AS `dc_acct_owners_details`
              , `invdb`.`dc_acct_details`
              , `invdb`.`dc_m_lookup`
            WHERE `dc_acct_owners_details`.`acctnum` = `p_acctnum`
                  AND `dc_acct_owners_details`.`acctOwnerId` = 1
                  AND `dc_acct_owners_details`.`acctnum` = `dc_acct_details`.`acctnum`
                  AND `dc_acct_details`.`acctTypeId` = `dc_m_lookup`.`lookupCode`
                  AND `dc_m_lookup`.`lookupSet` = 'ACCTTYPE';


          SELECT 'This account# was ADDED to ext_acct_info using TDs data' AS msg;
        ELSE
        INSERT INTO `invdb`.`ext_acct_info`
		(`clientAccountID`,
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
		`lastUpdated`)
          VALUES (
            tClientAccountID,
            p_acctnum,
            'P',
            NULL, -- rep
            CONCAT('testing', p_acctnum, '@invessence.com'), -- email
            'Individual', -- accountType
            'Testing', -- firstname
            NULL, -- middleI
            p_acctnum, -- lastname
            null, -- address1,
            NULL, -- address2
            NULL, -- address3
            null, -- city,
            null, -- state,
            null, -- zip,
            null, -- country,
            null, -- primary phone,
            null, -- secondary phone,
            NULL, -- work phone
            NULL, -- fax number
            null, -- ssnum
            null, -- DOB,
            null, -- acctType
            NULL, -- taxable
            NULL, -- objective
            `invdb`.`funct_date2inv_date`(now()), -- date opened
			now(), -- created
            NULL -- last updated
          );
                    
          SELECT 'This account# was ADDED to ext_acct_info using as sample data' AS msg;
          
        END IF;
        UPDATE `invdb`.`user_trade_profile`
			set clientAccountID = tClientAccountID
        WHERE `user_trade_profile`.`acctnum` = `p_acctnum`;
        
        CALL `invdb`.`sp_user_profile_manage`(`p_acctnum`, 'O');
      END IF;

    END IF;


  END$$
DELIMITER ;
