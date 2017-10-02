package com.vassarlabs.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionUtils {

	public static List<?> mergeLists(List<?> firstList, List<?> secondList) {
		
		ArrayList<Object> mergedList = new ArrayList<Object>();
		if (firstList != null) {
			for (Object object: firstList) {
				mergedList.add(object);
			}
		}
		if (secondList != null) {
			for (Object object: secondList) {
				mergedList.add(object);
			}
		}
		
		return mergedList;
	}
	
	public static void accumulateValues(Map<String, Double> toMap, Map<String, Double> fromMap, double fraction){
		
		Iterator<Map.Entry<String, Double>> it = fromMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Double> pair = (Entry<String, Double>) it.next();
			
			Double value  =	pair.getValue();
			String key = pair.getKey();
			if(value!=null){
				if(toMap.get(key) != null){
					toMap.put(key, toMap.get(key)+value*fraction)	;
				}else{
					toMap.put(key, value*fraction)	;
				}
			}else{
				toMap.put(key,null);
			}
			
		}
	}

}
