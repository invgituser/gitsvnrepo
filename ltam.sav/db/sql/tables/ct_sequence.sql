delimiter $$

CREATE TABLE `sequence` (
  `seqkey` varchar(10) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`seqkey`),
  UNIQUE KEY `seq_key` (`seqkey`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8
$$

