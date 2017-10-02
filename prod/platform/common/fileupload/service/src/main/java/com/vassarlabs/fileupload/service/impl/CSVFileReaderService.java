package com.vassarlabs.fileupload.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.vassarlabs.common.logging.api.IVLLogService;
import com.vassarlabs.common.logging.api.IVLLogger;
import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;
import com.vassarlabs.fileupload.service.api.ICSVFileReaderService;
import com.vassarlabs.fileupload.utils.FileUploadConstants;

@Component
public class CSVFileReaderService 
	implements ICSVFileReaderService {
	
	@Autowired
	private IVLLogService logFactory;

	private IVLLogger logger;

	@PostConstruct
	public void init() {
		logger = logFactory.getLogger(getClass());
	}
	
	@Override
	public <E> List<E> readCSVFile(IFileUploadDetails fileUploadDetails, Class<E> classType) throws FileNotFoundException, IOException {
		
		List<E> list = null;
		CSVReader reader = null;
		
		try {

			reader = getCSVReader(fileUploadDetails);
			
			CSVBeanReader<E> csvToBean = new CSVBeanReader<E>();
			
			MappingStrategy<E> mappingStrategy = getMappingStrategy(fileUploadDetails, classType);
			
			if(mappingStrategy == null)
			{
				logger.info("FileReaderService - readCSVFile(IFileUploadDetails fileUploadDetails, Class<E> classType) :: Could not create startegy ; Upload Type - "+fileUploadDetails.getUploadType());
				return list;
			}
			
			list = getDataFromReader(fileUploadDetails, csvToBean, mappingStrategy, reader);
		}
		finally{
			if(reader != null)
				reader.close();
		}
		
		return list;
	}
	
	private CSVReader getCSVReader(IFileUploadDetails fileUploadDetails) throws FileNotFoundException {
		
		CSVReader csvReader = null;
		
		if(fileUploadDetails.getDelimiter() == FileUploadConstants.CHAR_DEFAULT_VALUE && fileUploadDetails.getQuotedChar() == FileUploadConstants.CHAR_DEFAULT_VALUE){
		
			csvReader =  new CSVReaderBuilder(new FileReader(fileUploadDetails.getFileFullPath()))
						.withCSVParser(new CSVParser(CSVParser.DEFAULT_SEPARATOR))
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
						.withCSVParser(new CSVParser(fileUploadDetails.getDelimiter(), fileUploadDetails.getQuotedChar()))
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
	
	public <E> List<E> getDataFromReader(IFileUploadDetails fileUploadDetails, CSVBeanReader<E> csvToBeanReader, MappingStrategy<E> mappingStrategy, CSVReader reader) throws IOException{
		
		List<E> list = new ArrayList<E>();
		
		switch (fileUploadDetails.getUploadType()) {
			
			case UPLOAD: case UPLOADWITHNAMEMAPPING: default:
			
				list = csvToBeanReader.parse(mappingStrategy, reader);
			
			break;
			
			case UPLOADWITHBATCH: case UPLOADWITHBATCHANDNAMEMAPPING:
				
				mappingStrategy.captureHeader(reader);
				
				String[] readLine = null;
				int i = -1;
				
				do {

					i++;
					
					if(i <= fileUploadDetails.getBatchSize() * fileUploadDetails.getBatchNo())
					{
						readLine = reader.readNext();
						continue;
					}
					
					if(readLine != null && readLine.length != 0){
						try{
							list.add(csvToBeanReader.processLine(mappingStrategy, readLine));
						}
						catch(Exception e){
							logger.error("In FileReaderService : getDataFromReader(IFileUploadDetails fileUploadDetails, CSVBeanReader<E> csvToBeanReader, MappingStrategy<E> mappingStrategy, CSVReader reader) :: Exception while parsing line ", e);
						}
					}
					readLine = reader.readNext();
					
					if(readLine == null)
						break;
					
				} while(i < fileUploadDetails.getBatchSize() * (fileUploadDetails.getBatchNo() + 1));
				
			break;
			
		}
		
		return list;
	}

}
