package com.vassarlabs.fileupload.service.api;

import java.io.IOException;
import java.util.List;

import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;

/**
 * Reads different types of files and send its results in various forms
 * 
 * @author vaibhav
 *
 */
public interface ICSVFileReaderService {

	/**
	 * Reads a CSV file and returns <List> of  models
	 * 
	 * @param {@link IFileUploadDetails}
	 * @param classType
	 * @return
	 * @throws IOException
	 */
	public <E> List<E> readCSVFile(IFileUploadDetails fileUploadDetails, Class<E> classType) throws IOException;
	
}
