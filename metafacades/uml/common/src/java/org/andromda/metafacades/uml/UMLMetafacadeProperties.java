package org.andromda.metafacades.uml;

/**
 * Stores the metafacade namespace properties used throughout the UML
 * metafacades.
 * 
 * @author Chad Brandon
 */
public class UMLMetafacadeProperties
{
    /**
     * Stores the property containing the URI to the language mappings file.
     */
    public static final String LANGUAGE_MAPPINGS_URI = "languageMappingsUri";

    /**
     * Stores the property defining the prefix for entity table names.
     */
    public static final String TABLE_NAME_PREFIX = "tableNamePrefix";

    /**
     * Stores the property containing the URI to the SQL mappings file.
     */
    public static final String SQL_MAPPINGS_URI = "sqlMappingsUri";

    /**
     * Stores the property containing the URI to the JDBC mappings file.
     */
    public static final String JDBC_MAPPINGS_URI = "jdbcMappingsUri";

    /**
     * Stores the property containing the URI to the Wrapper mappings file.
     */
    public static final String WRAPPER_MAPPINGS_URI = "wrapperMappingsUri";

    /**
     * Allows the pluralization of association end names (when the multiplicity
     * is greater than one) to be turned on/off.
     */
    public static final String PLURALIZE_ASSOCIATION_END_NAMES = "pluralizeAssociationEndNames";

    /**
     * The default suffix to use for foreign keys.
     */
    public static final String FOREIGN_KEY_SUFFIX = "foreignKeySuffix";

    /**
     * The default suffix to use for foreign keys.
     */
    public static final String CONSTRAINT_SUFFIX = "constraintSuffix";

    /**
     * The mask to apply to role names {@see NameMasker#mask(String, String)}.
     */
    public static final String ROLE_NAME_MASK = "roleNameMask";
    
    /**
     * The mask to apply to any classifier names {@see NameMasker#mask(String, String)}.
     */
    public static final String CLASSIFIER_NAME_MASK = "classifierNameMask";
    
    /**
     * The mask to apply to any of the classifier property names {@see NameMasker#mask(String, String)}.
     */
    public static final String CLASSIFIER_PROPERTY_NAME_MASK = "classifierPropertyNameMask";
    
    /**
     * The mask to apply to any operation names {@see NameMasker#mask(String, String)}.
     */
    public static final String OPERATION_NAME_MASK = "operationNameMask";
    
    /**
     * The mask to apply to any parameter names {@see NameMasker#mask(String, String)}.
     */
    public static final String PARAMETER_NAME_MASK = "parameterNameMask";
    
    /**
     * The mask to apply to enumeration literal names {@see NameMasker#mask(String, String)}.
     */
    public static final String ENUMERATION_LITERAL_NAME_MASK = "enumerationLiteralNameMask";
    
    /**
     * The mask to apply to the enumeration literal name {@see NameMasker#mask(String, String)}.
     */
    public static final String ENUMERATION_NAME_MASK = "enumerationNameMask";
    
    /**
     * The mask to apply to the entity name {@see NameMasker#mask(String, String)}.
     */
    public static final String ENTITY_NAME_MASK = "entityNameMask";
    
    /**
     * The mask to apply to the entity property (attributes and association) 
     * names {@see MetafacadeUtils#getMaskedName(String, String)}.
     */
    public static final String ENTITY_PROPERTY_NAME_MASK = "entityPropertyNameMask";    

    /**
     * The maximum length a SQL name can be before its truncated.
     */
    public static final String MAX_SQL_NAME_LENGTH = "maxSqlNameLength";

    /**
     * Whether or not to allow default identifiers for modeled entities.
     */
    public static final String ALLOW_DEFAULT_IDENTITIFIERS = "allowDefaultIdentifiers";

    /**
     * The name given to default identifiers (if enabled).
     */
    public static final String DEFAULT_IDENTIFIER = "defaultIdentifier";

    /**
     * The type to given default identifiers.
     */
    public static final String DEFAULT_IDENTIFIER_TYPE = "defaultIdentifierType";

    /**
     * The visibility to apply to default identifiers.
     */
    public static final String DEFAULT_IDENTIFIER_VISIBILITY = "defaultIdentifierVisibility";
    
    /**
     * The separator used for packages and element names when constructing
     * fully qualified names.
     */
    public static final String NAMESPACE_SEPARATOR = "namespaceSeparator";
    
    /**
     * The name given to model elements without a name.
     */
    public static final String UNDEFINED_NAME = "undefinedName";
    
    /**
     * Stores the suffix given to array type names.
     */
    public static final String ARRAY_NAME_SUFFIX = "arrayNameSuffix";
    
    /**
     * Stores the boolean flag indicating whether or not we should use arrays
     * instead of collections with associations of type many (where appropriate).
     */
    public static final String USE_ARRAYS_FOR_MULTIPLICITIES_OF_TYPE_MANY = "useArraysForMultiplicitiesOfTypeMany";

    /**
     * The seperator for relation names between associations.
     */
    public static final String RELATION_NAME_SEPARATOR = "relationNameSeparator";
    
    /**
     * The seperator to use when constructing SQL names.
     */
    public static final String SQL_NAME_SEPARATOR = "sqlNameSeparator";
    
    /**
     * The pattern used for constructing operations that handle precondition constraints.
     */
    public static final String PRECONDITION_NAME_PATTERN = "preconditionNamePattern";
    
    /**
     * The pattern used for constructing operations that handle postcondition constraints.
     */
    public static final String POSTCONDITION_NAME_PATTERN = "postconditionNamePattern";
}