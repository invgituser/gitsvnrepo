package com.invessence.web.data.common;

/**
 * Created by prashant on 4/26/2017.
 */
public class WebMenu
{
   String url;
   String access;
   String label;
   Integer level;
   Integer toplevel;
   Integer seq;
   String status;
   String destdir;
   String htmlpage;
   String command;

   public WebMenu(String url, String access, String label,
                  Integer level, Integer toplevel, Integer seq,
                  String status, String destdir, String htmlpage, String command)
   {
      this.url = url;
      this.access = access;
      this.label = label;
      this.level = level;
      this.toplevel = toplevel;
      this.seq = seq;
      this.status = status;
      this.destdir = destdir;
      this.htmlpage = htmlpage;
      this.command = command;
   }

   public WebMenu()
   {
      initWebMenu();
      setdefault();
   }

   public void initWebMenu()
   {
   }

   public void setdefault() {

   }

}
