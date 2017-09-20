package com.invessence.aggr.dao;

import java.util.List;
import com.invessence.aggr.model.MXAudit;

public interface MXAuditsDAO {

	public MXAudit insertMXAudits(MXAudit mxAudit);
	
	public MXAudit updateMXAudits(MXAudit mxAudit);
	
	public MXAudit deleteMXAudits(MXAudit mxAudit);
	
	public List<MXAudit> getMXAuditsList();
	
	public MXAudit findByPK(Long Id);
	
	public List<MXAudit> findByWhereCluase(String where, Object[] values);
	
	public List<MXAudit> findByWhereCluase(String where);
}
