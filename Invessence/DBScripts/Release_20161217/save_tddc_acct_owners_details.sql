DELIMITER $$
CREATE PROCEDURE `save_tddc_acct_owners_details`(
		`p_acctnum`	bigint(20)
	,	`p_acctOwnerId`	int(2)
	,	`p_ownership`	varchar(45)
	,	`p_firstName`	varchar(45)
	,	`p_midInitial`	varchar(45)
	,	`p_lastName`	varchar(45)
	,	`p_ssn`	varchar(45)
	,	`p_dob`	varchar(45)
	,	`p_phoneNumber`	varchar(45)
	,	`p_phoneNumberNonUS`	varchar(45)
	,	`p_secondPhoneNumber`	varchar(45)
	,	`p_secondPhoneNumberNonUS`	varchar(45)
	,	`p_emailAddress`	varchar(45)
	,	`p_physicalAddressStreet`	varchar(45)
	,	`p_physicalAddressCity`	varchar(45)
	,	`p_physicalAddressState`	varchar(45)
	,	`p_physicalAddressZipCode`	varchar(45)
	,	`p_mailingAddressStreet`	varchar(45)
	,	`p_mailingAddressCity`	varchar(45)
	,	`p_mailingAddressState`	varchar(45)
	,	`p_mailingAddressZipCode`	varchar(45)
	,	`p_citizenshiId`	varchar(45)
	,	`p_countryOfCitizenship`	varchar(45)
	,	`p_countryOfDualCitizenship`	varchar(45)
	,	`p_countryOfBirth`	varchar(45)
	,	`p_isSPF`	char(1)
	,	`p_spfDetail`	varchar(255)
	,	`p_isDirectorShareholder`	char(1)
	,	`p_directorShareholderDetail`	varchar(255)
	,	`p_bd`	char(1)
	,	`p_bdDetail`	varchar(255)
	,	`p_ownershipPercent`	double
	,	`p_createdBy`	varchar(45)
  )
BEGIN
			INSERT INTO `dc_acct_owners_details`
			(`acctnum`,
			`acctOwnerId`,
			`ownership`,
			`firstName`,
			`midInitial`,
			`lastName`,
			`ssn`,
			`dob`,
			`phoneNumber`,
			`phoneNumberNonUS`,
			`secondPhoneNumber`,
			`secondPhoneNumberNonUS`,
			`emailAddress`,
			`physicalAddressStreet`,
			`physicalAddressCity`,
			`physicalAddressState`,
			`physicalAddressZipCode`,
			`mailingAddressStreet`,
			`mailingAddressCity`,
			`mailingAddressState`,
			`mailingAddressZipCode`,
			`citizenshiId`,
			`countryOfCitizenship`,
			`countryOfDualCitizenship`,
			`countryOfBirth`,
			`isSPF`,
			`spfDetail`,
			`isDirectorShareholder`,
			`directorShareholderDetail`,
			`bd`,
			`bdDetail`,
			`ownershipPercent`,
			`created`,
			`createdBy`,
			`updated`,
			`updatedBy`)
			VALUES
			(
					`p_acctnum`
				,	`p_acctOwnerId`
				,	`p_ownership`
				,	`p_firstName`
				,	`p_midInitial`
				,	`p_lastName`
				,	`p_ssn`
				,	`p_dob`
				,	`p_phoneNumber`
				,	`p_phoneNumberNonUS`
				,	`p_secondPhoneNumber`
				,	`p_secondPhoneNumberNonUS`
				,	`p_emailAddress`
				,	`p_physicalAddressStreet`
				,	`p_physicalAddressCity`
				,	`p_physicalAddressState`
				,	`p_physicalAddressZipCode`
				,	`p_mailingAddressStreet`
				,	`p_mailingAddressCity`
				,	`p_mailingAddressState`
				,	`p_mailingAddressZipCode`
				,	`p_citizenshiId`
				,	`p_countryOfCitizenship`
				,	`p_countryOfDualCitizenship`
				,	`p_countryOfBirth`
				,	`p_isSPF`
				,	`p_spfDetail`
				,	`p_isDirectorShareholder`
				,	`p_directorShareholderDetail`
				,	`p_bd`
				,	`p_bdDetail`
				,	`p_ownershipPercent`
				,	now()
				,	`p_createdBy`
				,	null
				,	null
			)
            ON DUPLICATE KEY UPDATE
            	`ownership`	 =	`p_ownership`
			,	`firstName`	 =	`p_firstName`
			,	`midInitial`	 =	`p_midInitial`
			,	`lastName`	 =	`p_lastName`
			,	`ssn`	 =	`p_ssn`
			,	`dob`	 =	`p_dob`
			,	`phoneNumber`	 =	`p_phoneNumber`
			,	`phoneNumberNonUS`	 =	`p_phoneNumberNonUS`
			,	`secondPhoneNumber`	 =	`p_secondPhoneNumber`
			,	`secondPhoneNumberNonUS`	 =	`p_secondPhoneNumberNonUS`
			,	`emailAddress`	 =	`p_emailAddress`
			,	`physicalAddressStreet`	 =	`p_physicalAddressStreet`
			,	`physicalAddressCity`	 =	`p_physicalAddressCity`
			,	`physicalAddressState`	 =	`p_physicalAddressState`
			,	`physicalAddressZipCode`	 =	`p_physicalAddressZipCode`
			,	`mailingAddressStreet`	 =	`p_mailingAddressStreet`
			,	`mailingAddressCity`	 =	`p_mailingAddressCity`
			,	`mailingAddressState`	 =	`p_mailingAddressState`
			,	`mailingAddressZipCode`	 =	`p_mailingAddressZipCode`
			,	`citizenshiId`	 =	`p_citizenshiId`
			,	`countryOfCitizenship`	 =	`p_countryOfCitizenship`
			,	`countryOfDualCitizenship`	 =	`p_countryOfDualCitizenship`
			,	`countryOfBirth`	 =	`p_countryOfBirth`
			,	`isSPF`	 =	`p_isSPF`
			,	`spfDetail`	 =	`p_spfDetail`
			,	`isDirectorShareholder`	 =	`p_isDirectorShareholder`
			,	`directorShareholderDetail`	 =	`p_directorShareholderDetail`
			,	`bd`	 =	`p_bd`
			,	`bdDetail`	 =	`p_bdDetail`
			,	`ownershipPercent`	 =	`p_ownershipPercent`
			,	`updated`	 =	now()
			,	`updatedBy`	 =	`p_createdBy`
            ;
            
			IF (`p_acctOwnerId` = 1)
            THEN
				update `user_trade_profile`
					set `user_trade_profile`.`firstname` = `p_firstName`
                    ,   `user_trade_profile`.`lastname` = `p_lastName`
				Where `user_trade_profile`.`acctnum` = `p_acctnum`;
            END IF;
            
	  
END$$
DELIMITER ;
