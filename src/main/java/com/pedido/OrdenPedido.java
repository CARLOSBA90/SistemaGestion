package com.pedido;

import java.util.ArrayList;
import java.util.List;

public class OrdenPedido {

	public static List[] productos(String texto) {

		List<Integer> miLista = new ArrayList<>();
		
		List<Integer> codigoProducto = new ArrayList<>();
		
		List<Integer> stockProducto = new ArrayList<>();
		
		boolean p=false;
		
		String sentencia="";
		
		for(int i=0;i<texto.length();i++) {
			
			if(texto.charAt(i)!=',') {
				
				sentencia+=texto.charAt(i);
				
			}else {p=!p;}

			if(!p || (i==(texto.length()-1))) {
				
				if(sentencia!="") {miLista.add(Integer.parseInt(sentencia)); sentencia="";}
				
				p=!p;
				
			}
			
		}
		
		for(int i=0;i<miLista.size()/2;i++) {
			
			codigoProducto.add(miLista.get(i));
			
		}
		
		for(int i=miLista.size()/2;i<miLista.size();i++) {
			
			stockProducto.add(miLista.get(i));
		}
		
		List[] lista = new List[2];
		
		lista[0]= codigoProducto;
		
		lista[1]= stockProducto;
		

		
		return lista;
	}
	
	
}
