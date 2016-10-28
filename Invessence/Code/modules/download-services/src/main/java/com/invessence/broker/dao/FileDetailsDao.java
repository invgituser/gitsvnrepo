package com.invessence.broker.dao;

import java.util.*;

import com.invessence.broker.bean.DownloadFileDetails;

public interface FileDetailsDao {
	public List<DownloadFileDetails> findByWhere(String where);
}
