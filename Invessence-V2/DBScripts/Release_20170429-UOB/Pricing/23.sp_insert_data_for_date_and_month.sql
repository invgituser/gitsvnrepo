## Working Day generation Queries

SET SQL_SAFE_UPDATES = 0;

##

delete from invdb.inv_date_table;

##

call rbsa.p_genDate('1970-01-01','2021-01-01');

##

delete from invdb.inv_monthly_date_table;

##

insert  into  invdb.inv_monthly_date_table(businessdateYYYYMM,first_businessdate,last_businessdate,prev_last_bdate) select 
DATE_FORMAT(idt.businessdate, '%Y-%m'),
min(idt.businessdate),
max(idt.businessdate),
(select max(idt3.businessdate) from invdb.inv_date_table idt3 where idt3.businessdate<idt.businessdate ) as 'premax'
from invdb.inv_date_table idt 
GROUP BY DATE_FORMAT(idt.businessdate, '%Y-%m');

##

call rbsa.p_genMonthTable();

##

SET SQL_SAFE_UPDATES = 1;

##

select 'Date data generation competed';

##