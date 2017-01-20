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
        gridPadding: {top: 0, bottom: 38, left: 0, right: 0}
    };
    this.cfg.axis ={
        showLabels: false
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

function line_extensions()
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
        tickOptions: {
            showGridline: false
        }
    };

    this.cfg.axesDefaults = {
        tickOptions: {
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

function goals_extensions()
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
        tickOptions: {
            showGridline: false
        }
    };

    this.cfg.axesDefaults = {
        tickOptions: {
            showGridline: false
        }
    };

    this.cfg.fillBetween = {
        series1: 2,
        series2: 3,
        color: "#00FF00",
        baseSeries: 0,
        fill: true
    };
}

function riskq()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false
    };

    this.cfg.axesDefaults = {
        axes: {
            xaxis: {
                renderer: {
                    tickOptions: {
                        labelPosition: 'middle'
                    }
                }
            }
        }
    };

    /*
     this.cfg.seriesDefaults = {
     show: true,     // whether to render the series.
     showMarker: true
     }
     */


}



