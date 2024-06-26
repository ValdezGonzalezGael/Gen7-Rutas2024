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

@WebServlet("/camiones/alta")
public class AltaCamionesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        IService<Camion> service = new CamionesService(conn);
        String matricula = req.getParameter("matricula");
        String tipo_camion = req.getParameter("tipo_camion");
        String modelo = req.getParameter("modelo");
        String marca = req.getParameter("marca");
        String capacidad = req.getParameter("capacidad");
        String kilometraje = req.getParameter("kilometraje");

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
        if (matricula == null || matricula.isBlank()){
            errores.put("matricula", "la matricula es requerido!");
        }
        if (tipo_camion == null || tipo_camion.isBlank()){
            errores.put("tipo_camion", "el camion es requerido!");
        }
        if (modelo == null || modelo.isBlank()){
            errores.put("modelo", "el modelo es requerido!");
        }
        if (marca == null || marca.isBlank()){
            errores.put("marca", "la marca es requerido!");
        }
        if (capacidad == null || capacidad.isBlank()){
            errores.put("capacidad", "la capccidad es requerido!");
        }
        if (kilometraje == null || kilometraje.isBlank()){
            errores.put("kilometraje ", "el kilometraje es requerido!");
        }
        if (errores.isEmpty()){
            Camion camion = new Camion();
            camion.setMatricula(matricula);
            camion.setTipoCamion(Tipos.valueOf(tipo_camion));
            camion.setModelo(Integer.valueOf(modelo));
            camion.setMarca(Marcas.valueOf(marca));
            camion.setCapacidad(Integer.valueOf(capacidad));
            camion.setKilometraje(Float.valueOf(kilometraje));
            camion.setDisponibilidad(habilitar);

            service.guardar(camion);
            service.guardar(camion);
            resp.sendRedirect(req.getContextPath()+"/camiones/listar");

        }else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaCamiones.jsp")
                    .forward(req,resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaCamiones.jsp").forward(req, resp);
    }
}
