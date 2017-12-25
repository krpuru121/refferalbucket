package com.vassarlabs.fileupload.service.api;

import java.io.IOException;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.utils.err.InsufficientDataException;
import com.vassarlabs.common.utils.err.ObjectNotFoundException;
import com.vassarlabs.config.err.PropertyNotFoundException;
import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;

/**
 * Service for File Upload, will be used when overriding with a child class.
 * 
 * <p>
 * {@link IFileUploadDetails}
 * {@link Class<E>}
 * </p>
 * 
 * @author vaibhav
 *
 */
public interface IFileUploadService {

	public <E> void uploadFile(IFileUploadDetails fileUploadDetails, Class<E> classType) throws IOException, ObjectNotFoundException, InsufficientDataException, PropertyNotFoundException, DSPException;

}