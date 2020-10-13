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

/**
 * Servlet implementation class ControladorInicio
 */
@WebServlet("/ControladorInicio")
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
			request.getRequestDispatcher("sesion/login.jsp").forward(request, response); 
			break;
		
		case "login":
			loguear(request,response);

			
		default:

		}
		
		
		
		
	}



	private void loguear(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}








}
