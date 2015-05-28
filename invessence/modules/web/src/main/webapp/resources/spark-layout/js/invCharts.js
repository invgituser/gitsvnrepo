/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/19/14
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */

function pie_extensions()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false,
        legend: false,
        gridPadding: {top:0, bottom:38, left:0, right:0}
    };
}

function line_extensions()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: true
    };

    this.cfg.seriesDefaults = {
        rendererOptions: {
            smooth: true
        },
        showMarker: false
    };
    this.cfg.axis = {
        yaxis: {
            labelOptions: {
                fontSize: '14pt'
            }
        }

    };
    this.cfg.highlighter = {
        show: true,
        sizeAdjust: 12,
        tooltipAxes: 'y',
        tooltipFormatString: "$%'d",
        useAxesFormatters: false
    };

}

function meter_extensions()
{
    this.cfg.grid = {
        backgroundColor: 'transparent'
    };
}

function bar_extensions()
{
    this.cfg.grid = {
        backgroundColor: 'transparent'
    };
}



