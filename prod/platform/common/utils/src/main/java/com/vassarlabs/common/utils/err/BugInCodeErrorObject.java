package com.vassarlabs.common.utils.err;

import com.vassarlabs.common.utils.StringUtils;

public class BugInCodeErrorObject
	extends ErrorObject {
	
	public BugInCodeErrorObject(String errorMessage) {
		this.errorCodeInt = IErrorObject.BUG_IN_CODE_ERROR_CODE;
		this.errorCodeStr = StringUtils.EMPTY_STRING;
		this.errorMessage = errorMessage;
	}
}
