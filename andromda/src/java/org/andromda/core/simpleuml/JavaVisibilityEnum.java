package org.andromda.core.simpleuml;

import java.util.List;

import org.omg.uml.foundation.datatypes.VisibilityKind;

/**
 * @author Anthony Mowers
 *
 * 
 */
public class JavaVisibilityEnum implements VisibilityKind
{
    public final static VisibilityKind PRIVATE = new JavaVisibilityEnum("private");
    public final static VisibilityKind PUBLIC = new JavaVisibilityEnum("public");
    public final static VisibilityKind PROTECTED = new JavaVisibilityEnum("protected");
    public final static VisibilityKind PACKAGE = new JavaVisibilityEnum("");
    
	private String visibility;
	
	private JavaVisibilityEnum(String visibility)
	{
		this.visibility = visibility;
	}

	public String toString()
	{
		return visibility;
	}
	
	/**
	 * @see javax.jmi.reflect.RefEnum#refTypeName()
	 */
	public List refTypeName()
	{
		return null;
	}

}
