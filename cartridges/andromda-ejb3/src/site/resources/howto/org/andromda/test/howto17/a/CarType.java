// license-header java merge-point
/* Autogenerated by AndroMDA (Enumeration.vsl) - do not edit */
package org.andromda.howto2.rental;

/**
 * Autogenerated enumeration CarType.
 *
 * 
 */
public enum CarType implements java.io.Serializable
{
    /**
     *  
     */
    SEDAN,

    /**
     *  
     */
    LIFTBACK,

    /**
     *  
     */
    WAGON;

    /**
     * Return the CarType from a string value
     * @return CarType enum object
     */
    public static CarType fromString(java.lang.String value)
    {
        return valueOf(value);
    }
    
    /**
     * Return a Collection of all literal values for this enumeration
     * @return java.util.Collection literal values
     */
    public static java.util.Collection literals()
    {
        final java.util.Collection<String> literals = new java.util.ArrayList<String>(values().length);
        for (int i = 0; i < values().length; i++)
        {
            literals.add(values()[i].name());
        }
        return literals;
    }
}