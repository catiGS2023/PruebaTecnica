package com.prueba.pruebatecnica.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.prueba.pruebatecnica.service.ObtenerDatosApiService;
import com.prueba.pruebatecnica.service.PersonajeService;

@RestController
@RequestMapping(
		value = "/pruebatecnica")
public class PruebaTecnicaController {
	@Autowired
	ObtenerDatosApiService data;
	@Autowired
	PersonajeService person;
	
	@GetMapping(value="/cargarDatosMarvel")
	public ResponseEntity<String> getPersonajesDataFromApi() throws  JsonProcessingException {   	
        return data.getPersonajesFromApi();
    }  
	
	@GetMapping(value="/consultarPersonajes")
	public void getPersonajesAll() {
		 person.consultarPersonajes();
	}
	@GetMapping(value="/consultarPersonajesyComic")
	public void getPersonajesComicAll() {
		 person.consultarPersonajesyComicsBBDD();
	}
	

}