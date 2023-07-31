package com.prueba.pruebatecnica.repository;

import org.springframework.data.repository.CrudRepository;

import com.prueba.pruebatecnica.entity.Personaje;



public interface PersonajeRepository extends CrudRepository < Personaje, Long > {
	
}
