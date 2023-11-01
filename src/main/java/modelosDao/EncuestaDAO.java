/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelosDao;

import db.cn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    
    
}
