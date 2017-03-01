/**
 * Created by Akhilesh on 2/24/2017.
 */
function drawProjectionChart(pageInfo){

$(function ()
  {
      var chartValue = "";
        if(pageInfo == 'page5'){
            chartValue = document.getElementById('ceForm:projectionChart').value;
        }else if(pageInfo == 'final2'){
            chartValue = document.getElementById('ceForm:performanceChart').value;
        }

      console.log(chartValue);
      //Fetched java beanValueChart in java script
      var maxGraghPlot = parseInt($.parseJSON(chartValue).maxGraghPlot);
      var maxGraghPlot1 = maxGraghPlot + 10000;
      var minGrowth = parseInt($.parseJSON(chartValue).minGrowth);

      var xCategories = $.parseJSON(chartValue).goalYearValue;

      $('#progressChart').highcharts(
              {

                  title: {
                      text: ''
                  },
                  xAxis: {

                      // tickInterval: 'auto',
                      labels: {
                          formatter: function ()
                          {
                              return xCategories[this.value];
                          },
                          pointStart: -1,
                          startOnTick: false,
                          endOnTick: false,
                          minPadding: 0,
                          maxPadding: 0,
                          rotation: 0,
                          align: 'center',
                          style: {
                              fontSize: '10px',
                              fontFamily: 'Verdana, sans-serif',
                              fontWeight: 'bold',
                              color: '#808080'

                          }
                      },
                  },
                  yAxis: {
                      //tickInterval: 'auto',//20000,
                      //minorTickInterval: 'auto',
                      min: minGrowth,
                      max: maxGraghPlot1,
                      lineWidth: 1,
                      //tickWidth: 1,
                      opposite: true,
                      title: {
                          text: '',
                          style: {
                              fontSize: '10px',
                              fontFamily: 'Verdana, sans-serif',
                              fontWeight: 'bold',
                              backgroundColor: '#808080'
                          }
                      },
                      labels: {
                          formatter: function ()
                          {
                              return '$'
                                      + this.axis.defaultLabelFormatter
                                              .call(this);
                          },
                          style: {
                              fontSize: '10px',
                              fontFamily: 'Verdana, sans-serif',
                              fontWeight: 'bold',
                              color: '#808080'

                          }
                      },
                  },
                  legend: {

                      floating: false,
                      shadow: true,
                      enabled: true,
                  },
                  plotOptions: {
                      series: {
                          marker: {
                              enabled: false
                          },
                          events: {
                              legendItemClick: function ()
                              {
                                  return false;
                              }
                          }
                      }
                  },
                  credits: {
                      enabled: true
                  },
                  tooltip: {
                      style: {
                          fontSize: '10pt',
                          fontFamily: 'Verdana',
                      },
                      formatter: function ()
                      {

                          return 'Year : '
                                  + '<b>'
                                  + xCategories[this.x]
                                  + '</b>'
                                  + '<br/>'
                                  + ' Average Performance : '
                                  + '<b>'
                                  + '$'
                                  + Highcharts.numberFormat(this.point.y,
                                                            0, ',', ',') + '</b>';
                      },
                      crosshairs: true,
                      //shared: true,
                  },
                  exporting: {
                      enabled: false
                  },
                  series: [
                      {

                          name: 'Average market performance',
                          fontWeight: 'normal',
                          lineColor: '#4d0000',
                          color: '#4d0000',
                          type: 'spline',
                          zIndex: 3,
                          data: $.parseJSON(chartValue).goalAvgValue,
                          zIndex: 3,
                      },
                      {
                          name: '50% probability range',
                          fontWeight: 'normal',
                          data: $.parseJSON(chartValue).goalLowerValue,
                          type: 'areasplinerange',
                          lineWidth: 0,
                          color: '#54a685', //Highcharts.getOptions().colors[0]'', //#3498DB
                          //fillOpacity: 0.7,
                          border: 0,
                          zIndex: 2,
                          enableMouseTracking: false,
                      },
                      {
                          name: '95% probability range',
                          fontWeight: 'normal',
                          data: $.parseJSON(chartValue).goalUpperValue,
                          type: 'areasplinerange',
                          lineWidth: 0,
                          color: '#dbe6e0', //Highcharts.getOptions().colors[0],  //#ABEBC6
                          //fillOpacity: 0.7,
                          border: 0,
                          zIndex: 1,
                          enableMouseTracking: false,
                      },
                  ]
              });

  });

}