<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.Valdez.app.rutas.models.*" %>
<%@page import="java.time.format.*" %>
<%@page import="com.Valdez.app.rutas.models.enums.*" %>
<%

Camion camion = (Camion) request.getAttribute("camion");
Boolean estado = camion.getDisponibilidad();
String disponible = estado != null ? "checked" : "";
Map<String,String> errores=(Map<String,String>) request.getAttribute("errores");
List<Integer> listaAnios = (List<Integer>) request.getAttribute("modelos");
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
                       <li><a href="<%=request.getContextPath()%>/choferes/alta">Alta Ruta</a></li>
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
        <div class="row">
            <div class="col-12">
                <h2>Formulario Editar Camion</h2>

            </div>
        </div>
        <br>
        <% if(errores!=null && errores.size()>0){ %>
            <ul class="alert alert-danger mx-t px-5"
            <% for(String error: errores.values()){%>
            <li><%=error%></li>
            <%} %>
            </ul>
            <%}%>
        <div class="row">
            <form action="<%=request.getContextPath()%>/camiones/editar" method="post">

                <input type="hidden" value="<%=camion.getId() %>"  name="id" id="id">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="">Matricula</label>
                            <input type="text" name="matricula" id="matricula" class="form-control" value="<%=camion.getMatricula() != null ? camion.getMatricula() : ""%>">
                            <%
                                if (errores != null && errores.containsKey("matricula")){
                                    out.println("<span class='text-danger'>"+ errores.get("matricula")+"</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="tipoCamion">Tipo Camion</label>
                            <select name="tipoCamion" id="tipoCamion" class="form-control">
                                <option class="">Selecciona un tipo de camion</option>
                                <% for(Tipos t : Tipos.values()){ %>
                                 <option value="<%= t %>"<%= t.equals(camion.getTipoCamion()) ? "selected" : ""%>><%= t %></option>
                                <% } %>
                            </select>
                            <%
                                if (errores != null && errores.containsKey("tipoCamion")){
                                    out.println("<span class='text-danger'>"+ errores.get("tipoCamion")+"</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="marca">Marca</label>
                            <select name="marca" id="marca" class="form-control">
                            <option class="">Selecciona una marca de camion</option>
                             <% for(Marcas m : Marcas.values()){ %>
                                <option value="<%= m %>"<%= m.equals(camion.getMarca()) ? "selected" : ""%>><%= m %></option>
                             <% } %>
                              </select>
                              <%
                              if (errores != null && errores.containsKey("marca")){
                                out.println("<span class='text-danger'>"+ errores.get("marca")+"</span>");
                                  }
                              %>

                        <div class="form-group">
                            <label for="modelo">Modelo</label>
                                <select name="modelo" id="modelo" class="form-control">

                                    <%
                                        List<Integer> modelos = (List<Integer>) request.getAttribute("modelos");
                                        if (modelos != null) {
                                            for (Integer modelo : modelos ) {
                                    %>
                                      <option value="<%= modelo %>"<%= modelo.equals(camion.getModelo()) ? "selected" : ""%>><%= modelo %></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                         </div>

                        <div class="form-group">
                            <label for="">Capacidad</label>
                            <input type="number" name="capacidad" id="capacidad" class="capacidad" value="<%=camion.getCapacidad() != null ? camion.getCapacidad() : ""%>">
                            <%
                                if (errores != null && errores.containsKey("capacidad")){
                                    out.println("<span class='text-danger'>"+ errores.get("capacidad")+"</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="">Kilometraje</label>
                            <input type="number" name="kilometraje" id="kilometraje" class="form-control" value= "<%=camion.getKilometraje() != null ? camion.getKilometraje() : ""%>">
                            <%
                                if (errores != null && errores.containsKey("kilometraje")){
                                    out.println("<span class='text-danger'>"+ errores.get("kilometraje")+"</span>");
                                }
                            %>
                        </div>

                        <div class="form-group">
                            <label for="">Disponibilidad</label>
                            <input type="checkbox" name="disponibilidad" id="disponibilidad" class="form-check-input" value="1" <%= disponible%>>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-success">Guardar</button>
                        </div>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
