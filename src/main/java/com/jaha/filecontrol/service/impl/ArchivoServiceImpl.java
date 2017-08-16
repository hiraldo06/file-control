package com.jaha.filecontrol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jaha.filecontrol.entity.Archivo;
import com.jaha.filecontrol.repository.ArchivoRepository;
import com.jaha.filecontrol.service.ArchivoService;

@Service("archivoService")
public class ArchivoServiceImpl implements ArchivoService{
	@Autowired
	@Qualifier("archivoRepository")
	public ArchivoRepository archivoRepository;

	@Override
	public List<Archivo> findByNombreLikeOrTelefonoLike(String nombre,String telefono) {
		// TODO Auto-generated method stub
		return archivoRepository.findByNombreLikeOrTelefonoLike(nombre,telefono);
	}

	@Override
	public List<Archivo> findByNombreContainingOrCedulaContaining(String nombre,String cedula) {
		
		return archivoRepository.findByNombreContainingOrCedulaContaining(nombre,cedula);
	}

	@Override
	public Archivo saveFile(Archivo archivo) {
		archivo.setNombre(archivo.getNombre().toUpperCase());
		return archivoRepository.save(archivo);
	}

	@Override
	public Archivo updateFile(Archivo archivo) {
		archivo.setNombre(archivo.getNombre().toUpperCase());
		return archivoRepository.save(archivo);
	}

	@Override
	public int removeFile(int id) {
		int result=0;
		if(archivoRepository.findById(id).getId()!=0){
			archivoRepository.delete(id);
			result=1;
		}
		
		return result;
	}

	@Override
	public Archivo findById(int id) {
		
		return archivoRepository.findById(id);
	}

	@Override
	public List<Archivo> findAll() {
		
		return archivoRepository.findAll();
	}

	@Override
	public Page<Archivo> findByNombreContainingOrCedulaContainingOrTelefonoContaining(String nombre, String cedula,
			String telefono,Pageable pageable) {
		
		return archivoRepository.findByNombreContainingOrCedulaContainingOrTelefonoContaining(nombre, cedula, telefono,pageable);
	}

	@Override
	public Page<Archivo> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return archivoRepository.findAll(pageable);
	}
	
}
