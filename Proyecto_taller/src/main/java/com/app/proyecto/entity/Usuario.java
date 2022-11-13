package com.app.proyecto.entity;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

	@Entity
	@Table (name = "tb_usuario")
	public class Usuario{
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="Id_usuario")
		private int id_usuario;
		
		@Column(name="Nombre_u")
		private String nombre_u; 
		
		@Column(name="Apellidos_u")
		private String apellidos_u; 
		
		@Column(name="Email")
		private String email;
		
		@Column(name="Pais")
		private String pais;
		
		@Column(name="Fecha_nac")
		private Date fecha_nac;
		
		@Column(name="Password")
		private String password;
		
		@OneToMany (mappedBy = "usuario", fetch = FetchType.EAGER)
		private List <Playlist> playlist;

		public Usuario(int id_usuario, String nombre_u, String apellidos_u, String email, String pais, Date fecha_nac,
				String password) {
			super();
			this.id_usuario = id_usuario;
			this.nombre_u = nombre_u;
			this.apellidos_u = apellidos_u;
			this.email = email;
			this.pais = pais;
			this.fecha_nac = fecha_nac;
			this.password = password;
		}

		public Usuario() {
			super();
		}

		public int getId_usuario() {
			return id_usuario;
		}

		public void setId_usuario(int id_usuario) {
			this.id_usuario = id_usuario;
		}

		public String getNombre_u() {
			return nombre_u;
		}

		public void setNombre_u(String nombre_u) {
			this.nombre_u = nombre_u;
		}

		public String getApellidos_u() {
			return apellidos_u;
		}

		public void setApellidos_u(String apellidos_u) {
			this.apellidos_u = apellidos_u;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

		public Date getFecha_nac() {
			return fecha_nac;
		}

		public void setFecha_nac(Date fecha_nac) {
			this.fecha_nac = fecha_nac;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	
	
		
	}