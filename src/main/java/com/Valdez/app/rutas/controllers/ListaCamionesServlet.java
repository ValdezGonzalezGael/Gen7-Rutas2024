package com.Valdez.app.rutas.controllers;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.services.CamionesService;
import com.Valdez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;


@WebServlet("/camiones/listar")
public class ListaCamionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos  la conexion que provee el filtro
        Connection conn= (Connection) req.getAttribute("conn");

        //Declaramos un objeto de tipo servicio
        IService<Camion> service = new CamionesService(conn);
        List<Camion> camiones =service.listar();
//        for (Chofer c:choferes){
//            resp.getWriter().println("<h1>"+c.getId()+" ->"
//            +c.getNombre()+" ->"+c.getApPaterno()+"</h1>");
//        }
        req.setAttribute("camiones", camiones);
        getServletContext().getRequestDispatcher("/listaCamiones.jsp").forward(req, resp);
    }
}
