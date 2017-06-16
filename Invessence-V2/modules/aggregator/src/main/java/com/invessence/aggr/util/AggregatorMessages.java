package com.invessence.aggr.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * Created by abhangp on 12/27/2016.
 */
@Configuration
public class AggregatorMessages
{

   private static final Logger logger = Logger.getLogger(AggregatorMessages.class);

   public static int wsResIssueCode;
   public static String wsResIssueMsg;

   public static int wsOperationNACode;
   public static String wsOperationNAMsg;

   public static int wsIGenErrCode;
   public static String wsIGenErrMsg;

   public static int wsEGenErrCode;
   public static String wsEGenErrMsg;

   public static int wsUserRegIssueCode;
   public static String wsUserRegIssueMsg;

   public static int wsDBErrCode;
   public static String wsDBErrMsg;

   @Autowired
   public void setWsOperationNAMsgCode(@Value("${wsOperationNACode}")int wsOperationNACode)
   {
      AggregatorMessages.wsOperationNACode = wsOperationNACode;
   }
   @Autowired
   public void setWsOperationNAMsg(@Value("${wsOperationNAMsg}")String wsOperationNAMsg)
   {
      AggregatorMessages.wsOperationNAMsg = wsOperationNAMsg;
   }
   @Autowired
   public void setWsUserRegIssueCode(@Value("${wsUserRegIssueCode}")int wsUserRegIssueCode)
   {
      AggregatorMessages.wsUserRegIssueCode = wsUserRegIssueCode;
   }
   @Autowired
   public void setWsUserRegIssueMsg(@Value("${wsUserRegIssueMsg}") String wsUserRegIssueMsg)
   {
      AggregatorMessages.wsUserRegIssueMsg = wsUserRegIssueMsg;
   }
   @Autowired
   public void setWsDBErrCode(@Value("${wsDBErrCode}")int wsDBErrCode)
   {
      AggregatorMessages.wsDBErrCode = wsDBErrCode;
   }
   @Autowired
   public void setWsDBErrMsg(@Value("${wsDBErrMsg}")String wsDBErrMsg)
   {
      AggregatorMessages.wsDBErrMsg = wsDBErrMsg;
   }
   @Autowired
   public void setWsResIssueCode(@Value("${wsResIssueCode}") int wsResIssueCode)
   {
      AggregatorMessages.wsResIssueCode = wsResIssueCode;
   }
   @Autowired
   public void setWsResIssueMsg(@Value("${wsResIssueMsg}") String wsResIssueMsg)
   {
      AggregatorMessages.wsResIssueMsg = wsResIssueMsg;
   }
   @Autowired
   public void setWsIGenErrCode(@Value("${wsIGenErrCode}")int wsIGenErrCode)
   {
      AggregatorMessages.wsIGenErrCode = wsIGenErrCode;
   }
   @Autowired
   public void setWsIGenErrMsg(@Value("${wsIGenErrMsg}")String wsIGenErrMsg)
   {
      AggregatorMessages.wsIGenErrMsg = wsIGenErrMsg;
   }
   @Autowired
   public void setWsEGenErrCode(@Value("${wsEGenErrCode}")int wsEGenErrCode)
   {
      AggregatorMessages.wsEGenErrCode = wsEGenErrCode;
   }
   @Autowired
   public void setWsEGenErrMsg(@Value("${wsEGenErrMsg}")String wsEGenErrMsg)
   {
      AggregatorMessages.wsEGenErrMsg = wsEGenErrMsg;
   }

}
