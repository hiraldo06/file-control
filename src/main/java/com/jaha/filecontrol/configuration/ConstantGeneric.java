package com.jaha.filecontrol.configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("constantGeneric")
public class ConstantGeneric {
	
	
	/*
	 * Varibles Estaticas para la paginacion
	 */
	
	//public static final int BOTON_A_MOSTRAR = 5;
	public static final int PAGINA_INCIAL = 0;
	public static final int SIZE_PAGINA_INCIAL =15;
	///public static final int[] SIZES_PAGINA = { 5, 10, 20 };
	
	
	
	
	
	
	/*
	 * Combia las el valor del menu para agregar la clase active de acuerdo en la pagina que te encuentres
	 */
	
	
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
	
	
	
	//Confirma el tipo de role que posee un usuario
	public Boolean confirmRole(String role){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
		for(GrantedAuthority grantedAuthority:authorities){
			if(grantedAuthority.getAuthority().equals(role)){
				return true;
			}
		}
		return false;
	}
}
