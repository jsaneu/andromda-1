// license-header java merge-point
// Generated by andromda-jsf cartridge (forms\Form.java.vsl) DO NOT EDIT!
package org.andromda.cartridges.jsf.tests.deferringoperations;

/**
 * This form encapsulates the fields that are used in the execution of the
 * <code>missingArgumentName</code> method, which is located on the
 * <code>org.andromda.cartridges.jsf.tests.deferringoperations.Controller</code> controller.
 *
 * This operation exists to test that parameters must have a non-empty name.
 *
 * @see org.andromda.cartridges.jsf.tests.deferringoperations.Controller#missingArgumentName(MissingArgumentNameForm)
 */
public interface MissingArgumentNameForm
{
    /**
     * Gets the ValueChangeEvent (if any) that is associated with this form.
     *
     * @return the faces ValueChangeEvent associated to this form.
     */
    public javax.faces.event.ValueChangeEvent getValueChangeEvent();

    /**
     * Gets the ActionEvent (if any) that is associated with this form.
     *
     * @return the faces ActionEvent associated to this form.
     */
    public javax.faces.event.ActionEvent getActionEvent();

    /**
     * Sets the event (if any) that is associated with this form.
     *
     * @param event the faces event to associate to this form.
     */
    public void setEvent(javax.faces.event.FacesEvent event);

    /**
     * 
     * @return 
     */
    public java.lang.String get();

    /**
     * 
     * @param 
     */
    public void set(java.lang.String );

}
