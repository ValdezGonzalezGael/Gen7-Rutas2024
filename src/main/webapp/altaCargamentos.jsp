<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<%
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta Cargamento</title>
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
                       aria-haspopup="true" aria-expanded="false">Camiones<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/camiones/lista">Lista Camiones</a></li>
                       <li><a href="<%=request.getContextPath()%>/camiones/alta">Alta Camiones</a></li>
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

               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Cargamento<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/cargamentos/alta">Alta Cargamentos</a></li>
                       <li><a href="<%=request.getContextPath()%>/cargamentos/lista">Lista Cargamentos</a></li>
                   </ul>
               </li>
           </ul>
       </div><!-- /.navbar-collapse -->
   </div><!-- /.container-fluid -->
</nav>
<div class="container">
    <h2>Formulario Alta Cargamento</h2>
    <% if (errores != null && errores.size() > 0) { %>
        <ul class="alert alert-danger">
            <% for (String error : errores.values()) { %>
                <li><%= error %></li>
            <% } %>
        </ul>
    <% } %>

    <form action="<%= request.getContextPath() %>/cargamentos/alta" method="post">
        <div class="form-group">
            <label for="rutaId">ID Ruta</label>
            <input type="text" name="rutaId" id="rutaId" class="form-control" value="<%= request.getAttribute("rutaId") != null ? request.getAttribute("rutaId") : "" %>">
            <% if (errores != null && errores.containsKey("rutaId")) { %>
                <span class="text-danger"><%= errores.get("rutaId") %></span>
            <% } %>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <input type="text" name="descripcion" id="descripcion" class="form-control" value="<%= request.getAttribute("descripcion") != null ? request.getAttribute("descripcion") : "" %>">
            <% if (errores != null && errores.containsKey("descripcion")) { %>
                <span class="text-danger"><%= errores.get("descripcion") %></span>
            <% } %>
        </div>
        <div class="form-group">
            <label for="peso">Peso</label>
            <input type="text" name="peso" id="peso" class="form-control" value="<%= request.getAttribute("peso") != null ? request.getAttribute("peso") : "" %>">
            <% if (errores != null && errores.containsKey("peso")) { %>
                <span class="text-danger"><%= errores.get("peso") %></span>
            <% } %>
        </div>
        <div class="form-group">
            <label for="estatus">Estatus</label>
            <input type="text" name="estatus" id="estatus" class="form-control" value="<%= request.getAttribute("estatus") != null ? request.getAttribute("estatus") : "" %>">
            <% if (errores != null && errores.containsKey("estatus")) { %>
                <span class="text-danger"><%= errores.get("estatus") %></span>
            <% } %>
        </div>
        <button type="submit" class="btn btn-success">Guardar</button>
    </form>
</div>
</body>
</html>
