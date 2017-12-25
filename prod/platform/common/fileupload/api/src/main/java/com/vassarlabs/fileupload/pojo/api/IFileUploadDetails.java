package com.vassarlabs.fileupload.pojo.api;

import java.util.Map;
import java.util.Properties;

/**
 * Represents File Upload Details
 * 
 * @author vaibhav
 *
 */
public interface IFileUploadDetails {

	public String getFileName();
	public void setFileName(String fileName);
	
	public String getFileFullPath();
	public void setFileFullPath(String fullPath);
	
	public char getDelimiter();
	public void setDelimiter(char delimiter);
	
	public char getQuotedChar();
	public void setQuotedChar(char quotedChar);
	
	public Long getFileUploadTs();
	public void setFileUploadTs(Long fileUploadTs);

	public Integer getBatchSize();
	public void setBatchSize(Integer batchSize);
	
	public Properties getProperties();
	public void setProperties(Properties properties);
	
	public Map<String, String> getColumnNameMapping();
	public void setColumnNameMapping(Map<String, String> columnNameMapping);
	
}
