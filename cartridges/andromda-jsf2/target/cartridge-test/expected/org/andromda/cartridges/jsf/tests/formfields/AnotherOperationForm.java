// license-header java merge-point
// Generated by andromda-jsf cartridge (forms\Form.java.vsl) DO NOT EDIT!
package org.andromda.cartridges.jsf.tests.formfields;

import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;

/**
 * This form encapsulates the fields that are used in the execution of the
 * <code>anotherOperation</code> method, which is located on the
 * <code>org.andromda.cartridges.jsf.tests.formfields.Controller</code> controller.
 *
 * 
 *
 * @see org.andromda.cartridges.jsf.tests.formfields.Controller#anotherOperation(AnotherOperationForm)
 */
public interface AnotherOperationForm
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
     * @return notused
     */
    public String getNotused();

    /**
     * 
     * @param notused
     */
    public void setNotused(String notused);

    /**
     * 
     * @return unusedNumber
     */
    public int getUnusedNumber();

    /**
     * 
     * @param unusedNumber
     */
    public void setUnusedNumber(int unusedNumber);

}