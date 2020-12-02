package com.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

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
import com.itextpdf.text.Image;



public class PDF {
	
    public static final String LOGO = "./src/main/webapp/img/logo.png";
	
	
	public void crear(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
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
			              
			              Image image = Image.getInstance(new URL("https://s3-us-west-2.amazonaws.com/lasaga-blog/media/images/grupo_imagen.original.jpg"));
			              
			              PdfPCell imagen = new PdfPCell(image, false);
			              
			              imagen.setFixedHeight(80);
			              
			              cabeceraA.addCell(imagen);
			              
			              ///////////////////////////////////////////////
			              
			              PdfPCell descripcionFactura = new PdfPCell(new Paragraph());
			              
			              descripcionFactura.addElement(new Paragraph("Pedido Nro:"+ request.getParameter("Codigo")));
			              
			              descripcionFactura.addElement(new Paragraph("Fecha __/__/__"));
			              
			              cabeceraA.addCell(descripcionFactura);
			              
			              
			              //////////////////////////////////////////////
			              
			              
			             cabeceraSuperior.addCell(cabeceraA);
			             
			             
			             float[] cabeceraFloatB = {8,2};
			              
			              PdfPTable  cabeceraB = new PdfPTable(cabeceraFloatB);
			              
			              PdfPCell clienteCelda = new PdfPCell(new Phrase ("Sr(es)....................."));
			              
			              clienteCelda.setFixedHeight(60);
			              
			              cabeceraB.addCell(clienteCelda);
			              
			              PdfPCell formaPagoCelda = new PdfPCell(new Phrase("Contado: __  Credito: __"));
			              
			              cabeceraB.addCell(formaPagoCelda);
		                           
			              cabeceraSuperior.addCell(cabeceraB);

			             tabla.addCell(cabeceraSuperior);
			             
			      ///////////////////////////////////////////////////////////////       
			             // Segunda tabla anidada
			             
			             float[] columnasTabla3 = {2, 11, 3 ,4};
			             
			             PdfPTable tabla3 = new PdfPTable(columnasTabla3);
			             
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
			        for (int counter = 1; counter < 16; counter++) {
			        	tabla3.addCell(String.valueOf(counter));
			        	tabla3.addCell(" ");
			        	tabla3.addCell(" ");
			        	tabla3.addCell(" ");
			        }
			        
			        tabla3.getDefaultCell().setFixedHeight(20);
			        
			        tabla.addCell(tabla3);
			     
			        //////////////////////////////////////////////////////////////////////
			        
			        float[] cabeceraFinalC = {13,3,4};
			        
		              PdfPTable  cabeceraFinal = new PdfPTable(cabeceraFinalC);
		              
		              cabeceraFinal.addCell("");
		              
		              cabeceraFinal.addCell("TOTAL $");
		              
		              cabeceraFinal.addCell("");
		              
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
