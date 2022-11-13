package com.app.proyecto.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cancion")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_cancion")
    private int idCancion;

    @Column(name = "Nombrecancion")
    private String nombreCancion;

    @Column(name = "Anio")
    private int anio;

    @Column(name = "Genero")
    private String genero;

    @Column(name = "Duracion")
    private int duracion;
    
    @ManyToMany(mappedBy = "canciones")
	private List<Album> albums;
    
	public Cancion() {
		super();
	}

	public Cancion(int idCancion, String nombreCancion, int anio, String genero, int duracion, List<Album> albums) {
		super();
		this.idCancion = idCancion;
		this.nombreCancion = nombreCancion;
		this.anio = anio;
		this.genero = genero;
		this.duracion = duracion;
		this.albums = albums;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}

	public String getNombreCancion() {
		return nombreCancion;
	}

	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}

	public int getAnio() {
		return anio; 
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}   
}