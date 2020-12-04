package com.pedido;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.cliente.Cliente;
import com.conexion.DbManager;
import com.producto.Producto;

public class ModeloPedido {

	private Connection miConexion;

	private DbManager conectar;

	public ModeloPedido() {

		conectar = new DbManager();
	}



	public List<Pedido> getPedidos() throws Exception {
		// TODO Auto-generated method stub

		Statement miStatement=null;

		ResultSet miResulset;

		List<Pedido> pedidos = new ArrayList<>();

		Statement STprod_ped=null;

		ResultSet RSprod_ped=null;


		try {

			miConexion = conectar.createConnection();

			String sql="SELECT pedido.cod_pedido, pedido.cod_cliente, cliente.nombre, cliente.apellido, "
					+ "pedido.cantidad_calzados, pedido.precio_total, pedido.forma_pago, pedido.enviado, pedido.fecha_pedido " +
					"FROM cliente INNER JOIN pedido ON cliente.cod_cliente=pedido.cod_cliente";

			miStatement= miConexion.createStatement();

			miResulset= miStatement.executeQuery(sql);

			//String sqlPD ="SELECT * FROM pedido_producto";

			String sqlPD ="SELECT * FROM pedido_producto";

			STprod_ped= miConexion.createStatement();

			RSprod_ped= STprod_ped.executeQuery(sqlPD);

			while(miResulset.next()) {


				int cod_pedido=miResulset.getInt(1);
				int cod_cliente=miResulset.getInt(2);
				String nombreApellido_cliente = miResulset.getString(3) +" "+ miResulset.getString(4);
				int cantidad_calzados=miResulset.getInt(5);
				double total=miResulset.getDouble(6);
				String forma=miResulset.getString(7);
				Boolean enviado=miResulset.getBoolean(8);
				Date   fecha=miResulset.getDate(9);



				List<CantidadCalzado> cantidades = new ArrayList<>();

				while(RSprod_ped.next()) {

					int codigoP_PD=RSprod_ped.getInt(2);

					if(cod_pedido==codigoP_PD) {

						int codPD=RSprod_ped.getInt(1);
						int cod_productoPD=RSprod_ped.getInt(3);
						int cantidadPr=RSprod_ped.getInt(4);
						double precioPr=RSprod_ped.getDouble(5);
						double totalPr=RSprod_ped.getDouble(6);

						CantidadCalzado temp2 = new CantidadCalzado(codPD,codigoP_PD,cod_productoPD,cantidadPr,precioPr,totalPr);

						cantidades.add(temp2);
					}
				}
				RSprod_ped.beforeFirst();

				Pedido temp = new Pedido(cod_pedido,cod_cliente,forma,enviado,fecha,total,cantidades,cantidad_calzados, nombreApellido_cliente);

				pedidos.add(temp);


			}

		}catch(Exception e) {
			e.printStackTrace();

		}

		finally{
			miStatement.close();
			RSprod_ped.close();
			miConexion.close();
		}



		return pedidos;
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

	public List<Cliente> getClientes() throws Exception {

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

				Cliente clienteTemp= new Cliente(codigo,nombre,apellido);

				clientes.add(clienteTemp);

			}}catch(SQLException e) {

				e.printStackTrace();
			}finally {
				miStatement.close();
				miConexion.close();
			}

		return clientes;

	}

	public void nuevoPedido(String nombre, String forma, List list, List list2) throws SQLException {
		// TODO Auto-generated method stub


		double total=0;

		int proximo=0;

		int stock=0;

		double precio=0;

		int numeroCalzados=0;

		int cantidad = 0;


		try {
			miConexion = conectar.createConnection();

			miConexion.setAutoCommit(false);


			/// OBTENER EL PROXIMO AUTO-INCREMENT DE LA TABLA PEDIDOS PARA UTILIZARLO EN LA TABLA PEDIDO_PRODUCTO

			Statement statementAU = null;

			ResultSet rs_AU = null;

		/*	String sqlAU="SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'empresa_calzado' " +
					"AND   TABLE_NAME   = 'pedido'";*/

					String sqlAU="SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'heroku_a449933febac428' " +
		  					"AND   TABLE_NAME   = 'pedido'";


			statementAU = miConexion.createStatement();

			rs_AU = statementAU.executeQuery(sqlAU);

			if(rs_AU.next()) {

				proximo = rs_AU.getInt(1);}

			rs_AU.close();



			///INSERTAR VALORES TABLA PEDIDO

			PreparedStatement miStatement3=null;

			String insertarPedido = "INSERT INTO pedido(cod_cliente,cantidad_calzados,precio_total,forma_pago,enviado,fecha_pedido)"
					+ "values(?,?,?,?,?,?)";

			miStatement3 = miConexion.prepareStatement(insertarPedido);

			miStatement3.setInt(1,Integer.parseInt(nombre)); // codigo cliente
			miStatement3.setInt(2,0);
			miStatement3.setDouble(3,0);
			miStatement3.setString(4,forma);
			miStatement3.setBoolean(5,false);
			miStatement3.setDate(6,new Date(Calendar.getInstance().getTime().getTime()));

			miStatement3.execute();

			miStatement3.close();




			////ACTUALIZAR VALORES DE LA TABLA PRODUCTO(COLUMNA STOCK)


			///INSTRUCCION SQL PARA ACTUALIZAR LA TABLA PRODUCTO_CALZADO CON LOS VALORES DEL NUEVO PEDIDO

			String sql = "SELECT stock, precio FROM producto_calzado WHERE cod_calzado=?";


			/// BUCLE QUE RECORRE CADA ORDEN DEL PRODUCTO PARA ACTUALIZAR LOS STOCK DE LA TABLA PRODUCTO

			///Se entiende por "list" el arrayList con los codigos del producto y "list2" como la cantidad, ambos emparejados con
			/// los indices

			for(int i=0; i<list.size();i++) {

				PreparedStatement miStatement=null;

				miStatement= miConexion.prepareStatement(sql);

				miStatement.setInt(1,(int) list.get(i));

				ResultSet miResulset= miStatement.executeQuery();



				if(miResulset.next()) {

					stock=miResulset.getInt(1)-(int)list2.get(i);

					numeroCalzados+=(int)list2.get(i);

					/// SI EL STOCK PASA DE 0 A VALOR MENOR LANZA EXCEPCION Y SE DESHACE EL PEDIDO

					if(stock<0) throw new Exception("STOCK NO PUEDE SER MENOR A 0");

					precio = miResulset.getDouble(2);

					total+=(int)list2.get(i)*precio;

					cantidad+=(int)list2.get(i); } else throw new Exception();

				String actualiza="UPDATE producto_calzado set stock=? WHERE cod_calzado=?";

				PreparedStatement miStatement2 = miConexion.prepareStatement(actualiza);

				miStatement2.setInt(1,stock);

				miStatement2.setInt(2,(int) list.get(i));

				miStatement2.execute();

				miStatement2.close();

				/// INSERTAR NUEVOS DATOS EN LA TABLA PEDIDO_PRODUCTO

				PreparedStatement StatementPED_PROD=null;

				String sqlPED_PROD="INSERT INTO pedido_producto(cod_pedido,cod_producto,cantidad_producto,precio,valor_total) values"
						+ "(?,?,?,?,?)";

				StatementPED_PROD= miConexion.prepareStatement(sqlPED_PROD);

				StatementPED_PROD.setInt(1,proximo);
				StatementPED_PROD.setInt(2,(int) list.get(i));
				StatementPED_PROD.setInt(3,(int) list2.get(i));
				StatementPED_PROD.setDouble(4,precio);
				StatementPED_PROD.setDouble(5,precio*(int)list2.get(i));
				StatementPED_PROD.execute();
				StatementPED_PROD.close();

				miStatement.close();
			}



			/// ACTUALIZAR EL TOTAL Y NUMERO DE CALZADOS EN TABLA PEDIDO

			String sqlActualiza = "UPDATE pedido set cantidad_calzados=?, precio_total=? WHERE cod_pedido=?";

			PreparedStatement ST_Actualiza = miConexion.prepareStatement(sqlActualiza);

			ST_Actualiza.setInt(1,numeroCalzados);
			ST_Actualiza.setDouble(2,total);
			ST_Actualiza.setInt(3,proximo);
			ST_Actualiza.execute();


			///COMPLETAR TODOS LOS QUERYS O NO COMPLETAR NINGUNO, TRANSACCION
			miConexion.commit();


		} catch(SQLException e) {

			miConexion.rollback();

			e.printStackTrace();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			miConexion.rollback();
		}


	}



	public Pedido ObtenerPedido(int i) throws SQLException {
		// TODO Auto-generated method stub
		
		Statement miStatement=null;

		ResultSet miResulset;
		
		Statement STprod_ped=null;

		ResultSet RSprod_ped=null;
		
		Pedido pedido = new Pedido();


		try {

			miConexion = conectar.createConnection();
			
			/// Se extrae de la base de datos los detalles del pedido

			String sql="SELECT pedido.cod_pedido, pedido.cod_cliente, cliente.nombre, cliente.apellido, "
					+ "pedido.cantidad_calzados, pedido.precio_total, pedido.forma_pago, pedido.enviado, pedido.fecha_pedido,"
					+ "cliente.telefono, cliente.direccion FROM cliente INNER JOIN pedido ON cliente.cod_cliente=pedido.cod_cliente WHERE pedido.cod_pedido="+i;

			miStatement= miConexion.createStatement();

			miResulset= miStatement.executeQuery(sql);

			/// Se realiza un List para sacar el detalle de cada producto con su nombre, cantidad, precio y total!

			String sqlPD ="SELECT pedido_producto.cod_producto, pedido_producto.cantidad_producto, pedido_producto.precio," + 
					" pedido_producto.valor_total, producto_calzado.nombre FROM producto_calzado INNER JOIN pedido_producto" + 
					" ON pedido_producto.cod_producto=producto_calzado.cod_calzado" + 
					" WHERE pedido_producto.cod_pedido="+i;

			STprod_ped= miConexion.createStatement();

			RSprod_ped= STprod_ped.executeQuery(sqlPD);

			while(miResulset.next()) {


				int cod_pedido=miResulset.getInt(1);
				int cod_cliente=miResulset.getInt(2);
				String nombreApellido_cliente = miResulset.getString(3) +" "+ miResulset.getString(4);
				int cantidad_calzados=miResulset.getInt(5);
				double total=miResulset.getDouble(6);
				String forma=miResulset.getString(7);
				Boolean enviado=miResulset.getBoolean(8);
				Date   fecha=miResulset.getDate(9);
				int telefono=miResulset.getInt(10);
				String direccion=miResulset.getString(11);

                      System.out.println(direccion);

				List<CantidadCalzado> cantidades = new ArrayList<>();

				while(RSprod_ped.next()) {

						int cod_productoPD=RSprod_ped.getInt(1);
						int cantidadPr=RSprod_ped.getInt(2);
						double precioPr=RSprod_ped.getDouble(3);
						double totalPr=RSprod_ped.getDouble(4);
						String nombre=RSprod_ped.getString(5);

						CantidadCalzado temp2 = new CantidadCalzado(cod_productoPD,cantidadPr,precioPr,totalPr, nombre);

						cantidades.add(temp2);
					
				}
				
			    pedido = new Pedido(cod_pedido,cod_cliente,forma,enviado,fecha,total,cantidades,cantidad_calzados, nombreApellido_cliente, direccion, telefono);



			}

		}catch(Exception e) {
			e.printStackTrace();

		}

		finally{
			miStatement.close();
			RSprod_ped.close();
			miConexion.close();
		}
		
		
		
		
		return pedido;
	}



}
