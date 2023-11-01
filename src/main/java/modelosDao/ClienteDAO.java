package modelosDao;

import db.cn;
import modelos.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private cn CN;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public ClienteDAO() throws SQLException {
        try {
            CN = new cn();
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> consultaGeneral() {
        ArrayList<Cliente> lista = new ArrayList<>();
        // Agrega aquí la lógica para obtener todos los clientes de la base de datos.
        // Utiliza el objeto CN para establecer la conexión y ejecutar consultas SQL.
        // Llena la lista con los resultados y devuelve la lista.
        String sql = "SELECT idCliente,nombres,apellidos,sexo,direccion,telefono,pais,correo,idRol FROM cliente";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setRolid(rs.getInt("idRol"));

                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Cliente cliente) {
        this.sql = "INSERT INTO cliente (nombres, apellidos, sexo, direccion, telefono, pais, clave, correo, idRol) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ps = CN.getConnection().prepareStatement(sql);
            // Configura los parámetros del PreparedStatement con los datos del cliente.
            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getSexo());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getPais());
            ps.setString(7, cliente.getClave());
            ps.setString(8, cliente.getCorreo());
            ps.setInt(9, cliente.getRolid()); // Agrega el rolid

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
                        
        }
        return false;
    }
    
public Cliente iniciarSesion(String correo, String contrasena) {
   this.sql = "SELECT cliente.*, rol.nombre_rol AS nombre_rol " +
           "FROM cliente " +
           "INNER JOIN rol ON cliente.idRol = rol.idRol " +
           "WHERE cliente.correo = ? AND cliente.clave = ?";

    
    try {
        ps = CN.getConnection().prepareStatement(sql);
        ps.setString(1, correo); // Establece el valor para el primer ? como correo
        ps.setString(2, contrasena); // Establece el valor para el segundo ? como contrasena
        rs = ps.executeQuery();
        
        if (rs.next()) {
            Cliente cliente = new Cliente();

            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setNombres(rs.getString("nombres"));
            cliente.setApellidos(rs.getString("apellidos"));
            
            // Ahora puedes obtener el nombre del rol
            cliente.setNombre_rol(rs.getString("nombre_rol"));

            // Configura otros atributos según tu tabla de clientes
            return cliente;
        }
    } catch (Exception e) {
        // Maneja las excepciones aquí.
        System.out.println("El error:"+e);
    }
    return null; // Retornar null si la autenticación falla.
}
public Cliente obtenerClientebyID(int idCliente) {
        String sql = "select * from cliente where idCliente = ?";
        Cliente cliente = null;

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {           
                  cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setRolid(rs.getInt("idRol"));
                cliente.setClave(rs.getString("clave"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
public boolean actualizarCliente(Cliente cliente) {
    String sql = "update cliente set nombres  = ?,  apellidos = ?,  sexo =?,  direccion =?,  telefono =?,  pais=?,  clave=?,  correo =?,  idRol =? where idCliente =?";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getSexo());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getPais());
            ps.setString(7, cliente.getClave());
            ps.setString(8, cliente.getCorreo());
            ps.setInt(9, cliente.getRolid());
            ps.setInt(10, cliente.getIdCliente());

            int filasAfectadas = ps.executeUpdate();
          
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
           return false;
        }
    }
 public boolean eliminarClientes(int idcliente) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idcliente);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Agrega otros métodos para actualizar, eliminar y buscar clientes según tus necesidades.
}
