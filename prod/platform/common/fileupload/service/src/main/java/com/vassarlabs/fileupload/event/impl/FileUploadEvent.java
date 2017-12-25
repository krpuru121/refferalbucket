package com.vassarlabs.fileupload.event.impl;

import java.util.List;

import org.springframework.core.ResolvableType;

import com.vassarlabs.fileupload.event.api.IFileUploadEvent;
import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;

public class FileUploadEvent<E> implements IFileUploadEvent<E>{

	protected Class<E> classType;
	protected List<E> dataList;
	protected IFileUploadDetails fileUploadDetails;
	
	public FileUploadEvent(Class<E> classType, IFileUploadDetails fileUploadDetails, List<E> dataList){
		this.classType = classType;
		this.dataList = dataList;
		this.fileUploadDetails = fileUploadDetails;
	}
    
	@Override
    public Class<E> getClassType() {
		return classType;
	}

	@Override
    public void setClassType(Class<E> classType) {
		this.classType = classType;
	}

	@Override
    public ResolvableType getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forClass(classType));
    }
    
	@Override
	public IFileUploadDetails getFileUploadDetails() {
		return fileUploadDetails;
	}

	@Override
	public void setFileUploadDetails(IFileUploadDetails fileUploadDetails) {
		this.fileUploadDetails = fileUploadDetails;
	}

	@Override
	public List<E> getDataList() {
		return dataList;
	}

	@Override
	public void setDataList(List<E> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "FileUploadEvent [fileUploadDetails=" + fileUploadDetails
				+ ", dataList=" + dataList + ", source=" + classType + "]";
	}
	
}
