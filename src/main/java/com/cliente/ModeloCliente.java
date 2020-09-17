package com.cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.conexion.DbManager;

public class ModeloCliente {
	

	private Connection miConexion=null;

	private DbManager conectar;

	public ModeloCliente() {

		conectar = new DbManager();
	}


	public List<Cliente> getClientes() throws Exception{

		List<Cliente> clientes = new ArrayList<>();

		Statement miStatement=null;

		ResultSet miResulset=null;


		try {
			// ESTABLECER CONEXION Y USAR SENTENCIA SQL

			miConexion = conectar.createConnection();

			String instruccion="SELECT * FROM cliente";

			miStatement=miConexion.createStatement();

			/// EJECUTAR SQL

			miResulset=miStatement.executeQuery(instruccion);


			/// RECORRER EL RESULSET

			while(miResulset.next()) {

				int    codigo=miResulset.getInt(1);
				String nombre=miResulset.getString(2);
				String apellido=miResulset.getString(3);
				int    dni=miResulset.getInt(4);
				int    telefono=miResulset.getInt(5);
				String direccion=miResulset.getString(6);
				String correo=miResulset.getString(7);

				Cliente clienteTemp= new Cliente(codigo,nombre,apellido,dni,telefono,direccion,correo);

				clientes.add(clienteTemp);

			}}catch(SQLException e) {

				e.printStackTrace();
			}finally {
				miStatement.close();
				miConexion.close();
			}

		return clientes;
	}



	public void agregarNuevoCliente(Cliente nuevoCliente) throws SQLException {
		// TODO Auto-generated method stub

		PreparedStatement miStatement=null;

		try{
			miConexion = conectar.createConnection();

			String sql="INSERT INTO cliente(nombre,apellido,dni,telefono,direccion,correo) "
					+ "VALUES(?,?,?,?,?,?)";


			miStatement=miConexion.prepareStatement(sql);

			///Establecer clientes

			miStatement.setString(1,nuevoCliente.getNombre());

			miStatement.setString(2,nuevoCliente.getApellido());

			miStatement.setInt(3,nuevoCliente.getDni());

			miStatement.setInt(4,nuevoCliente.getTelefono());

			miStatement.setString(5,nuevoCliente.getDireccion());

			miStatement.setString(6,nuevoCliente.getCorreo());

			///EJECUTAR SQL

			miStatement.execute();



		}catch(SQLException | ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}finally {
			miStatement.close();
			miConexion.close();
		}


	}

	public void eliminar(int codigo) {
		// TODO Auto-generated method stub

		PreparedStatement miStatement=null;

		try {

			miConexion = conectar.createConnection();

			String sql="DELETE FROM cliente WHERE cod_cliente=?";

			miStatement=miConexion.prepareStatement(sql);

			miStatement.setInt(1,codigo);

			miStatement.execute();


		}catch(Exception e) {
			e.printStackTrace();
		}


	}

	public void actualizar(Cliente temp) throws SQLException {
		// TODO Auto-generated method stub

		PreparedStatement miStatement=null;

		try {

			miConexion = conectar.createConnection();

			String sql="UPDATE cliente SET nombre=?, apellido=?, dni=?, telefono=?, direccion=?, correo=? WHERE cod_cliente=?";	

			miStatement = miConexion.prepareStatement(sql);


			miStatement.setString(1,temp.getNombre());

			miStatement.setString(2,temp.getApellido());

			miStatement.setInt(3, temp.getDni());

			miStatement.setInt(4, temp.getTelefono());

			miStatement.setString(5,temp.getDireccion());

			miStatement.setString(6,temp.getCorreo());

			miStatement.setInt(7, temp.getCod_cliente());

			miStatement.execute();

		}catch(SQLException | ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}finally {
			miStatement.close();
			miConexion.close();
		}

	}



}
