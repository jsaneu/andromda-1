package org.andromda.cartridges.bpm4struts.metafacades;

import org.andromda.cartridges.bpm4struts.Bpm4StrutsProfile;
import org.andromda.metafacades.uml.ModelElementFacade;
import org.andromda.metafacades.uml.PseudostateFacade;
import org.andromda.metafacades.uml.UseCaseFacade;

import java.util.Collection;


/**
 * MetafacadeLogic implementation.
 *
 * @see org.andromda.cartridges.bpm4struts.metafacades.StrutsActivityGraph
 */
public class StrutsActivityGraphLogicImpl
        extends StrutsActivityGraphLogic
        implements org.andromda.cartridges.bpm4struts.metafacades.StrutsActivityGraph
{
    // ---------------- constructor -------------------------------

    public StrutsActivityGraphLogicImpl(java.lang.Object metaObject, java.lang.String context)
    {
        super(metaObject, context);
    }
    // ------------- relations ------------------

    protected Object handleGetFirstAction()
    {
        Object firstAction = null;
        Collection initialStates = getInitialStates();
        if (initialStates.isEmpty() == false)
        {
            PseudostateFacade initialState = (PseudostateFacade)initialStates.iterator().next();
            Collection outgoing = initialState.getOutgoing();
            firstAction = (outgoing.isEmpty()) ? null : outgoing.iterator().next();
        }
        return firstAction;
    }

    /**
     * This method is 'overriding' the handleGetUseCase method from the ActivityGraphFacade class.
     */
    protected Object handleGetUseCase()
    {
        // this does not cause an endless loop because this facade does not define 'getUseCase'
        UseCaseFacade useCase = getUseCase();

        // If this was not the case then look for a use-case pointing to this activity graph using a tagged
        // value
        if (useCase == null)
        {
            useCase = getModel().findUseCaseWithTaggedValueOrHyperlink(Bpm4StrutsProfile.TAGGEDVALUE_USECASE_ACTIVITY,getName());
        }

        if ((useCase instanceof StrutsUseCase) == false)
        {
            // unset the use-case because it should not be processed (eg. missing <<FrontEndUseCase>> stereotype)
            useCase = null;
        }
        return useCase;
    }

    protected Object handleGetController()
    {
        Object controller = null;

        final ModelElementFacade contextElement = getContextElement();
        if (contextElement instanceof StrutsController)
        {
            controller = contextElement;
        }

        /*
         * for those tools not supporting setting the context of an activity graph (such as Poseidon)
         * an alternative is implemented: a tagged value on the controller, specifying the name of the use-case
         *
         * It is also allowed to set a hyperlink from the controller to the usecase
         */
        if (controller == null)
        {
            UseCaseFacade useCase = this.getUseCase();
            if (useCase != null)
            {
                final String useCaseName = useCase.getName();
                controller = getModel().findClassWithTaggedValueOrHyperlink(
                        Bpm4StrutsProfile.TAGGEDVALUE_CONTROLLER_USE_CASE,useCaseName);
            }
        }

        return controller;
    }
}
