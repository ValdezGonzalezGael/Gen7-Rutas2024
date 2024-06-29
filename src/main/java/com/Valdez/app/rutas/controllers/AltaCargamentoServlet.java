package com.Valdez.app.rutas.controllers;
import com.Valdez.app.rutas.models.Cargamento;
import com.Valdez.app.rutas.services.CargamentoService;
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

@WebServlet("/cargamentos/alta")
public class AltaCargamentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaCargamentos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Cargamento> service = new CargamentoService(conn);

        String rutaId = req.getParameter("rutaId");
        String descripcion = req.getParameter("descripcion");
        String peso = req.getParameter("peso");
        String estatus = req.getParameter("estatus");

        Map<String, String> errores = new HashMap<>();

        if (rutaId == null || rutaId.isBlank()) {
            errores.put("rutaId", "El ID de Ruta es requerido.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            errores.put("descripcion", "La descripción es requerida.");
        }
        if (peso == null || peso.isBlank()) {
            errores.put("peso", "El peso es requerido.");
        }
        if (estatus == null || estatus.isBlank()) {
            errores.put("estatus", "El estatus es requerido.");
        }

        if (errores.isEmpty()) {
            Cargamento cargamento = new Cargamento();
            cargamento.setRuta_id((long) Integer.parseInt(rutaId));
            cargamento.setDescripcion(descripcion);
            cargamento.setPeso((float) Double.parseDouble(peso));
            cargamento.setEstatus(Integer.valueOf(estatus));

            service.guardar(cargamento);
            resp.sendRedirect(req.getContextPath() + "/cargamentos/lista");
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaCargamentos.jsp").forward(req, resp);
        }
    }
}

