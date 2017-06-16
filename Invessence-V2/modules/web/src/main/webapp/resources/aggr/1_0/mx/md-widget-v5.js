/**
 * Created by abhangp on 12/13/2016.
 */
(function () {
    var MoneyDesktopWidgetLoader = function (options) {
        var baseUrlPattern = /^https?:\/\/[^\/]+/i;
        var widgetBaseUrl = options.url && options.url.match(baseUrlPattern) ? options.url.match(baseUrlPattern)[0] : null;

        this.options = options || {};

        this.options.autoload = typeof options.autoload === 'undefined' || options.autoload;
        this.options.id = options.id || 'md-widget';
        this.options.postMessageOrigin = options.postMessageOrigin || widgetBaseUrl || 'https://widgets.moneydesktop.com';

        if (this.options.autoload) {
            window.addEventListener('load', this.load.bind(this), false);
        }

        window.addEventListener('message', this.onPostMessage.bind(this), false);
    };

    MoneyDesktopWidgetLoader.prototype.load = function () {
        var targetElement = document.getElementById(this.options.id);
        var deepLink = this.options.deepLink ? '#' + this.options.deepLink : '';
        var url = this.options.url;
        var width = this.options.width || '100%';
        var height = this.options.height || '600';
        var iframe = document.createElement('iframe');

        iframe.setAttribute('width', width);
        iframe.setAttribute('height', height);
        iframe.setAttribute('border', '0');
        iframe.setAttribute('frame', '0');
        iframe.setAttribute('frameborder', '0');
        iframe.setAttribute('allowTransparency', 'true');
        iframe.setAttribute('src', url + deepLink);
        iframe.setAttribute('marginheight' , '0');
        iframe.setAttribute('marginwidth' , '0');

        targetElement.innerHTML = '';
        targetElement.appendChild(iframe);

        iframe.addEventListener('load', function () {
            targetElement.className += ' md-widget-loaded';
        }, false);

        this.iframeDetails = {
            iframe: iframe,
            loaded: false,
            targetElement: targetElement
        };
    };

    MoneyDesktopWidgetLoader.prototype.onPostMessage = function (evt) {
        var data = false;

        if (evt.source === this.iframeDetails.iframe.contentWindow) {
            try {
                data = JSON.parse(evt.data);
            } catch (e) {
                console.error(e);
            }

            if (data && data.moneyDesktop && data.type === 'load') {
                this.iframeDetails.loaded = true;

                this.postMessageToMoneyDesktop({
                                                   type: 'configure',
                                                   options: this.options
                                               });
            }
        }
    };

    MoneyDesktopWidgetLoader.prototype.postMessageToMoneyDesktop = function (data) {
        this.iframeDetails.iframe.contentWindow.postMessage(JSON.stringify(data), this.options.postMessageOrigin);
    };

    MoneyDesktopWidgetLoader.prototype.logout = function () {
        this.postMessageToMoneyDesktop({ type: 'logout'});
    };

    MoneyDesktopWidgetLoader.prototype.ping = function () {
        this.postMessageToMoneyDesktop({ type: 'ping' });
    };

    window.MoneyDesktopWidgetLoader = MoneyDesktopWidgetLoader;
}).call(this);


