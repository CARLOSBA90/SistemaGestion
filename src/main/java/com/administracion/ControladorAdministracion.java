package com.administracion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sesion.Usuario;

/**
 * Servlet implementation class ControladorInicio
 */
@WebServlet("/ControladorAdministracion")
public class ControladorAdministracion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloAdministracion modelo;
	
	public ControladorAdministracion() {
		
		modelo = new ModeloAdministracion();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
			try {
				listarUsuarios(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			

		
	}



	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List<Usuario> usuarios = new ArrayList<>();
		
		usuarios = modelo.listarUsuarios();
		
		response.sendRedirect("/administracion/usuarios.jsp");
		
	}
	
}