/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelosDao;

import db.cn;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Cliente;
import modelos.Encuesta;

/**
 *
 * @author Esau
 */
public class EncuestaDAO {
    private cn CN;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public EncuestaDAO() throws SQLException {
        try {
            CN = new cn();
            
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    //agregar las encuestas a la base datos
    public boolean agregar(Encuesta encuesta) {
        this.sql = "INSERT INTO encuesta (nombre, sexo, deporte, nivel_estudio, tema_favorito, fecha, idCliente) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = CN.getConnection().prepareStatement(sql);
            ps.setString(1, encuesta.getNombre());
            ps.setString(2, encuesta.getSexo());
            ps.setString(3, encuesta.getDeporte());
            ps.setString(4, encuesta.getNivelEstudio());
            ps.setString(5, encuesta.getTemaFavorito());
            ps.setDate(6, encuesta.getFecha());
            ps.setInt(7, encuesta.getIdCliente());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error este: " + e);
                        
        }
        return false;
    }
    
    //trae las encuestas por el id del usuario
    public Encuesta obtenerEncuestabyID(int idEncuesta) {
        String sql = "SELECT * FROM encuesta WHERE idCliente = ?";
        Encuesta encuesta = null;

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idEncuesta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    encuesta = new Encuesta();
                    encuesta.setIdEncuesta(rs.getInt("idEncuesta"));
                    encuesta.setNombre(rs.getString("nombre"));
                    encuesta.setSexo(rs.getString("sexo"));
                    encuesta.setDeporte(rs.getString("deporte"));
                    encuesta.setNivelEstudio(rs.getString("nivel_estudio"));
                    encuesta.setTemaFavorito(rs.getString("tema_favorito"));
                    encuesta.setFecha(rs.getDate("fecha"));
                    encuesta.setIdCliente(rs.getInt("idCliente"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print(encuesta);
        return encuesta;
    }
    
    
    //trae las encuestas por asu id de encuesta        
    public Encuesta editarEncuestas(int idEncuesta) {
        String sql = "SELECT * FROM encuesta WHERE idEncuesta = ?";
        Encuesta encuesta = null;

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idEncuesta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    encuesta = new Encuesta();
                    encuesta.setIdEncuesta(rs.getInt("idEncuesta"));
                    encuesta.setNombre(rs.getString("nombre"));
                    encuesta.setSexo(rs.getString("sexo"));
                    encuesta.setDeporte(rs.getString("deporte"));
                    encuesta.setNivelEstudio(rs.getString("nivel_estudio"));
                    encuesta.setTemaFavorito(rs.getString("tema_favorito"));
                    encuesta.setFecha(rs.getDate("fecha"));
                    encuesta.setIdCliente(rs.getInt("idCliente"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print(encuesta);
        return encuesta;
    }
    
    
    //mandar los datos editados de la encuesta por el usuario
    public boolean edicionEncuesta(Encuesta encuesta) {
        String sql = "update encuesta set nombre = ?, sexo = ?, deporte = ?, nivel_estudio = ?, tema_favorito = ? where idEncuesta = ?";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setString(1, encuesta.getNombre());
            ps.setString(2, encuesta.getSexo());
            ps.setString(3, encuesta.getDeporte());
            ps.setString(4, encuesta.getNivelEstudio());
            ps.setString(5, encuesta.getTemaFavorito());
            ps.setInt(6, encuesta.getIdEncuesta());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Encuesta> consultaGeneral() {
        ArrayList<Encuesta> lista = new ArrayList<>();

            String sql = "SELECT idEncuesta, nombre, sexo, deporte, nivel_estudio, tema_favorito, fecha, idCliente FROM encuesta";

            try (PreparedStatement ps = CN.getConnection().prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Encuesta encuesta = new Encuesta();
                    encuesta.setIdEncuesta(rs.getInt("idEncuesta"));
                    encuesta.setNombre(rs.getString("nombre"));
                    encuesta.setSexo(rs.getString("sexo"));
                    encuesta.setDeporte(rs.getString("deporte"));
                    encuesta.setNivelEstudio(rs.getString("nivel_estudio"));
                    encuesta.setTemaFavorito(rs.getString("tema_favorito"));
                    encuesta.setFecha(rs.getDate("fecha"));
                    encuesta.setIdCliente(rs.getInt("idCliente"));

                    lista.add(encuesta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return lista;
    }
    
    
    
    //trae las encuestas por fecha  
     public List<Encuesta> EncuestaFecha(Date fecha) {
         
         
        ArrayList<Encuesta> lista = new ArrayList<>();
    
        String sql = "SELECT * FROM encuesta where fecha = ?";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setDate(1, fecha);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Encuesta encuesta = new Encuesta();
                    encuesta.setIdEncuesta(rs.getInt("idEncuesta"));
                    encuesta.setNombre(rs.getString("nombre"));
                    encuesta.setSexo(rs.getString("sexo"));
                    encuesta.setDeporte(rs.getString("deporte"));
                    encuesta.setNivelEstudio(rs.getString("nivel_estudio"));
                    encuesta.setTemaFavorito(rs.getString("tema_favorito"));
                    encuesta.setFecha(rs.getDate("fecha"));
                    encuesta.setIdCliente(rs.getInt("idCliente"));

                    lista.add(encuesta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
     
     
         //trae las encuestas por fecha  
     public List<Encuesta> EncuestaNombre(String nombre) {
         
         
        ArrayList<Encuesta> lista = new ArrayList<>();
    
        String sql = "SELECT * FROM encuesta where nombre = ?";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Encuesta encuesta = new Encuesta();
                    encuesta.setIdEncuesta(rs.getInt("idEncuesta"));
                    encuesta.setNombre(rs.getString("nombre"));
                    encuesta.setSexo(rs.getString("sexo"));
                    encuesta.setDeporte(rs.getString("deporte"));
                    encuesta.setNivelEstudio(rs.getString("nivel_estudio"));
                    encuesta.setTemaFavorito(rs.getString("tema_favorito"));
                    encuesta.setFecha(rs.getDate("fecha"));
                    encuesta.setIdCliente(rs.getInt("idCliente"));

                    lista.add(encuesta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
     
     
    public List<Encuesta> EncuestaFiltros(String nombre, Date fecha) {
        ArrayList<Encuesta> lista = new ArrayList<>();
        String sql = "SELECT * FROM encuesta WHERE nombre = ? AND fecha = ?";

        try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setDate(2, fecha); // Establece la fecha en el PreparedStatement

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Encuesta encuesta = new Encuesta();
                    encuesta.setIdEncuesta(rs.getInt("idEncuesta"));
                    encuesta.setNombre(rs.getString("nombre"));
                    encuesta.setSexo(rs.getString("sexo"));
                    encuesta.setDeporte(rs.getString("deporte"));
                    encuesta.setNivelEstudio(rs.getString("nivel_estudio"));
                    encuesta.setTemaFavorito(rs.getString("tema_favorito"));
                    encuesta.setFecha(rs.getDate("fecha"));
                    encuesta.setIdCliente(rs.getInt("idCliente"));

                    lista.add(encuesta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    
            
        public boolean eliminarEncuesta(int idEncuesta) {
            String sql = "DELETE FROM encuesta WHERE idEncuesta = ?";

            try (PreparedStatement ps = CN.getConnection().prepareStatement(sql)) {
                ps.setInt(1, idEncuesta);

                int filasAfectadas = ps.executeUpdate();
                return filasAfectadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }
    
     
     
    
    
}
    
    
    

