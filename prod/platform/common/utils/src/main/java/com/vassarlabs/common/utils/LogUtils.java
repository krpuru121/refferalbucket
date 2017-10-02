package com.vassarlabs.common.utils;

import java.util.Arrays;
import java.util.List;

public class LogUtils {
	
	public static int ARRAY_LOG_MAX_SIZE = 100;
	
	public static String prepareForLog(List<?> objectList) {
		
		String logStr = "";
		if (objectList == null || objectList.isEmpty()) {
			logStr = " = <Empty List>...list size : 0";
			return logStr;
		}

		String tmp = objectList.toString();
		if (tmp.length() > ARRAY_LOG_MAX_SIZE) {
			int startIndex = ARRAY_LOG_MAX_SIZE / 2 - 2;
			int endIndex = tmp.length() - (ARRAY_LOG_MAX_SIZE / 2) + 2;
			if (startIndex < 0) {
				startIndex = ARRAY_LOG_MAX_SIZE;
			}
			if (endIndex > tmp.length()) {
				endIndex = tmp.length();
			}
			tmp = tmp.substring(0, startIndex) + "....." + tmp.substring(endIndex, tmp.length());
		}
		logStr = tmp + " - ....list size : " + objectList.size();
		return logStr;
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Start");
		Long[] arr = new Long[] {28110100203l,28110100204l,28110100301l,28110100403l,28110100502l,28110100702l,28110100803l,28110101002l,28110101003l,28110101005l,28110101101l,28110101201l,28110101602l,28110101701l,28110101810l,28110101811l,28110101812l,28110101813l,28110101815l,28110101816l,28110102004l,28110102103l,28110102203l,28110102501l,28110102801l,28110102901l,28110103001l,28110103101l,28110103204l,28110103602l,28110103603l,28110103702l,28110103801l,28110103901l,28110104002l,28110104102l,28110200102l,28110200302l,28110200402l,28110200501l,28110200601l,28110200901l,28110201002l,28110201203l,28110201601l,28110201702l,28110201703l,28110201705l,28110201706l,28110201707l,28110202001l,28110202101l,28110202102l,28110202201l,28110202402l,28110202702l,28110203003l,28110203202l,28110203401l,28110203703l,28110203704l,28110203705l,28110300201l,28110300403l,28110300404l,28110300501l,28110300803l,28110301003l,28110301202l,28110301203l,28110301402l,28110301602l,28110301702l,28110301903l,28110302301l,28110302404l,28110302602l,28110302803l,28110303101l,28110303103l,28110303402l,28110303502l,28110303604l,28110304302l,28110304501l,28110304602l,28110304903l,28110305001l,28110400103l,28110400302l,28110400402l,28110400504l,28110400506l,28110400507l,28110400604l,28110400714l,28110400716l,28110400719l,28110400722l,28110400723l,28110400725l,28110400726l,28110400728l,28110400731l,28110400732l,28110400745l,28110400746l,28110400748l,28110400751l,28110400803l,28110400904l,28110401102l,28110401204l,28110401501l,28110401703l,28110401802l,28110401902l,28110402001l,28110402202l,28110402302l,28110402403l,28110402404l,28110402501l,28110402801l,28110402903l,28110403002l,28110490405l,28110490406l,28110490408l,28110490409l,28110490410l,28110490411l,28110490412l,28110490414l,28110490416l,28110490419l,28110490421l,28110490422l,28110490425l,28110490427l,28110500102l,28110500201l,28110500303l,28110500502l,28110500701l,28110500801l,28110500803l,28110501104l,28110501105l,28110501201l,28110501402l,28110501503l,28110501703l,28110501704l,28110501708l,28110501711l,28110501714l,28110502001l,28110502102l,28110502402l,28110502703l,28110502803l,28110502804l,28110503002l,28110503102l,28110503201l,28110503302l,28110503402l,28110503701l,28110503803l,28110504001l,28110504203l,28110600101l,28110600204l,28110600401l,28110600603l,28110600703l,28110600704l,28110600803l,28110601103l,28110601104l,28110601201l,28110601402l,28110601504l,28110601701l,28110601804l,28110601906l,28110601907l,28110602004l,28110602005l,28110602106l,28110602108l,28110602110l,28110602112l,28110602202l,28110602203l,28110602601l,28110602802l,28110603101l,28110603302l,28110603405l,28110603406l,28110603407l,28110603503l,28110603801l,28110700201l,28110700303l,28110700503l,28110700702l,28110700703l,28110700806l,28110700808l,28110700809l,28110700812l,28110700813l,28110700815l,28110701003l,28110701004l,28110701101l,28110701303l,28110701503l,28110701504l,28110701505l,28110701507l,28110701701l,28110701807l,28110701809l,28110701810l,28110701907l,28110701910l,28110701913l,28110701918l,28110702103l,28110702201l,28110702702l,28110702703l,28110702901l,28110703201l};
		
		List<Long> tmp = Arrays.asList(arr);
		System.out.println(prepareForLog(tmp));
		
		System.out.println("End");
	}
}