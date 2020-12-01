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
				
				
			        PdfPTable tabla = new PdfPTable(1);
			        tabla.setWidthPercentage(100);
			        tabla.getDefaultCell().setUseAscender(true);
			        tabla.getDefaultCell().setUseDescender(true);
			        Font f = new Font(FontFamily.HELVETICA, 18, Font.BOLD, GrayColor.GRAYWHITE);
			        PdfPCell titulo = new PdfPCell(new Phrase("FACTURA C", f));
			        titulo.setBackgroundColor(GrayColor.GRAYBLACK);
			        titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
			        tabla.addCell(titulo);
			        tabla.getDefaultCell().setBackgroundColor(GrayColor.WHITE);
			       // cell1.setColspan(1);
			      //  tabla.setHeaderRows(1);
			        
			        

			              // Creando tabla anidada      
			              
			        
			        /// CABECERA SUPERIOR CON 4 SECCIONES: IMAGEN LOGO(SUPERIOR IZQUIERDA), FECHA Y NRO PEDIDO(SUPERIOR DERECHA)
			        /// DETALLES DEL CLIENTE INFERIOR IZQUIERDA, DETALLE DE PAGO(CONTADO O CREDITO) INFERIOR DERECHO
			              PdfPTable cabeceraSuperior = new PdfPTable(2);
			              
			              ///////////////////////////////////////////////
			              
			              float[] cabeceraFloatA = {6,4};
			              
			              PdfPTable  cabeceraA = new PdfPTable(cabeceraFloatA);
			              
			              PdfPCell imagen = new PdfPCell(new Phrase ("IMAGEN AQUI"));
			              
			              imagen.setFixedHeight(80);
			              
			              cabeceraA.addCell(imagen);
			              
			              PdfPCell descripcionFactura = new PdfPCell(new Paragraph("Pedido Nro:"+ request.getParameter("Codigo")));
			              
			              descripcionFactura.addElement(new Paragraph("Fecha __/__/__"));
			              
			              cabeceraA.addCell(descripcionFactura);
			              
			              
			              //////////////////////////////////////////////
			              
			              
			             cabeceraSuperior.addCell(cabeceraA);
			             
			             
			             float[] cabeceraFloatB = {8,2};
			              
			              PdfPTable  cabeceraB = new PdfPTable(cabeceraFloatB);
			              
			              PdfPCell clienteCelda = new PdfPCell(new Phrase ("Senor(es)....................."));
			              
			              clienteCelda.setFixedHeight(60);
			              
			              cabeceraB.addCell(clienteCelda);
			              
			              PdfPCell formaPagoCelda = new PdfPCell(new Phrase("Contado: __  Credito: __"));
			              
			              cabeceraB.addCell(formaPagoCelda);
		                           
			              cabeceraSuperior.addCell(cabeceraB);

			             tabla.addCell(cabeceraSuperior);
			             
			             
			             // Segunda tabla anidada
			             
			             float[] columnasTabla3 = {2, 10, 3 ,5};
			             
			             PdfPTable tabla3 = new PdfPTable(columnasTabla3);
			             
			             String[] cabecera = {"CANT.","DETALLE","P. UNIT.", "IMPORTE"};
			      
			             Font fuente3 = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
			             
			             for(String a:cabecera) {
					        PdfPCell Cabecera3 = new PdfPCell(new Phrase(a, fuente3));
					        Cabecera3.setBackgroundColor(GrayColor.GRAYBLACK);
					        Cabecera3.setHorizontalAlignment(Element.ALIGN_CENTER);
					        tabla3.addCell(Cabecera3);
			             }
			        
			             /*
			            tabla3.addCell("CANTIDAD");
			            tabla3.addCell("DETALLE");
			            tabla3.addCell("PRECIO UNITARIO");
			            tabla3.addCell("IMPORTE");*/
			        
			             tabla3.getDefaultCell().setBackgroundColor(GrayColor.WHITE);
					        tabla3.setHeaderRows(1);
			       
			       // table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
			        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        for (int counter = 1; counter < 15; counter++) {
			        	tabla3.addCell(String.valueOf(counter));
			        	tabla3.addCell(" ");
			        	tabla3.addCell(" ");
			        	tabla3.addCell(" ");
			        }
			        
			        tabla.addCell(tabla3);
			        
			        documento.add(tabla);
				
				
				
				documento.close();
				
			}catch(Exception ex) {ex.getMessage();}

		}
		finally{
			out.close();
		}
		
	}

	





}
