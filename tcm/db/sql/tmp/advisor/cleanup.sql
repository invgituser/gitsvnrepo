			delete from user_risk_questions
			where acctnum >= 191;

			delete from user_trade_profile
			where acctnum >= 191;

			delete from asset_alloc
			where acctnum >= 191;
			
			delete from virtual_portfolio
			where acctnum >= 191;

			delete from user_access_role
			where acctnum >= 191;

			commit;
