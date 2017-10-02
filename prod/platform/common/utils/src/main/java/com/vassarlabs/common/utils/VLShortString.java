package com.vassarlabs.common.utils;

public class VLShortString {
   private String stringToValidate;
   private static final int minlength=5;
   public VLShortString(String s)
   {
	      this.stringToValidate=s;
   }
   
   public boolean validateString()
   {
	   int length=stringToValidate.length();
	   if(length <minlength)
	   {
		   return true;
	   }
	   return false;
   }
}
