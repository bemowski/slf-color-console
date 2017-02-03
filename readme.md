

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

