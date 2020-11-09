package com.administracion;
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
@WebServlet("/ControladorAdministracion")
public class ControladorAdministracion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloAdministracion modelo;
	
	public ControladorAdministracion() {
		
		modelo = new ModeloAdministracion();
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
			listarUsuarios(request,response);
		
	}



	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/administracion/usuarios.jsp");
	}
	
}