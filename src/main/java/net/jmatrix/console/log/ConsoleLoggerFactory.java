package net.jmatrix.console.log;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class ConsoleLoggerFactory implements ILoggerFactory {
   Map<String, Logger> map=new HashMap<String, Logger>();
   
   @Override
   public Logger getLogger(String name) {
      Logger logger=map.get(name);
      if (logger == null) {
         synchronized(this) {
            logger=map.get(name);
            if (logger == null) {
               logger=buildLogger(name);
               map.put(name, logger);
            }
         }
      }
      return logger;
   }
   
   private Logger buildLogger(String name) {
      return new ColorConsoleLogger(name);
   }
   
   public Logger getLoggerIfExists(String name) {
      return map.get(name);
   }
}
