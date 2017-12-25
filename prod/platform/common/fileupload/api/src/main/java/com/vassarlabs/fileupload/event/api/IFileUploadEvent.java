package com.vassarlabs.fileupload.event.api;

import java.util.List;

import org.springframework.core.ResolvableTypeProvider;

import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;

/**
 * Event publisher for file upload
 * @author vaibhav
 *
 * @param <E>
 */
public interface IFileUploadEvent<E> extends ResolvableTypeProvider{

	public Class<E> getClassType();

	public void setClassType(Class<E> source);

	public IFileUploadDetails getFileUploadDetails();

	public void setFileUploadDetails(IFileUploadDetails fileUploadDetails);

	public List<E> getDataList();

	public void setDataList(List<E> dataList);

}
