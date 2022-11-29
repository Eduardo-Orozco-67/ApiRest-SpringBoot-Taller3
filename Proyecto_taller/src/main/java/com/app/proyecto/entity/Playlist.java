package com.app.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table (name = "tb_playlist")
public class Playlist{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id_playlist")
	private int id_playlist;

	@Column(name="Nombre")
	private String nombre;
	
	@Column(name="Fecha_creacion")
	private Date fecha_creacion;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn (name = "Id_usuario", referencedColumnName = "Id_usuario")
	private Usuario usuario;
	
	//Utiliza join table para hacer larelacion muchos a muchos y generar la tabla
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "relacion_playlist_cancion",
			joinColumns = { @JoinColumn(name = "Id_playlist") },
			inverseJoinColumns = { @JoinColumn(name = "Id_cancion") })
	private Set<Cancion> canciones = new HashSet<>();

	public Playlist(int id_playlist, String nombre, int id_usuario, Date fecha_creacion) {
		super();
		this.id_playlist = id_playlist;
		this.nombre = nombre;
		this.fecha_creacion = fecha_creacion;
	}

	public Playlist() {
		super();
	}

	public int getId_playlist() {
		return id_playlist;
	}

	public void setId_playlist(int id_playlist) {
		this.id_playlist = id_playlist;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(Set<Cancion> canciones) {
		this.canciones = canciones;
	}

	public void addCancion(Cancion tag) {
		this.canciones.add(tag);
		tag.getPlaylists().add(this);
	}

	public void removeCancion(Integer tagId) {
		Cancion tag = this.canciones.stream().filter(t -> t.getIdCancion() == tagId).findFirst().orElse(null);
		if (tag != null) {
			this.canciones.remove(tag);
			tag.getPlaylists().remove(this);
		}
	}

	public void addCancion2(Cancion tag2){
		this.canciones.add(tag2);
		tag2.getPlaylists().add(this);
	}

}