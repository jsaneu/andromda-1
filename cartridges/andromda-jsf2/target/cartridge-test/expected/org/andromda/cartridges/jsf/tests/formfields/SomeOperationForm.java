// license-header java merge-point
// Generated by andromda-jsf cartridge (forms\Form.java.vsl) DO NOT EDIT!
package org.andromda.cartridges.jsf.tests.formfields;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;

/**
 * This form encapsulates the fields that are used in the execution of the
 * <code>someOperation</code> method, which is located on the
 * <code>org.andromda.cartridges.jsf.tests.formfields.Controller</code> controller.
 *
 * 
 *
 * @see org.andromda.cartridges.jsf.tests.formfields.Controller#someOperation(SomeOperationForm)
 */
public interface SomeOperationForm
{
    /**
     * Gets the ValueChangeEvent (if any) that is associated with this form.
     *
     * @return the faces ValueChangeEvent associated to this form.
     */
    public ValueChangeEvent getValueChangeEvent();

    /**
     * Gets the ActionEvent (if any) that is associated with this form.
     *
     * @return the faces ActionEvent associated to this form.
     */
    public ActionEvent getActionEvent();

    /**
     * Sets the event (if any) that is associated with this form.
     *
     * @param event the faces event to associate to this form.
     */
    public void setEvent(FacesEvent event);

    /**
     * 
     * @return date
     */
    public Date getDate();

    /**
     * 
     * @param date
     */
    public void setDate(Date date);

    /**
     * 
     * @return number
     */
    public int getNumber();

    /**
     * 
     * @param number
     */
    public void setNumber(int number);

    /**
     * 
     * @return collections
     */
    public Collection getCollections();

    /**
     * 
     * @param collections
     */
    public void setCollections(Collection collections);

    /**
     * 
     * @return selectable
     */
    public String getSelectable();

    /**
     * 
     * @param selectable
     */
    public void setSelectable(String selectable);

    /**
     * The <code>selectable</code> field can be selected from a list,
     * this method allows you to retrieve the current elements from that list.
     * <p/>
     * <i>Please note that the elements from that list are key value pairs, so you will
     * need to call <code>getLabel()</code> and <code>getValue()</code> to get the label and
     * value for this entry respectively.</i>
     *
     * @return Object[]
     * @see #getSelectable()
     * @see #getSelectableValueList()
     * @see #getSelectableLabelList()
     * @see #setSelectableLabelList(Collection,String,String)
     */
    public Object[] getSelectableBackingList();

    /**
     * Convenient method to quickly set the value and label backing list for the
     * selectable property. This method takes a collection of objects, as well as
     * the property names to query these objects in order to find the corresponding
     * values and labels.
     * <p/>
     * Let's say you have a set of value objects with the following properties:
     * <ul>
     *  <li><code>id</code></li>
     *  <li><code>code</code></li>
     *  <li><code>name</code></li>
     *  <li><code>description</code></li>
     * </ul>
     * But you need to populate the selectable backing list with the <code>id</code> properties as the values and the
     * <code>name</code> properties as the labels then you would make a call like this:
     * <code>setSelectableBackingList(valueObjects, "id", "name")</code>
     * <p/>
     * This method knows how to handle primitive property types as it simply delegates to
     * <code>org.apache.commons.beanutils.PropertyUtils.getProperty(Object, String)</code>.
     *
     * @param items The items from which to read the properties, if this argument is <code>null</code> this method
     *        will simply clear the existing values and labels
     * @param valueProperty the name of the property to query on each object to retrieve
     *        the corresponding value, cannot be <code>null</code>
     * @throws java.lang.IllegalArgumentException if the valueProperty or labelProperty is <code>null</code>
     * @throws java.lang.RuntimeException if one of the items in the collection is <code>null</code>, or
     *         if the caller does not have access one of the object's properties, if an exception was thrown while
     *         accessing a property or if the property does not exist on at least one of the items
     *
     * @see #getSelectable()
     * @see #getSelectableValueList()
     * @see #getSelectableLabelList()
     * @see #getSelectableLabelList()
     */
    public void setSelectableBackingList(Collection<? extends Object> items, String valueProperty, String labelProperty);

    /**
     * The <code>selectable</code> field can be selected from a list,
     * this method allows you to retrieve the values from that list.
     *
     * @return Object[]
     * @see #getSelectable()
     * @see #getSelectableBackingList()
     */
    public Object[] getSelectableValueList();

    /**
     * The <code>selectable</code> field can be selected from a list,
     * this method allows you to set the values for that list.
     *
     * @param selectableValueList
     * @see #getSelectable()
     * @see #getSelectableBackingList()
     */
    public void setSelectableValueList(Object[] selectableValueList);

    /**
     * The <code>selectable</code> field can be selected from a list,
     * this method allows you to retrieve the labels from that list.
     *
     * @return Object[]
     * @see #getSelectable()
     * @see #getSelectableBackingList()
     */
    public Object[] getSelectableLabelList();

    /**
     * The <code>selectable</code> field can be selected from a list,
     * this method allows you to set the labels for that list.
     *
     * @param selectableLabelList
     * @see #getSelectable()
     * @see #getSelectableBackingList()
     */
    public void setSelectableLabelList(Object[] selectableLabelList);

    /**
     * 
     * @return bool
     */
    public boolean isBool();

    /**
     * 
     * @param bool
     */
    public void setBool(boolean bool);

    /**
     * 
     * @return file
     */
    public UploadedFile getFile();

    /**
     * 
     * @param file
     */
    public void setFile(UploadedFile file);

    /**
     * 
     * @return integerClass
     */
    public Integer getIntegerClass();

    /**
     * 
     * @param integerClass
     */
    public void setIntegerClass(Integer integerClass);

    /**
     * 
     * @return setClasses
     */
    public Collection getSetClasses();

    /**
     * 
     * @param setClasses
     */
    public void setSetClasses(Collection setClasses);

    /**
     * 
     * @return mapClass
     */
    public Map getMapClass();

    /**
     * 
     * @param mapClass
     */
    public void setMapClass(Map mapClass);

    /**
     * 
     * @return booleanClass
     */
    public Boolean getBooleanClass();

    /**
     * 
     * @param booleanClass
     */
    public void setBooleanClass(Boolean booleanClass);

    /**
     * 
     * @return floatClass
     */
    public Float getFloatClass();

    /**
     * 
     * @param floatClass
     */
    public void setFloatClass(Float floatClass);

}