package com.pedido;

import java.sql.Date;
import java.util.List;

public class Pedido {
	
   public Pedido(int cod_pedido, int cod_producto, String forma_pago, boolean enviado, Date fecha, double total,
			List<CantidadCalzado> cantidad,int cantidadCalzados) {
		this.cod_pedido = cod_pedido;
		this.cod_producto = cod_producto;
		this.forma_pago = forma_pago;
		this.enviado = enviado;
		this.fecha = fecha;
		this.total = total;
		this.cantidad = cantidad;
		this.cantidadCalzados = cantidadCalzados;
	}
   
   
   
   public Pedido(int cod_producto, String forma_pago, boolean enviado, Date fecha, double total,
		List<CantidadCalzado> cantidad, int cantidadCalzados) {
	this.cod_producto = cod_producto;
	this.forma_pago = forma_pago;
	this.enviado = enviado;
	this.fecha = fecha;
	this.total = total;
	this.cantidad = cantidad;
	this.cantidadCalzados = cantidadCalzados;
   }
   
   



public Pedido(int cod_pedido, int cod_producto, String forma_pago, boolean enviado, Date fecha, double total,
		List<CantidadCalzado> cantidad, int cantidadCalzados, String nombreApellido) {
	this.cod_pedido = cod_pedido;
	this.cod_producto = cod_producto;
	this.forma_pago = forma_pago;
	this.enviado = enviado;
	this.fecha = fecha;
	this.total = total;
	this.cantidad = cantidad;
	this.cantidadCalzados = cantidadCalzados;
	this.nombreApellido = nombreApellido;
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
	public String getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public boolean isEnviado() {
		return enviado;
	}
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<CantidadCalzado> getCantidad() {
		return cantidad;
	}
	public void setCantidad(List<CantidadCalzado> cantidad) {
		this.cantidad = cantidad;
	}
	

public int getCantidadCalzados() {
		return cantidadCalzados;
	}



	public void setCantidadCalzados(int cantidadCalzados) {
		this.cantidadCalzados = cantidadCalzados;
	}
	

public String getNombreApellido() {
		return nombreApellido;
	}



	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}



@Override
	public String toString() {
		return "Pedido [cod_pedido=" + cod_pedido + ", cod_cliente=" + cod_producto + ", forma_pago=" + forma_pago
				+ ", enviado=" + enviado + ", fecha=" + fecha + ", total=" + total + ", cantidad=" + cantidad
				+ ", cantidadCalzados=" + cantidadCalzados + "]";
	}









   private int cod_pedido;
   private int cod_producto;
   private String forma_pago;
   private boolean enviado;
   private Date fecha;
   private double total;
   private List<CantidadCalzado> cantidad;
   private int cantidadCalzados;
   private String nombreApellido;
}
