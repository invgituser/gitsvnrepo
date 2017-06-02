/**
 * Created with IntelliJ IDEA.
 * User: Sagar
 * Date: 5/25/17
 * Time: 9:24 !M
 * To handle session management.
 */

var vTimeoutCountdown = $('#idCountdownTime').val();
var vTimer;
function idleDialog()
{
    try
    {
        debugger;
        $('#idConfirmExt').addClass('confirmDialogueBlock');
        if ($('#idIsUserLoggedIn').val() == 'false')
        {

            console.log("idleDialog If ");
            $('.ui-confirm-dialog-message').html('Your session has been timeout.');
            $('#idLogout').hide();
            $('#idContinue').hide();
            $('#idOk').show();
        }
        else
        {
            console.log("idleDialog else ");
            vTimeoutCountdown = $('#idCountdownTime').val();
            $('.ui-confirm-dialog-message').html('You are about to get Inactivated, in ' + vTimeoutCountdown + ' seconds.');
            $('#idLogout').show();
            $('#idContinue').show();
            $('#idOk').hide();
            vTimer = setInterval(myTimer, 1100);
        }
        $('#confirmDialog').show();
    }
    catch (e)
    {
        console.error("idleDialog [" + e + "]");
    }
}

function SessionExpireEvent()
{
    try
    {
        console.log("SessionExpireEvent ");
        debugger;
        clearInterval(vTimer);
        $('.idLogoutCommon').click();
    }
    catch (e)
    {
        console.error("SessionExpireEvent [" + e + "]");
    }
}

function fnDisableDialog()
{
    try
    {
        console.log("fnDisableDialog ");
        debugger;
        clearInterval(vTimer);
        $('#confirmDialog').hide();
        $('#idConfirmExt').removeClass('confirmDialogueBlock');
        $('#idLogout').show();
        $('#idContinue').show();
        $('#idOk').show();
    }
    catch (e)
    {
        console.error("fnDisableDialog [" + e + "]");
    }
}
function fnDisableVisitorDialog()
{
    try
    {
        console.log("fnDisableVisitorDialog ");
        console.log("Inside");
        location.replace($('#idHomeURL').val());
        console.log("bye bye");
    }
    catch (e)
    {
        console.error("fnDisableVisitorDialog [" + e + "]");
    }
}

function myTimer()
{
    try
    {
        console.log("myTimer " + vTimeoutCountdown);
        if (vTimeoutCountdown > 0)
        {
            vTimeoutCountdown = vTimeoutCountdown - 1;
            $('.ui-confirm-dialog-message').html('You are about to get Inactivated, in ' + vTimeoutCountdown + ' seconds.');
        }
        if (vTimeoutCountdown == 0)
        {
            SessionExpireEvent();
        }
    }
    catch (e)
    {
        console.error("myTimer [" + e + "]");
    }
}

