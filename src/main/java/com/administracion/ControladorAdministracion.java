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
			
			default:
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
		case "editar":
			
			try {
				editarUsuario(request,response);
			} catch (SQLException | ServletException | IOException e) {
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


	
	private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		
		Usuario tempUsuario = new Usuario(Integer.parseInt(request.getParameter("id")),request.getParameter("usuario"),request.getParameter("correo"),request.getParameter("contrasena"),Integer.parseInt(request.getParameter("nivel")));
		
		boolean ok = modelo.editar(tempUsuario);
		
		
		String frase = (ok)? "edicion_ok" : "edicion_no_ok";
		

	    	request.setAttribute(frase, true);
			
	        RequestDispatcher miDispatcher=request.getRequestDispatcher("/ControladorAdministracion?instruccion=listar");
			
			miDispatcher.forward(request,response);
		
		
	}

	private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			
		int id = Integer.parseInt(request.getParameter("codigo"));
		
		int idSesion = Integer.parseInt(request.getParameter("codSesion"));
		
		boolean mismoID = (id==idSesion)? true: false;
		
		boolean eliminacion = modelo.eliminar(id);
		
		String frase = (eliminacion)? "eliminado" : "no_eliminado";
		
		if(mismoID && frase.equals("eliminado") ) {
			
			response.sendRedirect("/ControladorSesion?instruccion=cerrar");
			
		}else {
		
		request.setAttribute(frase, true);
		
        RequestDispatcher miDispatcher=request.getRequestDispatcher("/ControladorAdministracion?instruccion=listar");
		
		miDispatcher.forward(request,response);
		
		      }
		
		
		
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