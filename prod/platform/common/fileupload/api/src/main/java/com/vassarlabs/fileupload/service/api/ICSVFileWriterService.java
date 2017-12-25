package com.vassarlabs.fileupload.service.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ICSVFileWriterService {

	/**
	 * 
	 * @param errorObjectList
	 * @param FilePath
	 * @param fileName
	 * @param classType
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public <E> void writeErrorsInCSVFile(List<E> errorObjectList, String FilePath, String fileName, Class<E> classType)
			throws FileNotFoundException, IOException;
}