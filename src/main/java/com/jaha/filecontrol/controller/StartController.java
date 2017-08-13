package com.jaha.filecontrol.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.jaha.filecontrol.entity.Archivo;
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
	public ModelAndView startMethod(@RequestParam(name="search",required=false,defaultValue="NULL")String search,
									@RequestParam("page")Optional<Integer>page,
									@RequestParam("size")Optional<Integer>size){
	
		Page<Archivo>archivos=null;
		/*
		 * Evaluando el size de la pagina en caso de ser null se le agrega el valor inicial
		 */
		int ePageSize=size.orElse(constantGeneric.SIZE_PAGINA_INCIAL);
		
		/*
		 * Evaluando la pagina evalua si es null o menor que 0
		 */
		int ePage=(page.orElse(0)<1)?constantGeneric.PAGINA_INCIAL : page.get()-1;
		
		
		
		
		ModelAndView mav=new ModelAndView("index");
		LOG.info("Method:startM ROLE:"+SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
		if(!search.equals("NULL")){
			mav.addObject("archivos", archivoService.findByNombreContainingOrCedulaContaining(search, search));
			
		}else{
			/*
			 * creando el Objeto Page que sera devuelto y llenado por el metodo findAll(Pageable)
			 */
			LOG.info("PAGE:"+ePage+" SIZE:"+ePageSize);
			archivos=archivoService.findAll(new PageRequest(ePage, ePageSize));
			
			//mav.addObject("sizePage",constantGeneric.SIZES_PAGINA);
		}
			mav.addObject("archivos", archivos);
		
			mav.addObject("paginaSelecionada",ePageSize);
			mav.addObject("pageView","/");
			mav.addObject("usuario",constantGeneric.confirmRole("ROLE_ADMIN"));
			mav.addObject("menuActive",constantGeneric.changeValuerMenu("index", true));
		LOG.info("METHOD: startMethod()");
		return mav;
	}
}
