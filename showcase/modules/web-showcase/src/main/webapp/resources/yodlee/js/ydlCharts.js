/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/19/14
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */

function ydl_pie_extensions()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false,
        legend: false,
        gridPadding: {top: 0, bottom: 38, left: 0, right: 0}
    };
}

function ydl_bar_extensions()
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



