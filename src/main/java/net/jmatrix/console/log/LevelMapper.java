package net.jmatrix.console.log;

class LevelMapper {
   //  SEVERE  -- ERROR
   //  WARNING -- WARN
   //  INFO    -- INFO
   //  CONFIG  -- DEBUG
   //  FINE    -- DEBUG
   //  FINER   -- TRACE
   //  FINEST  -- TRACE
   
   static final org.slf4j.event.Level julToSlfLevel(java.util.logging.Level julevel) {
      if (julevel.intValue() == java.util.logging.Level.SEVERE.intValue())
         return org.slf4j.event.Level.ERROR;
      if (julevel.intValue() == java.util.logging.Level.WARNING.intValue())
         return org.slf4j.event.Level.WARN;
      if (julevel.intValue() == java.util.logging.Level.INFO.intValue())
         return org.slf4j.event.Level.INFO;
      if (julevel.intValue() == java.util.logging.Level.CONFIG.intValue())
         return org.slf4j.event.Level.DEBUG;
      if (julevel.intValue() == java.util.logging.Level.FINE.intValue())
         return org.slf4j.event.Level.DEBUG;
      if (julevel.intValue() == java.util.logging.Level.FINER.intValue())
         return org.slf4j.event.Level.TRACE;
      if (julevel.intValue() == java.util.logging.Level.FINEST.intValue())
         return org.slf4j.event.Level.TRACE;
      
      return org.slf4j.event.Level.TRACE;
   }
   
   //  SEVERE  -- ERROR
   //  WARNING -- WARN
   //  INFO    -- INFO
   //  CONFIG  -- DEBUG
   //  FINE    -- DEBUG
   //  FINER   -- TRACE
   //  FINEST  -- TRACE
   static final java.util.logging.Level slfToJulLevel(org.slf4j.event.Level slevel) {
      if (slevel == org.slf4j.event.Level.ERROR)
         return java.util.logging.Level.SEVERE;
      
      if (slevel == org.slf4j.event.Level.WARN)
         return java.util.logging.Level.WARNING;
      
      if (slevel == org.slf4j.event.Level.INFO)
         return java.util.logging.Level.INFO;
      
      if (slevel == org.slf4j.event.Level.DEBUG)
         return java.util.logging.Level.CONFIG;
      
      if (slevel == org.slf4j.event.Level.TRACE)
         return java.util.logging.Level.FINER;
      
      return java.util.logging.Level.FINER;
   }
}
