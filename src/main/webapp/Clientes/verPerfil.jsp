<%-- 
    Document   : verPerfil
    Created on : 10-31-2023, 04:09:34 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<title>Perfil</title>
<%@ include file="/Layout/header.jsp" %>

<body>

    <div class="container" style="padding-left: 270px; padding-right: 270px">
        <h2 class="text-center" style="font-size: 50px"><i class="fas fa-user"></i> Detalles del Cliente</h2>
        <table class="table" >
            <tr>
                <th>Nombres:</th>
                <td>${clientes.getNombres()}</td>
            </tr>
            <tr>
                <th>Apellidos:</th>
                <td>${clientes.getApellidos()}</td>
            </tr>
            <tr>
                <th>Sexo:</th>
                <td>${clientes.getSexo()}</td>
            </tr>
            <tr>
                <th>Dirección:</th>
                <td>${clientes.getDireccion()}</td>
            </tr>
            <tr>
                <th>Teléfono:</th>
                <td>${clientes.getTelefono()}</td>
            </tr>
            <tr>
                <th>Correo Electrónico:</th>
                <td>${clientes.getCorreo()}</td>
            </tr>
            <tr>
                <th>País:</th>
                <td>${clientes.getPais()}</td>
            </tr>
            <tr>
                <th>Contraseña:</th>
                <td>${clientes.getClave()}</td>
            </tr>
            <tr>
                <th>Rol:</th>
                <td>${clientes.getRolid() == 2 ? 'Cliente' : 'Administrador'}</td>
            </tr>
        </table>

            <div class="col-12">
                <a href="clienteseditar2?id=${cliente.getIdCliente()}" class="btn btn-primary d-flex justify-content-center align-items-center " style="margin-left: 130px; margin-right: 130px">
                    <i class="fas fa-pencil-alt mr-2"></i> Editar
                </a>
            </div>

    </div>

    <script>
        function validarFormulario() {
            var nombreCliente = document.getElementById("nombres").value;
            var apellidoCliente = document.getElementById("apellidos").value;
            var correo = document.getElementById("correo").value;
            var clave = document.getElementById("clave").value;

            if (nombreCliente.trim() === "" || apellidoCliente.trim() === "" || correo.trim() === "" || clave.trim() === "") {
                alert("Por favor, complete todos los campos.");
                return false;
            }

            if (precioNormal <= 0 || existencias < 0) {
                alert("Los campos Precio Normal y Existencias deben ser números positivos.");
                return false;
            }

            return true;
        }
    </script>
</body>

<%@ include file="/Layout/footer.jsp" %>
