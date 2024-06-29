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
import java.util.*;

@WebServlet("/camiones/editar")
public class EdicionCamionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn=(Connection) req.getAttribute("conn");
        IService<Camion> service=new CamionesService(conn);
        String matricula=req.getParameter("matricula");
        String tipoCamion= req.getParameter("tipoCamion");
        String modelo= req.getParameter("modelo");
        String marca= req.getParameter("marca");
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
        if(tipoCamion==null|| tipoCamion.equals("")){
            errores.put("tipoCamion","el tipo de camion es requerido!");
        }
        if(modelo==null|| modelo.equals("")){
            errores.put("modelo","el modelo es requerido!");
        }
        if(marca==null|| marca.equals("")){
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
            camion.setMarca(Marcas.valueOf(String.valueOf(marca)));
            camion.setKilometraje(Float.valueOf(kilometraje));
            camion.setCapacidad(Integer.valueOf(capacidad));
            camion.setModelo(Integer.valueOf(modelo));
            camion.setTipoCamion(Tipos.valueOf(String.valueOf(tipoCamion)));
            camion.setDisponibilidad(habilitar);
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
        Connection conn = (Connection) req.getAttribute("conn");

        // Lista de tipos y marcas
        Tipos[] arrayCamiones = Tipos.values();
        Marcas[] arrayMarcas = Marcas.values();
        req.setAttribute("tipos", arrayCamiones);
        req.setAttribute("marcas", arrayMarcas);

        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> listaAnios = new ArrayList<>();
        for (int i = anioActual - 20; i <= anioActual; i++) {
            listaAnios.add(i);
        }
        req.setAttribute("modelos", listaAnios);

        IService<Camion> service = new CamionesService(conn);
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Camion> optionalCamion = service.getById(id);

        if (optionalCamion == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Camión no encontrado");
            return;
        }
        Camion camion = optionalCamion.get();
        req.setAttribute("camion", camion);
        getServletContext().getRequestDispatcher("/edicionCamion.jsp").forward(req, resp);
    }
}
