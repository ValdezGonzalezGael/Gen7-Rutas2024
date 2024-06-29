package com.Valdez.app.rutas.controllers;

import com.Valdez.app.rutas.models.Camion;
import com.Valdez.app.rutas.models.Cargamento;
import com.Valdez.app.rutas.services.CamionesService;
import com.Valdez.app.rutas.services.CargamentoService;
import com.Valdez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/cargamentos/lista")
public class ListaCargamentosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=(Connection) req.getAttribute("conn");

        IService<Cargamento> service=new CargamentoService(conn);
        List<Cargamento> cargamentos = service.lista();

       /* for(Chofer c:choferes){
            resp.getWriter().println("<h1 style='color:blue; '>"+c.getId()+" ->"
                    +c.getNombre()+" ->"+c.getApPaterno()+"</h1>");
        }*/
        req.setAttribute("cargamentos",cargamentos);
        getServletContext().getRequestDispatcher("/listaCargamentos.jsp").forward(req,resp);
    }
}
