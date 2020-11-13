package com.sesion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.conexion.DbManager;

public class ModeloSesion {
	
	private Connection miConexion;

	private DbManager conectar;
	
	public ModeloSesion() {
		
		conectar = new DbManager();
		
	}

	public int autenticacion(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement miStatement=null;
		
		ResultSet rs;
		
		int nivel = 0;
		
		String sql = "select nivel from heroku_a449933febac428.usuarios where usuario=? and contrasena=?";
		
		try {
		
			miConexion = conectar.createConnection();
			
			miStatement = miConexion.prepareStatement(sql);
			
			miStatement.setString(1, usuario.getUsuario());
			
			miStatement.setString(2, usuario.getContrasena());
			
			rs = miStatement.executeQuery();
			
			while(rs.next()) {
				
				nivel = rs.getInt(1);
	
			}
			
		}catch(Exception e){
				e.printStackTrace();
			}finally {
				miStatement.close();
				miConexion.close();
			}

		return nivel;
	}

	public boolean registrar(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		int verificacion=0;
		
        PreparedStatement miStatement=null;
		
		ResultSet rs;
		
		String sql = "select exists (select usuario from heroku_a449933febac428.usuarios where usuario=?)";
		
		try {
			miConexion = conectar.createConnection();
			
			miStatement = miConexion.prepareStatement(sql);
			
			miStatement.setString(1, usuario.getUsuario());
			
			rs = miStatement.executeQuery();
			
	         while(rs.next()) {
				
			 verificacion = rs.getInt(1);
			 
	
			}
	         
	         if(verificacion!=1) {
	        	
	        	 String sqlAgregar="INSERT INTO heroku_a449933febac428.usuarios(usuario,correo,contrasena,fecha_alta,nivel) VALUES(?,?,?,?,?)";
	        	 
	        	 
	        	 PreparedStatement miStatementAgregar=miConexion.prepareStatement(sqlAgregar);
	        	 
	        	 miStatementAgregar.setString(1,usuario.getUsuario());
	        	 
	        	 miStatementAgregar.setString(2,usuario.getEmail());
	        	 
	        	 miStatementAgregar.setString(3,usuario.getContrasena());
	        	 
	        	 miStatementAgregar.setDate(4,usuario.getFecha());
	        	 
	        	 miStatementAgregar.setInt(5,usuario.getNivel());
	        	 
	        	 miStatementAgregar.execute();
	        	 
	         }
	         
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			miStatement.close();
			miConexion.close();
		}

		
		if(verificacion!=1) return true; else
		return false;
	}

	public Usuario obtenerUsuario(String usuario) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		   Usuario temp=null;
		
	       PreparedStatement miStatement=null;
			
			ResultSet rs;
			
			String sql = "SELECT * FROM heroku_a449933febac428.usuarios WHERE usuario=?";
			
			try {
				miConexion = conectar.createConnection();
				
				miStatement = miConexion.prepareStatement(sql);
				
				miStatement.setString(1, usuario);
				
				rs = miStatement.executeQuery();
				
				
				while(rs.next()) {
					
				temp= new Usuario(rs.getInt(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getDate(5),rs.getInt(6));
					
				}
				
			
			}catch(SQLException e) {

				e.printStackTrace();
			}finally {
				miStatement.close();
				miConexion.close();
			}
		
		
		
		return temp;
	}

}
