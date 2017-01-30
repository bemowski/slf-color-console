package net.jmatrix.console.log;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Responsible for writing, with color, if able.
 */
public class LogWriter {
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_BLACK = "\u001B[30m";
   public static final String ANSI_RED = "\u001B[31m";
   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_YELLOW = "\u001B[33m";
   public static final String ANSI_BLUE = "\u001B[34m";
   public static final String ANSI_PURPLE = "\u001B[35m";
   public static final String ANSI_CYAN = "\u001B[36m";
   public static final String ANSI_WHITE = "\u001B[37m";
   
   PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out), true);
   
   void trace(String msg, Throwable t) {
      out.println(msg);
      if (t != null)
         out.print(stackString(t));
   }
   
   void info(String msg, Throwable t) {
      out.println(msg);
      if (t != null)
         out.print(stackString(t));
   }
   
   void debug(String msg, Throwable t) {
      out.println(msg);
      if (t != null)
         out.print(stackString(t));
   }
   
   
   
   void error(String s, Throwable t) {
      out.println(color(ANSI_RED, s));
      if (t != null)
         out.println(color(ANSI_RED, stackString(t)));
   }
   
   
   void warn(String s, Throwable t) {
      out.println(color(ANSI_YELLOW,s));
      if (t != null)
         out.println(color(ANSI_YELLOW, stackString(t)));
   }
   
   
   private static final String color(String key, String s) {
      return key+s+ANSI_RESET;
   }
   
   public static final String stackString(Throwable ex) {
      ByteArrayOutputStream baos=new ByteArrayOutputStream();
      PrintWriter pw=new PrintWriter(new OutputStreamWriter(baos));
      ex.printStackTrace(pw);
      pw.flush();
      return baos.toString();
   }
}
