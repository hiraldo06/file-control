package com.jaha.filecontrol.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	@Qualifier("constantGeneric")
	private ConstantGeneric constantGeneric;
	
	@ModelAttribute
	public void addAtributesRole(Model model){
		
		model.addAttribute("usuario",constantGeneric.confirmRole("ROLE_ADMIN"));
	}
}
