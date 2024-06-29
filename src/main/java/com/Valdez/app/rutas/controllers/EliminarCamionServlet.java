package com.Valdez.app.rutas.controllers;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.services.CamionesService;
import com.Valdez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/camiones/eliminar")
public class EliminarCamionServlet {
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
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/camiones/lista");
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe en Camion de la base de datos ss");
            }

        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"El id es null, se debe enviar como parametro en la url");

        }
    }
}
