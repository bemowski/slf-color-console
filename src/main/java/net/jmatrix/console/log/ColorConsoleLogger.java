package net.jmatrix.console.log;

import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;

public class ColorConsoleLogger extends MarkerIgnoringBase implements Logger {
   String name;
   
   static Level level=Level.WARN;
   Level ilevel=null;
   
   static LogWriter writer=null;
   
   {
      writer=new ConsoleLogWriter();
   }
   
   public ColorConsoleLogger(String name) {
      this.name=name;
   }
   
   Level getLevel() {
      if (ilevel != null)
         return ilevel;
      return level;
   }
   
   public static void setGlobalLevel(Level l) {
      level=l;
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
         writer.write(Level.TRACE, name, msg, t);
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
         writer.write(Level.DEBUG, name, msg, t);
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
         writer.write(Level.INFO, name, msg, t);
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
         writer.write(Level.WARN, name, msg, t);
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
         writer.write(Level.ERROR, name, msg, t);
   }

}
