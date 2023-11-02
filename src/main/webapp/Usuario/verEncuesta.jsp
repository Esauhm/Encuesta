<%-- 
    Document   : verEncuesta
    Created on : 11-01-2023, 01:38:30 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Ver Encuesta</title>
<%@ include file="/Layout/header.jsp" %>

<body>

    <div class="container mt-5">
        <h2 class="text-center">Datos de la Encuesta</h2>
        <table class="table table-bordered">
            <tr>
                <th>Nombre</th>
                <th>Sexo</th>
                <th>Deporte Favorito</th>
                <th>Nivel de Estudio</th>
                <th>Temas Favoritos</th>
                <th>Editar</th>
            </tr>
            <tr>
                <td>${encuestas.getNombre()}</td>
                <td>${encuestas.getSexo()}</td>
                <td>${encuestas.getDeporte()}</td>
                <td>${encuestas.getNivelEstudio()}</td>
                <td>${encuestas.getTemaFavorito()}</td>
                <td>${encuestas.getFecha()}</td>
                <td>
                    <a href="editarEncuesta?id=${encuestas.getIdEncuesta()}" class="btn btn-primary">
                        <i class="fas fa-edit"></i> Editar
                    </a>
                </td>

            </tr>
        </table>
    </div>
</body>            
          

