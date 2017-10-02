package com.vassarlabs.common.errors;

import java.util.List;
import java.util.Map;


public interface IVLValidator {

	public List<IMessageObject> isValid(List<String> methods, Object obj);
	
	public boolean isNotUnique();
	
	public Map<String, Object> isCheck1(); 
	
	public Map<String, Object> isCheck2();
	
	public Map<String, Object> preCreationCheck();
	public Map<String, Object> postCreationCheck();
	public Map<String, Object> preDeleteCheck();
	public Map<String, Object> postDeleteCheck();
	public Map<String, Object> preUpdateCheck();
	public Map<String, Object> postUpdateCheck();
	public Map<String, Object> oneMoreCheck();
	
}
