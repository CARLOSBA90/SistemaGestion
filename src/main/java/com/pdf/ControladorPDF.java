package com.pdf;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pedido.ModeloPedido;



/**
 * Servlet implementation class ControladorPedido
 */
@WebServlet("/ControladorPDF")
public class ControladorPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloPedido modelo;
	
	public ControladorPDF() {
		
		modelo = new ModeloPedido();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String comando=request.getParameter("instruccion");
		
		if(comando==null) comando="nulo";
		
		switch(comando) {
		
		case "facturar": 
			try {
				facturar(request,response);
			} catch (NumberFormatException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			
			
		
		}
		
		
	}

	private void facturar(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		
		/// Generar factura con todos los campos de la clase Pedido y CantidadPedido
		
		///Base de la generacion de PDF
		
		/// Se crear una clase con metodo para procesar la creacion del pdf
	
		
		PDF miPDF = new PDF();
		
		miPDF.crear(request,response);
		
		
	}

	





}
