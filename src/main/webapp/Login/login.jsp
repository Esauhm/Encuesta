<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<title>Inicio de sesion</title>

<%@ include file="/Layout/header.jsp" %>

<style>
/* Estilo para el formulario de inicio de sesión */
.login-form {
    width: 100%; /* Ancho del 100% para ocupar todo el ancho disponible */
    max-width: 400px;
    margin:  auto;
    padding: 20px; /* Incrementa el espacio interno para hacerlo más grande */
    border: 1px solid #ccc;
    border-radius:10px;
    box-shadow: 0 4px 4px rgba(0, 0, 0, 0.1);
     transition: transform 0.2s; 
} 
.login-form:hover {
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
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.btn-login {
    display: block;
    width: 100%;
    padding: 10px;
    background-color: #007bff; /* Color de fondo azul */
    color: white !important; /* Color de texto blanco */
    border: none;
    border-radius: 5px;
    text-align: center;
    text-decoration: none;
    font-weight: bold;
}


.btn-login:hover {
    background-color: #0056b3; /* Cambio de color al pasar el mouse */
}

.btn-register {
    display: block;
    text-align: center;
    margin-top: 10px;
}

</style>

<!-- Contenido de la página de inicio de sesión -->
    <div class="login-form mt-5">
        <h2 class="text-center">Iniciar Sesión</h2>
        <form action="../LabTienda/login" method="post">
            <!-- Campos del formulario -->
            <div class="form-group">
                <!-- comment<label for="usuario">Usuario:</label> -->
                <input type="text" class="form-control" id="usuario" name="usuario"  placeholder="Correo Electronico"required>
            </div>
            <div class="form-group">
                <!-- <label for="clave">Contraseña:</label> --> 
                <input type="password" class="form-control" id="clave" name="clave" placeholder="Contraseña" required>
            </div>
            <button type="submit" class="btn btn-login">Iniciar Sesión</button>
        </form>
        <!-- Agregar el botón de registro -->
        <div class="text-center mt-3">
            <div class="text-body-secondary">
                          <hr>
                          <span>¿No tienes una cuenta?</span>
                   
                      </div>
            <a href="registro" >Registrarse</a>
        </div>
    </div>

<%@ include file="/Layout/footer.jsp" %>

