package org.andromda.core.common;

import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This is a logger that writes to stdout. At the moment,
 * it is written in the simplest possible way.
 * 
 * @since 26.11.2003
 * @author <a href="http://www.mbohlen.de">Matthias Bohlen</a>
 * @author Chad Brandon
 *
 */
public class StdoutLogger
{
    private static final String DEFAULT_LOGGER_NAME = "andromda";
    
    private static Logger logger = Logger.getLogger(DEFAULT_LOGGER_NAME);
        
    /**
     * Configures logging for the AndroMDA application
     * from the the xml resource "log4j.xml" found within
     * the same package as this class.     
     */
    public static void configure() 
    {
    	final String methodName = "StdoutLogger.configure";
    	String loggingConfiguration = "log4j.xml";
    	URL url = StdoutLogger.class.getResource(loggingConfiguration);
    	if (url == null) 
        {
    		throw new RuntimeException(methodName
    				+ " - could not find Logger configuration file '" 
					+ loggingConfiguration + "'");
    	}
    	configure(url);
    }
    
    /**   
     * Configures the Logger from the passed in logConfigurationXml 
     * 
     * @param logConfigurationXml
     */
    protected static void configure(URL logConfigurationXml) 
    {
    	try 
        {
    		DOMConfigurator.configure(logConfigurationXml);
    	} catch (Exception ex) 
        {
    		System.err.println(
    				"Unable to initialize logging system with configuration file '"
    				+ logConfigurationXml
					+ "' --> using basic configuration.");
    		ex.printStackTrace();
    		BasicConfigurator.configure();
    	}
    }

    /**
     * Allows us to set the logger to a new name.
     * 
     * @param name
     */
    public static void setLogger(String name) 
    {
        logger = Logger.getLogger(name);
    }
    
    /**
     * Resets the logger to the default name.
     */
    public static void reset() {
    	logger = Logger.getLogger(DEFAULT_LOGGER_NAME);
    }
    
    public static void debug (Object o)
    {
        logger.debug(o);
    }
    public static void info (Object o)
    {
        logger.info(o);
    }
    public static void warn (Object o)
    {
        logger.warn(o);
    }
    public static void error (Object o)
    {
        logger.error(o);
    }
}
