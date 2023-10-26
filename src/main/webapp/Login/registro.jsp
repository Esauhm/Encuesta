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
        <div class="registration-form mt-5">
            <h2 class="text-center">Registrarse</h2>
            <form action="../LabTienda/registro" method="post">
                <!-- Campos del formulario -->
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="nombres">Nombres:</label>
                            <input type="text" class="form-control" id="nombres" name="nombres" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="apellidos">Apellidos:</label>
                            <input type="text" class="form-control" id="apellidos" name="apellidos" required>
                        </div> 
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label for="dirrecion">Dirrecion: </label>
                            <input type="text" class="form-control" id="dirrecion" name="dirrecion">

                        </div>                        
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="sexo">Sexo:</label>
                            <select class="form-control" id="sexo" name="sexo">
                                <option value="masculino">Masculino</option>
                                <option value="femenino">Femenino</option>
                                <option value="otro">Otro</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="telefono">Teléfono:</label>
                            <input type="text" class="form-control" id="telefono" name="telefono"  oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="correo">Correo Electrónico:</label>
                    <input type="email" class="form-control" id="correo" name="correo" placeholder="correo@ejemplo.com"  pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" required>
                </div>
                
                <div class="row">
                    
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="pais">Pais: </label>
                            <input type="text" class="form-control" id="pais" name="pais">
                        </div>
                    </div>
                </div>
                
                
                
                <div class="form-group">
                    <label for="clave">Contraseña:</label>
                    <input type="password" class="form-control" id="clave" name="clave" required>
                </div>
                <div class="centered-buttons text-center">
                    <button type="submit" class="btn btn-primary btn-register mb-2">Registrarse</button>
                      <div class="text-body-secondary">
                          <hr>
                          <span>¿Ya tienes una cuenta?</span>
                   
                      </div>
                    <a href="login" >Iniciar sesión</a><!<!-- class="btn btn-secondary btn-login -->
                </div>
            </form>
        </div>
    <%@ include file="/Layout/footer.jsp" %>
</body>
</html>
