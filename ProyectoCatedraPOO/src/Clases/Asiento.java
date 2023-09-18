package Clases;

public class Asiento {
    private int id_asiento;
    private String numero_asiento;
    private int asientoComprado;

    public Asiento(int id_asiento, String numero_asiento, int asientoComprado) {
        this.id_asiento = id_asiento;
        this.numero_asiento = numero_asiento;
        this.asientoComprado = asientoComprado;
    }

    public int getId_asiento() {
        return id_asiento;
    }

    public String getNumero_asiento() {
        return numero_asiento;
    }

    public int getAsientoComprado() {
        return asientoComprado;
    }
}
