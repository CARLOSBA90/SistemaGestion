package com.inicio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorInicio
 */
@WebServlet("/ControladorInicio")
public class ControladorInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloInicio modelo;
	
	public ControladorInicio() {
		
		modelo = new ModeloInicio();
		
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String comando = request.getParameter("instruccion");
		
		if(comando==null) comando="noticia";
		
		switch(comando) {
		
		case "noticia": 
			obtenerNoticias(request,response);
			break;
			
		case "tablero":
			obtenerTablero(request,response);
			break;
			
		case "nuevaNota":
		    try {
				nuevaNota(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    break;
			
		default:

		}
		
		
		
		
	}



	private void nuevaNota(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	
		String titulo = request.getParameter("titulo");
		
		String texto = request.getParameter("texto");
		
		Noticia temp = new Noticia(titulo,texto);
		
		modelo.agregarNuevaNota(temp);
		
		obtenerTablero(request,response);
		
		
	}



	private void obtenerTablero(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<Noticia> tablero; /// se utiliza la misma clase de Noticias ya que tiene el mismo formato
		
		
		try {
			tablero=modelo.getTablero();
			
			request.setAttribute("TABLERO",tablero);
			
			RequestDispatcher miDispatcher=request.getRequestDispatcher("inicio/tablero.jsp");
			
			miDispatcher.forward(request,response);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void obtenerNoticias(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<Noticia> noticias;
		
		try {
			noticias=modelo.getNoticias();
			
			request.setAttribute("NOTICIAS",noticias);
			
			RequestDispatcher miDispatcher=request.getRequestDispatcher("inicio/inicio.jsp");
			
			miDispatcher.forward(request,response);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
