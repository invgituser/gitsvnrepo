// FNC for detecting for click outside of any elements ============== 
$.fn.clickOff = function(callback, selfDestroy) {
		var clicked = false;
		var parent = this;
		var destroy = selfDestroy || true;
		
		parent.click(function() {
			clicked = true;
		});
		
		$(document).click(function(event) { 
			if (!clicked) {
				callback(parent, event);
			}
			if (destroy) {
			};
			clicked = false;
		});
	};
	
/** 
 * PrimeFaces Modena Layout
 */
var Modena = {
    
    init: function() {
        this.menuWrapper = $('#layout-menu-cover');
        this.menu = this.menuWrapper.find('ul.modena-menu');
        this.menulinks = this.menu.find('a.menulink');
        this.topMenu = $('#top-menu');
        this.topMenuButton = $('#show-top-menu');
        this.mobileMenuButton = $('#mobile-menu-button');
        this.expandedMenuitems = this.expandedMenuitems||[];
        this.mobile = this.isMobile();
        
        //remove transform on Firefox Mobile 
        if(this.mobile && $.browser.mozilla) {
            this.mobileMenuButton.addClass('no-transform');
            this.menu.addClass('no-transform');
        }
        
        this.bindEvents();
    },
    
    bindEvents: function() {
        var $this = this;
        
        if(this.mobile) {
            this.menuWrapper.css('overflow-y', 'auto');
        }
        else {
            this.menuWrapper.perfectScrollbar({suppressScrollX: true});
        }
	
        this.menulinks.on('click',function(e) {
            var menuitemLink = $(this),
            menuitem = menuitemLink.parent();

            if(menuitem.hasClass('active-menu-parent')) {
                menuitem.removeClass('active-menu-parent');
                menuitemLink.removeClass('active-menu active-menu-restore').next('ul').removeClass('active-menu active-menu-restore');
                $this.removeMenuitem(menuitem.attr('id'));
            }
            else {
                var activeSibling = menuitem.siblings('.active-menu-parent');
                if(activeSibling.length) {
                    activeSibling.removeClass('active-menu-parent');
                    $this.removeMenuitem(activeSibling.attr('id'));

                    activeSibling.find('ul.active-menu,a.active-menu').removeClass('active-menu active-menu-restore');
                    activeSibling.find('li.active-menu-parent').each(function() {
                        var menuitem = $(this);
                        menuitem.removeClass('active-menu-parent');
                        $this.removeMenuitem(menuitem.attr('id'));
                    });
                }

                menuitem.addClass('active-menu-parent');
                menuitemLink.addClass('active-menu').next('ul').addClass('active-menu');
                $this.addMenuitem(menuitem.attr('id'));
            }

            if(menuitemLink.next().is('ul')) {
                e.preventDefault();
            }
            else {
                $this.menuWrapper.removeClass('showmenu');
                $this.mobileMenuButton.removeClass('MenuClose');
            }

            $this.saveMenuState();
            
            if($this.mobile) {
                $this.menuWrapper.perfectScrollbar("update");
            }
        });
        
        this.mobileMenuButton.on('click', function() {
            if(parseInt($this.menuWrapper.css('marginLeft')) < 0) {
                $(this).addClass('MenuClose');
                $this.menuWrapper.addClass('showmenu');
                $this.topMenu.removeClass('showmenu');
                $this.topMenuButton.removeClass('showmenu');
            }
            else {
                $(this).removeClass('MenuClose');
                $this.menuWrapper.removeClass('showmenu');
            }
        });

        this.topMenuButton.on('click',function(){
            if($this.topMenu.is(':hidden')) {
                $(this).addClass('MenuClose');
                $this.topMenu.addClass('showmenu');
                $this.mobileMenuButton.removeClass('MenuClose');
                $this.menuWrapper.removeClass('showmenu');
            }
            else {
                $(this).removeClass('MenuClose');
                $this.topMenu.removeClass('showmenu');
            }
        });
        
        //topbar
        this.topMenu.find('a').click(function(e) {
            var link = $(this),
            submenu = link.next('ul');
            
            if(submenu.length) {
                if(submenu.hasClass('active-menu')) {
                    submenu.removeClass('active-menu');
                    link.removeClass('active-menu');
                    $this.topMenuActive = false;
                }
                else {
                    $this.topMenu.find('> li > ul.active-menu').removeClass('active-menu').prev('a').removeClass('active-menu');
                    link.addClass('active-menu').next('ul').addClass('active-menu');
                    $this.topMenuActive = true;
                }
            }
            else {
                if($(e.target).is(':not(:input)')) {
                    $this.topMenu.find('.active-menu').removeClass('active-menu');
                    $this.topMenuActive = false;
                }
            }
        })
        .on('mouseenter', function() {
            var link = $(this);
    
            if(link.parent().parent().is($this.topMenu)&&$this.topMenuActive&&document.documentElement.clientWidth > 960) {
                var submenu = link.next('ul');
                
                $this.topMenu.find('.active-menu').removeClass('active-menu');
                link.addClass('active-menu');
                
                if(submenu.length) {
                    submenu.addClass('active-menu');
                }
            }
        });

        this.topMenu.find('li').clickOff(function() {
            $this.topMenu.find('.active-menu').removeClass('active-menu');
            $this.topMenuActive = false;
        });
    },
    
    removeMenuitem: function(id) {        
        this.expandedMenuitems = $.grep(this.expandedMenuitems, function(value) {
            return value !== id;
        });
    },
    
    addMenuitem: function(id) {
        if($.inArray(id, this.expandedMenuitems) === -1) {
            this.expandedMenuitems.push(id);
        }
    },
    
    saveMenuState: function() {
        $.cookie('modena_expandeditems', this.expandedMenuitems.join(','), {path:'/'});
    },
    
    clearMenuState: function() {
        $.removeCookie('modena_expandeditems', {path:'/'});
    },
    
    restoreMenuState: function() {
        var menucookie = $.cookie('modena_expandeditems');
        if(menucookie) {
            this.expandedMenuitems = menucookie.split(',');
            for(var i = 0; i < this.expandedMenuitems.length; i++) {
                var id = this.expandedMenuitems[i];
                if(id) {
                    var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g,"\\:"));
                    menuitem.addClass('active-menu-parent');
                    menuitem.children('a,ul').addClass('active-menu active-menu-restore');
                }             
            }
        }
    },
    
    isMobile: function() {
        return (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(window.navigator.userAgent));
    }
};


$(function() {
   Modena.init();
});

/**
 * PrimeFaces Spark Layout
 */
var Spark = {

    init: function() {
        this.menubar = $('#layout-menu');
        this.topmenu = $('#layout-topbar-menu');
        this.menumask = $('#menu-mask');
        this.focusedLink = null;
        this.focusedTopLink = null;

        // for keyboard navigation
        this.topmenu.attr('tabindex', '0');
        var topmenuItems = this.topmenu.find('a');
        for(var i=0; i<topmenuItems.size(); i++) {
            topmenuItems.eq(i).attr('tabindex', '-1');
        }

        this.bindEvents();
        this.bindKeyEvents();
    },

    bindEvents: function() {
        var $this = this;

        //main menu
        this.menubar.find('a').click(function() {
            var link = $(this),
                    submenu = link.next('ul');

            if(submenu.length) {
                if(link.next().hasClass('openSubMenu')) {
                    link.next().removeClass('openSubMenu');
                    $this.menubarActive = false;
                }
                else {
                    link.parent().parent().find('ul.openSubMenu').removeClass('openSubMenu');
                    link.next('.submenu').addClass('openSubMenu');
                    $this.menubarActive = true;
                }
            }
            else {
                $this.menubar.find('> li > ul').removeClass('openSubMenu');
                $this.menubarActive = false;
            }

            if($this.menubarActive) {
                $this.focusedLink = link;
                $this.menubar.trigger('focus');
            }
        })
                .on('mouseenter', function() {
                        var link = $(this),
                                menuItem = link.parent();

                        if($this.menubarActive && document.documentElement.clientWidth > 960 && menuItem.closest('ul').attr('id') === "layout-menu") {
                            menuItem.parent().find('ul.openSubMenu').removeClass('openSubMenu');
                            link.next('.submenu').addClass('openSubMenu');
                        }

                        if($this.menubarActive) {
                            if($this.focusedLink) {
                                $this.focusedLink.removeClass('ui-spark-focus');
                            }
                            $this.focusedLink = link;
                            $this.menubar.trigger('focus');
                        }
                    });

        this.menubar.find('li').clickOff(function() {
            $this.menubar.find('> li > ul').removeClass('openSubMenu');
            $this.menubarActive = false;
        });

        //topbar
        this.topmenu.find('a').click(function() {
            var link = $(this),
                    submenu = link.next('ul');

            if(submenu.length) {
                if(link.next().hasClass('openSubMenu')) {
                    link.next().removeClass('openSubMenu');
                    $this.topmenuActive = false;
                }
                else {
                    $this.topmenu.find('> li > ul.openSubMenu').removeClass('openSubMenu');
                    link.next('.submenu').addClass('openSubMenu');
                    $this.topmenuActive = true;
                }
            }
            else {
                $this.topmenu.find('> li > ul').removeClass('openSubMenu');
                $this.topmenuActive = false;
            }

            if($this.topmenuActive) {
                $this.focusedTopLink = link;
                $this.topmenu.trigger('focus');
            }
        })
                .on('mouseenter', function() {
                        var link = $(this),
                                topMenuItem = link.parent();

                        if($this.topmenuActive && document.documentElement.clientWidth > 960 && topMenuItem.closest('ul').attr('id') === "layout-topbar-menu") {
                            $this.topmenu.find('> li > ul.openSubMenu').removeClass('openSubMenu');
                            link.next('.submenu').addClass('openSubMenu');
                        }

                        if($this.topmenuActive) {
                            if($this.focusedTopLink) {
                                $this.focusedTopLink.removeClass('ui-spark-focus');
                            }
                            $this.focusedTopLink = link;
                            $this.topmenu.trigger('focus');
                        }
                    });

        this.topmenu.find('li').clickOff(function() {
            $this.topmenu.find('> li > ul').removeClass('openSubMenu');
            $this.topmenuActive = false;
        });

        $('#mobile-menu-button-gray').click(function(e){
            if($this.topmenu.is(':hidden')) {
                $this.topmenu.find('ul').removeClass('openSubMenu');
                $this.menubar.hide();
                $this.topmenu.show();
                $this.menumask.addClass('menu-mask-open');
            }
            else {
                $this.topmenu.hide();
                $this.menumask.removeClass('menu-mask-open');
            }

            e.preventDefault();
        });

        // mobile mode main menu open
        $('#mobile-menu-button').click(function(e) {
            if($this.menubar.is(':hidden')) {
                $this.menubar.find('ul').removeClass('openSubMenu');
                $this.menubar.show();
                $this.topmenu.hide();
                $this.menumask.addClass('menu-mask-open');
            }
            else {
                $this.menubar.hide();
                $this.menumask.removeClass('menu-mask-open');
            }

            e.preventDefault();
        });

        // closing all menus
        $('#menu-mask').click(function(){
            $this.menubar.hide();
            $this.topmenu.hide();
            $this.menumask.removeClass('menu-mask-open');
        });
    },

    bindKeyEvents: function() {
        var $this = this;

        // keyboard navigation for Layout Menu
        this.menubar.on('focus', function() {
            if(!$this.focusedLink) {
                $this.focusedLink = $this.menubar.find('a:first');
            }
            $this.focusedLink.addClass('ui-spark-focus');
        })
                .on('blur', function(){
                        if($this.focusedLink) {
                            $this.focusedLink.removeClass('ui-spark-focus');
                            $this.focusedLink = null;
                        }
                    })
                .on('keydown', function(e) {
                        if(!$this.focusedLink) {
                            return;
                        }

                        var keyCode = $.ui.keyCode;

                        switch(e.which) {
                            case keyCode.LEFT:
                                if($('#mobile-menu-button').is(':hidden')) {
                                    var prevMenuItem = $this.focusedLink.parent().prevAll("li[role='menuitem']:first");
                                    if(prevMenuItem.length) {
                                        var submenu = $this.focusedLink.next();
                                        if(submenu.length && submenu.hasClass('openSubMenu')) {
                                            submenu.removeClass('openSubMenu');
                                        }
                                        $this.focusItem(prevMenuItem);
                                    }
                                    e.preventDefault();
                                }
                                break;

                            case keyCode.RIGHT:
                                if($('#mobile-menu-button').is(':hidden')) {
                                    var nextMenuItem = $this.focusedLink.parent().nextAll("li[role='menuitem']:first");
                                    if(nextMenuItem.length) {
                                        var submenu = $this.focusedLink.next();
                                        if(submenu.length && submenu.hasClass('openSubMenu')) {
                                            submenu.removeClass('openSubMenu');
                                        }
                                        $this.focusItem(nextMenuItem);
                                    }
                                    e.preventDefault();
                                }
                                break;

                            case keyCode.UP:
                                if((!$this.isMenuRoot($this.focusedLink.parent())) || ($this.isMenuRoot($this.focusedLink.parent()) && $('#mobile-menu-button').is(':visible'))) {
                                    var prevItem = $this.focusedLink.parent().prevAll("li[role='menuitem']:first"),
                                            itemToFocus = null;
                                    if(!prevItem.length && $('#mobile-menu-button').is(':visible') && !$this.focusedLink.parent().parent().hasClass('submenu')) {
                                        $('#mobile-menu-button').focus();
                                    }
                                    else {
                                        if(prevItem.length) {
                                            if($this.isMenuRoot(prevItem) && $('#mobile-menu-button').is(':visible')) {
                                                var temp = prevItem.find('ul.openSubMenu:first > li:last');
                                                if(temp.length) {
                                                    prevItem = temp;
                                                }
                                            }

                                            itemToFocus = prevItem.find('ul.openSubMenu:last').children('li:last');

                                            if(!itemToFocus.length) {
                                                itemToFocus = prevItem;
                                            }
                                        }
                                        else {
                                            itemToFocus = $this.focusedLink.parent().closest('ul.submenu').parent('li');
                                        }

                                        if(itemToFocus.length) {
                                            $this.focusItem(itemToFocus);
                                        }
                                    }
                                }
                                e.preventDefault();
                                break;

                            case keyCode.DOWN:
                                var subMenu = $this.focusedLink.next('ul'),
                                        itemToFocus = null;
                                if(subMenu.length && subMenu.hasClass('openSubMenu')) {
                                    itemToFocus = subMenu.children('li:first');
                                }
                                else {
                                    var nextItem = $this.searchDownInMenu($this.focusedLink.parent());
                                    if(nextItem) {
                                        itemToFocus = nextItem;
                                    }
                                }

                                if(itemToFocus && itemToFocus.length) {
                                    $this.focusItem(itemToFocus);
                                }
                                e.preventDefault();
                                break;

                            case keyCode.ENTER:
                            case keyCode.NUMPAD_ENTER:
                            case keyCode.SPACE:
                                $this.focusedLink.trigger('click');
                                var href = $this.focusedLink.attr('href');
                                if(href && href !== '#') {
                                    window.location.href = href;
                                }
                                break;
                        }
                    });

        // keyboard navigation for Top bar
        this.topmenu.on('focus', function(){
            if(!$this.focusedTopLink) {
                if($('#mobile-menu-button-gray').is(':hidden')) {
                    $this.focusedTopLink = $this.topmenu.children("li:not('.menu-separator'):last").children('a');
                }
                else {
                    $this.focusedTopLink = $this.topmenu.children("li:not('.menu-separator'):first").children('a');
                }
            }
            $this.focusedTopLink.addClass('ui-spark-focus');
        })
                .on('blur', function() {
                        if($this.focusedTopLink) {
                            $this.focusedTopLink.removeClass('ui-spark-focus');
                            $this.focusedTopLink = null;
                        }
                    })
                .on('keydown', function(e) {
                        if(!$this.focusedTopLink) {
                            return;
                        }

                        var keyCode = $.ui.keyCode;

                        switch(e.which) {
                            case keyCode.LEFT:
                                if($('#mobile-menu-button-gray').is(':hidden')) {
                                    var prevMenuItem = $this.focusedTopLink.parent().nextAll("li:not('.menu-separator'):first");
                                    if(prevMenuItem.length) {
                                        var submenu = $this.focusedTopLink.next();
                                        if(submenu.length && submenu.hasClass('openSubMenu')) {
                                            submenu.removeClass('openSubMenu');
                                        }
                                        $this.focusItem(prevMenuItem, true);
                                    }
                                    e.preventDefault();
                                }
                                break;

                            case keyCode.RIGHT:
                                if($('#mobile-menu-button-gray').is(':hidden')) {
                                    var nextMenuItem = $this.focusedTopLink.parent().prevAll("li:not('.menu-separator'):first");
                                    if(nextMenuItem.length) {
                                        var submenu = $this.focusedTopLink.next();
                                        if(submenu.length && submenu.hasClass('openSubMenu')) {
                                            submenu.removeClass('openSubMenu');
                                        }
                                        $this.focusItem(nextMenuItem, true);
                                    }
                                    e.preventDefault();
                                }
                                break;

                            case keyCode.UP:
                                if((!$this.isTopMenuRoot($this.focusedTopLink.parent())) || ($this.isTopMenuRoot($this.focusedTopLink.parent()) && $('#mobile-menu-button-gray').is(':visible'))) {
                                    var prevItem = $this.focusedTopLink.parent().prevAll("li:not('.menu-separator'):first"),
                                            itemToFocus = null;

                                    if(!prevItem.length && $('#mobile-menu-button-gray').is(':visible') && !$this.focusedTopLink.parent().parent().hasClass('submenu')) {
                                        $('#mobile-menu-button-gray').focus();
                                    }
                                    else {
                                        if(prevItem.length) {
                                            if($this.isTopMenuRoot(prevItem) && $('#mobile-menu-button-gray').is(':visible')) {
                                                var temp = prevItem.find('ul.openSubMenu:first > li:last');
                                                if(temp.length) {
                                                    prevItem = temp;
                                                }
                                            }

                                            itemToFocus = prevItem.find('ul.openSubMenu:last').children('li:last');
                                            if(!itemToFocus.length) {
                                                itemToFocus = prevItem;
                                            }
                                        }
                                        else {
                                            itemToFocus = $this.focusedTopLink.parent().closest('ul.submenu').parent('li');
                                        }

                                        if(itemToFocus.length) {
                                            $this.focusItem(itemToFocus, true);
                                        }
                                    }
                                }
                                e.preventDefault();
                                break;

                            case keyCode.DOWN:
                                var subMenu = $this.focusedTopLink.next('ul'),
                                        itemToFocus = null;
                                if(subMenu.length && subMenu.hasClass('openSubMenu')) {
                                    itemToFocus = subMenu.children('li:first');
                                }
                                else {
                                    var nextItem = $this.searchDownInTopbar($this.focusedTopLink.parent());
                                    if(nextItem) {
                                        itemToFocus = nextItem;
                                    }
                                }

                                if(itemToFocus && itemToFocus.length) {
                                    $this.focusItem(itemToFocus, true);
                                }
                                e.preventDefault();
                                break;

                            case keyCode.ENTER:
                            case keyCode.NUMPAD_ENTER:
                            case keyCode.SPACE:
                                $this.focusedTopLink.trigger('click');
                                var href = $this.focusedTopLink.attr('href');
                                if(href && href !== '#') {
                                    window.location.href = href;
                                }
                                break;
                        }
                    });

        $('#mobile-menu-button-gray').keydown(function(e){
            var keyCode = $.ui.keyCode;

            if(e.which === keyCode.DOWN && $this.topmenu.is(':visible')) {
                $this.topmenu.focus();
                e.preventDefault();
            }

        });

        $('#mobile-menu-button').keydown(function(e) {
            var keyCode = $.ui.keyCode;

            if(e.which === keyCode.DOWN && $this.menubar.is(':visible')) {
                $this.menubar.focus();
                e.preventDefault();
            }
        });

    },

    isMenuRoot: function(item) {
        return item.parent().attr('id') === "layout-menu";
    },

    isTopMenuRoot: function(item) {
        return item.parent().attr('id') === "layout-topbar-menu";
    },

    focusItem: function(item, isTopmenu) {
        if(isTopmenu) {
            this.focusedTopLink.removeClass('ui-spark-focus');
            this.focusedTopLink = item.children('a');
            this.focusedTopLink.addClass('ui-spark-focus');
        }
        else {
            this.focusedLink.removeClass('ui-spark-focus');
            this.focusedLink = item.children('a');
            this.focusedLink.addClass('ui-spark-focus');
        }
    },

    searchDownInMenu: function(item) {
        var nextMenuItem = item.nextAll("li[role='menuitem']:first"),
                foundMenuItem = null;

        if(this.isMenuRoot(item) && $('#mobile-menu-button').is(':hidden')) { //for PC and mobile mode
            foundMenuItem = null;
        }
        else if(this.isMenuRoot(item) && $('#mobile-menu-button').is(':visible') && !nextMenuItem.length) { // for mobile mode
            foundMenuItem = null;
        }
        else if(nextMenuItem.length) {
            foundMenuItem = nextMenuItem;
        }
        else {
            foundMenuItem = this.searchDownInMenu(item.parent().closest('li'));
        }

        return foundMenuItem;
    },

    searchDownInTopbar: function(item) {
        var nextMenuItem = item.nextAll("li:not('.menu-separator'):first"),
                foundTopMenuItem = null;

        if(this.isTopMenuRoot(item) && $('#mobile-menu-button-gray').is(':hidden')) { //for PC and mobile mode
            foundTopMenuItem = null;
        }
        else if(this.isTopMenuRoot(item) && $('#mobile-menu-button-gray').is(':visible') && !nextMenuItem.length) { // for mobile mode
            foundTopMenuItem = null;
        }
        else if(nextMenuItem.length) {
            foundTopMenuItem = nextMenuItem;
        }
        else {
            foundTopMenuItem = this.searchDownInTopbar(item.parent().closest('li'));
        }
        return foundTopMenuItem;
    }

};

$(function() {
    Spark.init();
});



/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD (Register as an anonymous module)
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// Node/CommonJS
		module.exports = factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (arguments.length > 1 && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {},
			// To prevent the for loop in the first place assign an empty array
			// in case there are no cookies at all. Also prevents odd result when
			// calling $.cookie().
			cookies = document.cookie ? document.cookie.split('; ') : [],
			i = 0,
			l = cookies.length;

		for (; i < l; i++) {
			var parts = cookies[i].split('='),
				name = decode(parts.shift()),
				cookie = parts.join('=');

			if (key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));