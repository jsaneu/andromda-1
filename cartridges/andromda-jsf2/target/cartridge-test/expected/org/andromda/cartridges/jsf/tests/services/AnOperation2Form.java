// license-header java merge-point
// Generated by andromda-jsf cartridge (forms\Form.java.vsl) DO NOT EDIT!
package org.andromda.cartridges.jsf.tests.services;

import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;

/**
 * This form encapsulates the fields that are used in the execution of the
 * <code>anOperation2</code> method, which is located on the
 * <code>org.andromda.cartridges.jsf.tests.services.Controller</code> controller.
 *
 * 
 *
 * @see org.andromda.cartridges.jsf.tests.services.Controller#anOperation2(AnOperation2Form)
 */
public interface AnOperation2Form
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
     * @return one
     */
    public String getOne();

    /**
     * 
     * @param one
     */
    public void setOne(String one);

    /**
     * 
     * @return two
     */
    public int getTwo();

    /**
     * 
     * @param two
     */
    public void setTwo(int two);

    /**
     * 
     * @return three
     */
    public String getThree();

    /**
     * 
     * @param three
     */
    public void setThree(String three);

}