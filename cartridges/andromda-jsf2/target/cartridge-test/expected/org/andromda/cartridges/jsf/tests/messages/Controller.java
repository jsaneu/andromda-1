// license-header java merge-point
// Generated by andromda-jsf cartridge (controllers\Controller.java.vsl) DO NOT EDIT!
package org.andromda.cartridges.jsf.tests.messages;

import java.io.Serializable;
import java.lang.reflect.Method;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.andromda.presentation.jsf.AdfFacesContextWrapper;
import org.andromda.presentation.jsf.FormPopulator;
import org.andromda.presentation.jsf.JsfUtils;
import org.andromda.presentation.jsf.Messages;
import org.andromda.presentation.jsf.PatternMatchingExceptionHandler;
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
    public abstract boolean doSomething()
        throws Throwable;

    /**
     * Resets all the "isSet" flags on the forms to false.
     */
    protected void resetFormSetFlags()
    {
        // Resets all the "isSet" flags on the forms to false.
    }

    /**
     * 
     * This method is called when 'submit' is triggered in the view 'three'.
     * It can be safely overridden in descendant classes.
     */
    protected void _three_submit()
    {
        //this method can be overridden
    }

    /**
     * @return threeSubmit
     */
    public String threeSubmit()
    {
        return threeSubmit((FacesEvent)null);
    }

    /**
     * @param event
     */
    public void threeSubmit(final ActionEvent event)
    {
        this.threeSubmit((FacesEvent)event);
    }

    /**
     * @param event
     */
    public void threeSubmit(final ValueChangeEvent event)
    {
        this.threeSubmit((FacesEvent)event);
    }

    /**
     * @param event
     * @return threeSubmit
     */
    public String threeSubmit(final FacesEvent event)
    {
        String forward = null;
        final Object currentForm = this.resolveVariable("form");
        try
        {
            //trigger method execution
            _three_submit();

            forward = _one();
            final FacesMessage.Severity messageSeverity = this.getMaximumMessageSeverity();
            if (messageSeverity != null && FacesMessage.SEVERITY_ERROR.getOrdinal() <= messageSeverity.getOrdinal())
            {
                this.setForm("form", currentForm, false);
            }
                // - only add info messages if we don't have any messages or warnings are the maximum severity
                else if (messageSeverity == null || messageSeverity.equals(FacesMessage.SEVERITY_WARN))
                {
                this.addInfoMessage(Messages.get("success.6.1", (Object[])null));
                }
                this.addWarningMessage(Messages.get("warning.6.1", (Object[])null));
            if (event != null)
            {
                MessagesActivityViewPopulator.populateFormAndViewVariables(this.getContext(), null);
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
     * @param forward
     * @return forward
     */
    protected String _one( String forward)
    {
        //this method can be overridden
        return forward;
    }

    /**
     * 
     * @return forward
     * @throws Throwable
     */
    private String _one()
        throws Throwable
    {
        String forward = null;
        forward = _two();
        forward = _one( forward);
        return forward;
    }

    /**
     * 
     * It can be safely overridden in descendant classes,
     * you should return the forward unless you want to change the default flow.
     * @param forward
     * @return forward
     */
    protected String _two( String forward)
    {
        //this method can be overridden
        return forward;
    }

    /**
     * 
     * @return forward
     * @throws Throwable
     */
    private String _two()
        throws Throwable
    {
        String forward = null;
        forward = __doSomething();
        forward = _two( forward);
        return forward;
    }

    /**
     * 
     * It can be safely overridden in descendant classes,
     * you should return the forward unless you want to change the default flow.
     * @param forward
     * @return forward
     */
    protected String _four( String forward)
    {
        //this method can be overridden
        return forward;
    }

    /**
     * 
     * @return forward
     * @throws Throwable
     */
    private String _four()
        throws Throwable
    {
        String forward = null;
        forward = _one();
        forward = _four( forward);
        return forward;
    }

    /**
     * 
     */
    private String __doSomething()
        throws Throwable
    {
        final String value = String.valueOf(doSomething());
        if (value.equals("true"))
        {
            this.addInfoMessage(Messages.get("success.4.1", (Object[])null));
            this.addWarningMessage(Messages.get("warning.4.1", (Object[])null));
            return "messages-activity-three";
        }
        if (value.equals("false"))
        {
            this.addInfoMessage(Messages.get("success.5.1", (Object[])null));
            this.addWarningMessage(Messages.get("warning.5.1", (Object[])null));
            return _four();
        }
        // we take the last action in case we have an invalid return value from the controller
        return _four();
    }

    /**
     * This method is called when the use case 'Messages Activity' starts.
     * It can be safely overridden in descendant classes.
     */
    protected void _messagesActivity_started()
    {
        //this method can be overridden
    }

    /**
     * @return messagesActivity
     */
    public String messagesActivity()
    {
        return messagesActivity((FacesEvent)null);
    }

    /**
     * @param event
     */
    public void messagesActivity(final ActionEvent event)
    {
        this.messagesActivity((FacesEvent)event);
    }

    /**
     * @param event
     */
    public void messagesActivity(final ValueChangeEvent event)
    {
        this.messagesActivity((FacesEvent)event);
    }

    /**
     * @param event
     * @return messagesActivity
     */
    public String messagesActivity(final FacesEvent event)
    {
        String forward = null;
        final Object currentForm = this.resolveVariable("form");
        try
        {
            //trigger method execution
            _messagesActivity_started();

            forward = _one();
            final FacesMessage.Severity messageSeverity = this.getMaximumMessageSeverity();
            if (messageSeverity != null && FacesMessage.SEVERITY_ERROR.getOrdinal() <= messageSeverity.getOrdinal())
            {
                this.setForm("form", currentForm, false);
            }
                // - only add info messages if we don't have any messages or warnings are the maximum severity
                else if (messageSeverity == null || messageSeverity.equals(FacesMessage.SEVERITY_WARN))
                {
                this.addInfoMessage(Messages.get("success.1.1", (Object[])null));
                this.addInfoMessage(Messages.get("success.1.2", (Object[])null));
                this.addInfoMessage(Messages.get("success.1.3", (Object[])null));
                }
                this.addWarningMessage(Messages.get("warning.1.1", (Object[])null));
                this.addWarningMessage(Messages.get("warning.1.2", (Object[])null));
                this.addWarningMessage(Messages.get("warning.1.3", (Object[])null));
            if (event != null)
            {
                MessagesActivityViewPopulator.populateFormAndViewVariables(this.getContext(), null);
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
     * @param forward
     * @return forward
     */
    protected String _one( String forward)
    {
        //this method can be overridden
        return forward;
    }

    /**
     * 
     * @return forward
     * @throws Throwable
     */
    private String _one()
        throws Throwable
    {
        String forward = null;
        forward = _two();
        forward = _one( forward);
        return forward;
    }

    /**
     * 
     * It can be safely overridden in descendant classes,
     * you should return the forward unless you want to change the default flow.
     * @param forward
     * @return forward
     */
    protected String _two( String forward)
    {
        //this method can be overridden
        return forward;
    }

    /**
     * 
     * @return forward
     * @throws Throwable
     */
    private String _two()
        throws Throwable
    {
        String forward = null;
        forward = __doSomething();
        forward = _two( forward);
        return forward;
    }

    /**
     * 
     * It can be safely overridden in descendant classes,
     * you should return the forward unless you want to change the default flow.
     * @param forward
     * @return forward
     */
    protected String _four( String forward)
    {
        //this method can be overridden
        return forward;
    }

    /**
     * 
     * @return forward
     * @throws Throwable
     */
    private String _four()
        throws Throwable
    {
        String forward = null;
        forward = _one();
        forward = _four( forward);
        return forward;
    }

    /**
     * 
     */
    private String __doSomething()
        throws Throwable
    {
        final String value = String.valueOf(doSomething());
        if (value.equals("true"))
        {
            this.addInfoMessage(Messages.get("success.4.1", (Object[])null));
            this.addWarningMessage(Messages.get("warning.4.1", (Object[])null));
            return "messages-activity-three";
        }
        if (value.equals("false"))
        {
            this.addInfoMessage(Messages.get("success.5.1", (Object[])null));
            this.addWarningMessage(Messages.get("warning.5.1", (Object[])null));
            return _four();
        }
        // we take the last action in case we have an invalid return value from the controller
        return _four();
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