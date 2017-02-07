package net.jmatrix.console.log;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.slf4j.event.Level;;

public class ColorConsoleConfig {
   public static final String ANSIColorFormatter="ANSIColorFormatter";
   public static final String SimpleFormatter="SimpleFormatter";
   
   static final List<String> validLevels=Arrays.asList(new String[] {
         "ERROR", "WARN", "INFO", "DEBUG", "TRACE"
   });
   static final List<String> validStreams=Arrays.asList(new String[] {"out", "err"});
   static final List<String> validFormatters=Arrays.asList(new String[] {
         ANSIColorFormatter, SimpleFormatter
   });
   // n/a slf.cc.writer=ConsoleLogWriter (default)
   // slf.cc.writer.default.stream=out  [out|err]
   // slf.cc.writer.[ERROR,WARN,INFO,DEBUG,TRACE] = [out, err]
   // slf.cc.formatter=ANSIColorFormatter  [ANSIColorFormatter|SimpleFormatter]
   // slf.cc.level=[ERROR,WARN,INFO,DEBUG,TRACE]
   // slf.cc.java.util.logging=[true,false] default=false
   
   public static void configure(Properties props) {
      String slevel=props.getProperty("slf.cc.level", "WARN");
      if (slevel != null) {
         slevel=slevel.toUpperCase();
         if (validLevels.contains(slevel)) {
            ColorConsoleLogger.setGlobalLevel(Level.valueOf(slevel));
         }
      }
      
      ConsoleLogWriter writer=(ConsoleLogWriter)ColorConsoleLogger.getWriter();
      String defStream=props.getProperty("slf.cc.writer.default.stream");
      if (defStream != null) {
         defStream=defStream.toLowerCase();
         if (validStreams.contains(defStream)) {
            if (defStream.equals("out")) {
               writer.setDefaultStream(System.out);
            } else if (defStream.equals("err")) {
               writer.setDefaultStream(System.err);
            }
         }
      }
      
      for (String vlevel:validLevels) {
         String streamName=props.getProperty("slf.cc.writer."+vlevel);
         if (streamName != null) {
            if (validStreams.contains(streamName)) {
               Level level=Level.valueOf(vlevel);
               if (streamName.equals("out")) {
                  writer.setLevelStream(level, System.out);
               } else if (streamName.equals("err")) {
                  writer.setLevelStream(level, System.err);
               }
            }
         }
      }
      
      String formatter=props.getProperty("slf.cc.formatter");
      setFormatter(formatter);
      
      String jul=props.getProperty("slf.cc.java.util.logging");
      if (jul != null && jul.toLowerCase().equals("true")) {
         setAsJavaUtilLoggingHandler();
      }
   }
   
   public static void setLevel(Level level) {
      if (level != null)
         ColorConsoleLogger.setGlobalLevel(level);
   }
   
   public static void setFormatter(String formatter) {
      if (formatter != null) {
         if (validFormatters.contains(formatter)) {
            if (formatter.equals(ANSIColorFormatter)){
               ColorConsoleLogger.setFormatter(new ANSIColorFormatter());
            } else if (formatter.equals(SimpleFormatter)) {
               ColorConsoleLogger.setFormatter(new SimpleFormatter());
            }
         }
      }
   }
   
   static Handler julHandler=null;
   
   public static void setAsJavaUtilLoggingHandler() {
      LogManager logManager=LogManager.getLogManager();
      
      Logger logger=Logger.getLogger("");
      logger.removeHandler(logger.getHandlers()[0]);
      
      if (julHandler == null) {
         julHandler=new ColorConsoleHandler();
      }

      logger.addHandler(julHandler);
      
      logger.setLevel(java.util.logging.Level.ALL);
   }
   
   static class ColorConsoleHandler extends Handler {
      @Override
      public void close() throws SecurityException {
      }

      @Override
      public void flush() {
      }

      @Override
      public void publish(LogRecord record) {
         ColorConsoleLogger.formatAndWrite(LevelMapper.julToSlfLevel(record.getLevel()), record);
      }
   }
}
