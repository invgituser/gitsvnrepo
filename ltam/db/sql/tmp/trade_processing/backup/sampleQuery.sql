select ib.acctnum, ib.lastname, ib.firstname, p.clientAccountID, sec.assetclass, 
sum(positionValue) as pos, 
case when (nav.total = 0)
	then 0
else sum(positionValue)/nav.total 
end as pos_percent, 
sum(investmentvalue) as origpos, 
case when (nav.total = 0)
	then 0
else sum(investmentvalue)/nav.total 
end as origpos_percent
from position p,
IB_Accounts ib,
sec_master sec,
virtual_portfolio vp,
nav_daily nav
where p.ReportDate = (select value from invessence_switch where name = 'BROKER_BDATE')
and ib.IB_acctnum = p.clientAccountID
and ib.accountStatus in ('ACTIVE')
and sec.ticker = p.symbol
and vp.acctnum = ib.acctnum
and vp.ticker = sec.ticker
and sec.ticker not in ('CASH')
and nav.clientAccountID = ib.IB_acctnum
and nav.reportDate = (select value from invessence_switch where name = 'BROKER_BDATE')
group by ib.acctnum, ib.lastname, ib.firstname, p.clientAccountID, sec.assetclass, nav.total 
having 
abs(case when (nav.total = 0)
	then 0
else sum(positionValue)/nav.total 
end - 
case when (nav.total = 0)
	then 0
else sum(investmentvalue)/nav.total 
end
)
> .05


select ib.acctnum, sec.assetclass, 
sum(investmentvalue) as origpos
from 
IB_Accounts ib,
sec_master sec,
virtual_portfolio vp
where
upper(ib.accountStatus) in (upper('ACTIVE'))
and vp.acctnum = ib.acctnum
and vp.ticker = sec.ticker
group by ib.acctnum, sec.assetclass

select * from IB_Accounts ib
where upper(ib.accountStatus) in (upper('ACTIVE'))