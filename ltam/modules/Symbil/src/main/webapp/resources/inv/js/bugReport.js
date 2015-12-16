/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/19/14
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */


function riskq3()
{
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false
    };

    this.cfg.seriesDefaults = {
        pointLabels: { show: true }
    }

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




