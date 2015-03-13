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
        drawGridlines: false,
        shadow: true
    };

    this.cfg.seriesDefaults = {
        yaxis: 'y2axis',
        rendererOptions: {
            smooth: true
        },
        showMarker: false
    };
    this.cfg.axesDefaults = {
        rendererOptions: {
            baselineWidth: 1.5,
            baselineColor: '#444444',
            drawBaseline: false
        }
    };
    this.cfg.axis = {
        xaxis: {
            drawMajorGridlines: false
        },
        y2axis: {
            drawMajorGridlines: true,
            tickOptions: {
                formatString: "$%'d"
            },
            label: 'Growth',
            labelOptions: {
                fontFamily: 'Helvetica',
                fontSize: '14pt'
            }
        }

    };
    this.cfg.highlighter = {
        show: true,
        sizeAdjust: 12,
        tooltipLocation: 'n',
        tooltipAxes: 'y',
        tooltipFormatString: "$%'d",
        useAxesFormatters: false
    };

    this.cfg.fillBetween = {
         series1: 0,
         series2: 1,
         color: "rgba(167, 201, 194, 0.7)",
         baseSeries: 0,
         fill: true
    };
}


