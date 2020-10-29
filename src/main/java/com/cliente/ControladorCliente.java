package com.cliente;

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
 * Servlet implementation class ControladorCliente
 */
@WebServlet("/ControladorCliente")
public class ControladorCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

     private ModeloCliente modeloCliente;
     
     public ControladorCliente() {
    	 
    	  modeloCliente = new ModeloCliente();
     }
 
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comando=request.getParameter("instruccion");
		
		if(comando==null) comando="listar";
		
		switch(comando) {
		
		case "eliminar":
			  eliminar(request,response);
			break;
		
		case "listar":
			obtenerClientes(request,response);
			break;
			
		case "modificar":
			modificar(request,response);
			break;
			
			default:
				obtenerClientes(request,response);
		        break;
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comando=request.getParameter("instruccion");
		
		
		switch(comando) {
			
		case "insertarBBDD":
			try {
				insertarCliente(request,response);
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			break;
			
		case "actualizar":
			try {
				actualizar(request,response);
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
			break;
		
			default:
				obtenerClientes(request,response);
		        break;
		}
		
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		int codigo=Integer.parseInt(request.getParameter("Codigo"));
		
		  modeloCliente.eliminar(codigo);
		  
		  response.sendRedirect("/ControladorCliente?instruccion=listar");
		  
	}



	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		
		///Recibir por parametros los datos del formulario
		
		  int codigo = Integer.parseInt(request.getParameter("codigoCliente"));
		  String nombre = request.getParameter("nombre");
		  String apellido = request.getParameter("apellido");
          int dni = Integer.parseInt(request.getParameter("dni"));		  
          int telefono = Integer.parseInt(request.getParameter("telefono"));	
          String direccion = request.getParameter("direccion");
          String correo = request.getParameter("correo");
		
		// Preparar objeto Cliente
		
          Cliente temp= new Cliente(codigo,nombre,apellido,dni,telefono,direccion,correo);
          
		// enviar objeto al ModeloCliente
          
          modeloCliente.actualizar(temp);
		
		//Poner Listado
		obtenerClientes(request,response);
		
	}





	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
              List<Cliente> clientes;
		
		try {
			
			clientes=modeloCliente.getClientes();
			
			request.setAttribute("LISTACLIENTES",clientes);
			
			RequestDispatcher miDispatcher=request.getRequestDispatcher("clientes/modificar.jsp");
			
			miDispatcher.forward(request,response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			
		}
		
	}


	private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		
		String nombre=request.getParameter("nombre");
		
		String apellido=request.getParameter("apellido");
		
	    int dni=Integer.parseInt(request.getParameter("dni"));
	    
	    int telefono=Integer.parseInt(request.getParameter("telefono"));
	   
	    String direccion=request.getParameter("direccion");
	    
	    String correo=request.getParameter("correo");
	    
	    Cliente nuevoCliente = new Cliente(nombre,apellido,dni,telefono,direccion,correo);
	    
	    modeloCliente.agregarNuevoCliente(nuevoCliente);
	    
	    response.sendRedirect("/ControladorCliente?instruccion=listar");
	    
	    
	    
	    
		
	}


	private void obtenerClientes(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<Cliente> clientes;
		
		try {
			
			clientes=modeloCliente.getClientes();
			
			request.setAttribute("LISTACLIENTES",clientes);
			
			RequestDispatcher miDispatcher=request.getRequestDispatcher("clientes/ListaClientes.jsp");
			
			miDispatcher.forward(request,response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			
		}
	}

	
	
}



