package sample;

public class Computadora {
    private static int cont;
    private int idCompu;
    private String tipo_arreglo;
    private float random_fin_arreglo;
    private float ingreso_laboratorio;
    private float fin_arreglo;
    private float fin_formateo_A;
    private float fin_formateo_B;
    private int media;
    private String estado;
    private int tecnico;
    private int mostrado = 0;
    private int etapa_formateo;
    private float tiempo_remanente;
    private float tiempo_remanente_A;
    private float tiempo_remanente_B;


    public Computadora() {
        cont++;
        this.idCompu = cont;
        this.fin_formateo_A = 999999999;
        this.fin_formateo_B = 999999999;
        this.tiempo_remanente = 0;
        this.tiempo_remanente_A = 0;
        this.tiempo_remanente_B = 0;
        this.etapa_formateo = -1;
    }

    public void setTipo_arreglo() {
        float random =  (float)Math.random();
        this.tipo_arreglo = tipo_arreglo;
        if (random < 0.3){
            this.tipo_arreglo = "A";
            this.media = 115;
        } else {
            if (random < 0.55){
                this.tipo_arreglo = "B";
                this.media = 55;
            } else {
                if (random < 0.7){
                    this.tipo_arreglo = "C";
                    this.media = 175;
                } else {
                    if (random < 0.8){
                        this.tipo_arreglo = "D";
                        this.media = 55;
                    } else {
                        this.tipo_arreglo = "E";
                        this.media = 85;
                    }
                }
            }
        }
    }


    @Override
    public String toString() {
        if (this.mostrado > 1){
            String cadena =
                            "\t\t\t\t\t<td>" + " " +"</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n" +
                            "\t\t\t\t\t<td>" + " " +"</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n" +
                            "\t\t\t\t\t<td>" + " " + "</td>\n";
            return cadena;
        } else {
            String cadena =
                            "\t\t\t\t\t<td>" + idCompu +"</td>\n" +
                            "\t\t\t\t\t<td>" + ingreso_laboratorio + "</td>\n" +
                            "\t\t\t\t\t<td>" + estado +"</td>\n" +
                            "\t\t\t\t\t<td>" + tiempo_remanente + "</td>\n" +
                            "\t\t\t\t\t<td>" + fin_arreglo + "</td>\n" +
                            "\t\t\t\t\t<td>" + tipo_arreglo + "</td>\n";
            return cadena;
        }
    }


    public float getTiempo_remanente_A() {
        return tiempo_remanente_A;
    }

    public void setTiempo_remanente_A(float tiempo_remanente_A) {
        this.tiempo_remanente_A = tiempo_remanente_A;
    }

    public float getTiempo_remanente_B() {
        return tiempo_remanente_B;
    }

    public void setTiempo_remanente_B(float tiempo_remanente_B) {
        this.tiempo_remanente_B = tiempo_remanente_B;
    }

    public int getEtapa_formateo() {
        return etapa_formateo;
    }

    public void setEtapa_formateo(int etapa_formateo) {
        this.etapa_formateo = etapa_formateo;
    }

    public int getMostrado() {
        return mostrado;
    }

    public void setMostrado(int mostrado) {
        this.mostrado = mostrado;
    }

    public float getTiempo_remanente() {
        return tiempo_remanente;
    }

    public void setTiempo_remanente(float tiempo_remanente) {
        this.tiempo_remanente = tiempo_remanente;
    }

    public float getFin_formateo_A() {
        return fin_formateo_A;
    }

    public void setFin_formateo_A(float fin_formateo_A) {
        this.fin_formateo_A = fin_formateo_A;
    }

    public float getFin_formateo_B() {
        return fin_formateo_B;
    }

    public void setFin_formateo_B(float fin_formateo_B) {
        this.fin_formateo_B = fin_formateo_B;
    }

    public void setTipo_arreglo(String tipo_arreglo) {
        this.tipo_arreglo = tipo_arreglo;
    }

    public int getTecnico() {
        return tecnico;
    }

    public void setTecnico(int tecnico) {
        this.tecnico = tecnico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getIngreso_laboratorio() {
        return ingreso_laboratorio;
    }

    public void setIngreso_laboratorio(float inicio_arreglo) {
        this.ingreso_laboratorio = inicio_arreglo;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int getIdCompu() {
        return idCompu;
    }

    public void setIdCompu(int idCompu) {
        this.idCompu = idCompu;
    }


    public String getTipo_arreglo() {
        return tipo_arreglo;
    }


    public float getRandom_fin_arreglo() {
        return random_fin_arreglo;
    }

    public void setRandom_fin_arreglo(float random_fin_arreglo) {
        this.random_fin_arreglo = random_fin_arreglo;
    }

    public float getFin_arreglo() {
        return fin_arreglo;
    }

    public void setFin_arreglo(float fin_arreglo) {
        this.fin_arreglo = fin_arreglo;
    }
}
