/**
 * AuthenticateLogin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.aggr.bean;

import java.io.Serializable;

public class Credentials implements Serializable
{
    private String userId;
    private String password;
    private String companyName;

    public Credentials(String userId, String password, String companyName)
    {
        this.userId = userId;
        this.password = password;
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
