package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Simulacion2 {

    public int cantSimulaciones;
    public int contador;
    public int desde;    // MostrarDesde
    public int hasta;    // MostrarHasta
    private Fila2 fila;
    private ArrayList<Fila2> filasMostrar;
    private Fila2 ultimaFila;
    private String html;

    public Simulacion2(int cantSimulaciones, int contador, int desde, int cantFilas) throws IOException {
        this.cantSimulaciones = cantSimulaciones;
        this.contador = contador;
        this.desde = desde;
        this.hasta = cantFilas;
        this.filasMostrar = new ArrayList<Fila2>();
        this.html = "";
        generarSimulacion();
        Vehiculo.setCont(0);
    }

    public void generarSimulacion() throws IOException {

        for (int i = 0; i < cantSimulaciones; i++) {
            Fila2 filaAnterior;
            if (i == 0){
                filaAnterior = new Fila2();
                this.fila = new Fila2(filaAnterior);

            } else {
                filaAnterior = fila;
                this.fila = new Fila2(filaAnterior);

            }
            if (i >= desde && i <= hasta){
                filasMostrar.add(fila);
                this.html = html + fila.toString2();
            }

            if (i == cantSimulaciones - 1){
                this.ultimaFila = fila;
            }
        }
        this.html = html + ultimaFila.toString3();
        crearHTML();
    }

    public void crearHTML() throws IOException {
        Writer wr2 = new FileWriter("src/sample/prueba.html");
        String encabezados_vehiculos = "";
        for (int i = 0; i < ultimaFila.getVehiculos().size(); i++) {
            encabezados_vehiculos = encabezados_vehiculos + htmlVehiculo();
        }
        wr2.write(html1(encabezados_vehiculos));
        wr2.write(html);
        wr2.flush();
        wr2.close();
    }

    public String htmlVehiculo(){
        String cadena = "\t\t\t\t\t<th>Id Vehiculo</th>\n" +
                "\t\t\t\t\t<th>Llegada Vehiculo</th>\n" +
                "\t\t\t\t\t<th>Estado</th>\n" +
                "\t\t\t\t\t<th>Tipo Vehiculo</th>\n";
        return cadena;
    }

    public String html1(String encabezados_vehiculos){
        String cadena = "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>Trabajo Practico 7</title>\n" +
                "\t\t <link rel=\"shortcut icon\" href=\"computadora1.png\">\n" +
                "\t\t <link href=\"Style.css\" type=\"text/css\" rel=\"stylesheet\" media=\"\">\n" +
                "\t\t <link rel=\"shortcut icon\" href=\"computer1.png\">" +
                "\t</head>\n" +
                "\t\n" +
                "\t<body>\n" +
                "\n" +
                "\t\t<table class=\"tabla71\">\n" +
                "\t\t\t<thead>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<th>Nro Fila</th>\n" +
                "\t\t\t\t\t<th>Evento</th>\n" +
                "\t\t\t\t\t<th>Reloj(min)</th>\n" +
                "\t\t\t\t\t<th>RND llegada vehiculo</th>\n" +
                "\t\t\t\t\t<th>Tiempo entre llegadas</th>\n" +
                "\t\t\t\t\t<th>Proxima llegada vehiculo</th>\n" +
                "\t\t\t\t\t<th>RND Tipo</th>\n" +
                "\t\t\t\t\t<th>Tipo vehiculo</th>\n" +
                "\t\t\t\t\t<th>Tiempo carga</th>\n" +
                "\t\t\t\t\t<th>Fin carga 1</th>\n" +
                "\t\t\t\t\t<th>Fin carga 2</th>\n" +
                "\t\t\t\t\t<th>RND limpieza</th>\n" +
                "\t\t\t\t\t<th>Limpieza</th>\n" +
                "\t\t\t\t\t<th>RND tiempo limpieza</th>\n" +
                "\t\t\t\t\t<th>Tiempo limpieza</th>\n" +
                "\t\t\t\t\t<th>Fin limpieza 1</th>\n" +
                "\t\t\t\t\t<th>Fin limpieza 2</th>\n" +
                "\t\t\t\t\t<th>Fin cobro 1</th>\n" +
                "\t\t\t\t\t<th>Fin cobro 2</th>\n" +
                "\t\t\t\t\t<th>Surtidor 1</th>\n" +
                "\t\t\t\t\t<th>Surtidor 2</th>\n" +
                "\t\t\t\t\t<th>Cola1</th>\n" +
                "\t\t\t\t\t<th>Cola2</th>\n" +
                "\t\t\t\t\t<th>AC permanencia</th>\n" +
                "\t\t\t\t\t<th>CONT vehiculos</th>\n" +
                "\t\t\t\t\t<th>PROM permanencia</th>\n" +
                "\t\t\t\t\t<th>MAX cola 1</th>\n" +
                "\t\t\t\t\t<th>MAX cola 2</th>\n" +
                "\t\t\t\t\t<th>AC tiempo ocupacion S1</th>\n" +
                "\t\t\t\t\t<th>AC tiempo ocupacion S2</th>\n" +
                "\t\t\t\t\t<th>% Ocupacion S1</th>\n" +
                "\t\t\t\t\t<th>% Ocupacion S2</th>\n" +
                encabezados_vehiculos +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</thead>\n" +
                "\t\t\t\n" +
                "\t\t\t<tbody>\n";
        return cadena;
    }
}
