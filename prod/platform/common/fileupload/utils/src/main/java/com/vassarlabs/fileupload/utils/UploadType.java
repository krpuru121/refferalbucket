package com.vassarlabs.fileupload.utils;

public enum UploadType {

	UPLOAD (1),
	UPLOADWITHBATCH (2),
	UPLOADWITHNAMEMAPPING (3),
	UPLOADWITHBATCHANDNAMEMAPPING (4);
	
	private int uploadType;
	
	private UploadType(int uploadType) {
		this.uploadType = uploadType;
	}
	
	public int getUploadType() {
		return this.uploadType;
	}
	
	public static UploadType getUploadType(int value) {
	    for (UploadType uploadType : UploadType.values()) {
            if (value == uploadType.getUploadType()) {
                return uploadType;
            }
        }
	    return null;
    }
}
