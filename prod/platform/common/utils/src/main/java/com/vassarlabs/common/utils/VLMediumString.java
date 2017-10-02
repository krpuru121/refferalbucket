package com.vassarlabs.common.utils;

public class VLMediumString {
	   private String stringToValidate;
	   private static final int minlength=5;
	   private static final int maxlength=30;
	   public VLMediumString(String s)
	   {
		      this.stringToValidate=s;
	   }
	   
	   public boolean validateString()
	   {
		   int length=stringToValidate.length();
		   if(length >minlength && length <maxlength)
		   {
			   return true;
		   }
		   return false;
	   }
}
