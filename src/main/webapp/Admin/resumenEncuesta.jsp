<%-- 
    Document   : resumenEncuesta
    Created on : 11-01-2023, 09:56:30 PM
    Author     : Esau
--%>
<%@page import="modelos.Encuesta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<title>Lista de Encuestas</title>
<%@ include file="/Layout/header.jsp" %>

<style>
    /* Agregar sombreado al hover de los cards */
.card:hover {
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.2); /* Cambia los valores según tus preferencias */
    transition: box-shadow 0.3s ease-in-out;
}

/* Cambiar el color de fondo del card al hacer hover */
.card:hover {
    background-color: #f8f9fa; /* Cambia el color de fondo según tus preferencias */
    transition: background-color 0.3s ease-in-out;
}

.card-header {
    background: url("https://img.freepik.com/vector-premium/diseno-fondo-holograma-color-pastel-abstracto-horizontal_29865-3302.jpg") center/cover no-repeat;
/*    color: white;
    text-align: center;*/
    padding: 22px;
}
</style>

<div class="container card">
    <div class="card mb-4">
    <div class="card-header">
        <h2 class="mb-4 mt-5">1. Sexo de las personas encuestadas</h2>
    </div>
    <div class="card-body">
        <div class="row">
            <!-- Gráfico de barras -->
            <div class="col-4">
                <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                    <h3>Distribución de Sexo</h3>
                    <canvas id="graficoBarras"></canvas>
                </div>
            </div>
            <!-- Gráfico de pastel -->
            <div class="col-4 mx-auto">
                <div class="text-center" style="width: 70%; height: 350px; text-align: center; margin-left: 100px;">
                    <h3>Distribución de Sexo</h3>
                    <canvas id="graficoPastel"></canvas>
                </div>
            </div>
            <!-- Gráfico de línea -->
            <div class="col-4">
                <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                    <h3>Distribución de Sexo</h3>
                    <canvas id="graficoLinea"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="card mb-4">
    <div class="card-header">
        <h2 class="mb-4 mt-5">2. Deportes favoritos de los encuestados</h2>
    </div>
    <div class="card-body">
        <div class="row">
            <!-- Gráfico de barras para deporte -->
            <div class="col-4">
                <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                    <h3>Distribución de deporte</h3>
                    <canvas id="graficoBarrasDeporte"></canvas>
                </div>
            </div>
            <!-- Gráfico de pastel para deporte -->
            <div class="col-4 mx-auto">
                <div class="text-center" style="width: 70%; height: 350px; text-align: center; margin-left: 100px;">
                    <h3>Distribución de deporte</h3>
                    <canvas id="graficoPastelDeporte"></canvas>
                </div>
            </div>
            <!-- Gráfico de línea para deporte -->
            <div class="col-4">
                <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                    <h3>Distribución de deporte</h3>
                    <canvas id="graficoLineaDeporte"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>
    
    
    <div class="card mb-4">
        <div class="card-header">
            <h2 class="mb-4 mt-5">3. Nivel de estudio de los encuestados</h2>
        </div>
        <div class="card-body">
            <div class="row">
                <!-- Gráfico de barras para deporte -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Distribución de nivel de estudio</h3>
                        <canvas id="graficoBarrasEstudio"></canvas>
                    </div>
                </div>
                <!-- Gráfico de pastel para deporte -->
                <div class="col-4 mx-auto">
                    <div class="text-center" style="width: 70%; height: 350px; text-align: center; margin-left: 100px;">
                        <h3>Distribución de nivel de estudio</h3>
                        <canvas id="graficoPastelEstudio"></canvas>
                    </div>
                </div>
                <!-- Gráfico de línea para deporte -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Distribución de nivel de estudio</h3>
                        <canvas id="graficoLineaEstudio"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
        <div class="card mb-4">
        <div class="card-header">
            <h2 class="mb-4 mt-5">4. Temas favoritos de los encuestados</h2>
        </div>
        <div class="card-body">
            <div class="row">
                <!-- Gráfico de barras para deporte -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Distribución de temas favoritos</h3>
                        <canvas id="graficoBarrasTemas"></canvas>
                    </div>
                </div>
                <!-- Gráfico de pastel para deporte -->
                <div class="col-4 mx-auto">
                    <div class="text-center" style="width: 70%; height: 350px; text-align: center; margin-left: 100px;">
                        <h3>Distribución de temas favoritos</h3>
                        <canvas id="graficoPastelTemas"></canvas>
                    </div>
                </div>
                <!-- Gráfico de línea para deporte -->
                <div class="col-4">
                    <div class="text-center" style="width: 100%; height: 350px; text-align: center;">
                        <h3>Distribución de temas favoritos</h3>
                        <canvas id="graficoLineaTemas"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

















<script src="
https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js
"></script>

<script>
   // Obtener los datos JSON de encuestas
var encuestasJSON = ${encuestasJSON};

// Inicializar contadores para sexos
var hombres = 0;
var mujeres = 0;
var otros = 0;

// Contar cuántas respuestas corresponden a cada sexo
for (var i = 0; i < encuestasJSON.length; i++) {
    var sexo = encuestasJSON[i]['sexo']; // Accede a la propiedad usando notación de corchetes
    if (sexo === "masculino") {
        hombres++;
    } else if (sexo === "femenino") {
        mujeres++;
    } else {
        otros++;
    }
}
// Crear un gráfico de barras para la distribución de sexos
var ctxBarras = document.getElementById('graficoBarras').getContext('2d');
var graficoBarras = new Chart(ctxBarras, {
    type: 'bar',
    data: {
        labels: ['Hombres', 'Mujeres'],
        datasets: [{
            label: 'Distribución de Sexo',
            data: [hombres, mujeres],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)'
            ],
            borderWidth: 1
        }]
    }
});

// Crear un gráfico de pastel para la distribución de sexos
var ctxPastel = document.getElementById('graficoPastel').getContext('2d');
var graficoPastel = new Chart(ctxPastel, {
    type: 'pie',
    data: {
        labels: ['Hombres', 'Mujeres'],
        datasets: [{
            data: [hombres, mujeres],
            backgroundColor: [
               'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)'
            ],
            borderColor: [
               'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)'
            ],
            borderWidth: 1
        }]
    }
});

// Crear un gráfico de líneas para la distribución de sexos
var ctxLinea = document.getElementById('graficoLinea').getContext('2d');
var graficoLinea = new Chart(ctxLinea, {
    type: 'line',
    data: {
        labels: ['Hombres', 'Mujeres'],
        datasets: [{
            label: 'Distribución de Sexo (Línea)',
            data: [hombres, mujeres],
            fill: false, // No rellenes la línea
            borderColor: 'rgba(75, 192, 192, 1)', // Color de la línea
            borderWidth: 2
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

//deporte -------------------------


// Inicializar contadores para sexos
var futbol = 0;
var baloncesto = 0;
var jockey = 0;
var beisbol = 0;
var golf = 0;

// Contar cuántas respuestas corresponden a cada sexo
for (var i = 0; i < encuestasJSON.length; i++) {
    var deporte = encuestasJSON[i]['deporte']; // Accede a la propiedad usando notación de corchetes
    if (deporte === "futbol") {
        futbol++;
    } else if (deporte === "baloncesto") {
        baloncesto++;
    }else if (deporte === "jockey") {
        jockey++;
    }else if (deporte === "beisbol") {
        jockey++;
    }else if (deporte === "golf") {
        jockey++;
    }
     
}

// Crear un gráfico de barras para la distribución de sexos
var ctxBarras = document.getElementById('graficoBarrasDeporte').getContext('2d');
var graficoBarras = new Chart(ctxBarras, {
    type: 'bar',
    data: {
        labels: ['futbol', 'baloncesto', 'jockey', 'beisbol', 'golf'],
        datasets: [{
            label: 'Deportes',
            data: [futbol, baloncesto, jockey, beisbol, golf],
           backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)', // Nuevo color
                'rgba(255, 159, 64, 0.2)',  // Nuevo color
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)', // Nuevo color
                'rgba(255, 159, 64, 1)',  // Nuevo color
            ],
            borderWidth: 2
        }]
    }
});

// Crear un gráfico de pastel para la distribución de sexos
var ctxPastel2 = document.getElementById('graficoPastelDeporte').getContext('2d');
var graficoPastel = new Chart(ctxPastel2, {
    type: 'pie',
    data: {
        labels: ['futbol', 'baloncesto', 'jockey', 'beisbol', 'golf'],
        datasets: [{
            label: 'Deportes',
            data: [futbol, baloncesto, jockey, beisbol, golf],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)', // Nuevo color
                'rgba(255, 159, 64, 0.2)',  // Nuevo color
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)', // Nuevo color
                'rgba(255, 159, 64, 1)',  // Nuevo color
            ],
            borderWidth: 2
        }]
    }
});

// Crear un gráfico de líneas para la distribución de deportes
var ctxLine = document.getElementById('graficoLineaDeporte').getContext('2d');
var graficoLinea = new Chart(ctxLine, {
    type: 'line',
    data: {
        labels: ['futbol', 'baloncesto', 'jockey', 'beisbol', 'golf'],
        datasets: [{
            label: 'Deportes',
            data: [futbol, baloncesto, jockey, beisbol, golf],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 2
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

//nivel de estudio -------------------------


// Inicializar contadores para sexos
var basico = 0;
var intermedio = 0;
var superior = 0;


// Contar cuántas respuestas corresponden a cada sexo
for (var i = 0; i < encuestasJSON.length; i++) {
    var estudio = encuestasJSON[i]['nivel_estudio']; // Accede a la propiedad usando notación de corchetes
    if (estudio === "basico") {
        basico++;
    } else if (estudio === "intermedio") {
        intermedio++;
    }else if (estudio === "superior") {
        superior++;
    }
     
}

// Crear un gráfico de barras para la distribución de sexos
var ctxBarras = document.getElementById('graficoBarrasEstudio').getContext('2d');
var graficoBarras = new Chart(ctxBarras, {
    type: 'bar',
    data: {
        labels: ['basico', 'intermedio', 'superior'],
        datasets: [{
            label: 'Nivel de estudio',
            data: [basico, intermedio, superior],
           backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)', // Nuevo color
                'rgba(255, 159, 64, 0.2)',  // Nuevo color
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)', // Nuevo color
                'rgba(255, 159, 64, 1)',  // Nuevo color
            ],
            borderWidth: 2
        }]
    }
});

// Crear un gráfico de pastel para la distribución de sexos
var ctxPastel2 = document.getElementById('graficoPastelEstudio').getContext('2d');
var graficoPastel = new Chart(ctxPastel2, {
    type: 'pie',
    data: {
        labels: ['basico', 'intermedio', 'superior'],
        datasets: [{
            label: 'nivel de estudio',
            data: [basico, intermedio, superior],
           backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)', // Nuevo color
                'rgba(255, 159, 64, 0.2)',  // Nuevo color
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)', // Nuevo color
                'rgba(255, 159, 64, 1)',  // Nuevo color
            ],
            borderWidth: 2
        }]
    }
});

// Crear un gráfico de líneas para la distribución de deportes
var ctxLine = document.getElementById('graficoLineaEstudio').getContext('2d');
var graficoLinea = new Chart(ctxLine, {
    type: 'line',
    data: {
         labels: ['basico', 'intermedio', 'superior'],
        datasets: [{
            label: 'nivel de estudio',
           data: [basico, intermedio, superior],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 2
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});


// Inicializar contadores para sexos
var television = 0;
var cocina = 0;
var tecnologia = 0;
var musica = 0;
var deportes = 0;


// Contar cuántas respuestas corresponden a cada sexo
for (var i = 0; i < encuestasJSON.length; i++) {
    var tema = encuestasJSON[i]['tema_favorito']; // Accede a la propiedad usando notación de corchetes
    if (tema === "television") {
        television++;
    } else if (tema === "cocina") {
        cocina++;
    }else if (tema === "tecnologia") {
        tecnologia++;
    }else if (tema === "musica") {
        musica++;
    }else if (tema === "deporte") {
        deportes++;
    }
     
}


// Crear un gráfico de barras para la distribución de sexos
var ctxBarras = document.getElementById('graficoBarrasTemas').getContext('2d');
var graficoBarras = new Chart(ctxBarras, {
    type: 'bar',
    data: {
        labels: ['television', 'cocina', 'tecnologia', 'musica', 'deporte'],
        datasets: [{
            label: 'Temas Favorito',
            data: [television, cocina, tecnologia, musica, deportes],
           backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)', // Nuevo color
                'rgba(255, 159, 64, 0.2)',  // Nuevo color
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)', // Nuevo color
                'rgba(255, 159, 64, 1)',  // Nuevo color
            ],
            borderWidth: 2
        }]
    }
});

// Crear un gráfico de pastel para la distribución de sexos
var ctxPastel2 = document.getElementById('graficoPastelTemas').getContext('2d');
var graficoPastel = new Chart(ctxPastel2, {
    type: 'pie',
    data: {
        labels: ['television', 'cocina', 'tecnologia', 'musica', 'deporte'],
        datasets: [{
            label: 'Temas Favorito',
            data: [television, cocina, tecnologia, musica, deportes],
           backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)', // Nuevo color
                'rgba(255, 159, 64, 0.2)',  // Nuevo color
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)', // Nuevo color
                'rgba(255, 159, 64, 1)',  // Nuevo color
            ],
            borderWidth: 2
        }]
    }
});

// Crear un gráfico de líneas para la distribución de deportes
var ctxLine = document.getElementById('graficoLineaTemas').getContext('2d');
var graficoLinea = new Chart(ctxLine, {
    type: 'line',
    data: {
         labels: ['television', 'cocina', 'tecnologia', 'musica', 'deporte'],
        datasets: [{
            label: 'nivel de estudio',
           data: [television, cocina, tecnologia, musica, deportes],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 2
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});


</script>


