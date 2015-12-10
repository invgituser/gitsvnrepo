package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.dao.consumer.AggregationDAO;
import com.invessence.data.consumer.AggregationData;
import com.invessence.util.WebUtil;
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
                aggrData = aggregationDAO.loadDetailData(logonid);
                yodleeNavigation("dash");
            } else {
                yodleeNavigation("profile");
            }
        } catch (Exception e) {
            redirecttoErrorPage(null);
            e.printStackTrace();
        }
    }

    public void userUnRegistration() {
        System.out.println("userRegistration");
        Map<String, Object> result = null;
        try {
            if (logonid == null) {
                logonid = webutil.getLogonid();
            }
            result = yodleeAPIService.userUnRegistration(logonid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void yodleeNavigation(String pageId) {
        System.out.println("yodleeNavigation");
        Map<String, Object> result = null;
        try {
            if (pageId.equalsIgnoreCase("dash")) {
                displayDash();

            } else if (pageId.equalsIgnoreCase("acct")) {
                displayDash();
            } else if (pageId.equalsIgnoreCase("profile")) {

            } else if (pageId.equalsIgnoreCase("aggr")) {
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
            aggrData = aggregationDAO.loadDetailData(logonid);
            //return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // webutil.showMessage("", "Success", "Accounts data sucessfully refreshed!");
        //return "yDash";
    }

    List<ConsolidateData> consDataList;

    public List<ConsolidateData> getConsDataList() {
        consDataList = new ArrayList<ConsolidateData>();
        if (logonid == null) {
            logonid = webutil.getLogonid();
        }
        consDataList = (List<ConsolidateData>) yodleeAPIService.getUserAccountsDetail(logonid).get("consDataList");
        //System.out.println(consDataList.size()+" LIST SIZE");
        return consDataList;
    }

    List<Map<String, ConsolidateData>> consDataMapList;

    public List<Map<String, ConsolidateData>> getconsDataMapList() {
        consDataMapList = new ArrayList<Map<String, ConsolidateData>>();
        if (logonid == null) {
            logonid = webutil.getLogonid();
        }
        List<ConsolidateData> consDataLst = (List<ConsolidateData>) yodleeAPIService.getUserAccountsDetail(logonid).get("consDataList");
        Iterator<ConsolidateData> iterator = consDataLst.iterator();

        while (iterator.hasNext()) {
            ConsolidateData cd = (ConsolidateData) iterator.next();

        }
        return consDataMapList;
    }


    public void setConsDataList(List<ConsolidateData> consDataList) {
        this.consDataList = consDataList;
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
            if (result.get("errorDetails") != null) {
                YodleeError ye = (YodleeError) result.get("errorDetails");
                addMessage(ye.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return result;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
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
        if (yodleeCharts == null) {
            yodleeCharts = new YodleeCharts();
        }
        if (aggrData != null && aggrData.getTotalLevelArray().size() > 0)
            yodleeCharts.createPieModel(aggrData.getTotalLevelArray());
        else
            yodleeCharts.createPieModel(null);
    }

    private void displayAggr() {
        if (yodleeCharts == null) {
            yodleeCharts = new YodleeCharts();
        }
        if (aggrData != null && aggrData.getTotalAssetArray().size() > 0)
            yodleeCharts.createBarModel(aggrData.getTotalAssetArray());
        else
            yodleeCharts.createBarModel(null);
    }

    public void redirecttoErrorPage(YodleeError errorInfo) {

    }


}
