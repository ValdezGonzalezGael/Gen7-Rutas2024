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
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/choferes/alta")
public class AltaChoferSevlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(conn);
        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String licencia = req.getParameter("licencia");
        String telefono = req.getParameter("telefono");
        String fechaNacimiento = req.getParameter("fechaNacimiento");

        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaNacimiento);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fecha.format(formatter);

        }catch (DateTimeException e){
            fecha = null;
        }
        String checkbox[];
        checkbox = req.getParameterValues("disponibilidad");
        Boolean habilitar;
        if (checkbox !=null){
            habilitar = true;
        }
        else {
            habilitar = false;
        }
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()){
            errores.put("nombre", "el nombre es requerido!");
        }
        if (apPaterno == null || apPaterno.isBlank()){
            errores.put("apPaterno", "el Apellido paterno es requerido!");
        }
        if (apMaterno == null || apPaterno.isBlank()){
            errores.put("apMaterno", "el Apellido Materno es requerido!");
        }
        if (licencia == null || licencia.isBlank()){
            errores.put("licencia", "la licencia es requerido!");
        }
        if (telefono == null || telefono.isBlank()){
            errores.put("telefono", "el telefono es requerido!");
        }
        if (fechaNacimiento == null || fechaNacimiento.isBlank()){
            errores.put("fechaNacimiento ", "fecha Nacimiento  es requerido!");
        }
        if (errores.isEmpty()){
            Chofer chofer = new Chofer();
            chofer.setId(0L);
            chofer.setNombre(nombre);
            chofer.setApPaterno(apPaterno);
            chofer.setApMaterno(apMaterno);
            chofer.setLicencia(licencia);
            chofer.setTelefono(telefono);
            chofer.setFechaNacimiento(fecha);
            chofer.setDisponibilidad(habilitar);
            service.guardar(chofer);
            resp.sendRedirect(req.getContextPath()+"/choferes/lista");

        }else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaChofer.jsp")
                    .forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaChofer.jsp").forward(req, resp);
    }
}
