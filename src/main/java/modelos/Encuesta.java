/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Esau
 */
public class Encuesta {
     private int idEncuesta;
    private String nombre;
    private String sexo;
    private String deporte;
    private String nivel_estudio;
    private String tema_favorito;
    private Date fecha;
    private int idCliente;

    // Constructor
    public Encuesta(int idEncuesta, String nombre, String sexo, String deporte, String nivelEstudio, String temaFavorito, Date fecha, int idCliente) {
        this.idEncuesta = idEncuesta;
        this.nombre = nombre;
        this.sexo = sexo;
        this.deporte = deporte;
        this.nivel_estudio = nivelEstudio;
        this.tema_favorito = temaFavorito;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    public Encuesta(String nombre, String sexo, String deporte, String nivelEstudio, String temaFavorito, Date fecha, int idCliente) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.deporte = deporte;
        this.nivel_estudio = nivelEstudio;
        this.tema_favorito = temaFavorito;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }
    
    public Encuesta() {
    }

    // Getters and setters for each field
    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getNivelEstudio() {
        return nivel_estudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivel_estudio = nivelEstudio;
    }

    public String getTemaFavorito() {
        return tema_favorito;
    }

    public void setTemaFavorito(String temaFavorito) {
        this.tema_favorito = temaFavorito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
