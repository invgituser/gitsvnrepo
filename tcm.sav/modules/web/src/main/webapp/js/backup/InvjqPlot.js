/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/15/13
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */
/*rules for the plot target div.  These will be cascaded down to all plot elements according to css rules*/
.
jqplot - target
{
    position:relative;
    color:#
    666666;
    font - family
:
    "Trebuchet MS", Arial, Helvetica, sans - serif;
    font - size
:
    1e
    m;
    /*
     height:300px;width:400px;*/
}
/*rules applied to all axes*/.
jqplot - axis
{
    font - size
:
    0.75e
    m;
}
.
jqplot - xaxis
{
    margin - top
:
    10
    px;
}
.
jqplot - x2axis
{
    margin - bottom
:
    10
    px;
}
.
jqplot - yaxis
{
    margin - right
:
    10
    px;
}
.
jqplot - y2axis,
.
jqplot - y3axis,
.
jqplot - y4axis,
.
jqplot - y5axis,
.
jqplot - y6axis,
.
jqplot - y7axis,
.
jqplot - y8axis,
.
jqplot - y9axis
{
    margin - left
:
    10
    px;
    margin - right
:
    10
    px;
}
/*rules applied to all axis tick divs*/.
jqplot - axis - tick,
.
jqplot - xaxis - tick,
.
jqplot - yaxis - tick,
.
jqplot - x2axis - tick,
.
jqplot - y2axis - tick,
.
jqplot - y3axis - tick,
.
jqplot - y4axis - tick,
.
jqplot - y5axis - tick,
.
jqplot - y6axis - tick,
.
jqplot - y7axis - tick,
.
jqplot - y8axis - tick,
.
jqplot - y9axis - tick
{
    position:absolute;
}
.
jqplot - xaxis - tick
{
    top:0
    px;
    /* initial position untill tick is drawn in proper place */
    left:15
    px;
    /*    padding-top:10px;*/
    vertical - align
:
    top;
}
.
jqplot - x2axis - tick
{
    bottom:0
    px;
    /* initial position untill tick is drawn in proper place */
    left:15
    px;
    /*    padding-bottom:10px;*/
    vertical - align
:
    bottom;
}
.
jqplot - yaxis - tick
{
    right:0
    px;
    /* initial position untill tick is drawn in proper place */
    top:15
    px;
    /*    padding-right:10px;*/
    text - align
:
    right;
}
.
jqplot - yaxis - tick.jqplot - breakTick
{
    right:-20
    px;
    margin - right
:
    0
    px;
    padding:1
    px
    5
    px
    1
    px
    5
    px;
    /*	background-color:white;*/
    z - index
:
    2;
    font - size
:
    1.5e
    m;
}
.
jqplot - y2axis - tick,
.
jqplot - y3axis - tick,
.
jqplot - y4axis - tick,
.
jqplot - y5axis - tick,
.
jqplot - y6axis - tick,
.
jqplot - y7axis - tick,
.
jqplot - y8axis - tick,
.
jqplot - y9axis - tick
{
    left:0
    px;
    /* initial position untill tick is drawn in proper place */
    top:15
    px;
    /*    padding-left:10px;*/
    /*    padding-right:15px;*/
    text - align
:
    left;
}
.
jqplot - meterGauge - tick
{
    font - size
:
    0.75e
    m;
    color:#
    999999;
}
.
jqplot - meterGauge - label
{
    font - size
:
    1e
    m;
    color:#
    999999;
}
.
jqplot - xaxis - label
{
    margin - top
:
    10
    px;
    font - size
:
    11
    pt;
    position:absolute;
}
.
jqplot - x2axis - label
{
    margin - bottom
:
    10
    px;
    font - size
:
    11
    pt;
    position:absolute;
}
.
jqplot - yaxis - label
{
    margin - right
:
    10
    px;
    /*    text-align:center;*/
    font - size
:
    11
    pt;
    position:absolute;
}
.
jqplot - y2axis - label,
.
jqplot - y3axis - label,
.
jqplot - y4axis - label,
.
jqplot - y5axis - label,
.
jqplot - y6axis - label,
.
jqplot - y7axis - label,
.
jqplot - y8axis - label,
.
jqplot - y9axis - label
{/*    text-align:center;*/
    font - size
:
    11
    pt;
    position:absolute;
}
table.jqplot - table - legend
{
    margin - top
:
    12
    px;
    margin - bottom
:
    12
    px;
    margin - left
:
    12
    px;
    margin - right
:
    12
    px;
}
table.jqplot - table - legend, table.jqplot - cursor - legend
{
    background - color
:
    rgba(255, 255, 255, 0.6);
    border:1
    px
    solid #cccccc;
    position:absolute;
    font - size
:
    0.75e
    m;
}
td.jqplot - table - legend
{
    vertical - align
:
    middle;
}
td.jqplot - seriesToggle
:
hover, td.jqplot - seriesToggle
:
active
{
    cursor:pointer;
}
td.jqplot - table - legend > div
{
    border:1
    px
    solid #cccccc;
    padding:1
    px;
}
div.jqplot - table - legend - swatch
{
    width:0
    px;
    height:0
    px;
    border - top - width
:
    5
    px;
    border - bottom - width
:
    5
    px;
    border - left - width
:
    6
    px;
    border - right - width
:
    6
    px;
    border - top - style
:
    solid;
    border - bottom - style
:
    solid;
    border - left - style
:
    solid;
    border - right - style
:
    solid;
}
.
jqplot - title
{
    top:0
    px;
    left:0
    px;
    padding - bottom
:
    0.5e
    m;
    font - size
:
    1.2e
    m;
}
table.jqplot - cursor - tooltip
{
    border:1
    px
    solid #cccccc;
    font - size
:
    0.75e
    m;
}
.
jqplot - cursor - tooltip
{
    border:1
    px
    solid #cccccc;
    font - size
:
    0.75e
    m;
    white - space
:
    nowrap;
    background:rgba(208, 208, 208, 0.5);
    padding:1
    px;
}
.
jqplot - highlighter - tooltip
{
    border:1
    px
    solid #cccccc;
    font - size
:
    0.75e
    m;
    white - space
:
    nowrap;
    background:rgba(208, 208, 208, 0.5);
    padding:1
    px;
}
.
jqplot - point - label
{
    font - size
:
    0.75e
    m;
    z - index
:
    2;
}
td.jqplot - cursor - legend - swatch
{
    vertical - align
:
    middle;
    text - align
:
    center;
}
div.jqplot - cursor - legend - swatch
{
    width:1.2e
    m;
    height:0.7e
    m;
}
.
jqplot - error
{/*   Styles added to the plot target container when there is an error go here.*/
    text - align
:
    center;
}
.
jqplot - error - message
{/*    Styling of the custom error message div goes here.*/
    position:relative;
    top:46 %;
    display:inline - block;
}
div.jqplot - bubble - label
{
    font - size
:
    0.8e
    m;
    /*    background:rgba(90%, 90%, 90%, 0.15);*/
    padding - left
:
    2
    px;
    padding - right
:
    2
    px;
    color:rgb(20 %, 20 %, 20 %);
}
div.jqplot - bubble - label.jqplot - bubble - label - highlight
{
    background:rgba(90 %, 90 %, 90 %, 0.7);
}
div.jqplot - noData - container
{
    text - align
:
    center;
    background - color
:
    rgba(96 %, 96 %, 96 %, 0.3);
}
