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
public class AltaCamionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaCamion.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=(Connection) req.getAttribute("conn");
        IService<Camion> service=new CamionesService(conn);
        String matricula=req.getParameter("matricula");
        String tipocamion=req.getParameter("tipocamion");
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
            errores.put("matricula","La matricula es requerida!");
        }
        if(tipocamion==null|| tipocamion.isBlank()){
            errores.put("tipocamion","el tipo de camion es requerido!");
        }
        if(modelo==null|| modelo.isBlank()){
            errores.put("modelo","el modelo es requerido!");
        }
        if(marca==null|| marca.isBlank()){
            errores.put("marca","La marca es requerida!");
        }
        if(kilometraje==null|| kilometraje.isBlank()){
            errores.put("kilometraje","El kilometraje es requerido!");
        }
        if(capacidad==null|| capacidad.isBlank()){
            errores.put("capacidad","La capacidad es requerida!");
        }

        if(errores.isEmpty()){
            Camion camion=new Camion();
            camion.setId(0L);
            camion.setMatricula(matricula);
            camion.setMarca(Marcas.valueOf(marca));
            camion.setKilometraje(Float.valueOf(kilometraje));
            camion.setCapacidad(Integer.valueOf(capacidad));
            camion.setModelo(Integer.valueOf(modelo));
            camion.setTipoCamion(Tipos.valueOf(tipocamion));
            camion.setDisponibilidad(habilitar);
            service.guardar(camion);
            resp.sendRedirect(req.getContextPath()+"/camiones/lista");

        }
        else{
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaCamiones.jsp").forward(req,resp);
        }

    }
}
