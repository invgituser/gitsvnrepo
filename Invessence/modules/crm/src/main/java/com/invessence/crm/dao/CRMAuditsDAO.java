package com.invessence.crm.dao;

import java.util.List;
import com.invessence.crm.model.RedTailAudit;

public interface CRMAuditsDAO
{

	public RedTailAudit insertRedTailAudits(RedTailAudit redTailAudit);
	
	public RedTailAudit updateRedTailAudits(RedTailAudit redTailAudit);
	
	public RedTailAudit deleteRedTailAudits(RedTailAudit redTailAudit);
	
	public List<RedTailAudit> getRedTailAuditsList();
	
	public RedTailAudit findByPK(Long Id);
	
	public List<RedTailAudit> findByWhereCluase(String where, Object[] values);
	
	public List<RedTailAudit> findByWhereCluase(String where);
}
