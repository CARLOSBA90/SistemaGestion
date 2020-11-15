package com.administracion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.conexion.DbManager;

import com.sesion.Usuario;

public class ModeloAdministracion {
	
	

		private Connection miConexion=null;

		private DbManager conectar;

		public ModeloAdministracion() {

			conectar = new DbManager();
		}

		

		public List<Usuario> listarUsuarios() throws Exception{

			List<Usuario> usuarios = new ArrayList<>();

			Statement miStatement=null;

			ResultSet miResulset=null;


			try {
				// ESTABLECER CONEXION Y USAR SENTENCIA SQL

				miConexion = conectar.createConnection();

				String instruccion="SELECT * FROM usuarios";

				miStatement=miConexion.createStatement();

				/// EJECUTAR SQL

				miResulset=miStatement.executeQuery(instruccion);


				/// RECORRER EL RESULSET

				while(miResulset.next()) {

					int    codigo=miResulset.getInt(1);
					String usuario=miResulset.getString(2);
					String correo=miResulset.getString(3);
					String contrasena=miResulset.getString(4);
					Date    fecha=miResulset.getDate(5);
					int    nivel=miResulset.getInt(6);
			

					Usuario usuarioTemp= new Usuario(codigo,usuario,contrasena,correo,fecha,nivel);

					usuarios.add(usuarioTemp);

				}}catch(SQLException e) {

					e.printStackTrace();
				}finally {
					miStatement.close();
					miConexion.close();
				}

			return usuarios;
		}



		public boolean eliminar(int id) {
			// TODO Auto-generated method stub
			
			PreparedStatement miStatement=null;
			
			boolean eliminado = false;

			try{
				miConexion = conectar.createConnection();

				String sql="DELETE FROM usuarios WHERE ID=?";

				miStatement= miConexion.prepareStatement(sql);

				miStatement.setInt(1,id);

				miStatement.execute();
				
				eliminado = true;



			}catch(Exception e) {
				e.printStackTrace();

			}finally {
				try {
					miStatement.close();
					miConexion.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			
			return eliminado;
		}



		public boolean editar(Usuario tempUsuario) throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement miStatement=null;
			
			boolean ok = false;

			try {

				miConexion = conectar.createConnection();

				String sql="UPDATE usuarios SET usuario=?, correo=?, contrasena=?, nivel=? WHERE ID=?";	

				miStatement = miConexion.prepareStatement(sql);

				miStatement.setString(1,tempUsuario.getUsuario());

				miStatement.setString(2,tempUsuario.getEmail());

				miStatement.setString(3, tempUsuario.getContrasena());

				miStatement.setInt(4, tempUsuario.getNivel());

				miStatement.setInt(5,tempUsuario.getId());

				miStatement.execute();
				
				ok = true;
				
				

			}catch(SQLException | ClassNotFoundException | IOException e) {

				e.printStackTrace();
			}finally {
				miStatement.close();
				miConexion.close();
			}



			
			
			
			return ok;
		}



	

}
