<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<!-- Header -->
<div style="background: #F5F5F5;height: auto;width: 100%;padding: 5px;border-bottom: 4px solid #C7BFBF;background-image: linear-gradient(to bottom,#FFF,#E6E6E6);">
	<img class="logo" src="<?= $baseAssets ?>/img/logo-yodleeinteractive-transparent.png" style="margin-right:20px;">
	<span>CobrandId:  <a href="#"><?= $panel_login_info["user_info"]->userContext->cobrandId ?></a></span> |  
	<span><i class="icon-user"></i> Username: <a href="#"><?= $panel_login_info["user_info"]->loginName ?></a></span> |  
	<span>Rest URL: <a href="#"><i class="icon-globe"></i>  <?= $panel_login_info["base_url"] ?></a></span>
	<a style="margin-right: 20px;" class="pull-right btn btn-danger" href="<?= $baseURL ?>/logout"><i class="icon-off"></i> logout</a>
</div>

<!-- Content  -->
<div class="container-body">
	<div class="row">
		<div class="pull-left flow-column-left">
		     <div style="float:left;">
			<h3><?= $title ?></h3> <span class="c-gray"><?= $sub_title ?></span>
			</div>
			 <div style="float:right;">
				 Add accounts via <a data-toggle="dropdown" role="button" id="drop2" data-flow="add_account_fastlink" class="fastlink-inner" href="#">Fastlink</a>
			</div>
			<div style="clear:both"></div>
			<hr>
			<!-- Place Holder for subsequent flow views -->
			<div id="container-page-fastlink">
				 <div class="mt-page-section" id="section_1"><span id="Overview"></span><h4 class="editable" style="visibility: visible;">Overview</h4> <p style="visibility: visible;">Yodlee FastLink provides a successful add account experience for consumers by&nbsp;capturing the entire set of credentials required to access their accounts, including username, password, and authentication mechanisms such as answers to questions and&nbsp;tokens.</p> <p style="visibility: visible;">The add account experience offered by Yodlee FastLink provides consumers:</p> <ul> <li>A way to search and browse for sites</li> <li>Help messages to guide the consumer during the capture of credentials</li> <li>Positive reinforcement upon task completion</li> <li>Continuous updates to capture any changes in authentication requirements at end site.</li> </ul> </div>
				
				<div>
				 <p>For <span style="font-weight:bold;">FastLink Product Guide</span> <a target="_blank"  href="https://developer.yodlee.com/Indy_FinApp/Aggregation_Services_Guide/Yodlee_FastLink_and_LAW_Guide/Yodlee_FastLink_Product_Guide">click here</a></p>
				</div>
				<div>
				 <p>For <span style="font-weight:bold;">Fastlink Integration Guide</span> <a target="_blank" href="https://developer.yodlee.com/Indy_FinApp/Aggregation_Services_Guide/Yodlee_FastLink_and_LAW_Guide/Yodlee_FastLink_Integration_Guide/">click here</a></p>
				</div>
				</div>
				<div id="container-page"></div>
		</div>

		<!-- Right side: Api Logger -->
		<div class="pull-right flow-column-right">
			<div class="logger">
				<h4 class="pull-left">API Logger</h4>
				<a href="#" class="btn btn-link pull-right btnClearLogger">Clear Log</a>
				<div class="clearfix"></div>
				<ol class="rounded-list" id="accordion_log"></ol>
			</div>
		</div>
	</div>
</div>

<!-- Events Js for the current Page -->
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
			   var  parameters={cobSessionToken:cobSessionToken,userSessionToken:userSessionToken,bridgetAppId:10003200};
			   $.ajax({ 
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
		 function generateFastLinkUrl(oauth_token, token_secret) {
        var method = "GET";
        var signature = "+";
        var oauthCallBackURL = "OOB";
        var url = "https://fastlink.yodlee.com/appscenter/fastlinksb/linkAccount.fastlinksb.action";
        var authSeconds;
        var consumer_key = "a458bdf184d34c0cab7ef7ffbb5f016b";
        var version = "1.0";
        var signature_method = "HMAC-SHA1";
        var consumer_secret = "1ece74e1ca9e4befbb1b64daba7c4a24";
        var timestamp = freshTimestamp();
        var nonce = freshNonce();

        var parameters = "";
        parameters += "&oauth_consumer_key="+consumer_key;
        parameters += "&oauth_nonce="+nonce;
        parameters += "&oauth_signature_method="+signature_method;
        parameters += "&oauth_timestamp="+timestamp;
        parameters += "&oauth_token="+oauth_token;
        parameters += "&oauth_version="+version;

        var signature = sign(consumer_key,consumer_secret,oauth_token, token_secret, timestamp, nonce, method, url, "access_type=oauthdeeplink&oauth_callback=OOB", version, signature_method );

        
        var finalUrl = url + "?access_type=oauthdeeplink&oauth_callback=OOB&"+parameters;
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

<!-- Begin: Base Window Modal to show the details of the Api Logger -->
<div id="win_modal" class="yodlee modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		<div class="s-title-log" id="myModalLabel">
			<span class="method_expand c-gray"></span> <span class="short_url_expand"><span> 
		</div>
	</div>
	<div class="modal-body"></div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>
<!-- End: Base Window Modal to show the details of the Api Logger -->

<!-- Begin: Templates Using Underscore for the Api Logger -->
<%-- <script type="text/template" id="template_modal">
	<p class="font-size-10"><strong>Url:</strong> <span class="c-blue"><%= long_url %></span></p>
	<div class="span6 no-margin-left">
		<div><strong>Request: </strong> <span class="timer-expand"><%= request.timer %><span></div>
		<textarea class="request-expanded"></textarea>
	</div>
	<div class="span5 no-margin-left">
		<div><strong>Response: </strong> <span class="timer-expand"><%= response.timer %><span></div>
		<textarea class="response-expanded"></textarea>
	</div>
</script>

<script type="text/template" id="template_panel_log">
	<div class="accordion-heading">
		<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion_log" href="#">
			<div class="pull-left p-left-15 s-title-log">
				<span class="method c-gray"><%= method %>:</span> <span title="<%= long_url %>" class="short_url"><%= (short_url.length>55) ? short_url.substring(0,55)+"..." : short_url %><span> 
			</div>
			<button class="btn pull-right">&#43;</button>
			<div class="clearfix"></div>
		</a>
	</div>
	<div id class="accordion-body collapse">
		<div class="accordion-inner">
			<input type="hidden" name="key" value="<%= key %>">
			<div class="log-request pull-left">
				<div><strong>Request: </strong> <span class="timer"><%= request.timer %><span></div>
				<div class="body-log">
					<div class="request"></div>
				</div>
			</div>
			<div class="log-response pull-right">
				<div><strong>Response: </strong> <span class="timer"><%= response.timer %><span></div>
				<div class="body-log">
					<div class="response"></div>
				</div>
			</div>
			<div class="clearfix"></div>
			<input type="button" class="btn-expand-detail pull-right m-top-5 m-bottom-15 btn btn-warning" value="...">
		</div>
	</div>
</script> --%>
<!-- End: Templates Using Underscore for the Api Logger -->
<link rel="stylesheet" href="<?= $baseAssets ?>/css/colorbox.css">  
<link rel="stylesheet" href="<?= $baseAssets ?>/css/main.css">      	
<script src="<?= $baseAssets ?>/js/oauth.js"></script>	
<script src="<?= $baseAssets ?>/js/sha1.js"></script>		
<script src="<?= $baseAssets ?>/js/jquery.colorbox.js"></script> 
<!-- Loading Views, Collections, and Models for the Api Logger using Backbone -->
<script src="<?= $baseAssets ?>/js/logger.js"></script>