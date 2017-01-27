package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import net.jmatrix.console.log.ConsoleLoggerFactory;


public class StaticLoggerBinder implements LoggerFactoryBinder {
   static StaticLoggerBinder SINGLEDON=null;
   
   static ConsoleLoggerFactory clf=null;
   static {
      clf=new ConsoleLoggerFactory();
      SINGLETON = new StaticLoggerBinder();

   }  
   
   private static StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
   
   @Override
   public ILoggerFactory getLoggerFactory() {
      return clf;
   }

   @Override
   public String getLoggerFactoryClassStr() {
      return ConsoleLoggerFactory.class.getName();
   }
   
   public static StaticLoggerBinder getSingleton() {
      return SINGLETON;
    }
}
