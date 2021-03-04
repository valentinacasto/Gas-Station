package sample;

public class Vehiculo {
    private static int cont;
    private boolean limpio;
    private boolean cargado;
    private float fin_carga;
    private float fin_limpieza;
    private float fin_cobro;
    private String tipo_vehiculo;
    private float tiempo_llegada;
    private String estado;
    private int surtidor;
    private int mostrado = 0;
    private int idVehiculo;

    public Vehiculo() {
        cont++;
        this.idVehiculo = cont;
        this.limpio = true;
        this.cargado = false;
        this.fin_limpieza = 9999999;
    }

    public Vehiculo(boolean limpio, boolean cargado, String tipo_vehiculo, float tiempo_llegada) {
        this.limpio = false;
        this.cargado = false;
        this.tipo_vehiculo = tipo_vehiculo;
        this.tiempo_llegada = tiempo_llegada;
    }

    public Vehiculo(float fin_carga, float fin_limpieza, String tipo_vehiculo, float tiempo_llegada, String estado, int surtidor) {
        this.limpio = true;
        this.cargado = false;
        this.fin_carga = fin_carga;
        this.fin_limpieza = fin_limpieza;
        this.tipo_vehiculo = tipo_vehiculo;
        this.tiempo_llegada = tiempo_llegada;
        this.estado = estado;
        this.surtidor = surtidor;
    }

    public float setTipo_Vehiculo() {
        float random =  (float)Math.random();
        this.tipo_vehiculo = tipo_vehiculo;
        if (random < 0.35){
            this.tipo_vehiculo = "Moto";
        } else {
            if (random < 0.88) {
                this.tipo_vehiculo = "Auto";
            } else {
                this.tipo_vehiculo = "Camioneta";
            }
        }
        return random;
    }
    @Override
    public String toString() {
        if (this.mostrado > 1){
            String cadena =
                    "\t\t\t\t\t<td>" + " " +"</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n" +
                            "\t\t\t\t\t<td>" + " " +"</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n";
            return cadena;
        } else {
            String cadena =
                    "\t\t\t\t\t<td>" + idVehiculo +"</td>\n" +
                            "\t\t\t\t\t<td>" + tiempo_llegada + "</td>\n" +
                            "\t\t\t\t\t<td>" + estado +"</td>\n" +
                            "\t\t\t\t\t<td>" + tipo_vehiculo + "</td>\n";
            return cadena;
        }
    }

    public static int getCont() {
        return cont;
    }

    public static void setCont(int cont) {
        Vehiculo.cont = cont;
    }

    public int getMostrado() {
        return mostrado;
    }

    public void setMostrado(int mostrado) {
        this.mostrado = mostrado;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getEstado() {
        return estado;
    }

    public float getFin_cobro() {
        return fin_cobro;
    }

    public void setFin_cobro(float fin_cobro) {
        this.fin_cobro = fin_cobro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getSurtidor() {
        return surtidor;
    }

    public void setSurtidor(int surtidor) {
        this.surtidor = surtidor;
    }

    public boolean isLimpio() {
        return limpio;
    }

    public void setLimpio(boolean limpio) {
        this.limpio = limpio;
    }

    public boolean isCargado() {
        return cargado;
    }

    public void setCargado(boolean cargado) {
        this.cargado = cargado;
    }

    public float getFin_carga() {
        return fin_carga;
    }

    public void setFin_carga(float fin_carga) {
        this.fin_carga = fin_carga;
    }

    public float getFin_limpieza() {
        return fin_limpieza;
    }

    public void setFin_limpieza(float fin_limpieza) {
        this.fin_limpieza = fin_limpieza;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public float getTiempo_llegada() {
        return tiempo_llegada;
    }

    public void setTiempo_llegada(float tiempo_llegada) {
        this.tiempo_llegada = tiempo_llegada;
    }
}




