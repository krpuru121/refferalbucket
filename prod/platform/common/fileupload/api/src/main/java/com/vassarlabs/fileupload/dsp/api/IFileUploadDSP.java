package com.vassarlabs.fileupload.dsp.api;

import java.util.List;

/**
 * DSP to upload data, will be used when overriding with a child class.
 * @author vaibhav
 *
 */
public interface IFileUploadDSP {

	/**
	 * Uploads the list of data in a generic format
	 * @param dataList
	 */
	public <E> void uploadData(List<E> dataList);
	
}
