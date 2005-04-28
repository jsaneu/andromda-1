package org.andromda.cartridges.bpm4struts.metafacades;

import org.andromda.cartridges.bpm4struts.Bpm4StrutsProfile;
import org.andromda.core.common.StringUtilsHelper;
import org.andromda.metafacades.uml.EventFacade;
import org.andromda.metafacades.uml.GuardFacade;
import org.andromda.metafacades.uml.PseudostateFacade;
import org.andromda.metafacades.uml.StateVertexFacade;
import org.andromda.metafacades.uml.UseCaseFacade;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * MetafacadeLogic implementation.
 *
 * @see org.andromda.cartridges.bpm4struts.metafacades.StrutsForward
 */
public class StrutsForwardLogicImpl
        extends StrutsForwardLogic
{
    // ---------------- constructor -------------------------------

    public StrutsForwardLogicImpl(java.lang.Object metaObject, java.lang.String context)
    {
        super(metaObject, context);
    }

    // -------------------- business methods ----------------------

    // concrete business methods that were declared
    // abstract in class StrutsForward ...

    protected String handleGetGuardName()
    {
        final GuardFacade guard = getGuard();
        return (guard == null) ? null : guard.getName();
    }

    protected boolean handleIsEnteringPage()
    {
        return getTarget() instanceof StrutsJsp;
    }

    protected java.lang.String handleGetForwardName()
    {
        return StringUtilsHelper.toResourceMessageKey(resolveName());
    }

    protected java.lang.String handleGetForwardPath()
    {
        String forwardPath = null;

        final StateVertexFacade target = getTarget();
        if (isEnteringPage())
        {
            forwardPath = ((StrutsJsp)target).getFullPath() + ".jsp";
        }
        else if (isEnteringFinalState())
        {
            forwardPath = ((StrutsFinalState)target).getFullPath();
        }

        return forwardPath;
    }

    protected String handleGetActionMethodName()
    {
        return StringUtilsHelper.lowerCamelCaseName(resolveName());
    }

    protected String handleGetTargetNameKey()
    {
        if (isEnteringPage())
        {
            return ((StrutsJsp)getTarget()).getTitleKey();
        }
        else if (isEnteringFinalState())
        {
            return ((StrutsFinalState)getTarget()).getTargetUseCase().getTitleKey();
        }
        return null;
    }

    /**
     * If this forward has a trigger this method returns that trigger's name, otherwise if this forward
     * has a name this method returns that name, otherwise if this forward's target has a name this
     * method returns that name, otherwise simply returns <code>"unknown"</code>
     */
    private String resolveName()
    {
        String forwardName = null;
        //trigger
        final EventFacade trigger = getTrigger();
        if (trigger != null) forwardName = trigger.getName();
        //name
        if (forwardName == null) forwardName = getName();
        //target
        if (forwardName == null) forwardName = getTarget().getName();
        // else
        if (forwardName == null) forwardName = "unknown";
        // return
        return forwardName;
    }

    protected boolean handleIsExitingPage()
    {
        return getSource() instanceof StrutsJsp;
    }

    protected boolean handleIsSuccessMessagesPresent()
    {
        return getSuccessMessages().isEmpty() == false;
    }

    protected boolean handleIsWarningMessagesPresent()
    {
        return getWarningMessages().isEmpty() == false;
    }

    protected String handleGetMessageKey()
    {
        String messageKey = null;

        StrutsUseCase useCase = getUseCase();
        if (useCase != null)
        {
            messageKey = useCase.getName();
        }
        return messageKey;
    }

    /**
     * Collects specific messages in a map.
     *
     * @param messageType success or warning
     * @param messageKey the message resource key to use as a prefix
     * @param taggedValue the tagged value from which to read the message
     * @return maps message keys to message values, but only those that match the arguments
     *  will have been recorded
     */
    private Map getMessages(String messageType, String messageKey, String taggedValue)
    {
        Map messages = null;

        final Collection taggedValues = findTaggedValues(taggedValue);
        if (taggedValues.isEmpty())
        {
            messages = Collections.EMPTY_MAP;
        }
        else
        {
            messages = new LinkedHashMap(); // we want to keep the order

            final StringBuffer buffer = new StringBuffer();
            buffer.append(StringUtilsHelper.toResourceMessageKey(messageKey));
            buffer.append('.');
            buffer.append(messageType);
            buffer.append('.');

            final String prefix = buffer.toString();
            for (Iterator iterator = taggedValues.iterator(); iterator.hasNext();)
            {
                final String value = (String)iterator.next();
                messages.put(prefix + value.hashCode(), value);
            }
        }

        return messages;
    }

    protected Map handleGetSuccessMessages()
    {
        Map messages = null;

        String messageKey = getMessageKey();
        if (messageKey == null)
        {
            messages = Collections.EMPTY_MAP;
        }
        else
        {
            messages = getMessages("success", messageKey, Bpm4StrutsProfile.TAGGEDVALUE_ACTION_SUCCESS_MESSAGE);
        }

        return messages;
    }

    protected Map handleGetWarningMessages()
    {
        Map messages = null;

        String messageKey = getMessageKey();
        if (messageKey == null)
        {
            messages = Collections.EMPTY_MAP;
        }
        else
        {
            messages = getMessages("warning", messageKey, Bpm4StrutsProfile.TAGGEDVALUE_ACTION_WARNING_MESSAGE);
        }
        return messages;
    }

    protected java.util.Collection handleGetForwardParameters()
    {
        final EventFacade trigger = getTrigger();
        return (trigger == null) ? Collections.EMPTY_LIST : trigger.getParameters();
    }

    protected Object handleGetDecisionTrigger()
    {
        return (isEnteringDecisionPoint()) ? getTrigger() : null;
    }

    protected Object handleGetStrutsActivityGraph()
    {
        Object graph = getSource().getActivityGraph();
        return (graph instanceof StrutsActivityGraph) ? graph : null;
    }

    protected Collection handleGetActions()
    {
        final Set actions = new HashSet();
        findActions(actions, new HashSet());
        return actions;
    }

    /**
     * Recursively finds all actions for this forward, what this means depends on the context in which
     * this forward is used: if the source is a page action state it will collect all actions going out
     * of this page, if the source is a regular action state it will collect all actions that might traverse
     * this action state, if the source is the initial state it will collect all actions forwarding to this
     * forward's use-case (please not that those actions most likely are defined in other use-cases).
     *
     * @param actions the default set of actions, duplicates will not be recorded
     * @param handledForwards the forwards already processed
     */
    private void findActions(Set actions, Set handledForwards)
    {
        if (!handledForwards.contains(this))
        {
            handledForwards.add(this);

            if (this instanceof StrutsAction) // @todo this is not so nice because StrutsAction extends StrutsForward, solution would be to override in StrutsAction
            {
                actions.add(this);
            }
            else
            {
                StateVertexFacade vertex = getSource();
                if (vertex instanceof StrutsJsp)
                {
                    StrutsJsp jsp = (StrutsJsp)vertex;
                    actions.addAll(jsp.getActions());
                }
                else if (vertex instanceof StrutsActionState)
                {
                    StrutsActionState actionState = (StrutsActionState)vertex;
                    actions.addAll(actionState.getContainerActions());
                }
                else if (vertex instanceof PseudostateFacade)
                {
                    PseudostateFacade pseudostate = (PseudostateFacade)vertex;
                    if (!pseudostate.isInitialState())
                    {
                        Collection incomingForwards = pseudostate.getIncoming();
                        for (Iterator forwardIterator = incomingForwards.iterator(); forwardIterator.hasNext();)
                        {
                            StrutsForward forward = (StrutsForward)forwardIterator.next();
                            actions.addAll(forward.getActions());
                        }
                    }
                }
            }
        }
    }

    protected Object handleGetUseCase()
    {
        StrutsUseCase useCase = null;

        StrutsActivityGraph graph = getStrutsActivityGraph();
        if (graph != null)
        {
            UseCaseFacade graphUseCase = graph.getUseCase();
            if (graphUseCase instanceof StrutsUseCase)
            {
                useCase = (StrutsUseCase)graphUseCase;
            }
        }

        return useCase;
    }

    protected Object handleGetOperationCall()
    {
        StrutsControllerOperation operation = null;

        EventFacade triggerEvent = getTrigger();
        if (triggerEvent instanceof StrutsTrigger)
        {
            StrutsTrigger trigger = (StrutsTrigger)triggerEvent;
            operation = trigger.getControllerCall();
        }

        return operation;
    }
}
