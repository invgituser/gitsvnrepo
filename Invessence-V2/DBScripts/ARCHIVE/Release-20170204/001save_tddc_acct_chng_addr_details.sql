drop procedure if exists invdb.save_tddc_acct_chng_addr_details;
delimiter $$
CREATE PROCEDURE invdb.save_tddc_acct_chng_addr_details(
		p_acctnum	bigint(20)
	,	p_acctOwnerId	int(2)
    ,	p_physicalAddressStreetHstry	varchar(45)
	,	p_physicalAddressCityHstry	varchar(45)
	,	p_physicalAddressStateHstry	varchar(45)
	,	p_physicalAddressZipCodeHstry	varchar(45)
	,	p_mailingAddressStreetHstry	varchar(45)
	,	p_mailingAddressCityHstry	varchar(45)
	,	p_mailingAddressStateHstry	varchar(45)
	,	p_mailingAddressZipCodeHstry	varchar(45)
	,	p_phoneNumberHstry	varchar(45)
	,	p_emailAddressHstry	varchar(45)
	,	p_physicalAddressStreet	varchar(45)
	,	p_physicalAddressCity	varchar(45)
	,	p_physicalAddressState	varchar(45)
	,	p_physicalAddressZipCode	varchar(45)
	,	p_mailingAddressStreet	varchar(45)
	,	p_mailingAddressCity	varchar(45)
	,	p_mailingAddressState	varchar(45)
	,	p_mailingAddressZipCode	varchar(45)
	,	p_phoneNumber	varchar(45)
	,	p_emailAddress	varchar(45)
	,	p_createdBy	varchar(45)
  )
BEGIN

INSERT INTO dc_acct_owners_details_history
			(acctnum,
			acctOwnerId,
			physicalAddressStreet,
			physicalAddressCity,
			physicalAddressState,
			physicalAddressZipCode,
			mailingAddressStreet,
			mailingAddressCity,
			mailingAddressState,
			mailingAddressZipCode,
            phoneNumber,
            emailAddress,
			created,
			createdBy,
			updated,
			updatedBy,
            ownership,
			firstName,
			midInitial,
			lastName,
			ssn,
			dob,
			phoneNumberNonUS,
			secondPhoneNumber,
			secondPhoneNumberNonUS,
		citizenshiId,
countryOfCitizenship,
countryOfDualCitizenship,
countryOfBirth,
isSPF,
spfDetail,
isDirectorShareholder,
directorShareholderDetail,
bd,
bdDetail,
ownershipPercent)
				select
					p_acctnum
				,	p_acctOwnerId
				,	p_physicalAddressStreetHstry
				,	p_physicalAddressCityHstry
				,	p_physicalAddressStateHstry
				,	p_physicalAddressZipCodeHstry
				,	p_mailingAddressStreetHstry
				,	p_mailingAddressCityHstry
				,	p_mailingAddressStateHstry
				,	p_mailingAddressZipCodeHstry
                ,	p_phoneNumberHstry
				,   p_emailAddressHstry
				,	now()
				,	p_createdBy
				,	null
				,	null,
ownership,
firstName,
midInitial,
lastName,
ssn,
dob,
phoneNumberNonUS,
secondPhoneNumber,
secondPhoneNumberNonUS,
citizenshiId,
countryOfCitizenship,
countryOfDualCitizenship,
countryOfBirth,
isSPF,
spfDetail,
isDirectorShareholder,
directorShareholderDetail,
bd,
bdDetail,
ownershipPercent
from dc_acct_owners_details where acctnum=p_acctnum;

	  update dc_acct_owners_details set  physicalAddressStreet=p_physicalAddressStreet,
      physicalAddressCity=p_physicalAddressCity,physicalAddressState=p_physicalAddressState,
      physicalAddressZipCode=p_physicalAddressZipCode,mailingAddressStreet=p_mailingAddressStreet,
      mailingAddressCity=p_mailingAddressCity,mailingAddressState=p_mailingAddressState,
      mailingAddressZipCode=p_mailingAddressZipCode,phoneNumber=p_phoneNumber,
      emailAddress=p_emailAddress,updatedBy=p_createdBy,updated=now()  where acctnum=p_acctnum and acctOwnerId=p_acctOwnerId;
END