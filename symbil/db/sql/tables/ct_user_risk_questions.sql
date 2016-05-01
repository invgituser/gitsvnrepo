DROP TABLE `user_risk_questions`;

CREATE TABLE `user_risk_questions` (
  `acctnum` bigint(20) NOT NULL,
  `ans1` tinyint,
  `ans2` tinyint,
  `ans3` tinyint,
  `ans4` tinyint,
  `ans5` tinyint,
  `ans6` tinyint,
  `ans7` tinyint,
  `ans8` tinyint,
  `ans9` tinyint,
  `ans10` tinyint,
  `ans11` tinyint,
  `ans12` tinyint,
  `ans13` tinyint,
  `ans14` tinyint,
  `ans15` tinyint,
  `created` date,
  `lastUpdated` date,
  PRIMARY KEY (`acctnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

