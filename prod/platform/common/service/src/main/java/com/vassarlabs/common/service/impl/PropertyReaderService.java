package com.vassarlabs.common.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.vassarlabs.common.pojo.api.IPropertyFileDetails;
import com.vassarlabs.common.service.api.IPropertyReaderService;

@Component
public class PropertyReaderService implements IPropertyReaderService{

	private static Map<String, IPropertyFileDetails> propFilePathToProperties = new HashMap<String, IPropertyFileDetails>();
	
	@Override
	public String getProperty(IPropertyFileDetails propertyFileDetails, String propName) throws FileNotFoundException, IOException {

		if (propFilePathToProperties.get(propertyFileDetails.getFilePath()) == null || propFilePathToProperties.get(propertyFileDetails.getFilePath()).getProperties() == null) {
			fetchProperties(propertyFileDetails, false);
		}
		return propFilePathToProperties.get(propertyFileDetails.getFilePath()).getProperties().getProperty(propName);
	}

	@Override
	public String getProperty(IPropertyFileDetails propertyFileDetails, String propName, String defaultValue) throws IOException {

		String propValueStr = getProperty(propertyFileDetails, propName);

		if (propValueStr == null || propValueStr.trim().length() == 0) {
			return defaultValue;
		}

		return propValueStr;
	}

	@Override
	public int getProperty(IPropertyFileDetails propertyFileDetails, String propName, int defaultValue) throws NumberFormatException, IOException {

		String propValueStr = getProperty(propertyFileDetails, propName);

		if (propValueStr == null || propValueStr.trim().length() == 0) {
			return defaultValue;
		}

		return Integer.parseInt(propValueStr);
	}

	@Override
	public long getProperty(IPropertyFileDetails propertyFileDetails, String propName, long defaultValue) throws NumberFormatException, IOException {

		String propValueStr = getProperty(propertyFileDetails, propName);

		if (propValueStr == null || propValueStr.trim().length() == 0) {
			return defaultValue;
		}

		return Long.parseLong(propValueStr);
	}
	
	@Override
	public double getProperty(IPropertyFileDetails propertyFileDetails, String propName, double defaultValue) throws NumberFormatException, IOException {

		String propValueStr = getProperty(propertyFileDetails, propName);

		if (propValueStr == null || propValueStr.trim().length() == 0) {
			return defaultValue;
		}

		return Double.parseDouble(propValueStr);
	}
	
	@Override
	public boolean getProperty(IPropertyFileDetails propertyFileDetails, String propName, boolean defaultValue) throws IOException {

		String propValueStr = getProperty(propertyFileDetails, propName);

		if (propValueStr == null || propValueStr.trim().length() == 0) {
			return defaultValue;
		}

		return Boolean.parseBoolean(propValueStr);
	}
	
	@Override
	public IPropertyFileDetails fetchProperties(IPropertyFileDetails propertyFileDetails) throws FileNotFoundException, IOException {
		if (propFilePathToProperties.get(propertyFileDetails.getFilePath()) == null || propFilePathToProperties.get(propertyFileDetails.getFilePath()).getProperties() == null) {
			loadProperties(propertyFileDetails, false);
		}
		return propFilePathToProperties.get(propertyFileDetails.getFilePath());
	}

	@Override
	public IPropertyFileDetails fetchProperties(IPropertyFileDetails propertyFileDetails, boolean overrideExisting) throws FileNotFoundException, IOException {
		if (propFilePathToProperties.get(propertyFileDetails.getFilePath()) == null || propFilePathToProperties.get(propertyFileDetails.getFilePath()).getProperties() == null) {
			loadProperties(propertyFileDetails, overrideExisting);
		}
		return propFilePathToProperties.get(propertyFileDetails.getFilePath());
	}
	
	@Override
	public synchronized IPropertyFileDetails loadProperties(IPropertyFileDetails propertyFileDetails, boolean overrideExisting) throws FileNotFoundException, IOException {

		Properties properties = null;
		InputStream is = null;

		try {
			
			if (propFilePathToProperties == null) {
				throw new IOException("In PropertyReaderUtils - loadProperties(IPropertyFileDetails propertyFileDetails, boolean overrideExisting) :: Unable to Init Properties");
			}

			if (!overrideExisting && propFilePathToProperties.get(propertyFileDetails.getFilePath()) != null && propFilePathToProperties.get(propertyFileDetails.getFilePath()).getProperties() != null) {
				return propFilePathToProperties.get(propertyFileDetails.getFilePath());
			}

			FileReader fr = new FileReader(propertyFileDetails.getFilePath());
			
			properties = new Properties();
			properties.load(fr);
			
			propertyFileDetails.setProperties(properties);
			
			propFilePathToProperties.put(propertyFileDetails.getFilePath(), propertyFileDetails);
			
			return propertyFileDetails;
			
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("In PropertyReaderUtils - loadProperties(IPropertyFileDetails propertyFileDetails, boolean overrideExisting) :: FileNotFoundException while reading file :: " + propertyFileDetails);
		} catch (IOException e) {
			throw new IOException("In PropertyReaderUtils - loadProperties(IPropertyFileDetails propertyFileDetails, boolean overrideExisting) :: IOException while reading file :: " + propertyFileDetails, e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new IOException("In PropertyReaderUtils - loadProperties(IPropertyFileDetails propertyFileDetails, boolean overrideExisting) :: IOException while closing stream :: " + propertyFileDetails, e);
				}
			}
		}
	}
}
