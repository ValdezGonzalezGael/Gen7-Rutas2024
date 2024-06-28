<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.piedra.rutas.models.*" %>
<%@page import="java.time.format.*" %>
<%@page import="com.Valdez.app.rutas.models.enums.*" %>
<%

Camion camion=(Camion) request.getAttribute("camion");
Boolean estado= camion.getDisponibilidad();
String disponible = estado != null ? "checked" : "";
Map<String,String> errores=(Map<String,String>) request.getAttribute("errores");

   List<Marcas> marcas= new ArrayList<>(Arrays.asList(Marcas.VOLVO,Marcas.ALLIANCE,Marcas.FORD,Marcas.MERCEDES_BENZ,Marcas.DINA,Marcas.BMW));
 List<Tipos> tipos= new ArrayList<>(Arrays.asList(Tipos.TRAILER,Tipos.TORTON,Tipos.DOBLE_REMOLQUE,Tipos.VOLTEO,Tipos.SEMI_REMOLQUE));

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
                        <input type="text" value="<%=camion.getMatricula() !=null ?camion.getMatricula():"" %>"  name="matricula" id="matricula" class="form-control">

                        <%
                        if(errores!=null && errores.containsKey("matricula")){
                            out.println("<span class='text-danger'>"+errores.get("matricula")+"</span>");
                        }
                        %>
                    </div>
                        <div class="form-group">
                            <label for="">Marca</label>
                            <select class="form-select" name="marca" id="marca">
                             <% for (int i = 0; i < marcas.size(); i++) { %>
                               <option value="<%= marcas.get(i) %>" <%= camion.getMarca() != null && camion.getMarca().equals(marcas.get(i)) ? "selected" : "" %>><%= marcas.get(i) %></option>
                              <% } %>
                             </select>
                            <%
                            if(errores!=null && errores.containsKey("marca")){
                                out.println("<span class='text-danger'>"+errores.get("marca")+"</span>");
                            }
                            %>
                        </div>
                    <div class="form-group">
                        <label for="">Tipo Camion</label>
                        <select class="form-select" name="tipoCamion" id="tipoCamion">
                         <% for (int i = 0; i < tipos.size(); i++) { %>
                           <option value="<%= tipos.get(i) %>" <%= camion.getTipocamion() != null && camion.getTipocamion().equals(tipos.get(i)) ? "selected" : "" %>><%= tipos.get(i) %></option>
                          <% } %>
                         </select>
                        <%
                        if(errores!=null && errores.containsKey("tipoCamion")){
                            out.println("<span class='text-danger'>"+errores.get("tipoCamion")+"</span>");
                        }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Modelo</label>
                        <select class="form-select" name="modelo" id="modelo">
                        <% for(int i = 2000; i <= 2024; i++) { %>
                          <option value="<%= i %>"><%= i %></option>
                             <option value="<%= i %>" <%= camion.getModelo() != null && camion.getModelo().equals(i) ? "selected" : "" %>><%= i %></option>

                        <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="">Capacidad</label>
                        <input type="text" value="<%=camion.getCapacidad() !=null ? camion.getCapacidad(): "" %>"  name="capacidad" id="capacidad" class="form-control">
                        <%
                        if(errores!=null && errores.containsKey("capacidad")){
                            out.println("<span class='text-danger'>"+errores.get("capacidad")+"</span>");
                        }
                        %>
                    </div>


                    <div class="form-group">
                        <label for="">Kilometraje</label>
                        <input type="text" value="<%=camion.getKilometraje() %>"  name="kilometraje" id="kilometraje" class="form-control">
                        <%
                        if(errores!=null && errores.containsKey("kilometraje")){
                            out.println("<span class='text-danger'>"+errores.get("kilometraje")+"</span>");
                        }
                        %>
                    </div>
                    <div class="form-group">
                        <label for="">Disponibilidad</label>
                        <input type="checkbox"  value="1" name="disponibilidad" id="disponibilidad" class="form-check-input"  <%=disponible %> >
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
