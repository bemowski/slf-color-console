package net.jmatrix.console.log;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.event.Level;

/**
 * Responsible for writing, with color, if able.
 */
public class ConsoleLogWriter implements LogWriter {
   
   // trace, debug, info -> out
   PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out), true);
   
   // Warn and Error -> err
   PrintWriter err=new PrintWriter(new OutputStreamWriter(System.err), true);
   
   Map<Level, PrintWriter> writerMap=new HashMap<>();
   PrintWriter defaultWriter=out;
   
   public ConsoleLogWriter() {
      writerMap.put(Level.WARN, err);
      writerMap.put(Level.ERROR, err);
   }
   
   public void setLevelStream(Level level, PrintStream ps) {
      if (ps == System.out)
         writerMap.put(level, out);
      if (ps == System.err) 
         writerMap.put(level, err);
   }
   
   public void setDefaultStream(PrintStream ps) {
      if (ps == System.out)
         defaultWriter=out;
      else if (ps == System.err)
         defaultWriter=err;
   }

   @Override
   public void write(Level level, String formattedMessage) {
      PrintWriter pw=writerMap.get(level);
      if (pw == null)
         pw=defaultWriter;
      pw.println(formattedMessage);
   }
}
