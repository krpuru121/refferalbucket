package com.vassarlabs.fileupload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vassarlabs.fileupload.pojo.api.IFileUploadResult;
import com.vassarlabs.fileupload.service.api.ICSVFileReaderService;
import com.vassarlabs.fileupload.service.api.IFileUploadService;
import com.vassarlabs.fileupload.utils.FileUploadConstants;

@Component("FileUploadService")
public abstract class FileUploadService 
	implements IFileUploadService {	
	
	@Autowired
	protected ICSVFileReaderService fileReaderService;
	
	protected IFileUploadResult appendFileUploadResult(IFileUploadResult fileUploadResult, IFileUploadResult resultToAppend) {
	
		if(fileUploadResult == null)
		{
			fileUploadResult = resultToAppend;
		}
		else{
			fileUploadResult.setNoOfRecords(fileUploadResult.getNoOfRecords() + resultToAppend.getNoOfRecords());
			fileUploadResult.setNoOfInsertedRecords(fileUploadResult.getNoOfInsertedRecords() + resultToAppend.getNoOfInsertedRecords());
			fileUploadResult.setNoOfErrorRecords(fileUploadResult.getNoOfErrorRecords() + resultToAppend.getNoOfErrorRecords());
			fileUploadResult.setNoOfSuccessfulRecords(fileUploadResult.getNoOfSuccessfulRecords() + resultToAppend.getNoOfSuccessfulRecords());
			fileUploadResult.setNoOfUpdatedRecords(fileUploadResult.getNoOfUpdatedRecords() + resultToAppend.getNoOfUpdatedRecords());
			fileUploadResult.setUploadStatus((fileUploadResult.getUploadStatus() == FileUploadConstants.FILE_UPLOAD_FAILURE || resultToAppend.getUploadStatus() == FileUploadConstants.FILE_UPLOAD_FAILURE) ? FileUploadConstants.FILE_UPLOAD_FAILURE : FileUploadConstants.FILE_UPLOAD_SUCCESS);
			fileUploadResult.getErrorList().addAll(resultToAppend.getErrorList());
		}
			
		return fileUploadResult;
	}

}
