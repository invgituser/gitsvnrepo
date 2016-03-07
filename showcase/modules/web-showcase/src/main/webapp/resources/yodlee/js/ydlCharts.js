/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/19/14
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */

function ydlpie_summary() {
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false,
        legend: false,
        gridPadding: {top: 0, bottom: 38, left: 0, right: 0}
    };
}

function ydldonut_details() {
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false,
        legend: false,
        gridPadding: {top: 0, bottom: 38, left: 0, right: 0}
    };
}

function ydlbar_types() {
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false
    };

    this.cfg.axesDefaults = {
        show: false,
        showTicks: true,
        showTickMarks: true,
        tickOptions: {
            showGridline: false
        },
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

function ydlbar_summary() {
    this.cfg.grid = {
        backgroundColor: 'transparent',
        drawBorder: false,
        shadow: false
    };

    this.cfg.axesDefaults = {
        show: false,
        showTicks: true,
        showTickMarks: true,
        tickOptions: {
            showGridline: false
        },
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
     this.cfg.axesDefaults = {
     show: false,
     showTicks: true,
     showTickMarks: false,
     tickOptions: {
     showGridline: false
     }
     }
     this.cfg.seriesDefaults = {
     show: true,     // whether to render the series.
     showMarker: true
     }
     */
}


