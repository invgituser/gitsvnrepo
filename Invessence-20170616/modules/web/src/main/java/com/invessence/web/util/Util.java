package com.invessence.web.util;
/*

*/
/* Prashant 1/7/2014 Both Util and UserValidation is merged with WebUtil *//*


//public class Util {
*/
/*

   UserValidation uvalidate = new UserValidation();

    public static boolean isNull(String val) {

        if ( (val == null) || (val.equals("")) )
            return true;
        else
            return false;
    }


    public static boolean isInteger(String input) {

        try {
            Integer.parseInt(input);

        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }


	public static String getValByAttr(HttpServletRequest request, String arg, String defaultVal) {

   	    String val = (String) request.getAttribute(arg);

	    if ( Util.isNull(val) ) {
	    	return defaultVal;
	    } else {
    	    return val;
	    }
	}


	public static int getIntValByAttr(HttpServletRequest request, String arg, int defaultVal) {

		String integerVal = (String) request.getAttribute(arg);

		if (isInteger(integerVal)){
			return Integer.parseInt(integerVal);
		} else {
			return defaultVal;
		}

	}

	public static int getIdxByAttribute(HttpServletRequest request, String idxVal) {

   	    String idx = (String) request.getAttribute(idxVal);

	    if ( Util.isNull(idx) ) {
	    	idx = "0";
	    }

    	return Integer.parseInt(idx);
	}


*//*

*/
/*
     public static ProjUtil getProjUtil(HttpServletRequest request) {

         WebApplicationContext ctx =
     	    WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
         return (ProjUtil) ctx.getBean("projUtil");
      }

*//*
*/
/*



     public String getSource(String urlAddress) {

         try {
             URL url = new URL(urlAddress);
             String host = url.getHost();
             //System.out.println("host = " + host);

             String source = host;

             if ( (host != null) && (host.length() > 4) ) {
                 String startStr = host.substring(0, 3);
                 if (startStr.equalsIgnoreCase("www")) {
                     source = host.substring(4);
                 }
             }

             //System.out.println("source = " + source);
             return source;
         } catch (Exception e) {

             System.out.println(e);
             return "";
         }
     }

   public String getMacAddress(){

      InetAddress ip;
      try {

         ip = InetAddress.getLocalHost();
         //System.out.println("Current IP address : " + ip.getHostAddress());

         NetworkInterface network = NetworkInterface.getByInetAddress(ip);

         byte[] mac = network.getHardwareAddress();

         //System.out.print("Current MAC address : ");

         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
         }
         //System.out.println(sb.toString());
         return sb.toString();

      } catch (UnknownHostException e) {

         e.printStackTrace();

      } catch (SocketException e){

         e.printStackTrace();

      }
      return null;

   }

   public String getClientIpAddr(HttpServletRequest request) {
      String ip = request.getHeader("X-Forwarded-For");
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("HTTP_CLIENT_IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getRemoteAddr();
      }
      //System.out.println("Current IP:" + ip);
      return ip;
   }

   public void setCookie(String cookieName, String info, Map map) {
      FacesContext.getCurrentInstance()
         .getExternalContext()
         .addResponseCookie(cookieName, info, map);
   }


   public Map<String, Object> getCookie () {
      Map<String, Object> requestCookieMap = FacesContext.getCurrentInstance()
         .getExternalContext()
         .getRequestCookieMap();
      return requestCookieMap;
   }

   public Integer randomGenerator(Integer min, Integer max) {
      Random generator = new Random();
      try {
         Integer newNum = generator.nextInt(max);
         if (newNum < min)
            newNum = min;
         return newNum;
      }
      catch (Exception ex) {
         return (max);
      }
   }

   public String[][] getRandomQuestionAns() {
      Integer [] digits={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
      String [] number={"zero","one","two","three","four","five","six","seven","eight","nine","ten"};
      String [] operator={"+","-"};
      Random generator = new Random();
      String calcQuestion;
      Integer calcAns;
      String [][]response = new String[1][2];
      try {
         Integer first=generator.nextInt(21);
         Integer second=generator.nextInt(11);
         Integer third=generator.nextInt(2);
         // Don't allow subtration of the first < second number
         if ((first < second) && (third > 0))
            third = 0;
         if (third > 0)
            third = 1;

         if (third == 0)
            calcAns = first + second;
         else
            calcAns = first - second;

         calcQuestion = digits[first].toString() + " " + operator[third] + " " + number[second];
         // System.out.println("Do following:" + question + " -> " + ans );
         response[0][0] = calcQuestion;
         response[0][1] = calcAns.toString();
         return response;

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return response;
   }

   public Boolean redirecttoMessagePage(String type, String title, String body) {
      String spMsg = "";
      Map<String,String> args = new HashMap<String, String>();
      try {
         if (type != null)
            args.put("type",type);
         if (title != null)
            args.put("title",title);
         if (body != null) {
            args.put("message",body);
            uvalidate.redirect("/message.xhtml?faces-redirect=true",args);
         }
      }
      catch (Exception ex) {
         args.put("message","mbse");
         args.put("type","Error");
         args.put("title","mtse");
         uvalidate.redirect("/message.xhtml?faces-redirect=true",args);
         return false;
      }
      return true;
   }

*//*


//}
*/
