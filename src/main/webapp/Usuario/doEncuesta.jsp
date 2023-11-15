<%-- 
    Document   : doEncuesta
    Created on : 10-31-2023, 05:40:54 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Ver Encuesta</title>
<%@ include file="/Layout/header.jsp" %>
<style>
    body {
        background-color: #f8f9fa;
    }

    .container {
        padding: 20px;
        margin-top: 50px;
    }

    .col-6 {
        margin: 0 auto;
    }

    .card {
        border: none;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .card-header {
        background: url("https://img.freepik.com/fotos-premium/horizontal-oscuro-1-negro-grunge-textura-cemento-showroom-o-muro-hormigon-banner-estudio-fondo-blanco_28629-2065.jpg?w=360") center/cover no-repeat;
        color: white;
        text-align: center;
        padding: 30px; /* Ajusta el padding para cambiar el tamaño del header */
    }

    h2 {
        font-size: 30px;
    }

    label {
        font-weight: bold;
    }

    .form-group {
        margin-bottom: 20px;
    }

    .form-check-input {
        margin-right: 5px;
    }

    .form-check-label {
        font-size: 18px; /* Ajusta el tamaño del texto del checkbox */
    }

    button {
        background-color: #007bff;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
    }

    button:hover {
        background-color: #0056b3;
    }
    
    .center-button {
        text-align: center;
    }
</style>


<body>
    <div class="container mt-5">
        <div class="col-6">
            <div class="card">
                <div class="card-header">
                    <h2 class="text-center mb-4" style="font-size: 40px;">Datos de la Encuesta</h2>
                </div>
                <div class="card-body">
                    <form action="enviarEncuesta" method="post">
                        <!-- Resto del formulario -->
                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required>
                        </div>

                        <!-- Resto del formulario -->
                        <!-- Resto del formulario -->
                        <div class="form-group">
                            <label>Sexo:</label><br>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="masculino" name="sexo" value="masculino">
                                <label class="form-check-label" for="masculino">Masculino</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="femenino" name="sexo" value="femenino">
                                <label class="form-check-label" for="femenino">Femenino</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Deportes favoritos:</label><br>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="futbol" name="deporte" value="futbol">
                                <label class="form-check-label" for="futbol">Fútbol</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="baloncesto" name="deporte" value="baloncesto">
                                <label class="form-check-label" for="baloncesto">Baloncesto</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="jockey" name="deporte" value="jockey">
                                <label class="form-check-label" for="jockey">Jockey</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="beisbol" name="deporte" value="beisbol">
                                <label class="form-check-label" for="beisbol">Beisbol</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="golf" name="deporte" value="golf">
                                <label class="form-check-label" for="golf">Golf</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="nivel_estudio">Nivel de Estudio:</label>
                            <select class="form-control" id="nivel_estudio" name="nivel_estudio">
                                <option value="basico">Básico</option>
                                <option value="intermedio">Intermedio</option>
                                <option value="superior">Superior</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Temas favoritos:</label><br>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="television" name="temas" value="television">
                                <label class="form-check-label" for="television">Television</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="cocina" name="temas" value="cocina">
                                <label class="form-check-label" for "cocina">cocina</label>
                            </div>
                             <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="tecnologia" name="temas" value="tecnologia">
                                <label class="form-check-label" for="tecnologia">tecnologia</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="musica" name="temas" value="musica">
                                <label class="form-check-label" for="musica">Música</label>
                            </div>
                             <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="depoprte" name="temas" value="depoprte">
                                <label class="form-check-label" for="depoprte">Deportes</label>
                            </div>

                                
                        <div class="center-button mt-4">
                            <button type="submit" class="btn btn-success">Enviar Datos</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


