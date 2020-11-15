<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
<c:choose> 
  <c:when test="${sessionScope.usuario!=null}">
   <c:set var="usuario" value="${sessionScope.usuario}"/>
   <c:set var="nivel" value="${sessionScope.nivel}"/>
    <!--<c:out value = "${'validado'}"/>-->
  </c:when>
  
  <c:otherwise>
    <c:redirect url = "/sesion/login.jsp"/>
  </c:otherwise>
</c:choose>

<c:if test="${usuario!=null && nivel==1}">					 
    <c:redirect url = "../sesion/denegado.jsp"/>					
</c:if>	


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Sistema de Gestion: Distribuidora de Calzados</title>

<link rel="stylesheet" href="./css/bootstrap.min.css" />

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="ControladorInicio?instruccion=noticia">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-shoe-prints"></i>
				</div>
				<div class="sidebar-brand-text mx-3">
					Distribuidora de Calzados <sup>1</sup>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Inicio -->
			<li class="nav-item active"><a class="nav-link"
				href="ControladorInicio?instruccion=noticia"> <i
					class="far fa-newspaper"></i> <span>Inicio</span></a></li>

			<li class="nav-item active"><a class="nav-link"
				href="ControladorInicio?instruccion=tablero"> <i
					class="far fa-edit"></i> <span>Tablero</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Menu</div>

			<!-- Nav Item - CLIENTES -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#MenuCliente"
				aria-expanded="true" aria-controls="MenuCliente"> <i
					class="fas fa-users"></i> <span>Clientes</span>
			</a>
				<div id="MenuCliente" class="collapse"
					aria-labelledby="cabeceraCliente" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Opciones</h6>
						<a class="collapse-item" href="clientes/agregar.jsp">Nuevo
							cliente</a> <a class="collapse-item"
							href="ControladorCliente?instruccion=listar">Ver listado</a> <a
							class="collapse-item"
							href="ControladorCliente?instruccion=modificar">Modificar</a>
					</div>
				</div></li>

			<!-- Nav Item - PEDIDOS -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#MenuPedido"
				aria-expanded="true" aria-controls="MenuPedido"> <i
					class="fas fa-shopping-basket"></i> <span>Pedidos</span>
			</a>
				<div id="MenuPedido" class="collapse"
					aria-labelledby="cabeceraPedido" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Opciones</h6>
						<a class="collapse-item"
							href="ControladorPedido?instruccion=nuevo">Nuevo pedido</a> <a
							class="collapse-item" href="ControladorPedido?instruccion=listar">Ver listado</a>
					</div>
				</div></li>


			<!-- Nav Item - PRODUCTOS -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#MenuProducto"
				aria-expanded="true" aria-controls="MenuProducto"> <i
					class="fab fa-product-hunt"></i> <span>Productos</span>
			</a>
				<div id="MenuProducto" class="collapse"
					aria-labelledby="cabeceraProducto" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Opciones</h6>
						
						 <c:if test="${nivel == 2 || nivel ==3 }">
						 
						 <a class="collapse-item" href="productos/agregarProducto.jsp">Nuevo producto</a> 
						
						</c:if>	
						
						 <a class="collapse-item"
							href="ControladorProducto?instruccion=listar">Ver listado</a>
							
							
						 <c:if test="${nivel == 2 || nivel ==3 }">
							
						<a class="collapse-item"
							href="ControladorProducto?instruccion=modificar">Modificar</a>
							
						   </c:if>	
					</div>
				</div></li>
       
        <c:if test="${nivel ==3 }">
			<!-- Divider -->
			<hr class="sidebar-divider">
			<!-- Heading -->
			<div class="sidebar-heading">Utilidades</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <span>Administracion Web</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Opciones</h6>
						<a class="collapse-item" href="/ControladorAdministracion">Usuarios</a>
					</div>
				</div></li>
 
          </c:if>	


			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) ------------>
					<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">


						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small"><c:out value="${usuario}"/></span>
								<img class="img-profile rounded-circle" src="img/usuario.png">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								   <a class="dropdown-item" href="/sesion/perfil.jsp"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
								</a> 
							<!--  <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Configuracion
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
									Registro Actividad
								</a>  -->
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Salir
								</a>
							</div></li>

					</ul>

				</nav>

				<!-- End of Topbar -->







				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Content Row -->
					<div class="row">

                     <div class="col-10">


                  <%-- Formulario para modificar un nuevo Cliente--%>
                	<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Lista de productos</h1>
						
					</div>
 
                      <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="form-group">
									<form name="modificacion" method="post" action="productos/modificandoProducto.jsp">
										<select name="listaProducto" id="listaProducto" class="form-control">
											<option selected="selected" disabled selected>-SELECCIONE PRODUCTO-</option>
											<c:forEach var="tempProducto" items="${LISTAPRODUCTOS}">
												<option
													value="${tempProducto.cod_calzado}/${tempProducto.tipo}/${tempProducto.nombre}/${tempProducto.precio}/${tempProducto.fabricante}/${tempProducto.talla}/${tempProducto.stock}">${tempProducto.nombre}</option>
											</c:forEach>
										</select> <input type="submit" name="submit" id="submit" value="Modificar" class="btn btn-primary">
									</form>
								</div>
								</div>
                        <div class="card-body">

													<div class="form-group row">
 													 <label for="codigoProducto" class="col-sm-4 col-form-label">Codigo Producto</label>
 													 <div class="col-sm-8">
 													 <input type="text" class="form-control" input type="text" name="codigoProducto" id="codigoProducto" placeholder="Codigo Producto" readonly>
 													 </div>
 												 </div>

												 <div class="form-group row">
													 <label for="tipo" class="col-sm-4 col-form-label">Tipo</label>
													 <div class="col-sm-8">
													 <input type="text" class="form-control" input type="text" name="tipo" id="tipo" placeholder="Tipo" readonly>
													 </div>
												 </div>

	                        <div class="form-group row">
	                          <label for="nombre" class="col-sm-4 col-form-label">Nombre</label>
	                          <div class="col-sm-8">
	                          <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" readonly>
	                          </div>
	                        </div>

													<div class="form-group row">
														<label for="fabricante" class="col-sm-4 col-form-label">Fabricante</label>
														<div class="col-sm-8">
														<input type="text" class="form-control" id="fabricante" name="fabricante" placeholder="Fabricante" readonly>
														</div>
													</div>


	                        <div class="form-group row">
	                          <label for="precio" class="col-sm-4 col-form-label">Precio</label>
	                          <div class="col-sm-8">
	                          <input type="number" min="0" max="99999" class="form-control" id="precio" name="precio" placeholder="Precio" readonly>
	                          </div>
	                        </div>



	                        <div class="form-group row">
	                          <label for="talla" class="col-sm-4 col-form-label">Talla</label>
	                          <div class="col-sm-8">
	                          <input type="number" min="0" max="60" class="form-control" id="talla" name="talla" placeholder="Talla"  readonly>
	                          </div>
	                        </div>



	                        <div class="form-group row">
	                          <label for="stock" class="col-sm-4 col-form-label">Stock</label>
	                          <div class="col-sm-8">
	                          <input type="number" min="0" max="99999" class="form-control" id="stock" name="stock" placeholder="Stock" readonly>
	                          </div>
	                        </div>

                        </div>

                      </div>

                    </div>

                  </div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span> Desarrollado por Carlos Peña </span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">¿Listo para
						salir?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
					</button>
				</div>
				<div class="modal-body">Selecciona "Salir" si estas listo para
					cerrar la sesion actual.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancelar</button>
				<a class="btn btn-primary" href="ControladorSesion?instruccion=cerrar">Salir</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="vendor/chart.js/Chart.min.js"></script>



</body>
</html>
