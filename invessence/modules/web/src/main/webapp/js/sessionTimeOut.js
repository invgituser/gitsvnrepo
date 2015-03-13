/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/28/14
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 * This is a session timeout script to monitor idel time.
 */

    var TIME = 120; // in seconds
    var countTimer = TIME;
    var processTimer;
    var timer_is_on = 0;
    var redirectPage = "/j_spring_security_logout";

    var countDownDiv = "dialog-countdown";
    var txtCountDown = null;
    if (!txtCountDown)
    txtCountDown = document.getElementById(countDownDiv);

    function startIdleMonitor() {
            countTimer = TIME;
            txtCountDown.innerHTML = countTimer;
            timeoutDialog.show();
            }
    function timedCount() {
            txtCountDown.innerHTML = countTimer;
            if (countTimer == 0) {
            stopCount();
            window.location.href = redirectPage;
            return;
            }
    countTimer = countTimer - 1;
    processTimer = setTimeout("timedCount()", 1000);
    }
    function doTimer() {
            if (!timer_is_on) {
            timer_is_on = 1;
            timedCount();
            }
    }
    function stopCount() {
            clearTimeout(processTimer);
            timer_is_on = 0;
            keepAlive();
            }
