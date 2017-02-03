package net.jmatrix.console.log;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;

@SuppressWarnings("serial")
public class ColorConsoleLogger extends MarkerIgnoringBase implements Logger {
   
   /** Logger name, typically class name. */
   String name;
   
   /** Theoretically allow per-logger level, but there is no way to configure
    * it at this time.*/
   Level ilevel=null;
   
   static Level level=Level.WARN;
   
   static LogWriter writer=null;
   
   static Formatter formatter=null;
   
   static {
      // some simple defaults.
      writer=new ConsoleLogWriter();
      formatter=new ANSIColorFormatter();
   }
   
   public ColorConsoleLogger(String name) {
      this.name=name;
   }
   
   public static void setGlobalLevel(Level l) {
      level=l;
   }
   public static Level getGlobalLevel() {return level;}
   
   public static void setFormatter(Formatter f) {
      formatter=f;
   }
   public static Formatter getFormatter() {return formatter;}
   
   public static void setWriter(LogWriter w) {
      writer=w;
   }
   
   public static LogWriter getWriter() {return writer;}
   
   //////////////////////////////////////////////////////////////////////////////
   final void formatAndWrite(Level level, String message, Throwable t) {
      LogRecord lr=new LogRecord(LevelMapper.slfToJulLevel(level), message);
      lr.setMillis(System.currentTimeMillis());
      lr.setLoggerName(name);
      
      lr.setThrown(t); 
      
      formatAndWrite(level, lr);
   }
   
   static final void formatAndWrite(Level level, LogRecord lr) {
      if (ColorConsoleLogger.level.toInt() <= level.toInt()) {
         String formattedMessage=formatter.format(lr);
         writer.write(level, formattedMessage);
      }
   }
   
   Level getLevel() {
      if (ilevel != null)
         return ilevel;
      return level;
   }
   
   // trace, debug, info, warn, error
   
   //////////////////////////// TRACE ////////////////////////////////
   @Override
   public boolean isTraceEnabled() {
      return getLevel().toInt() <= Level.TRACE.toInt();
   }

   @Override
   public void trace(String msg) {
     trace(msg, (Throwable)null);
   }

   @Override
   public void trace(String format, Object arg) {
      trace(String.format(format, arg));
   }

   @Override
   public void trace(String format, Object arg1, Object arg2) {
      trace(String.format(format, arg1, arg2));
   }

   @Override
   public void trace(String format, Object... arguments) {
      trace(String.format(format, arguments));
   }

   @Override
   public void trace(String msg, Throwable t) {
      if (isTraceEnabled())
         formatAndWrite(Level.TRACE, msg, t);
   }

   //////////////////////////// DEBUG ////////////////////////////////
   @Override
   public boolean isDebugEnabled() {
      return getLevel().toInt() <= Level.DEBUG.toInt();
   }

   
   @Override
   public void debug(String msg) {
      debug(msg, (Throwable)null);
   }

   @Override
   public void debug(String format, Object arg) {
      debug(String.format(format, arg));
   }

   @Override
   public void debug(String format, Object arg1, Object arg2) {
      debug(String.format(format, arg1, arg2));
   }

   @Override
   public void debug(String format, Object... arguments) {
      debug(String.format(format, arguments));
   }

   @Override
   public void debug(String msg, Throwable t) {
      if (isDebugEnabled())
         formatAndWrite(Level.DEBUG, msg, t);
   }
   //////////////////////////// INFO ////////////////////////////////
   @Override
   public boolean isInfoEnabled() {
      return getLevel().toInt() <= Level.INFO.toInt();
   }

   @Override
   public void info(String msg) {
       info(msg, (Throwable)null);
   }

   @Override
   public void info(String format, Object arg) {
      info(String.format(format, arg));
   }

   @Override
   public void info(String format, Object arg1, Object arg2) {
      info(String.format(format, arg1, arg2));
   }

   @Override
   public void info(String format, Object... arguments) {
      info(String.format(format, arguments));
   }

   @Override
   public void info(String msg, Throwable t) {
      if (isInfoEnabled())
         formatAndWrite(Level.INFO,msg, t);
   }

   //////////////////////////// WARN ////////////////////////////////
   @Override
   public boolean isWarnEnabled() {
      return getLevel().toInt() <= Level.WARN.toInt();
   }

   @Override
   public void warn(String msg) {
      warn(msg, (Throwable)null);
   }

   @Override
   public void warn(String format, Object arg) {
      warn(String.format(format, arg));
   }

   @Override
   public void warn(String format, Object... arguments) {
      warn(String.format(format, arguments));
   }

   @Override
   public void warn(String format, Object arg1, Object arg2) {
      warn(String.format(format, arg1, arg2));
   }

   @Override
   public void warn(String msg, Throwable t) {
      if (isWarnEnabled())
         formatAndWrite(Level.WARN, msg, t);
   }

   //////////////////////////// ERROR ////////////////////////////////
   @Override
   public boolean isErrorEnabled() {
      return getLevel().toInt() <= Level.ERROR.toInt();
   }

   @Override
   public void error(String msg) {
      error(msg, (Throwable)null);
   }

   @Override
   public void error(String format, Object arg) {
      error(String.format(format, arg));
   }

   @Override
   public void error(String format, Object arg1, Object arg2) {
      error(String.format(format, arg1, arg2));
   }

   @Override
   public void error(String format, Object... arguments) {
      error(String.format(format, arguments));
   }

   @Override
   public void error(String msg, Throwable t) {
      if (isErrorEnabled())
         formatAndWrite(Level.ERROR, msg, t);
   }
}
