/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Console;
import modelos.Cliente;
import modelos.Encuesta;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
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
@WebServlet(name = "EncuestaController", urlPatterns = {"/EncuestaController", "/doEncuesta", "/enviarEncuesta", "/verEncuesta", "/editarEncuesta", "/edicion", "/ConsultarEnc", "/FiltarEncuestas", "/eliminarEncuesta", "/graficos"})
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
                mostrarEncuesta(request, response);
                break;
            case "/verEncuesta":
                visualizarEncuesta(request, response);
                break;
            case "/editarEncuesta":
                editarEncuesta(request, response);
                break;
            case "/ConsultarEnc":
                ConsultarEnc(request, response);
                break;
            case "/eliminarEncuesta":
                eliminarEncuesta(request, response);
                break;
            case "/graficos":
                graficos(request, response);
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
            case "/edicion":
                edicionEncuesta(request, response);
                break;
            case "/FiltarEncuestas":
                FiltarEncuestas(request, response);
                break;
                
            default:
                // Lógica para otras rutas si es necesario
                break;
        }
    }
    
    private void graficos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
         HttpSession session = request.getSession();

        try {
            // Lógica para obtener los datos del producto desde la base de datos
            List<Encuesta> listaEncuestas = encuestaDAO.consultaGeneral();

            if (listaEncuestas != null) {
                // Crear una instancia de Gson
                Gson gson = new Gson();

                // Convertir la lista de encuestas a JSON
                String encuestasJSON = gson.toJson(listaEncuestas);

                // Pasar los datos JSON a la vista
                request.setAttribute("encuestasJSON", encuestasJSON);

                RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/resumenEncuesta.jsp");
                dispatcher.forward(request, response);
            } else {
                session.setAttribute("errorMessage", "Llene antes su encuesta");
                response.sendRedirect("clientes?error=true");
            }

        } catch (NumberFormatException e) {
            // Manejo de error si el ID no es un número válido
            session.setAttribute("errorMessage", "1Error al cargar editar producto");
            response.sendRedirect("clientes?error=true");
        }
    }        
    
    
    
    
    
            
            
    private void eliminarEncuesta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
            String idcliente = request.getParameter("id");

            try{
                int idEncuesta = Integer.parseInt(idcliente);

                // Llamar al método en el DAO para eliminar el producto
                boolean eliminado = encuestaDAO.eliminarEncuesta(idEncuesta);

                if (eliminado) {
                    // Redirigir a la página de productos si la eliminación fue exitosa con un mensaje de éxito
                    session.setAttribute("successMessage", "Encuesta eliminada con éxito");
                    
                     List<Encuesta> listaEncuestas = encuestaDAO.consultaGeneral();

                    if (listaEncuestas != null) {

                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("encuestas", listaEncuestas);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/consultarEncuestas.jsp");
                        dispatcher.forward(request, response);
                    } else {
                         session.setAttribute("errorMessage", "Llene antes su encuesta");
                        response.sendRedirect("clientes?error=true");
                    }
                } else {
                    // Manejar el caso en el que la eliminación falla, tal vez mostrar un mensaje de error
                    session.setAttribute("errorMessage", "Error al eliminar la encuesta");
                    response.sendRedirect("clientes");
                }
            } catch (NumberFormatException e) {
                // Manejar el caso en el que el parámetro de ID no es un número válido con un mensaje de error
                session.setAttribute("errorMessage", "Error al eliminar el cliente");
                response.sendRedirect("clientes");
            }


    }
    
            
    private void FiltarEncuestas(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String nombre = request.getParameter("filtroNombre");
        String fechaString = request.getParameter("fechaInicio");
        java.sql.Date sqlDate = null;
        
            if (nombre != null && !nombre.isEmpty() && fechaString != null && !fechaString.isEmpty()) {
                System.out.println("111111111111111111");
                
                try {
                      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                      java.util.Date utilDate = formatoFecha.parse(fechaString);
                      sqlDate = new java.sql.Date(utilDate.getTime());
                      
                       List<Encuesta> encuestas = encuestaDAO.EncuestaFiltros(nombre, sqlDate);

                    if (encuestas != null) {
                        System.out.println("3.111111");

                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("encuestas", encuestas);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/consultarEncuestas.jsp");
                        dispatcher.forward(request, response);
                    } else {
                         session.setAttribute("errorMessage", "Llene antes su encuesta");
                        response.sendRedirect("clientes?error=true");
                    }
                      
                  } catch (ParseException e) {
                      e.printStackTrace();
                      // Maneja cualquier excepción que pueda ocurrir al analizar la fecha
                  }
                
                
            } else if (nombre != null && !nombre.isEmpty()) {
                  System.out.println("22222222222222");
                  
                   List<Encuesta> encuestas = encuestaDAO.EncuestaNombre(nombre);

                    if (encuestas != null) {
                        System.out.println("3.111111");

                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("encuestas", encuestas);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/consultarEncuestas.jsp");
                        dispatcher.forward(request, response);
                    } else {
                         session.setAttribute("errorMessage", "Llene antes su encuesta");
                        response.sendRedirect("clientes?error=true");
                    }
                
            } else if (fechaString != null && !fechaString.isEmpty()){
                System.out.println("33333333333333333");
                  try {
                      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                      java.util.Date utilDate = formatoFecha.parse(fechaString);
                      sqlDate = new java.sql.Date(utilDate.getTime());
                      
                       List<Encuesta> encuestas = encuestaDAO.EncuestaFecha(sqlDate);

                    if (encuestas != null) {
                        System.out.println("3.111111");

                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("encuestas", encuestas);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/consultarEncuestas.jsp");
                        dispatcher.forward(request, response);
                    } else {
                         session.setAttribute("errorMessage", "Llene antes su encuesta");
                        response.sendRedirect("clientes?error=true");
                    }
                      
                  } catch (ParseException e) {
                      e.printStackTrace();
                      // Maneja cualquier excepción que pueda ocurrir al analizar la fecha
                  }
               
                  
            } else {
                System.out.println("4444444444444444444444");
                
                try {
                    // Lógica para obtener los datos del producto desde la base de datos
                    List<Encuesta> listaEncuestas = encuestaDAO.consultaGeneral();

                    if (listaEncuestas != null) {

                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("encuestas", listaEncuestas);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/consultarEncuestas.jsp");
                        dispatcher.forward(request, response);
                    } else {
                         session.setAttribute("errorMessage", "Llene antes su encuesta");
                        response.sendRedirect("clientes?error=true");
                    }
                } catch (NumberFormatException e) {
                    // Manejo de error si el ID no es un número válido
                    session.setAttribute("errorMessage", "1Error al cargar editar producto");
                    response.sendRedirect("clientes?error=true");
                }
            }

        
      

            
    }
            
            
            
    private void ConsultarEnc(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");


        if (cliente != null && "Admin".equalsIgnoreCase(cliente.getNombre_rol())) {
             
            int valor = cliente.getIdCliente();

            if (valor != 0) {

                try {
                    // Lógica para obtener los datos del producto desde la base de datos
                    List<Encuesta> listaEncuestas = encuestaDAO.consultaGeneral();

                    if (listaEncuestas != null) {

                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("encuestas", listaEncuestas);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/consultarEncuestas.jsp");
                        dispatcher.forward(request, response);
                    } else {
                         session.setAttribute("errorMessage", "Llene antes su encuesta");
                        response.sendRedirect("clientes?error=true");
                    }
                } catch (NumberFormatException e) {
                    // Manejo de error si el ID no es un número válido
                    session.setAttribute("errorMessage", "1Error al cargar editar producto");
                    response.sendRedirect("clientes?error=true");
                }
            } else {
                session.setAttribute("errorMessage", "2Error al cargar editar producto");
                // Manejo de error si no se proporciona un ID válido en la ruta
                response.sendRedirect("clientes?error=true");
            }
        } else {
           // Manejo de error si el ID no es un número válido
                    session.setAttribute("errorMessage", "1diegoooError al cargar editar producto");
                    response.sendRedirect("clientes?error=true");
            response.sendRedirect("index.jsp");
        }
    }        
            
   
    
    private void mostrarEncuesta(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        int valor = cliente.getIdCliente();
         
        if (valor != 0) {

            try {
                // Lógica para obtener los datos del producto desde la base de datos
                Encuesta encuestas = encuestaDAO.obtenerEncuestabyID(valor);

                if (encuestas == null) {

                    // Si no existe una sesión de usuario, muestra el formulario de registro
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Usuario/doEncuesta.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // Manejo de error si el producto no existe
                        session.setAttribute("errorMessage", "No puede llenar la encuesta 2 veces");

                        response.sendRedirect("clientes?error=true");
                }
            } catch (NumberFormatException e) {
                // Manejo de error si el ID no es un número válido
                session.setAttribute("errorMessage", "1Error al cargar editar producto");
                response.sendRedirect("clientes?error=true");
            }
        } else {
            session.setAttribute("errorMessage", "2Error al cargar editar producto");
            // Manejo de error si no se proporciona un ID válido en la ruta
            response.sendRedirect("clientes?error=true");
        }     
    }
    
    
    private void visualizarEncuesta(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");


        if (cliente != null && "Cliente".equalsIgnoreCase(cliente.getNombre_rol())) {
             
            int valor = cliente.getIdCliente();

            if (valor != 0) {

                try {
                    // Lógica para obtener los datos del producto desde la base de datos
                    Encuesta encuestas = encuestaDAO.obtenerEncuestabyID(valor);

                    if (encuestas != null) {

                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("encuestas", encuestas);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Usuario/verEncuesta.jsp");
                        dispatcher.forward(request, response);
                    } else {
                         session.setAttribute("errorMessage", "Llene antes su encuesta");
                        response.sendRedirect("clientes?error=true");
                    }
                } catch (NumberFormatException e) {
                    // Manejo de error si el ID no es un número válido
                    session.setAttribute("errorMessage", "1Error al cargar editar producto");
                    response.sendRedirect("clientes?error=true");
                }
            } else {
                session.setAttribute("errorMessage", "2Error al cargar editar producto");
                // Manejo de error si no se proporciona un ID válido en la ruta
                response.sendRedirect("clientes?error=true");
            }
        } else {
           // Manejo de error si el ID no es un número válido
                    session.setAttribute("errorMessage", "1diegoooError al cargar editar producto");
                    response.sendRedirect("clientes?error=true");
            response.sendRedirect("index.jsp");
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

        
        

        Encuesta nuevaEncuesta = new Encuesta(nombre, sexo, deporte, nivelEstudio, temaFavorito, fecha, idCliente);

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
    
    
    private void editarEncuesta(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (cliente != null ) {
             String idCliente = request.getParameter("id");

            // Verificar si el ID es válido (puedes agregar validaciones adicionales)
            if (idCliente != null && !idCliente.isEmpty()) {
                try {
                    int idclient = Integer.parseInt(idCliente);

                    Encuesta encuestas = encuestaDAO.editarEncuestas(idclient);

                    if (encuestas != null) {
                        
                        
                        
                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("encuestas", encuestas);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Usuario/editarEncuesta.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Manejo de error si el producto no existe
                        response.sendRedirect("clientes?error=true");
                    }
                } catch (NumberFormatException e) {
                    // Manejo de error si el ID no es un número válido
                    session.setAttribute("errorMessage", "Error al cargar editar encuesta");
                    response.sendRedirect("clientes?error=true");
                }
            } else {
                session.setAttribute("errorMessage", "Error al cargar editar producto");
                // Manejo de error si no se proporciona un ID válido en la ruta
                response.sendRedirect("clientes?error=true");
            }
        } else {
            // Si no existe una sesión de usuario, muestra el formulario de inicio de sesión
                        response.sendRedirect("index.jsp");

        }
    }
    
    
    
    private void edicionEncuesta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");
//            int idCliente = cliente.getIdCliente();
            
           

        if (cliente != null ) {


            String nombre = request.getParameter("nombre");
            String sexo = request.getParameter("sexo");
            String deporte = request.getParameter("deporte");
            String nivelEstudio = request.getParameter("nivel_estudio");
            String temaFavorito = request.getParameter("temas"); 
            String idEncuestaParam = request.getParameter("idencuesta");
            String idclienteParam = request.getParameter("idcliente");
            
            
            
            int idEncuesta = Integer.parseInt(idEncuestaParam);
            
            
            int idCliente = Integer.parseInt(idclienteParam);
            



             // Obtener la fecha actual
            Calendar calendar = Calendar.getInstance();
            Date fecha = new Date(calendar.getTimeInMillis());

            Encuesta nuevaEncuesta = new Encuesta(idEncuesta, nombre, sexo, deporte, nivelEstudio, temaFavorito, fecha, idCliente);

            boolean exito = encuestaDAO.edicionEncuesta(nuevaEncuesta);

            if (exito) {
                // Redirige a la página de inicio de sesión o a donde desees
                session.setAttribute("successMessage", "Encuesta registrada correctamente.");

                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("errorMessage", "Error al subir la encuesta");

                // Manejo de errores (puedes personalizar esto según tus necesidades)
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
            
            

}
