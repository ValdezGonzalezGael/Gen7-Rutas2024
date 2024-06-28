package com.Valdez.app.rutas.controllers;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.services.CamionesService;
import com.Valdez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

public class DetalleCamionServlet extends HttpServlet {
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
        Camion camiones= new Camion();
        if(id>0){
            Optional<Camion> o =service.getById(id);
            if(o.isPresent()){
                camiones=o.get();
                System.out.println("camion:" + camiones.getId());
                req.setAttribute("camiones", camiones);
                getServletContext().getRequestDispatcher("/detalleCamion.jsp").forward(req,resp);
            }
            else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No se a encontrado un camion de la base de datos");
            }

        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"El id no es existente, se verifique sus datos");

        }
    }
}
