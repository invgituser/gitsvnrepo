## Create Procedure rbsa.p_genDate

USE rbsa;
DROP procedure IF EXISTS p_genDate;

DELIMITER $$
USE rbsa$$
CREATE PROCEDURE p_genDate(p_fromdt varchar(10),p_todt varchar(10))
begin
declare p_previousbdate varchar(10);

set p_previousbdate='';
if(weekday(p_fromdt) <5) then 

select case dayofweek(p_fromdt - interval 1 day) when 1 then p_fromdt - interval 3 day when 7 then p_fromdt- interval 2 day 
else p_fromdt - interval 1 day end into p_previousbdate;

insert into invdb.inv_date_table(businessdate,prev_businessdate) values (p_fromdt,p_previousbdate);
end if;
while(p_fromdt<p_todt) do

if(weekday(p_fromdt)<5) then 
set p_previousbdate=p_fromdt;
end if;

set p_fromdt=date_add(p_fromdt,interval 1 day);
if(weekday(p_fromdt) <5) then 
insert into invdb.inv_date_table(businessdate,prev_businessdate) values (p_fromdt,p_previousbdate);
end if;

end while;

-- if(weekday(p_todt) <5) then 
-- insert into rbsa.date_table_1(businessdate,previousbdate) values (p_todt,p_previousbdate);
-- end if;
end$$

DELIMITER ;