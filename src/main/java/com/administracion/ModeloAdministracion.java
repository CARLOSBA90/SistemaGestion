package com.administracion;

import java.sql.Connection;
import java.sql.Date;
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

}
