package net.jmatrix.console.log;

import org.slf4j.Logger;
import org.slf4j.helpers.MarkerIgnoringBase;

public class ColorConsoleLogger extends MarkerIgnoringBase implements Logger {
   String name;
   
   static Level level=Level.WARN;
   Level ilevel=null;
   
   static LogWriter writer=null;
   
   {
      writer=new LogWriter();
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
      return getLevel().getILevel() >= Level.ALL.getILevel();
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
         writer.trace(msg, t);
   }

   //////////////////////////// DEBUG ////////////////////////////////
   @Override
   public boolean isDebugEnabled() {
      return getLevel().getILevel() >= Level.DEBUG.getILevel();
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
      writer.debug(msg, t);
   }

   @Override
   public boolean isInfoEnabled() {
      return getLevel().getILevel() >= Level.INFO.getILevel();
   }

   //////////////////////////// INFO ////////////////////////////////
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
      writer.info(msg, t);
   }

   //////////////////////////// WARN ////////////////////////////////
   @Override
   public boolean isWarnEnabled() {
      return getLevel().getILevel() >= Level.WARN.getILevel();
   }

   @Override
   public void warn(String msg) {
      writer.warn(msg, (Throwable)null);
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
      writer.warn(msg, t);
   }

   //////////////////////////// ERROR ////////////////////////////////
   @Override
   public boolean isErrorEnabled() {
      return getLevel().getILevel() >= Level.ERROR.getILevel();
   }

   @Override
   public void error(String msg) {
      writer.error(msg, (Throwable)null);
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
      writer.error(msg, t);
   }

}
