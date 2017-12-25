package com.vassarlabs.fileupload.aspect.api;

import org.aspectj.lang.JoinPoint;

import com.vassarlabs.common.utils.err.InsufficientDataException;
import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;

public interface IFileUploadAspect {
	
	public void verifyUploadDetails(JoinPoint joinPoint, IFileUploadDetails fileUploadDetails) throws InsufficientDataException;
	
}
