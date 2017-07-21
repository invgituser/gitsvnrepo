USE `testing`;
DROP procedure IF EXISTS `sp_emulate_step1_process_account`;

DELIMITER $$
USE `testing`$$
CREATE PROCEDURE `sp_emulate_step1_process_account`(
  IN p_acctnum BIGINT
)
BEGIN
    DECLARE tFound1 INTEGER;
    DECLARE tFound2 INTEGER;
    DECLARE tStatus VARCHAR(2);
    DECLARE tManaged VARCHAR(2);

    SELECT count(*)
    INTO tFound1
    FROM invdb.ext_acct_info eai
    WHERE eai.acctnum = p_acctnum;

    IF (IFNULL(tFound1, 0) > 0)
    THEN
      SELECT 'This account is already active!' AS msg;
    ELSE
      SELECT
        managed,
        status
      INTO tManaged, tStatus
      FROM invdb.user_trade_profile utp
      WHERE utp.acctnum = p_acctnum;

      IF (IFNULL(tManaged, 'N') != 'N')
      THEN
        SELECT 'This account is marked active but has not external account details.  Cannot proceed!' AS msg;
      ELSE
        UPDATE invdb.user_trade_profile utp
        SET managed = 'N', status = 'P'
        WHERE utp.acctnum = p_acctnum;

        SELECT 'This account is process!' AS msg;
      END IF;

    END IF;

  END$$

DELIMITER ;


######################################

USE `testing`;
DROP procedure IF EXISTS `sp_emulate_step2_openaccount`;

DELIMITER $$
USE `testing`$$
CREATE  PROCEDURE `sp_emulate_step2_openaccount`(
  IN p_acctnum BIGINT
)
BEGIN
    DECLARE tFound1 INTEGER;
    DECLARE tFound2 INTEGER;
    DECLARE tStatus INTEGER;
    DECLARE tClientAccountID VARCHAR(10);

    SELECT count(*)
    INTO tFound1
    FROM invdb.user_trade_profile utp
    WHERE utp.acctnum = p_acctnum;

    SELECT count(*)
    INTO tFound2
    FROM invdb.ext_acct_info eai
    WHERE eai.acctnum = p_acctnum;

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
        FROM invdb.dc_acct_owners_details daod
        WHERE daod.acctnum = p_acctnum;

        set tClientAccountID = CONCAT('TST', p_acctnum);

		IF (IFNULL(tFound2, 0) > 0)
        THEN
        INSERT INTO invdb.ext_acct_info
		(clientAccountID,
		acctnum,
		status,
		rep,
		email,
		accountType,
		applicantFName,
		applicantMName,
		applicantLName,
		address1,
		address2,
		address3,
		city,
		state,
		zipcode,
		country,
		primaryPhoneNbr,
		secondayPhoneNbr,
		workPhoneNbr,
		faxNbr,
		ssn,
		dob,
		acctType,
		taxable,
		objective,
		dateOpened,
		created,
		lastUpdated)
            SELECT
              tClientAccountID AS clientAccountID,
               daod.acctnum,
              'P',
              NULL,
              daod.emailAddress,
              dad.acctTypeId,
              daod.firstName,
              NULL,
              daod.lastName,
              daod.physicalAddressStreet,
              NULL,
              NULL,
              daod.physicalAddressCity,
              daod.physicalAddressState,
              daod.physicalAddressZipCode,
              'USA',
              daod.phoneNumber,
              daod.secondPhoneNumber,
              NULL, -- worknumber
              NULL, -- faxnum
              daod.ssn,
              invdb.funct_strdate2inv_date(daod.dob, '%m/%d/%Y'),
              dml.displayName,-- acctType,
              NULL, -- taxable
              NULL, -- objective
              invdb.funct_date2inv_date(now()) as performanceInceptionDate, -- dateOpened
              now(),
              NULL
            FROM invdb.dc_acct_owners_details AS daod
              , invdb.dc_acct_details dad
              , invdb.dc_m_lookup dml
            WHERE daod.acctnum = p_acctnum
                  AND daod.acctOwnerId = 1
                  AND daod.acctnum = dad.acctnum
                  AND dad.acctTypeId = dml.lookupCode
                  AND dml.lookupSet = 'ACCTTYPE';


          SELECT 'This account# was ADDED to ext_acct_info using TDs data' AS msg;
        ELSE
        INSERT INTO invdb.ext_acct_info
		(clientAccountID,
		acctnum,
		status,
		rep,
		email,
		accountType,
		applicantFName,
		applicantMName,
		applicantLName,
		address1,
		address2,
		address3,
		city,
		state,
		zipcode,
		country,
		primaryPhoneNbr,
		secondayPhoneNbr,
		workPhoneNbr,
		faxNbr,
		ssn,
		dob,
		acctType,
		taxable,
		objective,
		dateOpened,
		created,
		lastUpdated)
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
            invdb.funct_date2inv_date(now()), -- date opened
			now(), -- created
            NULL -- last updated
          );

          SELECT 'This account# was ADDED to ext_acct_info using as sample data' AS msg;

        END IF;
        UPDATE invdb.user_trade_profile utp
			set clientAccountID = tClientAccountID
        WHERE utp.acctnum = p_acctnum;

        CALL invdb.sp_user_profile_manage(p_acctnum, 'O');
      END IF;

    END IF;


  END$$

DELIMITER ;


################################
USE `testing`;
DROP procedure IF EXISTS `sp_fund_account`;

DELIMITER $$
USE `testing`$$
CREATE  PROCEDURE `sp_fund_account`(
  IN p_acctnum BIGINT,
  IN p_amount  DOUBLE
)
BEGIN

  DECLARE tClientAccountID	VARCHAR(10);


      IF (IFNULL(p_acctnum, 'XXX') != 'XXX')
      THEN

			SELECT clientAccountID
            INTO tClientAccountID
            FROM invdb.ext_acct_info eai
            WHERE eai.acctnum = p_acctnum;

			INSERT INTO invdb.ext_position
				(acctnum,
				clientAccountID,
				currencyPrimary,
				fxRateToBase,
				symbol,
				reportDate,
				purchaseDate,
				side,
				quantity,
				costBasisPrice,
				costBasisMoney,
				markPrice,
				positionValue,
				pnlUnrealized,
				levelOfDetail,
				created
				)
			SELECT
				eai.acctnum
				, eai.clientAccountID as clientAccountID
				, 'USD' as currencyPrimary
				, '1.0' as fxRateToBase
				, 'Cash' as symbol
				, invdb.FUNCT_GET_SWITCH('BROKER_BDATE') as reportDate
				, invdb.FUNCT_GET_SWITCH('BROKER_BDATE') as purchaseDate
				, 'Long' as side
				, p_amount as quantity
				, 1 as costBasisPrice
				, p_amount as costBasisMoney
				, 1 as markPrice
				, p_amount as positionValue
				, 0 as pnlUnrealized
				, 'Cash' as levelOfDetail
				, now() as created
			FROM invdb.ext_acct_info as eai
			WHERE eai.acctnum = p_acctnum
            ON duplicate key update
				  quantity = quantity + p_amount
                , costBasisMoney = costBasisMoney + p_amount
                , positionValue = positionValue + p_amount
                , levelOfDetail = 'Funded'
			;


		   INSERT INTO invdb.ext_nav
				(clientAccountID,
				reportDate,
				cash,
				stock,
				funds,
				interestAccrual,
				dividentAccrual,
				total)
			VALUES
			(tClientAccountID,
			invdb.FUNCT_GET_SWITCH('BROKER_BDATE'),
			p_amount,
			0,
			0,
			0,
			0,
			p_amount)
            ON duplicate key update
				 cash = cash + p_amount
                , total = total + p_amount
			;

			INSERT INTO invdb.trade_process_identifier
			(acctnum,
			tradeStatus,
			processStatus,
			reason,
			created,
			updated)
			VALUES
			(p_acctnum,
			'N',
			null,
			'New',
			now(),
			null)
            ON duplicate key update
				tradeStatus = CASE WHEN (tradeStatus = 'N') THEN 'N'
									 ELSE 'A'
								END,
                reason = 'Funded',
                updated = now()
			;

    END IF;
  END$$

DELIMITER ;


################################

USE `testing`;
DROP procedure IF EXISTS `sp_emulate_step3_activateaccount`;

DELIMITER $$
USE `testing`$$
CREATE  PROCEDURE `sp_emulate_step3_activateaccount`(
	IN p_acctnum	BIGINT,
	IN p_amount		DOUBLE
)
BEGIN
	DECLARE tFound1 INTEGER;
  DECLARE tFound2	INTEGER;
	DECLARE tStatus	INTEGER;
  DECLARE tClientAccountID VARCHAR(20);

    SELECT count(*)
    INTO tFound1
    FROM invdb.ext_acct_info eai
    WHERE eai.acctnum = p_acctnum
    ;

	IF (IFNULL(tFound1,0) = 0)
	THEN
		SELECT 'This account# NOT YET OPENED' as msg;
	ELSE
		SELECT clientAccountID
		INTO tClientAccountID
		FROM invdb.ext_acct_info eai
		WHERE eai.acctnum = p_acctnum
        ;

		IF (IFNULL(tClientAccountID,'XXX') != 'XXX')
		THEN

			UPDATE invdb.user_trade_profile utp
				set utp.managed = 'A',
					  utp.status = 'A'
			WHERE utp.acctnum = p_acctnum;

			UPDATE invdb.ext_acct_info eai
				set eai.status = 'A'
			WHERE eai.acctnum = p_acctnum;

            if (p_amount is not null )
            THEN
				CALL testing.sp_fund_account(p_acctnum, p_amount);
				call invdb.sp_user_profile_manage(p_acctnum, 'F');

				SELECT 'This account# was ACTIVATED and POSITION created' as msg;
            ELSE
				call invdb.sp_user_profile_manage(p_acctnum, 'A');

				SELECT 'This account# was ACTIVATED' as msg;
            END IF;

		ELSE
			SELECT 'This account# was NOT FOUND to ext_acct_info' as msg;
 		END IF;

	END IF;



END$$

DELIMITER ;



#################################

USE `testing`;
DROP procedure IF EXISTS `sp_emulate_step4_funding`;

DELIMITER $$
USE `testing`$$
CREATE PROCEDURE `sp_emulate_step4_funding`(
  IN p_acctnum BIGINT,
  IN p_amount  DOUBLE
)
BEGIN
    DECLARE tFound1 INTEGER;
    DECLARE tFound2 INTEGER;
    DECLARE tStatus INTEGER;
    DECLARE tClientAccountID VARCHAR(20);

    SELECT count(*)
    INTO tFound1
    FROM invdb.ext_acct_info eai
    WHERE eai.acctnum = p_acctnum;

    IF (IFNULL(tFound1, 0) = 0)
    THEN
      SELECT 'This account# NOT YET OPENED' AS msg;
    ELSE
      SELECT clientAccountID
      INTO tClientAccountID
      FROM invdb.ext_acct_info eai
      WHERE eai.acctnum = p_acctnum;

      IF (IFNULL(tClientAccountID, 'XXX') != 'XXX')
      THEN
        CALL testing.sp_fund_account(p_acctnum, p_amount);
        CALL invdb.sp_user_profile_manage(p_acctnum, 'F');

        SELECT 'This account# was ACTIVATED and POSITION created' AS msg;
      ELSE
        SELECT 'This account# was NOT FOUND to ext_acct_info' AS msg;
      END IF;

    END IF;


  END$$

DELIMITER ;



