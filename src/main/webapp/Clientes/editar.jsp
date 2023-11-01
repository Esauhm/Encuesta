<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <%@ include file="/Layout/header.jsp" %>
    
    <style>
/* Estilo para el formulario de inicio de sesión */
.registration-form {
    width: 100%;
    max-width: 550px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
     transition: transform 0.2s; 
}

.registration-form:hover {
            transform: scale(1.02); /* Aumenta el tamaño de la imagen al 110% cuando se hace hover */
     }
.login-form h2 {
    text-align: center;
    margin-bottom: 20px;
}

.form-group {
    margin-bottom: 15px;
}

.form-group label {
    font-weight: bold;
}

.form-control {
    width: 100%;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.btn-register {
    display: block;
    width: 100%;
    padding: 10px;
    background-color: #007bff; /* Color de fondo azul */
    color: #fff; /* Color de texto blanco */
    border: none;
    border-radius: 5px;
    text-align: center;
    text-decoration: none;
    font-weight: bold;
}

.btn-register:hover {
    background-color: #0056b3; /* Cambio de color al pasar el mouse */
}

.btn-login {
    display: block;
    text-align: center;
    margin-top: 10px;
}


    </style>
</head>
<body>
        <div class="registration-form mt-5 mb-5">
            <h2 class="text-center">Editar Cliente</h2>
            <form action="clienteseditar" method="post" onsubmit="return validarFormulario()">
                <!-- Campos del formulario -->
                <div class="row">
                    
                    <input type="hidden" name="idCliente" value="${clientes.getIdCliente()}">
                       <input type="hidden" name="idRol" value="${clientes.getRolid()}">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="nombres">Nombres:</label>
                            <input type="text" class="form-control" id="nombres" name="nombres" required value="${clientes.getNombres()}">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="apellidos">Apellidos:</label>
                            <input type="text" class="form-control" id="apellidos" name="apellidos" required value="${clientes.getApellidos()}">
                        </div> 
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label for="dirrecion">Dirrecion: </label>
                            <input type="text" class="form-control" id="dirrecion" name="dirrecion" value="${clientes.getDireccion()}">

                        </div>                        
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                    <label for="sexo">Sexo:</label>
                    <select class="form-control" id="sexo" name="sexo">
                        <option value="masculino" ${clientes.getSexo() == 'masculino' ? 'selected' : ''}>Masculino</option>
                        <option value="femenino" ${clientes.getSexo() == 'femenino' ? 'selected' : ''}>Femenino</option>
                        <option value="otro" ${clientes.getSexo() == 'otro' ? 'selected' : ''}>Otro</option>
                    </select>
                </div>

                    </div>
                    
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="telefono">Teléfono:</label>
                            <input type="text" class="form-control" id="telefono" name="telefono"  value="${clientes.getTelefono()}" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="correo">Correo Electrónico:</label>
                    <input type="email" class="form-control" id="correo" name="correo" placeholder="correo@ejemplo.com" value="${clientes.getCorreo()}"  pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" required>
                </div>
                
                <div class="row">
                    
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="pais">Pais: </label>
                            <input type="text" class="form-control" id="pais" value="${clientes.getPais()}" name="pais">
                        </div>
                    </div>
                </div>
              
                
                
                <div class="form-group">
                    <label for="clave">Contraseña:</label>
                    <input type="password" class="form-control" id="clave" name="clave" required value="${clientes.getClave()}" >
                </div>
                
                
                <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                    <label for="rol">Rol</label>
                    <select class="form-control" id="rol" name="rol">
                        <option value="1" ${clientes.getRolid() == '1' ? 'selected' : ''}>Cliente</option>
                        <option value="2" ${clientes.getRolid() == '2' ? 'selected' : ''}>Administrador</option>                     
                </select>
                </div>
                <div class="centered-buttons text-center">
                    <button type="submit" class="btn btn-primary btn-register mb-2">Guardar</button>
                 
                </div>
            </form>
        </div>
   
    
     <script>
        function validarFormulario() {
            var nombreCliente = document.getElementById("nombres").value;
            var apellidoCliente = document.getElementById("apellidos").value;
            var correo = document.getElementById("correo").value;
            var clave = document.getElementById("clave").value;
            
            if (nombreCliente.trim() === "" || apellidoCliente.trim() === "" || correo.trim() === "" || clave.trim() ==="") {
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
</html>
