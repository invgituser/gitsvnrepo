package com.invessence.data;

public class RoleData {
	
	private long useracct = 0;
	private String role = null;
    private int functionid = 0;
	private int userlogon = 0;
	private String privilege = null;


	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


    public long getUseracct() {
        return useracct;
    }
    public void setUseracct(long useracct) {
        this.useracct = useracct;
    }

    public int getFunctionid() {
        return functionid;
    }
    public void setFunctionid(int functionid) {
        this.functionid = functionid;
    }

    public int getUserlogon() {
        return userlogon;
    }
    public void setUserlogon(int userlogon) {
        this.userlogon = userlogon;
    }

    public String getPrivilege() {
        return privilege;
    }
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

}
