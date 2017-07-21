package com.jaha.filecontrol.service;

import java.util.List;

import com.jaha.filecontrol.entity.Archivo;

public interface ArchivoService {
	public abstract List<Archivo>findByNombreLikeOrTelefonoLike(String nombre,String telefono);
	public abstract List<Archivo>findByNombreContainingOrCedulaContaining(String nombre, String cedula);
	public abstract Archivo saveFile(Archivo archivo);
	public abstract Archivo updateFile(Archivo archivo);
	public abstract int removeFile(int id);
	public abstract Archivo findById(int id);
	public abstract List<Archivo> findAll();
	public abstract List<Archivo>findByNombreContainingOrCedulaContainingOrTelefonoContaining(String nombre,String cedula,String telefono);
}
