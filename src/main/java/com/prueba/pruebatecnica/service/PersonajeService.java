package com.prueba.pruebatecnica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.prueba.pruebatecnica.entity.Personaje;
import com.prueba.pruebatecnica.repository.PersonajeRepository;



@Service
public class PersonajeService {

	@Autowired
	private PersonajeRepository personajeRepo;
	
	
	public void consultarPersonajes() {
		// Consulta de los personajes. Sólo su info, id, nombre, descripción y url de la
		// imagen del personaje.
		Iterable<Personaje> personajes= personajeRepo.findAll();
		System.out.println("Consulta de todos los personajes:\n ");
		personajes.forEach((personaj)->{
		
			System.out.println("Id del personaje: " + personaj.getId()+"\n");
			System.out.println("Nombre del personaje: " + personaj.getName()+"\n");
			System.out.println("Descripción del personaje: " + personaj.getDescription()+"\n");
			System.out.println("Url imagen del personaje: " + personaj.getUrlImagen()+"\n");
		});
		
	}
	
	public void modificarPersonaje(Long id, String nombre, String direccion) {
		Personaje personajeModi=getPersonajeById(id);
		personajeModi.setName(nombre);
		personajeModi.setDescription(direccion);
		personajeRepo.save(personajeModi);
	}

	public Personaje getPersonajeById(Long id) {
		return personajeRepo.findById(id).orElse(null);
	}

	public Personaje createPersonaje(Personaje personaje) {
		return personajeRepo.save(personaje);
	}

	public void deletePersonaje(Long id) {
		personajeRepo.deleteById(id);
	}
	
	public void consultarPersonajesyComicsBBDD() {
		// Consulta de los personajes y comics almacenados en bbdd. 

		Iterable<Personaje> personajes= personajeRepo.findAll();
		System.out.println("Consulta de todos los personajes:\n ");
		personajes.forEach((personaj)->{
		
			System.out.println("Id del personaje: " + personaj.getId()+"\n");
			System.out.println("Nombre del personaje: " + personaj.getName()+"\n");
			System.out.println("Descripción del personaje: " + personaj.getDescription()+"\n");
			System.out.println("Url imagen del personaje: " + personaj.getUrlImagen()+"\n");
			System.out.println("Id del primer comic: " + personaj.getIdcomic()+"\n");
			System.out.println("Nombre del primer comic: " + personaj.getNamecomic()+"\n");
			
			
		});
		
	}	
		
	
}
