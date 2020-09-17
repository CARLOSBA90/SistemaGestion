package com.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.conexion.DbManager;

public class ModeloProducto {

	private Connection miConexion;

	private DbManager conectar;


	public ModeloProducto() {

		conectar = new DbManager();
	}




	public List<Producto> getProductos() throws Exception {
		// TODO Auto-generated method stub
		List<Producto> productos = new ArrayList<>();

		Statement miStatement = null;

		ResultSet miResulset=null;

		try {

			miConexion = conectar.createConnection();

			String sql = "SELECT * FROM producto_calzado";

			miStatement= miConexion.createStatement();

			miResulset= miStatement.executeQuery(sql);

			while(miResulset.next()) {

				int codigo=miResulset.getInt(1);
				String tipo=miResulset.getString(2);
				String nombre=miResulset.getString(3);
				double precio=miResulset.getDouble(4);
				String fabricante=miResulset.getString(5);
				int talla=miResulset.getInt(6);
				int stock=miResulset.getInt(7);

				Producto temp = new Producto(codigo,tipo,nombre,precio,fabricante,talla,stock);

				productos.add(temp);

			}


		}finally {
			miStatement.close();
			miConexion.close();
		}

		return productos;
	}



	public void insertar(Producto temp) {
		// TODO Auto-generated method stub

		PreparedStatement miStatement=null;

		try {
			miConexion = conectar.createConnection();

			String sql="INSERT INTO producto_calzado(tipo,nombre,precio,fabricante,talla,stock) values(?,?,?,?,?,?)";

			miStatement= miConexion.prepareStatement(sql);

			miStatement.setString(1,temp.getTipo());
			miStatement.setString(2,temp.getNombre());
			miStatement.setDouble(3,temp.getPrecio());
			miStatement.setString(4,temp.getFabricante());
			miStatement.setInt(5,temp.getTalla());
			miStatement.setInt(6,temp.getStock());

			miStatement.execute();


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



	}



	public void eliminar(int codigo) {
		// TODO Auto-generated method stub

		PreparedStatement miStatement=null;

		try{
			miConexion = conectar.createConnection();

			String sql="DELETE FROM producto_calzado WHERE cod_calzado=?";

			miStatement= miConexion.prepareStatement(sql);

			miStatement.setInt(1,codigo);

			miStatement.execute();



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



	}



	public void actualizar(Producto temp) {
		// TODO Auto-generated method stub

		PreparedStatement miStatement=null;

		try {

			miConexion = conectar.createConnection();

			String sql = "UPDATE producto_calzado set tipo=?, nombre=?, precio=?, fabricante=?, talla=?, stock=? WHERE cod_calzado=?";

			miStatement = miConexion.prepareStatement(sql);

			miStatement.setInt(7,temp.getCod_calzado());
			miStatement.setString(1,temp.getTipo());
			miStatement.setString(2,temp.getNombre());
			miStatement.setDouble(3,temp.getPrecio());
			miStatement.setString(4,temp.getFabricante());
			miStatement.setInt(5,temp.getTalla());
			miStatement.setInt(6,temp.getStock());

			miStatement.execute();

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

	}

}
