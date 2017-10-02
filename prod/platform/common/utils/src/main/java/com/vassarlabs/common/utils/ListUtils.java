package com.vassarlabs.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;
import java.util.Set;
import java.util.HashSet;

public class ListUtils {

	public static <E> List<E> reverseList(List<E> list) {

		if (list == null) {
			return list;
		}

		List<E> reversed = new ArrayList<E>();
		for (int index = 0; index < list.size();index++) {
			reversed.add(list.get(list.size() - index - 1));
		}
		return reversed;
	}


    public static <E> Set<E> convertListToSet(List<E> list){

		if(list == null || list.isEmpty())
			return null;

		Set<E> set = new HashSet<E>();
		set.addAll(list);

		return set;
	}

    public static <E> E[] convertListToArray(List<E> list) {

    	if(list == null || list.isEmpty())
    		return null;

        @SuppressWarnings("unchecked")
        E arr[] = (E[]) Array.newInstance(getComponentTypeofArrayList(list), list.size());
        arr = list.toArray(arr);
        return arr;
    }

    public static <E> List<E> convertSetToList(Set<E> set) {

    	if(set == null || set.isEmpty())
        	return null;

    	List<E> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    private static Class<?> getComponentTypeofArrayList(List<?> list){
        if(list == null || list.isEmpty())
            return null;
        return list.get(0).getClass();
    }

    public static <E> List<String> convertListGenericToStringList(List<E> list){

    	if(list == null || list.isEmpty())
            return null;

        List<String> stringList = new ArrayList<>(list.size());

        for (Object object : list)
            stringList.add(object != null ? object.toString() : null);

        return stringList;
    }
}
