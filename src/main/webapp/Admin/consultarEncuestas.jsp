<%-- 
    Document   : conusltarEncuestas
    Created on : 11-01-2023, 06:17:00 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<title>Lista de Encuestas</title>
<%@ include file="/Layout/header.jsp" %>
<style>
    /* Estilo para la tabla de encuestas */
    .table {
        width: 100%;
        border-collapse: collapse;
    }

    .container {
        max-width: 85%;
    }

    .table th, .table td {
        border: 1px solid #ccc;
        padding: 8px;
        text-align: left;
    }

    .table th {
        background-color: #f2f2f2;
    }

    .btn {
        padding: 5px 10px;
        text-decoration: none;
        border: none;
        cursor: pointer;
    }

    .btn-primary {
        background-color: #007bff;
        color: #fff;
    }

    .btn-primary:hover {
        background-color: #0056b3;
    }

    .btn-danger {
        background-color: #dc3545;
        color: #fff;
    }

    .btn-danger:hover {
        background-color: #c82333;
    }
</style>
<div class="container">

<h1 class="mb-3">Lista de Encuestas</h1>

<!-- Filtros para las encuestas -->

<form method="post" action="FiltarEncuestas" class="form-inline d-flex align-items-center mb-4">
    <div class="form-group mr-2" style="margin-right: 15px;">
        <label for="fechaInicio" class="mr-2" >Filtrar por Fecha:</label>
        <input type="date" id="fechaInicio" name="fechaInicio" class="form-control" >
    </div>
    <div class="form-group mr-2">
        <label for="filtroNombre" class="mr-2" >Filtrar por Nombre:</label>
        <input type="text" id="filtroNombre" name="filtroNombre" class="form-control">
    </div>
    <button type="submit" class="btn btn-primary mt-4" style="margin-left: 15px;">Filtrar Encuestas</button>
</form>




<!-- Tabla para mostrar la lista de encuestas -->
<table id="miTabla" class="table">
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Sexo</th>
            <th>Deporte</th>
            <th>Nivel de Estudio</th>
            <th>Tema Favorito</th>
            <th>Fecha</th>
            <th>Acciones</th>
             
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${encuestas}" var="encuesta">
            <tr>
                <td>${encuesta.getNombre()}</td>
                <td>${encuesta.getSexo()}</td>
                <td>${encuesta.getDeporte()}</td>
                <td>${encuesta.getNivelEstudio()}</td>
                <td>${encuesta.getTemaFavorito()}</td>
                <td>${encuesta.getFecha()}</td>
                <td>
                    <a href="editarEncuesta?id=${encuesta.getIdEncuesta()}" class="btn btn-primary">
                        <i class="fas fa-pencil-alt"></i> Editar
                    </a>
                    <a href="eliminarEncuesta?id=${encuesta.getIdEncuesta()}" class="btn btn-danger" >
                        <i class="fas fa-trash"></i> Eliminar
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

<%@ include file="/Layout/footer.jsp" %>

