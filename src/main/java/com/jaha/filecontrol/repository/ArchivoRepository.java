package com.jaha.filecontrol.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jaha.filecontrol.entity.Archivo;

@Transactional
@Repository("archivoRepository")
public interface ArchivoRepository extends JpaRepository<Archivo, Serializable> {
	public abstract List<Archivo>findByNombreLikeOrTelefonoLike(String nombre, String telefono);
	public abstract List<Archivo>findByNombreContainingOrCedulaContaining(String nombre,String cedula);
	public abstract Archivo findById(int id); 
	public abstract List<Archivo>findByNombreContainingOrCedulaContainingOrTelefonoContaining(String nombre,String cedula,String telefono);
	
}
