<%-- 
    Document   : editarEncuesta
    Created on : 11-01-2023, 05:07:09 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Ver Encuesta</title>
<%@ include file="/Layout/header.jsp" %>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Responda el siguiente formulario</h2>
        <form action="edicion" method="post">
            
             <div class="form-group">
                <input type="text" class="form-control" id="idencuesta" name="idencuesta" hidden value="${encuestas.getIdEncuesta()}">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="idcliente" name="idcliente" hidden value="${encuestas.getIdCliente()}">
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${encuestas.getNombre()}">
            </div>

            <div class="form-group">
                <label>Sexo:</label><br>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="masculino" name="sexo" value="masculino" ${encuestas.getSexo() == 'masculino' ? 'checked' : ''}>
                    <label class="form-check-label" for="masculino">Masculino</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="femenino" name="sexo" value="femenino" ${encuestas.getSexo() == 'femenino' ? 'checked' : ''}>
                    <label class="form-check-label" for="femenino">Femenino</label>
                </div>
            </div>

            <div class="form-group">
                <label>Deportes favoritos:</label><br>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="futbol" name="deporte" value="futbol" ${encuestas.getDeporte() == 'futbol' ? 'checked' : ''}>
                    <label class="form-check-label" for="futbol">Fútbol</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="baloncesto" name="deporte" value="baloncesto" ${encuestas.getDeporte() == 'baloncesto' ? 'checked' : ''}>
                    <label class="form-check-label" for="baloncesto">Basquetbal</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="jockey" name="deporte" value="jockey" ${encuestas.getDeporte() == 'jockey' ? 'checked' : ''}>
                    <label class="form-check-label" for="jockey">Jockey</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="beisbol" name="deporte" value="beisbol" ${encuestas.getDeporte() == 'beisbol' ? 'checked' : ''}>
                    <label class="form-check-label" for="beisbol">Beisbol</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="golf" name="deporte" value="golf" ${encuestas.getDeporte() == 'golf' ? 'checked' : ''}>
                    <label class="form-check-label" for="golf">Golf</label>
                </div>
                    
                <!-- Agrega lógica similar para las otras opciones de deporte -->
            </div>

            <div class="form-group">
                <label for="nivel_estudio">Nivel de Estudio:</label>
                <select class="form-control" id="nivel_estudio" name="nivel_estudio">
                    <option value="basico" ${encuestas.getNivelEstudio() == 'basico' ? 'selected' : ''}>Básico</option>
                    <option value="intermedio" ${encuestas.getNivelEstudio() == 'intermedio' ? 'selected' : ''}>Intermedio</option>
                    <option value="superior" ${encuestas.getNivelEstudio() == 'superior' ? 'selected' : ''}>Superior</option>
                </select>
            </div>

            <div class="form-group">
                <label>Temas favoritos:</label><br>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="television" name="temas" value="tecnologia" ${encuestas.getTemaFavorito() == 'tecnologia' ? 'checked' : ''}>
                    <label class="form-check-label" for="television">Television</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="cocina" name="temas" value="cocina" ${encuestas.getTemaFavorito() == 'cocina' ? 'checked' : ''}>
                    <label class="form-check-label" for="cocina">Cocina</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="musica" name="temas" value="musica" ${encuestas.getTemaFavorito() == 'musica' ? 'checked' : ''}>
                    <label class="form-check-label" for="musica">Música</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="deportes" name="temas" value="deportes" ${encuestas.getTemaFavorito() == 'deportes' ? 'checked' : ''}>
                    <label class="form-check-label" for="deportes">Deportes</label>
                </div>
            </div>


            <button type="submit" class="btn btn-primary">Actualizar</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

