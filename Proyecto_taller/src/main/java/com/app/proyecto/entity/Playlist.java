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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
		@JoinTable(
		        name = "relacion_playlist_cancion",
		        joinColumns = @JoinColumn(name = "Id_playlist", nullable = false),
		        inverseJoinColumns = @JoinColumn(name="Id_cancion", nullable = false)
		    )
		@ManyToMany(cascade = CascadeType.ALL)
		private List<Cancion> canciones;
		
		public void addCancion(Cancion canc){
	        if(this.canciones == null){
	            this.canciones = new ArrayList<>();
	        }
	        
	        this.canciones.add(canc);
	    }
		
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



}