package net.jmatrix.console.log;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SimpleFormatter extends Formatter {

   @Override
   public String format(LogRecord record) {
      StringBuilder sb=new StringBuilder();
      
      sb.append(record.getMessage());
      if (record.getThrown() != null) {
         sb.append("\n"+stackString(record.getThrown()));
      }
      return sb.toString();
   }
   
   private static final String stackString(Throwable ex) {
      ByteArrayOutputStream baos=new ByteArrayOutputStream();
      PrintWriter pw=new PrintWriter(new OutputStreamWriter(baos));
      ex.printStackTrace(pw);
      pw.flush();
      return baos.toString();
   }
}
