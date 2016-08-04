package com.invessence.web.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.invessence.web.dao.consumer.AggregationDAO;
import com.invessence.web.data.consumer.AggregationData;
import com.invessence.web.util.*;
import com.invessence.yodlee.model.*;
import com.invessence.yodlee.service.YodleeAPIService;
import org.primefaces.context.RequestContext;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/10/15
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "yodleeBean")
@SessionScoped
public class YodleeBean implements Serializable {
    private Long logonid;
    private String controlURL;

    private enum Severity {
        INFO, WARNING, ERROR
    }

    private AggregationData aggrData;

    @ManagedProperty("#{yodleeAPIService}")
    YodleeAPIService yodleeAPIService;

    public YodleeAPIService getYodleeAPIService() {
        return yodleeAPIService;
    }

    public void setYodleeAPIService(YodleeAPIService yodleeAPIService) {
        this.yodleeAPIService = yodleeAPIService;
    }

    @ManagedProperty("#{webutil}")
    private WebUtil webutil;

    public void setWebutil(WebUtil webutil) {
        this.webutil = webutil;
    }

    @ManagedProperty("#{aggregationDAO}")
    private AggregationDAO aggregationDAO;

    public void setAggregationDAO(AggregationDAO aggregationDAO) {
        this.aggregationDAO = aggregationDAO;
    }

    @ManagedProperty("#{yodleeCharts}")
    private YodleeCharts yodleeCharts;

    public void setYodleeCharts(YodleeCharts yodleeCharts) {
        this.yodleeCharts = yodleeCharts;
    }

    public WebUtil getWebutil() {
        return webutil;
    }

    public Long getLogonid() {
        return logonid;
    }

    public void setLogonid(Long logonid) {
        this.logonid = logonid;
    }

    public AggregationData getAggrData() {
        return aggrData;
    }

    public YodleeCharts getYodleeCharts() {
        return yodleeCharts;
    }

    public String getControlURL() {
        return controlURL;
    }

    public void startup() {
        System.out.println("startup");
        // System.out.print(yodleeAPIService.getInvUserList());
        try {
            if (logonid == null) {
                logonid = webutil.getLogonid();
            }
            if (isUserRegisteredAtYodlee()) {
                yodleeNavigation("dash");
            } else {
                yodleeNavigation("profile");
            }
        } catch (Exception e) {
            redirecttoErrorPage(null);
            e.printStackTrace();
        }
    }

    private void loadData(Long logonid) {
        aggrData = aggregationDAO.loadDetailData(logonid);
        aggrData.addTotal();

    }

    public void userUnRegistration() {
        System.out.println("userRegistration");
        Map<String, Object> result = null;
        try {
            if (logonid == null) {
                logonid = webutil.getLogonid();
            }
            result = yodleeAPIService.userUnRegistration(logonid);
            if (result != null && result.get("errorDetails") != null) {
                YodleeError ye = (YodleeError) result.get("errorDetails");
                promptMessage(Severity.ERROR, ye.getMessage());
            }
            else {
                promptMessage(Severity.INFO, "Successfully, unregisted.  ALL data will be purged in 24 hours.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void yodleeNavigation(String pageId) {
        System.out.println("yodleeNavigation");
        Map<String, Object> result = null;
        System.out.println("We are here");
        try {
            if (pageId.equalsIgnoreCase("dash")) {
                displayDash();

            } else if (pageId.equalsIgnoreCase("acct")) {
                displayDash();
            } else if (pageId.equalsIgnoreCase("profile")) {

            } else if (pageId.equalsIgnoreCase("investment")) {
                displayAggr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        webutil.redirect("/pages/consumer/aggr/" + pageId + ".xhtml", null);
    }

    public void refreshAccountsData() {
        System.out.println("refreshAccountsData");
        Map<String, Object> result = null;
        try {
            if (logonid == null) {
                logonid = webutil.getLogonid();
            }
            yodleeAPIService.refreshUserAccDetails(logonid);
            displayDash();  // Refresh Charts on Dashboard.
            //return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // webutil.showMessage("", "Success", "Accounts data sucessfully refreshed!");
        //return "dash";
    }

    public void userRegistration() {
        System.out.println("userRegistration");
        Map<String, Object> result = null;
        try {
            if (logonid == null) {
                logonid = webutil.getLogonid();
            }
            result = yodleeAPIService.userRegistration(logonid);
            //return result;
            if (result != null && result.get("errorDetails") != null) {
                YodleeError ye = (YodleeError) result.get("errorDetails");
                promptMessage(Severity.ERROR, ye.getMessage());
            }
            else {
                promptMessage(Severity.INFO, "Successfully, registed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return result;
    }

    public void promptMessage(Severity severity, String summary) {
        FacesMessage message = null;
        switch (severity) {
            case INFO:
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
                break;
            case WARNING:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null);
                break;
            case ERROR:
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
                break;
            default:
        }
        if (message != null)
            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addAcount(String operation, String siteId) {
        System.out.println("userRegistration");
        Map<String, Object> result = null;
        try {
            if (logonid == null) {
                logonid = webutil.getLogonid();
            }
            result = yodleeAPIService.getFastLinkDetails(operation, siteId, logonid);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            Map<String, String> flDetails = (Map<String, String>) result.get("flDetails");
            int i = 100;

            // requestContext.execute("abhangCall("+flDetails.get("OAUTH_TOKEN")+","+ flDetails.get("OAUTH_TOKEN_SECRET")+","+ flDetails.get("APPLICATION_KEY")+","+ flDetails.get("APPLICATION_TOKEN")+","+ flDetails.get("FL_API_URL")+","+ flDetails.get("FL_API_PARAM")+")");
            requestContext.execute("getFastLinkUrl('" + flDetails.get("OAUTH_TOKEN") + "','"
                    + flDetails.get("OAUTH_TOKEN_SECRET") + "','"
                    + flDetails.get("APPLICATION_KEY") + "','"
                    + flDetails.get("APPLICATION_TOKEN") + "','"
                    + flDetails.get("FL_API_URL") + "','"
                    + flDetails.get("FL_API_PARAM") + "')");
            //return result;
            System.out.println("getFastLinkUrl('" + flDetails.get("OAUTH_TOKEN") + "','"
                    + flDetails.get("OAUTH_TOKEN_SECRET") + "','"
                    + flDetails.get("APPLICATION_KEY") + "','"
                    + flDetails.get("APPLICATION_TOKEN") + "','"
                    + flDetails.get("FL_API_URL") + "','"
                    + flDetails.get("FL_API_PARAM") + "')");
            //requestContext.execute("confirmDelete('"+i+"');");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return result;
    }

    public Boolean isUserRegisteredAtYodlee() {
        try {
            if (logonid == null || logonid == 0L) {
                return false;
            }

            if (yodleeAPIService == null)
                return false;

            Map<String, Object> result = yodleeAPIService.userLogin(logonid);
            if (result == null) {
                return true;
            }
            if (result.containsKey("errorDetails")) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    private void displayDash() {
       loadData(logonid);
        if (yodleeCharts == null) {
            yodleeCharts = new YodleeCharts();
        }
        if (aggrData != null && aggrData.getTotalTypeArray().size() > 0)
            yodleeCharts.createTypeModel(aggrData.getTotalTypeArray());
        else
            yodleeCharts.createTypeModel(null);
    }

    private void displayAggr() {
       loadData(logonid);
        if (yodleeCharts == null) {
            yodleeCharts = new YodleeCharts();
        }
        if (aggrData != null && aggrData.getTotalAssetMap() != null && aggrData.getTotalAssetMap().size() > 0)
            yodleeCharts.createAssetModel(aggrData.getTotalAssetMap(), aggrData.getAssetMasterMap());
        else
            yodleeCharts.createAssetModel(null, null);
    }

    public void redirecttoErrorPage(YodleeError errorInfo) {

    }

    public void onCompleteEvent(AjaxBehaviorEvent event) {
        return;
    }


}
