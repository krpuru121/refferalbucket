package com.vassarlabs.fileupload.aspect.impl;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vassarlabs.common.logging.api.IVLLogService;
import com.vassarlabs.common.logging.api.IVLLogger;
import com.vassarlabs.common.utils.err.InsufficientDataException;
import com.vassarlabs.fileupload.aspect.api.IFileUploadAspect;
import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;

@Component
@Aspect
public class FileUploadAspect implements IFileUploadAspect{
	
	@Autowired
	private IVLLogService logFactory;

	private IVLLogger logger;

	@PostConstruct
	public void init() {
		logger = logFactory.getLogger(getClass());
	}
	
	@Override
	@Before("execution(* *.IFileUploadService.uploadFile(..)) && args(fileUploadDetails, ..)")
	public void verifyUploadDetails(JoinPoint joinPoint, IFileUploadDetails fileUploadDetails) throws InsufficientDataException {

		if(fileUploadDetails.getFileFullPath() == null)
			logger.error("In FileUploadAspect - verifyFileUploadDetails(JoinPoint joinPoint, IFileUploadDetails fileUploadDetails) :: File full path not found "+ fileUploadDetails);
		
		if(fileUploadDetails.getBatchSize() == null)
			logger.error("In FileUploadAspect - verifyFileUploadDetails(JoinPoint joinPoint, IFileUploadDetails fileUploadDetails) :: Batch Size not found "+ fileUploadDetails);
		
		if(fileUploadDetails.getProperties() == null)
			logger.error("In FileUploadAspect - verifyFileUploadDetails(JoinPoint joinPoint, IFileUploadDetails fileUploadDetails) :: Properties not found "+ fileUploadDetails);
		
		logger.info("In FileUploadAspect - verifyFileUploadDetails(JoinPoint joinPoint, IFileUploadDetails fileUploadDetails) :: File Verified Successfully "+ fileUploadDetails);
		
	}
	
}
