package controladores;

import modelos.Cliente;
import modelosDao.ClienteDAO;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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


@WebServlet(name = "ClienteController", urlPatterns = {"/registro", "/login", "/logout","/verPerfil","/clientes","/clienteseditar","/clienteseditar2","/clienteeliminar","/clientecrear"})
public class ClienteController extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            clienteDAO = new ClienteDAO();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     String action = request.getServletPath();
        HttpSession session = request.getSession();
        switch (action) {
            case "/registro":
                mostrarFormularioRegistro(request, response);
                break;
            case "/login":
                mostrarFormularioLogin(request, response);
                break;
            case "/logout":
                cerrarSesion(request, response);
                break;
             case "/clientes":
                mostrarFormularioCliente(request, response);
                break;
             case "/clienteseditar":
                 mostrarFormularioEditarCliente(request,response);
                 break;
            case "/clienteseditar2":
            mostrarFormularioEditarCliente2(request,response);
            break;
               case "/clienteeliminar":
                 eliminarCliente(request,response);
                 break;
             case "/clientecrear":
                  fomularioCrear(request, response);
                 break;
                case "/verPerfil":
                 verPerfil(request, response);
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
            case "/registro":
                registrarCliente(request, response);
                break;
            case "/login":
                iniciarSesion(request, response);
                break;
            case "/clienteborrar":
              //  mostrarFormularioEditarCliente(request,response);
                 break;
                  case "/clienteseditar":
                    editarCliente(request,response);
                 break;
            default:
                // Lógica para otras rutas si es necesario
                break;
        }
    }

    private void mostrarFormularioRegistro(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("cliente") != null) {
            // Si ya existe una sesión de usuario, redirige a index.jsp
            response.sendRedirect("index.jsp");
        } else {
            // Si no existe una sesión de usuario, muestra el formulario de registro
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login/registro.jsp");
            dispatcher.forward(request, response);
        }
    }


    private void mostrarFormularioLogin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("cliente") != null) {
            
            // Si ya existe una sesión de usuario, redirige a index.jsp
            response.sendRedirect("index.jsp");
        } else {
            // Si no existe una sesión de usuario, muestra el formulario de inicio de sesión
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login/login.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void fomularioCrear(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("admin") != null) {
            
            // Si ya existe una sesión de usuario, redirige a index.jsp
            response.sendRedirect("index.jsp");
        } else {
            // Si no existe una sesión de usuario, muestra el formulario de inicio de sesión
            RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes/crear.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void verPerfil(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (cliente != null && "Cliente".equalsIgnoreCase(cliente.getNombre_rol())) {
             
        
            int valor = cliente.getIdCliente();

            // Verificar si el ID es válido (puedes agregar validaciones adicionales)
            if (valor != 0) {
                try {
//                    int idclient = Integer.parseInt(valor);

                    // Lógica para obtener los datos del producto desde la base de datos
                    Cliente clientes = clienteDAO.obtenerClientebyID(valor);

                    if (clientes != null) {
                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("clientes", clientes);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes/verPerfil.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Manejo de error si el producto no existe
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
            // Si no existe una sesión de usuario, muestra el formulario de inicio de sesión
                        response.sendRedirect("index.jsp");

        }
    }
    
    
    
    private void mostrarFormularioCliente(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
         HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        
    if (cliente != null && "admin".equalsIgnoreCase(cliente.getNombre_rol())) {
            List<Cliente> listaClientes = clienteDAO.consultaGeneral();
            session.setAttribute("clientes", listaClientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes/index.jsp");
            dispatcher.forward(request, response);

        } else {
            // Si no existe una sesión de usuario, muestra el formulario de inicio de sesión
             response.sendRedirect("index.jsp");

        }
        
    
    }
    
    private void mostrarFormularioEditarCliente(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (cliente != null && "admin".equalsIgnoreCase(cliente.getNombre_rol())) {
             String idCliente = request.getParameter("id");

            // Verificar si el ID es válido (puedes agregar validaciones adicionales)
            if (idCliente != null && !idCliente.isEmpty()) {
                try {
                    int idclient = Integer.parseInt(idCliente);

                    // Lógica para obtener los datos del producto desde la base de datos
                    Cliente clientes = clienteDAO.obtenerClientebyID(idclient);

                    if (clientes != null) {
                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("clientes", clientes);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes/editar.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Manejo de error si el producto no existe
                        response.sendRedirect("clientes?error=true");
                    }
                } catch (NumberFormatException e) {
                    // Manejo de error si el ID no es un número válido
                    session.setAttribute("errorMessage", "Error al cargar editar producto");
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
    
    private void mostrarFormularioEditarCliente2(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (cliente != null && "Cliente".equalsIgnoreCase(cliente.getNombre_rol())) {
             String idCliente = request.getParameter("id");

            // Verificar si el ID es válido (puedes agregar validaciones adicionales)
            if (idCliente != null && !idCliente.isEmpty()) {
                try {
                    int idclient = Integer.parseInt(idCliente);

                    // Lógica para obtener los datos del producto desde la base de datos
                    Cliente clientes = clienteDAO.obtenerClientebyID(idclient);

                    if (clientes != null) {
                        // Pasar los datos del producto a la vista de edición
                        request.setAttribute("clientes", clientes);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes/editar.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Manejo de error si el producto no existe
                        response.sendRedirect("clientes?error=true");
                    }
                } catch (NumberFormatException e) {
                    // Manejo de error si el ID no es un número válido
                    session.setAttribute("errorMessage", "Error al cargar editar producto");
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


    private void registrarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();

            String nombre = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("clave");
            String sexo = request.getParameter("sexo"); // Obtener el valor del campo sexo del formulario
            String direccion = request.getParameter("dirrecion"); // Obtener el valor del campo direccion del formulario
            String telefono = request.getParameter("telefono"); // Obtener el valor del campo telefono del formulario
            String pais = request.getParameter("pais"); 
            int rol = 1;
        // Hasheamos la contraseña antes de guardarla en la base de datos
        String hashedContrasena = hashContrasena(contrasena);

        Cliente nuevoCliente = new Cliente(nombre, apellidos, sexo, direccion, telefono, pais, hashedContrasena, correo,rol);
        boolean exito = clienteDAO.agregar(nuevoCliente);

        if (exito) {
            // Redirige a la página de inicio de sesión o a donde desees
            session.setAttribute("successMessage", "Usuario registrado con exito, inicie sesión.");

            response.sendRedirect("login");
        } else {
            session.setAttribute("errorMessage", "Error al registrar al usuario");

            // Manejo de errores (puedes personalizar esto según tus necesidades)
            response.sendRedirect("registro?error=true");
        }
    }

  private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession existingSession = request.getSession(false);
    HttpSession session = request.getSession();

    if (existingSession != null && existingSession.getAttribute("cliente") != null) {
        // Ya existe una sesión de usuario, redirige o realiza alguna acción
        response.sendRedirect("index.jsp?=sesionyainiciada");
        session.setAttribute("successMessage", "¡Bienvenido de vuelta, cliente!");
    } else {
        // El usuario no tiene una sesión existente, procede con el inicio de sesión
        String correo = request.getParameter("usuario");
        String contrasena = request.getParameter("clave");

        // Hasheamos la contraseña antes de compararla con la almacenada en la base de datos
        String hashedContrasena = hashContrasena(contrasena);

        Cliente cliente = clienteDAO.iniciarSesion(correo, hashedContrasena);

        if (cliente != null) {
            // Si el inicio de sesión es exitoso, crea una sesión
            session.setAttribute("cliente", cliente);

            // Verificar si el carrito tiene productos
            //List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute("carrito");

//            if (carrito != null && !carrito.isEmpty()) {
//                // Si hay productos en el carrito, redirige al carrito
//                response.sendRedirect("carrito");
//            } else {
//                // Si no hay productos en el carrito, redirige a la página de inicio
//                
//            }

            response.sendRedirect("index.jsp");
        } else {
            session.setAttribute("errorMessage", "Error al iniciar sesión");

            // Manejo de errores (puedes personalizar esto según tus necesidades)
            response.sendRedirect("login?error=true");
        }
    }
}



    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.invalidate(); // Invalida la sesión

        // Redirige a la página de inicio de sesión o a donde desees

        response.sendRedirect("login");
    }

    private String hashContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(contrasena.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString().substring(0, 10);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if (cliente != null && "admin".equalsIgnoreCase(cliente.getNombre_rol())) {
            // Obtener los parámetros enviados desde el formulario
           String nombre = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("clave");
            String sexo = request.getParameter("sexo"); // Obtener el valor del campo sexo del formulario
            String direccion = request.getParameter("dirrecion"); // Obtener el valor del campo direccion del formulario
            String telefono = request.getParameter("telefono"); // Obtener el valor del campo telefono del formulario
            String pais = request.getParameter("pais"); 
            String idCliente = request.getParameter("idCliente"); 
            String rolId = request.getParameter("rol");
            
             String hashedContrasena = hashContrasena(contrasena);
            // Crear un objeto Producto con los datos editados
            Cliente clienteEdit = new Cliente( nombre, apellidos,  sexo,  direccion,  telefono,  pais,  hashedContrasena,  correo,  Integer.parseInt(rolId));
            
            clienteEdit.setIdCliente(Integer.parseInt(idCliente));

            // Lógica para guardar los cambios en la base de datos
            boolean exito = clienteDAO.actualizarCliente(clienteEdit);

            
            if (exito) {
                // Redirigir a la página de productos después de la edición exitosa con un mensaje de éxito
                session.setAttribute("successMessage", "Cliente editado con éxito");
                response.sendRedirect("clientes");
            } else {
                // En caso de error, redirigir a la página de productos con un mensaje de error
                session.setAttribute("errorMessage", "Error al editar el Cliente");
                response.sendRedirect("clientes");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
     private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente != null && "admin".equalsIgnoreCase(cliente.getNombre_rol())) {
            // Obtener el ID del producto a eliminar desde la solicitud
            String idcliente = request.getParameter("id");

            if (idcliente != null && !idcliente.isEmpty()) {
                try {
                    int idCliente = Integer.parseInt(idcliente);

                    // Llamar al método en el DAO para eliminar el producto
                    boolean exito = clienteDAO.eliminarClientes(idCliente);

                    if (exito) {
                        // Redirigir a la página de productos si la eliminación fue exitosa con un mensaje de éxito
                        session.setAttribute("successMessage", "cliente eliminado con éxito");
                        response.sendRedirect("clientes");
                    } else {
                        // Manejar el caso en el que la eliminación falla, tal vez mostrar un mensaje de error
                        session.setAttribute("errorMessage", "Error al eliminar el cliente");
                        response.sendRedirect("clientes");
                    }
                } catch (NumberFormatException e) {
                    // Manejar el caso en el que el parámetro de ID no es un número válido con un mensaje de error
                    session.setAttribute("errorMessage", "Error al eliminar el cliente");
                    response.sendRedirect("clientes");
                }
            } else {
                // Manejar el caso en el que no se proporcionó un ID válido con un mensaje de error
                session.setAttribute("errorMessage", "Error al eliminar el cliente");
                response.sendRedirect("clientes");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

}
