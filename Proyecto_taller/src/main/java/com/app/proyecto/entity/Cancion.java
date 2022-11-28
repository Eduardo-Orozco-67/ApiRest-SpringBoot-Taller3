package com.app.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "canciones1")
	@JsonIgnore
	private Set<Album> albums = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "canciones")
	@JsonIgnore
	private Set<Playlist> playlists= new HashSet<>();

	public Cancion() {
		super();
	}

	public Cancion(int idCancion, String nombreCancion, int anio, String genero, int duracion) {
		super();
		this.idCancion = idCancion;
		this.nombreCancion = nombreCancion;
		this.anio = anio;
		this.genero = genero;
		this.duracion = duracion;
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

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}
}