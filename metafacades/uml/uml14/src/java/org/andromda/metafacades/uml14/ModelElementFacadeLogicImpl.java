package org.andromda.metafacades.uml14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.andromda.core.common.DocumentationAnalyzer;
import org.andromda.core.common.Paragraph;
import org.andromda.core.metafacade.MetafacadeFactory;
import org.andromda.core.metafacade.MetafacadeProperties;
import org.andromda.metafacades.uml.ConstraintFacade;
import org.andromda.metafacades.uml.EnumerationLiteralFacade;
import org.andromda.metafacades.uml.StereotypeFacade;
import org.andromda.metafacades.uml.TaggedValueFacade;
import org.andromda.metafacades.uml.TypeMappings;
import org.andromda.metafacades.uml.UMLMetafacadeProperties;
import org.andromda.metafacades.uml.UMLMetafacadeUtils;
import org.andromda.metafacades.uml.UMLProfile;
import org.andromda.translation.ocl.ExpressionKinds;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.omg.uml.behavioralelements.activitygraphs.ActivityGraph;
import org.omg.uml.foundation.core.Abstraction;
import org.omg.uml.foundation.core.Comment;
import org.omg.uml.foundation.core.Dependency;
import org.omg.uml.foundation.core.ModelElement;
import org.omg.uml.foundation.datatypes.VisibilityKind;
import org.omg.uml.foundation.datatypes.VisibilityKindEnum;

/**
 * Metaclass facade implementation.
 */
public class ModelElementFacadeLogicImpl
        extends ModelElementFacadeLogic
{
    // ---------------- constructor -------------------------------

    public ModelElementFacadeLogicImpl(org.omg.uml.foundation.core.ModelElement metaObject, String context)
    {
        super(metaObject, context);
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getTaggedValues()
     */
    public java.util.Collection handleGetTaggedValues()
    {
        return metaObject.getTaggedValue();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getPackageName()
     */
    public String handleGetPackageName()
    {
        return this.getPackageName(false);
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getPackageName(boolean)
     */
    public String handleGetPackageName(boolean modelName)
    {
        return UML14MetafacadeUtils.getPackageName(this.metaObject,
                String.valueOf(this.getConfiguredProperty(this.getNamespaceScopeProperty(modelName))));
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getFullyQualifiedName(boolean)
     */
    public java.lang.String handleGetFullyQualifiedName(boolean modelName)
    {
        String fullName = StringUtils.trimToEmpty(this.getName());
        final String packageName = this.getPackageName(true);
        final String metafacadeNamespaceScopeOperator = String.valueOf(
                this.getConfiguredProperty(MetafacadeProperties.METAFACADE_NAMESPACE_SCOPE_OPERATOR));
        if (StringUtils.isNotBlank(packageName))
        {
            fullName = packageName + metafacadeNamespaceScopeOperator + fullName;
        }
        if (!modelName)
        {
            if (this.getLanguageMappings() != null)
            {
                fullName = StringUtils.deleteWhitespace(this.getLanguageMappings().getTo(fullName));
                // now replace the metafacade scope operators
                // with the mapped scope operators
                final String namespaceScopeOperator = String.valueOf(
                        this.getConfiguredProperty(UMLMetafacadeProperties.NAMESPACE_SEPARATOR));
                fullName = StringUtils.replace(fullName, metafacadeNamespaceScopeOperator, namespaceScopeOperator);
            }
        }
        return fullName;
    }

    /**
     * Gets the appropriate namespace property for retrieve the namespace scope operation (dependng on the given
     * <code>modelName</code> flag.
     *
     * @param modelName whether or not the scope operation for the model should be retrieved as oppposed to the mapped
     *                  scope operator.
     * @return the scope operator.
     */
    private String getNamespaceScopeProperty(boolean modelName)
    {
        String namespaceScopeProperty = MetafacadeProperties.METAFACADE_NAMESPACE_SCOPE_OPERATOR;
        if (!modelName)
        {
            namespaceScopeProperty = UMLMetafacadeProperties.NAMESPACE_SEPARATOR;
        }
        return namespaceScopeProperty;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getFullyQualifiedName()
     */
    public java.lang.String handleGetFullyQualifiedName()
    {
        return this.getFullyQualifiedName(false);
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#findTaggedValues(java.lang.String)
     */
    public Collection handleFindTaggedValues(String name)
    {
        final Collection values = new ArrayList();
        // only search a tagged value when it actually has a name
        if (StringUtils.isNotBlank(name))
        {
            // trim the name, we don't want leading/trailing spaces
            name = StringUtils.trimToEmpty(name);
            // loop over the tagged values
            Collection taggedValues = this.getTaggedValues();
            for (Iterator taggedValueIterator = taggedValues.iterator(); taggedValueIterator.hasNext();)
            {
                TaggedValueFacade taggedValue = (TaggedValueFacade)taggedValueIterator.next();
                // does this name match the argument tagged value name ?
                if (name.equals(taggedValue.getName()))
                {
                    for (Iterator valueIterator = taggedValue.getValues().iterator(); valueIterator.hasNext();)
                    {
                        // only process tagged values when they actually have a
                        // non-empty value
                        Object value = valueIterator.next();
                        if (value != null)
                        {
                            // if an enumeration literal is referenced we assume
                            // its name
                            if (EnumerationLiteralFacade.class.isAssignableFrom(value.getClass()))
                            {
                                values.add(((EnumerationLiteralFacade)value).getValue());
                            }
                            else if (String.class.isAssignableFrom(value.getClass()))
                            {
                                // only add String when they are not blank
                                String valueString = (String)value;
                                if (StringUtils.isNotBlank(valueString))
                                {
                                    values.add(value);
                                }
                            }
                            else
                            {
                                values.add(value);
                            }
                        }
                    }
                }
            }
        }
        return values;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#findTaggedValue(java.lang.String)
     */
    public Object handleFindTaggedValue(String name)
    {
        Collection taggedValues = findTaggedValues(name);
        return taggedValues.isEmpty() ? null : taggedValues.iterator().next();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#hasStereotype(java.lang.String)
     */
    public boolean handleHasStereotype(String stereotypeName)
    {
        final String stereotype = stereotypeName;
        Collection stereotypes = this.getStereotypes();

        boolean hasStereotype = StringUtils.isNotBlank(stereotypeName) && stereotypes != null &&
                !stereotypes.isEmpty();

        if (hasStereotype)
        {
            class StereotypeFilter
                    implements Predicate
            {
                public boolean evaluate(Object object)
                {
                    boolean valid = false;
                    StereotypeFacade facade = (StereotypeFacade)object;
                    String name = StringUtils.trimToEmpty(facade.getName());
                    valid = stereotype.equals(name);
                    while (!valid && facade != null)
                    {
                        facade = (StereotypeFacade)facade.getGeneralization();
                        valid = facade != null && StringUtils.trimToEmpty(facade.getName()).equals(stereotype);
                    }
                    return valid;
                }
            }
            hasStereotype = CollectionUtils.find(this.getStereotypes(), new StereotypeFilter()) != null;
        }
        return hasStereotype;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getId()
     */
    public String handleGetId()
    {
        return this.metaObject.refMofId();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#hasExactStereotype(java.lang.String)
     */
    public boolean handleHasExactStereotype(String stereotypeName)
    {
        return this.getStereotypeNames().contains(StringUtils.trimToEmpty(stereotypeName));
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getVisibility()
     */
    public String handleGetVisibility()
    {
        String visibility = null;

        final VisibilityKind visibilityKind = metaObject.getVisibility();
        if (visibilityKind == null || VisibilityKindEnum.VK_PRIVATE.equals(visibilityKind))
        {
            visibility = "private";
        }
        else if (VisibilityKindEnum.VK_PROTECTED.equals(visibilityKind))
        {
            visibility = "protected";
        }
        else if (VisibilityKindEnum.VK_PUBLIC.equals(visibilityKind))
        {
            visibility = "public";
        }
        else // VisibilityKindEnum.VK_PACKAGE
        {
            visibility = "package";
        }

        final TypeMappings languageMappings = this.getLanguageMappings();
        if (languageMappings != null)
        {
            visibility = languageMappings.getTo(visibility);
        }

        return visibility;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getStereotypeNames()
     */
    public java.util.Collection handleGetStereotypeNames()
    {
        Collection stereotypeNames = new ArrayList();

        Collection stereotypes = metaObject.getStereotype();
        for (Iterator stereotypeIt = stereotypes.iterator(); stereotypeIt.hasNext();)
        {
            ModelElement stereotype = (ModelElement)stereotypeIt.next();
            stereotypeNames.add(StringUtils.trimToEmpty(stereotype.getName()));
        }
        return stereotypeNames;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getFullyQualifiedNamePath()
     */
    public String handleGetFullyQualifiedNamePath()
    {
        return StringUtils.replace(this.getFullyQualifiedName(), String.valueOf(this.getConfiguredProperty(
                UMLMetafacadeProperties.NAMESPACE_SEPARATOR)), "/");
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getPackagePath()
     */
    public String handleGetPackagePath()
    {
        return StringUtils.replace(this.getPackageName(), String.valueOf(this.getConfiguredProperty(
                UMLMetafacadeProperties.NAMESPACE_SEPARATOR)), "/");
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getDocumentation(java.lang.String)
     */
    protected String handleGetDocumentation(String indent)
    {
        return getDocumentation(indent, 64);
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getDocumentation(java.lang.String, int)
     */
    protected String handleGetDocumentation(String indent, int lineLength)
    {
        return getDocumentation(indent, lineLength, true);
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getDocumentation(java.lang.String, int, boolean)
     */
    protected String handleGetDocumentation(String indent, int lineLength, boolean htmlStyle)
    {
        if (StringUtils.isEmpty(indent))
        {
            indent = "";
        }
        StringBuffer documentation = new StringBuffer();
        Collection comments = this.metaObject.getComment();
        if (comments != null && !comments.isEmpty())
        {
            Iterator commentIt = comments.iterator();
            while (commentIt.hasNext())
            {
                Comment comment = (Comment)commentIt.next();
                String commentString = StringUtils.trimToEmpty(comment.getBody());
                // if there isn't anything in the body, try the name
                if (StringUtils.isEmpty(commentString))
                {
                    commentString = StringUtils.trimToEmpty(comment.getName());
                }
                documentation.append(StringUtils.trimToEmpty(commentString));
            }
        }
        // if there still isn't anything, try a tagged value
        if (StringUtils.isEmpty(documentation.toString()))
        {
            documentation.append(StringUtils.trimToEmpty((String)this.findTaggedValue(
                    UMLProfile.TAGGEDVALUE_DOCUMENTATION)));
        }
        try
        {
            String newLine = "\n";
            String startParaTag = (htmlStyle) ? "<p>" : "";
            String endParaTag = (htmlStyle) ? "</p>" : "";
            Collection paragraphs = new DocumentationAnalyzer(lineLength).toParagraphs(documentation.toString(),
                    htmlStyle);
            if (paragraphs != null && !paragraphs.isEmpty())
            {
                documentation = new StringBuffer();
                for (Iterator paragraphIt = paragraphs.iterator(); paragraphIt.hasNext();)
                {
                    Paragraph paragraph = (Paragraph)paragraphIt.next();
                    documentation.append(indent + startParaTag + newLine);
                    Collection lines = paragraph.getLines();
                    if (lines != null && !lines.isEmpty())
                    {
                        Iterator lineIt = lines.iterator();
                        while (lineIt.hasNext())
                        {
                            documentation.append(indent + lineIt.next() + newLine);
                        }
                    }
                    documentation.append(indent + endParaTag);
                    if (paragraphIt.hasNext())
                    {
                        documentation.append(newLine);
                    }
                }
            }
            else
            {
                documentation.append(indent);
            }
        }
        catch (Throwable th)
        {
            final String errMsg = "Error performing ModelElementFacadeImpl.getDocumentation";
            logger.error(errMsg, th);
        }
        if (lineLength < 0)
        {
            documentation = new StringBuffer(StringUtils.trimToEmpty(documentation.toString()).replaceAll("[$\\s]+",
                    " "));
        }
        return documentation.toString();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getName()
     */
    protected String handleGetName()
    {
        return metaObject.getName();
    }

    /**
     * Gets the array suffix from the configured metafacade properties.
     *
     * @return the array suffix.
     */
    private String getArraySuffix()
    {
        return String.valueOf(this.getConfiguredProperty(UMLMetafacadeProperties.ARRAY_NAME_SUFFIX));
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getLanguageMappings()
     */
    protected TypeMappings handleGetLanguageMappings()
    {
        final String propertyName = UMLMetafacadeProperties.LANGUAGE_MAPPINGS_URI;
        Object property = this.getConfiguredProperty(propertyName);
        TypeMappings mappings = null;
        String uri = null;
        if (String.class.isAssignableFrom(property.getClass()))
        {
            uri = (String)property;
            try
            {
                mappings = TypeMappings.getInstance(uri);
                mappings.setArraySuffix(this.getArraySuffix());
                this.setProperty(propertyName, mappings);
            }
            catch (Throwable th)
            {
                String errMsg = "Error getting '" + propertyName + "' --> '" + uri + "'";
                logger.error(errMsg, th);
                // don't throw the exception
            }
        }
        else
        {
            mappings = (TypeMappings)property;
        }
        return mappings;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getPackage()
     */
    protected Object handleGetPackage()
    {
        return metaObject.getNamespace();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getRootPackage()
     */
    protected Object handleGetRootPackage()
    {
        return UML14MetafacadeUtils.getRootPackage();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getSourceDependencies()
     */
    protected java.util.Collection handleGetSourceDependencies()
    {
        Collection dependencies = new ArrayList();
        Collection clientDependencies = UML14MetafacadeUtils.getCorePackage().getAClientClientDependency()
                .getClientDependency(this.metaObject);
        if (clientDependencies != null)
        {
            dependencies.addAll(clientDependencies);
        }
        CollectionUtils.filter(dependencies, new Predicate()
        {
            public boolean evaluate(Object object)
            {
                return (object instanceof Dependency) && !(object instanceof Abstraction);
            }
        });
        return dependencies;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacadeLogic#getTargetDependencies()
     */
    protected Collection handleGetTargetDependencies()
    {
        Collection dependencies = new ArrayList();
        Collection supplierDependencies = UML14MetafacadeUtils.getCorePackage().getASupplierSupplierDependency()
                .getSupplierDependency(this.metaObject);
        if (supplierDependencies != null)
        {
            dependencies.addAll(supplierDependencies);
        }
        CollectionUtils.filter(dependencies, new Predicate()
        {
            public boolean evaluate(Object object)
            {
                return (object instanceof Dependency) && !(object instanceof Abstraction);
            }
        });
        return dependencies;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getStereotypes()
     */
    protected java.util.Collection handleGetStereotypes()
    {
        return this.metaObject.getStereotype();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getNameSpace()
     */
    protected Object handleGetNameSpace()
    {
        return metaObject.getNamespace();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacadeLogic#getModel()
     */
    protected Object handleGetModel()
    {
        return MetafacadeFactory.getInstance().getModel().getModel();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacadeLogic#getConstraints()
     */
    protected Collection handleGetConstraints()
    {
        return this.metaObject.getConstraint();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getConstraints(java.lang.String)
     */
    protected Collection handleGetConstraints(final String kind)
    {
        final Collection filteredConstraints = CollectionUtils.select(getConstraints(), new Predicate()
        {
            public boolean evaluate(Object object)
            {
                if (object instanceof ConstraintFacade)
                {
                    ConstraintFacade constraint = (ConstraintFacade)object;
                    return ((ExpressionKinds.BODY.equals(kind) && constraint.isBodyExpression()) ||
                            (ExpressionKinds.DEF.equals(kind) && constraint.isDefinition()) ||
                            (ExpressionKinds.INV.equals(kind) && constraint.isInvariant()) ||
                            (ExpressionKinds.PRE.equals(kind) && constraint.isPreCondition()) ||
                            (ExpressionKinds.POST.equals(kind) && constraint.isPostCondition()));
                }
                return false;
            }
        });
        return filteredConstraints;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#translateConstraint(java.lang.String, java.lang.String)
     */
    protected String handleTranslateConstraint(final String name, String translation)
    {
        String translatedExpression = "";
        ConstraintFacade constraint = (ConstraintFacade)CollectionUtils.find(this.getConstraints(), new Predicate()
        {
            public boolean evaluate(Object object)
            {
                ConstraintFacade constraint = (ConstraintFacade)object;
                return StringUtils.trimToEmpty(constraint.getName()).equals(StringUtils.trimToEmpty(name));
            }
        });

        if (constraint != null)
        {
            translatedExpression = constraint.getTranslation(translation);
        }
        return translatedExpression;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#translateConstraints(java.lang.String)
     */
    protected java.lang.String[] handleTranslateConstraints(String translation)
    {
        return this.translateConstraints(this.getConstraints(), translation);
    }

    /**
     * Private helper that translates all the expressions contained in the <code>constraints</code>, and returns an
     * array of the translated expressions.
     *
     * @param constraints the constraints to translate
     * @param translation the translation to transate <code>to</code>.
     * @return String[] the translated expressions, or null if no constraints were found
     */
    private String[] translateConstraints(Collection constraints, String translation)
    {
        String[] translatedExpressions = null;
        if (constraints != null && !constraints.isEmpty())
        {
            translatedExpressions = new String[constraints.size()];
            Iterator constraintIt = constraints.iterator();
            for (int ctr = 0; constraintIt.hasNext(); ctr++)
            {
                ConstraintFacade constraint = (ConstraintFacade)constraintIt.next();
                translatedExpressions[ctr] = constraint.getTranslation(translation);
            }
        }
        return translatedExpressions;
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#translateConstraints(java.lang.String, java.lang.String)
     */
    protected java.lang.String[] handleTranslateConstraints(final String kind, String translation)
    {
        Collection constraints = this.getConstraints();
        CollectionUtils.filter(constraints, new Predicate()
        {
            public boolean evaluate(Object object)
            {
                ConstraintFacade constraint = (ConstraintFacade)object;
                return UMLMetafacadeUtils.isConstraintKind(constraint.getBody(), kind);
            }
        });
        return this.translateConstraints(constraints, translation);
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getActivityGraphContext()
     */
    protected Object handleGetActivityGraphContext()
    {
        ActivityGraph graphContext = null;

        Collection graphs = UML14MetafacadeUtils.getModel().getActivityGraphs().getActivityGraph().refAllOfType();
        for (Iterator graphIterator = graphs.iterator(); graphIterator.hasNext();)
        {
            ActivityGraph graph = (ActivityGraph)graphIterator.next();
            ModelElement contextElement = graph.getContext();
            if (metaObject.equals(contextElement))
            {
                graphContext = graph;
            }
        }

        return graphContext;
    }

    /**
     * @see org.andromda.core.metafacade.MetafacadeBase#getValidationName()
     */
    public String getValidationName()
    {
        StringBuffer validationName = new StringBuffer("");
        Object seperator = this.getConfiguredProperty(MetafacadeProperties.METAFACADE_NAMESPACE_SCOPE_OPERATOR);
        for (ModelElement namespace = metaObject.getNamespace();
             namespace != null; namespace = namespace.getNamespace())
        {
            if (validationName.length() == 0)
            {
                validationName.append(namespace.getName());
            }
            else
            {
                validationName.insert(0, seperator);
                validationName.insert(0, namespace.getName());
            }
        }
        if (validationName.length() > 0)
        {
            validationName.append(seperator);
        }
        if (StringUtils.isNotEmpty(this.getName()))
        {
            validationName.append(this.getName());
        }
        else
        {
            validationName.append(this.getConfiguredProperty(UMLMetafacadeProperties.UNDEFINED_NAME));
        }
        return validationName.toString();
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#isConstraintsPresent()
     */
    protected boolean handleIsConstraintsPresent()
    {
        return this.getConstraints() != null && !this.getConstraints().isEmpty();
    }
}