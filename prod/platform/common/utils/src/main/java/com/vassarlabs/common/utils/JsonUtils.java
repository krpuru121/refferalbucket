package com.vassarlabs.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
 
public class JsonUtils {

   private static final Gson gson = initializeGson();
  
  /**
   * 
   * @return {@link Gson}
   */
   private static final Gson initializeGson() {
     final GsonBuilder builder = new GsonBuilder();
     return builder.create();
   }
  
   /**
    * Get reusable {@link Gson} instance
    *
    * @return Gson instance
    */
   private static final Gson getGson() {
     return gson;
   }
  
   /**
    * Convert string to given type
    *
    * @param json
    * @param classType
    * @return type from Json
    */
   public static final <V> V fromJson(String json, Class<V> classType) {
     return getGson().fromJson(json, classType);
   }
  
   /**
    * Convert string to given type
    *
    * @param json
    * @param classType
    * @return type from Json
    */
   public static final <V> V fromJson(String json, Type classType) {
     return getGson().fromJson(json, classType);
   }
  
}
