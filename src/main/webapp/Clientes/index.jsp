<%@page contentType="text/html" pageEncoding="UTF-8"%>


    <title>Lista de Clientes</title>
    <%@ include file="/Layout/header.jsp" %>
    <style>
        /* Estilo para la tabla de productos */
        .table {
            width: 100%;
            border-collapse: collapse;
        }

        .container{
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
        
    <h1 class="mb-3">Lista de Clientes</h1>
    
    <!-- Botón para agregar un nuevo producto -->
    <a href="clientecrear" class="btn btn-primary mb-3">
        <i class="fas fa-plus"></i> Agregar Cliente
    </a>

    <!-- Tabla para mostrar la lista de productos -->
    <table id="miTabla" class="table">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Sexo</th>
                <th>Correo</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${clientes}" var="cliente">
                <tr>
                    <td>${cliente.getNombres()}</td>
                    <td>${cliente.getApellidos()}</td>
                    <td>${cliente.getSexo()}</td>
                    <td>${cliente.getCorreo()}</td>
                    <td>
                        <a href="clienteseditar?id=${cliente.getIdCliente()}" class="btn btn-primary">
                            <i class="fas fa-pencil-alt"></i> Editar
                        </a>
                        <a href="#" class="btn btn-danger" onclick="eliminarElemento(${cliente.getIdCliente()}, 'cliente')">
                            <i class="fas fa-trash"></i> Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    
    <%@ include file="/Layout/footer.jsp" %>

