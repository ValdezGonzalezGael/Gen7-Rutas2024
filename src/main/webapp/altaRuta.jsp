<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.Valdez.app.rutas.models.*" %>
<%
//recuperamos la lista de choferes que obtuvimos en el request desde el servlet
List<Chofer> choferes= (List<Chofer>)request.getAttribute("choferes");
List<Camion> camiones= (List<Camion>)request.getAttribute("camiones");
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

    <style>
           #loadingMessage {
               display: none;
               margin-top: 20px;
               text-align: center;
           }
       </style>
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
                       <li><a href="<%=request.getContextPath()%>/choferes/lista">Lista Camiones</a></li>
                       <li><a href="<%=request.getContextPath()%>/camiones/alta">Alta Camion</a></li>
                   </ul>
               </li>

               <li class="dropdown">
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Rutas<span
                           class="caret"></span></a>
                   <ul class="dropdown-menu">
                       <li><a href="<%=request.getContextPath()%>/rutas/alta">Alta Camion</a></li>
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


    <div class="container body-content">
     <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyCWeeateTaYGqsHhNcmoDfT7Us-vLDZVPs&amp;sensor=false&amp;language=en"></script>
     <div class="row">
        <div class="col-md-12">
            <h2>Iniciar Ruta</h2>
        </div>
        <div style=" display: block;" >
            <input type="text" name="" id="txtEsOD" class="form-control">
        </div>
     </div>

     <div class="row">

            <div class="col-md-6">
                <div class="form-group">
                    <label for="">Chofer</label>
                    <select name="chofer" id="chofer" class="form-control">
                        <option value="">seleccionar</option>
                        <% for(Chofer c: choferes) { %>
                                <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                             <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-9">
                            <label for="">Origen</label>
                            <input type="text" name="origen" id="origen" class="form-control">
                            <input type="hidden" name="idOrigen" id="idOrigen" class="form-control">
                        </div>
                        <div class="col-md-3">
                            <button class="btn btn-primary btn-xs" style="margin-top: 30px;" onclick="getDireccion(1)">ObtenerDatos</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="">Fecha Salida</label>
                   <input type="text" name="FSalida" id="FSalida" class="form-control">
                </div>

                <div class="form-group">
                    <label for="">Distancia</label>
                   <input type="text" name="distancia" id="distancia" class="form-control">
                </div>

            </div>

        <div class="col-md-6">
            <div class="form-group">
                <label for="">Camion</label>
                <select name="camion" id="camion" class="form-control">
                    <option value="">seleccionar</option>
                    <% for(Camion c: camiones) { %>
                        <option value="<%=c.getId()%>" data-capacidad="<%=c.getCapacidad()%>"><%=c.getMatricula()%></option>
                         <% } %>
                </select>
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-9">
                        <label for="">Destino</label>
                        <input type="text" name="destino" id="destino" class="form-control">
                        <input type="hidden" name="idDestino" id="idDestino" class="form-control">
                    </div>
                    <div class="col-md-3">
                        <button class="btn btn-primary btn-xs" style="margin-top: 30px;"
                        onclick="getDireccion(2)">ObtenerDatos</button>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="">Fecha Estimada de Llegada</label>
               <input type="text" name="FELlegada" id="FELlegada" class="form-control">
            </div>

            <div class="form-group">
                <label for="">Capacidad camion</label>
               <input type="text" name="capCamion" id="capCamion" class="form-control" readonly>
            </div>

            <div class="form-group">
                <button class="btn-success">Iniciar Ruta</button>
            </div>
        </div>
     </div>
     <script>
        $(document).ready(function () {
            // Evento change del select #camion
            $('#camion').change(function () {
                // Obtener la capacidad seleccionada
                var capacidad = $(this).find(':selected').data('capacidad');

                // Mostrar la capacidad en el input #capCamion
                $('#capCamion').val(capacidad);
            });
        });
    </script>

    </div>
    <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="row">
                            <div class="col-md-12">
                                <h4>Guardar Direccion</h4>
                            </div>
                        </div>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="">Calle</label>
                                    <input type="text" name="Calle" id="Calle" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Numero</label>
                                    <input type="text" name="Numero" id="Numero" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Colonia</label>
                                    <input type="text" name="Colonia" id="Colonia" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Ciudad</label>
                                    <input type="text" name="Ciudad" id="Ciudad" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Estado</label>
                                    <input type="text" name="Estado" id="Estado" class="form-control">
                                </div>

                                <div class="form-group">
                                    <label for="">Cp</label>
                                    <input type="text" name="Cp" id="Cp" class="form-control">
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-md-10-offset-1">
                                    <div class="col-md-4">
                                        <div class="btn btn-success" onclick="btnGuardarDir()">Guardar</div>
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                        </div>
                                    </div>

                                    <div id="loadingMessage">
                                    <div class="spinner-border text-primary" role="status">
                                        <span class="sr-only">Cargando...</span>
                                    </div>
                                    <div>Cargando...</div>
                                    </div>

                                    <div class="con-md-4 col-md-offset-2">
                                        <button class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                    </div>

                                       <script>
                                            function btnGuardarDir() {
                                            var loadingMessage = document.getElementById('loadingMessage');
                                            loadingMessage.style.display = 'block';

                                            setTimeout(function() {
                                            loadingMessage.style.display = 'none';
                                            $('#myModal').modal('hide'); // Cerrar el modal después de 6 segundos
                                            }, 6000); // 6000 milisegundos = 6 segundos
                                            }
                                        </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>


<script>
            function LimpiarDatos(){
                    $('#Calle').val("");
                    $('#Numero').val("");
                    $('#Colonia').val("");
                    $('#Ciudad').val("");
                    $('#estado').val("");
                    $('#Cp').val("");

            }

            function getDireccion(fuente){
                LimpiarDatos();
                 $('#myModal').modal('show');
                 var direccion="";
                 if(fuente==1){
                        direccion=$('#origen').val();

                 }
                 else{
                        direccion=$('#destino').val();
                 }
                 $("#txtEsOD").val(fuente);
                 if(direccion!=""){
                        var geocoder=new google.maps.Geocoder();
                        geocoder.geocode({'address':direccion},function (results,status){
                            console.log(results);
                            console.log(status);
                            if(status== google.maps.GeocoderStatus.OK){
                                var numresults=results[0].address_components.length;
                                for(var indice=0;indice < numresults;indice++){
                                        var numresultstypes=
                                        results[0].address_components[indice].types.length;
                                    for(var propiedad=0;propiedad<numresultstypes;propiedad++){
                                            var Tipo=results[0].address_components[indice].types[propiedad];
                                            var Descripcion=
                                            results[0].address_components[indice].long_name;
                                            switch(Tipo){
                                                case "route":
                                                $("#Calle").val(Descripcion);
                                                break;
                                                case "street_number":
                                                $("#Numero").val(Descripcion);
                                                break;
                                                case "sublocality_level_1":
                                                $("#Colonia").val(Descripcion);
                                                break;
                                                case "locality":
                                                $("#Ciudad").val(Descripcion);
                                                break;
                                                case "administrative_area_level_1":
                                                $("#Estado").val(Descripcion);
                                                break;
                                                case "postal_code":
                                                $("#Cp").val(Descripcion);
                                                break;
                                            }
                                    }
                                }
                                    if(results[0].address_components.length>0){
                                        if(fuente==1){
                                            $("#origen").val(results[0].formatted_address);
                                        }
                                        else{
                                            $("#destino").val(results[0].formatted_address);
                                        }

                                    }
                            } else{
                                    alert("Google no obtuvo datos ");
                                }
                        });
                    }else{
                        alert("No podemos obtener datos sin no proporciona una direccion");
                        $("#myModal").modal('hide');
                    }
            }

            function btnGardarDir(){
                var calle = $('#Calle').val();
                var numero= $('#Numero').val();
                var colonia= $('#Colonia').val();
                var ciudad = $('#Ciudad').val();
                var estado = $('#estado').val();
                var cp = $('#Cp').val();

                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8080/Gen7-Api-2024/api/direcciones",
                    data: {"calle": calle, "numero": numero, "colonia": colonia, "ciudad":ciudad,
                        "estado":estado, "cp": cp},
                        //Esperar la respuesta del servidor
                        success:function(resp){
                            //mostrar respuesta
                            console.log(resp);
                                $('#myModal').modal('hide');
                                if($('#txtEsOD').val() == 1 ){//es origen
                                    $("#idorigen").val(resp.message);
                                }
                                else{//es destino
                                    $("#idDestino").val(resp.message);
                                }
                        }
                        //habilitar el boton
                });
            }
     </script>
</body>
</html>