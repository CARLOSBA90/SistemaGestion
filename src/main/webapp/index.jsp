<%@ page import = "java.io.*,java.util.*" %>

<html>
   <body>   
      <%
         // Redireccion
         String site = new String("/ControladorInicio");
         response.setStatus(response.SC_MOVED_TEMPORARILY);
         response.setHeader("Location", site); 
      %>
   </body>
</html>