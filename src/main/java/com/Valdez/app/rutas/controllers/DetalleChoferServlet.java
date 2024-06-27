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
import java.util.Optional;

@WebServlet("/choferes/detalle")
public class DetalleChoferServlet extends HttpServlet {
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
                getServletContext().getRequestDispatcher("/detalleChofer.jsp").forward(req, resp);
            }else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el chofer en la BDD");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error en el ID");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}


