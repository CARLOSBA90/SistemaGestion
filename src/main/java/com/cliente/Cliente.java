package com.cliente;

public class Cliente {
	
	public Cliente(int cod_cliente, String nombre, String apellido, int dni, int telefono, String direccion,
			String correo) {
		this.cod_cliente = cod_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
	}
	
	public Cliente(String nombre, String apellido, int dni, int telefono, String direccion, String correo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
	}
	
	public Cliente(int cod_cliente, String nombre, String apellido) {
		
		this.cod_cliente = cod_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		
	}

	public int getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	

	@Override
	public String toString() {
		return "Cliente [cod_cliente=" + cod_cliente + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", telefono=" + telefono + ", direccion=" + direccion + ", correo=" + correo + "]";
	}

	private int cod_cliente;

	private String nombre;
	
	private String apellido;
	
	private int dni;
	
	private int telefono;
	
	private String direccion;
	
	private String correo;

}
