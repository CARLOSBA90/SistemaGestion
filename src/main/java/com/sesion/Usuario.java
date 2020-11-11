package com.sesion;

import java.sql.Date;

public class Usuario {
	
	public Usuario(String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	
	public Usuario(String usuario, String contrasena, String email, Date fecha,int nivel) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.fecha = fecha;
		this.nivel = nivel;
	}
	
	public Usuario(int id, String usuario, String contrasena, String email, Date fecha,int nivel) {
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.fecha = fecha;
		this.nivel = nivel;
	}
	
	
	
	


	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getNivel() {
		return nivel;
	}


	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasena=" + contrasena + ", email=" + email + ", fecha=" + fecha
				+ ", nivel=" + nivel + "]";
	}

 



    private int id;
	private String usuario;
	private String contrasena;
	private String email;
	private Date   fecha;
	private int nivel;
}
