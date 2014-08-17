/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/14/13
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
/**
 * Bean validator for Password
 */
PrimeFaces.validator['Password'] = {

    pattern: /\S+@\S+/,

    MESSAGE_ID: 'Password does not batch required criteria.',

    validate: function (element, value)
    {
        var vc = PrimeFaces.util.ValidationContext;

        if (!this.pattern.test(value))
        {
            var msgStr = element.data('p-password-msg'),
                    msg = msgStr ? {summary: msgStr, detail: msgStr} : this.MESSAGE_ID;

            throw msg;
        }
    }
};

