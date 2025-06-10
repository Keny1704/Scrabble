package Back;

public class Ficha {

    private String letra;
    final private int puntosLetra;
    private Casilla casillaAsignada;

    public Ficha() {
        letra = "#";
        puntosLetra = 0;
        casillaAsignada = null;
    }

    public void setCasillaAsignada(Casilla nuevaCasilla) {
        casillaAsignada = nuevaCasilla;
    }

    public Casilla getCasillaAsignada() {
        return casillaAsignada;
    }

    public void eliminarCasillaAsignada() {
        casillaAsignada = null;
    }

    public Ficha(String nuevaLetra, int nuevosPuntos) {
        letra = nuevaLetra;
        puntosLetra = nuevosPuntos;
    }
    
    public Ficha(String nuevaLetra) {
        letra = nuevaLetra;
        puntosLetra = 0;
    }
    
    public String getLetra() {
        return letra;
    }

    public void setLetra(String nuevaLetra) {
        letra = nuevaLetra;
    }

    public int getPuntos() {
        return puntosLetra;
    }

    @Override
    public String toString() {
        return letra;
    } 
}
