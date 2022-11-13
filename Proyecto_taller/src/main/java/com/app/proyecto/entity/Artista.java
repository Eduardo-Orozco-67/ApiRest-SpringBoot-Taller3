package com.app.proyecto.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="tb_artista")
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id_artista")
	private int id_artista;
	
	@OneToMany (mappedBy = "artista", fetch = FetchType.EAGER)
	private List <Album> album;
	
	@Column(name="Nombre_art")
	private String nombreArtista;
	
	@Column(name="Edad")
	private int edad;
	
	@Column(name="Genero")
	private String genero;
	
	@Column(name="Popularidad")
	private float popularidad;
	
	@Column(name="Facebook_url")
	private String facebook_url;
	
	@Column(name="Imagen_url")
	private String imagen_url;
	
	
	public Artista()
	{
		super();
	}
	
	
	public Artista(int id_artista, String nombreArtista, int edad, String genero, float popularidad
	,String facebook_url, String imagen_url) 
	{
		super();
		this.id_artista = id_artista;
		this.nombreArtista = nombreArtista;
		this.edad = edad;
		this.genero =genero;
		this.popularidad = popularidad;
		this.facebook_url = facebook_url;
		this.imagen_url = imagen_url;
	}
	
	
	
	public int getId_artista() {
		return id_artista;
	}
	public void setId_artista(int id_artista) {
		this.id_artista = id_artista;
	}
	public String getNombreArtista() {
		return nombreArtista;
	}
	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public double getPopularidad() {
		return popularidad;
	}
	public void setPopularidad(float popularidad) {
		this.popularidad = popularidad;
	}
	public String getFacebook_url() {
		return facebook_url;
	}
	public void setFacebook_url(String facebook_url) {
		this.facebook_url = facebook_url;
	}
	public String getImagen_url() {
		return imagen_url;
	}
	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}
	

}