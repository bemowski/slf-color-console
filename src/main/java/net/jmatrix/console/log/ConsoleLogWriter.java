package net.jmatrix.console.log;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.slf4j.event.Level;

/**
 * Responsible for writing, with color, if able.
 */
public class ConsoleLogWriter implements LogWriter {
   Formatter formatter=new ANSIColorFormatter();
   
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

   @Override
   public void write(Level level, String loggerName, String message, Throwable t) {
      PrintWriter pw=writerMap.get(level);
      if (pw == null)
         pw=defaultWriter;
      LogRecord lr=new LogRecord(LevelMapper.slfToJulLevel(level), message);
      lr.setMillis(System.currentTimeMillis());
      lr.setLoggerName(loggerName);
      
      lr.setThrown(t);
      
      pw.println(formatter.format(lr));
   }
}
