/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Esau
 */
public class Cliente {
     private int idCliente;
    private String nombres;
    private String apellidos;
    private String sexo;
    private String direccion;
    private String telefono;
    private String pais;
    private String clave;
    private String correo;
    private int idrol;// Nuevo campo
    private String nombre_rol;

    public Cliente() {
    }

 
    
    public Cliente(String nombres, String apellidos, String sexo, String direccion, String telefono, String pais, String clave, String correo, int rolid) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.pais = pais;
        this.clave = clave;
        this.correo = correo;
        this.idrol = rolid; // Asigna el rolid
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setRolid(int rolid) {
        this.idrol = rolid;
    }
    

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
      public int getRolid() {
        return idrol;
    }
}
