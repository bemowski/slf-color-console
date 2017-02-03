package net.jmatrix.console.log;

import org.slf4j.event.Level;

public interface LogWriter {
   public void write(Level level, String formattedMessage);
}
