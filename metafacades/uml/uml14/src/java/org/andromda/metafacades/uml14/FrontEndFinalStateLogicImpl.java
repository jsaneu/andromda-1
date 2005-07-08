package org.andromda.metafacades.uml14;

import org.andromda.metafacades.uml.FrontEndActivityGraph;
import org.andromda.metafacades.uml.FrontEndUseCase;
import org.andromda.metafacades.uml.UMLProfile;
import org.andromda.metafacades.uml.UseCaseFacade;
import org.apache.commons.lang.StringUtils;


/**
 * MetafacadeLogic implementation for org.andromda.metafacades.uml.FrontEndFinalState.
 *
 * @see org.andromda.metafacades.uml.FrontEndFinalState
 */
public class FrontEndFinalStateLogicImpl
    extends FrontEndFinalStateLogic
{

    public FrontEndFinalStateLogicImpl (Object metaObject, String context)
    {
        super (metaObject, context);
    }

    /**
     * @see org.andromda.metafacades.uml.FrontEndFinalState#isContainedInFrontEndUseCase()
     */
    protected boolean handleIsContainedInFrontEndUseCase()
    {
        return this.getStateMachine() instanceof FrontEndActivityGraph;
    }
    
    public String handleGetName()
    {
        String name = super.handleGetName();
        if (name == null)
        {
            FrontEndUseCase useCase = this.getTargetUseCase();
            if (useCase != null)
            {
                name = useCase.getName();
            }
        }
        return name;
    }
    
    /**
     * @see org.andromda.metafacades.uml.FrontEndFinalState#getTargetUseCase()
     */
    protected Object handleGetTargetUseCase()
    {
        Object targetUseCase = null;

        // first check if there is a hyperlink from this final state to a use-case
        // this works at least in MagicDraw
        final Object taggedValue = this.findTaggedValue(UMLProfile.TAGGEDVALUE_HYPERLINK);
        if (taggedValue != null)
        {
            if (taggedValue instanceof FrontEndActivityGraph)
            {
                targetUseCase = ((FrontEndActivityGraph)taggedValue).getUseCase();
            }
            else if (taggedValue instanceof FrontEndUseCase)
            {
                targetUseCase = taggedValue;
            }
        }
        else // maybe the name points to a use-case ?
        {
            final String name = super.handleGetName();
            if (StringUtils.isNotBlank(name))
            {
                final UseCaseFacade useCase = getModel().findUseCaseByName(name);
                if (useCase instanceof FrontEndUseCase)
                {
                    targetUseCase = useCase;
                }
            }
        }
        return targetUseCase;
    }
}