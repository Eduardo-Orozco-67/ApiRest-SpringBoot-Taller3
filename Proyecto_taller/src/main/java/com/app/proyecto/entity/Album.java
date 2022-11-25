package com.app.proyecto.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.proyecto.entity.Artista;

@Entity
@Table (name = "tb_album")

public class Album {
	@Id	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "Id_album")
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn (name = "id_artista", referencedColumnName = "id_artista")
	private Artista artista;
	
	//Utiliza join table para hacer larelacion muchos a muchos y generar la tabla 
	@JoinTable(
	        name = "relacion_album_cancion",
	        joinColumns = @JoinColumn(name = "id_Album", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="id_Cancion", nullable = false)
	    )
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Cancion> canciones;
	
	public void addCancion(Cancion canc){
        if(this.canciones == null){
            this.canciones = new ArrayList<>();
        }
        this.canciones.add(canc);
    }
	
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
		super();
	}

	public Album(int id, Artista artista, List<Cancion> canciones, String nombre_album, int lanzamiento,
			String productora, String tipo, String no_canciones, String imagen_url) {
		super();
		this.id = id;
		this.artista = artista;
		this.canciones = canciones;
		this.nombre_album = nombre_album;
		this.lanzamiento = lanzamiento;
		this.productora = productora;
		this.tipo = tipo;
		this.no_canciones = no_canciones;
		this.imagen_url = imagen_url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
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
	
}
