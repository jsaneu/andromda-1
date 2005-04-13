package org.andromda.metafacades.uml14;

/**
 * Metaclass facade implementation.
 */
public class ActionStateFacadeLogicImpl
        extends ActionStateFacadeLogic
{
    // ---------------- constructor -------------------------------

    public ActionStateFacadeLogicImpl(org.omg.uml.behavioralelements.activitygraphs.ActionState metaObject,
                                      String context)
    {
        super(metaObject, context);
    }

    // ------------- relations ------------------

    protected Object handleGetEntry()
    {
        return metaObject.getEntry();
    }
}
