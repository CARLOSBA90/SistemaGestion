<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Sistema Gestion - Login</title>

  <!-- Custom fonts for this template-->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="../css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-5 col-lg-6 col-md-5">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Registro de Usuario</h1>
                  </div>
                  <form class="user" name="form1" method="post" action="../ControladorSesion">
										<input type="hidden" name="instruccion" value="registrar">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="usuario" name="usuario" placeholder="Nuevo Usuario">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="contrasena" name="contrasena" placeholder="Contrasena">
                    </div>
                      <div class="form-group">
                      <input type="email" class="form-control form-control-user" id="email" name="email" placeholder="Correo">
                    </div>
                    
                     <div class="form-group">
                       <label for="nivel">Nivel</label>
                        <select class="form-control" id="nivel" name="nivel">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        </select>
                     </div>

                    <button type="submit" class="btn btn-primary btn-user btn-block">Crear usuario</button>
                     </form>
                   
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <c:if test="${mensaje}">
            <div class="alert alert-danger" role="alert">
              Usuario existente, intente con usuario diferente!
             </div>
        </c:if>  
      </div>

    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="../vendor/jquery/jquery.min.js"></script>
  <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="../js/sb-admin-2.min.js"></script>

</body>

</html>
