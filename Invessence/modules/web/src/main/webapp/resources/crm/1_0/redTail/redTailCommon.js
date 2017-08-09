/**
 * Created by abhangp on 08/08/2017.
 */


function openCRMWidget(finalUrl){
    //window.open(finalUrl,'_self');
    //alert("finalUrl :"+finalUrl);
    var ifrm = document.createElement("IFRAME");
    //ifrm.style.width = 1200 + "px";
    //ifrm.style.height = 525 + "px";
    //$.colorbox({inline:true, href: ifrm});
    ifrm.setAttribute("src", finalUrl);
    // What a silly solution
   // cover = document.createElement("DIV");
    //cover.style.width = 30 + "px";
    //cover.style.height = 30 + "px";
    //cover.style.background = "#ffffff";
    //cover.style.position = "absolute";
    //cover.style.top = "5px";
    //cover.style.right = "5px";
    //$('#cboxContent').css('position','relative');
    //$('#cboxContent').append(cover);
    document.getElementById("redTailWidgetDiv").appendChild(ifrm);
}





