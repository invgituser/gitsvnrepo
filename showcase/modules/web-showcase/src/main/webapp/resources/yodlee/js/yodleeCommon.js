/**
 * Created by abhangp on 11/23/2015.
 */
        function getFastLinkUrl(oauth_token, token_secret, consumer_key, consumer_secret, url, oauthCallBackURL) {

            var finalUrl=getUrlWithSignature(oauth_token, token_secret, consumer_key, consumer_secret, url, oauthCallBackURL);

        var ifrm = document.createElement("IFRAME");
            ifrm.style.width = 750 + "px";
            ifrm.style.height = 525 + "px";
            $.colorbox({inline:true, href: ifrm});
            ifrm.setAttribute("src", finalUrl);
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

        function getUrlWithSignature(oauth_token, token_secret, consumer_key, consumer_secret, url, oauthCallBackURL){

            // alert("Parameters :\n"+oauth_token+"\n"+ token_secret+"\n"+ consumer_key+"\n"+ consumer_secret+"\n"+ url+"\n"+ oauthCallBackURL)
            var method = "GET";
            var signature = "+";
            var authSeconds;
            var version = "1.0";
            var signature_method = "HMAC-SHA1";
            var timestamp = freshTimestamp();
            var nonce = freshNonce();
            //alert(timestamp+" : "+nonce);

            var parameters = "";
            parameters += "&oauth_consumer_key="+consumer_key;
            parameters += "&oauth_nonce="+nonce;
            parameters += "&oauth_signature_method="+signature_method;
            parameters += "&oauth_timestamp="+timestamp;
            parameters += "&oauth_token="+oauth_token;
            parameters += "&oauth_version="+version;



            var signature = sign(consumer_key,consumer_secret,oauth_token, token_secret, timestamp, nonce, method, url, oauthCallBackURL, version, signature_method );


            var finalUrl = url + "?"+oauthCallBackURL+"&"+parameters;
            finalUrl += "&oauth_signature="+signature;

            //alert(finalUrl);

            if(/\+/.test(finalUrl)) {
                return getUrlWithSignature(oauth_token, token_secret, consumer_key, consumer_secret, url, oauthCallBackURL);
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

        function confirmDelete(id)
        {
            alert(id);
            if (confirm('Do you really want to delete?'))
            {
                // remoteDeleteDemand(id);
                return true;
            }
        }
