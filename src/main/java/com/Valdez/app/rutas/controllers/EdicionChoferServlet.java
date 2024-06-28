package com.Valdez.app.rutas.controllers;

import com.Valdez.app.rutas.models.Chofer;
import com.Valdez.app.rutas.services.ChoferesService;
import com.Valdez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@WebServlet("/choferes/editar")
public class EdicionChoferServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=(Connection) req.getAttribute("conn");
        IService<Chofer> service=new ChoferesService(conn);
        String nombre=req.getParameter("nombre");
        String apPaterno=req.getParameter("apPaterno");
        String apMaterno=req.getParameter("apMaterno");
        String licencia=req.getParameter("licencia");
        String telefono=req.getParameter("telefono");
        String fechaNacimiento=req.getParameter("fechaNacimiento");
        LocalDate fecha;
        try{
            fecha=LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        }catch (DateTimeException e){
            fecha=null;
        }
        String checkbox[];
        checkbox=req.getParameterValues("disponibilidad");
        Boolean habilitar;
        if(checkbox!=null){
            habilitar=true;
        }
        else{
            habilitar=false;
        }
        Map<String,String> errores=new HashMap<>();
        if(nombre==null|| nombre.isBlank()){
            errores.put("nombre","el nombre es requerido!");
        }
        if(apPaterno==null|| apPaterno.isBlank()){
            errores.put("apPaterno","el Apellido Paterno es requerido!");
        }
        if(apMaterno==null|| apMaterno.isBlank()){
            errores.put("apMaterno","el Apellido Materno es requerido!");
        }
        if(licencia==null|| licencia.isBlank()){
            errores.put("licencia","La licencia es requerida!");
        }
        if(telefono==null|| telefono.isBlank()){
            errores.put("telefono","El telefono es requerida!");
        }
        if(fechaNacimiento==null|| fechaNacimiento.isBlank()){
            errores.put("fechanacimiento","La fecha de nacimiento es requerida!");
        }
        long id;
        id=Long.parseLong(req.getParameter("id"));
        Chofer chofer=new Chofer();
        chofer.setId(id);
        chofer.setNombre(nombre);
        chofer.setApPaterno(apPaterno);
        chofer.setApMaterno(apMaterno);
        chofer.setLicencia(licencia);
        chofer.setTelefono(telefono);
        chofer.setFechaNacimiento(fecha);
        chofer.setDisponibilidad(habilitar);
        if(errores.isEmpty()){

            service.guardar(chofer);
            resp.sendRedirect(req.getContextPath()+"/choferes/lista");

        }
        else{
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/edicionChofer.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(connection);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException ex){
            id = 0L;
        }
        Chofer chofer = new Chofer();
        if (id>0){
            Optional<Chofer> optionalDriver = service.getById(id);
            if (optionalDriver.isPresent()){
                chofer = optionalDriver.get();
                req.setAttribute("chofer", chofer);
                getServletContext().getRequestDispatcher("/edicionChofer.jsp").forward(req, resp);
            }else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el chofer en la BDD");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error en el ID");
        }
    }
}
