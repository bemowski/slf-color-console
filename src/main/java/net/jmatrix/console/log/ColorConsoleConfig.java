package net.jmatrix.console.log;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.slf4j.event.Level;
import org.slf4j.impl.StaticLoggerBinder;;

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
   
   static boolean DEBUG=false;
   
   static ConsoleLoggerFactory logFactory=null;
   public static void init(ConsoleLoggerFactory f) {
      logFactory=f;
      if (System.getProperty("slfcc.debug") != null) {
         DEBUG=true;
      }
      try {
         URL url=ColorConsoleConfig.class.getResource("/slfcc.properties");
         if (DEBUG)
            System.out.println("Properties url: "+url);
         if (url != null) {
            Properties p=new Properties();
            p.load(url.openStream());
            configure(p);
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
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
      
      for (Object key:props.keySet()) {
         String skey=key!=null?key.toString():"null";
         if (skey.startsWith("slf.cc.logger.")) {
            String lname=skey.substring("slf.cc.logger.".length());
            String value=props.getProperty(skey).toString();
            if (validLevels.contains(value)) {
               setLevel(lname, Level.valueOf(value));
            }
         }
      }
   }
   
   @Deprecated
   public static void setLevel(Level l) {
      setGlobalLevel(l);
   }
   
   public static void setGlobalLevel(Level level) {
      if (level != null)
         ColorConsoleLogger.setGlobalLevel(level);
   }
   
   public static void setLevel(String name, Level level) {
      ConsoleLoggerFactory factory=(ConsoleLoggerFactory)StaticLoggerBinder.getSingleton().getLoggerFactory();
      
      ColorConsoleLogger logger=(ColorConsoleLogger)factory.getLogger(name);
      logger.setLevel(level);
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
      System.err.println("Setting color console as java.util.logging hangler.");
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
