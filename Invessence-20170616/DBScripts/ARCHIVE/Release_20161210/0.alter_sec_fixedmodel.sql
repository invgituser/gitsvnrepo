ALTER TABLE `invdb`.`sec_fixedmodel` 
CHANGE COLUMN `description` `description` VARCHAR(600) ;

update invdb.sec_fixedmodel
set description = 'Income Focus – clients typically have low tolerance for nominal principal fluctuations and a desire for maximum current cash flow from the portfolio.  Their total return expectations are low but income expectations are high.  The investment time horizon will tend to be shorter (under five years) as the need to maintain long term purchasing power is not a priority.'
where displayName = 'Income Focus';

update invdb.sec_fixedmodel
set description = 'Conservative Balanced – clients typically have a low tolerance for risk, and a moderate desire for cash flow from the portfolio.  Their total return expectations are modest.  The investment time horizon will tend to be moderate (five to ten years).  The client will accept the risk of nominal principal fluctuations to the extent needed in order to preserve purchasing power.'
where displayName = 'Conservative Balanced';

update invdb.sec_fixedmodel
set description = 'Balanced Return – clients typically have a moderate tolerance for risk, and a modest desire for some cash flow from the portfolio.  Their return expectations are realistic in that they will accept long term returns less than that as achieved by the equity markets.  The investment time horizon will tend to be moderate (ten to twenty years).  The client will accept risk to grow the portfolio in modest excess over inflation.'
where displayName = 'Balanced Return';

update invdb.sec_fixedmodel
set description = 'Balanced Opportunity – clients typically have a moderate long term tolerance for risk but an above average tolerance for short-term risk, and a limited desire for cash flow from the portfolio.  Their return expectations are high and consistent with long term equity market performance.  The fixed income portion of their allocation is more to provide stability rather than income.  The investment time horizon will tend to be long (ten years or generational).  The client will accept risk to grow the portfolio in excess of inflation.'
where displayName = 'Balanced Opportunity';

update invdb.sec_fixedmodel
set description = 'Capital Appreciation – clients typically have an above average tolerance for risk, and little to no cash flow needs from the portfolio.  Their return expectations are high and consistent with long term equity market performance.  Fixed income investment is viewed more as a tactical decision to enhance returns rather than for income or stability.  Investment time horizon is long term or generational.  The client will accept higher risks and volatility with the objective of performing in line with general market indices over long periods of time (10 years or more).'
where displayName = 'Capital Appreciation';
update invdb.sec_fixedmodel
set description = 'Aggressive Appreciation – clients typically have an above average tolerance for risk, and little to no cash flow needs from the portfolio.  Their return expectations are to meet or exceed the long term performance of the equity markets.  Fixed income investment is viewed more as a tactical decision to enhance returns rather than for income or stability.  Investment time horizon is long term or generational.  The client will accept higher risks and volatility with the objective of outperforming the general market indices over long periods of time (10 years or more).'
where displayName = 'Aggressive Appreciation';




