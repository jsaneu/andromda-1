// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: ValueObject.vsl in andromda-java-cartridge.
//
package org.andromda.test.howto6.a;

/**
 * 
 */
public class CarListItem
    implements java.io.Serializable
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -2232633419024752787L;

    public CarListItem()
    {
        this.name = null;
        this.serial = null;
        this.ownerName = null;
    }

    public CarListItem(java.lang.String name, java.lang.String serial, java.lang.String ownerName)
    {
        this.name = name;
        this.serial = serial;
        this.ownerName = ownerName;
    }

    /**
     * Copies constructor from other CarListItem
     *
     * @param otherBean, cannot be <code>null</code>
     * @throws java.lang.NullPointerException if the argument is <code>null</code>
     */
    public CarListItem(CarListItem otherBean)
    {
        this(otherBean.getName(), otherBean.getSerial(), otherBean.getOwnerName());
    }

    /**
     * Copies all properties from the argument value object into this value object.
     */
    public void copy(CarListItem otherBean)
    {
        this.setName(otherBean.getName());
        this.setSerial(otherBean.getSerial());
        this.setOwnerName(otherBean.getOwnerName());
    }

    private java.lang.String name;

    /**
     * 
     */
    public java.lang.String getName()
    {
        return this.name;
    }

    public void setName(java.lang.String name)
    {
        this.name = name;
    }

    private java.lang.String serial;

    /**
     * 
     */
    public java.lang.String getSerial()
    {
        return this.serial;
    }

    public void setSerial(java.lang.String serial)
    {
        this.serial = serial;
    }

    private java.lang.String ownerName;

    /**
     * 
     */
    public java.lang.String getOwnerName()
    {
        return this.ownerName;
    }

    public void setOwnerName(java.lang.String ownerName)
    {
        this.ownerName = ownerName;
    }

}