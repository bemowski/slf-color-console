package net.jmatrix.console.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
   static Logger log=LoggerFactory.getLogger(TestMain.class);
   
   public static void main(String args[]) {
      
      ColorConsoleLogger.setGlobalLevel(Level.ALL);
      
      log.trace("Trace Message");
      log.debug("Debug Messgae");
      log.info("Info Message");
      
      log.warn("Warn Message");
      
      log.error("Error Message", new Exception("foo!"));
   }
}
