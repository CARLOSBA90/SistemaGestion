package com.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
import com.pedido.Pedido;
import com.itextpdf.text.Image;



public class PDF {
	
	private ModeloPedido modelo;
	
	public PDF () {
		
		modelo = new ModeloPedido();
	}
	
	public void crear(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, SQLException {
		
        
		///// instanciamos la clase pedido para recibir de la BBDD el pedido con el codigo especifico!
		
		/// Solicitamos mediante metodo al ModeloPedido los datos del pedido mediante el codigo proporcionado
		
		Pedido facturaPedido = new Pedido(modelo.ObtenerPedido(Integer.parseInt(request.getParameter("Codigo"))));
		
		
		response.setContentType("application/pdf");
		
		OutputStream out = response.getOutputStream();
		
		try {


			try {
				
				Document documento = new Document();
				
				PdfWriter.getInstance(documento, out);
				
				documento.open();
		
				
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
			

			              // Creando tabla anidada      
			              
			        
			        /// CABECERA SUPERIOR CON 4 SECCIONES: IMAGEN LOGO(SUPERIOR IZQUIERDA), FECHA Y NRO PEDIDO(SUPERIOR DERECHA)
			        /// DETALLES DEL CLIENTE INFERIOR IZQUIERDA, DETALLE DE PAGO(CONTADO O CREDITO) INFERIOR DERECHO
			              PdfPTable cabeceraSuperior = new PdfPTable(1);
			              
			              ///////////////////////////////////////////////
			              
			              float[] cabeceraFloatA = {6,4};
			              
			              //// LOGO DE LA EMPRESA 
			              
			             PdfPTable  cabeceraA = new PdfPTable(cabeceraFloatA);
			              
			              Image image = Image.getInstance(new URL("https://i.ibb.co/C0n3THC/LOGO.png"));
			              
			              
			              PdfPCell imagen = new PdfPCell(image, true);
			              
			              imagen.setFixedHeight(80);
			              
			               imagen.setBorder(PdfPCell.NO_BORDER);
			              
			              cabeceraA.addCell(imagen);
			              
			              ///////////////////////////////////////////////
			              
			              PdfPCell descripcionFactura = new PdfPCell(new Paragraph());
			              
			              descripcionFactura.addElement(new Paragraph("Pedido Nro:"+ request.getParameter("Codigo")));
			              
			              descripcionFactura.addElement(new Paragraph("Fecha "+ facturaPedido.getFecha().toString()));////+ facturaPedido.getFecha()
			              
			              descripcionFactura.setBorder(PdfPCell.NO_BORDER);
			              
			              cabeceraA.addCell(descripcionFactura);
			              
			              
			              //////////////////////////////////////////////
			              
			              
			             cabeceraSuperior.addCell(cabeceraA);
			             
			             
			             float[] cabeceraFloatB = {8,2};
			              
			              PdfPTable  cabeceraB = new PdfPTable(cabeceraFloatB);
			              
			              PdfPCell clienteCelda = new PdfPCell(new Paragraph());
			              
			              clienteCelda.addElement(new Paragraph("Nombre: "+ facturaPedido.getNombreApellido()));
			              
			              clienteCelda.addElement(new Paragraph("Domicilio: "+ facturaPedido.getDireccion()));
			              
			              clienteCelda.addElement(new Paragraph("Telefono: "+Integer.toString(facturaPedido.getTelefono())));//
			              
			              clienteCelda.setFixedHeight(60);
			              
			              clienteCelda.setBorder(PdfPCell.NO_BORDER);
			              
			              cabeceraB.addCell(clienteCelda);
			              
			              PdfPCell formaPagoCelda = new PdfPCell(new Phrase("Forma pago: "+ facturaPedido.getForma_pago()));
			              
			               formaPagoCelda.setBorder(PdfPCell.NO_BORDER);
			              
			              cabeceraB.addCell(formaPagoCelda);
			              
			              cabeceraSuperior.addCell(cabeceraB);
			              
			             tabla.addCell(cabeceraSuperior);
			             
			             
			             
			      ///////////////////////////////////////////////////////////////       
			             // Segunda tabla anidada
			             
			             float[] columnasTabla3 = {2, 11, 3 ,4};
			             
			             PdfPTable tabla3 = new PdfPTable(columnasTabla3);
			             
			             tabla3.getDefaultCell().setFixedHeight(15);
			             
			             String[] cabecera = {"CANT.","DETALLE","P. UNIT.", "IMPORTE"};
			      
			             Font fuente3 = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
			             
			             for(String a:cabecera) {
					        PdfPCell Cabecera3 = new PdfPCell(new Phrase(a, fuente3));
					        Cabecera3.setBackgroundColor(GrayColor.GRAYBLACK);
					        Cabecera3.setHorizontalAlignment(Element.ALIGN_CENTER);
					        tabla3.addCell(Cabecera3);
			             }
			        
			        
			        
			             tabla3.getDefaultCell().setBackgroundColor(GrayColor.WHITE);
					        tabla3.setHeaderRows(1);
			       
			    
			        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        for (int i = 0; i < 20; i++) {/*
			        	
			        	if(false) {
			        	
			        	   	tabla3.addCell(""+Integer.toString(facturaPedido.getCantidad().get(i).getCantidad()));
				        	tabla3.addCell(""+facturaPedido.getCantidad().get(i).getNombre());
				        	tabla3.addCell(""+Double.toString(facturaPedido.getCantidad().get(i).getPrecio()));
				        	tabla3.addCell(""+Double.toString(facturaPedido.getCantidad().get(i).getTotal()));	
			        		
			        		
			        	}else {
			        	tabla3.addCell(" ");
			        	tabla3.addCell(" ");
			        	tabla3.addCell(" ");
			        	tabla3.addCell(" ");
			        	}*/
			        }
			        
			        
			        
			        tabla.addCell(tabla3);
			     
			        //////////////////////////////////////////////////////////////////////
			        
			        float[] cabeceraFinalC = {13,3,4};
			        
		              PdfPTable  cabeceraFinal = new PdfPTable(cabeceraFinalC);
		              
		              cabeceraFinal.addCell("");
		              
		              cabeceraFinal.addCell("TOTAL $");
		              
		              cabeceraFinal.addCell(""+Double.toString(facturaPedido.getTotal()));
		              
			        tabla.addCell(cabeceraFinal);
			      
			        
			        documento.add(tabla);
				
				
				
				documento.close();
				
			}catch(Exception ex) {ex.getMessage();}

		}
		finally{
			out.close();
		}
		
	}

}
