package com.sesion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControladorInicio
 */
@WebServlet("/ControladorSesion")
public class ControladorSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloSesion modelo;
	
	public ControladorSesion() {
		
		modelo = new ModeloSesion();
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String comando = request.getParameter("instruccion");
		
		if(comando==null) comando="nulo";
		
		switch(comando) {
		
		case "nulo": 
			request.getRequestDispatcher("/sesion/login.jsp").forward(request, response); 
			break;
		
		case "login":
			try {
				loguear(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		default:

		}
		
		
		
		
	}



	private void loguear(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		
		Usuario usuario = new Usuario(request.getParameter("usuario"),request.getParameter("contrasena"));
		
		int nivel = modelo.autenticacion(usuario);
		
		switch(nivel) {
		 
		case 0: 
			
			request.setAttribute("mensaje", true);
			
            RequestDispatcher miDispatcher=request.getRequestDispatcher("/sesion/login.jsp");
			
			miDispatcher.forward(request,response);
			
			break;
		
		default:
			
			
			HttpSession sesion = request.getSession();
			
			sesion.setAttribute("usuario", usuario.getUsuario());
			
			sesion.setAttribute("nivel", nivel);
			
			response.sendRedirect("/ControladorInicio");
			
		
		
		}
	}








}
