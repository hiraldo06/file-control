package com.jaha.filecontrol.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

@Component("constantGeneric")
public class ConstantGeneric {
	private static HashMap<String, Boolean>mapGeneric=new HashMap<String,Boolean>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put("index",true);
		put("file",false);
		put("user",false);
	}};
	
	public HashMap<String,Boolean> changeValuerMenu(String key,Boolean value){
		for (Entry<String, Boolean> e: mapGeneric.entrySet()) {
	       if(e.getKey().equals(key))
	    	   mapGeneric.put(key, value);
	       else
	    	   mapGeneric.put(e.getKey(), false);
	    }
		return mapGeneric;
	}
}
