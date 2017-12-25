package com.vassarlabs.fileupload.service.impl;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.vassarlabs.common.logging.api.IVLLogService;
import com.vassarlabs.common.logging.api.IVLLogger;
import com.vassarlabs.fileupload.service.api.ICSVFileWriterService;

@Component
public class CSVFileWriterService implements ICSVFileWriterService {

	@Autowired
	private IVLLogService logFactory;

	private IVLLogger logger;

	@PostConstruct
	public void init() {
		logger = logFactory.getLogger(getClass());
	}
	
	@Override
	public <E> void writeErrorsInCSVFile(List<E> errorObjectList, String filePath, String fileName, Class<E> classType)
			throws FileNotFoundException, IOException {

		CSVWriter csvWriter = null;
		
		//String fullFilePath = filePath.concat("/errors/").concat(fileName).concat(".error");
		String fullFilePath = filePath.concat(fileName).concat(".error");
		System.out.println("fullFilePath  "+fullFilePath);
		
		try{
			csvWriter = new CSVWriter(new FileWriter(fullFilePath));
			
			BeanToCsv<E> beanTOCSV = new BeanToCsv<E>();
			
			ColumnPositionMappingStrategy<E> mappingStrategy = new ColumnPositionMappingStrategy<E>();
            
			String[] columns = new String[]{"lineNo","errorCode","errorType","errorMessage", "rowUploadStatus", "rowData"};
			
			mappingStrategy.setType(classType);
			mappingStrategy.setColumnMapping(columns);
			
            beanTOCSV.write(mappingStrategy,csvWriter,errorObjectList);
            
		} catch(Exception e) {
			
			logger.error("In CSVFileWriterService : writeErrorsInCSVFile(List<E>, String, String, Class<E>) :: Exception in writing in CSV file "+e);
			e.printStackTrace();
			
		} finally{
			try {
				csvWriter.close();
			} catch (IOException e) {
				logger.error("In CSVFileWriterService : writeErrorsInCSVFile(List<E>, String, String, Class<E>) :: Exception in closing CSVWriter "+e);
				e.printStackTrace();
			}
		}
	}

}