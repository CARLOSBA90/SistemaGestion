package com.sesion;

import java.sql.Date;

public class Usuario {
	
	public Usuario(String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	
	public Usuario(String usuario, String contrasena, String email, Date fecha) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.fecha = fecha;
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
	
	
	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasena=" + contrasena + ", email=" + email + ", fecha=" + fecha
				+ "]";
	}


	private String usuario;
	private String contrasena;
	private String email;
	private Date   fecha;
}
