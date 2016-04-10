package com.invessence.bean;

/**
 * Created by abhangp on 4/7/2016.
 */
public class DBResponse
{
   private int msgCode;
   private String msg;

   public DBResponse(int msgCode, String msg)
   {
      this.msgCode = msgCode;
      this.msg = msg;
   }

   public String getMsg()
   {
      return msg;
   }

   public void setMsg(String msg)
   {
      this.msg = msg;
   }

   public int getMsgCode()
   {
      return msgCode;
   }

   public void setMsgCode(int msgCode)
   {
      this.msgCode = msgCode;
   }

   @Override
   public String toString()
   {
      return "DBResponse{" +
         "msg='" + msg + '\'' +
         ", msgCode=" + msgCode +
         '}';
   }
}
