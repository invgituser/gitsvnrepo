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

        IF (IFNULL(tFound2, 0) > 0)
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
              CONCAT('TST', `dc_acct_owners_details`.`acctnum`) AS clientAccountID,
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
              NULL,
              NULL,
              `dc_acct_owners_details`.`ssn`,
              `invdb`.`funct_strdate2inv_date`(`dc_acct_owners_details`.`dob`, '%m/%d/%Y'),
              `dc_m_lookup`.`displayName`,
              NULL,
              NULL,
              `invdb`.`funct_date2inv_date`(now())                 `performanceInceptionDate`,
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


          SELECT 'This account# was ADDED to ext_acct_info' AS msg;
        ELSE
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
          VALUES (
            CONCAT('TST', p_acctnum),
            p_acctnum,
            'P',
            NULL,
            CONCAT('testing', p_acctnum, '@invessence.com'),
            'Individual',
            'Testing',
            NULL,
            p_acctnum,
            '1 Main Street, Suite 202',
            NULL,
            NULL,
            'Chatham',
            'NJ',
            '07928',
            'USA',
            '(201) 977-1955',
            '(201) 977-6490',
            NULL,
            NULL,
            '123-12-1234'
            '1986-06-12',
            CONCAT('TST', p_acctnum),
            NULL,
            NULL,
            `invdb`.`funct_date2inv_date`(now()),
            NULL
          );
        END IF;
        CALL `invdb`.`sp_user_profile_manage`(`p_acctnum`, 'O');
      END IF;

    END IF;


  END$$
DELIMITER ;
