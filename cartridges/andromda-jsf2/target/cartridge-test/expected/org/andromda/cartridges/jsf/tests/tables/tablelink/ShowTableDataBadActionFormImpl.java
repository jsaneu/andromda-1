// license-header java merge-point
// Generated by andromda-jsf cartridge (forms\FormImpl.java.vsl) on 07/30/2011 09:32:36-0300
package org.andromda.cartridges.jsf.tests.tables.tablelink;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 */
public class ShowTableDataBadActionFormImpl
    implements Serializable
{
    /**
     * Default constructor
     */
    public ShowTableDataBadActionFormImpl()
    {
        // - setup the default java.util.Date.toString() formatter
        final DateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
        dateFormatter.setLenient(true);
        this.dateTimeFormatters.put(null, dateFormatter);
    }

    private transient FacesEvent event;

    /**
     * @param eventIn
     */
    public void setEvent(FacesEvent eventIn)
    {
        this.event = eventIn;
    }

    /**
     * @return ValueChangeEvent
     */
    public ValueChangeEvent getValueChangeEvent()
    {
        return this.event instanceof ValueChangeEvent
            ? (ValueChangeEvent)this.event : null;
    }

    /**
     * @return ActionEvent
     */
    public ActionEvent getActionEvent()
    {
        return this.event instanceof ActionEvent
            ? (ActionEvent)this.event : null;
    }

    // Action table-link-activity-bad-action formFields

    private String thisParameterNameDoesNotExistAsTableColumn;

    /**
     * 
     * @return thisParameterNameDoesNotExistAsTableColumn 
     */
    public String getThisParameterNameDoesNotExistAsTableColumn()
    {
        return this.thisParameterNameDoesNotExistAsTableColumn;
    }

    /**
     * Keeps track of whether or not the value of thisParameterNameDoesNotExistAsTableColumn has
     * be populated at least once.
     */
    private boolean thisParameterNameDoesNotExistAsTableColumnSet = false;

    /**
     * Resets the value of the thisParameterNameDoesNotExistAsTableColumnSet to false
     */
    public void resetThisParameterNameDoesNotExistAsTableColumnSet()
    {
        this.thisParameterNameDoesNotExistAsTableColumnSet = false;
    }

    /**
     * Indicates whether or not the value for thisParameterNameDoesNotExistAsTableColumn has been set at least
     * once.
     *
     * @return true/false
     */
    public boolean isThisParameterNameDoesNotExistAsTableColumnSet()
    {
        return this.thisParameterNameDoesNotExistAsTableColumnSet;
    }

    /**
     * 
     * @param thisParameterNameDoesNotExistAsTableColumnIn 
     */
    public void setThisParameterNameDoesNotExistAsTableColumn(String thisParameterNameDoesNotExistAsTableColumnIn)
    {
        this.thisParameterNameDoesNotExistAsTableColumn = thisParameterNameDoesNotExistAsTableColumnIn;
        this.thisParameterNameDoesNotExistAsTableColumnSet = true;
    }

    /**
     * Stores the values.
     */
    private Object[] thisParameterNameDoesNotExistAsTableColumnValueList;

    /**
     * Stores the labels
     */
    private Object[] thisParameterNameDoesNotExistAsTableColumnLabelList;

    /**
     * @return backingList Object[]
     */
    public Object[] getThisParameterNameDoesNotExistAsTableColumnBackingList()
    {
        Object[] values = this.thisParameterNameDoesNotExistAsTableColumnValueList;
        Object[] labels = this.thisParameterNameDoesNotExistAsTableColumnLabelList;

        if (values == null || values.length == 0)
        {
            return values;
        }

        if (labels == null || labels.length == 0)
        {
            labels = values;
        }

        final int length = Math.min(labels.length, values.length);
        SelectItem[] backingList = new SelectItem[length];

        for (int ctr = 0; ctr < length; ctr++)
        {
            backingList[ctr] = new SelectItem(
                values[ctr] != null ? values[ctr] : "", labels[ctr] != null ? String.valueOf(labels[ctr]) : "");
        }
        return backingList;
    }

    /**
     * @return thisParameterNameDoesNotExistAsTableColumnValueList
     */
    public Object[] getThisParameterNameDoesNotExistAsTableColumnValueList()
    {
        return this.thisParameterNameDoesNotExistAsTableColumnValueList;
    }

    /**
     * @param thisParameterNameDoesNotExistAsTableColumnValueListIn
     */
    public void setThisParameterNameDoesNotExistAsTableColumnValueList(Object[] thisParameterNameDoesNotExistAsTableColumnValueListIn)
    {
        this.thisParameterNameDoesNotExistAsTableColumnValueList = thisParameterNameDoesNotExistAsTableColumnValueListIn;
    }

    /**
     * @return thisParameterNameDoesNotExistAsTableColumnLabelList Object[]
     */
    public Object[] getThisParameterNameDoesNotExistAsTableColumnLabelList()
    {
        return this.thisParameterNameDoesNotExistAsTableColumnLabelList;
    }

    /**
     * @param thisParameterNameDoesNotExistAsTableColumnLabelListIn
     */
    public void setThisParameterNameDoesNotExistAsTableColumnLabelList(Object[] thisParameterNameDoesNotExistAsTableColumnLabelListIn)
    {
        this.thisParameterNameDoesNotExistAsTableColumnLabelList = thisParameterNameDoesNotExistAsTableColumnLabelListIn;
    }

    /**
     * @param items
     * @param valueProperty
     * @param labelProperty
     */
    public void setThisParameterNameDoesNotExistAsTableColumnBackingList(Collection<? extends Object> items, String valueProperty, String labelProperty)
    {
        this.thisParameterNameDoesNotExistAsTableColumnValueList = null;
        this.thisParameterNameDoesNotExistAsTableColumnLabelList = null;
        if (items != null)
        {
            this.thisParameterNameDoesNotExistAsTableColumnValueList = new Object[items.size()];
            this.thisParameterNameDoesNotExistAsTableColumnLabelList = new Object[items.size()];

            try
            {
                final List<String> labelProperties =
                    labelProperty == null ? null : new ArrayList<String>(Arrays.asList(labelProperty.split("[\\W&&[^\\.]]+")));
                final List<String> labelDelimiters =
                    labelProperty == null ? null : new ArrayList<String>(Arrays.asList(labelProperty.split("[\\w\\.]+")));
                int ctr = 0;
                for (final Iterator<? extends Object> iterator = items.iterator(); iterator.hasNext(); ctr++)
                {
                    final Object item = iterator.next();
                    if (item != null)
                    {
                        this.thisParameterNameDoesNotExistAsTableColumnValueList[ctr] = valueProperty == null ? item :
                            PropertyUtils.getProperty(item, valueProperty.trim());
                        if (labelProperties == null)
                        {
                            this.thisParameterNameDoesNotExistAsTableColumnLabelList[ctr] = item;
                        }
                        else
                        {
                            final StringBuilder labelText = new StringBuilder();
                            int ctr2 = 0;
                            do
                            {
                                if (!labelDelimiters.isEmpty())
                                {
                                    labelText.append(labelDelimiters.get(ctr2));
                                }
                                String property = null;
                                if (ctr2 < labelProperties.size())
                                {
                                    property = labelProperties.get(ctr2);
                                }
                                if (property != null && property.length() > 0)
                                {
                                    if (PropertyUtils.isReadable(item, property))
                                    {
                                        Object value = PropertyUtils.getProperty(item, property);
                                        if (value != null)
                                        {
                                            if (value instanceof String)
                                            {
                                                if (((String)value).trim().length() == 0)
                                                {
                                                    value = null;
                                                }
                                            }
                                            if (value != null)
                                            {
                                                labelText.append(value);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        labelText.append(property);
                                    }
                                }
                                ctr2++;
                            }
                            while (ctr2 < labelDelimiters.size());
                            this.thisParameterNameDoesNotExistAsTableColumnLabelList[ctr] = labelText.toString().replaceAll("\\s+", " ").trim();
                        }
                    }
                }
            }
            catch (final Throwable throwable)
            {
                throw new RuntimeException(throwable);
            }
        }
    }


    /**
     * Resets all the "isSet" flags.
     */
     public void resetIsSetFlags()
     {
         this.resetThisParameterNameDoesNotExistAsTableColumnSet();
     }

    /**
     * Stores any date or time formatters for this form.
     */
    private final Map<String, DateFormat> dateTimeFormatters =
        new HashMap<String, DateFormat>();

    /**
     * Gets any date and time formatters (keyed by property name)
     * for this form.
     *
     * @return the Map containing any date and time formatters.
     */
    public Map<String, DateFormat> getDateTimeFormatters()
    {
        return this.dateTimeFormatters;
    }

    /**
     * The current collection of messages stored within this form.
     */
    private transient Map<String, FacesMessage> jsfMessages =
        new LinkedHashMap<String, FacesMessage>();


    /**
     * Adds a {@link FacesMessage} message to the current messages
     * stored within this form.
     *
     * @param jsfMessage the faces message to add.
     */
    public void addJsfMessages(FacesMessage jsfMessage)
    {
        if (this.jsfMessages != null)
        {
            this.jsfMessages.put(jsfMessage.getDetail(), jsfMessage);
        }
    }

    /**
     * Gets the current {@link FacesMessage} message
     * instances stored within this form.
     *
     * @return the current Faces messages.
     */
    public Collection<FacesMessage> getJsfMessages()
    {
        if (this.jsfMessages == null)
        {
            this.jsfMessages = new LinkedHashMap<String, FacesMessage>();
        }
        return this.jsfMessages.values();
    }

    /**
     * Sets the current {@link FacesMessage} message
     * instances stored within this form.
     *
     * @param messages a collection of the current Faces messages.
     */
    public void setJsfMessages(final Collection<FacesMessage> messages)
    {
        if (messages != null)
        {
            for (final FacesMessage jsfMessage: messages)
            {
                this.jsfMessages.put(jsfMessage.getDetail(), jsfMessage);
            }
        }
    }

    /**
     * Clear the current {@link FacesMessage} message
     * instances stored within this form.
     */
    public void clearJsfMessages()
    {
        this.jsfMessages.clear();
    }

    /**
     * The faces message title (used on a view).
     */
    private String jsfMessagesTitle;

    /**
     * The optional faces message title to set (used on a view).  If not set, the default title
     * will be used.
     *
     * @param jsfMessagesTitleIn the title to use for the messages on the view.
     */
    public void setJsfMessagesTitle(final String jsfMessagesTitleIn)
    {
        this.jsfMessagesTitle = jsfMessagesTitleIn;
    }

    /**
     * Gets the faces messages title to use.
     *
     * @return the faces messages title.
     */
    public String getJsfMessagesTitle()
    {
        return this.jsfMessagesTitle;
    }

    /**
     * Gets the maximum severity of the messages stored in this form.
     *
     * @return the maximum severity or null if no messages are present and/or severity isn't set.
     */
    public FacesMessage.Severity getMaximumMessageSeverity()
    {
        FacesMessage.Severity maxSeverity = null;
        for (final FacesMessage message : this.getJsfMessages())
        {
            final FacesMessage.Severity severity = message.getSeverity();
            if (maxSeverity == null || (severity != null && severity.getOrdinal() > maxSeverity.getOrdinal()))
            {
                maxSeverity = severity;
            }
        }
        return maxSeverity;
    }

    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 6946340935181195343L;
}