package com.vassarlabs.fileupload.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.vassarlabs.common.logging.api.IVLLogService;
import com.vassarlabs.common.logging.api.IVLLogger;
import com.vassarlabs.common.utils.err.ErrorObject;
import com.vassarlabs.common.utils.err.IErrorObject;
import com.vassarlabs.fileupload.event.impl.FileUploadEvent;
import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;
import com.vassarlabs.fileupload.service.api.ICSVFileReaderService;
import com.vassarlabs.fileupload.service.api.ICSVFileWriterService;
import com.vassarlabs.fileupload.utils.FileUploadConstants;

@Component
public class CSVFileReaderService implements ICSVFileReaderService {
	
	@Autowired
	protected ApplicationEventPublisher publisher;
    
	@Autowired
	private IVLLogService logFactory;
	
	@Autowired ICSVFileWriterService csvFileWriterService;

	private IVLLogger logger;

	@PostConstruct
	public void init() {
		logger = logFactory.getLogger(getClass());
	}
	
	@Override
	public <E> List<E> readCSVFile(IFileUploadDetails fileUploadDetails, Class<E> classType) throws FileNotFoundException, IOException {
		
		List<E> list = null;
		CSVReader reader = null;
		CSVBeanReader<E> csvToBean = null;
		MappingStrategy<E> mappingStrategy = null;
		
		try {

			csvToBean = new CSVBeanReader<E>();
			reader = getCSVReader(fileUploadDetails);
			mappingStrategy = getMappingStrategy(fileUploadDetails, classType);
			
			parseData(fileUploadDetails, csvToBean, mappingStrategy, reader, classType);
		}
		finally{
			if(reader != null)
				reader.close();
		}
		
		return list;
	}
	
	private CSVReader getCSVReader(IFileUploadDetails fileUploadDetails) throws FileNotFoundException {
		
		CSVReader csvReader = null;
		
		if(fileUploadDetails.getDelimiter() != FileUploadConstants.CHAR_DEFAULT_VALUE && fileUploadDetails.getQuotedChar() != FileUploadConstants.CHAR_DEFAULT_VALUE){
			
			csvReader =  new CSVReaderBuilder(new FileReader(fileUploadDetails.getFileFullPath()))
						.withCSVParser(new CSVParser(fileUploadDetails.getDelimiter(), fileUploadDetails.getQuotedChar()))
						.build();

		} else if(fileUploadDetails.getDelimiter() == FileUploadConstants.CHAR_DEFAULT_VALUE){
			
			csvReader = new CSVReaderBuilder(new FileReader(fileUploadDetails.getFileFullPath()))
						.withCSVParser(new CSVParser(CSVParser.DEFAULT_SEPARATOR, fileUploadDetails.getQuotedChar()))
						.build();
		
		} else if(fileUploadDetails.getQuotedChar() == FileUploadConstants.CHAR_DEFAULT_VALUE){
			
			csvReader = new CSVReaderBuilder(new FileReader(fileUploadDetails.getFileFullPath()))
						.withCSVParser(new CSVParser(fileUploadDetails.getDelimiter()))
						.build();
		
		} else {

			csvReader =  new CSVReaderBuilder(new FileReader(fileUploadDetails.getFileFullPath()))
						.withCSVParser(new CSVParser(CSVParser.DEFAULT_SEPARATOR))
						.build();
					
		}
		
		return csvReader;
	}

	private <E> MappingStrategy<E> getMappingStrategy(IFileUploadDetails fileUploadDetails, Class<E> classType) {
		
		if(fileUploadDetails.getColumnNameMapping() != null) {
		
			HeaderColumnNameTranslateMappingStrategy<E> columnNameMappingStrategy = new HeaderColumnNameTranslateMappingStrategy<E>();
			columnNameMappingStrategy.setType(classType);
			columnNameMappingStrategy.setColumnMapping(fileUploadDetails.getColumnNameMapping());
			
			return columnNameMappingStrategy;
		
		} else {	
		
			HeaderColumnNameMappingStrategy<E> mappingStrategy = new HeaderColumnNameMappingStrategy<E>();
			mappingStrategy.setType(classType);
		
			return mappingStrategy;
			
		}
	}
	
	public <E> void parseData(IFileUploadDetails fileUploadDetails, CSVBeanReader<E> csvToBeanReader, MappingStrategy<E> mappingStrategy, CSVReader reader, Class<E> source) throws IOException{
		
		List<E> list = new ArrayList<E>();
		List<IErrorObject> errorObjectList = new ArrayList<>();
		
		mappingStrategy.captureHeader(reader);
		
		String[] readLine = null;
		int i = 0;
		
		do {
			i++;

			if(readLine != null && readLine.length != 0){
				try{
					list.add(csvToBeanReader.processLine(mappingStrategy, readLine));
				}
				catch(Exception e){
					
					IErrorObject errorObject = new ErrorObject();
					
					errorObject.setLineNo(Integer.valueOf(readLine[0]));
					errorObject.setErrorCode(IErrorObject.FILE_UPLOAD_ERROR_CODE);
					errorObject.setErrorType(IErrorObject.FILE_UPLOAD_ERROR_MESSAGE);
					errorObject.setErrorMessage("error in parsing the line ");
					errorObject.setRowUploadStatus(IErrorObject.ROW_NOT_UPLOADED_SUCCESSFULLY_MESSAGE);
					errorObject.setRowData(Arrays.toString(readLine));
					
					errorObjectList.add(errorObject);
					logger.error("In FileReaderService : getDataFromReader(IFileUploadDetails fileUploadDetails, CSVBeanReader<E> csvToBeanReader, MappingStrategy<E> mappingStrategy, CSVReader reader) :: Exception while parsing line ", e);
				}
			}
			
			if(list.size() == fileUploadDetails.getBatchSize()){
				if(errorObjectList.size() > 0){
					File file = new File(fileUploadDetails.getFileFullPath());
					String parent = file.getParent();
					csvFileWriterService.writeErrorsInCSVFile(errorObjectList, parent, fileUploadDetails.getFileName(), IErrorObject.class);
				}
				publisher.publishEvent(new FileUploadEvent<E>(source, fileUploadDetails, list));
				list = new ArrayList<E>();
			}
			
			readLine = reader.readNext();
			
		} while(readLine != null);
		
		if(list.size() != 0){
			if(errorObjectList.size() > 0){
				File file = new File(fileUploadDetails.getFileFullPath());
				String parent = file.getParent();
				csvFileWriterService.writeErrorsInCSVFile(errorObjectList, parent, fileUploadDetails.getFileName(), IErrorObject.class);
			}
			
			//publisher.publishEvent(new FileUploadEvent<E>(source, fileUploadDetails, list));
			list = new ArrayList<E>();
		}
	
	}
	
}
