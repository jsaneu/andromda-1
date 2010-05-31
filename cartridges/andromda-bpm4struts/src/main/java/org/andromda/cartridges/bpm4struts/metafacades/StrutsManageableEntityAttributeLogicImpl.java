package org.andromda.cartridges.bpm4struts.metafacades;

import org.andromda.cartridges.bpm4struts.Bpm4StrutsGlobals;
import org.andromda.cartridges.bpm4struts.Bpm4StrutsProfile;
import org.andromda.cartridges.bpm4struts.Bpm4StrutsUtils;
import org.andromda.metafacades.uml.ClassifierFacade;
import org.andromda.utils.StringUtilsHelper;


/**
 * MetafacadeLogic implementation for org.andromda.cartridges.bpm4struts.metafacades.StrutsManageableEntityAttribute.
 *
 * @see org.andromda.cartridges.bpm4struts.metafacades.StrutsManageableEntityAttribute
 */
public class StrutsManageableEntityAttributeLogicImpl
    extends StrutsManageableEntityAttributeLogic
{
    /**
     * @param metaObject
     * @param context
     */
    public StrutsManageableEntityAttributeLogicImpl(
        Object metaObject,
        String context)
    {
        super(metaObject, context);
    }

    /**
     * @return messageKey
     * @see StrutsManageableEntityAttribute#getMessageKey()
     */
    protected String handleGetMessageKey()
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
     * @return messageValue
     * @see StrutsManageableEntityAttribute#getMessageValue()
     */
    protected String handleGetMessageValue()
    {
        return StringUtilsHelper.toPhrase(getName());
    }

    private String internalGetDateFormat()
    {
        String dateFormat = null;

        if (this.getType() != null && this.getType().isDateType())
        {
            final Object taggedValueObject = this.findTaggedValue(Bpm4StrutsProfile.TAGGEDVALUE_INPUT_FORMAT);
            if (taggedValueObject == null)
            {
                dateFormat = (String)this.getConfiguredProperty(Bpm4StrutsGlobals.PROPERTY_DEFAULT_DATEFORMAT);
            }
            else
            {
                dateFormat = taggedValueObject.toString();
            }
        }

        return dateFormat;
    }

    protected String handleGetDateFormat()
    {
        String dateFormat = this.internalGetDateFormat();

        if (dateFormat != null)
        {
            final String[] tokens = dateFormat.split("[\\s]+");
            int tokenIndex = 0;
            if (tokenIndex < tokens.length && "strict".equals(tokens[tokenIndex].trim()))
            {
                tokenIndex++;
            }
            if (tokenIndex < tokens.length)
            {
                dateFormat = tokens[tokenIndex].trim();
            }
        }

        return dateFormat;
    }

    protected boolean handleIsStrictDateFormat()
    {
        final String dateFormat = this.internalGetDateFormat();
        return (dateFormat != null && dateFormat.trim().startsWith("strict"));
    }

    protected boolean handleIsNeedsFileUpload()
    {
        return this.getType() != null && this.getType().isBlobType();
    }

    protected boolean handleIsHidden()
    {
        return !this.isDisplay() || Bpm4StrutsProfile.TAGGEDVALUE_INPUT_TYPE_HIDDEN.equals(this.getWidgetType());
    }

    protected String handleGetWidgetType()
    {
        final Object widgetTag = findTaggedValue(Bpm4StrutsProfile.TAGGEDVALUE_INPUT_TYPE);
        return (widgetTag == null) ? Bpm4StrutsProfile.TAGGEDVALUE_INPUT_TYPE_TEXT : widgetTag.toString();
    }

    protected Integer handleGetFieldColumnCount()
    {
        Integer columnCount = null;

        Object columnCountObject = this.findTaggedValue(Bpm4StrutsProfile.TAGGEDVALUE_INPUT_COLUMN_COUNT);
        if (columnCountObject == null)
        {
            columnCountObject = this.getConfiguredProperty(Bpm4StrutsGlobals.PROPERTY_DEFAULT_INPUT_COLUMN_COUNT);
        }

        if (columnCountObject != null)
        {
            try
            {
                columnCount = Integer.valueOf(columnCountObject.toString());
            }
            catch (NumberFormatException ignore)
            {
                // do nothing, we want columnCount to be null in case of an invalid value
            }
        }

        return columnCount;
    }

    protected Integer handleGetFieldRowCount()
    {
        Integer rowCount = null;

        Object rowCountObject = this.findTaggedValue(Bpm4StrutsProfile.TAGGEDVALUE_INPUT_ROW_COUNT);
        if (rowCountObject == null)
        {
            rowCountObject = this.getConfiguredProperty(Bpm4StrutsGlobals.PROPERTY_DEFAULT_INPUT_ROW_COUNT);
        }

        if (rowCountObject != null)
        {
            try
            {
                rowCount = Integer.valueOf(rowCountObject.toString());
            }
            catch (NumberFormatException ignore)
            {
                // do nothing, we want rowCount to be null in case of an invalid value
            }
        }

        return rowCount;
    }

    protected boolean handleIsSafeNamePresent()
    {
        return Bpm4StrutsUtils.isSafeName(this.getName());
    }

    protected String handleGetOnlineHelpKey()
    {
        return this.getMessageKey() + ".online.help";
    }

    protected String handleGetOnlineHelpValue()
    {
        return (!this.isDocumentationPresent()) ? "No field documentation has been specified" : 
            StringUtilsHelper.toResourceMessage(this.getDocumentation("", 64, false));
    }
}