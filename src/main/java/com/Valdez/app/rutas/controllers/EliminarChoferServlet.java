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

@WebServlet("/choferes/eliminar")
public class EliminarChoferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            id = 0l;
        }
        if (id > 0) {
            Optional<Chofer> optionalChofer = service.getById(id);
            if (optionalChofer.isPresent()) {
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath()+ "/choferes/lista");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el chofer en la BDD");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error en el ID");
        }
    }
}
