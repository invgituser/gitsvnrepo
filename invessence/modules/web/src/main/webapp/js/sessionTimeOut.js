/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/28/14
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 * This is a session timeout script to monitor idel time.
 */
<script type="text/javascript">

var countDownTime; //in miliseconds
var sessionTime = 61000; //in miliseconds
var whenToShowAdditionalTimeDialog = 60000; //in miliseconds
var isAdditionalTimeDialogShowed = false;
var countDownStarted = false;
var minute;
var second;
function countDownInit() {
        countDownTime = (new Date()).getTime();
        minute = 1;
        second = 00;
        countDownStarted = false;
        }

function checkCountDown() {
        if (document.getElementById('loggedInUserId') == null) {
        return;
        }
var now = (new Date()).getTime();
if (now - countDownTime > (sessionTime - whenToShowAdditionalTimeDialog)) {
        additionalTimeDialog.show();
        countDownStarted = true;
        }
if (countDownStarted) {
        second = second - 1;
        if (0 > second) {
        second = 59;
        minute = minute - 1;
        }
if (0 > minute) {
        exit();
        return;
        }
document.getElementById('additionalTimeDialog:minuteOutput').innerHTML = minute;
document.getElementById('additionalTimeDialog:secondOutput').innerHTML = second;
}
}
function exit() {
        document.location.href = 'j_spring_security_logout';
        }
window.setInterval(checkCountDown, 1000);

</script>