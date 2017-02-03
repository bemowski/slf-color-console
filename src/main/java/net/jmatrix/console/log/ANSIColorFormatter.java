package net.jmatrix.console.log;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.slf4j.event.Level;

public class ANSIColorFormatter extends Formatter {
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_BLACK = "\u001B[30m";
   public static final String ANSI_RED = "\u001B[31m";
   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_YELLOW = "\u001B[33m";
   public static final String ANSI_BLUE = "\u001B[34m";
   public static final String ANSI_PURPLE = "\u001B[35m";
   public static final String ANSI_CYAN = "\u001B[36m";
   public static final String ANSI_WHITE = "\u001B[37m";
    
   private DateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
   
   private Map<Level, String> levelColors=new HashMap<>();
   private Map<Level, String> levelName=new HashMap<>();
   
   public ANSIColorFormatter() {
      levelColors.put(Level.ERROR, ANSI_RED);
      levelColors.put(Level.WARN, ANSI_YELLOW);
      levelColors.put(Level.TRACE, ANSI_PURPLE);
      levelColors.put(Level.DEBUG, ANSI_GREEN);
      
      levelName.put(Level.ERROR, "E");
      levelName.put(Level.WARN, "W");
      levelName.put(Level.INFO, "I");
      levelName.put(Level.DEBUG, "D");
      levelName.put(Level.TRACE, "T");

   }
   
   public void setLevelColor(Level level, String color) {
      levelColors.put(level, color);
   }
   
   // `12:32:03 [D] {thread} ShortClass: message
   
   @Override
   public String format(LogRecord record) {
      StringBuilder sb=new StringBuilder();
      
      synchronized (dateFormat) {
         sb.append(dateFormat.format(new Date(record.getMillis())));
      }
      
      sb.append(" ["+getLevelString(record.getLevel())+"]");
      sb.append(" {"+Thread.currentThread().getName()+"} ");
      sb.append(shortLoggerName(record.getLoggerName()+": "));
      
      sb.append(record.getMessage());
      
      if (record.getThrown() != null) {
         sb.append("\n"+stackString(record.getThrown()));
      }
      
      return sb.toString();
   }
   
   private final String getLevelString(java.util.logging.Level jlevel) {
      Level level=LevelMapper.julToSlfLevel(jlevel);
      return color(levelColors.get(level), levelName.get(level));
   }
   
   private static final String color(String color, String s) {
      return color == null ? s : color+s+ANSI_RESET;
   }
   
   private static final String shortLoggerName(String loggerName) {
      return loggerName.substring(loggerName.lastIndexOf(".")+1);
   }
   
   private static final String stackString(Throwable ex) {
      ByteArrayOutputStream baos=new ByteArrayOutputStream();
      PrintWriter pw=new PrintWriter(new OutputStreamWriter(baos));
      ex.printStackTrace(pw);
      pw.flush();
      return baos.toString();
   }
}
