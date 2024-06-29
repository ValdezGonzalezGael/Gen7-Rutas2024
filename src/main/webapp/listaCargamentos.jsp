<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.Valdez.app.rutas.models.*" %>

<%
    // Recuperamos la lista de cargamentos que obtuvimos en el request desde el servlet
    List<Cargamento> cargamentos = (List<Cargamento>) request.getAttribute("cargamentos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Cargamentos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
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
                       aria-haspopup="true" aria-expanded="false">Choferes<span class="caret"></span></a>
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
                       aria-haspopup="true" aria-expanded="false">Rutas<span class="caret"></span></a>
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
                        <li><a href="<%=request.getContextPath()%>/cargamentos/alta">Lista Cargamentos</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-xs-6">
            <h2>Listado de Cargamentos</h2>
        </div>
        <div class="col-xs-6 text-right">
            <a href="<%=request.getContextPath()%>/cargamentos/alta" class="btn btn-success">Alta Cargamento</a>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID_CARGAMENTO</th>
                            <th>ID_RUTA</th>
                            <th>DESCRIPCIÓN</th>
                            <th>PESO</th>
                            <th>ESTATUS</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (cargamentos != null && !cargamentos.isEmpty()) {
                            for(Cargamento ca : cargamentos) { %>
                                <tr>
                                    <td><%= ca.getId_Cargamento() %></td>
                                    <td><%= ca.getRuta_id() %></td>
                                    <td><%= ca.getDescripcion() %></td>
                                    <td><%= ca.getPeso() %></td>
                                    <td><%= ca.getEstatus() %></td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/cargamentos/detalle?id=<%= ca.getId_Cargamento() %>" class="btn btn-success btn-sm">Detalle</a>
                                        <a href="<%=request.getContextPath()%>/cargamentos/editar?id=<%= ca.getId_Cargamento() %>" class="btn btn-primary btn-sm">Editar</a>
                                        <a href="<%=request.getContextPath()%>/cargamentos/eliminar?id=<%= ca.getId_Cargamento() %>" class="btn btn-danger btn-sm">Eliminar</a>
                                    </td>
                                </tr>
                        <% }} else { %>
                                <tr>
                                    <td colspan="6" class="text-center">No hay cargamentos disponibles</td>
                                </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
