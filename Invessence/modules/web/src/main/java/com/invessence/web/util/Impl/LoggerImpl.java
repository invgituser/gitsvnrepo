package com.invessence.web.util.Impl;

import java.io.Serializable;
import java.util.*;

import com.invessence.web.util.*;
import org.apache.commons.logging.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/14/15
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoggerImpl implements Serializable, Logger
{
   protected final Log logger = LogFactory.getLog(getClass());

   public LoggerImpl()
   {
   }

   public Log getLogger()
   {
      return logger;
   }
}
