update invdb.user_basket_access
set theme = replace (theme, 'BB', 'TA')
where advisor = 'BB-TCM' and theme like '%BB';

update invdb.web_site_info
set value = '0.TA'
where url in ('traditionadvisers', 'demo', 'localhost', 'uattcm')
AND name = 'DEFAULT.MODEL';
