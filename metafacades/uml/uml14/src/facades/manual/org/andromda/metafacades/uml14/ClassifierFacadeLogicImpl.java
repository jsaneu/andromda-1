package org.andromda.metafacades.uml14;

import java.util.Collection;
import java.util.Iterator;

import org.andromda.core.metafacade.MetafacadeFactory;
import org.andromda.metafacades.uml.AttributeFacade;
import org.andromda.metafacades.uml.ClassifierFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.omg.uml.UmlPackage;
import org.omg.uml.foundation.core.Abstraction;
import org.omg.uml.foundation.core.Attribute;
import org.omg.uml.foundation.core.Operation;


/**
 * 
 *
 * Metaclass facade implementation.
 *
 */
public class ClassifierFacadeLogicImpl
       extends ClassifierFacadeLogic
       implements org.andromda.metafacades.uml.ClassifierFacade
{
    // ---------------- constructor -------------------------------
    
    public ClassifierFacadeLogicImpl (org.omg.uml.foundation.core.Classifier metaObject, String context)
    {
        super (metaObject, context);
    }

    // -------------------- business methods ----------------------

    // concrete business methods that were declared
    // abstract in class ClassifierFacade ...

    // ------------- relations ------------------

    /**
     *
     */
    public java.util.Collection handleGetOperations()
    {
        return new FilteredCollection(metaObject.getFeature())
        {
            protected boolean accept(Object object)
            {
                return object instanceof Operation;
            }
        };
    }

    // ------------------------------------------------------------

    /**
     *
     */
    public java.util.Collection handleGetAttributes()
    {
        return new FilteredCollection(metaObject.getFeature())
        {
            protected boolean accept(Object object)
            {
                return (object instanceof Attribute);
            }
        };
    }
    
    /**
     * 
     */
    public java.util.Collection handleGetAssociationEnds()
    {
        return ((UmlPackage)MetafacadeFactory.getInstance().getModel().getModel())
            .getCore()
            .getAParticipantAssociation()
            .getAssociation(metaObject);
    }
    
    /**
     * @see org.andromda.metafacades.uml.ClassifierFacade#isPrimitiveType()
     */
    public boolean isPrimitiveType()
    {
        String name = getName();
        return (
            "void".equals(name)
                || "char".equals(name)
                || "byte".equals(name)
                || "short".equals(name)
                || "int".equals(name)
                || "long".equals(name)
                || "float".equals(name)
                || "double".equals(name)
                || "boolean".equals(name));
    }
    
    /**
     * @see org.andromda.metafacades.uml.ClassifierFacade#getAttributes(boolean)
     */
    public Collection getAttributes(boolean follow) {
        Collection attributes = this.getAttributes();
        for (ClassifierFacade superClass = (ClassifierFacade) getGeneralization();
	    	superClass != null && follow;
	    	superClass = (ClassifierFacade) superClass.getGeneralization()) {
            attributes.addAll(superClass.getAttributes(follow));
        }   
        return attributes;
    }

    /**
     * @see org.andromda.metafacades.uml.ClassifierFacade#getAttributesAsList(boolean)
     */
    public String getAttributesAsList(boolean withTypeNames)
    {
        StringBuffer sb = new StringBuffer();
        String separator = "";
        sb.append("(");

        for (Iterator it = getAttributes().iterator(); it.hasNext();)
        {
            AttributeFacade a = (AttributeFacade)it.next();

            sb.append(separator);
            if (withTypeNames)
            {
                String typeName = a.getType().getFullyQualifiedName();
                sb.append(typeName);
                sb.append(" ");
                sb.append(a.getName());
            }
            else
            {
                sb.append(a.getGetterName());
                sb.append("()");
            }
            separator = ", ";
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * @see org.andromda.metafacades.uml.ClassifierFacade#isAbstract()
     */
    public boolean isAbstract() {
        return this.metaObject.isAbstract();
    }

    /**
     * @see org.andromda.metafacades.uml.ClassifierFacade#getStaticAttributes()
     */
    public Collection getStaticAttributes() {
        Collection attributes = this.getAttributes();
        class StaticAttributeFilter implements Predicate {
            public boolean evaluate(Object object) {
                return ((AttributeFacade)object).isStatic();
            }
        }
        CollectionUtils.filter(attributes, new StaticAttributeFilter());
        return attributes;
    }
    
    /**
     * @see org.andromda.metafacades.uml.ClassifierFacade#getInstanceAttributes()
     */
    public java.util.Collection getInstanceAttributes() {
        Collection attributes = this.getAttributes();
        class StaticAttributeFilter implements Predicate {
            public boolean evaluate(Object object) {
                return !((AttributeFacade)object).isStatic();
            }
        }
        CollectionUtils.filter(attributes, new StaticAttributeFilter());
        return attributes;      
    }
    
    /**
     * @see org.andromda.metafacades.uml.ClassifierFacade#getAbstractions()
     */
    public Collection getAbstractions() {
        Collection clientDependencies =
            this.getDependencies();
            
        class AbstractionFilter implements Predicate {
            public boolean evaluate(Object object) {
                return object instanceof Abstraction;
            }
        }
        
        CollectionUtils.filter(clientDependencies, new AbstractionFilter());
        return clientDependencies;
    }

}
