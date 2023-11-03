<%-- 
    Document   : verEncuesta
    Created on : 11-01-2023, 01:38:30 PM
    Author     : Esau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<title>Ver Encuesta</title>
<%@ include file="/Layout/header.jsp" %>

<head>
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s;
        }

        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }

        .table {
            width: 100%;
            margin-bottom: 1rem;
            color: #333;
            background-color: #000; /* Color de fondo negro para la tabla */
        }

       .table thead th {
            background-color: #333; /* Color de fondo gris oscuro para el encabezado */
            color: #fff; /* Texto en blanco para el encabezado */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h2 class="text-center">Datos de la Encuesta</h2>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead> <!-- Agregar el elemento thead para los encabezados -->
                        <tr>
                            <th>Nombre</th>
                            <th>Sexo</th>
                            <th>Deporte Favorito</th>
                            <th>Nivel de Estudio</th>
                            <th>Temas Favoritos</th>
                            <th>Fecha</th>
                            <th>Editar</th>
                        </tr>
                    </thead>
                    <tbody>
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
                        <!-- Repite las filas según sea necesario -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>