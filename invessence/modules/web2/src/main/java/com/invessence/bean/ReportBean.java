package com.invessence.bean;

import com.invessence.constant.Const;
import com.invessence.dao.ReportDAO;
import com.invessence.data.UserInfoData;
import com.invessence.util.UserValidation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/27/14
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "report")
@SessionScoped
public class ReportBean {
    UserValidation uv = new UserValidation();
    UserInfoData uid;

    @ManagedProperty("#{reportDAO}")
    ReportDAO rpdao;

    public ReportDAO getRpdao() {
        return rpdao;
    }

    public void setRpdao(ReportDAO rpdao) {
        this.rpdao = rpdao;
    }

    @PostConstruct
    public void init()
    {
        try
        {
            uv.validateSession();
            uid = (UserInfoData)  getCurrentInstance().getExternalContext().getSessionMap().get(Const.USER_INFO);
        }
        catch (Exception ex)
        {
            uv.redirect("/message.xhtml",null);
        }
    }

    public void collect(String reportname) {
        try {
            if (reportname != null) {
               rpdao.fetchReports(uid.getLogonID(),reportname);
            }
        }
        catch (Exception ex) {

        }

    }

}
