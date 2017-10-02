package com.vassarlabs.common.service.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.vassarlabs.common.pojo.api.IPropertyFileDetails;

/**
 * 
 * Reads the properties file given a resource name and maintains a map, to
 * identify them.
 * 
 * @author vaibhav
 *
 */
public interface IPropertyReaderService {
	
	/**
	 * Returns PropertyValue for given PropertyName
	 * 
	 * @param propertyFileDetails
	 * @param propName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getProperty(IPropertyFileDetails propertyFileDetails, String propName) throws FileNotFoundException, IOException;

	/**
	 * Returns PropertyValue for given PropertyName, returns defValue if not found
	 *  
	 * @param propertyFileDetails
	 * @param propName
	 * @param defaultValue
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getProperty(IPropertyFileDetails propertyFileDetails, String propName, String defaultValue) throws FileNotFoundException, IOException ;
	
	/**
	 * Returns PropertyValue for given PropertyName, returns defValue if not found
	 * 
	 * @param propertyFileDetails
	 * @param propName
	 * @param defaultValue
	 * @return
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public int getProperty(IPropertyFileDetails propertyFileDetails, String propName, int defaultValue) throws NumberFormatException, FileNotFoundException, IOException;

	/**
	 * Returns PropertyValue for given PropertyName, returns defValue if not found
	 * 
	 * @param propertyFileDetails
	 * @param propName
	 * @param defaultValue
	 * @return
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public long getProperty(IPropertyFileDetails propertyFileDetails, String propName, long defaultValue) throws NumberFormatException, FileNotFoundException, IOException;

	/**
	 * Returns PropertyValue for given PropertyName, returns defValue if not found
	 * 
	 * @param propertyFileDetails
	 * @param propName
	 * @param defaultValue
	 * @return
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public double getProperty(IPropertyFileDetails propertyFileDetails, String propName, double defaultValue) throws NumberFormatException, FileNotFoundException, IOException;

	/**
	 * Returns PropertyValue for given PropertyName, returns defValue if not found
	 * 
	 * @param propertyFileDetails
	 * @param propName
	 * @param defaultValue
	 * @return
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public boolean getProperty(IPropertyFileDetails propertyFileDetails, String propName, boolean defaultValue) throws NumberFormatException, FileNotFoundException, IOException;

	/**
	 * Returns {@link IPropertyFileDetails} for given filePath
	 * 
	 * @param propertyFileDetails
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public IPropertyFileDetails fetchProperties(IPropertyFileDetails propertyFileDetails) throws FileNotFoundException, IOException;

	/**
	 * Returns {@link IPropertyFileDetails} for given filePath
	 * 
	 * @param propertyFileDetails
	 * @param overrideExisting
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public IPropertyFileDetails fetchProperties(IPropertyFileDetails propertyFileDetails, boolean overrideExisting) throws FileNotFoundException, IOException;
	
	/**
	 * Returns {@link IPropertyFileDetails} for given filePath
	 * 
	 * @param propertyFileDetails
	 * @param overrideExisting
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public IPropertyFileDetails loadProperties(IPropertyFileDetails propertyFileDetails, boolean overrideExisting) throws FileNotFoundException, IOException;
	
}
