package org.andromda.cartridges.bpm4struts.metafacades;

import org.andromda.metafacades.uml.ClassifierFacade;
import org.andromda.core.common.StringUtilsHelper;


/**
 * MetafacadeLogic implementation for org.andromda.cartridges.bpm4struts.metafacades.StrutsManageableEntityAttribute.
 *
 * @see org.andromda.cartridges.bpm4struts.metafacades.StrutsManageableEntityAttribute
 */
public class StrutsManageableEntityAttributeLogicImpl
    extends StrutsManageableEntityAttributeLogic
{
    // ---------------- constructor -------------------------------

    public StrutsManageableEntityAttributeLogicImpl (Object metaObject, String context)
    {
        super (metaObject, context);
    }

    /**
     * @see org.andromda.cartridges.bpm4struts.metafacades.StrutsManageableEntityAttribute#getTitleKey()
     */
    protected java.lang.String handleGetMessageKey()
    {
        String titleKey = "";

        final ClassifierFacade owner = getOwner();
        if (owner != null)
        {
            titleKey += owner.getName() + '.';
        }

        return StringUtilsHelper.toResourceMessageKey(titleKey + getName());
    }

    /**
     * @see org.andromda.cartridges.bpm4struts.metafacades.StrutsManageableEntityAttribute#getTitleValue()
     */
    protected java.lang.String handleGetMessageValue()
    {
        return StringUtilsHelper.toPhrase(getName());
    }
}