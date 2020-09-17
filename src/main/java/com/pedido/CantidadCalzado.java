package com.pedido;

public class CantidadCalzado {
	
	public CantidadCalzado(int cod_pedido_pro, int cod_pedido, int cod_producto, int cantidad, double precio,
			double total) {
		this.cod_pedido_pro = cod_pedido_pro;
		this.cod_pedido = cod_pedido;
		this.cod_producto = cod_producto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
	}
	
	public CantidadCalzado(int cod_pedido, int cod_producto, int cantidad, double precio, double total) {
		
		this.cod_pedido = cod_pedido;
		this.cod_producto = cod_producto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
	}




	public int getCod_pedido_pro() {
		return cod_pedido_pro;
	}
	public void setCod_pedido_pro(int cod_pedido_pro) {
		this.cod_pedido_pro = cod_pedido_pro;
	}
	public int getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(int cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
	public int getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(int cod_producto) {
		this.cod_producto = cod_producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
	@Override
	public String toString() {
		return "COD_PRODUCTO "+cod_producto+": "+cantidad+" U. * "+precio+"="+total+" ";
	}
	





	private int cod_pedido_pro;
	private int cod_pedido;
	private int cod_producto;
	private int cantidad;
	private double precio;
	private double total;
}
