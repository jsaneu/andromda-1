package org.andromda.core.common;

import java.io.IOException;
import java.net.URL;

/**
 * A facade that is used to hide the underlying data repository technology.  A data 
 * repository is a facility for loading and retrieving object models.
 *  
 * @author    Anthony Mowers
 */
public interface RepositoryFacade
{

	/**
	 * Reads an object model from a specified URL
     * 
     * The model must conform to the meta model used by the repository
     * 
	 * @param modelURL url of model
	 * @throws MetaDataReadException if model syntax is violated
	 * @throws IOException if io error occurs during file read
	 */
	public void readModel(URL modelURL) throws RepositoryReadException, IOException;

	/**
	 *  Returns the date/time of when the model was last modified
	 *
	 *@return    The lastModified value
	 */
	public long getLastModified();

	/**
	 *  Gets the top-level model object from the repository
	 *
	 *@return    The model value
	 */
	public Object getModel();

}
