/*
 * Created on 24-Jan-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.andromda.core.common;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * This class provides statistics on how the build was
 * done.
 * 
 * @author martin
 *
 */
public class BuildInformation
{

    /** The build timestamp */
    static final String BUILD_DATE;
    /** The build operating system and version */
    static final String BUILD_SYSTEM;
    /** The JDK details used to build the system */
    static final String BUILD_JDK;
    /** The name of the user that built the system */
    static final String BUILD_BUILDER;
    
    static
    {
        final String buildPropertiesUri = "META-INF/andromda-build.properties";
        final String datePropertyName = "andromda.build.date";
        final String systemPropertyName = "andromda.build.system";
        final String jdkPropertyName = "andromda.build.jdk";
        final String builderPropertyName = "andromda.build.builder";
        try
        {
            URL versionUri = ResourceUtils.getResource(buildPropertiesUri);
            if (versionUri == null)
            {
                throw new IllegalStateException("BuildInformation:Could not load file --> '"
                    + buildPropertiesUri + "'");
            }
            Properties properties = new Properties();
            InputStream stream = versionUri.openStream();
            properties.load(stream);
            stream.close();
            stream = null;
            BUILD_DATE = properties.getProperty(datePropertyName);
            BUILD_SYSTEM = properties.getProperty(systemPropertyName);
            BUILD_JDK = properties.getProperty(jdkPropertyName);
            BUILD_BUILDER = properties.getProperty(builderPropertyName);
        }
        catch (IllegalStateException ex)
        {
            throw ex;
        }
        catch (Throwable th)
        {
            ExceptionRecorder.record( th );
            throw new IllegalStateException(th.getMessage());
        }
    }


    /**
     * Return the user name of the id which built the system.
     * @return Returns the Build BUILDER.
     */
    public static String getBUILD_BUILDER()
    {
        return BUILD_BUILDER;
    }
    /**
     * Return the timestamp of the build in
     * yyy-mm-dd hh:mm:ss format but check the filter
     * in andromda/maven.xml for current setting.
     * @return Returns the BUILD DATE.
     */
    public static String getBUILD_DATE()
    {
        return BUILD_DATE;
    }
    /**
     * Return the vendor and jdk version.
     * @return Returns the BUILD JDK.
     */
    public static String getBUILD_JDK()
    {
        return BUILD_JDK;
    }
    /**
     * Return the name of the operating system and version.
     * @return Returns the BUILD SYSTEM.
     */
    public static String getBUILD_SYSTEM()
    {
        return BUILD_SYSTEM;
    }
}
