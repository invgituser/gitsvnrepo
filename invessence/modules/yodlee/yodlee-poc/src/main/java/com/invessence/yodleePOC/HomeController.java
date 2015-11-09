package com.invessence.yodleePOC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.invessence.yodlee.model.UserLogon;
import com.invessence.yodlee.service.YodleeAPIService;
import com.invessence.yodlee.util.YodleeAPI;

@Controller
public class HomeController {
	
	@Autowired YodleeAPIService yodleeAPIService;
	
	HttpSession session;
	
	
	@RequestMapping(value="/")
	public ModelAndView home(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView("home");
		//List<UserLogon> listUsers = (List<UserLogon>) yodleeAPIService.getUserRegistrationList().get("userList");
		List<UserLogon> listUsers = (List<UserLogon>) yodleeAPIService.getInvUserList().get("userList");
		model.addObject("userList", listUsers);
		System.out.println(listUsers.size()+": List Size");

		try{ 	
			System.out.println("HomeController.home()");
//			Map<String, Object> resultMap=yodleeAPIService.advisorLogin();
			
//			session=request.getSession();
//			session.setAttribute("cobrandSessionToken", resultMap.get("cobrandSessionToken"));
//			System.out.println("From Session cobrandSessionToken:"+session.getAttribute("cobrandSessionToken"));		
		}catch(Exception e){
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value="/registration")
	public @ResponseBody String registration(HttpServletRequest request) {
		
		Gson g=null;
		Map<String, Object> resultMap=null;
		
		try{ 	
			System.out.println("HomeController.registration()");
			resultMap=yodleeAPIService.userRegistration(Long.valueOf(request.getParameter("invUserId").toString()));

			resultMap.put("resultMap", resultMap);
			g=new Gson();
		}catch(Exception e){
			e.printStackTrace();
		}
		return g.toJson(resultMap);
	}
	
	@RequestMapping(value="/unRegistration")
	public ModelAndView unRegistration(HttpServletRequest request) {
		
		Map<String, Object> resultMap=null;
		ModelAndView model = new ModelAndView("home");
		
		
		try{ 	
			System.out.println("HomeController.registration()");
			resultMap=yodleeAPIService.userUnRegistration(Long.valueOf(request.getParameter("invUserId").toString()));

			List<UserLogon> listUsers = (List<UserLogon>) yodleeAPIService.getUserRegistrationList().get("userList");
			model.addObject("userList", listUsers);
			model.addObject("resultMap", resultMap);
			System.out.println(listUsers.size()+": List Size");

		}catch(Exception e){
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value="/login")
	public @ResponseBody String login(HttpServletRequest request) {
		
		Gson g=null;
		Map<String, Object> resultMap=null;
		try{
			System.out.println("HomeController.login()");
			System.out.println(request.getParameter("userId")+" : User ID");
			
			resultMap=yodleeAPIService.userLogin(Long.valueOf(request.getParameter("invUserId").toString()));
		
			//JSONObject userConvCreds = jb.getJSONObject("userContext").getJSONObject("conversationCredentials");
			session=request.getSession();
			session.setAttribute("userSessionToken",resultMap.get("userSessionToken"));
			session.setAttribute("userDetails", resultMap.get("userDetails"));
			//new YodleeAPI().addSiteAccount(session.getAttribute("cobrandSessionToken").toString(),session.getAttribute("userSessionToken").toString());
			System.out.println("From Session cobrandSessionToken:"+session.getAttribute("cobrandSessionToken")+" & userSessionToken:"+session.getAttribute("userSessionToken"));
			g=new Gson();
		}catch(Exception e){
			e.printStackTrace();
		}
		return g.toJson(resultMap);
	}
	
	
	
	@RequestMapping(value="/getAllSiteAccounts")
	public @ResponseBody String getAllSiteAccounts(HttpServletRequest request) {
		Gson g=null;
		Map<String, Object> resultMap=null;
		try{
			System.out.println("HomeController.getAllSiteAccounts()");
			//jb=new YodleeAPI().getAllSiteAccounts(session.getAttribute("cobrandSessionToken").toString(),session.getAttribute("userSessionToken").toString());
			
			System.out.println("invUserId :"+request.getParameter("invUserId").toString());
			
			resultMap=yodleeAPIService.getAllSiteAccounts(Long.valueOf(request.getParameter("invUserId").toString()));
			System.out.println("***********************************************");
			g=new Gson();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return g.toJson(resultMap);
	}
	
	
	@RequestMapping(value="/getItemSummariesForSite")
	public @ResponseBody String getItemSummariesForSite(HttpServletRequest request) {
		Gson g=null;
		Map<String, Object> resultMap=null;
		try{
			System.out.println("HomeController.getItemSummariesForSite()");
			resultMap=yodleeAPIService.getItemSummariesForSite(request.getParameter("siteAccId"), Long.valueOf(request.getParameter("invUserId").toString()));		
			
			g=new Gson();
		}catch(Exception e){
			e.printStackTrace();
		}
		return g.toJson(resultMap);
		/*JSONArray jb=null;
		try{
		
			System.out.println("HomeController.getPopularSites()");
			jb=yodleeAPIService.getItemSummariesForSite(request.getParameter("siteAccId"), Long.valueOf(request.getParameter("invUserId").toString()),"Asach");
				
		}catch(Exception e){
			e.printStackTrace();
		}
		return jb.toString();*/
		
	}
	
	@RequestMapping(value="/getTokan")
	public @ResponseBody String getTokan(HttpServletRequest request) {
		Gson g=null;
		Map<String, Object> resultMap=null;
		try{
		
			resultMap=yodleeAPIService.getFastLinkDetails(request.getParameter("operation"),
					request.getParameter("siteAccId"),Long.valueOf(request.getParameter("invUserId").toString()));	
			
			
			System.out.println("HomeController.getTokan()");
			//jb=new YodleeAPI().getToken(session.getAttribute("cobrandSessionToken").toString(),session.getAttribute("userSessionToken").toString(),BRIDGE_APP_ID);
			System.out.println("From Session cobrandSessionToken:"+session.getAttribute("cobrandSessionToken")+" & userSessionToken:"+session.getAttribute("userSessionToken"));
		
			g=new Gson();
		}catch(Exception e){
			e.printStackTrace();
		}
		return g.toJson(resultMap);
	}
	
	@RequestMapping(value="/getPopularSites")
	public @ResponseBody String getPopularSites(HttpServletRequest request) {
		JSONArray jb=null;
		try{
		
			System.out.println("HomeController.getPopularSites()");
			jb=new YodleeAPI().getPopularSites(session.getAttribute("cobrandSessionToken").toString(),session.getAttribute("userSessionToken").toString());
			System.out.println("From Session cobrandSessionToken:"+session.getAttribute("cobrandSessionToken")+" & userSessionToken:"+session.getAttribute("userSessionToken"));
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return jb.toString();
	}

	@RequestMapping(value="/getAllSites")
	public @ResponseBody String getAllSites(HttpServletRequest request) {
		JSONArray jb=null;
		try{
			System.out.println("HomeController.getAllSites()");
			jb=new YodleeAPI().getAllSites(session.getAttribute("cobrandSessionToken").toString(),session.getAttribute("userSessionToken").toString());
					
			System.out.println("From Session cobrandSessionToken:"+session.getAttribute("cobrandSessionToken")+" & userSessionToken:"+session.getAttribute("userSessionToken"));

		}catch(Exception e){
			e.printStackTrace();
		}
		return jb.toString();
	}
	
	
	public YodleeAPIService getYodleeAPIService() {
		return yodleeAPIService;
	}

	public void setYodleeAPIService(YodleeAPIService yodleeAPIService) {
		this.yodleeAPIService = yodleeAPIService;
	}
	
	
	
	
}