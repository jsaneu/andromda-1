package org.andromda.core.common;

/**
 * <p>
 * Contains namespace properties used witin the AndroMDA core.
 * </p>
 * 
 * @author Chad Brandon
 */
public class NamespaceProperties
{
    /**
     * <p>
     * The location of an <strong>optional </strong> merge
     * mappings file (merge mappings are mappings that allow the replacement of
     * patterns in generated files during model processing time).
     * </p>
     */
    public static final String MERGE_MAPPINGS_URI = "mergeMappingsUri";

    /**
     * <p>
     * Defines the location of merge templates. Merge templates are templates
     * that can either be <em>merged</em> into existing cartridge output, or
     * replace cartridge templates all together.  This must be the directory
     * where the merge files can be found.
     * </p>
     */
    public static final String MERGE_LOCATION = "mergeLocation";

    /**
     * <p>
     * The URI to the mappings file containing the AndroMDA profile.
     * This property provides the ability to override the default 
     * profile values (i.e. stereotype names).
     * </p>
     */
    public static final String PROFILE_MAPPINGS_URI = "profileMappingsUri";
}