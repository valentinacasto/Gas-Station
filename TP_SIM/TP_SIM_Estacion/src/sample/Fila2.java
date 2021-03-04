package sample;

import java.util.ArrayList;

public class Fila2 {
    private Fila2 filaAnterior;
    private int nroFila;
    private String evento;
    private String proximo_evento;
    private float reloj;
    private float proximo_reloj;
    private int cola1;
    private int cola2;
    private float random_limpieza;
    private float random_tipo_vehiculo;
    private String tipo_vehiculo;
    private float random_duracion_limpieza;
    private String limpieza;
    private float tiempo_limpieza;

    // Proxima llegada
    private float random_llegada;
    private float tiempo_entre_llegadas;
    private float proxima_llegada_vehiculo;

    // Servidores
    private String estado_S1;
    private String estado_S2;
    private float fin_carga_S1;
    private float fin_carga_S2;

    private float fin_limpieza_S1;
    private float fin_limpieza_S2;

    private float fin_cobro_S1;
    private float fin_cobro_S2;

    // Vehiculos
    private Vehiculo vehiculo_actual;
    private Vehiculo vehiculo_proximo;

    // Colecciones
    private ArrayList<Vehiculo> vehiculos_S1;
    private ArrayList<Vehiculo> vehiculos_S2;
    private ArrayList<Vehiculo> cola_vehiculos_S1;
    private ArrayList<Vehiculo> cola_vehiculos_S2;

    // Array de vehiculos total
    private ArrayList<Vehiculo> vehiculos;

    // Estadisticas
    private float acumulador_permanencia;
    private int contador_vehiculos;
    private float promedio_permanencia;
    private int max_vehiculos_S1;
    private int max_vehiculos_S2;
    private float ac_tiempo_ocupacion_S1;
    private float ac_tiempo_ocupacion_S2;
    private float porc_ocupacion_S1;
    private float porc_ocupacion_S2;
    private float tiempo_carga;


    // CONSTRUCTORES
    public Fila2() {
        this.proximo_reloj = 0;
        this.proximo_evento = "Llegada vehiculo";
        this.proxima_llegada_vehiculo = 0;
        this.vehiculos_S1 = new ArrayList<>();
        this.vehiculos_S2 = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        // this.cola_computadoras = new ArrayList<>();
        this.cola_vehiculos_S1 = new ArrayList<>();
        this.cola_vehiculos_S2 = new ArrayList<>();
        this.nroFila = -1;
        this.cola1 = 0;
        this.cola2 = 0;
        this.random_llegada = 0;
        this.random_duracion_limpieza = 0;
        this.random_limpieza = 0;
        this.random_tipo_vehiculo = 0;
        this.tiempo_limpieza = 0;
        this.estado_S1 = "Libre";
        this.estado_S2 = "Libre";


    }

    public Fila2(Fila2 filaAnterior){
        this.nroFila = filaAnterior.getNroFila() + 1;
        this.filaAnterior = filaAnterior;
        this.vehiculos_S1 = filaAnterior.getVehiculosS1();
        this.vehiculos_S2 = filaAnterior.getVehiculosS2();
        this.cola_vehiculos_S1 = filaAnterior.getCola_vehiculos_S1();
        this.cola_vehiculos_S2 = filaAnterior.getCola_vehiculos_S2();
        this.cola1 = filaAnterior.getCola1();
        this.cola2 = filaAnterior.getCola2();
        this.evento = filaAnterior.proximo_evento;
        this.reloj  = filaAnterior.proximo_reloj;
        this.estado_S1 = filaAnterior.estado_S1;
        this.estado_S2 = filaAnterior.estado_S2;
        this.proxima_llegada_vehiculo = filaAnterior.proxima_llegada_vehiculo;
        this.vehiculo_actual = filaAnterior.vehiculo_proximo;
        this.vehiculos = filaAnterior.vehiculos;
        this.fin_carga_S1 = filaAnterior.fin_carga_S1;
        fin_carga_S2 = filaAnterior.fin_carga_S2;

        fin_limpieza_S1 = filaAnterior.fin_limpieza_S1;
        fin_limpieza_S2 = filaAnterior.fin_limpieza_S2;

        fin_cobro_S1 = filaAnterior.fin_cobro_S1;
        fin_cobro_S2 = filaAnterior.fin_cobro_S2;

        this.ac_tiempo_ocupacion_S1 = filaAnterior.ac_tiempo_ocupacion_S1;
        this.ac_tiempo_ocupacion_S2 = filaAnterior.ac_tiempo_ocupacion_S2;

        this.max_vehiculos_S1 = filaAnterior.max_vehiculos_S1;
        this.max_vehiculos_S2 = filaAnterior.max_vehiculos_S2;

        this.acumulador_permanencia = filaAnterior.acumulador_permanencia;
        this.contador_vehiculos = filaAnterior.contador_vehiculos;

        this.random_llegada = 0;
        this.random_duracion_limpieza = 0;
        this.random_limpieza = 0;
        this.random_tipo_vehiculo = 0;
        this.tiempo_limpieza = 0;
        this.limpieza = " ";
        this.tipo_vehiculo = " ";

        if(nroFila == 0){
            this.evento = "Inicializacion";
            generarFila(evento);
        } else{
            generarFila(filaAnterior.proximo_evento);
        }
    }

    private void generarFila(String eventoFila) {
        switch (eventoFila) {

            case "Inicializacion":
                generarProximaLlegadaVehiculo();
                this.proximo_evento = "Llegada vehiculo";
                this.proximo_reloj = proxima_llegada_vehiculo;
                break;

            case "Llegada vehiculo":
                generarProximaLlegadaVehiculo();
                int surtidor = revisarDisponibilidadSurtidor();
                crearVehiculo(surtidor);
                elegirProximoEvento();
                calcularEstadisticas(" ");
                break;

            case "Fin carga":
                // Seteo fin carga para el surtidor que toco
                if (vehiculo_actual.getSurtidor() == 1)
                { this.fin_carga_S1 = 0; }
                else { this.fin_carga_S2 = 0; }

                vehiculo_actual.setCargado(true);
                if (vehiculo_actual.isLimpio()){generarFinCobro();}
                elegirProximoEvento();
                calcularEstadisticas("Fin carga");
                break;

            case "Fin limpieza":
                // Seteo fin limpieza para el surtidor que toco
                if (vehiculo_actual.getSurtidor() == 1) { this.fin_limpieza_S1 = 0; } else {this.fin_limpieza_S2 = 0; }

                vehiculo_actual.setLimpio(true);
                if (vehiculo_actual.isCargado()){generarFinCobro();}
                elegirProximoEvento();
                calcularEstadisticas(" ");
                break;

            case "Fin cobro":
                // Seteo fin cobro para el surtidor que toco
                if (vehiculo_actual.getSurtidor() == 1) { this.fin_cobro_S1 = 0; } else { this.fin_cobro_S2 = 0; }

                destruirVehiculo();
                revisarCola(vehiculo_actual.getSurtidor());
                elegirProximoEvento();
                calcularEstadisticas("Fin cobro");
                break;
        }
        noMostrarDestruidos();
        }

    private void revisarCola(int surtidor) {
        if (surtidor == 1){
            // Si no hay interrumpidas ni en cola de Cs se fija si hay compus en la cola
            if (cola_vehiculos_S1.size() > 0){
                // Lo obtenemos de la cola y lo sacamos
                Vehiculo vehiculo = cola_vehiculos_S1.get(0);
                cola_vehiculos_S1.remove(0);
                this.cola1--;
                // Cambiamos los atributos del vehiculo actual
                vehiculo.setEstado("Siendo atendido (1)");
                vehiculo.setSurtidor(1);
                calcularDuracionCarga(vehiculo);
                // Agregamos a los vectores los vehiculos
                vehiculos_S1.add(vehiculo);
                // Cambio el estado del surtidor
                this.estado_S1 = "Ocupado";

                // Si el vehiculo quiere limpieza le genero ademas su fin limpieza (SOLO SI ES AUTO O CAMIONETA)
                if (!(vehiculo.getTipo_vehiculo().equals("Moto"))){
                    this.random_limpieza = (float) Math.random();
                    if (random_limpieza < 0.3)
                    {this.limpieza = "Si";
                     this.calcularTiempoLimpieza(vehiculo);
                    }
                    else {limpieza = "No";}
                }
                return;
            }
            this.estado_S1 = "Libre";
        } else{
            if (cola_vehiculos_S2.size() > 0){
                Vehiculo vehiculo = cola_vehiculos_S2.get(0);
                cola_vehiculos_S2.remove(0);
                this.cola2--;
                vehiculo.setEstado("Siendo atendido (2)");
                vehiculo.setSurtidor(2);
                calcularDuracionCarga(vehiculo);
                vehiculos_S2.add(vehiculo);
                this.estado_S2 = "Ocupado";

                // Si el vehiculo quiere limpieza le genero ademas su fin limpieza (SOLO SI ES AUTO O CAMIONETA)
                if (!(vehiculo.getTipo_vehiculo().equals("Moto"))){
                    this.random_limpieza = (float) Math.random();
                    if (random_limpieza < 0.3)
                    { this.limpieza = "Si";
                      this.calcularTiempoLimpieza(vehiculo);}
                    else { limpieza = "No";}
                }
                return;
            }
            this.estado_S2 = "Libre";
        }
    }

    private void generarFinCobro() {
        vehiculo_actual.setFin_cobro(reloj + 2);
        if (vehiculo_actual.getSurtidor() == 1)
        {
            this.fin_cobro_S1 = reloj + 2;
        }
        else {
            this.fin_cobro_S2 = reloj + 2;
        }
    }

    // Setea el estado en Destruido a los vehiculos que tuvieron su fin cobro
    private void destruirVehiculo() { vehiculo_actual.setEstado("Destruido");}

    private void generarProximaLlegadaVehiculo() {
        // A partir de un RND se genera la proxima llegada
        this.random_llegada = (float) Math.random();
        float num = 1-random_llegada;
        this.tiempo_entre_llegadas = (float)((-3) * Math.log(num));
        this.proxima_llegada_vehiculo = tiempo_entre_llegadas + reloj;
    }

    // Revisa si alguno de los surtidores esta libre
    private int revisarDisponibilidadSurtidor() {
        // Si el 1 esta libre le setea el estado a Ocupado y retorna 1
        if (estado_S1.equals("Libre")){
            this.estado_S1 = "Ocupado";
            return 1;
        } else {
            // Si el 2 esta libre le setea el estado a Ocupado y retorna 2
            if (estado_S2.equals("Libre")){
                this.estado_S2 = "Ocupado";
                return 2;
            }
        }
        // Si ninguno esta libre retorna -1
        return -1;
    }

    // El vehiculo que acaba de llegar lo creo y lo voy a asignar o al array de un servidor o a la cola de alguno
    private void crearVehiculo(int surtidor) {
        Vehiculo vehiculo = new Vehiculo();
        // El vehiculo genera su propio tipo de vehiculo y se lo setea
        this.random_tipo_vehiculo = vehiculo.setTipo_Vehiculo();
        this.tipo_vehiculo = vehiculo.getTipo_vehiculo();
        vehiculo.setTiempo_llegada(reloj);
        vehiculo_actual = vehiculo;

        // Si el surtidor libre era el 1:
        if (surtidor == 1){
            vehiculo.setEstado("Siendo atendido (1)");
            vehiculo.setSurtidor(1);
            vehiculos_S1.add(vehiculo);
            vehiculos.add(vehiculo);
            // Calculamos la duracion de la carga y le seteamos el fin carga
            calcularDuracionCarga(vehiculo);

            // Si el vehiculo quiere limpieza le genero ademas su fin limpieza (SOLO SI ES AUTO O CAMIONETA)
            if (!(vehiculo.getTipo_vehiculo().equals("Moto"))){ this.calcularTiempoLimpieza(vehiculo); }
        } else {
            // Si el surtidor libre era el 2:
            if(surtidor == 2){
                vehiculo.setEstado("Siendo atendido (2)");
                vehiculo.setSurtidor(2);
                vehiculos_S2.add(vehiculo);
                vehiculos.add(vehiculo);
                // Calculamos la duracion de la carga y le seteamos el fin carga
                calcularDuracionCarga(vehiculo);

                // Si el vehiculo quiere limpieza le genero ademas su fin limpieza (SOLO SI ES AUTO O CAMIONETA)
                if (!(vehiculo.getTipo_vehiculo().equals("Moto"))){ this.calcularTiempoLimpieza(vehiculo);}
            } else {
                // Si los 2 servidores estan ocupados
                float random_cola = (float) Math.random();
                if (random_cola < 0.5)
                {
                    cola_vehiculos_S1.add(vehiculo);
                    vehiculos.add(vehiculo);
                    vehiculo.setEstado("En cola (1)");
                    this.cola1++;
                }
                else {
                    cola_vehiculos_S2.add(vehiculo);
                    vehiculos.add(vehiculo);
                    vehiculo.setEstado("En cola (2)");
                    this.cola2++;
                }
            }
        }
    }

    public void calcularTiempoLimpieza(Vehiculo vehiculo){
        this.random_limpieza = (float)Math.random();
        if (random_limpieza < 0.3) {
            this.limpieza = "Si";
            this.random_duracion_limpieza = (float) Math.random();
            this.tiempo_limpieza = 1 + random_duracion_limpieza;
            vehiculo.setFin_limpieza((float) (tiempo_limpieza + reloj + 0.5));
            vehiculo.setLimpio(false);
            if (vehiculo.getSurtidor() == 1) {
                this.fin_limpieza_S1 = (float) (tiempo_limpieza + reloj + 0.5);
            } else {
                this.fin_limpieza_S2 = (float) (tiempo_limpieza + reloj + 0.5);
            }
        }
        else { this.limpieza = "No"; }
    }

    private void calcularDuracionCarga(Vehiculo vehiculo) {
        if ((vehiculo.getTipo_vehiculo().equals("Moto"))){
            vehiculo.setFin_carga((float)1.15 + reloj);
            if (vehiculo.getSurtidor() == 1)
            { this.fin_carga_S1 = ((float)1.15 + reloj); this.tiempo_carga = (float)1.15; }
            else { this.fin_carga_S2 = (float)1.15 + reloj; this.tiempo_carga = (float)1.15; }
        }
        else {
            if ((vehiculo.getTipo_vehiculo().equals("Auto")))
            {
                vehiculo.setFin_carga((float)1.65 + reloj);
                if (vehiculo.getSurtidor() == 1)
                { this.fin_carga_S1 = ((float)1.65 + reloj); this.tiempo_carga = (float)1.65; }
                else { this.fin_carga_S2 = (float)1.65 + reloj; this.tiempo_carga = (float)1.65; }
            }
            else {
                vehiculo.setFin_carga((float)1.75 + reloj);
                if (vehiculo.getSurtidor() == 1)
                { this.fin_carga_S1 = ((float)1.75 + reloj); this.tiempo_carga = (float)1.75; }
                else { this.fin_carga_S2 = (float)1.75 + reloj; this.tiempo_carga = (float)1.75; }
            }
        }

    }

    // Elige proximo evento entre prox llegada, fin atencion, fin cobro, fin limpieza.
    private void elegirProximoEvento() {
        // Busca el menor tiempo para el prox evento y toma el vehiculo
        // a la que le corresponde ese evento y lo guarda en proximo vehiculo
        double min;
        if (proxima_llegada_vehiculo != 0)
        {
            min = proxima_llegada_vehiculo;
            proximo_evento = "Llegada vehiculo";
        }
        else {
            min = 999999999999999999999.0;
            proximo_evento = " ";
        }

        for(Vehiculo vehiculo: vehiculos)
        {
            if ((!(vehiculo.getEstado().equals("Destruido"))) && !(vehiculo.getEstado().equals("En cola (1)")) && !(vehiculo.getEstado().equals("En cola (2)"))) {

                if (vehiculo.isLimpio()) {

                if (vehiculo.isCargado()) {
                    if (vehiculo.getFin_cobro() < min) {
                        min = vehiculo.getFin_cobro();
                        this.proximo_evento = "Fin cobro";
                        this.vehiculo_proximo = vehiculo;
                    }
                } else {
                    if (vehiculo.getFin_carga() < min) {
                        min = vehiculo.getFin_carga();
                        this.proximo_evento = "Fin carga";
                        this.vehiculo_proximo = vehiculo;
                    }
                }
            }


            else {
                if (vehiculo.getFin_limpieza() < min) {
                            min = vehiculo.getFin_limpieza();
                            this.proximo_evento = "Fin limpieza";
                            this.vehiculo_proximo = vehiculo;
                }
                if (!vehiculo.isCargado()) {
                    if (vehiculo.getFin_carga() < min) {
                        min = vehiculo.getFin_carga();
                        this.proximo_evento = "Fin carga";
                        this.vehiculo_proximo = vehiculo;
                    }
                }
            }
        }
        this.proximo_reloj = (float)min;
        }
    }

    private void calcularEstadisticas(String e) {
        // Porcentajes de ocupación
        if (filaAnterior.estado_S1.equals("Ocupado"))
        {
            ac_tiempo_ocupacion_S1 += (reloj - filaAnterior.getReloj());
        }
        if (filaAnterior.estado_S2.equals("Ocupado"))
        {
            ac_tiempo_ocupacion_S2 += (reloj - filaAnterior.getReloj());
        }
        this.porc_ocupacion_S1 = this.ac_tiempo_ocupacion_S1 / reloj * 100;
        this.porc_ocupacion_S2 = this.ac_tiempo_ocupacion_S2 / reloj * 100;

        // Tiempo promedio de permanencia de los vehiculos en el sistema
        if (e.equals("Fin cobro"))
        {
            this.contador_vehiculos += 1;
            this.acumulador_permanencia += (reloj - vehiculo_actual.getTiempo_llegada());

        }
        if (contador_vehiculos != 0) { this.promedio_permanencia = acumulador_permanencia / contador_vehiculos; }

        //Maximo de vehiculos en cola1
        if (this.cola1 > filaAnterior.cola1) {this.max_vehiculos_S1 = this.cola1;}

        //Maximo de vehiculos en cola2
        if (this.cola2 > filaAnterior.cola2) {this.max_vehiculos_S2 = this.cola2;}
    }

    private void noMostrarDestruidos(){
        for (Vehiculo vehiculo : this.vehiculos) {
            if (vehiculo.getEstado().equals("Destruido")){
                vehiculo.setMostrado(vehiculo.getMostrado() + 1);
            }
        }
    }





    // GET Y SET
    public Fila2 getFilaAnterior() {
        return filaAnterior;
    }

    public void setFilaAnterior(Fila2 filaAnterior) {
        this.filaAnterior = filaAnterior;
    }

    public void setNroFila(int nroFila) {
        this.nroFila = nroFila;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getProximo_evento() {
        return proximo_evento;
    }

    public void setProximo_evento(String proximo_evento) {
        this.proximo_evento = proximo_evento;
    }

    public float getReloj() {
        return reloj;
    }

    public void setReloj(float reloj) {
        this.reloj = reloj;
    }

    public float getProximo_reloj() {
        return proximo_reloj;
    }

    public void setProximo_reloj(float proximo_reloj) {
        this.proximo_reloj = proximo_reloj;
    }

    public void setCola1(int cola) {
        this.cola1 = cola;
    }

    public void setCola2(int cola) {
        this.cola2 = cola;
    }

    public float getRandom_llegada() {
        return random_llegada;
    }

    public void setRandom_llegada(float random_llegada) {
        this.random_llegada = random_llegada;
    }

    public float getTiempo_entre_llegadas() {
        return tiempo_entre_llegadas;
    }

    public void setTiempo_entre_llegadas(float tiempo_entre_llegadas) {
        this.tiempo_entre_llegadas = tiempo_entre_llegadas;
    }

    public float getProxima_llegada_vehiculo() {
        return proxima_llegada_vehiculo;
    }

    public void setProxima_llegada_vehiculo(float proxima_llegada_vehiculo) {
        this.proxima_llegada_vehiculo = proxima_llegada_vehiculo;
    }

    public String getEstado_S1() {
        return estado_S1;
    }

    public void setEstado_S1(String estado_S1) {
        this.estado_S1 = estado_S1;
    }

    public String getEstado_S2() {
        return estado_S2;
    }

    public void setEstado_S2(String estado_S2) {
        this.estado_S2 = estado_S2;
    }

    public Vehiculo getVehiculo_actual() {
        return vehiculo_actual;
    }

    public void setVehiculo_actual(Vehiculo vehiculo_actual) {
        this.vehiculo_actual = vehiculo_actual;
    }

    public Vehiculo getVehiculo_proximo() {
        return vehiculo_proximo;
    }

    public void setVehiculo_proximo(Vehiculo vehiculo_proximo) {
        this.vehiculo_proximo = vehiculo_proximo;
    }

    public ArrayList<Vehiculo> getVehiculos_S1() {
        return vehiculos_S1;
    }

    public void setVehiculos_S1(ArrayList<Vehiculo> vehiculos_S1) {
        this.vehiculos_S1 = vehiculos_S1;
    }

    public ArrayList<Vehiculo> getVehiculos_S2() {
        return vehiculos_S2;
    }

    public void setVehiculos_S2(ArrayList<Vehiculo> vehiculos_S2) {
        this.vehiculos_S2 = vehiculos_S2;
    }

    public void setCola_vehiculos_S1(ArrayList<Vehiculo> cola_vehiculos_S1) {
        this.cola_vehiculos_S1 = cola_vehiculos_S1;
    }

    public void setCola_vehiculos_S2(ArrayList<Vehiculo> cola_vehiculos_S2) {
        this.cola_vehiculos_S2 = cola_vehiculos_S2;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    private ArrayList<Vehiculo> getCola_vehiculos_S2() {
        return this.cola_vehiculos_S2;
    }

    private ArrayList<Vehiculo> getCola_vehiculos_S1() {
        return this.cola_vehiculos_S1;
    }

    private ArrayList<Vehiculo> getVehiculosS1() {
        return this.vehiculos_S1;
    }

    private ArrayList<Vehiculo> getVehiculosS2() {
        return this.vehiculos_S2;
    }

    public int getNroFila() {
        return nroFila;
    }

    private int getCola1() {
        return this.cola1;
    }

    private int getCola2() {
        return this.cola2;
    }





    // ToString
    @Override
    public String toString() {
        return String.format("|%-8s", nroFila) + vehiculos.toString() + "\n";
    }

    public String toString2() {

        String cadena = "";
        cadena = "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>" + nroFila + "</td>\n" +
                "\t\t\t\t\t<td>" + evento + "</td>\n" +
                "\t\t\t\t\t<td>" + reloj + "</td>\n" +
                "\t\t\t\t\t<td>" + random_llegada + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempo_entre_llegadas + "</td>\n" +
                "\t\t\t\t\t<td>" + proxima_llegada_vehiculo + "</td>\n" +
                "\t\t\t\t\t<td>" + random_tipo_vehiculo + "</td>\n" +
                "\t\t\t\t\t<td>" + tipo_vehiculo + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempo_carga + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_carga_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_carga_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + random_limpieza + "</td>\n" +
                "\t\t\t\t\t<td>" + limpieza + "</td>\n" +
                "\t\t\t\t\t<td>" + random_duracion_limpieza + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempo_limpieza + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_limpieza_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_limpieza_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_cobro_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_cobro_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + estado_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + estado_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + cola1 + "</td>\n" +
                "\t\t\t\t\t<td>" + cola2 + "</td>\n" +
                "\t\t\t\t\t<td>" + acumulador_permanencia + "</td>\n" +
                "\t\t\t\t\t<td>" + contador_vehiculos + "</td>\n" +
                "\t\t\t\t\t<td>" + promedio_permanencia + "</td>\n" +
                "\t\t\t\t\t<td>" + max_vehiculos_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + max_vehiculos_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + ac_tiempo_ocupacion_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + ac_tiempo_ocupacion_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + porc_ocupacion_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + porc_ocupacion_S2 + "</td>\n" +
                toStringVehiculos() + "\n" +
                "\t\t\t\t</tr>\n";
        return cadena;
    }

    public String toString3(){

        String cadena = "\t\t\t<tfoot>\n" +
                "\t\t\t\t\t<td>" + nroFila + "</td>\n" +
                "\t\t\t\t\t<td>" + evento + "</td>\n" +
                "\t\t\t\t\t<td>" + reloj + "</td>\n" +
                "\t\t\t\t\t<td>" + random_llegada + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempo_entre_llegadas + "</td>\n" +
                "\t\t\t\t\t<td>" + proxima_llegada_vehiculo + "</td>\n" +
                "\t\t\t\t\t<td>" + random_tipo_vehiculo + "</td>\n" +
                "\t\t\t\t\t<td>" + tipo_vehiculo + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempo_carga + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_carga_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_carga_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + random_limpieza + "</td>\n" +
                "\t\t\t\t\t<td>" + limpieza + "</td>\n" +
                "\t\t\t\t\t<td>" + random_duracion_limpieza + "</td>\n" +
                "\t\t\t\t\t<td>" + tiempo_limpieza + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_limpieza_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_limpieza_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_cobro_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + fin_cobro_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + estado_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + estado_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + cola1 + "</td>\n" +
                "\t\t\t\t\t<td>" + cola2 + "</td>\n" +
                "\t\t\t\t\t<td>" + acumulador_permanencia + "</td>\n" +
                "\t\t\t\t\t<td>" + contador_vehiculos + "</td>\n" +
                "\t\t\t\t\t<td>" + promedio_permanencia + "</td>\n" +
                "\t\t\t\t\t<td>" + max_vehiculos_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + max_vehiculos_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + ac_tiempo_ocupacion_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + ac_tiempo_ocupacion_S2 + "</td>\n" +
                "\t\t\t\t\t<td>" + porc_ocupacion_S1 + "</td>\n" +
                "\t\t\t\t\t<td>" + porc_ocupacion_S2 + "</td>\n" +
                toStringVehiculos() + "\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</tfoot>\n";
        return cadena;
    }

    private String toStringVehiculos(){
        String cadena = "";
        for (Vehiculo vehiculo : vehiculos) {
            cadena = cadena + vehiculo.toString();
        }
        return cadena;
    }

}
