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
        shadow: false
    };

    this.cfg.legend = {
	show:true, 
	rendererOptions: {numberRows: 1}, 
	location: 's', 
	placement: 'outsideGrid'
    };

    this.cfg.highlighter = {
        show: false
    };
}

function ltam_perf()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false
    };

    /*
    this.cfg.seriesDefaults = {
        show: true,     // whether to render the series.
	showMarker: true
    }
    */

    this.cfg.axesDefaults = {
        axes: {
            xaxis: {
                renderer: {
                    tickOptions: {
                        labelPosition: 'middle',
                        angle: 15
                    }
                }
            }
        }
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

function ltam_riskq3()
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
}

function ltam_donut()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false
    };
}




