<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.Valdez.app.rutas.models.*" %>
<%@page import="java.time.format.*" %>
<%
Camion camiones=(Camion) request.getAttribute("camiones");
Boolean estado= camiones.getDisponibilidad();
String disponible = estado != null ? "Disponible" : "No disponible";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<body>
<nav class="navbar navbar-inverse">
   <div class="container-fluid">
       <!-- Brand and toggle get grouped for better mobile display -->
       <div class="navbar-header" id="div1">
           <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
               data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
               <span class="sr-only">Toggle navigation</span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
               <span class="icon-bar"></span>
           </button>
           <a class="navbar-brand" href="#" id="enlace1">Rutas App</a>
       </div>

       <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
           <ul class="nav navbar-nav">
               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Choferes<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/choferes/lista">Lista Choferes</a></li>
                       <li><a href="<%=request.getContextPath()%>/choferes/alta">Alta Chofer</a></li>
                   </ul>
               </li>

               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Camiones<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/camiones/lista">Lista Camiones</a></li>
                       <li><a href="<%=request.getContextPath()%>/camiones/alta">Alta Camion</a></li>
                   </ul>
               </li>

               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Rutas<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/rutas/alta">Alta Ruta</a></li>
                   </ul>
               </li>
           </ul>
       </div><!-- /.navbar-collapse -->
   </div><!-- /.container-fluid -->
</nav>


    <div class="container">

        <div class="row">

            <div class="col-12">
                <div class="card border">
                    <div class="card-header">
                        <h3><strong>Detalle de Camion</strong></h3>
                    </div>
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Matricula: </strong><%=camiones.getMatricula() %></li>
                        <li class="list-group-item"><strong>Tipo Camion: </strong><%=camiones.getTipoCamion() %></li>
                        <li class="list-group-item"><strong>Modelo: </strong><%=camiones.getModelo() %></li>
                        <li class="list-group-item"><strong>Marca:</strong><%=camiones.getMarca() %></li>
                        <li class="list-group-item"><strong>Capacidad:</strong><%=camiones.getCapacidad() %></li>
                        <li class="list-group-item"><strong>Kilometraje: </strong><%=camiones.getKilometraje() %></li>
                        <li class="list-group-item"><strong>Disponibilidad</strong><%=camiones.getDisponibilidad() %></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>