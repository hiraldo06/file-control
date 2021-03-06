package com.jaha.filecontrol.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jaha.filecontrol.entity.Archivo;

public interface ArchivoService {
	public abstract List<Archivo>findByNombreLikeOrTelefonoLike(String nombre,String telefono);
	public abstract List<Archivo>findByNombreContainingOrCedulaContaining(String nombre, String cedula);
	public abstract Archivo saveFile(Archivo archivo);
	public abstract Archivo updateFile(Archivo archivo);
	public abstract int removeFile(int id);
	public abstract Archivo findById(int id);
	public abstract List<Archivo> findAll();
	public abstract Page<Archivo> findAll(Pageable pageable);
	public abstract Page<Archivo>findByNombreContainingOrCedulaContainingOrTelefonoContaining(String nombre,String cedula,String telefono,Pageable pageable);
}
