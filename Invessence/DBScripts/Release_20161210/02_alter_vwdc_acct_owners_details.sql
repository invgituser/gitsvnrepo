use invdb;

CREATE or replace 
    VIEW `invdb`.`vwdc_acct_owners_details` AS
    SELECT 
        `aod`.`acctnum` AS `acctnum`,
        `aod`.`acctOwnerId` AS `acctOwnerId`,
        GET_LOOKUP_VALUE(`aod`.`ownership`) AS `ownership`,
        CONCAT(`aod`.`firstName`,
                (CASE
                    WHEN
                        (ISNULL(`aod`.`midInitial`)
                            OR (TRIM(`aod`.`midInitial`) = ''))
                    THEN
                        ''
                    ELSE CONCAT(' ', `aod`.`midInitial`, '.')
                END),
                ' ',
                `aod`.`lastName`) AS `fullName`,
        `aod`.`firstName` AS `firstName`,
        `aod`.`midInitial` AS `midInitial`,
        `aod`.`lastName` AS `lastName`,
        `aod`.`ssn` AS `ssn`,
        `aod`.`dob` AS `dob`,
        `aod`.`phoneNumber` AS `phoneNumber`,
        `aod`.`phoneNumberNonUS` AS `phoneNumberNonUS`,
        `aod`.`secondPhoneNumber` AS `secondPhoneNumber`,
        `aod`.`secondPhoneNumberNonUS` AS `secondPhoneNumberNonUS`,
        `aod`.`emailAddress` AS `emailAddress`,
        `aod`.`physicalAddressStreet` AS `physicalAddressStreet`,
        `aod`.`physicalAddressCity` AS `physicalAddressCity`,
        `aod`.`physicalAddressState` AS `physicalAddressState`,
        `aod`.`physicalAddressZipCode` AS `physicalAddressZipCode`,
        `aod`.`mailingAddressStreet` AS `mailingAddressStreet`,
        `aod`.`mailingAddressCity` AS `mailingAddressCity`,
        `aod`.`mailingAddressState` AS `mailingAddressState`,
        `aod`.`mailingAddressZipCode` AS `mailingAddressZipCode`,
        GET_LOOKUP_VALUE(`aod`.`citizenshiId`) AS `citizenship`,
        `aod`.`countryOfCitizenship` AS `countryOfCitizenship`,
        `aod`.`countryOfDualCitizenship` AS `countryOfDualCitizenship`,
        `aod`.`countryOfBirth` AS `countryOfBirth`,
        `aod`.`isSPF` AS `isSPF`,
        `aod`.`spfDetail` AS `spfDetail`,
        `aod`.`isDirectorShareholder` AS `isDirectorShareholder`,
        `aod`.`directorShareholderDetail` AS `directorShareholderDetail`,
        `aod`.`bd` AS `bd`,
        `aod`.`bdDetail` AS `bdDetail`,
        `aod`.`ownershipPercent` AS `ownershipPercent`,
        'Y' as `feesCheck`
    FROM
        (`invdb`.`dc_acct_owners_details` `aod`
        JOIN `invdb`.`dc_acct_details` `ad`)
    WHERE
        ((`aod`.`acctnum` = `ad`.`acctnum`)
            AND (CASE
            WHEN (`ad`.`acctTypeId` NOT IN ('ACJOINT' , 'ACCSTD')) THEN (`aod`.`ownership` IN ('AOPRIMARY' , 'AOCUSTODIAN'))
            ELSE (`aod`.`ownership` IN ('AOCUSTODIAN' , 'AOJOINT', 'AOMINOR', 'AOPRIMARY'))
        END))
    ORDER BY `aod`.`acctnum`;
