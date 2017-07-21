# Script for assigning developer role to qa USER

use invdb;

insert into invdb.role(logonid,role,status)
select logonid,'Developer','A' from invdb.user_logon where userid like '%qa.user%';