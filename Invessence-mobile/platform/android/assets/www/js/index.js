/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
 var state="";
 var ref;

var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
	
         ///$('#error').hide();
         document.addEventListener("backbutton", app.onBackKey, false);
       /* document.addEventListener("online", app.onOnline,false);*/
           /* document.addEventListener("offline", app.onOffline,false);*/
                $('#indexPg').hide();
              checkConnection();


    },
    onOnline: function(){
    alert("online function");

    },
    onBackKey: function(){
     navigator.app.exitApp();
    },
    onOffline: function(){
       // alert("onOffline function");
 $('#indexPg').hide();
     checkConnection();
   //  alert(state);
     // window.location="index.html";
var location=window.location.href;
     // alert(location+"location offline");
             var resltList=location.split("/");
            // alert(resltList+" offline");
            // alert(resltList.indexOf("index.html")+"index offline");
                                 if(resltList.indexOf("index.html")>0){
                                 //alert("if");
                                  $.mobile.changePage("#error", { transition: "slideup", changeHash: false });
                                         $('#indexPg').hide();
                                         $('#error').show();
                                 }else{
                                // alert("else");
                                 window.location="index.html";
                                 }
                               //  alert(window.location+" location after offline");



        },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
               var parentElement = document.getElementById(id);
              /* var listeningElement = parentElement.querySelector('.listening');
               var receivedElement = parentElement.querySelector('.received');

               listeningElement.setAttribute('style', 'display:none;');
               receivedElement.setAttribute('style', 'display:block;');*/

               console.log('Received Event: ' + id);
           }

};
   function checkConnection() {
	
          // alert("inside");
                state = navigator.connection.type;
          // alert("networkState"+state);
              var states = {};
               states[Connection.UNKNOWN]  = 'Unknown connection';
               states[Connection.ETHERNET] = 'Ethernet connection';
               states[Connection.WIFI]     = 'WiFi connection';
               states[Connection.CELL_2G]  = 'Cell 2G connection';
               states[Connection.CELL_3G]  = 'Cell 3G connection';
               states[Connection.CELL_4G]  = 'Cell 4G connection';
               states[Connection.CELL]     = 'Cell generic connection';
               states[Connection.NONE]     = 'No network connection';


             //  alert(state+ "state");
               if(state != "none" && state !="unknown"){
             //  alert("if");
 //alert("networkState"+state);

             var $testdiv = "</div>";

              $('#error').hide();
               $('#indexPg').show();
                ref = window.open('https://www.invessence.com/login.xhtml', '_target','zoom=no,toolbar=no,location=no,disallowoverscroll=yes');
                                 ref.addEventListener('loadstart', function(event) {navigator.notification.activityStart("","Loading...");/*alert('start: ' + event.url); */});
                                          ref.addEventListener('loadstop', function(event) {navigator.notification.activityStop();/*alert('stop: ' + event.url);*/ });
                                          ref.addEventListener('loaderror', function(event) { /*alert('error: ' + event.message);*/

                                           window.location="error.html";
                                        //   ref.close();
                                         //  window.location="index.html";

               /*var location=window.location.href;
                       alert(location+"location online");
                       var resltList=location.split("/");
                       alert(resltList+" online");
                       alert(resltList.indexOf("index.html")+"index online");
                                           if(resltList.indexOf("index.html")>0){
                                           alert("if");
                                            $.mobile.changePage("#error", { transition: "slideup", changeHash: false });
                                                   $('#indexPg').hide();
                                                   $('#error').show();
                                           }else{
                                           alert("if");
                                           window.location="index.html";
                                           }
                                           alert(window.location+" location after");*/
                                             });
                                          ref.addEventListener('exit', function(event) {
                                          navigator.app.exitApp();
                                           });
               }else{
                $('#indexPg').hide();
		navigator.notification.activityStop();
         if($('#error').css('display')=='block'){
             navigator.notification.alert('Connection type: ' + states[state], null, 'Error', 'OK');
          // alert('Connection type: ' + states[state]);
         }
               var location=window.location.href;
                    // alert(location+"location offline");
                            var resltList=location.split("/");
                           // alert(resltList+" offline");
                           // alert(resltList.indexOf("index.html")+"index offline");
                                                if(resltList.indexOf("index.html")>0){
                                                //alert("if");
                                                 $.mobile.changePage("#error", { transition: "none", changeHash: false });
                                                        $('#indexPg').hide();
                                                        $('#error').show();
                                                }else{
                                               // alert("else");
                                                window.location="index.html";
                                                }
                                              //  alert(window.location+" location after offline");

               }
           };
$(document).on('click','#chkNw', function(){
//alert("clicked");
// alert("error state"+state);
 $('#indexPg').hide();
   checkConnection();
   //  alert("after");

   // alert(Object.keys(navigator.connection) +"keys");

    });
app.initialize();
