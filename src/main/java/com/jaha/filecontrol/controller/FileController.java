package com.jaha.filecontrol.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jaha.filecontrol.entity.Archivo;
import com.jaha.filecontrol.service.ArchivoService;

@Controller
@RequestMapping("/")
public class FileController {
	
	@Autowired
	@Qualifier("archivoService")
	private ArchivoService archivoService;
	
	Log LOG=LogFactory.getLog(FileController.class);
	
	@GetMapping("/registro")
	public ModelAndView registro(){
		LOG.info("METHOD: RESTRIGO()");
		ModelAndView mav=new ModelAndView("archivos");
		mav.addObject("archivos",archivoService.findAll());
		mav.addObject("name","Tabla de archivo");
		mav.addObject("form_name","Formulario De Archivos");
		mav.addObject("archivo",new Archivo());
		return mav;
	}
	
	@PostMapping("/addFile")
	public String addFile(@ModelAttribute("archivo") Archivo archivo){
		archivoService.saveFile(archivo);
		return "redirect:/";
	}
}
