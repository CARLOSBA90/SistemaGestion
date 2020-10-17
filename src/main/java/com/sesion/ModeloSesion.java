package com.sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
