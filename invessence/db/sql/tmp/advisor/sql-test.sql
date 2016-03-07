select * from user_logon
where email like 'test%'

select * from user_access_role
where logonid = 62

update user_access_role
set role = 'ADVISOR'
where logonid = 62

select * from IB_Accounts

select * from user_access_role
where acctnum in (select acctnum from user_access_role where logonid = 62)
order by acctnum, logonid

call sel_AdvisorAcctList(62,'All')

UPDATE `invdb`.`user_logon` 
SET `logonstatus`='A', `resetID`=null 
WHERE `logonid`in (62, 136, 63);

UPDATE `invdb`.`user_logon` 
SET `logonstatus`='A', `accttype`='Admin' 
WHERE `logonid`in (63);

call sel_AdvisorBaskets('Sam.McPherson');