package com.jaha.filecontrol.controller;

import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jaha.filecontrol.configuration.ConstantGeneric;
import com.jaha.filecontrol.service.ArchivoService;


@Controller
@RequestMapping("/")
public class StartController {
	@Autowired
	@Qualifier("archivoService")
	private ArchivoService archivoService;
	
	@Autowired
	@Qualifier("constantGeneric")
	private ConstantGeneric constantGeneric;
	
	org.apache.commons.logging.Log LOG =LogFactory.getLog(StartController.class);
	
	
	//localhost:8080/?search=Datos
	//se puede usar hasRole('ROLE_USE') or o and hasRole('ROLE_ADMIN')
	//se puede usar permitAll()
	@PreAuthorize("permitAll()")
	@GetMapping("/")
	public ModelAndView startMethod(@RequestParam(name="search",required=false,defaultValue="NULL")String search){
		ModelAndView mav=new ModelAndView("index");
		boolean usuario=false;
	
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
		for(GrantedAuthority grantedAuthority:authorities){
			if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
				usuario=true;
				break;
			}
		}
		
		LOG.info("Method:startM ROLE:"+SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
		if(!search.equals("NULL")){
			mav.addObject("archivos", archivoService.findByNombreContainingOrCedulaContaining(search, search));
			
		}else{
			mav.addObject("archivos", archivoService.findAll());
		}
			mav.addObject("usuario",usuario);
			mav.addObject("menuActive",constantGeneric.changeValuerMenu("index", true));
		LOG.info("METHOD: startMethod()");
		return mav;
	}
}
