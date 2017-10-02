package com.vassarlabs.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListResourceBundle;
import java.util.Map;

import com.vassarlabs.common.errors.ErrorObject;
import com.vassarlabs.common.errors.IMessageObject;
import com.vassarlabs.common.errors.InfoObject;
import com.vassarlabs.common.errors.InputObject;
import com.vassarlabs.common.errors.WarningObject;

public class CommonResources_hi_IN extends ListResourceBundle{

	private Object[][] contents;
	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		
		Map<String, IMessageObject> errorMap =getI8nErrors();
		contents=formatData(errorMap);
		return contents;
	}

	public Map<String, IMessageObject> getI8nErrors()
	{
		Map<String, IMessageObject> errors = new HashMap<String, IMessageObject>();
		IMessageObject err_10001 = new ErrorObject(10001, "Location Name ${0} - ${1} not Unique Error 4.", "Location Name not Unique Custom Error 4.");
		//IMessageObject err_10001 = new ErrorObject(10001, ResourceBundle.getBundle("MessageBundle", currentLocale).getString("10001"));
		
		errors.put("10001", err_10001);
		
		IMessageObject err_10002 = new WarningObject(10002, "Location Name not Unique Warning 3.", "Location Name not Unique Custom Warning 3.");
		errors.put("10002", err_10002);
		
		IMessageObject err_10003 = new InfoObject(10003, "Location Name not Unique Information 2.", "Location Name not Unique Custom Information 2.");
		errors.put("10003", err_10003);
		
		IMessageObject err_10004 = new InputObject(10004, "Location Name not Unique Input 1.", "Location Name not Unique Custom Input 1.");
		errors.put("10004", err_10004);
		
		IMessageObject err_10005 = new ErrorObject(10005, "Location name can't be too small", "Location Name cannot be too small");
		errors.put("10005", err_10005);
		
		IMessageObject err_10006 = new WarningObject(10006, "Minimum length for location is 5", "Minimum length for location is 5");
		errors.put("10006", err_10006);
		
		IMessageObject err_10007 = new WarningObject(10007, "Description field cannot be too small", "Minimum Description length is 10");
		errors.put("10007", err_10007);
		
		return errors;
	}
	
	public Object[][] formatData(Map<String, IMessageObject> map)
	{
		contents=new Object[map.size()][2];

		Iterator<Map.Entry<String,IMessageObject>> itr = map.entrySet().iterator();
		int i=0;
		while(itr.hasNext())
		{
			Map.Entry<String,IMessageObject> mapping =(Map.Entry<String, IMessageObject>)itr.next();
			contents[i][0]=mapping.getKey();
			contents[i][1]=mapping.getValue();
			i++;
		}
		return contents;
	}
	
}
