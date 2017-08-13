package com.jaha.filecontrol.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jaha.filecontrol.configuration.ConstantGeneric;
import com.jaha.filecontrol.entity.Archivo;
import com.jaha.filecontrol.service.ArchivoService;

@Controller
@RequestMapping("/")
public class FileController {
	
	@Autowired
	@Qualifier("archivoService")
	private ArchivoService archivoService;
	
	@Autowired
	@Qualifier("constantGeneric")
	private ConstantGeneric constantGeneric;
	
	Log LOG=LogFactory.getLog(FileController.class);
	
	@PreAuthorize("permitAll()")
	@GetMapping("/registro")
	public ModelAndView registro(@RequestParam("page")Optional<Integer>page,
			@RequestParam("size")Optional<Integer>size){
		
		
		/*
		 * Evaluando el size de la pagina en caso de ser null se le agrega el valor inicial
		 */
		int ePageSize=size.orElse(constantGeneric.SIZE_PAGINA_INCIAL);
		
		/*
		 * Evaluando la pagina evalua si es null o menor que 0
		 */
		int ePage=(page.orElse(0)<1)?constantGeneric.PAGINA_INCIAL : page.get()-1;
		
		
		LOG.info("METHOD: RESTRIGO()");
		ModelAndView mav=new ModelAndView("archivos");
		Page<Archivo>archivos=archivoService.findAll(new PageRequest(ePage, ePageSize));
		mav.addObject("archivos",archivos);
		mav.addObject("paginaSelecionada",ePageSize);
		mav.addObject("name","Tabla de archivo");
		mav.addObject("form_name","Formulario De Archivos");
		mav.addObject("archivo",new Archivo());
		mav.addObject("pageView","/registro/");
		mav.addObject("menuActive",constantGeneric.changeValuerMenu("file", true));
		return mav;
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping("/addFile")
	public String addFile(@ModelAttribute("archivo") Archivo archivo){
		archivoService.saveFile(archivo);
		return "redirect:/";
	}
}
