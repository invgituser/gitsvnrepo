//Created by Gregoire Vella 2016
//more infos on www.gregoire-vella.com


//Slider 
$(function(){
	setTimeout(function()
		{
		$("#jobs_1").slideUp(800);
		}, 4000); 
	});
$(function(){
	setTimeout(function()
		{
		$("#jobs_2").fadeIn(400);
		}, 4800); 
	});	
$(function(){
	setTimeout(function()
		{
		$("#jobs_2").slideUp(800);
		}, 8000); 
	});
$(function(){
	setTimeout(function()
		{
		$("#jobs_3").fadeIn(400);
		}, 8800); 
	});		
$(function(){
	setTimeout(function()
		{
		$("#jobs_3").slideUp(800);
		}, 12000); 
	});
$(function(){
	setTimeout(function()
		{
		$("#jobs_4").fadeIn(400);
		}, 12800); 
	});
$(function(){
	setTimeout(function()
		{
		$("#jobs_4").slideUp(800);
		}, 16000); 
	});
$(function(){
	setTimeout(function()
		{
		$("#jobs_1").fadeIn(400);
		}, 16800); 
	});		
	  
//Read more
$(document).ready(function(){

$("#more").click(function () {
			$("#more").hide();
			$('#hidden_text').fadeIn('600')
		});			
})


//Video
$(document).ready(function(){

$("#open_popup").click(function () {
			$("#popup-bg").fadeIn();	
		});
})

$(document).ready(function(){

$("#close_popup").click(function () {
			$("#popup-bg").fadeOut();

		});
})

//Header
$(function() {
    var header = $(".no-bg-header");
    $(window).scroll(function() {    
       var scroll = $(window).scrollTop();
    
        if (scroll >= 200) {
            header.addClass("bg-header");
        } else {
            header.removeClass("bg-header");
        }
    });
});


	  