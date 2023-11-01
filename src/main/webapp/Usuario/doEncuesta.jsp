<%-- 
    Document   : doEncuesta
    Created on : 10-31-2023, 05:40:54 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario de Captura</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Responda el siguiente formalario</h2>
        <form action="enviarEncuesta" method="post">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
            </div>
            
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
                    <input type="radio" class="form-check-input" id="jockey" name="deporte" value="tenis">
                    <label class="form-check-label" for="jockey">Jockey</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="besibol" name="deporte" value="tenis">
                    <label class="form-check-label" for="besibol">Besibol</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="golf" name="deporte" value="tenis">
                    <label class="form-check-label" for="golg">Golf</label>
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
                    <input type="radio" class="form-check-input" id="television" name="temas" value="tecnologia">
                    <label class="form-check-label" for="television">Television</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="cocina" name="temas" value="deportes">
                    <label class="form-check-label" for "cocina">cocina</label>
                </div>
                 <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="tecnologia" name="temas[]" value="musica">
                    <label class="form-check-label" for="tecnologia">tecnologia</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="musica" name="temas[]" value="musica">
                    <label class="form-check-label" for="musica">Música</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="deportes" name="temas[]" value="musica">
                    <label class="form-check-label" for="deportes">deportes</label>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Enviar Datos</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
