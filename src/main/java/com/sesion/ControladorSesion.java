package com.sesion;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
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
	
	private HttpSession sesion =null;
	
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
			break;
		case "registrar":
			
			try {
				registro(request,response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
			
		default:

		}
		
		
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String comando = request.getParameter("instruccion");
		
		if(comando==null) comando="nulo";
		
		switch(comando) {
		
		case "nulo": 
			request.getRequestDispatcher("/sesion/login.jsp").forward(request, response); 
			break;
			
		case "cerrar":
			
			sesion.invalidate();
			request.getRequestDispatcher("/sesion/login.jsp").forward(request, response); 
		
			break;
		
		default:
			
		}
		
		
		
	}



	private void registro(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		
		Usuario usuario = new Usuario(request.getParameter("usuario"),request.getParameter("contrasena"), request.getParameter("email"),new Date(Calendar.getInstance().getTime().getTime()),Integer.parseInt(request.getParameter("nivel")));
		
		boolean registro = modelo.registrar(usuario);

		
		if(registro) {
			
			request.setAttribute("registrado", true);
			
            RequestDispatcher miDispatcher=request.getRequestDispatcher("/sesion/login.jsp");
			
			miDispatcher.forward(request,response);
			
		}else {
			
			request.setAttribute("fallo", true);
			
            RequestDispatcher miDispatcher=request.getRequestDispatcher("/sesion/login.jsp");
			
			miDispatcher.forward(request,response);
			
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
			
			sesion = request.getSession();
			
			sesion.setAttribute("usuario", usuario.getUsuario());
			
			sesion.setAttribute("nivel", nivel);
			
			response.sendRedirect("/ControladorInicio");
			
			
		
		
		}
	}








}
