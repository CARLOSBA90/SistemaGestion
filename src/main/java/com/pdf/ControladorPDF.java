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
import com.cliente.Cliente;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pedido.ModeloPedido;
import com.producto.Producto;


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
			facturar(request,response);
			break;
			
		default:
			
			
		
		}
		
		
	}

	private void facturar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		/// Generar factura con todos los campos de la clase Pedido y CantidadPedido
		
		///Base de la generacion de PDF
		
		response.setContentType("application/pdf");
		
		OutputStream out = response.getOutputStream();
		
		try {


			try {
				
				Document documento = new Document();
				
				PdfWriter.getInstance(documento, out);
				
				documento.open();
				/*
				Paragraph parrafo = new Paragraph();
				
				Font fuente = new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD,BaseColor.BLACK);
				
				parrafo.add(new Phrase("Seccion en desarrollo! Codigo de pedido: "+ request.getParameter("Codigo")+" ", fuente));

				parrafo.setAlignment(Element.ALIGN_CENTER);
				
				parrafo.add(new Phrase(Chunk.NEWLINE));

				documento.add(parrafo);*/
				
				
				  float[] columnas = {1, 5, 5};
			        PdfPTable table = new PdfPTable(columnas);
			        table.setWidthPercentage(100);
			        table.getDefaultCell().setUseAscender(true);
			        table.getDefaultCell().setUseDescender(true);
			        Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
			        PdfPCell cell = new PdfPCell(new Phrase("FACTURA C", f));
			        cell.setBackgroundColor(GrayColor.GRAYBLACK);
			        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        table.addCell(cell);
			        table.getDefaultCell().setBackgroundColor(GrayColor.WHITE);
			        cell.setColspan(2);
			        table.setHeaderRows(2);

			              // Creando tabla anidada        
			              float [] anidado = {800f, 600f};      
			              
			              PdfPTable Tabla2 = new PdfPTable(anidado);
		                           Tabla2.addCell("Parte 1");
			                       Tabla2.addCell("Parte 2");

			             table.addCell(Tabla2);
			                      
			        
			            table.addCell("CANTIDAD");
			            table.addCell("DETALLE");
			            table.addCell("PRECIO UNITARIO");
			            table.addCell("IMPORTE");
			        
			            cell.setColspan(4);
				        table.setHeaderRows(4);
				        table.setFooterRows(1);
			       
			       // table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
			        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        for (int counter = 1; counter < 5; counter++) {
			            table.addCell(String.valueOf(counter));
			            table.addCell("Contador Ejemplo " + counter);
			            table.addCell("Valores " + counter);
			            table.addCell("Otro " + counter);
			        }
			        
			        documento.add(table);
				
				
				
				documento.close();
				
			}catch(Exception ex) {ex.getMessage();}

		}
		finally{
			out.close();
		}
		
	}

	





}
