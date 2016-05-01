window.onresize = function(event) {
    if (document.body.clientWidth>=731) {
        document.getElementById('menu_mobile').style.display="none";
    }
};

function animateLeft(obj, from, to){
    if(from == 5 || from==-805){
        return;
    }
    else {
        var box = obj;
        box.style.display = "block";
        box.style.left = from + "px";
        setTimeout(function(){
            animateLeft(obj, ((to==-800)?from-5:from+5), to);
        }, 1)
    }
}

function display_menu_mobile() {

    var obj = document.getElementById('menu_mobile');

    if (obj.style.left=="" || obj.style.left=="-800px") {
        animateLeft(obj, -800, 0);

    } else {
        animateLeft(obj, 0, -800);
    }
}