package com.prueba.pruebatecnica.service;

import java.util.Iterator;

import java.util.Map.Entry;

import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.prueba.pruebatecnica.entity.Personaje;

import com.prueba.pruebatecnica.repository.PersonajeRepository;
import com.prueba.pruebatecnica.utils.Encrypt;



@Service
public class ObtenerDatosApiService {
	Logger log = Logger.getLogger("ObtenerDatosApiService");
	@Autowired
	private PersonajeRepository personajeRepo;
	private Personaje personaje = new Personaje();

	public ResponseEntity<String> getPersonajesFromApi() throws  JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		Encrypt security = new Encrypt();

		String url = "http://gateway.marvel.com/v1/public/characters";
		url = url.concat("?apikey=<<ponervuestraapikey&ts=1");
		//md5(ts+privateKey+publicKey)
		
		String hash = security.getMD5("<<ts+privateKey+publicKey>>");
		
		url = url.concat("&hash=").concat(hash);
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(responseEntity.getBody());
		if (root.isObject()) {
			 appendNodeSave(root); 
		 }		
		return responseEntity;

	}

	
	private void appendNodeSave(JsonNode node) {
					
		Iterator<Entry<String, JsonNode>> fields = node.fields();

		while (fields.hasNext()) {
			Entry<String, JsonNode> jsonField = fields.next();
			log.log(Level.INFO," DATA: key-value {0} ", jsonField.getKey() + "-" + jsonField.getValue());	
			JsonNode datanodes=jsonField.getValue();
				Iterator<Entry<String, JsonNode>> fields1 = datanodes.fields();

				while (fields1.hasNext()) {
					Entry<String, JsonNode> jsonField1 = fields1.next();
					log.log(Level.INFO, "DATA VALUES: Valor key-value {0} ", jsonField1.getKey() + "-" + jsonField1.getValue());
					
						for (JsonNode arrayItem : jsonField1.getValue()) {							
							Iterator<Entry<String, JsonNode>> fieldsResult = arrayItem.fields();
							while (fieldsResult.hasNext()) {
								Entry<String, JsonNode> jsonFieldResults = fieldsResult.next();
								log.log(Level.INFO, "RESULTS: Valor key-value {0} ", jsonFieldResults.getKey() + "-" + jsonFieldResults.getValue());
								if (jsonFieldResults.getValue()!=null&&jsonFieldResults.getKey().equals("name"))
									this.personaje=new Personaje();
								switch (jsonFieldResults.getKey()) {
									case "id":{
										//this.personaje.setId(jsonField.getValue().asLong());
										log.log(Level.INFO, "PERSONAJE: Valor key-value {0} ", jsonFieldResults.getKey() + "-" + jsonFieldResults.getValue());
										break;}
									case "name":{
										this.personaje.setName(jsonFieldResults.getValue().asText());
										log.log(Level.INFO, "PERSONAJE: Valor key-value {0} ", jsonFieldResults.getKey() + "-" + jsonFieldResults.getValue());
										break;}
									case "description":{
										this.personaje.setDescription(jsonFieldResults.getValue().asText());
										log.log(Level.INFO, "PERSONAJE: Valor key-value {0} ", jsonFieldResults.getKey() + "-" + jsonFieldResults.getValue());
										break;}
									case "thumbnail":{
										JsonNode thumbnail=jsonFieldResults.getValue();	
										Iterator<Entry<String, JsonNode>> fieldsThumbnail = thumbnail.fields();
										String path="";
										while (fieldsThumbnail.hasNext()) {
											Entry<String, JsonNode> jsonFieldThumbnail = fieldsThumbnail.next();
											log.log(Level.INFO, "THUMBNAIL: Valor key-value {0} ", jsonFieldThumbnail.getKey() + "-" + jsonFieldThumbnail.getValue());
											path+=jsonFieldThumbnail.getValue().asText();										
										}
										this.personaje.setUrlImagen(path);									
									}
									break;
									case "comics":{
										JsonNode comics=jsonFieldResults.getValue();	
										Iterator<Entry<String, JsonNode>> fieldsComics = comics.fields();
										int comicIt=0;
										String namePrimerComic="";
										String idPrimerComic="";
										while (fieldsComics.hasNext()) {
											Entry<String, JsonNode> jsonFieldComics = fieldsComics.next();
											log.log(Level.INFO, "COMICS: Valor key-value {0} ", jsonFieldComics.getKey() + "-" + jsonFieldComics.getValue());
											for (JsonNode comicItem : jsonFieldComics.getValue()) {							
												Iterator<Entry<String, JsonNode>> fieldsComicItem = comicItem.fields();
												while (fieldsComicItem.hasNext()) {
													
													Entry<String, JsonNode> jsonFieldComicItem = fieldsComicItem.next();
													log.log(Level.INFO, "COMIC ITEMS: Valor key-value {0} ", jsonFieldComicItem.getKey() + "-" + jsonFieldComicItem.getValue());
													
													if (comicIt==0) {
														switch (jsonFieldComicItem.getKey()) {
															case "resourceURI":{
															
																String uriItem=jsonFieldComicItem.getValue().asText();
																String[] arrayItems=uriItem.split("/");
																idPrimerComic=arrayItems[arrayItems.length-1];															
																break;														
															}
															case "name":{															
																namePrimerComic=jsonFieldComicItem.getValue().asText();															
																comicIt++;
																break;
															}
															default:
														}
														
													}
													
												}
											}	
										}
										log.log(Level.INFO, "COMIC ITEMS: Primer valor Valor key-value {0} ", idPrimerComic + "-" + namePrimerComic);
										this.personaje.setIdcomic(idPrimerComic);
										this.personaje.setNamecomic(namePrimerComic);
										break;
									}
								}
								if (this.personaje.getName()!=null)
									personajeRepo.save(this.personaje);
								
								}
							}
						}
				}
			}			
		

	}	

	

