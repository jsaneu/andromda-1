// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\Controller.java.vsl) DO NOT EDIT!
package org.andromda.cartridges.jsf.tests.tables.notablelink;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.andromda.presentation.jsf.AdfFacesContextWrapper;
import org.andromda.presentation.jsf.FormPopulator;
import org.andromda.presentation.jsf.JsfUtils;
import org.andromda.presentation.jsf.Messages;
import org.andromda.presentation.jsf.PatternMatchingExceptionHandler;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.trinidad.context.RequestContext;

/**
 * 
 */
public abstract class Controller
    implements Serializable{
    private static final long serialVersionUID = 1L;

    private static final Log logger = LogFactory.getLog(Controller.class);

    /**
     * 
     * @param form
     * @throws Throwable
     */
    public abstract void loadTableData()
        throws Throwable;

    /**
     * Resets all the "isSet" flags on the forms to false.
     */
    protected void resetFormSetFlags()
    {
        // Resets all the "isSet" flags on the forms to false.
        this.getShowTableDataAgainForm().resetIsSetFlags();
        this.getNoTableLinkActivityForm().resetIsSetFlags();
    }

    /**
     * Retrieves the {@link ShowTableDataAgainFormImpl} form instance (normally you wont't
     * need to call this method explicitly, however this is here for times when you need to access the
     * <em>noTableLinkActivityShowTableDataAgainForm</em> instance outside of its assigned controller operation).
     * @return ShowTableDataAgainFormImpl
     */
    protected ShowTableDataAgainFormImpl getShowTableDataAgainForm()
    {
        // - we do this in the case a button that submitted the form was set to immediate (this should be removed
        //   when we found a better way to handle this).
        final UIForm uiForm = this.findForm(this.getContext().getViewRoot(), "noTableLinkActivityShowTableDataAgainForm");
        this.populateComponentInputs(uiForm);
        return (ShowTableDataAgainFormImpl)this.resolveVariable("noTableLinkActivityShowTableDataAgainForm");
    }

    /**
     * 
     * This method is called when 'again' is triggered in the view 'show table data'.
     * It can be safely overridden in descendant classes.
ShowTableDataAgainFormImpl)
     * @param form
     */
    protected void _showTableData_again(ShowTableDataAgainFormImpl form)
    {
        //this method can be overridden
    }

    /**
     * @return showTableDataAgain
     */
    public String showTableDataAgain()
    {
        return showTableDataAgain((FacesEvent)null);
    }

    /**
     * @param event
     */
    public void showTableDataAgain(final ActionEvent event)
    {
        this.showTableDataAgain((FacesEvent)event);
    }

    /**
     * @param event
     */
    public void showTableDataAgain(final ValueChangeEvent event)
    {
        this.showTableDataAgain((FacesEvent)event);
    }

    /**
     * @param event
     * @return showTableDataAgain
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String showTableDataAgain(final FacesEvent event)
    {
        String forward = null;
        final Object currentForm = this.resolveVariable("form");
        try
        {
            final ShowTableDataAgainFormImpl form =
                this.getShowTableDataAgainForm();
            form.setEvent(event);
            this.setForm("form", form, true);
            // - pass any properties from the previous form along
            FormPopulator.populateForm(currentForm, form);
            // - populate the form with any event attributes that may match
            // IMPORTANT: it isn't possible to automatically populate any property named "id" since that
            // is a reserved name in JSF (the id of a component), therefore we have to unfortunately ignore any available "id" attribute
            FormPopulator.populateFormFromPropertyMap(
                form, form.getDateTimeFormatters(), (Map)this.getRequestAttribute(ACTION_EVENT_ATTRIBUTES), new String[] {"id"});
            if (event != null)
            {
                final Map eventProperties = new HashMap();
                eventProperties.put(event.getComponent().getId(), event.getSource());
                FormPopulator.populateFormFromPropertyMapAssignableTypesOnly(form, null, eventProperties);
                FormPopulator.populateFormFromPropertyMap(
                    form, form.getDateTimeFormatters(), event.getComponent().getAttributes(), new String[] {"id"});
            }
            // - populate the form with any request attributes that may match
            FormPopulator.populateFormFromRequestAttributes(form, form.getDateTimeFormatters(), false, true);
            // - populate the form with any request parameters that may match
            FormPopulator.populateFormFromPropertyMap(
                form, form.getDateTimeFormatters(), this.getContext().getExternalContext().getRequestParameterMap());
            //trigger method execution
            _showTableData_again(form);

            forward = _loadTableData(form);
            final FacesMessage.Severity messageSeverity = this.getMaximumMessageSeverity();
            if (messageSeverity != null && FacesMessage.SEVERITY_ERROR.getOrdinal() <= messageSeverity.getOrdinal())
            {
                // - copy any messages to the 'currentForm'
                PropertyUtils.setProperty(currentForm,
                    "jsfMessages", PropertyUtils.getProperty(form, "jsfMessages"));
                this.setForm("form", currentForm, false);
            }
            if (event != null)
            {
                NoTableLinkActivityViewPopulator.populateFormAndViewVariables(this.getContext(), form);
            }
        }
        catch (final Throwable throwable)
        {
            this.setForm("form", currentForm, false);
            // - set the forward to null so that we stay on the current view
            forward = null;
            try
            {
                // - the exception is re-thrown by the exception handler and handled by the catch below if it can't get a messageKey
                //   (no reason to check for presence of messageKey)
                this.addErrorMessage(Messages.get(
                    PatternMatchingExceptionHandler.instance().handleException(throwable),
                    PatternMatchingExceptionHandler.instance().getMessageArguments(throwable)));
            }
            catch (Throwable exception)
            {
                logger.error(exception.getMessage(),exception);
                this.addExceptionMessage(exception);
            }
        }
        return forward;
    }

    /**
     * 
     * It can be safely overridden in descendant classes,
     * you should return the forward unless you want to change the default flow.
     * @param form
     * @param forward
     * @return forward
     */
    protected String _loadTableData(final ShowTableDataAgainFormImpl form, String forward)
    {
        //this method can be overridden
        return forward;
    }

    /**
     * 
     * @param form
     * @return forward
     * @throws Throwable
     */
    private String _loadTableData(final ShowTableDataAgainFormImpl form)
        throws Throwable
    {
        String forward = null;
        loadTableData();
        forward = "no-table-link-activity-show-table-data";
        forward = _loadTableData(form, forward);
        return forward;
    }

    /**
     * Retrieves the {@link NoTableLinkActivityFormImpl} form instance (normally you wont't
     * need to call this method explicitly, however this is here for times when you need to access the
     * <em>noTableLinkActivityNoTableLinkActivityForm</em> instance outside of its assigned controller operation).
     * @return NoTableLinkActivityFormImpl
     */
    protected NoTableLinkActivityFormImpl getNoTableLinkActivityForm()
    {
        // - we do this in the case a button that submitted the form was set to immediate (this should be removed
        //   when we found a better way to handle this).
        final UIForm uiForm = this.findForm(this.getContext().getViewRoot(), "noTableLinkActivityNoTableLinkActivityForm");
        this.populateComponentInputs(uiForm);
        return (NoTableLinkActivityFormImpl)this.resolveVariable("noTableLinkActivityNoTableLinkActivityForm");
    }

    /**
     * This method is called when the use case 'No Table Link Activity' starts.
     * It can be safely overridden in descendant classes.
NoTableLinkActivityFormImpl)
     * @param form
     */
    protected void _noTableLinkActivity_started(NoTableLinkActivityFormImpl form)
    {
        //this method can be overridden
    }

    /**
     * @return noTableLinkActivity
     */
    public String noTableLinkActivity()
    {
        return noTableLinkActivity((FacesEvent)null);
    }

    /**
     * @param event
     */
    public void noTableLinkActivity(final ActionEvent event)
    {
        this.noTableLinkActivity((FacesEvent)event);
    }

    /**
     * @param event
     */
    public void noTableLinkActivity(final ValueChangeEvent event)
    {
        this.noTableLinkActivity((FacesEvent)event);
    }

    /**
     * @param event
     * @return noTableLinkActivity
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String noTableLinkActivity(final FacesEvent event)
    {
        String forward = null;
        final Object currentForm = this.resolveVariable("form");
        try
        {
            final NoTableLinkActivityFormImpl form =
                this.getNoTableLinkActivityForm();
            form.setEvent(event);
            this.setForm("form", form, true);
            // - pass any properties from the previous form along
            FormPopulator.populateForm(currentForm, form);
            // - populate the form with any event attributes that may match
            // IMPORTANT: it isn't possible to automatically populate any property named "id" since that
            // is a reserved name in JSF (the id of a component), therefore we have to unfortunately ignore any available "id" attribute
            FormPopulator.populateFormFromPropertyMap(
                form, form.getDateTimeFormatters(), (Map)this.getRequestAttribute(ACTION_EVENT_ATTRIBUTES), new String[] {"id"});
            if (event != null)
            {
                final Map eventProperties = new HashMap();
                eventProperties.put(event.getComponent().getId(), event.getSource());
                FormPopulator.populateFormFromPropertyMapAssignableTypesOnly(form, null, eventProperties);
                FormPopulator.populateFormFromPropertyMap(
                    form, form.getDateTimeFormatters(), event.getComponent().getAttributes(), new String[] {"id"});
            }
            // - populate the form with any request attributes that may match
            FormPopulator.populateFormFromRequestAttributes(form, form.getDateTimeFormatters(), false, true);
            // - populate the form with any request parameters that may match
            FormPopulator.populateFormFromPropertyMap(
                form, form.getDateTimeFormatters(), this.getContext().getExternalContext().getRequestParameterMap());
            //trigger method execution
            _noTableLinkActivity_started(form);

            forward = _loadTableData(form);
            final FacesMessage.Severity messageSeverity = this.getMaximumMessageSeverity();
            if (messageSeverity != null && FacesMessage.SEVERITY_ERROR.getOrdinal() <= messageSeverity.getOrdinal())
            {
                // - copy any messages to the 'currentForm'
                PropertyUtils.setProperty(currentForm,
                    "jsfMessages", PropertyUtils.getProperty(form, "jsfMessages"));
                this.setForm("form", currentForm, false);
            }
            if (event != null)
            {
                NoTableLinkActivityViewPopulator.populateFormAndViewVariables(this.getContext(), form);
            }
        }
        catch (final Throwable throwable)
        {
            this.setForm("form", currentForm, false);
            // - set the forward to null so that we stay on the current view
            forward = null;
            try
            {
                // - the exception is re-thrown by the exception handler and handled by the catch below if it can't get a messageKey
                //   (no reason to check for presence of messageKey)
                this.addErrorMessage(Messages.get(
                    PatternMatchingExceptionHandler.instance().handleException(throwable),
                    PatternMatchingExceptionHandler.instance().getMessageArguments(throwable)));
            }
            catch (Throwable exception)
            {
                logger.error(exception.getMessage(),exception);
                this.addExceptionMessage(exception);
            }
        }
        return forward;
    }

    /**
     * 
     * It can be safely overridden in descendant classes,
     * you should return the forward unless you want to change the default flow.
     * @param form
     * @param forward
     * @return forward
     */
    protected String _loadTableData(final NoTableLinkActivityFormImpl form, String forward)
    {
        //this method can be overridden
        return forward;
    }

    /**
     * 
     * @param form
     * @return forward
     * @throws Throwable
     */
    private String _loadTableData(final NoTableLinkActivityFormImpl form)
        throws Throwable
    {
        String forward = null;
        loadTableData();
        forward = "no-table-link-activity-show-table-data";
        forward = _loadTableData(form, forward);
        return forward;
    }

    /**
     * Gets the current faces context.  This object is the point
     * from which to retrieve any request, session, etc information.
     *
     * @return the JSF faces context instance.
     */
    public FacesContext getContext()
    {
        return FacesContext.getCurrentInstance();
    }

    /**
     * A helper method that gets the current request from the faces
     * context.
     *
     * @return the current request instance.
     */
    protected HttpServletRequest getRequest()
    {
        return (HttpServletRequest)this.getContext().getExternalContext().getRequest();
    }

    /**
     * A helper method that gets the current reponse from the faces
     * context.
     *
     * @return the current response instance.
     */
    protected HttpServletResponse getResponse()
    {
        return (HttpServletResponse)this.getContext().getExternalContext().getResponse();
    }

    /**
     * A helper method that gets the current session from the faces
     * context.
     *
     * @param create If the create parameter is true, create (if necessary) and return a
     *        session instance associated with the current request. If the create
     *        parameter is false return any existing session instance associated with the
     *        current request, or return null if there is no such session.
     * @return the current session instance.
     */
    protected HttpSession getSession(final boolean create)
    {
        return (HttpSession)this.getContext().getExternalContext().getSession(create);
    }

    /**
     * Attempts to resolve the variable, given, the <code>name</code> of
     * the variable using the faces context variable resolver instance.
     * @param name
     * @return the resolved variable or null if not found.
     */
    @SuppressWarnings("deprecation")
    protected Object resolveVariable(final String name)
    {
        RequestContext adfContext = RequestContext.getCurrentInstance();
        Object variable = adfContext.getPageFlowScope().get(name);
        // - if we couldn't get the variable from the regular ADF context, see if
        //   the session contains an ADF context with the variable
        if (variable == null)
        {
            final HttpSession session = this.getSession(false);
            if (session != null)
            {
                final AdfFacesContextWrapper contextWrapper =
                    (AdfFacesContextWrapper)session.getAttribute("AndroMDAADFContext");
                adfContext = contextWrapper != null ? contextWrapper.getCurrentInstance() : null;
            }
            variable = adfContext != null ? adfContext.getPageFlowScope().get(name) : null;
        }
        // - finally try resolving it in the standard JSF manner
        if (variable == null)
        {
            final FacesContext context = this.getContext();
            variable = context != null ? context.getApplication().getVariableResolver().resolveVariable(context, name) : null;
        }
        return variable;
    }

    private void setForm(final String formKey, final Object form, boolean includeInSession)
    {
        final AdfFacesContextWrapper contextWrapper = new AdfFacesContextWrapper();
        contextWrapper.getCurrentInstance().getPageFlowScope().put(formKey, form);
        if (includeInSession)
        {
            this.setSessionAttribute(formKey, form);
            // - add this temporary ADF context to the session so that we can retrieve from a view populator if required
            this.getSession(false).setAttribute("AndroMDAADFContext", contextWrapper);
        }
    }

    /**
     * Finds the root cause of the given <code>throwable</code> and
     * adds the message taken from that cause to the faces context messages.
     *
     * @param throwable the exception information to add.
     */
    protected final void addExceptionMessage(
        Throwable throwable)
    {
        String message = null;
        final Throwable rootCause = ExceptionUtils.getRootCause(throwable);
        if (rootCause != null)
        {
            message = rootCause.toString();
        }
        if (message == null || message.trim().length() == 0)
        {
            message = throwable.toString();
        }
        this.addErrorMessage(message);
    }

    /**
     * Adds the given error <code>message</code> to the current faces context.
     *
     * @param message the message to add.
     */
    protected void addErrorMessage(final String message)
    {
        this.addMessage(FacesMessage.SEVERITY_ERROR, message);
    }

    /**
     * Adds the given warning <code>message</code> to the current faces context.
     *
     * @param message the message to add.
     */
    protected void addWarningMessage(final String message)
    {
        this.addMessage(FacesMessage.SEVERITY_WARN, message);
    }

    /**
     * Adds the given info <code>message</code> to the current faces context.
     *
     * @param message the message to add.
     */
    protected void addInfoMessage(final String message)
    {
        this.addMessage(FacesMessage.SEVERITY_INFO, message);
    }

    /**
     * Adds the given fatal <code>message</code> to the current faces context.
     *
     * @param message the message to add.
     */
    protected void addFatalMessage(final String message)
    {
        this.addMessage(FacesMessage.SEVERITY_FATAL, message);
    }

    /**
     * Adds a message to the faces context (which will show up on your view when using the
     * lt;h:messages/gt; tag).
     *
     * @param severity the severity of the message
     * @param message the message to add.
     */
    protected void addMessage(final FacesMessage.Severity severity, final String message)
    {
        final FacesMessage facesMessage = new FacesMessage(severity, message, message);
        final Object form = this.resolveVariable("form");
        if (form != null)
        {
            try
            {
                final Method method = form.getClass().getMethod(
                    "addJsfMessages",
                    new Class[]{FacesMessage.class});
                method.invoke(form, new Object[]{facesMessage});
            }
            catch (final Exception exception)
            {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * Sets the messages title to use on the next view.
     *
     * @param messagesTitle the title to use.
     */
    protected void setMessagesTitle(final String messagesTitle)
    {
        final Object form = this.resolveVariable("form");
        if (form != null)
        {
            try
            {
                final Method method = form.getClass().getMethod(
                    "setJsfMessagesTitle",
                    new Class[]{String.class});
                method.invoke(form, new Object[]{messagesTitle});
            }
            catch (final Exception exception)
            {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * Gets the maximum severity of the messages stored in the current form.
     *
     * @return the maximum message severity.
     */
    protected FacesMessage.Severity getMaximumMessageSeverity()
    {
        FacesMessage.Severity maximumSeverity = null;
        final Object form = this.resolveVariable("form");
        if (form != null)
        {
            try
            {
                final Method method = form.getClass().getMethod(
                    "getMaximumMessageSeverity",
                    (Class[])null);
                maximumSeverity = (FacesMessage.Severity)method.invoke(form, (Object[])null);
            }
            catch (final Exception exception)
            {
                throw new RuntimeException(exception);
            }
        }
        return maximumSeverity;
    }

    /**
     * Copies all matching properties from the <code>fromForm</code> to the given
     * <code>toForm</code> overriding any previously set values.
     * @param fromForm
     * @param toForm
     */
    protected void copyForm(final Object fromForm, final Object toForm)
    {
        FormPopulator.populateForm(fromForm, toForm, true);
    }

    /**
     * Finds the form (if one is present) on the given <code>component</code> having the given
     * <code>id</code>.
     *
     * @param component the component to search.
     * @param id the id of the form.
     * @return the form or null if none was found.
     */
    private UIForm findForm(UIComponent component, String id)
    {
        UIForm foundForm = null;
        if (component != null)
        {
            for (final Iterator<UIComponent> iterator = component.getFacetsAndChildren(); iterator.hasNext();)
            {
                final UIComponent uiComponent = iterator.next();
                if (uiComponent instanceof UIForm)
                {
                    final UIForm form = (UIForm)uiComponent;
                    if (form.getId().equals(id))
                    {
                        foundForm = form;
                        break;
                    }
                }
                foundForm = this.findForm(uiComponent, id);
                if (foundForm != null)
                {
                    break;
                }
            }
        }
        return foundForm;
    }

    /**
     * If the given <code>component</code> has an child input elements, this method finds
     * them all and populates them.  This is to get around the fact that when immediate is set to true
     * on a button that submits the form that the form isn't populated.
     *
     * @param component the component to populate.
     */
    private void populateComponentInputs(UIComponent component)
    {
        if (component != null)
        {
            for (final Iterator<UIComponent> iterator = component.getFacetsAndChildren(); iterator.hasNext();)
            {
                final UIComponent uiComponent = iterator.next();
                if (uiComponent instanceof UIInput)
                {
                    try
                    {
                        final UIInput input = (UIInput)uiComponent;
                        input.validate(this.getContext());
                        input.updateModel(this.getContext());
                    }
                    catch (ValidatorException exception)
                    {
                        // - ignore, no value is set (validate will be called by the regular
                        //   JSF lifecycle processing anyway, this is just called to populate the
                        //   local value
                    }
                }
                else
                {
                    this.populateComponentInputs(uiComponent);
                }
            }
        }
    }
    /**
     * Retrieves the current action form while making sure the form is of the given
     * <code>type</code>.  If the action form is found, but not of the given type, null will
     * be returned.
     *
     * @param type the type of form to retrieve.
     * @return the found form.
     */
    protected Object getCurrentActionForm(final Class<?> type)
    {
        Object form = this.getCurrentActionForm();
        if (!type.isInstance(form))
        {
            form = null;
        }
        return form;
    }

    /**
     * Retrieves the current action form instance.
     *
     * @return the current action form instance.
     */
    protected Object getCurrentActionForm()
    {
        return this.resolveVariable("form");
    }

    /**
     * The name of the request attribute that stores the attributes from the current action event.
     */
    private static final String ACTION_EVENT_ATTRIBUTES = "actionEventAttributes";

    /**
     * This method just captures the event attributes and sets them into the request
     * so that we can retrieve in controller action operation and use to populate form.
     *
     * @param event the action event.
     */
    public void action(ActionEvent event)
    {
        this.setRequestAttribute(ACTION_EVENT_ATTRIBUTES, event.getComponent().getAttributes());
    }

    /**
     * @param name
     * @param object
     */
    protected void setRequestAttribute(final String name, final Object object)
    {
        JsfUtils.setAttribute(this.getContext().getExternalContext().getRequest(), name, object);
    }

    /**
     * @param name
     * @return RequestAttribute
     */
    protected Object getRequestAttribute(final String name)
    {
        return JsfUtils.getAttribute(this.getContext().getExternalContext().getRequest(), name);
    }

    /**
     * @param name
     * @param object
     */
    protected void setSessionAttribute(final String name, final Object object)
    {
        JsfUtils.setAttribute(this.getContext().getExternalContext().getSession(false), name, object);
    }

    /**
     * @param name
     * @return SessionAttribute
     */
    protected Object getSessionAttribute(final String name)
    {
        return JsfUtils.getAttribute(this.getContext().getExternalContext().getSession(false), name);
    }

}