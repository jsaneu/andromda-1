package org.andromda.core.profile;

import java.net.URL;

import org.andromda.core.common.AndroMDALogger;
import org.andromda.core.common.NamespaceProperties;
import org.andromda.core.common.Namespaces;
import org.andromda.core.common.Property;
import org.andromda.core.common.ResourceFinder;
import org.andromda.core.mapping.Mappings;
import org.apache.commons.lang.StringUtils;

/**
 * The base profile instance, all classes that load profile information shall
 * extend from this class.
 * 
 * @author Chad Brandon
 */
abstract class ProfileValues
{
    /**
     * Gets the profile value for the given <code>from</code> value. Returns
     * the <code>from</code> if the profile value can not be found.
     * 
     * @param from the <code>from</code> value of the mapped profile value.
     * @return the mapped profile value.
     */
    public String get(String from)
    {
        String value = from;
        if (this.profileMappings != null)
        {
            value = this.profileMappings.getTo(from);
        }
        return value;
    }

    /**
     * Stores the profile values.
     */
    private Mappings profileMappings = null;

    /**
     * Constructs a new instance of ProfileValues with the given
     * <code>namespaceProperty</code> and <code>defaultFileName</code>.
     * 
     * @param mappingsProperty the name of the namespace property that will
     *        provide the ability to override any default default profile
     *        values.
     * @param defaultFileName the name of the file to search for that contains
     *        the default profile values.
     */
    protected ProfileValues(
        String namespaceProperty,
        String defaultFileName)
    {
        this.profileMappings = this.getMappings(
            namespaceProperty,
            defaultFileName);
    }

    /**
     * The location to which default profiles are stored. If the
     * {@link NamespaceProperties#MERGE_MAPPINGS_URI}isn't defined then profile
     * mappings are found here.
     */
    private static final String DEFAULT_LOCATION = "META-INF/profile/";

    /**
     * Attempts to retrieve the Mappings instance for the given
     * <code>mappingsUri</code> belonging to the given <code>namespace</code>.
     * 
     * @param mappingsProperty the name of the namespace property that will
     *        provide the ability to override any default default profile
     *        values.
     * @param defaultFileName the name of the file to search for that contains
     *        the default profile values.
     */
    private Mappings getMappings(
        String namespaceProperty,
        String defaultFileName)
    {
        String defaultLocation = DEFAULT_LOCATION + defaultFileName;
        Mappings mappings = null;
        URL[] profileResources = ResourceFinder.findResources(defaultLocation);
        if (profileResources != null && profileResources.length > 0)
        {
            for (int ctr = 0; ctr < profileResources.length; ctr++)
            {
                URL profileResource = profileResources[ctr];
                if (mappings == null)
                {
                    mappings = Mappings.getInstance(profileResource);
                }
                else
                {
                    mappings.addMappings(Mappings.getInstance(profileResource));
                }
            }
        }
        Property mappingsUri = Namespaces.instance().findNamespaceProperty(
            Namespaces.DEFAULT,
            namespaceProperty,
            false);
        String mappingsUriValue = mappingsUri != null
            ? mappingsUri.getValue()
            : null;
        mappingsUriValue = StringUtils.trimToEmpty(mappingsUriValue);
        if (StringUtils.isNotEmpty(mappingsUriValue))
        {
            if (mappings == null)
            {
                mappings = Mappings.getInstance(mappingsUriValue);
            }
            else
            {
                mappings.addMappings(Mappings.getInstance(mappingsUriValue));
            }
        }
        if (mappings == null)
        {
            AndroMDALogger.error("Profile resources could not be found --> '"
                + defaultLocation + "'");
        }
        return mappings;
    }
}
