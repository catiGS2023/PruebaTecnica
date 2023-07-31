package com.prueba.pruebatecnica.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personaje {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	 	private String name;
	 	@Column(columnDefinition = "TEXT")
		private String description;
		private String urlImagen;
		private String idcomic;
		@Column(columnDefinition = "TEXT")
		private String namecomic;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getUrlImagen() {
			return urlImagen;
		}
		public void setUrlImagen(String urlImagen) {
			this.urlImagen = urlImagen;
		}
		public String getIdcomic() {
			return idcomic;
		}
		public void setIdcomic(String idcomic) {
			this.idcomic = idcomic;
		}
		public String getNamecomic() {
			return namecomic;
		}
		public void setNamecomic(String namecomic) {
			this.namecomic = namecomic;
		}
		@Override
		public String toString() {
			return "Personaje [id=" + id + ", name=" + name + ", description=" + description + ", urlImagen="
					+ urlImagen + ", idcomic=" + idcomic + ", namecomic=" + namecomic + ", getId()=" + getId()
					+ ", getName()=" + getName() + ", getDescription()=" + getDescription() + ", getUrlImagen()="
					+ getUrlImagen() + ", getIdcomic()=" + getIdcomic() + ", getNamecomic()=" + getNamecomic()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
					+ "]";
		}
		
}
