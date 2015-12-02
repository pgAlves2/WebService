package br.com.ibring.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	
	private static Gson gson;
	
	public static synchronized Gson getGsonInstance(){
		if (gson == null)
			gson = new GsonBuilder().setDateFormat(TimestampAdapter.FORMAT).create();
		
		return gson;
	}

}
