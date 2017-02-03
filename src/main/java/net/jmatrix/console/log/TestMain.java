package net.jmatrix.console.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class TestMain {
   static Logger log=LoggerFactory.getLogger(TestMain.class);
   
   public static void main(String args[]) {
      
      logLevels();
      
      ColorConsoleLogger.setGlobalLevel(Level.TRACE);
      
      log.trace("Trace Message");
      log.debug("Debug Messgae");
      log.info("Info Message");
      
      log.warn("Warn Message");
      
      log.error("Error Message", new Exception("foo!"));
   }
   
   static void logLevels() {

      // slf4j.Level is an Enum with values
      // DEBUG
      //   ERROR
      // INFO
      // TRACE
      //   WARN
      
      // java.util.logging.Level is a class, with values
      // SEVERE (highest value)
      //   WARNING
      //   INFO
      // CONFIG
      // FINE
      // FINER
      // FINEST (lowest value)
      
      
      //  SEVERE  -- ERROR
      //  WARNING -- WARN
      //  INFO    -- INFO
      //  CONFIG  -- DEBUG
      //  FINE    -- DEBUGde
      //  FINER   -- TRACE
      //  FINEST  -- TRACE
      
      
      StringBuilder sb=new StringBuilder();
      sb.append("java.util.logging.Level (class)\n");
      sb.append("-------------------------------\n");
      sb.append("SEVERE   "+java.util.logging.Level.SEVERE.intValue()+"\n");
      sb.append("WARNING  "+java.util.logging.Level.WARNING.intValue()+"\n");
      sb.append("INFO     "+java.util.logging.Level.INFO.intValue()+"\n");
      sb.append("CONFIG?  "+java.util.logging.Level.CONFIG.intValue()+"\n");
      sb.append("FINE     "+java.util.logging.Level.FINE.intValue()+"\n");
      sb.append("FINER    "+java.util.logging.Level.FINER.intValue()+"\n");
      sb.append("FINEST   "+java.util.logging.Level.FINEST.intValue()+"\n");
      sb.append("\n");
      sb.append("org.slf4j.event.Level (enum)\n");
      sb.append("----------------------------\n");
      sb.append("ERROR    "+Level.ERROR.toInt()+"\n");
      sb.append("WARN     "+Level.WARN.toInt()+"\n");
      sb.append("INFO     "+Level.INFO.toInt()+"\n");
      sb.append("DEBUG    "+Level.DEBUG.toInt()+"\n");
      sb.append("TRACE    "+Level.TRACE.toInt()+"\n");
      
      sb.append("\n\n");
      
      System.out.println(sb.toString());
   }
   
   
}
