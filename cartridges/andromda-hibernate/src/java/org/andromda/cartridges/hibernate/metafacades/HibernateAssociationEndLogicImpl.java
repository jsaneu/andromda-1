package org.andromda.cartridges.hibernate.metafacades;

import java.util.ArrayList;
import java.util.Collection;

import org.andromda.cartridges.hibernate.HibernateProfile;
import org.andromda.cartridges.hibernate.HibernateUtils;
import org.andromda.metafacades.uml.ClassifierFacade;
import org.andromda.metafacades.uml.EntityAssociationEnd;
import org.andromda.metafacades.uml.EntityMetafacadeUtils;
import org.andromda.metafacades.uml.ModelElementFacade;
import org.andromda.metafacades.uml.NameMasker;
import org.andromda.metafacades.uml.TypeMappings;
import org.andromda.metafacades.uml.UMLMetafacadeProperties;
import org.andromda.metafacades.uml.UMLProfile;
import org.andromda.utils.JavaTypeConverter;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;


/**
 * MetafacadeLogic implementation for
 * org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd.
 *
 * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd
 */
public class HibernateAssociationEndLogicImpl
    extends HibernateAssociationEndLogic
{
    /**
     * @param metaObject
     * @param context
     */
    public HibernateAssociationEndLogicImpl(
        Object metaObject,
        String context)
    {
        super(metaObject, context);
    }

    /**
     * Value for set
     */
    private static final String COLLECTION_TYPE_SET = "set";

    /**
     * Value for map
     */
    private static final String COLLECTION_TYPE_MAP = "map";

    /**
     * Value for bags
     */
    private static final String COLLECTION_TYPE_BAG = "bag";

    /**
     * Value for list
     */
    private static final String COLLECTION_TYPE_LIST = "list";

    /**
     * Value for collections
     */
    private static final String COLLECTION_TYPE_COLLECTION = "collection";

    /**
     * Stores the valid collection types
     */
    private static final Collection collectionTypes = new ArrayList();

    static
    {
        collectionTypes.add(COLLECTION_TYPE_SET);
        collectionTypes.add(COLLECTION_TYPE_MAP);
        collectionTypes.add(COLLECTION_TYPE_BAG);
        collectionTypes.add(COLLECTION_TYPE_LIST);
        collectionTypes.add(COLLECTION_TYPE_COLLECTION);
    }

    /**
     * Stores the property indicating whether or not composition should define
     * the eager loading strategy.
     */
    private static final String COMPOSITION_DEFINES_EAGER_LOADING = "compositionDefinesEagerLoading";

    /**
     * Stores the default outerjoin setting for this association end.
     */
    private static final String PROPERTY_ASSOCIATION_END_OUTERJOIN = "hibernateAssociationEndOuterJoin";

    /**
     * Stores the default collection index name.
     */
    private static final String COLLECTION_INDEX_NAME = "associationEndCollectionIndexName";

    /**
     * Stores the default collection index type.
     */
    private static final String COLLECTION_INDEX_TYPE = "associationEndCollectionIndexType";

    /**
     * Stores the value of the cascade behavior when modeling an aggregation.
     */
    private static final String HIBERNATE_AGGREGATION_CASCADE = "hibernateAggregationCascade";

    /**
     * Stores the value of the cascade behavior when modeling a composition.
     */
    private static final String HIBERNATE_COMPOSITION_CASCADE = "hibernateCompositionCascade";

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isOne2OnePrimary()
     */
    protected boolean handleIsOne2OnePrimary()
    {
        boolean primary  = !BooleanUtils.toBoolean(
            ObjectUtils.toString(this.getOtherEnd().findTaggedValue(
                HibernateProfile.TAGGEDVALUE_PERSISTENCE_ASSOCIATION_END_PRIMARY)));
        if (primary)
        {
            primary = (this.isOne2One() && (this.isAggregation() || this.isComposition()) ||
                BooleanUtils.toBoolean(ObjectUtils.toString(
                    this.findTaggedValue(HibernateProfile.TAGGEDVALUE_PERSISTENCE_ASSOCIATION_END_PRIMARY))));
        }
        return primary;
    }

    /**
     * @see org.andromda.metafacades.uml.AssociationEndFacade#getGetterSetterTypeName()
     */
    public String getGetterSetterTypeName()
    {
        String getterSetterTypeName = null;

        if (this.isMany())
        {
            final boolean specificInterfaces =
                Boolean.valueOf(
                    ObjectUtils.toString(this.getConfiguredProperty(HibernateGlobals.SPECIFIC_COLLECTION_INTERFACES)))
                       .booleanValue();

            final TypeMappings mappings = this.getLanguageMappings();
            if (mappings != null)
            {
                if (this.isMap())
                {
                    getterSetterTypeName = mappings.getTo(UMLProfile.MAP_TYPE_NAME);
                }
                else if (specificInterfaces)
                {
                    if (this.isSet())
                    {
                        getterSetterTypeName = mappings.getTo(UMLProfile.SET_TYPE_NAME);
                    }
                    else if (this.isList())
                    {
                        getterSetterTypeName = mappings.getTo(UMLProfile.LIST_TYPE_NAME);
                    }
                }
                else
                {
                    getterSetterTypeName =
                        ObjectUtils.toString(this.getConfiguredProperty(HibernateGlobals.DEFAULT_COLLECTION_INTERFACE));
                }
            }
            else
            {
                getterSetterTypeName =
                    ObjectUtils.toString(this.getConfiguredProperty(HibernateGlobals.DEFAULT_COLLECTION_INTERFACE));
            }
        }
        else
        {
            final ClassifierFacade type = this.getType();

            if (type instanceof HibernateEntity)
            {
                final String typeName = ((HibernateEntity)type).getFullyQualifiedEntityName();

                if (StringUtils.isNotEmpty(typeName))
                {
                    getterSetterTypeName = typeName;
                }
            }
        }

        if (StringUtils.isBlank(getterSetterTypeName))
        {
            getterSetterTypeName = super.getGetterSetterTypeName();
        }
        else if (this.isMany())
        {
            // set this association end's type as a template parameter if required
            if (Boolean.valueOf(String.valueOf(this.getConfiguredProperty(UMLMetafacadeProperties.ENABLE_TEMPLATING)))
                       .booleanValue())
            {
                final StringBuilder lBuffer = new StringBuilder();
                lBuffer.append(getterSetterTypeName);
                lBuffer.append("<");
                if (this.isMap())
                {
                    lBuffer.append(this.getCollectionIndexType());                    
                    lBuffer.append(", ");
                }
                lBuffer.append(this.getType().getFullyQualifiedName());
                lBuffer.append(">");
                getterSetterTypeName = lBuffer.toString();            
            }
        }

        return getterSetterTypeName;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isLazy()
     */
    protected boolean handleIsLazy()
    {
        String lazyString = (String)findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_LAZY);
        boolean lazy = true;

        if (StringUtils.isBlank(lazyString))
        {
            // check whether or not composition defines eager loading is turned
            // on
            boolean compositionDefinesEagerLoading =
                Boolean.valueOf(String.valueOf(this.getConfiguredProperty(COMPOSITION_DEFINES_EAGER_LOADING)))
                       .booleanValue();

            if (compositionDefinesEagerLoading)
            {
                lazy = !this.getOtherEnd().isComposition();
            }
        }
        else
        {
            lazy = Boolean.valueOf(lazyString).booleanValue();
        }

        return lazy;
    }

    /**
     * calculates the hibernate cascade attribute of this association end.
     *
     * @return null if no relevant cascade attribute to deliver
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getHibernateCascade()
     */
    protected String handleGetHibernateCascade()
    {
        String cascade = null;
        final String individualCascade = (String)findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_CASCADE);

        if ((individualCascade != null) && (individualCascade.length() > 0))
        {
            cascade = individualCascade;
        }
        else if (this.isChild()) // other end is a composition
        {
            if (StringUtils.isBlank(this.getHibernateCompositionCascade()))
            {
                cascade = HibernateGlobals.HIBERNATE_CASCADE_DELETE;

                final Object type = this.getType();

                if (type != null && type instanceof HibernateEntity)
                {
                    HibernateEntity entity = (HibernateEntity)type;
                    final String defaultCascade = entity.getHibernateDefaultCascade();

                    if (defaultCascade.equalsIgnoreCase(HibernateGlobals.HIBERNATE_CASCADE_SAVE_UPDATE) ||
                        defaultCascade.equalsIgnoreCase(HibernateGlobals.HIBERNATE_CASCADE_ALL))
                    {
                        if (this.isMany())
                        {
                            cascade = HibernateGlobals.HIBERNATE_CASCADE_ALL_DELETE_ORPHAN;
                        }
                        else
                        {
                            cascade = HibernateGlobals.HIBERNATE_CASCADE_ALL;
                        }
                    }
                }
            }
            else
            {
                cascade = this.getHibernateCompositionCascade();
            }
        }
        else if (this.isComposition())
        {
            // on the composition side, always enforce "none", overriding a
            // default-cascade value
            cascade = HibernateGlobals.HIBERNATE_CASCADE_NONE;
        }
        else if (StringUtils.isNotBlank(this.getHibernateAggregationCascade()))
        {
            // on the aggregation side, always enforce "none", overriding a
            // default-cascade value
            if (this.isAggregation())
            {
                cascade = HibernateGlobals.HIBERNATE_CASCADE_NONE;
            }
            else if (this.getOtherEnd() != null && this.getOtherEnd().isAggregation())
            {
                cascade = this.getHibernateAggregationCascade();
            }
        }
        return cascade;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isHibernateInverse()
     */
    protected boolean handleIsHibernateInverse()
    {
        // inverse can only be true if the relation is bidirectional
        boolean inverse = this.isNavigable() && this.getOtherEnd().isNavigable();

        if (inverse)
        {
            inverse = this.isMany2One();

            // for many-to-many we just put the flag on the side that
            // is aggregation or composition and on the lexically longer
            // fully qualified name for it's type on other types of relations
            if (this.isMany2Many() && !inverse)
            {
                if (this.isAggregation() || this.isComposition())
                {
                    inverse = false;
                }
                else if (this.getOtherEnd().isAggregation() || this.getOtherEnd().isComposition()) {
                    inverse = true;
                }
                else
                {
                    String endTypeName = StringUtils.trimToEmpty(this.getType().getFullyQualifiedName(true));
                    String otherEndTypeName =
                        StringUtils.trimToEmpty(this.getOtherEnd().getType().getFullyQualifiedName(true));
                    int compareTo = endTypeName.compareTo(otherEndTypeName);

                    // if for some reason the fully qualified names are equal,
                    // compare the names.
                    if (compareTo == 0)
                    {
                        String endName = StringUtils.trimToEmpty(this.getName());
                        String otherEndName = StringUtils.trimToEmpty(this.getOtherEnd().getName());
                        compareTo = endName.compareTo(otherEndName);
                    }

                    inverse = compareTo < 0;
                }
                if (inverse && this.isBidirectionalOrderedListChild() && this.isVersion3())
                { // A special case - when using ver 3 of hibernate for a bi-dir
                  // ordered list, "inverse" should be set to FALSE, rather than
                  // the usual TRUE. See http://www.hibernate.org/193.html
                    inverse = false;
                }
            }
        }

        return inverse;
    }

    /**
     * Hibernate 2 outer join option
     */
    private static final String HIBERNATE_OUTER_JOIN_YES = "yes";

    /**
     * Hibernate 2 outer join option
     */
    private static final String HIBERNATE_OUTER_JOIN_AUTO = "auto";

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getOuterJoin()
     */
    protected String handleGetOuterJoin()
    {
        Object value = this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_OUTER_JOIN);

        if (value == null)
        {
            value = this.getConfiguredProperty(PROPERTY_ASSOCIATION_END_OUTERJOIN);
        }
        String outerValue = StringUtils.trimToEmpty(String.valueOf(value));
        String version = (String)this.getConfiguredProperty(HibernateGlobals.HIBERNATE_VERSION);

        if (StringUtils.isBlank(version) || version.equals(HibernateGlobals.HIBERNATE_VERSION_3))
        {
            outerValue =
                (outerValue.equals(HIBERNATE_OUTER_JOIN_AUTO) || outerValue.equals(HIBERNATE_OUTER_JOIN_YES))
                ? "select" : "join";
        }
        return outerValue;
    }

    /**
     * Overridden to provide handling of inheritance.
     *
     * @see org.andromda.metafacades.uml.AssociationEndFacade#isRequired()
     */
    public boolean isRequired()
    {
        boolean required = super.isRequired();
        Object type = this.getOtherEnd().getType();

        if ((type != null) && HibernateEntity.class.isAssignableFrom(type.getClass()))
        {
            HibernateEntity entity = (HibernateEntity)type;

            if (entity.isHibernateInheritanceClass() && (entity.getGeneralization() != null))
            {
                required = false;
            }
        }

        return required;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getCollectionType()
     */
    protected String handleGetCollectionType()
    {
        String collectionType = this.getSpecificCollectionType();

        if (!collectionTypes.contains(collectionType))
        {
            if (this.isOrdered())
            {
                collectionType = COLLECTION_TYPE_LIST;
            }
            else
            {
                collectionType =
                    (String)this.getConfiguredProperty(HibernateGlobals.HIBERNATE_ASSOCIATION_COLLECTION_TYPE);
            }
        }

        return collectionType;
    }

    /**
     * Gets the collection type defined on this association end.
     *
     * @return the specific collection type.
     */
    private String getSpecificCollectionType()
    {
        return ObjectUtils.toString(
            this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_ASSOCIATION_COLLECTION_TYPE));
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getSortType()
     */
    protected String handleGetSortType()
    {
        return ObjectUtils.toString(this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_ASSOCIATION_SORT_TYPE));
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getOrderByColumns()
     */
    protected String handleGetOrderByColumns()
    {
        String orderColumns =
            (String)this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_ASSOCIATION_ORDER_BY_COLUMNS);

        if (StringUtils.isBlank(orderColumns))
        {
            orderColumns = ((EntityAssociationEnd)this.getOtherEnd()).getColumnName();
        }

        return orderColumns;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getWhereClause()
     */
    protected String handleGetWhereClause()
    {
        return (String)this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_ASSOCIATION_WHERE_CLAUSE);
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isIndexedCollection()
     */
    protected boolean handleIsIndexedCollection()
    {
        boolean indexed = false;

        if (this.isOrdered())
        {
            if ((
                    this.getCollectionType().equals(COLLECTION_TYPE_LIST) ||
                    this.getCollectionType().equals(COLLECTION_TYPE_MAP)
                ) && StringUtils.isNotBlank(this.getCollectionIndexName()))
            {
                indexed = true;
            }
        }

        return indexed;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getCollectionIndexName()
     */
    protected String handleGetCollectionIndexName()
    {
        Object value = this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_ASSOCIATION_INDEX);

        if ((value == null) && this.isConfiguredProperty(COLLECTION_INDEX_NAME))
        {
            value = this.getConfiguredProperty(COLLECTION_INDEX_NAME);

            if (StringUtils.isBlank(ObjectUtils.toString(value)))
            {
                value = null;
            }
        }

        if (value != null)
        {
            return ObjectUtils.toString(value);
        }
        final String otherEntityName = ((HibernateEntity)this.getOtherEnd().getType()).getEntityName();
        final Object separator = this.getConfiguredProperty(UMLMetafacadeProperties.SQL_NAME_SEPARATOR);
        return EntityMetafacadeUtils.toSqlName(
            otherEntityName,
            separator) + separator + EntityMetafacadeUtils.toSqlName(
            this.getName(),
            separator) + separator + "IDX";
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getCollectionIndexType()
     */
    protected String handleGetCollectionIndexType()
    {
        Object value = this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_ASSOCIATION_INDEX_TYPE);

        if (value == null)
        {
            value = this.getConfiguredProperty(COLLECTION_INDEX_TYPE);

            if (StringUtils.isBlank(ObjectUtils.toString(value)))
            {
                value = null;
            }
        }

        if (value != null)
        {
            if (value instanceof String)
            {
                ModelElementFacade element = this.getRootPackage().findModelElement((String)value);
                if (element!=null)
                {
                    value = element.getFullyQualifiedName();
                }
                // Otherwise, just use the taggedValue String, and hope things line up with the model.
                // Add java.lang. if needed...
                value = new JavaTypeConverter().getJavaLangTypeName((String)value);
            }
            if (value instanceof HibernateType)
            {
                value = ((HibernateType)value).getFullyQualifiedHibernateType();
            }
        }

        return (value != null) ? ObjectUtils.toString(value) : null;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isMap()
     */
    protected boolean handleIsMap()
    {
        boolean isMap = this.getCollectionType().equalsIgnoreCase(COLLECTION_TYPE_MAP);

        if (isMap && StringUtils.isBlank(this.getSpecificCollectionType()))
        {
            isMap = !this.isOrdered();
        }

        return isMap;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isList()
     */
    protected boolean handleIsList()
    {
        boolean isList = this.getCollectionType().equalsIgnoreCase(COLLECTION_TYPE_LIST);

        if (!isList && StringUtils.isBlank(this.getSpecificCollectionType()))
        {
            isList = this.isOrdered();
        }

        return isList;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isSet()
     */
    protected boolean handleIsSet()
    {
        boolean isSet = this.getCollectionType().equalsIgnoreCase(COLLECTION_TYPE_SET);

        if (isSet && StringUtils.isBlank(this.getSpecificCollectionType()))
        {
            isSet = !this.isOrdered();
        }

        return isSet;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isBag()
     */
    protected boolean handleIsBag()
    {
        return this.getCollectionType().equalsIgnoreCase(COLLECTION_TYPE_BAG);
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getCollectionTypeImplementation()
     */
    protected String handleGetCollectionTypeImplementation()
    {
        StringBuffer implementation = new StringBuffer();

        if (this.isMany())
        {
            implementation.append("new ");

            if (this.isSet())
            {
                implementation.append(this.getConfiguredProperty(HibernateGlobals.SET_TYPE_IMPLEMENTATION));
            }
            else if (this.isMap())
            {
                implementation.append(this.getConfiguredProperty(HibernateGlobals.MAP_TYPE_IMPLEMENTATION));
            }
            else if (this.isBag())
            {
                implementation.append(this.getConfiguredProperty(HibernateGlobals.BAG_TYPE_IMPLEMENTATION));
            }
            else if (this.isList())
            {
                implementation.append(this.getConfiguredProperty(HibernateGlobals.LIST_TYPE_IMPLEMENTATION));
            }

            // set this association end's type as a template parameter if required
            if (Boolean.valueOf(String.valueOf(this.getConfiguredProperty(UMLMetafacadeProperties.ENABLE_TEMPLATING)))
                       .booleanValue())
            {
                implementation.append("<");
                if (this.isMap())
                {
                    implementation.append(this.getCollectionIndexType());
                    implementation.append(", ");
                }
                implementation.append(this.getType().getFullyQualifiedName());
                implementation.append(">");
            }

            implementation.append("()");
        }

        return implementation.toString();
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getHibernateAggregationCascade()
     */
    protected String handleGetHibernateAggregationCascade()
    {
        return StringUtils.trimToEmpty(ObjectUtils.toString(this.getConfiguredProperty(HIBERNATE_AGGREGATION_CASCADE)));
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getHibernateCompositionCascade()
     */
    protected String handleGetHibernateCompositionCascade()
    {
        return StringUtils.trimToEmpty(ObjectUtils.toString(this.getConfiguredProperty(HIBERNATE_COMPOSITION_CASCADE)));
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isBidirectionalOrderedListParent()
     */
    protected boolean handleIsBidirectionalOrderedListParent()
    {
        boolean isBidirectionalOrderedListParent = false;
        boolean biDirectional = this.isNavigable() && this.getOtherEnd().isNavigable();

        if (biDirectional && this.isOne2Many() && (this.getOtherEnd() instanceof HibernateAssociationEnd))
        {
            HibernateAssociationEnd otherEnd = (HibernateAssociationEnd)this.getOtherEnd();

            isBidirectionalOrderedListParent =
                otherEnd.getCollectionType().equals(COLLECTION_TYPE_LIST) && otherEnd.isIndexedCollection();
        }

        return isBidirectionalOrderedListParent;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#isBidirectionalOrderedListChild()
     */
    protected boolean handleIsBidirectionalOrderedListChild()
    {
        boolean biDirectional = false;
        if (this.getOtherEnd() instanceof HibernateAssociationEnd)
        {
            HibernateAssociationEnd otherEnd = (HibernateAssociationEnd)this.getOtherEnd();
            biDirectional = otherEnd.isBidirectionalOrderedListParent();
        }
        return biDirectional;
    }

    /**
     * @return HibernateGlobals.HIBERNATE_VERSION
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd
     */
    protected boolean handleIsUsingHibernate3()
    {
        boolean usingHibernate3 = false;
        String property = (String)this.getConfiguredProperty(HibernateGlobals.HIBERNATE_VERSION);
        if (property != null)
        {
            usingHibernate3 = property.equals(HibernateGlobals.HIBERNATE_VERSION_3);
        }
        return usingHibernate3;
    }

    /**
     * @return NameMasker.mask(this.getCollectionIndexName(), NameMasker.UPPERCAMELCASE)
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getCollectionIndexName()
     */
    protected String handleGetCollectionIndexNameGetter()
    {
        return "get" + NameMasker.mask(
            this.getCollectionIndexName(),
            NameMasker.UPPERCAMELCASE);
    }

    /**
     * @return NameMasker.mask(this.getCollectionIndexName(), NameMasker.UPPERCAMELCASE)
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getCollectionIndexName()
     */
    protected String handleGetCollectionIndexNameSetter()
    {
        return "set" + NameMasker.mask(
            this.getCollectionIndexName(),
            NameMasker.UPPERCAMELCASE);
    }

    private boolean isVersion3()
    {
        return HibernateUtils.isVersion3((String)this.getConfiguredProperty(HibernateGlobals.HIBERNATE_VERSION));
    }

    private boolean isXMLPersistenceActive()
    {
        return HibernateUtils.isXmlPersistenceActive(
            (String)this.getConfiguredProperty(HibernateGlobals.HIBERNATE_VERSION),
            (String)this.getConfiguredProperty(HibernateGlobals.HIBERNATE_XML_PERSISTENCE));
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getEmbedXML()
     */
    protected String handleGetEmbedXML()
    {
        String embedVal = null;

        if (isXMLPersistenceActive())
        {
            embedVal = (String)this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_XML_EMBED);

            if (StringUtils.isBlank(embedVal))
            {
                boolean isBiDirectional = this.isNavigable() && this.getOtherEnd().isNavigable();
                if (isBiDirectional && this.isMany())
                {
                    embedVal = "false";
                }
                else
                {
                    embedVal = "true";
                }
            }
        }
        return (StringUtils.isBlank(embedVal)) ? null : embedVal;
    }

    @Override
    /**
     * @see org.andromda.cartridges.hibernate.metafacades.HibernateAssociationEnd#getXmlTagName()
     */
    protected String handleGetXmlTagName()
    {
        String tagName = null;

        if (isXMLPersistenceActive())
        {
            tagName = (String)this.findTaggedValue(HibernateProfile.TAGGEDVALUE_HIBERNATE_XML_TAG_NAME);

            if (StringUtils.isBlank(tagName))
            {
                tagName = this.getName();
            }
        }
        return (StringUtils.isBlank(tagName)) ? null : tagName;
    }
}