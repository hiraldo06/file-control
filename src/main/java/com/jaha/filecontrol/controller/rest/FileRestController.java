package com.jaha.filecontrol.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaha.filecontrol.entity.Archivo;
import com.jaha.filecontrol.service.ArchivoService;

@RestController("/files")
public class FileRestController {
	
	@Autowired
	@Qualifier("archivoService")
	private ArchivoService archivoService;
	
	@GetMapping("/files")
	Page<Archivo>archivosPageable(Pageable pageable){
		return archivoService.findAll(pageable);
	}
}
