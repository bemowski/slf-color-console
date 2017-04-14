

SLF Color Console Logger (and Binder)
=====================================

# Purpose

Simple command line tools consume more complex libraries that use complex logging - which is in turn typically abstracted by one of the main logging abstractions
  - SLF4J
  - Apache Commons Logging

Or, occasionally, uses and implementation directly, like the java.util.logging framework.

Specifically with SLF4J - unless otherwise specified - it uses NOOPLogger - which effectively sends useful debugging output to /dev/null.


# Configuration

This is designed specifically for small command line or console apps that have low thread counts, and are relatively short lived.  As such - the focus of configuration is simplicity.  A few simple rules:
 - There is but 1 logger factory, that creates instances of the ConsoleLogger
 - There is 1 formatter reused by each instance of ConsoleLogger
 - There is 1 LogWriter reysed by each instance of ConsoleLogger
   - The LogWriter can output various levels of logging to various streams.  (think System.out, System.err)
 - The Log level is typically set globally for output.  However individual loggers can have different log levels, its not simple to configure.

## Configuration via Properties

Create a java.util.Properties object, and add any of fht properties detailed below.  Then call the static method:

    ColorConsoleConfig.configure(properties);

## Properties

Set the logging level.  

    slf.cc.level=[ERROR,WARN,INFO,DEBUG,TRACE]

The writer can log to either System.err or System.out.  These properties allow for setting the default stream, and level specific streams.  

    slf.cc.writer.default.stream=out  [out|err]
    slf.cc.writer.[ERROR,WARN,INFO,DEBUG,TRACE] = [out, err]

There are 2 available formatters. Simple formatter adds no context.  ANSIColorFormatter adds timestamp, thread, logger name with some color.

    slf.cc.formatter=ANSIColorFormatter  [ANSIColorFormatter|SimpleFormatter]
   
This property sets the SLFColorConsole as the default Handler for java.util.logging.  

    slf.cc.java.util.logging=[true,false] default=false

## Formatter

The formatter determines what logging output will look like.  Two formatters are available:

### ANSIColorFormatter

Ansi color formatter uses the following format:

    12:00:00 [L] {thread} LoggerName: message
    stack 

### SimpleFormatter

Simple formatter 

## Log Level

Levels is set using org.slf4j.event.Level levels - referred to as (in order of increasing verbosity):
 - ERROR
 - WARN
 - INFO
 - DEBUG
 - TRACE

## Writer

The LogWriter writes the formatted message to a configured PrintWriter.  Very typically this would be PrintWriters wrapping System.out and System.err.

# Releases

1.0.7 - Configure level of individual loggers.

1.0.6 - back off to java 1.7 compatibility.  (was defaulting to java 8)

1.0.5 - adding config property for java.util.logging bridge

1.0.4 - adding bridge for java.util.logging root logger.

1.0.3 - configuration stuff