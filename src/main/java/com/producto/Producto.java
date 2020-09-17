package com.producto;

public class Producto {
	
	public Producto(int cod_calzado, String tipo, String nombre, double precio, String fabricante, int talla,
			int stock) {
		this.cod_calzado = cod_calzado;
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.fabricante = fabricante;
		this.talla = talla;
		this.stock = stock;
	}
	
	
	public Producto(String tipo, String nombre, double precio, String fabricante, int talla, int stock) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.fabricante = fabricante;
		this.talla = talla;
		this.stock = stock;
	}
	


	public int getCod_calzado() {
		return cod_calzado;
	}
	public void setCod_calzado(int cod_calzado) {
		this.cod_calzado = cod_calzado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public int getTalla() {
		return talla;
	}
	public void setTalla(int talla) {
		this.talla = talla;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	@Override
	public String toString() {
		return "Producto [cod_calzado=" + cod_calzado + ", tipo=" + tipo + ", nombre=" + nombre + ", precio=" + precio
				+ ", fabricante=" + fabricante + ", talla=" + talla + ", stock=" + stock + "]";
	}


	private int cod_calzado;
	private String tipo;
	private String nombre;
	private double precio;
	private String fabricante;
	private int talla;
	private int stock;
}
