/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/19/14
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */

function ltam_pie()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false,
        legend: false,
        gridPadding: {top: 0, bottom: 38, left: 0, right: 0}
    };
}

function ltam_meter()
{
    this.cfg.grid = {
        backgroundColor: 'transparent'
    };
}

function ltam_bar()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false
    };

    this.cfg.axesDefaults = {
        show: false,
        showTicks: false,
        showTickMarks: false,
        tickOptions: {
            showGridline: false
        }
    };

}

function ltam_line()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false
    };

    this.cfg.seriesDefaults = {
        showMarker: false,
        markerOptions: {
            show: false,
            color: 'transparent',
            shadow: false },
        tickOptions : {
            showGridline: false
        }
    };

    this.cfg.axesDefaults = {
        tickOptions : {
            showGridline: false
        }
    };

    this.cfg.fillBetween = {
        series1: 0,
        series2: 1,
        color: "#7C8686",
        baseSeries: 0,
        fill: true
    };
}



