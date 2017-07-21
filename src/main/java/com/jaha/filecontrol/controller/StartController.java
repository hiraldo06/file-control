package com.jaha.filecontrol.controller;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jaha.filecontrol.service.ArchivoService;


@Controller
@RequestMapping("/")
public class StartController {
	@Autowired
	@Qualifier("archivoService")
	private ArchivoService archivoService;
	
	org.apache.commons.logging.Log LOG =LogFactory.getLog(StartController.class);
	
	
	//localhost:8080/?search=Datos
	@GetMapping("/")
	public ModelAndView startMethod(@RequestParam(name="search",required=false,defaultValue="NULL")String search){
		ModelAndView mav=new ModelAndView("index");
		if(!search.equals("NULL")){
			mav.addObject("archivos", archivoService.findByNombreContainingOrCedulaContaining(search, search));
			
		}else{
			mav.addObject("archivos", archivoService.findAll());
		}
		
		LOG.info(
		archivoService.findByNombreLikeOrTelefonoLike("Juan","123").toString());
		
		LOG.info(
				archivoService.findByNombreContainingOrCedulaContaining("Juan","123").toString());
		
		
		LOG.info(archivoService.findByNombreContainingOrCedulaContainingOrTelefonoContaining("Juan", "", ""));
		
		return mav;
	}
}
