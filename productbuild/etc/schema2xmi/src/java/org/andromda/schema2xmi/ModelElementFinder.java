package org.andromda.schema2xmi;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.omg.uml.foundation.core.ModelElement;
import org.omg.uml.modelmanagement.Model;
import org.omg.uml.modelmanagement.UmlPackage;

/**
 * Finds model elements by their fully qualified names.
 * 
 * @author Chad Brandon
 */
public class ModelElementFinder
{
    /**
     * Finds the model element having the <code>fullyQualifiedName</code>
     * in the <code>model</code>, returns <code>null</code> if not
     * found.
     * 
     * @param model The model to search
     * @param fullyQualifiedName the fully qualified name to find.
     * @return the found model element.
     */
    public static ModelElement find(
        Model model,
        String fullyQualifiedName)
    {
        ModelElement modelElement = null;
        if (model != null) 
        {
            String[] names = fullyQualifiedName.split("\\.");
            if (names != null && names.length > 0) 
            {
                Object element = model;
                for (int ctr = 0; ctr < names.length; ctr++)
                {
                    String name = names[ctr];
                    if (UmlPackage.class.isAssignableFrom(element.getClass()))
                    {
                        element = getElement(
                            ((UmlPackage)element).getOwnedElement(), name);  
                    }
                    modelElement = (ModelElement)element;
                }
            }
        }
        return modelElement;
    }  
    
    /**
     * Finds the model element having the <code>name</code> contained
     * within the <code>elements</code>, returns null if it
     * can't be found.
     * @param elements the collectoin of model elements to search
     * @param name the name of the model element.
     * @return the found model element or null if not found.
     */
    private static Object getElement(Collection elements, final String name)
    {
        return CollectionUtils.find(
            elements,
            new Predicate() 
            {
                public boolean evaluate(Object object)
                {
                    return StringUtils.trimToEmpty(
                        ((ModelElement)object).getName()).equals(name);
                }
            });
    }
}
