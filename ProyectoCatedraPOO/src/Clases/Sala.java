package Clases;

public class Sala {
    private String numeroSala;
    private String nombreSucursal;

    public Sala(String numeroSala, String nombreSucursal) {
        this.numeroSala = numeroSala;
        this.nombreSucursal = nombreSucursal;
    }

    public String getNumeroSala() {
        return numeroSala;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    @Override
    public String toString() {
        return numeroSala + "; " + nombreSucursal;
    }
}

