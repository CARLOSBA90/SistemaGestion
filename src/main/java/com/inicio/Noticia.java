package com.inicio;

import java.sql.Date;

public class Noticia {
	
	public Noticia(String titulo, String texto) {
		this.titulo = titulo;
		this.texto = texto;
	}
	public Noticia(Date fecha, String titulo, String texto) {
		this.fecha = fecha;
		this.titulo = titulo;
		this.texto = texto;
	}
	public Noticia(Date fecha, String titulo, String texto, int cod_noticia) {
		this.fecha = fecha;
		this.titulo = titulo;
		this.texto = texto;
		this.cod_noticia = cod_noticia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getCod_noticia() {
		return cod_noticia;
	}
	public void setCod_noticia(int cod_noticia) {
		this.cod_noticia = cod_noticia;
	}
	private Date fecha;
	private String titulo;
	private String texto;
	private int cod_noticia;
}
