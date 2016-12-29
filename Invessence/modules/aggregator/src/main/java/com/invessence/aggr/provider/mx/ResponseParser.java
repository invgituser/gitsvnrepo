package com.invessence.aggr.provider.mx;

import com.fasterxml.jackson.databind.*;
import com.invessence.aggr.provider.mx.bean.Error;
import com.invessence.aggr.provider.mx.bean.User;

/**
 * Created by abhangp on 12/21/2016.
 */
public class ResponseParser<T>
{
//   T t;
//   public ResponseParser(T t){
//      this.t=t;
//   }

   public T parse(Class<T> theClass, String resStr){
      T t=null;
      try
      {
         ObjectMapper mapper = new ObjectMapper();
         mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

            t= mapper.readValue(resStr, theClass);

      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());
//         e.printStackTrace();
      }
      return t;
   }

   public static void main(String[] args)
   {
      User user=new ResponseParser<User>().parse(User.class, "{\"error\":{\"code\":4002,\"message\":\"param is missing or the value is empty: user\"}}");
      System.out.println("user = " + user);

      Error error=new ResponseParser<Error>().parse(Error.class,"{\"error\":{\"code\":4002,\"message\":\"param is missing or the value is empty: user\"}}");
      System.out.println("error = " + error);
   }
}
