package com.pedido;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cliente.Cliente;
import com.producto.Producto;

/**
 * Servlet implementation class ControladorPedido
 */
@WebServlet("/ControladorPedido")
public class ControladorPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloPedido modelo;
	
	public ControladorPedido() {
		
		modelo = new ModeloPedido();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String comando=request.getParameter("instruccion");
		
		if(comando==null) comando="listar";
		
		switch(comando) {
		
		case "listar": 
			ObtenerPedidos(request,response);
			break;
		case "nuevo":
			NuevoPedido(request,response);
			break;
		case "insertarBBDD":
			try {
				InsertarBBDD(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		default:
			
			
		
		}
		
		
	}

	
	private void InsertarBBDD(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		
		String nombre = request.getParameter("cliente");
		
		String forma = request.getParameter("forma");
		
		String orden = request.getParameter("salida");
		
		List[] lista = new List[2];
		
		lista = OrdenPedido.productos(orden);
	
		modelo.nuevoPedido(nombre,forma,lista[0],lista[1]);
		
		ObtenerPedidos(request,response);
	}

	private void NuevoPedido(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<Cliente> clientes;
		List<Producto> productos;
		
      
		try {
			
			clientes=modelo.getClientes();
			
			productos=modelo.getProductos();
			
			request.setAttribute("LISTACLIENTES",clientes);
			
			request.setAttribute("LISTAPRODUCTOS",productos);
			
			RequestDispatcher miDispatcher=request.getRequestDispatcher("pedidos/nuevoPedido.jsp");
			
			miDispatcher.forward(request,response);
			
		}catch(Exception e) {
			e.printStackTrace();	
		}
		
		
	}

	private void ObtenerPedidos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		List<Pedido> pedidos;
		
		try {
			
			pedidos=modelo.getPedidos();
			
			request.setAttribute("LISTAPEDIDOS",pedidos);
			
			RequestDispatcher miDispatcher=request.getRequestDispatcher("pedidos/ListaPedidos.jsp");
			
			miDispatcher.forward(request,response);
			
		}catch(Exception e) {
			e.printStackTrace();	
		}
		
		
	}

}
