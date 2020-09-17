package com.producto;

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
 * Servlet implementation class ControladorProducto
 */
@WebServlet("/ControladorProducto")
public class ControladorProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloProducto modelo;
	
	
	public ControladorProducto() {
		
		modelo= new ModeloProducto();
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String comando = request.getParameter("instruccion");
		
		if(comando==null) comando="listar";
		
		switch(comando) {
		
		case "listar": 
			obtenerProductos(request,response);
		     break;
			
		case "eliminar":
			eliminar(request,response);
			break;
		
		case "modificar":
			modificar(request,response);
			break;
		
		
		default:
			obtenerProductos(request,response);
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String comando = request.getParameter("instruccion");
		
		if(comando==null) comando="listar";
		
		switch(comando) {
		
		case "insertarBBDD":
			insertarBBDD(request,response);
			break;
	    
		case "actualizar":
			actualizar(request,response);
			break;
			
			default:
				obtenerProductos(request,response);
		        break;
		}
		
		
	}


	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int codigo=Integer.parseInt(request.getParameter("codigoProducto"));
		String tipo=request.getParameter("tipo");
		String nombre=request.getParameter("nombre");
		double precio=Double.parseDouble(request.getParameter("precio"));
		String fabricante=request.getParameter("fabricante");
		int talla=Integer.parseInt(request.getParameter("talla"));
		int stock=Integer.parseInt(request.getParameter("stock"));
		
		
		Producto temp = new Producto(codigo,tipo,nombre,precio,fabricante,talla,stock);
	     
		modelo.actualizar(temp);
		
		obtenerProductos(request,response);
		
	}



	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int codigo=Integer.parseInt(request.getParameter("Codigo"));
		
		modelo.eliminar(codigo);
		
		obtenerProductos(request,response);
		
	}



	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
        List<Producto> productos;
		
	try {
		
		productos=modelo.getProductos();
		
		request.setAttribute("LISTAPRODUCTOS",productos);
		
		RequestDispatcher miDispatcher=request.getRequestDispatcher("productos/modificarProducto.jsp");
		
		miDispatcher.forward(request,response);
		
	}catch(Exception e) {
		e.printStackTrace();
		
		
	                    }
	
	}



	private void insertarBBDD(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		///RECIBIR PARAMETROS POR EL REQUEST
		
		String tipo=request.getParameter("tipo");
		String nombre=request.getParameter("nombre");
		double precio=Double.parseDouble(request.getParameter("precio"));
		String fabricante=request.getParameter("fabricante");
		int talla=Integer.parseInt(request.getParameter("talla"));
		int stock=Integer.parseInt(request.getParameter("stock"));
		
		//AGREGAR PARAMETROS AL OBJETO
		
		Producto temp= new Producto(tipo,nombre,precio,fabricante,talla,stock);
		
		//ENVIAR OBJETO A LA BBDD
		
		modelo.insertar(temp);
		
		obtenerProductos(request,response);
	}



	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<Producto> productos;
		
		try {
			
			productos=modelo.getProductos();
			
			request.setAttribute("LISTAPRODUCTOS", productos);
			
			RequestDispatcher miDispatcher=request.getRequestDispatcher("productos/ListaProductos.jsp");
			
			miDispatcher.forward(request,response);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
