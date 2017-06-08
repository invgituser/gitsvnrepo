/**
 * Created by abhangp on 12/13/2016.
 */

function openWidget(url) {
    var myWidget = new MoneyDesktopWidgetLoader({
        url:url
    });
}

function openLinkedInWidget(finalUrl){
    window.open(finalUrl,'_self');
   // //alert("finalUrl :"+finalUrl);
   // var ifrm = document.createElement("IFRAME");
   // ifrm.style.width = 750 + "px";
   // ifrm.style.height = 525 + "px";
   // //$.colorbox({inline:true, href: ifrm});
   // ifrm.setAttribute("src", finalUrl);
   // // What a silly solution
   //// cover = document.createElement("DIV");
   // //cover.style.width = 30 + "px";
   // //cover.style.height = 30 + "px";
   // //cover.style.background = "#ffffff";
   // //cover.style.position = "absolute";
   // //cover.style.top = "5px";
   // //cover.style.right = "5px";
   // //$('#cboxContent').css('position','relative');
   // //$('#cboxContent').append(cover);
   // document.getElementById("linkedInWidgetDiv").appendChild(ifrm);
}

