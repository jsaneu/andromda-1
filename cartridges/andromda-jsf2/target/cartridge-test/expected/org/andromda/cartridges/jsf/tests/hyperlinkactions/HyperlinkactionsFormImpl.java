// license-header java merge-point
// Generated by andromda-jsf cartridge (forms\FormImpl.java.vsl) on 07/30/2011 09:32:39-0300
package org.andromda.cartridges.jsf.tests.hyperlinkactions;

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
public class HyperlinkactionsFormImpl
    implements Serializable
{
    /**
     * Default constructor
     */
    public HyperlinkactionsFormImpl()
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

    // Action hyperlinkactions-do-something formFields

    private String someParameter;

    /**
     * 
     * @return someParameter 
     */
    public String getSomeParameter()
    {
        return this.someParameter;
    }

    /**
     * Keeps track of whether or not the value of someParameter has
     * be populated at least once.
     */
    private boolean someParameterSet = false;

    /**
     * Resets the value of the someParameterSet to false
     */
    public void resetSomeParameterSet()
    {
        this.someParameterSet = false;
    }

    /**
     * Indicates whether or not the value for someParameter has been set at least
     * once.
     *
     * @return true/false
     */
    public boolean isSomeParameterSet()
    {
        return this.someParameterSet;
    }

    /**
     * 
     * @param someParameterIn 
     */
    public void setSomeParameter(String someParameterIn)
    {
        this.someParameter = someParameterIn;
        this.someParameterSet = true;
    }

    /**
     * Stores the values.
     */
    private Object[] someParameterValueList;

    /**
     * Stores the labels
     */
    private Object[] someParameterLabelList;

    /**
     * @return backingList Object[]
     */
    public Object[] getSomeParameterBackingList()
    {
        Object[] values = this.someParameterValueList;
        Object[] labels = this.someParameterLabelList;

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
     * @return someParameterValueList
     */
    public Object[] getSomeParameterValueList()
    {
        return this.someParameterValueList;
    }

    /**
     * @param someParameterValueListIn
     */
    public void setSomeParameterValueList(Object[] someParameterValueListIn)
    {
        this.someParameterValueList = someParameterValueListIn;
    }

    /**
     * @return someParameterLabelList Object[]
     */
    public Object[] getSomeParameterLabelList()
    {
        return this.someParameterLabelList;
    }

    /**
     * @param someParameterLabelListIn
     */
    public void setSomeParameterLabelList(Object[] someParameterLabelListIn)
    {
        this.someParameterLabelList = someParameterLabelListIn;
    }

    /**
     * @param items
     * @param valueProperty
     * @param labelProperty
     */
    public void setSomeParameterBackingList(Collection<? extends Object> items, String valueProperty, String labelProperty)
    {
        this.someParameterValueList = null;
        this.someParameterLabelList = null;
        if (items != null)
        {
            this.someParameterValueList = new Object[items.size()];
            this.someParameterLabelList = new Object[items.size()];

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
                        this.someParameterValueList[ctr] = valueProperty == null ? item :
                            PropertyUtils.getProperty(item, valueProperty.trim());
                        if (labelProperties == null)
                        {
                            this.someParameterLabelList[ctr] = item;
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
                            this.someParameterLabelList[ctr] = labelText.toString().replaceAll("\\s+", " ").trim();
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


    private String parameterWithDefaultValue = "someValue";

    /**
     * 
     * @return parameterWithDefaultValue 
     */
    public String getParameterWithDefaultValue()
    {
        return this.parameterWithDefaultValue;
    }

    /**
     * Keeps track of whether or not the value of parameterWithDefaultValue has
     * be populated at least once.
     */
    private boolean parameterWithDefaultValueSet = false;

    /**
     * Resets the value of the parameterWithDefaultValueSet to false
     */
    public void resetParameterWithDefaultValueSet()
    {
        this.parameterWithDefaultValueSet = false;
    }

    /**
     * Indicates whether or not the value for parameterWithDefaultValue has been set at least
     * once.
     *
     * @return true/false
     */
    public boolean isParameterWithDefaultValueSet()
    {
        return this.parameterWithDefaultValueSet;
    }

    /**
     * 
     * @param parameterWithDefaultValueIn 
     */
    public void setParameterWithDefaultValue(String parameterWithDefaultValueIn)
    {
        this.parameterWithDefaultValue = parameterWithDefaultValueIn;
        this.parameterWithDefaultValueSet = true;
    }

    /**
     * Stores the values.
     */
    private Object[] parameterWithDefaultValueValueList;

    /**
     * Stores the labels
     */
    private Object[] parameterWithDefaultValueLabelList;

    /**
     * @return backingList Object[]
     */
    public Object[] getParameterWithDefaultValueBackingList()
    {
        Object[] values = this.parameterWithDefaultValueValueList;
        Object[] labels = this.parameterWithDefaultValueLabelList;

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
     * @return parameterWithDefaultValueValueList
     */
    public Object[] getParameterWithDefaultValueValueList()
    {
        return this.parameterWithDefaultValueValueList;
    }

    /**
     * @param parameterWithDefaultValueValueListIn
     */
    public void setParameterWithDefaultValueValueList(Object[] parameterWithDefaultValueValueListIn)
    {
        this.parameterWithDefaultValueValueList = parameterWithDefaultValueValueListIn;
    }

    /**
     * @return parameterWithDefaultValueLabelList Object[]
     */
    public Object[] getParameterWithDefaultValueLabelList()
    {
        return this.parameterWithDefaultValueLabelList;
    }

    /**
     * @param parameterWithDefaultValueLabelListIn
     */
    public void setParameterWithDefaultValueLabelList(Object[] parameterWithDefaultValueLabelListIn)
    {
        this.parameterWithDefaultValueLabelList = parameterWithDefaultValueLabelListIn;
    }

    /**
     * @param items
     * @param valueProperty
     * @param labelProperty
     */
    public void setParameterWithDefaultValueBackingList(Collection<? extends Object> items, String valueProperty, String labelProperty)
    {
        this.parameterWithDefaultValueValueList = null;
        this.parameterWithDefaultValueLabelList = null;
        if (items != null)
        {
            this.parameterWithDefaultValueValueList = new Object[items.size()];
            this.parameterWithDefaultValueLabelList = new Object[items.size()];

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
                        this.parameterWithDefaultValueValueList[ctr] = valueProperty == null ? item :
                            PropertyUtils.getProperty(item, valueProperty.trim());
                        if (labelProperties == null)
                        {
                            this.parameterWithDefaultValueLabelList[ctr] = item;
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
                            this.parameterWithDefaultValueLabelList[ctr] = labelText.toString().replaceAll("\\s+", " ").trim();
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
         this.resetSomeParameterSet();
         this.resetParameterWithDefaultValueSet();
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
    private static final long serialVersionUID = 1680437251588242758L;
}