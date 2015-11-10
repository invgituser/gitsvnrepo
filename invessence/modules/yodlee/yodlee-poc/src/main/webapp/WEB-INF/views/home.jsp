<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<script type="text/javascript" language="javascript" src="/yodlee-poc/resources/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/yodlee-poc/resources/css/colorbox.css">  
<link rel="stylesheet" href="/yodlee-poc/resources/css/main.css">      	
<script src="/yodlee-poc/resources/js/oauth.js"></script>	
<script src="/yodlee-poc/resources/js/sha1.js"></script>		
<script src="/yodlee-poc/resources/js/jquery.colorbox.js"></script> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <script type="text/javascript">

        
        function registration(invUserId){
        	//alert(username+" : "+password+" : "+userId);
        	 $.ajax({
        	  type: "post",
        	  url: "registration",
        	  cache: false,  
        	  data:{invUserId:invUserId},
        	  success: function(response){
        	  //alert(response);
        	  $('#FASTLINKURL').val(response);
        	   var obj = JSON.parse(response);
        	  //alert(obj.userDetails);
        	   if(obj.errorDetails!=null){        		   
        		   alert(obj.errorDetails.message);
        	   }else{
            	   var DYN_HTML_TBODY="";
	        	   $('#userDetails').html("<B><U>User Details</U></B>" + 
	        			   "</br>Login Name:- " + obj.userDetails.id +
	        			   //"</br>User Id:- " + obj.userId  + 
	        			   "</br>Email:- " + obj.userDetails.email);//+ 
	        			  // "</br>User Type:- " + obj.userType.userTypeName);
	        			   DYN_HTML_TBODY+="<B><U>User Operation</U></B>"+
	        			   //"</br><a href='#' onclick='getAllSites()'>getAllSites</a>"+
	        			   "</br><a href='#' onclick='getAllSiteAccounts(\""+invUserId+"\")'>All Site Accounts</a>"+
	        			   "</br><a href='#' onclick='getTokan(\"ADD_ACC\",\"\",\""+invUserId+"\")'>FastLink API</a>"+
	        			   "</br><a href='unRegistration?userId="+invUserId+"' onclick='return confirm(\"Are you sure?\")'>Unregister</a>";
	        			   //alert(DYN_HTML_TBODY);
	        		$('#userOperations').html(DYN_HTML_TBODY);	  
	        		$('#oerationDetails').html("");
	      	      	$('#getItemSummariesForSiteRes').html("");
	        	  }
        	  },
        	  error: function(){      
        	   alert('Error while request..');
        	  }
        	 });
        	}
        
        
        function login(invUserId){
        	//alert(username+" : "+password+" : "+userId);
        	 $.ajax({
        	  type: "post",
        	  url: "login",
        	  cache: false,  
        	  data:{invUserId:invUserId},
        	  success: function(response){
        	  //alert(response);
        	  $('#FASTLINKURL').val(response);
        	  $('#userDetails').html("");
        	   var obj = JSON.parse(response);
        	  //alert(obj.userDetails);
        	   if(obj.errorDetails!=null){        		
        		   $('#userOperations').html("");	  
	        		$('#oerationDetails').html("");
	      	      	$('#getItemSummariesForSiteRes').html("");
        		   alert(obj.errorDetails.message);
        	   }else{
	        	   var DYN_HTML_TBODY="";
	        	   $('#userDetails').html("<B><U>User Details</U></B>" + 
	        			   "</br>Login Name:- " + obj.userDetails.userId +
	        			   //"</br>User Id:- " + obj.userId  + 
	        			   "</br>Email:- " + obj.userDetails.email);//+ 
	        			  // "</br>User Type:- " + obj.userType.userTypeName);
	        			   DYN_HTML_TBODY+="<B><U>User Operation</U></B>"+
	        			   //"</br><a href='#' onclick='getAllSites()'>getAllSites</a>"+
	        			   "</br><a href='#' onclick='getAllSiteAccounts(\""+invUserId+"\")'>All Site Accounts</a>"+
	        			   "</br><a href='#' onclick='getTokan(\"ADD_ACC\",\"\",\""+invUserId+"\")'>FastLink API</a>"+
	        			   "</br><a href='unRegistration?invUserId="+invUserId+"' onclick='return confirm(\"Are you sure?\")'>Unregister</a>";
	        			   //alert(DYN_HTML_TBODY);
	        		$('#userOperations').html(DYN_HTML_TBODY);	  
	        		$('#oerationDetails').html("");
	      	      	$('#getItemSummariesForSiteRes').html("");
        	   }
        	  },
        	  error: function(){      
        	   alert('Error while request..');
        	  }
        	 });
        	}
        
        
					
        function getAllSiteAccounts(invUserId){
         	 $.ajax({
         	  type: "post",
         	  url: "getAllSiteAccounts",
         	  cache: false,  
         	  data:{invUserId:invUserId},
         	  success: function(response){
         	  //alert(response);
         	 $('#FASTLINKURL').val(response);
         	  $('#oerationDetails').html("");
         	   var obj = JSON.parse(response);
         	  if(obj.errorDetails!=null){        		
         		 $('#oerationDetails').html("");
         		$('#getItemSummariesForSiteRes').html("");
       		   alert(obj.errorDetails.message);
       	   }else{
         		var DYN_HTML_TBODY="";
         		DYN_HTML_TBODY+="<B><U>All Site Accounts</U></B>";
	  	       	$.each(obj.siteDetails, function( index, value ) {
	  				DYN_HTML_TBODY+="</br><a href='#' onclick='getItemSummariesForSite("+value.siteAccId+","+invUserId+")'>"+value.siteName+"</a> ("+value.siteAccId+","+value.siteId+")  <a href='#' onclick='getTokan(\"EDIT_ACC\",\""+value.siteAccId+"\",\""+invUserId+"\")'>Edit</a>   <a href='#' onclick='getTokan(\"REFRESH_ACC\",\""+value.siteAccId+"\",\""+invUserId+"\")'>Refresh</a>";
	  				$.each(value.itemDetails, function( index, value1 ) {
	  	  				DYN_HTML_TBODY+="</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+value1.itemName+" ("+value1.itemId+","+value.siteId+")";
	  	  			});				
	  			});
	         	   
	         	   //$('#getPopularSitesRes').html("First Name:- " + obj.lastLoginTime +"</br>Last Name:- " + obj.userId  + "</br>Email:- " + obj.emailAddress + "</br>User Type:- " + obj.userType.userTypeName);
	  	       	$('#oerationDetails').html(DYN_HTML_TBODY);
	  	      	$('#getItemSummariesForSiteRes').html("");
	       	   }
         	  },
         	  error: function(){      
         	   alert('Error while request..');
         	  }
         	 });
         	}
        
        function getItemSummariesForSite(siteAccountId,invUserId){
        	//alert(siteAccountId);
         	 $.ajax({
         	  type: "post",
         	  url: "getItemSummariesForSite",
         	  cache: false,  
         	 data: { siteAccId: siteAccountId,invUserId:invUserId} ,
         	  success: function(response){
         		 $('#FASTLINKURL').val(response);
         	  //alert(response);
         	  $('#getItemSummariesForSiteRes').html("");
         	   var obj = JSON.parse(response);
         	  if(obj.errorDetails!=null){        
          		$('#getItemSummariesForSiteRes').html("");
        		   alert(obj.errorDetails.message);
        	   }else{
	         		var DYN_HTML_TBODY="";
	         		DYN_HTML_TBODY+="<B><U>Item Summaries For Site</U></B>";
	         		DYN_HTML_TBODY+="<table width='100%' border=1><tr style='vertical-align: top;'>";
	  	       	$.each(obj.itemDetails, function( index, value ) {
	  	       	DYN_HTML_TBODY+="<td>";
	  	       		if(value.contServName=='bank'){
		  	       		DYN_HTML_TBODY+="<B><U>Bank Details</U></B>"+
		  	       						"</br><B>" +value.itemDispName+"</B>";
			  	       	$.each(value.accountDetails, function( index, value ) {
				  	      	$.each(value.bankDetail, function( index, value ) {
				  				DYN_HTML_TBODY+="</br>Account Holder:- " +value.accHolder+
				  				"</br> Account Type:- " +value.accType+
				  				"</br> Account Number:- " +value.accNum+
				  				"</br> Available Balance:- " +value.avilbBal+
				  				//"</br> Maturity Date:- " +value.maturityDate+
				  				"</br> Current Balance:- " +value.curBal+"</br>";
				  			});
			  			});
	  	       		}else if(value.contServName=='credits'){
	  	       			 DYN_HTML_TBODY+="<B><U>Card Details</U></B>"+
			  	      					"</br><B>" +value.itemDispName+"</B>";
			  	       	$.each(value.accountDetails, function( index, value ) {	
			  	       		DYN_HTML_TBODY+="</br>Account Name:- "+value.accName;
			  	       		$.each(value.cardDetail, function( index, value ) {
			  				DYN_HTML_TBODY+="</br>Account Holder:- "+value.accHolder+
				  				"</br>Account Type:- " +value.accType+
				  				"</br>Account Number:- " +value.accNum+
				  				"</br>Running Balance:- " +value.runningBal+
				  				"</br>Total Credit Line:- " +value.totCreditLine;
			  	       	});
		  			}); 
	  	       		}else if(value.contServName=='minutes'){  	       			
	  	       		}else if(value.contServName=='orders'){  	       			
	  	       		}else if(value.contServName=='telephone'){  	       			
	  	       		}else if(value.contServName=='stocks'){  	
	  	       		 DYN_HTML_TBODY+="<B><U>Investment Details</U></B>"+
     					"</br><B>" +value.itemDispName+"</B>";
      	$.each(value.accountDetails, function( index, value ) {	
      		$.each(value.investmentDetail, function( index, value ) {	
      			DYN_HTML_TBODY+="</br>Account Type:- "+value.accType+
				"</br>Account Number:- " +value.accNum+
				"</br>Account Name:- " +value.accName+
				"</br>Plan Name:- " +value.planName+
				//"</br>As Of:- " +value.asofDate.date+
				"</br>Cash:- " +value.cash+
				"</br>Margin Balance:- " +value.margBal+
				"</br>Total Balance:- " +value.totBal+
				"</br>Total Vested Balance:- " +value.totVestedBal+
				"</br>Account Holder:- " +value.accHolder+
				"</br>loan401k:- " +value.loan401k;
      	 	});
			}); 
	  	       		}else if(value.contServName=='miles'){  	       			
	  	       		}else if(value.contServName=='bills'){  	       			
	  	       		}else if(value.contServName=='loans'){  	   
	  	       			/* DYN_HTML_TBODY+="<B><U>Loan Details</U></B>"+
	    								"</br><B>" +value.itemDisplayName+"</B>";
				     	$.each(value.itemData.accounts, function( index, value ) {
				     		$.each(value.loans, function( index, value ) {	
								DYN_HTML_TBODY+="</br>Type Loan:- "+value.loanType+
								"</br>Account Name:- " +value.accountName+
								"</br>Description:- " +value.description+
								"</br>Last Payment Amount:- " +value.lastPaymentAmount.amount+
								"</br>Principal Balance:- " +value.principalBalance.amount+
								"</br>Original Loan Amount:- " +value.originalLoanAmount.amount;
				     		});
					});  	        */			
	  	       		}else if(value.contServName=='mortgage'){  	       			
	  	       		}else if(value.contServName=='bill_payment'){  	       			
	  	       		}else if(value.contServName=='insurance'){  	       			
	  	       		}else if(value.contServName=='utilities'){  	       			
	  	       		}else if(value.contServName=='cable_satellite'){  	       			
	  	       		}else if(value.contServName=='isp'){  	       			
	  	       		}else if(value.contServName=='prepay'){  	       			
	  	       		}else if(value.contServName=='RealEstate'){  	       			
	  	       		}
	  	       		
	  	       	DYN_HTML_TBODY+="</td>";	
	  				
	  			});
	  	      DYN_HTML_TBODY+="</tr></table>";
	         	   //$('#getPopularSitesRes').html("First Name:- " + obj.lastLoginTime +"</br>Last Name:- " + obj.userId  + "</br>Email:- " + obj.emailAddress + "</br>User Type:- " + obj.userType.userTypeName);
	  	       	$('#getItemSummariesForSiteRes').html(DYN_HTML_TBODY);
	         	  }
         	  },
         	  error: function(){      
         	   alert('Error while request..');
         	  }
         	 });
         	}
        
       
        function getTokan(purpose, siteID, invUserId){
        	//alert(purpose+ " : "+ siteID);
          	 $.ajax({
          	  type: "post",
          	  url: "getTokan",
          	  cache: false,  
          	  async:false,
          	  data:{operation:purpose,siteAccId:siteID,invUserId:invUserId},
          	  success: function(response){
          	   var obj = JSON.parse(response);
          		var DYN_HTML_TBODY="";
          		DYN_HTML_TBODY+="<B><U>Popular Sites</U></B>";
   				DYN_HTML_TBODY+="</br> token :-" +obj.flDetails.OAUTH_TOKEN+
   				"</br> tokenSecret :-" +obj.flDetails.OAUTH_TOKEN_SECRET;
   				// alert(obj.flDetails.OAUTH_TOKEN+" : "+ obj.flDetails.OAUTH_TOKEN_SECRET+" : "+ obj.flDetails.APPLICATION_KEY+" : "+ obj.flDetails.APPLICATION_TOKEN+" : "+ obj.flDetails.FL_API_URL+" : "+ obj.flDetails.FL_API_PARAM);
   				var fastlinkURL = generateFastLinkUrl(obj.flDetails.OAUTH_TOKEN, obj.flDetails.OAUTH_TOKEN_SECRET, obj.flDetails.APPLICATION_KEY, obj.flDetails.APPLICATION_TOKEN, obj.flDetails.FL_API_URL, obj.flDetails.FL_API_PARAM);
   				
   				$('#FASTLINKURL').val(fastlinkURL);
   				
				/* try{
					alert('1');
					parent.callFastlinkIframe(data);
					return;	
				}catch(err){	
					alert('2'); */
					var ifrm = document.createElement("IFRAME");
					ifrm.style.width = 750 + "px";
					ifrm.style.height = 525 + "px";
					$.colorbox({inline:true, href: ifrm});						
					ifrm.setAttribute("src", fastlinkURL);
					// What a silly solution
					cover = document.createElement("DIV");
					cover.style.width = 30 + "px";
					cover.style.height = 30 + "px";
					cover.style.background = "#ffffff";
					cover.style.position = "absolute";
					cover.style.top = "5px";
					cover.style.right = "5px";
					$('#cboxContent').css('position','relative');
					$('#cboxContent').append(cover);					
				/* } */	
   				
          	  //alert(DYN_HTML_TBODY);
				//alert(fastlinkURL);
          	  },
          	  error: function(){      
          	   alert('Error while request..');
          	  }
          	 });
          	}
        
        
        function generateFastLinkUrl(oauth_token, token_secret, consumer_key, consumer_secret, url, oauthCallBackURL) {
        	//alert(oauth_token+"\n"+ token_secret+"\n"+ consumer_key+"\n"+ consumer_secret+"\n"+ url+"\n"+ oauthCallBackURL)
            var method = "GET";
            var signature = "+";
            //var oauthCallBackURL = "OOB";
            //var url = "https://fastlink.yodlee.com/appscenter/fastlinksb/linkAccount.fastlinksb.action";
            var authSeconds;
            //var consumer_key = "a458bdf184d34c0cab7ef7ffbb5f016b";
            var version = "1.0";
            var signature_method = "HMAC-SHA1";
            //var consumer_secret = "1ece74e1ca9e4befbb1b64daba7c4a24";
            var timestamp = freshTimestamp();
            var nonce = freshNonce();

            var parameters = "";
            parameters += "&oauth_consumer_key="+consumer_key;
            parameters += "&oauth_nonce="+nonce;
            parameters += "&oauth_signature_method="+signature_method;
            parameters += "&oauth_timestamp="+timestamp;
            parameters += "&oauth_token="+oauth_token;
            parameters += "&oauth_version="+version;

           /*  var signature = sign(consumer_key,consumer_secret,oauth_token, token_secret, timestamp, nonce, method, url, oauthCallBackURL, version, signature_method );

            
            var finalUrl = url + oauthCallBackURL + parameters;	
            finalUrl += "&oauth_signature="+signature; */
            
            var signature = sign(consumer_key,consumer_secret,oauth_token, token_secret, timestamp, nonce, method, url, oauthCallBackURL, version, signature_method );

            
            var finalUrl = url + "?"+oauthCallBackURL+"&"+parameters;
            finalUrl += "&oauth_signature="+signature;

            // Urls with +'s causing issues
            if(/\+/.test(finalUrl)) {
                return generateFastLinkUrl(oauth_token, token_secret);
            }

            return finalUrl;
        }
        
        
        function getRefreshTokan(){
         	 $.ajax({
         	  type: "post",
         	  url: "getTokan",
         	  cache: false,  
         	  async:false,
         	  success: function(response){
         	   var obj = JSON.parse(response);
         		var DYN_HTML_TBODY="";
         		DYN_HTML_TBODY+="<B><U>Popular Sites</U></B>";
  				DYN_HTML_TBODY+="</br> token :-" +obj.TOKEN_DETAILS.token+
  				"</br> tokenSecret :-" +obj.TOKEN_DETAILS.tokenSecret;
  				var fastlinkURL = generateRefreshLinkUrl(obj.TOKEN_DETAILS.token, obj.TOKEN_DETAILS.tokenSecret, obj.flDetails.APPLICATION_KEY, obj.flDetails.APPLICATION_TOKEN, obj.flDetails.FL_REFR_URL, obj.flDetails.FL_REFR_PARAM);
  				$('#FASTLINKURL').val(fastlinkURL);
  				
				/* try{
					alert('1');
					parent.callFastlinkIframe(data);
					return;	
				}catch(err){	
					alert('2'); */
					var ifrm = document.createElement("IFRAME");
					ifrm.style.width = 750 + "px";
					ifrm.style.height = 525 + "px";
					$.colorbox({inline:true, href: ifrm});						
					ifrm.setAttribute("src", fastlinkURL);
					// What a silly solution
					cover = document.createElement("DIV");
					cover.style.width = 30 + "px";
					cover.style.height = 30 + "px";
					cover.style.background = "#ffffff";
					cover.style.position = "absolute";
					cover.style.top = "5px";
					cover.style.right = "5px";
					$('#cboxContent').css('position','relative');
					$('#cboxContent').append(cover);					
				/* } */	
  				
         	  //alert(DYN_HTML_TBODY);
				//alert(fastlinkURL);
         	  },
         	  error: function(){      
         	   alert('Error while request..');
         	  }
         	 });
         	}
        
        function generateRefreshLinkUrl(oauth_token, token_secret, consumer_key, consumer_secret, url, oauthCallBackURL) {
        	//alert(oauth_token+"\n"+ token_secret+"\n"+ consumer_key+"\n"+ consumer_secret+"\n"+ url+"\n"+ oauthCallBackURL)
            var method = "GET";
            var signature = "+";
            //var oauthCallBackURL = "OOB";
            //var url = "https://fastlink.yodlee.com/appscenter/fastlinksb/linkAccount.fastlinksb.action";
            var authSeconds;
            //var consumer_key = "a458bdf184d34c0cab7ef7ffbb5f016b";
            var version = "1.0";
            var signature_method = "HMAC-SHA1";
            //var consumer_secret = "1ece74e1ca9e4befbb1b64daba7c4a24";
            var timestamp = freshTimestamp();
            var nonce = freshNonce();

            var parameters = "";
            parameters += "&oauth_consumer_key="+consumer_key;
            parameters += "&oauth_nonce="+nonce;
            parameters += "&oauth_signature_method="+signature_method;
            parameters += "&oauth_timestamp="+timestamp;
            parameters += "&oauth_token="+oauth_token;
            parameters += "&oauth_version="+version;

           /*  var signature = sign(consumer_key,consumer_secret,oauth_token, token_secret, timestamp, nonce, method, url, oauthCallBackURL, version, signature_method );

            
            var finalUrl = url + oauthCallBackURL + parameters;	
            finalUrl += "&oauth_signature="+signature; */
            
            var signature = sign(consumer_key,consumer_secret,oauth_token, token_secret, timestamp, nonce, method, url, oauthCallBackURL, version, signature_method );

            
            var finalUrl = url + "?"+oauthCallBackURL+"&"+parameters;
            finalUrl += "&oauth_signature="+signature;

            // Urls with +'s causing issues
            if(/\+/.test(finalUrl)) {
                return generateFastLinkUrl(oauth_token, token_secret);
            }

            return finalUrl;
        }
        
    	 function freshTimestamp() {
            return OAuth.timestamp();
        }

        function freshNonce() {
            return OAuth.nonce(11);
        }
    	function sign(oauth_consumer_key, consumerSecret, oauth_token, tokenSecret, timestamp, nonce, httpMethod, URL, parameters, oauth_version, oauth_signature_method) {
          var accessor = { consumerSecret: consumerSecret, tokenSecret: tokenSecret};
          
          var message = { method: httpMethod   , action: URL, parameters: OAuth.decodeForm(parameters)           };
            message.parameters.push(["oauth_consumer_key", oauth_consumer_key]);
            message.parameters.push(["oauth_token", oauth_token]);
            message.parameters.push(["oauth_version", oauth_version]);
            message.parameters.push(["oauth_signature_method", oauth_signature_method]);
            message.parameters.push(["oauth_timestamp", timestamp]);
            message.parameters.push(["oauth_nonce", nonce]);
            OAuth.SignatureMethod.sign(message, accessor);

            var signature = OAuth.getParameter(message.parameters, "oauth_signature");
            return signature;
        }
        
        </script>
        
        
        <script type="text/javascript">
		$(function(){

			// Event to Search a site
			$("a.fastlink-inner").click(function(ev) {
				ev.preventDefault();
				var id_cobSessionToken = $("#id_cobSessionToken").val();
				var id_userSessionToken = $("#id_userSessionToken").val();
				if($(this).parent().is(".disabled")){
				return false;
				}
				if($("#id_cobSessionToken").val()==""){
				alert("You must get the Cobrand session token first.");
				return false;
				}
				if($("#id_userSessionToken").val()==""){
				alert("You must get the username session token first.");
				return false;
				}
			   var  cobSessionToken=$("#id_cobSessionToken").val();
			   var  userSessionToken	=$("#id_userSessionToken").val();	
			   var  parameters={cobSessionToken:cobSessionToken,userSessionToken:userSessionToken,bridgetAppId:10003200};$.ajax({ 
				    url: "<?= $baseURL ?>/controller",
                    data: {request_type:'call_api_service',request_api_service:'getOAuthAccessToken',url:'/jsonsdk/OAuthAccessTokenManagementService/getOAuthAccessToken',parameters:JSON.stringify(parameters)},
                    type: 'post',
					success: function(data) {					   
                        data = JSON.parse(data);										
                        var token = data.Body.token;
                        var tokenSecret = data.Body.tokenSecret;
                        var fastlinkURL = generateFastLinkUrl(token, tokenSecret);
						try{
							parent.callFastlinkIframe(data);
							return;	
						}catch(err){						
							var ifrm = document.createElement("IFRAME");
							ifrm.style.width = 750 + "px";
							ifrm.style.height = 525 + "px";
							$.colorbox({inline:true, href: ifrm});						
							ifrm.setAttribute("src", fastlinkURL);
							// What a silly solution
							cover = document.createElement("DIV");
							cover.style.width = 30 + "px";
							cover.style.height = 30 + "px";
							cover.style.background = "#ffffff";
							cover.style.position = "absolute";
							cover.style.top = "5px";
							cover.style.right = "5px";
							$('#cboxContent').css('position','relative');
							$('#cboxContent').append(cover);					
						}				
										
				      }
					});
					
			});

			// Api logger: relate logic
			$(document).ajaxComplete(function( event,request, settings ) {
			  if(_.isUndefined(settings.logger)){
			  	$.startLogger();
			  }
			});

			$("a.btnClearLogger").click(function(){
				$("div.logger").find("#accordion_log").unbind();
				$("div.logger").find("#accordion_log").empty();
			});

			$.startLogger = function(){
				$.ajax({
					url: "<?= $baseURL ?>/check-logger",
					method:'GET',
					async: true,
					logger:true,
					success: function(response){
						var attributes, logs, log,key_reference, model_log={};
						if(response!=""){
							logs = JSON.parse(response);

							for(key in logs){
								log = logs[key];
								key_reference = key;
								model_log = new App.Models.Log();
								model_log.set("method", log.method || "POST");
								model_log.set("key", key);
								model_log.set("long_url", log.long_url);
								model_log.set("short_url", log.short_url);

								try{
									model_log.set("request", JSON.parse(log.request));
								}catch(e){
									model_log.set("request", log.request);
								}

								try{
									model_log.set("response", JSON.parse(log.response));
								}catch(e){
									model_log.set("response", log.response);
								}

								if(App.Instances.Collection.Logs.where({"key":key}).length==0){
									App.Instances.Collection.Logs.add(model_log);
								}
							}
						}
					}
				});
			}
		});
		function getAllSites(invUserId){
         	 $.ajax({
         	  type: "post",
         	  url: "getAllSites",
         	  cache: false,  
         	  success: function(response){
         	  //alert(response);
         	  $('#oerationDetails').html("");
         	   var obj = JSON.parse(response);
         		var DYN_HTML_TBODY="";
         		DYN_HTML_TBODY+="<B><U>All Sites</U></B>";
         		alert(response);
  	       	/* $.each(obj, function( index, value ) {
  				DYN_HTML_TBODY+="</br>" +value.defaultDisplayName;
  			}); */
         	   
         	   //$('#getPopularSitesRes').html("First Name:- " + obj.lastLoginTime +"</br>Last Name:- " + obj.userId  + "</br>Email:- " + obj.emailAddress + "</br>User Type:- " + obj.userType.userTypeName);
  	       	$('#oerationDetails').html(DYN_HTML_TBODY);
  	       	$('#getItemSummariesForSiteRes').html("");
         	  },
         	  error: function(){      
         	   alert('Error while request..');
         	  }
         	 });
         	}
       
       function getPopularSites(){
      	 $.ajax({
      	  type: "post",
      	  url: "getPopularSites",
      	  cache: false,  
      	  success: function(response){
      	  //alert(response);
      	  $('#oerationDetails').html("");
      	   var obj = JSON.parse(response);
      		var DYN_HTML_TBODY="";
      		DYN_HTML_TBODY+="<B><U>Popular Sites</U></B>";
	       	$.each(obj, function( index, value ) {
				DYN_HTML_TBODY+="</br>" +value.defaultDisplayName;
			});
      	   
      	   //$('#getPopularSitesRes').html("First Name:- " + obj.lastLoginTime +"</br>Last Name:- " + obj.userId  + "</br>Email:- " + obj.emailAddress + "</br>User Type:- " + obj.userType.userTypeName);
	       	$('#oerationDetails').html(DYN_HTML_TBODY);
	       	$('#getItemSummariesForSiteRes').html("");
      	  },
      	  error: function(){      
      	   alert('Error while request..');
      	  }
      	 });
      	}
		
</script>
        
    </head>
    <body>
    <input type="text" id="FASTLINKURL" size="200" />
   <!--  <iframe src="https://fastlink.yodlee.com/appscenter/fastlinksb/linkAccount.fastlinksb.action?access_type=oauthdeeplink&displayMode=desktop&oauth_callback=https%3A%2F%2Fwww.google.com&oauth_consumer_key=5e1597a5f8fd4401a25c654c93df73b6&oauth_nonce=VEXbDFtd6ov&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1445256640&oauth_token=e3111e06258544f0bec13d2330f555d8&oauth_version=1.0&oauth_signature=yHClmY1gZ4s6pQdLucIkDH+ngxQ="></iframe>
    -->     <div align="center">
	      
        	<table border="1" width="80%">
        	<col width="100%" />
        	
        	
        	<tr>
        	<%-- <td> <div id="errorMsg"></div>
        	  <h2>User Registration</h2>
        	  <form method="post" action="registration">
        	<table>
	        	<tr>
	        		<td>User Name</td>
					<td><input type="text" name="userName" maxlength="150"></td>							
	        	</tr>   
	        	<tr>
	        		<td>Password</td>
					<td><input type="password" name="password" value="Password@2015"  maxlength="50"></td>							
	        	</tr>
	        	<tr>
	        		<td>e-Mail</td>
					<td><input type="text" size="40" name="email" value="abhangp@mindcraft.in" maxlength="50"></td>							
					
	        	</tr>
	        	<tr>
	        		<td colspan="2"><input type="submit" value="Save"> 
	        		<c:if test="${not empty resultMap.errorDetails}">
	        		${resultMap.errorDetails.message}
	        		</c:if> 
	        		</td>		
	        	</tr>     	
        	</table>
        	</form>
        	</td>  --%>
        	<td>
        	  <h2>User List</h2>
        	  <div style="overflow:auto;  height: 300px;">
        	<table>
	        	<th>No</th>
	        	<th>User Name</th>
	        	<th>Email</th>
	        	
				<c:forEach var="user" items="${userList}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td><a href="#" onclick="registration('${user.invUserId}')">Registration</a></td>
					<td><a href="#" onclick="login('${user.invUserId}')">Login</a></td>
	        	</tr>
				</c:forEach>	        	
        	</table>
        	</div>
        	</td>
        	</tr>
        	</table>
        	  
        </div>
        <div style="vertical-align: top;">
        <table width="100%">
        <col width="25%" />
         <col width="25%" />
          <col width="25%" />
           <col width="25%" />
        	<tr style="vertical-align: top;">
        	<td>
        	 <div id="userDetails"></div>
        	</td>
        	<td>
        	<div id="userOperations"></div>
        	</td>
        	<td>
        	<div id="oerationDetails"></div>
        	</td>
        	<td>        	
        	</td>
        	</tr>
        	</table>
        	</div>
        	<div id="getItemSummariesForSiteRes"></div>
        	

    </body>
</html>
