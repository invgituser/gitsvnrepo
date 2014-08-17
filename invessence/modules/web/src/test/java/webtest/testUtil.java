package webtest;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 3/13/14
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 3/13/14
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

import com.invessence.util.*;

import static java.lang.String.valueOf;

public class testUtil
{
   public static void main(String[] args) throws Exception
   {
      testRedirect(args);
   }

   public static void testRedirect(String[] args) {
      UserValidation userv = new UserValidation();
      String URL="http://www.yahoo.com?redirect";
      Map <String,String> obj = new HashMap<String, String>();
      obj.put("key1","Value1");
      obj.put("key2","Value2");
      obj.put("key3","Value3");
      userv.redirect(URL,obj);

   }
}



