package com.vassarlabs.common.utils;

public class VLLongString {
	   private String stringToValidate;
	   private static final int maxlength=30;
	   public VLLongString(String s)
	   {
		      this.stringToValidate=s;
	   }
	   
	   public boolean validateString()
	   {
		   int length=stringToValidate.length();
		   if(length > maxlength)
		   {
			   return true;
		   }
		   return false;
	   }
}
