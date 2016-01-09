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
        legend: false
    }

    this.cfg.highlighter = {
        show: true
    };
}

function ltam_meter()
{
    this.cfg.grid = {
        backgroundColor: 'transparent'
    };
}

function ltam_perf()
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

function ltam_line()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false
    };

}



