// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: ValueObject.vsl in andromda-java-cartridge.
//
package org.andromda.test.howto6.a;

/**
 * 
 */
public class CarDetails
    implements java.io.Serializable
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -2523674736578380328L;

    public CarDetails()
    {
        this.name = null;
        this.serial = null;
        this.ownerName = null;
        this.year = null;
        this.timesRented = 0;
    }

    public CarDetails(java.lang.String name, java.lang.String serial, java.lang.String ownerName, java.util.Date year, int timesRented)
    {
        this.name = name;
        this.serial = serial;
        this.ownerName = ownerName;
        this.year = year;
        this.timesRented = timesRented;
    }

    /**
     * Copies constructor from other CarDetails
     *
     * @param otherBean, cannot be <code>null</code>
     * @throws java.lang.NullPointerException if the argument is <code>null</code>
     */
    public CarDetails(CarDetails otherBean)
    {
        this(otherBean.getName(), otherBean.getSerial(), otherBean.getOwnerName(), otherBean.getYear(), otherBean.getTimesRented());
    }

    /**
     * Copies all properties from the argument value object into this value object.
     */
    public void copy(CarDetails otherBean)
    {
        this.setName(otherBean.getName());
        this.setSerial(otherBean.getSerial());
        this.setOwnerName(otherBean.getOwnerName());
        this.setYear(otherBean.getYear());
        this.setTimesRented(otherBean.getTimesRented());
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

    private java.util.Date year;

    /**
     * 
     */
    public java.util.Date getYear()
    {
        return this.year;
    }

    public void setYear(java.util.Date year)
    {
        this.year = year;
    }

    private int timesRented;

    /**
     * 
     */
    public int getTimesRented()
    {
        return this.timesRented;
    }

    public void setTimesRented(int timesRented)
    {
        this.timesRented = timesRented;
    }

}