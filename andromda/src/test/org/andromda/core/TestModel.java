package org.andromda.core;

/**
 * @author tony
 *
 * 
 */
public interface TestModel
{
    public static final String XMI_FILE_NAME = "/TestModel.xmi";
    public static final String CLASSA_NAME = "ClassA";
    public static final String CLASSA_PACKAGE_NAME = "org.andromda";
    public static final String CLASSA_STEREOTYPE_NAME = "EntityBean";
    
    public static final String OPERATIONA_NAME = "operationA";
    
    // Assocation Names
    public static final String ONE2ONE = "one2one";
    public static final String ONE2MANY= "one2many";
    public static final String MANY2MANY = "many2many";
    public static final String MANY2ONE = "many2one";
}
