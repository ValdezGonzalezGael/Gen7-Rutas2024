package com.Valdez.app.rutas.controllers;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.models.enums.Marcas;
import com.Valdez.app.rutas.models.enums.Tipos;
import com.Valdez.app.rutas.services.CamionesService;
import com.Valdez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/camiones/editar")
public class EdicionCamionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn=(Connection) req.getAttribute("conn");
        IService<Camion> service=new CamionesService(conn);
        String matricula=req.getParameter("matricula");
        String tipoCamion=req.getParameter("tipoCamion");
        String modelo=req.getParameter("modelo");
        String marca=req.getParameter("marca");
        String capacidad=req.getParameter("capacidad");
        String kilometraje=req.getParameter("kilometraje");

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
        if(matricula==null|| matricula.isBlank()){
            errores.put("matricula","el matricula es requerido!");
        }
        if(tipoCamion==null|| tipoCamion.isBlank()){
            errores.put("tipocamion","el tipo de camion es requerido!");
        }
        if(marca==null|| marca.isBlank()){
            errores.put("marca","la marcao es requerido!");
        }
        if(capacidad==null|| capacidad.isBlank()){
            errores.put("capacidad","La capacidad es requerida!");
        }
        if(kilometraje==null|| kilometraje.isBlank()){
            errores.put("kilometrake","el kilometraje es requerida!");
        }

        long id;
        id=Long.parseLong(req.getParameter("id"));
        Camion camion=new Camion();
        camion.setId(id);
        camion.setMatricula(String.valueOf(matricula));
        camion.setMarca(Marcas.valueOf(marca
        ));
        camion.setKilometraje(Float.valueOf(kilometraje));
        camion.setCapacidad(Integer.valueOf(capacidad));
        camion.setModelo(Integer.valueOf(modelo));
        camion.setTipoCamion(Tipos.valueOf(tipoCamion));
        camion.setDisponibilidad(habilitar);
        if(errores.isEmpty()){

            service.guardar(camion);
            resp.sendRedirect(req.getContextPath()+"/camiones/lista");

        }
        else{
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/edicionCamion.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn= (Connection) req.getAttribute("conn");
        IService<Camion> service= new CamionesService(conn);
        long id;
        try{
            id=Long.parseLong(req.getParameter("id"));

        }catch (NumberFormatException e){
            id=0L;
        }
        Camion camion=new Camion();
        if(id>0){
            Optional<Camion> o =service.getById(id);
            if(o.isPresent()){
                camion=o.get();
                req.setAttribute("camion",camion);
                getServletContext().getRequestDispatcher("/edicionCamion.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe en camion de la base de datos");
            }

        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"El id es null, se debe enviar como parametro en la url");

        }
}}
