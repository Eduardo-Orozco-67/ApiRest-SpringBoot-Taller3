package com.app.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table (name = "tb_album")

public class Album {
	@Id	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Id_album")
	private int idAlbum;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn (name = "id_artista", referencedColumnName = "id_artista")
	private Artista artista;
	
	//Utiliza join table para hacer larelacion muchos a muchos y generar la tabla
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "relacion_album_cancion",
			joinColumns = { @JoinColumn(name = "Id_album") },
			inverseJoinColumns = { @JoinColumn(name = "Id_cancion") })
	@JsonIgnore
	private Set<Cancion> canciones1 = new HashSet<>();

	@Column (name = "Nombre_album")
	private String nombre_album;
	
	@Column (name = "Lanzamiento")
	private int lanzamiento;
	
	@Column (name = "Productora")
	private String productora;
	
	@Column (name = "Tipo")
	private String tipo;
	
	@Column (name = "No_canciones")
	private String no_canciones;
	
	@Column (name = "Imagen_url")
	private String imagen_url;

	public Album() {
	}

	public Album(int idAlbum, Artista artista, Set<Cancion> canciones, String nombre_album, int lanzamiento, String productora, String tipo, String no_canciones, String imagen_url) {
		this.idAlbum = idAlbum;
		this.artista = artista;
		this.canciones1 = canciones;
		this.nombre_album = nombre_album;
		this.lanzamiento = lanzamiento;
		this.productora = productora;
		this.tipo = tipo;
		this.no_canciones = no_canciones;
		this.imagen_url = imagen_url;
	}

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int id) {
		this.idAlbum = id;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public String getNombre_album() {
		return nombre_album;
	}

	public void setNombre_album(String nombre_album) {
		this.nombre_album = nombre_album;
	}

	public int getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(int lanzamiento) {
		this.lanzamiento = lanzamiento;
	}

	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNo_canciones() {
		return no_canciones;
	}

	public void setNo_canciones(String no_canciones) {
		this.no_canciones = no_canciones;
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}

	public Set<Cancion> getCanciones() {
		return canciones1;
	}

	public void setCanciones(Set<Cancion> canciones) {
		this.canciones1 = canciones;
	}

	public void addCancion(Cancion tag) {
		this.canciones1.add(tag);
		tag.getAlbums().add(this);
	}

	public void removeCancion(Integer tagId) {
		Cancion tag = this.canciones1.stream().filter(t -> t.getIdCancion() == tagId).findFirst().orElse(null);
		if (tag != null) {
			this.canciones1.remove(tag);
			tag.getAlbums().remove(this);
		}
	}

}
