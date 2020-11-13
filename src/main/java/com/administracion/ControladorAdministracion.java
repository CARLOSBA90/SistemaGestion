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
		
		String comando = request.getParameter("instruccion");
		
		
		if(comando==null) comando="listar";
		
		switch(comando) {
		
		
		case "listar":
			try {
				listarUsuarios(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case "eliminar":
			
			eliminarUsuario(request,response);
			
			
			break;
			
			
			default:
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			

		
	}


	
	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("nivel")!="3") response.sendRedirect("../sesion/denegado.jsp"); // Evasion de vunerabilidad para usuarios diferentes de nivel 3
		
		int id = Integer.parseInt(request.getParameter("codigo"));
		
		int idSesion = Integer.parseInt(request.getParameter("codSesion"));
		
		boolean mismoID = (id==idSesion)? true: false;
		
		boolean eliminacion = modelo.eliminar(id);
		
		String frase = (eliminacion)? "eliminado" : "no_eliminado";
		
		if(mismoID && frase=="eliminado") {
			
			request.setAttribute("cuentaEliminada", true);
			
			response.sendRedirect("/ControladorSesion?instruccion=cerrar");
			
		}
		
		request.setAttribute(frase, true);
		
        RequestDispatcher miDispatcher=request.getRequestDispatcher("/ControladorAdministracion?instruccion=listar");
		
		miDispatcher.forward(request,response);
		
		
		
		
		
	}

	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List<Usuario> usuarios = new ArrayList<>();
		
		usuarios = modelo.listarUsuarios();
		
		request.setAttribute("USUARIOS",usuarios);
		
		RequestDispatcher miDispatcher=request.getRequestDispatcher("administracion/usuarios.jsp");
		
		miDispatcher.forward(request,response);
		
	}
	
}