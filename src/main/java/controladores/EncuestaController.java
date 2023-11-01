/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Console;
import modelos.Cliente;
import modelos.Encuesta;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelosDao.ClienteDAO;
import modelosDao.EncuestaDAO;

/**
 *
 * @author Esau
 */
@WebServlet(name = "EncuestaController", urlPatterns = {"/EncuestaController", "/doEncuesta", "/enviarEncuesta"})
public class EncuestaController extends HttpServlet {
     private EncuestaDAO encuestaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            encuestaDAO = new EncuestaDAO();
        } catch (SQLException ex) {
            Logger.getLogger(EncuestaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     String action = request.getServletPath();
        HttpSession session = request.getSession();
        switch (action) {
            case "/doEncuesta":
                System.out.println("Leggop aquiii");
                mostrarEncuesta(request, response);
                
                break;
            default:
                // Lógica para otras rutas si es necesario
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/enviarEncuesta":
                GuardarEncuesta(request, response);
                break;
            default:
                // Lógica para otras rutas si es necesario
                break;
        }
    }
   
    
    private void mostrarEncuesta(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        


        if (session != null && session.getAttribute("Cliente") != null) {
            // Si ya existe una sesión de usuario, redirige a index.jsp
            response.sendRedirect("index.jsp");
        } else {
            // Si no existe una sesión de usuario, muestra el formulario de registro
            RequestDispatcher dispatcher = request.getRequestDispatcher("Usuario/doEncuesta.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void GuardarEncuesta(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            HttpSession session = request.getSession();
            
            Cliente cliente = (Cliente) session.getAttribute("cliente");
            int idCliente = cliente.getIdCliente();
            
            // Obtener la fecha actual
            Calendar calendar = Calendar.getInstance();
            Date fecha = new Date(calendar.getTimeInMillis());


            String nombre = request.getParameter("nombre");
            String sexo = request.getParameter("sexo");
            String deporte = request.getParameter("deporte");
            String nivelEstudio = request.getParameter("nivel_estudio");
            String temaFavorito = request.getParameter("temas"); 
            
            System.out.println(fecha);
            System.out.println(nombre);
            System.out.println(sexo);
            System.out.println(deporte);
            System.out.println(nivelEstudio);
            System.out.println(idCliente);
            System.out.println(temaFavorito);
            

        

        Encuesta nuevaEncuesta = new Encuesta(nombre, sexo, deporte, nivelEstudio, temaFavorito, fecha, idCliente);
        System.out.println(nuevaEncuesta.getNombre());
        boolean exito = encuestaDAO.agregar(nuevaEncuesta);

        if (exito) {
            // Redirige a la página de inicio de sesión o a donde desees
            session.setAttribute("successMessage", "Encuesta registrada correctamente.");

            response.sendRedirect("index.jsp");
        } else {
            session.setAttribute("errorMessage", "Error al subir la encuesta");

            // Manejo de errores (puedes personalizar esto según tus necesidades)
            response.sendRedirect("index.jsp");
        }
    }

}
