<script type="text/javascript">
		(function ($) {
			/* MENU DESCRIPTION MAKER*/
			var wapoWindowWidth = $(window).width();

			// fetch titles and add descriptions
			$('#main-menu > li > a').each(function(){
				var mainMenuTitle = $(this).attr('title');
				var linkParent = $(this).parent();

				$('<span>', {
					'class' : 'menu-description hidden-phone hidden-tablet',
					'text' : mainMenuTitle,
				}).appendTo(linkParent);
			});

			/* END MENU DESCRIPTION MAKER*/
		})(jQuery);
</script>

<script type="text/javascript">
		(function ($) {

			var mainMenuItemsCount = $('#main-menu > li').length;
			if(mainMenuItemsCount > 6){
				$('#main-menu > li').css('margin', '0 10px');

				$('#main-menu > li:first-child').css('margin', '0 10px 0 0');
			}

		})(jQuery);
</script>
<script type="text/javascript">
		(function ($) {

			var mainMenuItemsCount = $('#main-menu > li').length;
			if(mainMenuItemsCount > 6){
				$('#main-menu > li').css('margin', '0 8px');
			}

		})(jQuery);
</script>